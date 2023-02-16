package com.bjs.contract;

import com.bijinsuo.common.redis.config.EnableRedis;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: Doctor
 * @Date: 2022/7/1 11:29
 * @Description:
 */
@SpringBootApplication
@EnableDubbo
@EnableRedis
public class CoMatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoMatchApplication.class);
    }
}
