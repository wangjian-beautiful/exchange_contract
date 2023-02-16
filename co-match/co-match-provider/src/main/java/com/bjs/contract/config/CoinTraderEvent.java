package com.bjs.contract.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.enums.MatchInfoTypeEnum;
import com.bijinsuo.common.utils.enums.OrderStatus;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoTrade;
import com.bjs.contract.entity.vo.MatchOrderVO;
import com.bjs.contract.mapper.MatchCoOrderMapper;
import com.bjs.contract.mapper.CoTradeMapper;
import com.bjs.contract.match.CoinTrader;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nike
 * @date 2022年11月10日 13:12
 */
@Component
public class CoinTraderEvent {
    @Resource
    private CoinTraderFactory coinTraderFactory;
    //程序启动时候添加启动数据，防止和mq信息重复搓单问题
    public static ConcurrentHashMap<String, MatchOrderVO> concurrentHashMap = new ConcurrentHashMap<>();
    @Resource
    private MatchCoOrderMapper coOrderMapper;
    @Resource
    private CoTradeMapper coTradeMapper;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @PostConstruct
    public void onApplicationEvent() {
        //获取初始化的币对队列
        Map<String, CoinTrader> traders = coinTraderFactory.getTraderMap();
        List<MatchOrderVO> completedOrders = new ArrayList<>();
        traders.forEach((symbol, trader) -> {
            List<CoOrder> orderList = coOrderMapper.getCoOrderAll(symbol);
            for (CoOrder coOrder : orderList) {
                MatchOrderVO exchangeOrder = new MatchOrderVO();
                exchangeOrder.setId(coOrder.getId());
                exchangeOrder.setUserId(coOrder.getUid());
                exchangeOrder.setSide(coOrder.getOperateSide());
                exchangeOrder.setPrice(coOrder.getPrice());
                if (coOrder.getMatchType()==2){
                    exchangeOrder.setVolume(coOrder.getVolumeBase());
                }else {
                    exchangeOrder.setVolume(coOrder.getVolumeQuote());
                }
                exchangeOrder.setSymbol(symbol);
                exchangeOrder.setType(coOrder.getMatchType());
                exchangeOrder.setStatus(coOrder.getStatus());
                if (exchangeOrder.getUserId() == 10000) {
                    exchangeOrder.setRobot(1);
                } else {
                    exchangeOrder.setRobot(0);
                }
                BigDecimal tradedAmount = BigDecimal.ZERO;
                BigDecimal turnover = BigDecimal.ZERO;

                LambdaQueryWrapper<CoTrade> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(CoTrade::getOrderId, coOrder.getId());
                List<CoTrade> exTradeList = coTradeMapper.selectList(queryWrapper);
                if (!exTradeList.isEmpty()) {
                    for (CoTrade exTrade : exTradeList) {
                        //统计成交数量
                        tradedAmount = tradedAmount.add(exTrade.getVolumeQuote());
                        //统计市价单的已成交金额
                        turnover = turnover.add(exTrade.getVolumeBase().multiply(exTrade.getPrice()));
                    }
                }
                exchangeOrder.setDealVolume(tradedAmount);
                exchangeOrder.setDealMoney(turnover);

                //判断是否为待撤销
                if (exchangeOrder.getStatus() == OrderStatus.PENDING_CANCEL.value) {
                    MatchTradeDTO dto=new MatchTradeDTO();
                    dto.setOrderId(exchangeOrder.getId());
                    dto.setMatchInfoType(MatchInfoTypeEnum.cancel_order);
                    dto.setSymbol(symbol);
                    rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_TRADE, JSON.toJSONString(dto),symbol);
                } else {
                    if (!exchangeOrder.isCompleted()) {
                        //将启动的数据放入 防止启动后和mq里面的数据重复
                        concurrentHashMap.put(exchangeOrder.getId().toString(), exchangeOrder);
                        trader.trade(exchangeOrder, false);
                    } else {
                        completedOrders.add(exchangeOrder);
                    }
                }
            }
            //判断已完成的订单发送消息通知
            if (completedOrders.size() > 0) {
                rocketMQTemplate.syncSend(TopicConstant.EXCHANGE_ORDER_COMPLETED, JSON.toJSONString(completedOrders));
            }
            trader.setReady(true);
        });
    }

}
