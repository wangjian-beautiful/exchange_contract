package com.bijinsuo.common.mq.topic;

/**
 * @author Watson
 */
public class TopicConstant {
    /**
     * 撮合结果 撮合出来的明细 topic
     */
    public final static String MATCH_TRADE = "match_trade";

    /**
     * 撮合结果 撮合出来的机器人明细 topic
     */
    public final static String ROBOT_MATCH_TRADE = "robot_match_trade";
    /**
     * 撮合结果 已完成的订单 topic
     */
    public static final String EXCHANGE_ORDER_COMPLETED = "exchange_order_completed";
    /**
     * 撮合结果 撤销订单 topic
     */
    public static final String EXCHANGE_ORDER_CANCEL_SUCCESS = "exchange_order_cancel_success";
    /**
     * 撮合结果 盘口数据
     */
    public static final String EXCHANGE_TRADE_PLATE = "exchange_trade_plate";

    /**
     * 发送订单数据 topic
     */
    public final static String MATCH_ORDER = "match_order";

    /**
     * 风险率计算 topic
     */
    public final static String RISK_CALCULATE = "risk_calculate";


    /**
     * 资金费结算 欠款
     */
    public final static String FUNDING_RATE_SETTLE_ARREARS = "funding_rate_settle_arrears";

    /**
     * 爆仓/穿仓
     */
    public static final String POSITION_LIQUIDATION = "position_liquidation";

    /**
     * 开仓订单完成后
     */
    public static final String ORDER_COMPLETE_CLEAR = "order_complete_clear";

    /**
     * 仓位全平，触发检查是否有剩余保证金
     */
    public static final String POSITION_CLEAR = "position_clear";

    /**
     * 持仓延时同步topic
     */
    public static final String POSITION_DELAY_SYNC_CACHE = "position_delay_sync_cache";
}
