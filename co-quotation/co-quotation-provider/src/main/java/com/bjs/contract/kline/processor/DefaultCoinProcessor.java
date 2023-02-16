package com.bjs.contract.kline.processor;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.CoinThumb;
import com.bjs.contract.dto.KLine;
import com.bjs.contract.kline.handler.MarketHandler;
import com.bjs.contract.kline.handler.MongoMarketHandler;
import com.bjs.contract.kline.handler.WebsocketMarketHandler;
import com.bjs.contract.kline.handler.MongoService;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 默认交易处理器，产生1mK线信息 使用内存计算
 */
@ToString
public class DefaultCoinProcessor implements CoinProcessor {
    private Logger logger = LoggerFactory.getLogger(DefaultCoinProcessor.class);
    private String symbol;
    private KLine currentKLine;
    private List<MarketHandler> handlers;
    private CoinThumb coinThumb;
    private MongoService service;
    //是否暂时处理
    private Boolean isHalt = true;

    public DefaultCoinProcessor(String symbol) {
        handlers = new ArrayList<>();
        createNewKLine();
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public KLine createNewKLine(int range, int field, long time) {
        return null;
    }


    public void initializeThumb() {
        Calendar calendar = Calendar.getInstance();
        //将秒、微秒字段置为0
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long nowTime = calendar.getTimeInMillis();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        long firstTimeOfToday = calendar.getTimeInMillis();
        String period = "1min";
        logger.info("initializeThumb from {} to {}", firstTimeOfToday, nowTime);
        List<KLine> lines = service.findAllKLine(this.symbol, firstTimeOfToday, nowTime, period);
        coinThumb = new CoinThumb();
        synchronized (coinThumb) {
            coinThumb.setSymbol(symbol);
            for (KLine kline : lines) {
                if (kline.getOpen().compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                if (coinThumb.getOpen().compareTo(BigDecimal.ZERO) == 0) {
                    coinThumb.setOpen(kline.getOpen());
                }
                if (coinThumb.getHigh().compareTo(kline.getHigh()) < 0) {
                    coinThumb.setHigh(kline.getHigh());
                }
                if (kline.getLow().compareTo(BigDecimal.ZERO) > 0 && coinThumb.getLow().compareTo(kline.getLow()) > 0) {
                    coinThumb.setLow(kline.getLow());
                }
                if (kline.getClose().compareTo(BigDecimal.ZERO) > 0) {
                    coinThumb.setClose(kline.getClose());
                }
                coinThumb.setVolume(coinThumb.getVolume().add(kline.getVol()));
                coinThumb.setTurnover(coinThumb.getTurnover().add(kline.getAmount()));
            }
            coinThumb.setChange(coinThumb.getClose().subtract(coinThumb.getOpen()));
            if (coinThumb.getOpen().compareTo(BigDecimal.ZERO) > 0) {
                coinThumb.setChg(coinThumb.getChange().divide(coinThumb.getOpen(), 4, RoundingMode.UP));
            }
//            coinThumb.setCloseStr(coinThumb.getClose().setScale(baseScale,RoundingMode.DOWN).toPlainString());
            try {
                coinThumb.setCnyPrice(coinThumb.getClose().multiply(coinThumb.getBaseUsdRate()).multiply(BigDecimal.valueOf(6.8)).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
            } catch (Exception e) {
                logger.info("初始化价格为空");
            }
        }
    }

    public void createNewKLine() {
        currentKLine = KLine.createNewKLine(symbol);
        synchronized (currentKLine) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            //1Min时间要是下一整分钟的
            calendar.add(Calendar.MINUTE, 1);
            currentKLine.setTime(calendar.getTimeInMillis());
            currentKLine.setPeriod("1min");
            currentKLine.setCount(0);
        }
    }

    /**
     * 00:00:00 时重置CoinThumb
     */

    public void resetThumb() {
        logger.info("reset coinThumb");
        synchronized (coinThumb) {
            coinThumb.setOpen(BigDecimal.ZERO);
            coinThumb.setHigh(BigDecimal.ZERO);
            //设置昨收价格
            coinThumb.setLastDayClose(coinThumb.getClose());
            coinThumb.setClose(BigDecimal.ZERO);
            coinThumb.setLow(BigDecimal.ZERO);
            coinThumb.setChg(BigDecimal.ZERO);
            coinThumb.setChange(BigDecimal.ZERO);
        }
    }


    public void update24HVolume(long time) {
        if (coinThumb != null) {
            synchronized (coinThumb) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                calendar.add(Calendar.HOUR_OF_DAY, -24);
                long timeStart = calendar.getTimeInMillis();
                BigDecimal volume = service.findTradeVolume(this.symbol, timeStart, time);
                coinThumb.setVolume(volume.setScale(4, RoundingMode.DOWN));
            }
        }
    }


    public void autoGenerate() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        logger.info("auto generate 1min kline in {},data={}", df.format(new Date(currentKLine.getTime())), JSON.toJSONString(currentKLine));
        if (coinThumb != null) {
            synchronized (currentKLine) {
                //没有成交价时存储上一笔成交价
                if (currentKLine.getOpen().compareTo(BigDecimal.ZERO) == 0) {
                    currentKLine.setOpen(coinThumb.getClose());
                    currentKLine.setLow(coinThumb.getClose());
                    currentKLine.setHigh(coinThumb.getClose());
                    currentKLine.setClose(coinThumb.getClose());
                }
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                currentKLine.setTime(calendar.getTimeInMillis());
                handleKLineStorage(currentKLine);
                createNewKLine();
            }
        }
    }


