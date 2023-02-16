package com.bijinsuo.common.redis.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Auther: Doctor
 * @Date: 2022/7/8 10:47
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(RedisConfig.class)
public @interface EnableRedis {
}
