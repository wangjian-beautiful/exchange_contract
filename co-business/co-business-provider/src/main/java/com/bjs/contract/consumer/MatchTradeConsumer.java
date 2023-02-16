package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.mq.topic.ConsumerGroupConstant;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bjs.contract.service.MatchTradeAggregationService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Watson
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.MATCH_TRADE, consumerGroup = TopicConstant.MATCH_TRADE + ConsumerGroupConstant.BUSINESS, consumeMode = ConsumeMode.ORDERLY)
@Slf4j
public class MatchTradeConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Resource
    MatchTradeAggregationService matchTradeAggregationService;

    @Override
    public void onMessage(String msg) {
        log.info("MatchTradeConsumer start consumer msg {}", msg);
        matchTradeAggregationService.handle(JSON.parseObject(msg, MatchTradeDTO.class));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setMaxReconsumeTimes(10);
    }

    public static void main(String[] args) {
        System.out.println("1");
    }
}