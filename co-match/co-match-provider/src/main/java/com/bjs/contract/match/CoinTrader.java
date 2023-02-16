package com.bjs.contract.match;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bijinsuo.common.utils.enums.ExchangeOrderType;
import com.bijinsuo.common.utils.enums.MatchInfoTypeEnum;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import com.bjs.contract.config.CoinTraderEvent;
import com.bjs.contract.entity.vo.MatchOrderVO;
import com.bjs.contract.entity.vo.MergeOrder;
import com.bjs.contract.entity.vo.TradePlate;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author nike
 * @date 2022年11月10日 10:39
 */
public class CoinTrader {
    private final String symbol;

    //交易币种的精度
    private final int coinScale;

    private final Logger logger = LoggerFactory.getLogger(CoinTrader.class);
    //买入限价订单链表，价格从高到低排列
    private TreeMap<BigDecimal, MergeOrder> buyLimitPriceQueue;
    //卖出限价订单链表，价格从低到高排列
    private TreeMap<BigDecimal, MergeOrder> sellLimitPriceQueue;

    //机器人买入限价订单链表，价格从高到低排列
    private TreeMap<BigDecimal, MergeOrder> robotBuyLimitPriceQueue;
    //机器人卖出限价订单链表，价格从低到高排列
    private TreeMap<BigDecimal, MergeOrder> robotSellLimitPriceQueue;

    //买入市价订单链表，按时间从小到大排序
    private LinkedList<MatchOrderVO> buyMarketQueue;
    //卖出市价订单链表，按时间从小到大排序
    private LinkedList<MatchOrderVO> sellMarketQueue;
    //卖盘盘口信息
    private TradePlate sellTradePlate;
    //买盘盘口信息
    private TradePlate buyTradePlate;
    //是否暂停交易
    private final boolean tradingHalt = false;

    private boolean ready = false;

    private RocketMQTemplate rocketMQTemplate;

