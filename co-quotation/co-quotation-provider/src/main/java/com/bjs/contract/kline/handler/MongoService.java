package com.bjs.contract.kline.handler;

import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.KLine;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Service
public class MongoService {
    
    public static final String TIME_KEY = "time";
    @Autowired
    private MongoTemplate mongoTemplate;

    public static String getCollectionNameKline(String symbol,String period){
        return "market_e_"+symbol.toLowerCase()+"_kline_"+period;
    }

    public static String getCollectionNameTrade(String symbol){
        return "market_e_"+symbol.toLowerCase()+"_trade_ticker";
    }

    public List<KLine> findAllKLine(String symbol, String period){
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,TIME_KEY));
        Query query = new Query().with(sort).limit(1000);
        return mongoTemplate.find(query,KLine.class,getCollectionNameKline(symbol,period));
    }

    public List<KLine> findAllKLine(String symbol,long fromTime,long toTime,String period){
        Criteria criteria = Criteria.where(TIME_KEY).gte(fromTime).andOperator(Criteria.where(TIME_KEY).lte(toTime));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,TIME_KEY));
        Query query = new Query(criteria).with(sort);
        List<KLine> kLines = mongoTemplate.find(query,KLine.class,getCollectionNameKline(symbol,period));
        return kLines;
    }

    public MatchTradeDetailsDTO findFirstTrade(String symbol, long fromTime, long toTime){
        Criteria criteria = Criteria.where(TIME_KEY).gte(fromTime).andOperator(Criteria.where(TIME_KEY).lte(toTime));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,TIME_KEY));
        Query query = new Query(criteria).with(sort);
        return mongoTemplate.findOne(query,MatchTradeDetailsDTO.class,getCollectionNameTrade(symbol));
    }

    public MatchTradeDetailsDTO findLastTrade(String symbol,long fromTime,long toTime){
        Criteria criteria = Criteria.where(TIME_KEY).gte(fromTime).andOperator(Criteria.where(TIME_KEY).lte(toTime));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,TIME_KEY));
        Query query = new Query(criteria).with(sort);
        return mongoTemplate.findOne(query,MatchTradeDetailsDTO.class,getCollectionNameTrade(symbol));
    }

    public MatchTradeDetailsDTO findTrade(String symbol, long fromTime, long toTime, Sort.Order order){
        Criteria criteria = Criteria.where(TIME_KEY).gte(fromTime).andOperator(Criteria.where(TIME_KEY).lte(toTime));
        Sort sort = Sort.by(order);
        Query query = new Query(criteria).with(sort);
        return mongoTemplate.findOne(query,MatchTradeDetailsDTO.class,getCollectionNameTrade(symbol));
    }

    public List<MatchTradeDetailsDTO> findTradeByTimeRange(String symbol, long timeStart, long timeEnd){
        Criteria criteria = Criteria.where(TIME_KEY).gte(0).andOperator(Criteria.where(TIME_KEY).lt(timeEnd));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,TIME_KEY));
        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query,MatchTradeDetailsDTO.class,getCollectionNameTrade(symbol));
    }

    /**
     * 查找某时间段内的交易量
     * @param symbol
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public BigDecimal findTradeVolume(String symbol, long timeStart, long timeEnd){
        Criteria criteria = Criteria.where(TIME_KEY).gt(0)
                .andOperator(Criteria.where(TIME_KEY).lte(timeEnd));
                //.andOperator(Criteria.where("volume").gt(0));
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,TIME_KEY));
        Query query = new Query(criteria).with(sort);
        List<KLine> kLines = mongoTemplate.find(query,KLine.class,getCollectionNameKline(symbol,"1min"));
        BigDecimal totalVolume = BigDecimal.ZERO;
        for(KLine kLine:kLines){
            totalVolume = totalVolume.add(kLine.getVol());
        }
        return totalVolume;
    }
    /**
     * mongo 聚合查询一段时间内成交记录的 最大值，最小值，交易量
     * @param symbol
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public KLine findMaxMinPriceByTrade(String symbol, long timeStart, long timeEnd) {
        String fieldName = "price";
        Criteria criteria = Criteria.where(TIME_KEY).gt(0l)
                .andOperator(Criteria.where(TIME_KEY).lte(timeEnd));
        //  开始管道聚合操作
        Aggregation aggregation =  Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(symbol)
                        .min(fieldName).as("minPrice")
                        .max(fieldName).as("maxPrice")
                        .count().as("count")
                        .sum(fieldName).as("sumPrice")
                        .sum("amount").as("vol")
        );
        AggregationResults<BasicDBObject> outputTypeCount = mongoTemplate.aggregate(aggregation, getCollectionNameTrade(symbol), BasicDBObject.class);
        DBObject obj = new BasicDBObject();

        for (Iterator<BasicDBObject> iterator = outputTypeCount.iterator(); iterator.hasNext();) {
            obj = iterator.next();
        }
        KLine kline =KLine.createNewKLine(symbol);
        if (obj.containsField("maxPrice")){
            BigDecimal maxPrice = new BigDecimal(obj.get("maxPrice").toString());
            kline.setHigh(maxPrice);
        }
        if (obj.containsField("minPrice")){
            BigDecimal minPrice = new BigDecimal(obj.get("minPrice").toString());
            kline.setLow(minPrice);
        }
        if (obj.containsField("sumPrice")){
            BigDecimal sumPrice = new BigDecimal(obj.get("sumPrice").toString());
            kline.setAmount(sumPrice);
        }
        if (obj.containsField("count")){
            Integer count = (Integer) obj.get("count");
            kline.setCount(count);
        }
        if (obj.containsField("vol")){
            BigDecimal vol = new BigDecimal(obj.get("vol").toString());
            kline.setVol(vol);
        }
        kline.setTime(timeStart);
        Sort sortAsc = Sort.by(new Sort.Order(Sort.Direction.ASC,TIME_KEY));
        Sort sortDesc = Sort.by(new Sort.Order(Sort.Direction.DESC,TIME_KEY));
        List<MatchTradeDetailsDTO> tradeAsc = mongoTemplate.find(new Query(criteria).with(sortAsc).limit(1), MatchTradeDetailsDTO.class, getCollectionNameTrade(symbol));
        List<MatchTradeDetailsDTO> tradeDesc = mongoTemplate.find(new Query(criteria).with(sortDesc).limit(1),MatchTradeDetailsDTO.class,getCollectionNameTrade(symbol));
        //开盘价
        if (CollectionUtils.isNotEmpty(tradeAsc)){
            kline.setOpen(tradeAsc.get(0).getPrice());
        }
        //收盘价
        if (CollectionUtils.isNotEmpty(tradeDesc)){
            kline.setClose(tradeDesc.get(0).getPrice());
        }
        return kline;
    }

    /**
     * mongo 聚合查询一段时间内K线的 最大值，最小值，交易量, 并统计开盘价，收盘价
     * @param symbol
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public KLine findMaxMinPriceByKLine(String symbol, long timeStart, long timeEnd,String period) {
        Criteria criteria = Criteria.where(TIME_KEY).gt(0)
                .andOperator(Criteria.where(TIME_KEY).lte(timeEnd));
        Aggregation aggregation =  Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(symbol)
                        .min("low").as("minLow")
                        .max("high").as("maxHigh")
                        .sum("vol").as("sumVol")
                        .sum("amount").as("sumTurnover")
        );
        AggregationResults<BasicDBObject> outputTypeCount = mongoTemplate.aggregate(aggregation, getCollectionNameKline(symbol,period), BasicDBObject.class);
        DBObject obj = new BasicDBObject();
        for (Iterator<BasicDBObject> iterator = outputTypeCount.iterator(); iterator.hasNext();) {
            obj = iterator.next();
        }
        KLine kline = KLine.createNewKLine(symbol);
        if (obj.containsField("minLow")){
            BigDecimal minLow = new BigDecimal(obj.get("minLow").toString());
            kline.setLow(minLow);

        }
        if (obj.containsField("maxHigh")){
            BigDecimal maxHigh = new BigDecimal(obj.get("maxHigh").toString());
            kline.setHigh(maxHigh);
        }
        if (obj.containsField("sumVol")){
            BigDecimal sumVol = new BigDecimal(obj.get("sumVol").toString());
            kline.setVol(sumVol);

        }
        if (obj.containsField("sumTurnover")){
            BigDecimal sumTurnover = new BigDecimal(obj.get("sumTurnover").toString());
            kline.setAmount(sumTurnover);
        }
        kline.setTime(timeStart);

        Sort sortAsc = Sort.by(new Sort.Order(Sort.Direction.ASC,TIME_KEY));
        Sort sortDesc = Sort.by(new Sort.Order(Sort.Direction.DESC,TIME_KEY));
        List<KLine> tradeAsc = mongoTemplate.find(new Query(criteria).with(sortAsc).limit(1), KLine.class, getCollectionNameKline(symbol,period));
        List<KLine> tradeDesc = mongoTemplate.find(new Query(criteria).with(sortDesc).limit(1),KLine.class,getCollectionNameKline(symbol,period));
        //开盘价
        if (CollectionUtils.isNotEmpty(tradeAsc)){
            kline.setOpen(tradeAsc.get(0).getOpen());
        }
        //收盘价
        if (CollectionUtils.isNotEmpty(tradeDesc)){
            kline.setClose(tradeDesc.get(0).getClose());
            kline.setLastKlineTime(tradeDesc.get(0).getTime());
        }
        return kline;
    }



    public void saveTrade(String symbol, MatchTradeDetailsDTO trade) {
         mongoTemplate.insert(trade, getCollectionNameTrade(symbol));
    }

    public void saveKLine(String symbol, KLine kLine) {
        mongoTemplate.insert(kLine,getCollectionNameKline(symbol,kLine.getPeriod()));
    }

}
