package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.mq.topic.ConsumerGroupConstant;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bjs.contract.domain.PositionDelaySyncCacheDTO;
import com.bjs.contract.entity.CoPositionOrder;
import com.bjs.contract.service.CoPositionOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Watson
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.POSITION_DELAY_SYNC_CACHE, consumerGroup = TopicConstant.POSITION_DELAY_SYNC_CACHE + ConsumerGroupConstant.BUSINESS

        , consumeMode = ConsumeMode.CONCURRENTLY, consumeThreadNumber = 10)
@Slf4j
public class PositionDelaySyncCacheConsumer implements RocketMQListener<String> {

    @Resource
    CoPositionOrderService coPositionOrderService;

    @Override
    public void onMessage(String message) {
        CoPositionOrder coPositionOrder = JSON.parseObject(message, CoPositionOrder.class);
        coPositionOrderService.delaySyncCache(coPositionOrder);
    }
}
