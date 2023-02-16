package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bijinsuo.common.mq.topic.ConsumerGroupConstant;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchOrderDTO;
import com.bjs.contract.service.CoOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Eric
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.EXCHANGE_ORDER_COMPLETED, consumerGroup = TopicConstant.EXCHANGE_ORDER_COMPLETED + ConsumerGroupConstant.BUSINESS)
@Slf4j
public class OrderCompleteConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Resource
    CoOrderService coOrderService;

    @Override
    public void onMessage(String msg) {
        log.info("OrderCompleteConsumer start consumer msg {}", msg);
        List<MatchOrderDTO> completedOrders = JSON.parseObject(msg, new TypeReference<List<MatchOrderDTO>>() {
        });
        log.info("completedOrders ={}", completedOrders);
        if (!CollectionUtils.isEmpty(completedOrders)) {
            coOrderService.handleCompleteOrders(completedOrders);
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setMaxReconsumeTimes(3);
    }
}
