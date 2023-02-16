package com.bjs.contract.consumer;

import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bjs.contract.service.CoOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Eric
 */
//@Component
//@RocketMQMessageListener(topic = TopicConstant.ORDER_COMPLETE_CLEAR, consumerGroup = TopicConstant.ORDER_COMPLETE_CLEAR)
@Slf4j
public class OrderCompleteClearConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Resource
    CoOrderService coOrderService;

    @Override
    public void onMessage(String msg) {
        log.info("OrderCompleteClearConsumer start consumer msg {}", msg);
        coOrderService.refundOrderFeeAmount(Long.valueOf(msg));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setMaxReconsumeTimes(10);
    }
}
