package com.bjs.contract.kline.processor;

import com.alibaba.fastjson.JSON;
import com.bjs.contract.util.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 原计划用来做实时计算K线的
 */
@Slf4j
public class KlineTime {

    public static final String min_1 = "1min";
    public static final String min_5 = "5min";
    public static final String min_10 = "10min";
    public static final String min_15 = "15min";
    public static final String min_30 = "30min";
    public static final String min_60 = "60min";
    public static final String hour_1 = "1hour";
    public static final String hour_4 = "4hour";
    public static final String day_1 = "1day";
    public static final String week_1 = "1week";
    public static final String month_1 = "1month";


    private static final Map<String, List<Long[]>> map = new HashMap();

    private static final int plusDays = 1;
    /**
     * 交易开始时间 北京时间 00:00:00
     */
    public static final String startOfDay = "00:00:00";
    private static Long initTimeMillis = null;

    static {
        init();
    }
    private static void init() {
        LocalTime parse = LocalTime.parse(startOfDay);
        LocalDateTime currTime = LocalDate.now().atTime(parse);
        initTimeMillis = LocalDateUtils.localDateTimeToMilli(currTime);
        initTimes(currTime, min_1, 1, plusDays);
        initTimes(currTime, min_5, 5, plusDays);
        initTimes(currTime, min_10, 10, plusDays);
        initTimes(currTime, min_15, 15, plusDays);
        initTimes(currTime, min_30, 30, plusDays);
        initTimes(currTime, min_60, 60, plusDays);
        initTimes(currTime, hour_1, 60, plusDays);
        initTimes(currTime, hour_4, 1 * 60 * 4, plusDays);
        initTimes(currTime, day_1, 1 * 60 * 24, plusDays);
        //添加周线
        LocalDateTime weekStart = LocalDateUtils.getWeekStart();
        LocalDateTime weekEnd = LocalDateUtils.getWeekEnd();
        map.put(week_1, new ArrayList<Long[]>() {
            {
                add(new Long[]{LocalDateUtils.localDateTimeToMilli(weekStart), LocalDateUtils.localDateTimeToMilli(weekEnd)});
            }
        });
        //添加月线
        LocalDateTime firstDayOfMonth = LocalDateUtils.firstDayOfMonth();
        LocalDateTime lastDayOfMonth = LocalDateUtils.lastDayOfMonth();
        map.put(month_1, new ArrayList<Long[]>() {
            {
                add(new Long[]{LocalDateUtils.localDateTimeToMilli(firstDayOfMonth), LocalDateUtils.localDateTimeToMilli(lastDayOfMonth)});
            }
        });

        map.forEach((k, v) -> {
            List<String[]> results = v.stream().map(a -> {
                String start = LocalDateUtils.format(a[0]);
                String end = LocalDateUtils.format(a[1]);
                return new String[]{
                        start, end
                };
            }).collect(Collectors.toList());
            log.info("全天日期结果 {}:{}", k, JSON.toJSONString(results,true));
        });
    }

    private static void initTimes(LocalDateTime currentTime, String key, int offMin, int plusDays) {
        LocalDateTime of1DayAfter = currentTime.plusDays(plusDays);
        LocalDateTime useTime = currentTime.plusMinutes(0);
        Long nextMillis = null;
        while (useTime.compareTo(of1DayAfter) <= 0) {
            long milli = useTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            if (null != nextMillis) {
                List<Long[]> longs;
                if (map.containsKey(key)) {
                    longs = map.get(key);
                } else {
                    longs = new ArrayList<>();
                    map.put(key, longs);
                }
                longs.add(new Long[]{
                        nextMillis, milli,
                });
            }
            nextMillis = milli;
            useTime = useTime.plusMinutes(offMin);
        }
    }

    public static Long[] getPeriodRangeTimes(String period,LocalDateTime nowTime){
        if ((System.currentTimeMillis()-initTimeMillis) >= TimeUnit.DAYS.toMillis(1)){
           synchronized (map){
               map.clear();
               init();
           }
        }
        long curr = LocalDateUtils.localDateTimeToMilli(nowTime);
        if (map.containsKey(period)){
            for (Long[] longs : map.get(period)) {
                if (curr > longs[0] && curr < longs[1]){
                    return longs;
                }
            }
        }else{
            log.info("未缓存时间刻度{}",period);
        }

        return null;
    }


    public static void main(String[] args) {
        getPeriodRangeTimes("1day",LocalDateTime.now());
    }

}
