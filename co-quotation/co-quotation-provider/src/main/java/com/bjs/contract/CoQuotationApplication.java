package com.bjs.contract;

import com.bijinsuo.common.redis.config.EnableRedis;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Auther: Doctor
 * @Date: 2022/7/1 11:29
 * @Description:
 */
@SpringBootApplication
@EnableDubbo
@EnableRedis
@EnableScheduling
public class CoQuotationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoQuotationApplication.class);
    }
}
