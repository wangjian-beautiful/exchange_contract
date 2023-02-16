

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.utils.entity.MatchOrderDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.apache.rocketmq.common.message.Message;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class RockerMqTest {


    @Test
    public void testMq2() throws Exception {
        String endpoint = "10.16.64.18:9876";
//        String endpoint = "10.16.64.62:9876";
        //消息发送的目标Topic名称，需要提前创建。
//        String topic = "match_trade_go_test";
        String topic = "match_trade";
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr(endpoint);
        producer.setInstanceName("producer");
        producer.start();

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        try {
            service.scheduleAtFixedRate(() -> {
                try {
                    MatchTradeDTO orderDTO = new MatchTradeDTO();
                    orderDTO.setSymbol("BTCUSDT");
                    MatchTradeDetailsDTO trade = new MatchTradeDetailsDTO();
                    double price = new Random().nextDouble();
                    double vol = new Random().nextDouble();
                    trade.setPrice(BigDecimal.valueOf(price).setScale(4, RoundingMode.DOWN));
                    trade.setAmount(BigDecimal.valueOf(vol).setScale(4,RoundingMode.DOWN));
                    trade.setTrendSide(OperateSideEnum.BUY);
                    trade.setTurnover(trade.getPrice().multiply(trade.getAmount()));
                    trade.setTime(System.currentTimeMillis());
                    orderDTO.setDetailsDTO(trade);
                    Message msg = new Message(topic,
                            "TagA",
                            "OrderID188",
                            JSON.toJSONString(orderDTO).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    //step3.

                    SendResult sendResult = producer.send(msg);
                    System.out.println(JSON.toJSONString(sendResult, true));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            }, 0,3, TimeUnit.SECONDS);
            //step2.封装将要发送消息的内容

        } catch (Exception e) {
            //Exception code
        }
        while (true) {
            if (service.awaitTermination(100, TimeUnit.MINUTES)) {
                break;
            }
        }
        producer.shutdown();
    }


}
