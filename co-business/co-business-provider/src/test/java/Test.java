import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchOrderDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bijinsuo.common.utils.entity.PositionLiquidationDTO;
import com.bijinsuo.common.utils.enums.MatchInfoTypeEnum;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoPositionOrder;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        sendTrade();
    }

    private static String getTradeBody () {
        MatchTradeDTO matchTradeDTO = new MatchTradeDTO();
        matchTradeDTO.setSymbol("BTCUSDT");
        matchTradeDTO.setMatchInfoType(MatchInfoTypeEnum.match_order);
        matchTradeDTO.setOrderId(90286l);
        MatchTradeDetailsDTO detailsDTO = new MatchTradeDetailsDTO();
        detailsDTO.setPrice(new BigDecimal("17000.00000000"));
        detailsDTO.setAmount(BigDecimal.ONE);
        detailsDTO.setTurnover(new BigDecimal("17000.00000000"));
        detailsDTO.setSellFinish(false);
        detailsDTO.setBuyFinish(true);
        detailsDTO.setBuyOrderId(90286l);
        detailsDTO.setSellOrderId(90287l);
        detailsDTO.setTrendSide(OperateSideEnum.BUY);
        matchTradeDTO.setDetailsDTO(detailsDTO);
        return JSON.toJSONString(matchTradeDTO);
    }

    private static void sendTrade() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("10.16.64.18:9876");
        producer.setInstanceName("bjs-client");
        producer.setProducerGroup("bjs");
        producer.start();

        Message message = new Message(TopicConstant.MATCH_TRADE, getTradeBody().getBytes());
        SendResult send = producer.send(message);
        System.out.println(send);
        System.out.println(send.getSendStatus());
    }

    private static String getMessageBody () {
        PositionLiquidationDTO dto = new PositionLiquidationDTO();
        dto.setUid(10021);
        dto.setType(PositionLiquidationDTO.Type.LIQUIDATION);
        dto.setFloatingProfit(new BigDecimal(-17000));
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("BTCUSDT", new BigDecimal("16000"));
        dto.setSymbolPriceMap(map);
        return JSON.toJSONString(dto);
    }

    private static void sendLiquidation () throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("10.16.64.18:9876");
        producer.setInstanceName("bjs-client");
        producer.setProducerGroup("bjs");
        producer.start();

        Message message = new Message(TopicConstant.POSITION_LIQUIDATION, getMessageBody().getBytes());
        SendResult send = producer.send(message);
        System.out.println(send);
        System.out.println(send.getSendStatus());
    }
}
