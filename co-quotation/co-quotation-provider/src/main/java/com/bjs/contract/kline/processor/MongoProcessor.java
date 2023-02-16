package com.bjs.contract.kline.processor;

import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.KLine;
import com.bjs.contract.kline.handler.MarketHandler;
import com.bjs.contract.kline.handler.MongoService;
import com.bjs.contract.kline.handler.WebsocketMarketHandler;
import com.bjs.contract.util.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 使用mongo数据计算K线
 */
@Slf4j
public class MongoProcessor implements CoinProcessor{
    private String symbol;

    private MongoService service;

    private List<MarketHandler> handlers = new ArrayList<>();

    public void addHandler(MarketHandler handler) {
        handlers.add(handler);
    }

    public MongoProcessor(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public KLine createNewKLine(int range, int field, long time) {
        TimeTick tick = TimeTick.instance(range, field, time);
        KLine kline = KLine.createNewKLine(symbol);
        kline.setTime(tick.startTick);
        kline.setPeriod(tick.period);
        kline.setSymbol(this.symbol);
        kline.setCount(0);
        return kline;
    }

    public void processTrade(KLine kLine, MatchTradeDetailsDTO trade) {
        if (kLine.getClose().compareTo(BigDecimal.ZERO) == 0) {
            //第一次设置K线值
            kLine.setOpen(trade.getPrice());
            kLine.setHigh(trade.getPrice());
            kLine.setLow(trade.getPrice());
            kLine.setClose(trade.getPrice());
        } else {
            kLine.setHigh(trade.getPrice().max(kLine.getHigh()));
            kLine.setLow(trade.getPrice().min(kLine.getLow()));
            kLine.setClose(trade.getPrice());
        }
        kLine.setCount(kLine.getCount() + 1);
        kLine.setVol(kLine.getVol().add(trade.getAmount()));
        BigDecimal turnover = trade.getPrice().multiply(trade.getAmount());
        kLine.setAmount(kLine.getAmount().add(turnover));
    }

    public void setMarketService(MongoService service) {
        this.service = service;
    }

    public KLine generateKLineByTrade(int range, int field, long time) {
        TimeTick tick = TimeTick.instance(range, field, time);
        List<MatchTradeDetailsDTO> MatchTradeDetailsDTOs = service.findTradeByTimeRange(this.symbol, tick.startTick, tick.endTick);
        KLine kLine = KLine.createNewKLine(symbol);
        //k线的time值设置为起始时刻
        kLine.setTime(tick.startTick);
        kLine.setPeriod(tick.period);
        // 处理K线信息
        for (MatchTradeDetailsDTO MatchTradeDetailsDTO : MatchTradeDetailsDTOs) {
            processTrade(kLine, MatchTradeDetailsDTO);
        }
        handleKLineStorageAndPush(kLine);
        return kLine;
    }


    public KLine generateKLineByKline(int range, int field, long time) {
        TimeTick tick = TimeTick.instance(range, field, time);
        String period = "1min";
        KLine kLine = KLine.createNewKLine(symbol);
        kLine.setTime(tick.startTick);
        kLine.setPeriod(tick.period);
        kLine.setSymbol(this.symbol);
        List<KLine> allKLine = service.findAllKLine(this.symbol, tick.startTick, tick.endTick, period);
        allKLine.forEach(k -> jointKLine(kLine, k));
        handleKLineStorageAndPush(kLine);
        return kLine;
    }

    public void jointKLine(KLine k1, KLine k2) {
        if (k1.getClose().compareTo(BigDecimal.ZERO) == 0) {
            //第一次设置K线值
            k1.setOpen(k2.getOpen());
            k1.setHigh(k2.getHigh());
            k1.setLow(k2.getLow());
            k1.setClose(k2.getClose());
        } else {
            k1.setHigh(k1.getHigh().max(k2.getHigh()));
            k1.setLow(k1.getLow().min(k2.getLow()));
            k1.setClose(k2.getClose());
        }
        k1.setCount(k1.getCount() + k2.getCount());
        k1.setVol(k1.getVol().add(k2.getVol()));
        k1.setAmount(k1.getAmount().add(k2.getAmount()));
    }

    public KLine getCurrentKLineByTrade(int range, int field) {
        TimeTick tick = TimeTick.currentTick(range, field);
        KLine kline = service.findMaxMinPriceByTrade(this.symbol, tick.startTick, tick.endTick);
        kline.setPeriod(tick.period);
        kline.setTime(tick.time);
        kline.setSymbol(this.symbol);
        handleKLinePush(kline);
        return kline;
    }

    public KLine getCurrentKLineByLine(int range, int field) {
        TimeTick tick = TimeTick.currentTick(range, field);
        String period = "1min";
        KLine kline = service.findMaxMinPriceByKLine(this.symbol, tick.startTick, tick.endTick,period);
        kline.setPeriod(tick.period);
        kline.setSymbol(this.symbol);
        TimeTick curr1Min = TimeTick.currentTick(1, Calendar.MINUTE);
        if (curr1Min.startTick > kline.getLastKlineTime()){
            KLine currentKLineByTrade = getCurrentKLineByTrade(1, Calendar.MINUTE);
            jointKLine(kline,currentKLineByTrade);
        }
        kline.setTime(tick.startTick);
        handleKLinePush(kline);
        return kline;
    }

    public static class TimeTick{
        public long startTick;
        public long endTick;
        public long time;
        public  String period;
        private TimeTick(){}
       public static TimeTick instance(int range, int field, long time){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long endTick = calendar.getTimeInMillis();
            String endTime = df.format(calendar.getTime());
            //往前推range个时间单位
            calendar.add(field, -range);
            String fromTime = df.format(calendar.getTime());
            long startTick = calendar.getTimeInMillis();
            System.out.println("time range from " + fromTime + " to " + endTime);
            log.info("time range from " + fromTime + " to " + endTime);
            TimeTick timeTick =  new TimeTick();
            timeTick.startTick=startTick;
            timeTick.endTick =endTick;
            timeTick.time = startTick;
            timeTick.period = getPeriod(field,range);
           return timeTick;
        }
        public static TimeTick currentTick(int range, int field){
            LocalDateTime now = LocalDateTime.now();
            long currentTimeMillis = LocalDateUtils.localDateTimeToMilli(now);
            Long[] periodRangeTimes = KlineTime.getPeriodRangeTimes(getPeriod(field, range), now);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTime = df.format(currentTimeMillis);
            String fromTime = df.format(periodRangeTimes[0]);
            log.info("time range from " + fromTime + " to " + endTime);
            TimeTick timeTick =  new TimeTick();
            timeTick.startTick=periodRangeTimes[0];
            timeTick.endTick =currentTimeMillis;
            timeTick.time = periodRangeTimes[0];
            timeTick.period = getPeriod(field,range);
            return timeTick;
        }
        private static String getPeriod(int field, int range) {
            String rangeUnit = "";
            if (field == Calendar.MINUTE) {
                rangeUnit = "min";
            } else if (field == Calendar.HOUR_OF_DAY) {
                rangeUnit = "hour";
            } else if (field == Calendar.DAY_OF_WEEK) {
                rangeUnit = "week";
            } else if (field == Calendar.DAY_OF_YEAR) {
                rangeUnit = "day";
            } else if (field == Calendar.DAY_OF_MONTH) {
                rangeUnit = "month";
            }

            return range + rangeUnit;
        }

    }

    //K线
    public void handleKLineStorageAndPush(KLine kLine) {
        for (MarketHandler handler : handlers) {
            handler.handleKLine(this.symbol,kLine);
        }
    }

    public void handleKLinePush(KLine kLine) {
        for (MarketHandler handler : handlers) {
            if (handler instanceof WebsocketMarketHandler){
                handler.handleKLine(this.symbol,kLine);
            }
        }
    }
}
