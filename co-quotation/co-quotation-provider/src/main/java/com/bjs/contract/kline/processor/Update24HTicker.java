package com.bjs.contract.kline.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bijinsuo.common.redis.utils.RedisLockUtil;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.Ticker;
import com.bjs.contract.util.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
public class Update24HTicker {
    private static final String COUNT_24H_TICKER_AMOUNT_KEY = "count_24H_ticker_amount_key";
    private static final String COUNT_24H_TICKER_VOL_KEY = "count_24H_ticker_vol_key";
    private static int count_24h_ticker_wait_time = 5;

    enum TickerField {
        open,
        high,
        low,
        close,
        vol,
        amount,
        rose;
    }

    public static void processTrade(String symbol, MatchTradeDetailsDTO trade) {
        RedisUtil redis = RedisUtil.instance();
        String key = getTickerRedisKey(symbol);
        //凌晨12点清空当天数据
        LocalDateTime tradeTime = LocalDateUtils.millisecondsToDate(trade.getTime());
        if (tradeTime.getHour() == 0 && tradeTime.getMinute() == 0 && tradeTime.getSecond() == 0) {
            Set<String> allKeys = redis.hmgetAllKeys(key);
            redis.hmdelAll(key, allKeys.toArray(new String[]{}));
            redis.remove(key);
        }
        BigDecimal price = trade.getPrice();
        BigDecimal vol = trade.getAmount();
        BigDecimal turnover = trade.getTurnover();
        if (!redis.exists(key)) {
            Ticker ticker = Ticker.builder().amount(turnover).close(price)
                    .open(price).low(price).high(price).firstPrice(price).vol(vol).build();
            redis.hmset(key, ticker.toMap());
            return;
        }
        //收盘价
        redis.hmput(key, TickerField.close.name(), price);
        //最低价
        BigDecimal low = redis.hmget(key, TickerField.low.name());
        if (low != null && price.compareTo(low) < 0) {
            redis.hmput(key, TickerField.low.name(), price);
        }
        //最高价
        BigDecimal high = redis.hmget(key, TickerField.high.name());
        if (null != high && price.compareTo(high) > 0) {
            redis.hmput(key, TickerField.high.name(), price);
        }
        //开盘价
        BigDecimal openPrice = redis.hmget(key, TickerField.open.name());
        if (null == openPrice) {
            redis.hmput(key, TickerField.open.name(), price);
            openPrice = price;
        }
        BigDecimal role = price.subtract(openPrice).divide(openPrice, 2, RoundingMode.DOWN);
        redis.hmput(key, TickerField.rose.name(), role);

        RedisLockUtil redisLock = RedisLockUtil.instance();
        //统计成交额
        try {
            if (redisLock.tryLock(COUNT_24H_TICKER_AMOUNT_KEY, count_24h_ticker_wait_time, count_24h_ticker_wait_time)) {
                BigDecimal amount = redis.hmget(key, TickerField.amount.name());
                if (null == amount || amount.compareTo(BigDecimal.ZERO) == 0) {
                    redis.hmput(key, TickerField.amount.name(), price);
                } else {
                    redis.hmput(key, TickerField.amount.name(), amount.add(price));
                }
            }
        } catch (Exception e) {
            log.error("统计24小时行情错误", e);
        } finally {
            redisLock.unLock(COUNT_24H_TICKER_AMOUNT_KEY);
        }
        //统计成交量
        try {
            if (redisLock.tryLock(COUNT_24H_TICKER_VOL_KEY, count_24h_ticker_wait_time, count_24h_ticker_wait_time)) {
                BigDecimal volPrice = redis.hmget(key, TickerField.vol.name());
                if (null == volPrice || volPrice.compareTo(BigDecimal.ZERO) == 0) {
                    redis.hmput(key, TickerField.vol.name(), vol);
                } else {
                    redis.hmput(key, TickerField.vol.name(), volPrice.add(vol));
                }
            }
        } catch (Exception e) {
            log.error("统计24小时行情错误", e);
        } finally {
            redisLock.unLock(COUNT_24H_TICKER_VOL_KEY);
        }
    }

    public static void pushRedis(String symbol) {
        String channel = getChannelRedisKey(symbol);
        String tickerKey = getTickerRedisKey(symbol);
        RedisUtil redis = RedisUtil.instance();
        Set<String> fields = redis.hmgetAllKeys(tickerKey);
        JSONObject tick = new JSONObject();
        if (!CollectionUtils.isEmpty(fields)){
           fields.forEach(f->tick.put(f,redis.hmget(tickerKey,f)));
        }
        JSONObject result = new JSONObject();
        result.put("channel",channel);
        result.put("ts",System.currentTimeMillis());
        result.put("tick",tick);
        redis.publish(channel,result.toJSONString());
    }

    public static String redisTickerKey = "Ticker_key_";

    public static String getTickerRedisKey(String symbol) {
        return redisTickerKey + getChannelRedisKey(symbol);
    }

    public static String getChannelRedisKey(String symbol) {
        return "market_e_" + symbol.toLowerCase() + "_ticker";
    }
}
