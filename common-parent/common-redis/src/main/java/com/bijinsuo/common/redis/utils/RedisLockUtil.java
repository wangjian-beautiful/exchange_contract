package com.bijinsuo.common.redis.utils;

import com.bijinsuo.common.utils.SpringBeanUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author Watson
 */
@Slf4j
public class RedisLockUtil {

    private RedissonClient redissonClient;

    private static RedisLockUtil redisLockUtil;


    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public static RedisLockUtil instance() {
        if (redisLockUtil == null) {
            redisLockUtil = SpringBeanUtils.getBean(RedisLockUtil.class);
        }

        return redisLockUtil;
    }

    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        return tryLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS);
    }

    /**
     * @param lockKey
     * @param waitTime  等待加锁时间
     * @param leaseTime 锁最大持续时间
     * @param unit      时间单位
     * @return
     */
    @SneakyThrows
    public boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean locked = lock.tryLock(waitTime, leaseTime, unit);
        return locked;
    }

    /**
     * 如果是重入锁，则加了几次锁就需要释放几次锁
     *
     * @param lockKey
     * @return
     */
    public boolean unLock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            if (null != lock && lock.isHeldByCurrentThread()) {//判断锁是否存在，和是否当前线程加的锁。
                lock.unlock();
                return true;
            }
            //没有这个锁或者线程不持有这个锁也返回true吧。
            return true;
        } catch (Exception e) {
            log.error("un lock error ", e);
        }
        return false;
    }
}
