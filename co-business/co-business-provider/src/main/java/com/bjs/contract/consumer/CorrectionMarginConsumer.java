package com.bjs.contract.consumer;

import com.bijinsuo.common.mq.topic.ConsumerGroupConstant;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bjs.contract.service.CorrectionMarginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.POSITION_CLEAR, consumerGroup = TopicConstant.POSITION_CLEAR + ConsumerGroupConstant.BUSINESS)
@Slf4j
public class CorrectionMarginConsumer implements RocketMQListener<String> {
  @Autowired
  private CorrectionMarginService service;

  @Override
  public void onMessage(String msg) {
    log.info("CorrectionMarginConsumer start consumer msg {}", msg);
    service.correct(Long.parseLong(msg));
  }
}
