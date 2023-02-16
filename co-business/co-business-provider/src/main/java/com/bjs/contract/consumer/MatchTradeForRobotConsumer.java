package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.mq.topic.ConsumerGroupConstant;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bjs.contract.service.MatchTradeAggregationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Eric
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.ROBOT_MATCH_TRADE, consumerGroup = TopicConstant.ROBOT_MATCH_TRADE + ConsumerGroupConstant.BUSINESS, consumeMode = ConsumeMode.ORDERLY)
@Slf4j
public class MatchTradeForRobotConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Resource
    MatchTradeAggregationService matchTradeAggregationService;

    @Override
    public void onMessage(String msg) {
        log.info("MatchTradeForRobotConsumer start consumer msg {}", msg);
        matchTradeAggregationService.handleForRobot(JSON.parseObject(msg, MatchTradeDTO.class));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setMaxReconsumeTimes(3);
    }
}