    private RedisUtil redisUtil;

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public void setRocketMQTemplate(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isTradingHalt() {
        return this.tradingHalt;
    }

    public boolean getReady() {
        return this.ready;
    }

    public CoinTrader(String symbol, int coinScale) {
        this.symbol = symbol;
        this.coinScale = coinScale;
        initialize();
    }

    /**
     * 初始化交易线程
     */
    public void initialize() {
        logger.info("init CoinTrader for symbol {}", symbol);
        //买单队列价格降序排列
        this.buyLimitPriceQueue = new TreeMap<>(Comparator.reverseOrder());
        //卖单队列价格升序排列
        this.sellLimitPriceQueue = new TreeMap<>(Comparator.naturalOrder());

        this.robotBuyLimitPriceQueue = new TreeMap<>(Comparator.reverseOrder());
        this.robotSellLimitPriceQueue = new TreeMap<>(Comparator.naturalOrder());

        this.buyMarketQueue = new LinkedList<>();
        this.sellMarketQueue = new LinkedList<>();
        this.sellTradePlate = new TradePlate(symbol, OperateSideEnum.SELL);
        this.buyTradePlate = new TradePlate(symbol, OperateSideEnum.BUY);
    }

    public void trade(MatchOrderVO exchangeOrder, Boolean check) {
        if (tradingHalt) {
            return;
        }
        if (check) {
            //判断订单是否已内存里面
            MatchOrderVO value = CoinTraderEvent.concurrentHashMap.get(exchangeOrder.getId().toString());
            if (value != null) {
                return;
            }
        }
        if (!symbol.equalsIgnoreCase(exchangeOrder.getSymbol())) {
            return;
        }
        //判断数量
        if (exchangeOrder.getVolume().compareTo(BigDecimal.ZERO) <= 0 ||
                exchangeOrder.getVolume().subtract(exchangeOrder.getDealVolume()).compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        TreeMap<BigDecimal, MergeOrder> limitPriceOrderList;
        TreeMap<BigDecimal, MergeOrder> robotLimitPriceOrderList;
        LinkedList<MatchOrderVO> marketPriceOrderList;
        if (Objects.equals(exchangeOrder.getSide(), OperateSideEnum.BUY)) {
            limitPriceOrderList = sellLimitPriceQueue;
            robotLimitPriceOrderList = robotSellLimitPriceQueue;
            marketPriceOrderList = sellMarketQueue;
        } else {
            limitPriceOrderList = buyLimitPriceQueue;
            robotLimitPriceOrderList = robotBuyLimitPriceQueue;
            marketPriceOrderList = buyMarketQueue;
        }

        //判断订单类型是否为市价订单
        if (Objects.equals(exchangeOrder.getType(), ExchangeOrderType.MARKET_PRICE.value)) {
            //与限价单交易
            matchMarketPriceWithLPList(limitPriceOrderList, exchangeOrder, robotLimitPriceOrderList);
        } else if (Objects.equals(exchangeOrder.getType(), ExchangeOrderType.LIMIT_PRICE.value)) {
            //限价单价格必须大于0
            if (exchangeOrder.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                return;
            }
            //先与限价单交易
            matchLimitPriceWithLPList(limitPriceOrderList, exchangeOrder, false, robotLimitPriceOrderList);
            if (exchangeOrder.getVolume().compareTo(exchangeOrder.getDealVolume()) > 0) {
                //logger.info(">>>>限价单未交易完>>>>与市价单交易>>>>");
                //后与市价单交易
                matchLimitPriceWithMPList(marketPriceOrderList, exchangeOrder);
            }
        }

    }


    /**
     * 市价委托单与限价对手单列表交易
     *
     * @param lpList       限价对手单列表
     * @param focusedOrder 待交易订单
     */
    public void matchMarketPriceWithLPList(TreeMap<BigDecimal, MergeOrder> lpList,
                                           MatchOrderVO focusedOrder,
                                           TreeMap<BigDecimal, MergeOrder> robotList) {

        List<MatchOrderVO> completedOrders = new ArrayList<>();
        synchronized (this) {
            Iterator<Map.Entry<BigDecimal, MergeOrder>> mergeOrderIterator = lpList.entrySet().iterator();
            boolean exitLoop = false;
            boolean robotExitLoop = false;
            matchMarket(focusedOrder, completedOrders, mergeOrderIterator, exitLoop);
            //如果还没有交易完，就和机器人进行交易
            if (Objects.equals(focusedOrder.getSide(), OperateSideEnum.SELL) &&
                    focusedOrder.getDealVolume().compareTo(focusedOrder.getVolume()) < 0
                    || Objects.equals(focusedOrder.getSide(), OperateSideEnum.BUY) &&
                    focusedOrder.getDealMoney().compareTo(focusedOrder.getVolume()) < 0) {
                Iterator<Map.Entry<BigDecimal, MergeOrder>> robotMergeOrderIterator = robotList.entrySet().iterator();
                matchMarket(focusedOrder, completedOrders, robotMergeOrderIterator, robotExitLoop);
            }
        }

        //如果还没有交易完，订单压入列表中,市价买单按成交量算
        if (Objects.equals(focusedOrder.getSide(), OperateSideEnum.SELL) &&
                focusedOrder.getDealVolume().compareTo(focusedOrder.getVolume()) < 0
                || Objects.equals(focusedOrder.getSide(), OperateSideEnum.BUY) &&
                focusedOrder.getDealMoney().compareTo(focusedOrder.getVolume()) < 0) {
            addMarketPriceOrder(focusedOrder);
        }

        if (completedOrders.size() > 0) {
            TradePlate plate = Objects.equals(focusedOrder.getSide(), OperateSideEnum.BUY) ? sellTradePlate : buyTradePlate;
            sendTradePlateMessage(plate);
        }
    }


    /**
     * 限价委托单与限价队列匹配
     *
     * @param lpList       限价对手单队列
     * @param focusedOrder 交易订单
     */
    public void matchLimitPriceWithLPList(TreeMap<BigDecimal, MergeOrder> lpList,
                                          MatchOrderVO focusedOrder,
                                          boolean canEnterList,
                                          TreeMap<BigDecimal, MergeOrder> robotLpList) {

        List<MatchOrderVO> completedOrders = new ArrayList<>();
        synchronized (this) {
            matchLimit(focusedOrder, lpList, completedOrders);
            //如果还没有交易完，再和机器人交易
            if (focusedOrder.getDealVolume().compareTo(focusedOrder.getVolume()) < 0) {
                matchLimit(focusedOrder, robotLpList, completedOrders);
            }
        }
        //如果还没有交易完，订单压入列表中
//        if (focusedOrder.getDealVolume().compareTo(focusedOrder.getVolume()) < 0) {
//            addLimitPriceOrder(focusedOrder);
//        }

        if (completedOrders.size() > 0) {
            TradePlate plate = Objects.equals(focusedOrder.getSide(), OperateSideEnum.BUY) ? sellTradePlate : buyTradePlate;
            sendTradePlateMessage(plate);
        }

    }



    /**
     * 限价委托单与市价队列匹配
     *
     * @param mpList       市价对手单队列
     * @param focusedOrder 交易订单
     */
    public void matchLimitPriceWithMPList(LinkedList<MatchOrderVO> mpList, MatchOrderVO focusedOrder) {

//        List<MatchOrderVO> completedOrders = new ArrayList<>();
        synchronized (this) {
            Iterator<MatchOrderVO> iterator = mpList.iterator();
            while (iterator.hasNext()) {
                MatchOrderVO matchOrder = iterator.next();
                MatchTradeDTO trade = processMatch(focusedOrder, matchOrder);
                if (trade != null) {
                    //判断两个是不是都是机器人订单
                    if (focusedOrder.getRobot() == 1 && matchOrder.getRobot() == 1) {
                        rocketMQTemplate.syncSendOrderly(TopicConstant.ROBOT_MATCH_TRADE, JSON.toJSONString(trade), focusedOrder.getSymbol());
                    } else {
                        logger.info("撮合完成数据：{}",JSON.toJSONString(trade));
                        rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_TRADE, JSON.toJSONString(trade), focusedOrder.getSymbol());
                    }
                }
                //判断匹配单是否完成，市价单amount为成交量
                if (matchOrder.isCompleted()) {
                    iterator.remove();
//                    completedOrders.add(matchOrder);
                }
                //判断吃单是否完成，判断成交量是否完成
                if (focusedOrder.isCompleted()) {
                    //交易完成
//                    completedOrders.add(focusedOrder);
                    //退出循环
                    break;
                }
            }
        }
        //如果还没有交易完，订单压入列表中
        if (focusedOrder.getDealVolume().compareTo(focusedOrder.getVolume()) < 0) {
            addLimitPriceOrder(focusedOrder);
        }
    }




    private void matchLimit(MatchOrderVO focusedOrder, TreeMap<BigDecimal, MergeOrder> robotLpList,
                            List<MatchOrderVO> completedOrders) {
        Iterator<Map.Entry<BigDecimal, MergeOrder>> robotMergeOrderIterator = robotLpList.entrySet().iterator();
        boolean robotExitLoop = false;
        while (!robotExitLoop && robotMergeOrderIterator.hasNext()) {
            Map.Entry<BigDecimal, MergeOrder> entry = robotMergeOrderIterator.next();
            MergeOrder mergeOrder = entry.getValue();
            Iterator<MatchOrderVO> orderIterator = mergeOrder.iterator();
            //买入单需要匹配的价格不大于委托价，否则退出
            if (Objects.equals(focusedOrder.getSide(), OperateSideEnum.BUY) &&
                    mergeOrder.getPrice().compareTo(focusedOrder.getPrice()) > 0) {
                break;
            }
            //卖出单需要匹配的价格不小于委托价，否则退出
            if (Objects.equals(focusedOrder.getSide(), OperateSideEnum.SELL) &&
                    mergeOrder.getPrice().compareTo(focusedOrder.getPrice()) < 0) {
                break;
            }

            while (orderIterator.hasNext()) {
                MatchOrderVO matchOrder = orderIterator.next();
                //处理匹配
                MatchTradeDTO trade = processMatch(focusedOrder, matchOrder);
                if (trade != null) {
                    //判断两个是不是都是机器人订单
                    if (focusedOrder.getRobot() == 1 && matchOrder.getRobot() == 1) {
                        rocketMQTemplate.syncSendOrderly(TopicConstant.ROBOT_MATCH_TRADE, JSON.toJSONString(trade), focusedOrder.getSymbol());
                    } else {
                        logger.info("撮合完成数据：{}",JSON.toJSONString(trade));
                        rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_TRADE, JSON.toJSONString(trade), focusedOrder.getSymbol());
                    }
                }
                //判断匹配单是否完成
                if (matchOrder.isCompleted()) {
                    //当前匹配的订单完成交易，删除该订单
                    orderIterator.remove();
                    completedOrders.add(matchOrder);
                }
                //判断交易单是否完成
                if (focusedOrder.isCompleted()) {
                    //交易完成
                    completedOrders.add(focusedOrder);
                    //退出循环
                    robotExitLoop = true;
                    break;
                }
            }
            if (mergeOrder.size() == 0) {
                robotMergeOrderIterator.remove();
            }
        }
    }


