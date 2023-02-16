package com.bjs.contract.kline.processor;



import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.KLine;
import com.bjs.contract.kline.handler.MarketHandler;
import com.bjs.contract.kline.handler.MongoService;


public interface CoinProcessor {
     String getSymbol();

     KLine createNewKLine(int range, int field, long time) ;

     void processTrade(KLine kLine, MatchTradeDetailsDTO trade);
     void setMarketService(MongoService service);

     KLine generateKLineByTrade(int range, int field, long time) ;

     KLine generateKLineByKline(int range, int field, long time);

     void jointKLine(KLine k1, KLine k2) ;

     KLine getCurrentKLineByTrade(int range, int field) ;

     KLine getCurrentKLineByLine(int range, int field) ;

     void addHandler(MarketHandler handler);

}