    public void setIsHalt(boolean status) {
        this.isHalt = status;
    }


    public void process(List<MatchTradeDetailsDTO> trades) {
        if (!isHalt) {
            if (trades == null || trades.size() == 0) {
                return;
            }
            synchronized (currentKLine) {
                for (MatchTradeDetailsDTO MatchTradeDetailsDTO : trades) {
                    //处理K线
                    processTrade(currentKLine, MatchTradeDetailsDTO);
                    //处理今日概况信息
                    logger.info("处理今日概况信息");
                    handleThumb(MatchTradeDetailsDTO);
                    //成交信息 存储 不做推送，推送
                    handleTradeStorage(MatchTradeDetailsDTO);
                }
            }
        }
    }

    public void process(MatchTradeDetailsDTO trade) {
        if (!isHalt) {
            if (trade == null) {
                return;
            }
            synchronized (currentKLine) {
                //处理K线
                processTrade(currentKLine, trade);
                //处理今日概况信息
                logger.info("处理今日概况信息");
                handleThumb(trade);
                //成交信息存储 不做推送，推送go直接消费MQ
                handleTradeStorage(trade);
            }
        }
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

    public void handleTradeStorage(MatchTradeDetailsDTO trade) {
        for (MarketHandler storage : handlers) {
            if (storage instanceof MongoMarketHandler) {
                storage.handleTrade(symbol, trade);
            }
        }
    }

    //K线需要走推送
    public void handleKLineStorage(KLine kLine) {
        for (MarketHandler storage : handlers) {
            storage.handleKLine(symbol, kLine);
        }
    }

    public void handleThumb(MatchTradeDetailsDTO trade) {
        logger.info("handleThumb symbol = {}", this.symbol);
        synchronized (coinThumb) {
            if (coinThumb.getOpen().compareTo(BigDecimal.ZERO) == 0) {
                //第一笔交易记为开盘价
                coinThumb.setOpen(trade.getPrice());
            }
            coinThumb.setHigh(trade.getPrice().max(coinThumb.getHigh()));
            if (coinThumb.getLow().compareTo(BigDecimal.ZERO) == 0) {
                coinThumb.setLow(trade.getPrice());
            } else {
                coinThumb.setLow(trade.getPrice().min(coinThumb.getLow()));
            }
            coinThumb.setClose(trade.getPrice());
            coinThumb.setVolume(coinThumb.getVolume().add(trade.getAmount()).setScale(4, RoundingMode.UP));
            BigDecimal turnover = trade.getPrice().multiply(trade.getAmount()).setScale(4, RoundingMode.UP);
            coinThumb.setTurnover(coinThumb.getTurnover().add(turnover));
            BigDecimal change = coinThumb.getClose().subtract(coinThumb.getOpen());
            coinThumb.setChange(change);
            if (coinThumb.getOpen().compareTo(BigDecimal.ZERO) > 0) {
                coinThumb.setChg(change.divide(coinThumb.getOpen(), 4, BigDecimal.ROUND_UP));
            }
            coinThumb.setUsdRate(trade.getPrice().multiply(coinThumb.getBaseUsdRate()));
            try {
                coinThumb.setCnyPrice(coinThumb.getClose().multiply(coinThumb.getBaseUsdRate()).multiply(BigDecimal.valueOf(6.8)).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
            } catch (Exception e) {
                logger.info("初始化价格为空");
            }
            logger.info("thumb = {}", coinThumb);
        }
    }


    public void addHandler(MarketHandler storage) {
        handlers.add(storage);
    }


    @Override
    public void setMarketService(MongoService service) {
        this.service = service;
    }

    @Override
    public KLine generateKLineByTrade(int range, int field, long time) {
        return null;
    }

    @Override
    public KLine generateKLineByKline(int range, int field, long time) {
        return null;
    }


    public KLine generateKLine(int range, int field, long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long endTick = calendar.getTimeInMillis();
        String endTime = df.format(calendar.getTime());
        //往前推range个时间单位
        calendar.add(field, -range);
        String fromTime = df.format(calendar.getTime());
        long startTick = calendar.getTimeInMillis();
        logger.info("time range from " + fromTime + " to " + endTime);
        List<MatchTradeDetailsDTO> MatchTradeDetailsDTOs = service.findTradeByTimeRange(this.symbol, startTick, endTick);
        KLine kLine = KLine.createNewKLine(symbol);
        //k线的time值设置为起始时刻
        kLine.setTime(startTick);
        kLine.setPeriod(getPeriod(field, range));

        // 处理K线信息
        for (MatchTradeDetailsDTO MatchTradeDetailsDTO : MatchTradeDetailsDTOs) {
            processTrade(kLine, MatchTradeDetailsDTO);
        }
        return kLine;
    }

    private String getPeriod(int field, int range) {
        String rangeUnit = "";
        if (field == Calendar.MINUTE) {
            rangeUnit = "min";
        } else if (field == Calendar.HOUR_OF_DAY) {
            rangeUnit = "hour";
        } else if (field == Calendar.DAY_OF_WEEK) {
            rangeUnit = "week";
        } else if (field == Calendar.DAY_OF_YEAR) {
            rangeUnit = "day";
        } else if (field == Calendar.MONTH) {
            rangeUnit = "month";
        }
        return range + rangeUnit;
    }

    public void generateKLineAndHandle(int range, int field, long time) {
        handleKLineStorage(generateKLine(range, field, time));
    }

    public void joinCurrentKLineAndPush(int range, int field, long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long endTick = calendar.getTimeInMillis();
        String endTime = df.format(calendar.getTime());
        //往前推range个时间单位
        calendar.add(field, -range);
        String fromTime = df.format(calendar.getTime());
        long startTick = calendar.getTimeInMillis();
        logger.info("time range from " + fromTime + " to " + endTime);
        String period = "1min";
        KLine kLine = KLine.createNewKLine(symbol);
        kLine.setTime(startTick);
        kLine.setPeriod(getPeriod(field, range));
        kLine.setSymbol(this.symbol);
        List<KLine> allKLine = service.findAllKLine(this.symbol, startTick, endTick, period);
        allKLine.forEach(k -> jointKLine(kLine, k));
        joinCurrentKLine(kLine);
        handlers.forEach(handler -> {
            if (handler instanceof WebsocketMarketHandler) {
                handler.handleKLine(this.symbol, kLine);
            }
        });
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
        k1.setCount(k1.getCount() + 1);
        k1.setVol(k1.getVol().add(k2.getVol()));
        k1.setAmount(k1.getAmount().add(k2.getAmount()));
    }

    @Override
    public KLine getCurrentKLineByTrade(int range, int field) {
        return null;
    }

    @Override
    public KLine getCurrentKLineByLine(int range, int field) {
        return null;
    }

    public void joinCurrentKLine(KLine kLine) {
        jointKLine(kLine, this.currentKLine);
    }


    public KLine getKLine() {
        return currentKLine;
    }
}
