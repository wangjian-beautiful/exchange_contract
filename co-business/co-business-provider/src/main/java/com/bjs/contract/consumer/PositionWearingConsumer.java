package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.mq.topic.ConsumerGroupConstant;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.PositionLiquidationDTO;
import com.bjs.contract.service.PositionWearingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.POSITION_LIQUIDATION, consumerGroup = TopicConstant.POSITION_LIQUIDATION + ConsumerGroupConstant.BUSINESS+"WINTER")
@Slf4j
public class PositionWearingConsumer implements RocketMQListener<String> {

  @Autowired
  private PositionWearingService service;

  @Override
  public void onMessage(String msg) {
    log.info("PositionLiquidationConsumer start consumer msg {}", msg);
    PositionLiquidationDTO dto = JSON.parseObject(msg, PositionLiquidationDTO.class);
    if (dto.getType() == PositionLiquidationDTO.Type.WEARING) {
      try {
        service.wear(dto);
      } catch (Exception e) {
        log.error("处理穿仓消息出错！", e);
        e.printStackTrace();
      }
    } else {
      service.liquidation(dto);
    }
  }
}