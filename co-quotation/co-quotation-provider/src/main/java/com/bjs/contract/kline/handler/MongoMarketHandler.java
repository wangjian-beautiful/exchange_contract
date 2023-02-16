package com.bjs.contract.kline.handler;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.KLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoMarketHandler implements MarketHandler {
    @Autowired
    private MongoService mongoService;

    @Override
    public void handleTrade(String symbol, MatchTradeDetailsDTO trade) {
        mongoService.saveTrade(symbol,trade );
    }
    @Override
    public void handleKLine(String symbol, KLine kLine) {
        mongoService.saveKLine(symbol,kLine);
    }
}
