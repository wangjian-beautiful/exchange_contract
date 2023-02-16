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
@SpringBootApplication(scanBasePackages = {"com.bjs.contract", "com.bijinsuo.common"})
@EnableDubbo
@EnableRedis
public class CoAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoAppApiApplication.class);
    }
}