    private void matchMarket(MatchOrderVO focusedOrder,
                             List<MatchOrderVO> completedOrders,
                             Iterator<Map.Entry<BigDecimal, MergeOrder>> mergeOrderIterator,
                             boolean exitLoop) {
        while (!exitLoop && mergeOrderIterator.hasNext()) {
            Map.Entry<BigDecimal, MergeOrder> entry = mergeOrderIterator.next();
            MergeOrder mergeOrder = entry.getValue();
            Iterator<MatchOrderVO> orderIterator = mergeOrder.iterator();
            while (orderIterator.hasNext()) {
                MatchOrderVO matchOrder = orderIterator.next();
                //处理匹配
                MatchTradeDTO trade = processMatch(focusedOrder, matchOrder);
                if (trade != null) {
                    //判断两个是不是都是机器人订单
                    if (focusedOrder.getRobot() == 1 && matchOrder.getRobot() == 1) {
                        rocketMQTemplate.syncSendOrderly(TopicConstant.ROBOT_MATCH_TRADE, JSON.toJSONString(trade), focusedOrder.getSymbol());
                    } else {
                        logger.info("撮合完成数据：{}",JSON.toJSONString(trade));
                        rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_TRADE, JSON.toJSONString(trade), focusedOrder.getSymbol());
                    }
                }
                //判断匹配单是否完成
                if (matchOrder.isCompleted()) {
                    //当前匹配的订单完成交易，删除该订单
                    orderIterator.remove();
                    completedOrders.add(matchOrder);
                }
                //判断焦点订单是否完成
                if (focusedOrder.isCompleted()) {
                    completedOrders.add(matchOrder);
                    //退出循环
                    exitLoop = true;
                    break;
                }
            }
            if (mergeOrder.size() == 0) {
                mergeOrderIterator.remove();
            }
        }
    }
    /**
     * 处理两个匹配的委托订单
     *
     * @param focusedOrder 焦点单
     * @param matchOrder   匹配单
     * @return ExchangeTrade
     */
    private MatchTradeDTO processMatch(MatchOrderVO focusedOrder, MatchOrderVO matchOrder) {

        //需要交易的数量，成交量,成交价，可用数量
        BigDecimal needAmount, dealPrice, availAmount;
        //如果匹配单是限价单，则以其价格为成交价
        if (Objects.equals(matchOrder.getType(), ExchangeOrderType.LIMIT_PRICE.value)) {
            dealPrice = matchOrder.getPrice();
        } else {
            dealPrice = focusedOrder.getPrice();
        }
        //成交价必须大于0
        if (dealPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        needAmount = calculateTradedAmount(focusedOrder, dealPrice);

        availAmount = calculateTradedAmount(matchOrder, dealPrice);
        //计算成交量
        BigDecimal tradedAmount = (availAmount.compareTo(needAmount) >= 0 ? needAmount : availAmount);

        logger.info("dealPrice={},amount={}", dealPrice, tradedAmount);
        //如果成交额为0说明剩余额度无法成交，退出
        if (tradedAmount.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        //计算成交额,成交额要保留足够精度
        BigDecimal turnover = tradedAmount.multiply(dealPrice);
        matchOrder.setDealVolume(matchOrder.getDealVolume().add(tradedAmount));
        matchOrder.setDealMoney(matchOrder.getDealMoney().add(turnover));
        focusedOrder.setDealVolume(focusedOrder.getDealVolume().add(tradedAmount));
        focusedOrder.setDealMoney(focusedOrder.getDealMoney().add(turnover));

        MatchTradeDTO tradeDTO = new MatchTradeDTO();
        tradeDTO.setOrderId(focusedOrder.getId());
        tradeDTO.setMatchInfoType(MatchInfoTypeEnum.match_order);
        tradeDTO.setSymbol(symbol);
        MatchTradeDetailsDTO detailsDTO = new MatchTradeDetailsDTO();

        detailsDTO.setAmount(tradedAmount);
        detailsDTO.setTrendSide(focusedOrder.getSide());
        detailsDTO.setPrice(dealPrice);
        detailsDTO.setTurnover(turnover);
        //校正市价单剩余成交额
        if (Objects.equals(ExchangeOrderType.MARKET_PRICE.value, focusedOrder.getType()) &&
                Objects.equals(focusedOrder.getSide(), OperateSideEnum.BUY)) {
            BigDecimal adjustTurnover = adjustMarketOrderTurnover(focusedOrder, dealPrice);
//            detailsDTO.setTurnover(turnover.add(adjustTurnover));
        } else if (Objects.equals(ExchangeOrderType.MARKET_PRICE.value, matchOrder.getType()) &&
                Objects.equals(matchOrder.getSide(), OperateSideEnum.BUY)) {
            BigDecimal adjustTurnover = adjustMarketOrderTurnover(matchOrder, dealPrice);
//            detailsDTO.setTurnover(turnover.add(adjustTurnover));
        }
        if (Objects.equals(focusedOrder.getSide(), OperateSideEnum.BUY)) {
            detailsDTO.setBuyOrderId(focusedOrder.getId());
            detailsDTO.setSellOrderId(matchOrder.getId());
            //判断订单是否完成
            if (focusedOrder.isCompleted()) {
                detailsDTO.setBuyFinish(true);
            }
            if (matchOrder.isCompleted()) {
                detailsDTO.setSellFinish(true);
            }
        } else {
            detailsDTO.setBuyOrderId(matchOrder.getId());
            detailsDTO.setSellOrderId(focusedOrder.getId());
            //判断订单是否完成
            if (focusedOrder.isCompleted()) {
                detailsDTO.setSellFinish(true);
            }
            if (matchOrder.isCompleted()) {
                detailsDTO.setBuyFinish(true);
            }
        }
        detailsDTO.setTime(System.currentTimeMillis());
        tradeDTO.setDetailsDTO(detailsDTO);
        if (Objects.equals(matchOrder.getType(), ExchangeOrderType.LIMIT_PRICE.value)) {
            if (Objects.equals(matchOrder.getSide(), OperateSideEnum.BUY)) {
                buyTradePlate.remove(matchOrder, tradedAmount);
            } else {
                sellTradePlate.remove(matchOrder, tradedAmount);
            }
        }
        return tradeDTO;
    }


    /**
     * 调整市价单剩余成交额，当剩余成交额不足时设置订单完成
     *
     * @param order     委托单
     * @param dealPrice 成交价
     * @return BigDecimal
     */
    private BigDecimal adjustMarketOrderTurnover(MatchOrderVO order, BigDecimal dealPrice) {
        if (Objects.equals(order.getSide(), OperateSideEnum.BUY) &&
                Objects.equals(order.getType(), ExchangeOrderType.MARKET_PRICE.value)) {
            BigDecimal leftTurnover = order.getVolume().subtract(order.getDealMoney());
            logger.info("@@@@剩余成交额:,{},订单号：{}", leftTurnover, order.getId());
            //判断剩余金额是否满足最低数量精度
            if (leftTurnover.divide(dealPrice, coinScale, RoundingMode.DOWN)
                    .compareTo(BigDecimal.ZERO) == 0) {
                order.setDealMoney(order.getVolume().add(leftTurnover));
                return leftTurnover;
            }
        }
        return BigDecimal.ZERO;
    }


    /**
     * 计算委托单剩余可成交的数量
     *
     * @param order     委托单
     * @param dealPrice 成交价
     * @return BigDecimal
     */
    private BigDecimal calculateTradedAmount(MatchOrderVO order, BigDecimal dealPrice) {
        if (Objects.equals(order.getSide(), OperateSideEnum.BUY) &&
                Objects.equals(order.getType(), ExchangeOrderType.MARKET_PRICE.value)) {
            //剩余成交量
            BigDecimal leftTurnover = order.getVolume().subtract(order.getDealMoney());
            return leftTurnover.divide(dealPrice, coinScale, RoundingMode.DOWN);
        } else {
            return order.getVolume().subtract(order.getDealVolume());
        }
    }

    private void addMarketPriceOrder(MatchOrderVO exchangeOrder) {
        if (!Objects.equals(exchangeOrder.getType(), ExchangeOrderType.MARKET_PRICE.value)) {
            return;
        }
        logger.info("addMarketPriceOrder,orderId = {}", exchangeOrder.getId());
        LinkedList<MatchOrderVO> list = Objects.equals(exchangeOrder.getSide(), OperateSideEnum.BUY) ? buyMarketQueue : sellMarketQueue;
        synchronized (this) {
            list.addLast(exchangeOrder);
        }
    }

    /**
     * 增加限价订单到队列，买入单按从价格高到低排，卖出单按价格从低到高排
     *
     * @param exchangeOrder 数据
     */
    public void addLimitPriceOrder(MatchOrderVO exchangeOrder) {
        if (!Objects.equals(exchangeOrder.getType(), ExchangeOrderType.LIMIT_PRICE.value)) {
            return;
        }
        TreeMap<BigDecimal, MergeOrder> list;
        if (Objects.equals(exchangeOrder.getSide(), OperateSideEnum.BUY)) {
            if (exchangeOrder.getRobot()==1){
                list=robotBuyLimitPriceQueue;
            }else {
                list = buyLimitPriceQueue;
            }
            buyTradePlate.add(exchangeOrder);
            if (ready) {
                sendTradePlateMessage(buyTradePlate);
            }
        } else {
            if (exchangeOrder.getRobot()==1){
                list = robotSellLimitPriceQueue;
            }else {
                list = sellLimitPriceQueue;
            }
            sellTradePlate.add(exchangeOrder);
            if (ready) {
                sendTradePlateMessage(sellTradePlate);
            }
        }
        synchronized (this) {
            MergeOrder mergeOrder = list.get(exchangeOrder.getPrice());
            if (mergeOrder == null) {
                mergeOrder = new MergeOrder();
                mergeOrder.add(exchangeOrder);
                list.put(exchangeOrder.getPrice(), mergeOrder);
            } else {
                mergeOrder.add(exchangeOrder);
            }
        }
    }


    /**
     * 发送盘口变化消息
     *
     * @param plate 数据
     */
    public void sendTradePlateMessage(TradePlate plate) {
        //防止并发引起数组越界，造成盘口倒挂
        synchronized (this) {
            try {
                logger.info("盘口数据：{}", JSON.toJSONString(plate));
                tradePlateRedisAdd(plate);
            } catch (Exception e) {
                logger.error("存储盘口数据错误：", e);
            }
            //rocketMQTemplate.syncSend(TopicConstant.EXCHANGE_TRADE_PLATE, JSON.toJSONString(plate));
        }
    }

    private void tradePlateRedisAdd(TradePlate plate) {
        String key = "CONTRACT_TRADE_PLATE:" + plate.getSymbol() + "_" + plate.getDirection();
        redisUtil.set(key, JSON.toJSONString(plate.getItems()));
    }

    /**
     * 取消委托订单
     *
     * @param exchangeOrder 订单
     * @return ExchangeOrder
     */
    public MatchOrderVO cancelOrder(MatchOrderVO exchangeOrder) {
        logger.info("cancelOrder,orderId={}", exchangeOrder.getId());
        if (Objects.equals(exchangeOrder.getType(), ExchangeOrderType.MARKET_PRICE.value)) {
            //处理市价单
            Iterator<MatchOrderVO> orderIterator;
            List<MatchOrderVO> list;
            if (Objects.equals(exchangeOrder.getSide(), OperateSideEnum.BUY)) {
                list = this.buyMarketQueue;
            } else {
                list = this.sellMarketQueue;
            }
            synchronized (this) {
                orderIterator = list.iterator();
                while ((orderIterator.hasNext())) {
                    MatchOrderVO order = orderIterator.next();
                    if (Objects.equals(order.getId(), exchangeOrder.getId())) {
                        orderIterator.remove();
                        MatchTradeDTO dto=new MatchTradeDTO();
                        dto.setOrderId(order.getId());
                        dto.setMatchInfoType(MatchInfoTypeEnum.cancel_order);
                        dto.setSymbol(order.getSymbol());
                        logger.info("取消委托订单：{}",JSON.toJSONString(dto));
                        rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_TRADE, JSON.toJSONString(dto),order.getSymbol());
                        onRemoveOrder(order);
                        return order;
                    }
                }
            }
        } else {
            //处理限价单
            TreeMap<BigDecimal, MergeOrder> list;
            if (Objects.equals(exchangeOrder.getSide(), OperateSideEnum.BUY)) {
                list = this.buyLimitPriceQueue;
            } else {
                list = this.sellLimitPriceQueue;
            }
            synchronized (this) {
                MergeOrder mergeOrder = list.get(exchangeOrder.getPrice());
                if (mergeOrder != null) {
                    Iterator<MatchOrderVO> orderIterator = mergeOrder.iterator();
                    while (orderIterator.hasNext()) {
                        MatchOrderVO order = orderIterator.next();
                        if (Objects.equals(order.getId(), exchangeOrder.getId())) {
                            orderIterator.remove();
                            if (mergeOrder.size() == 0) {
                                list.remove(exchangeOrder.getPrice());
                            }
                            MatchTradeDTO dto=new MatchTradeDTO();
                            dto.setOrderId(order.getId());
                            dto.setMatchInfoType(MatchInfoTypeEnum.cancel_order);
                            dto.setSymbol(order.getSymbol());
                            logger.info("取消委托订单：{}",JSON.toJSONString(dto));
                            rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_TRADE, JSON.toJSONString(dto),order.getSymbol());
                            onRemoveOrder(order);
                            return order;
                        }
                    }
                }
            }
        }
        return null;
    }

    private void onRemoveOrder(MatchOrderVO order) {
        if (Objects.equals(order.getType(), ExchangeOrderType.LIMIT_PRICE.value)) {
            if (Objects.equals(order.getSide(), OperateSideEnum.BUY)) {
                buyTradePlate.remove(order);
                sendTradePlateMessage(buyTradePlate);
            } else {
                sellTradePlate.remove(order);
                sendTradePlateMessage(sellTradePlate);
            }
        }
    }
}
