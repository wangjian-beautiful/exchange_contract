package com.bjs.contract;

import com.bijinsuo.common.identify.UserIdentityAdvice;
import com.bijinsuo.common.identify.mvc.TokenFilter;
import com.bijinsuo.common.redis.config.EnableRedis;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Auther: Doctor
 * @Date: 2022/7/1 11:29
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bjs.contract", "com.bijinsuo.common"}, excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {TokenFilter.class, UserIdentityAdvice.class})})
@EnableDubbo
@EnableRedis
public class CoOpenApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoOpenApiApplication.class);
    }
}
