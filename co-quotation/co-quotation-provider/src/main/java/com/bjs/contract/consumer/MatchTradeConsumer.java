package com.bjs.contract.consumer;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.kline.handler.MongoMarketHandler;
import com.bjs.contract.kline.handler.MongoService;
import com.bjs.contract.kline.processor.CoinProcessorFactory;
import com.bjs.contract.kline.processor.Update24HTicker;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Watson
 */
@Component
@Slf4j
@RocketMQMessageListener(topic =TopicConstant.MATCH_TRADE, consumerGroup = "kline", consumeMode = ConsumeMode.ORDERLY)
public class MatchTradeConsumer implements RocketMQListener<MatchTradeDTO>, RocketMQPushConsumerLifecycleListener {
//    TopicConstant.MATCH_TRADE
//    match_trade_go_test
    @Autowired
    MongoService mongoService;

    @Override
    public void onMessage(MatchTradeDTO matchTradeDTO) {
        if(matchTradeDTO != null){
            log.info("来自撮合消息 :{}", JSON.toJSONString(matchTradeDTO) );
            String symbol = matchTradeDTO.getSymbol();
            mongoService.saveTrade(symbol,matchTradeDTO.getDetailsDTO());
            Update24HTicker.processTrade(symbol,matchTradeDTO.getDetailsDTO());
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setMaxReconsumeTimes(5);
    }
}