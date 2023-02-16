package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.enums.MatchInfoTypeEnum;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import com.bijinsuo.common.utils.enums.OperateTypeEnum;
import com.bjs.contract.config.CoinTraderFactory;
import com.bjs.contract.entity.vo.MatchOrderVO;
import com.bjs.contract.match.CoinTrader;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author nike
 * @date 2022年11月11日 17:20
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.MATCH_ORDER, consumerGroup = "match", consumeMode = ConsumeMode.ORDERLY)
public class MatchOrderConsumer implements RocketMQListener<String>{

    private static final Logger logger = LoggerFactory.getLogger(MatchOrderConsumer.class);

    @Resource
    private CoinTraderFactory traderFactory;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(String message) {
        logger.info("参数信息:{}",message);

        JSONObject jsonObject=JSON.parseObject(message);
        MatchOrderVO vo=new MatchOrderVO();
        vo.setId(jsonObject.getLong("id"));
        vo.setUserId(jsonObject.getLong("userId"));
        vo.setSide(jsonObject.getObject("side", OperateSideEnum.class));
        vo.setPrice(jsonObject.getBigDecimal("price"));
        vo.setVolume(jsonObject.getBigDecimal("volume"));
        vo.setSymbol(jsonObject.getString("symbol"));
        vo.setDealVolume(BigDecimal.ZERO);
        vo.setDealMoney(BigDecimal.ZERO);
        vo.setType(jsonObject.getInteger("type"));
        vo.setStatus(jsonObject.getInteger("status"));
        vo.setRobot(jsonObject.getInteger("robot"));
        vo.setOperateType(jsonObject.getObject("operateType", OperateTypeEnum.class));
        vo.setMatchInfoType(jsonObject.getObject("matchInfoType",MatchInfoTypeEnum.class));

        CoinTrader trader = traderFactory.getTrader(vo.getSymbol());
        if (trader == null) {
            logger.error("没有该币对的信息");
            return;
        }
        //如果当前币种交易暂停会自动取消订单
        if (trader.isTradingHalt() || !trader.getReady()) {
            logger.debug("====撮合器未准备完成,撤回当前等待的订单=====");
            //撮合器未准备完成，撤回当前等待的订单

            MatchTradeDTO dto=new MatchTradeDTO();
            dto.setOrderId(vo.getId());
            dto.setMatchInfoType(MatchInfoTypeEnum.cancel_order);
            dto.setSymbol(vo.getSymbol());
            rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_ORDER, dto,vo.getSymbol());
        } else {
            try {
                long startTick = System.currentTimeMillis();
                if (vo.getMatchInfoType()==MatchInfoTypeEnum.match_order){
                    logger.info("撮合数据：{}",JSON.toJSONString(vo));
                    trader.trade(vo, true);
                }else if (vo.getMatchInfoType()==MatchInfoTypeEnum.cancel_order){
                    trader.cancelOrder(vo);
                }else {
                    logger.debug("类型错误--无对应类型，{}",message);
                }
                logger.info("消耗时间--》，{}", System.currentTimeMillis() - startTick);
            } catch (Exception e) {
                logger.error("====交易出错，退回订单===", e);
                MatchTradeDTO dto=new MatchTradeDTO();
                dto.setOrderId(vo.getId());
                dto.setMatchInfoType(MatchInfoTypeEnum.cancel_order);
                dto.setSymbol(vo.getSymbol());
                rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_ORDER, dto,vo.getSymbol());
            }
        }
    }
}
