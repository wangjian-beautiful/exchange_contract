package com.bijinsuo.common.redis.utils;

import com.bijinsuo.common.redis.constant.RedisLockKey;

/**
 * @author Watson
 */
public class RedisCacheUtil {


    public static String getUserLockKey(Long uid) {
        return RedisLockKey.USER_LOCK_PREFIX + uid;
    }


    /**
     * 用户下单/更改杠杆倍数  锁Key
     */
    public static String getUserOrdersLockKey(Long uid) {
        return RedisLockKey.USER_ORDERS_LOCK_PREFIX + uid;
    }

}
