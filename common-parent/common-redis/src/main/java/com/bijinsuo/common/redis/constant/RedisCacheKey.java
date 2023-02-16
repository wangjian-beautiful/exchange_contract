package com.bijinsuo.common.redis.constant;

/**
 * @author Watson
 */
public class RedisCacheKey {
    //用户持仓浮动盈亏排序key
    public final static String POSITION_SORT_PNL_KEY = "position_sort_pnl_key";

    public final static String POSITION_SET_PREFIX = "position_set_prefix:";
    public final static String POSITION_DATA_PREFIX = "position_data_prefix:";


    //下期资金费率Key
    public final static String FUNDING_RATE_KEY = "funding_rate_key";
    /**
     * 最新行情
     */
    public final static String LATEST_PRICE_KEY = "latest_price_key";






    /**
     * 临时保存已撤销的委托单的ID的key
     */
    public final static String CANCELLED_ORDERS_KEY = "cancelled_orders";

    /**
     * 用户可用
     */
    public final static String  USER_ACCOUNT_NORMAL="user_account:normal:";

    /**
     * 用户冻结
     */
    public final static String  USER_ACCOUNT_FROZEN="user_account:frozen:";

    /**
     * 用户保证金
     */
    public final static String  USER_ACCOUNT_BOND="user_account:bond:";
}
