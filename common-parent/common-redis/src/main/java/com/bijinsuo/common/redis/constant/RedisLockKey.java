package com.bijinsuo.common.redis.constant;

/**
 * @author Watson
 */
public class RedisLockKey {

    /**
     * 爆仓/穿仓/资金费率扣减强制平仓时锁定用户的key
     * 仅限制于这三种业务，别乱用
     */
    public final static String USER_LOCK_PREFIX = "user_lock:";


    /**
     * 用户下单/更改杠杆倍数  锁Key
     */
    public final static String USER_ORDERS_LOCK_PREFIX = "user_orders_lock_prefix:";
}
