package com.bjs.contract.kline.handler;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.KLine;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebsocketMarketHandler implements MarketHandler {


    /**
     * 推送币种简化信息
     *
     * @param symbol
     * @param trade
     */
    @Override
    public void handleTrade(String symbol, MatchTradeDetailsDTO trade) {
        //推送行情
    }

    @Override
    public void handleKLine(String symbol, KLine kLine) {
        //推送K线数据
        String channel = String.format("market_e_%s_kline_%s", symbol, kLine.getPeriod());
        RedisUtil.instance().publish(channel, JSON.toJSONString(getKlineMsg(channel, kLine)));
    }

    private PublishKlineMsg getKlineMsg(String channel, KLine kline) {
        PublishKlineMsg msg = new PublishKlineMsg();
        msg.setTs(System.currentTimeMillis());
        msg.setTick(kline);
        msg.setChannel(channel);
        return msg;
    }

    @Data
    class PublishKlineMsg {
        private String channel;
        private Long ts;
        private KLine tick;
    }
}
