package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.domain.FundingRateSettleArrearsDTO;
import com.bijinsuo.common.mq.topic.ConsumerGroupConstant;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bjs.contract.service.SettleArrearsService;
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
@RocketMQMessageListener(topic = TopicConstant.FUNDING_RATE_SETTLE_ARREARS, consumerGroup = TopicConstant.FUNDING_RATE_SETTLE_ARREARS + ConsumerGroupConstant.BUSINESS

        , consumeMode = ConsumeMode.CONCURRENTLY, consumeThreadNumber = 10)
@Slf4j
public class FundingRateSettleArrearsConsumer implements RocketMQListener<String> {


    @Resource
    SettleArrearsService settleArrearsService;


    @Override
    public void onMessage(String message) {
        log.info("FundingRateSettleArrearsConsumer start consume msg {}", message);
        FundingRateSettleArrearsDTO dto = JSON.parseObject(message, FundingRateSettleArrearsDTO.class);
        settleArrearsService.handleArrears(dto);
    }


}
