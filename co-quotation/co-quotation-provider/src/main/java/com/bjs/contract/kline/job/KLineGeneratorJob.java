package com.bjs.contract.kline.job;


import com.bjs.contract.kline.processor.CoinProcessorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * 生成各时间段的K线信息
 *
 */
@Component
@Slf4j
@EnableScheduling
public class KLineGeneratorJob {
    @Autowired
    private CoinProcessorFactory processorFactory;

    /**
     * 每分钟定时器，生成K线
     */
    @Scheduled(cron = "0 * * * * *")
    public void handleKLine(){
        Calendar calendar = Calendar.getInstance();
        //将秒、微秒字段置为0
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long time = calendar.getTimeInMillis();
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        processorFactory.getProcessorMap().forEach((symbol,processor)->{
            log.info("生成k线:{}",symbol);
            //生成1分钟K线
            processor.generateKLineByTrade(1,Calendar.MINUTE,time);
            //生成5分钟K线
            if(minute%5 == 0) {
                processor.generateKLineByTrade(5, Calendar.MINUTE, time);
            }
            if(minute%10 == 0){
                processor.generateKLineByTrade(10, Calendar.MINUTE,time);
            }
            if(minute%15 == 0){
                processor.generateKLineByTrade(15, Calendar.MINUTE,time);
            }
            if(minute%30 == 0){
                processor.generateKLineByTrade(30, Calendar.MINUTE,time);
            }
            //处理1小时K线
            if(minute == 0){
                processor.generateKLineByTrade(1, Calendar.HOUR_OF_DAY,time);
            }
            //处理4小时K线
            if(minute == 0 && hour %4 == 0){
                processor.generateKLineByTrade(4, Calendar.HOUR_OF_DAY,time);
            }
            //处理日K线 及24小时成交量
            if(hour == 0 && minute == 0){
                processor.generateKLineByTrade(1, Calendar.DAY_OF_YEAR,time);
            }
            //处理周K线
            if(week == 1){
                processor.generateKLineByKline(1, Calendar.DAY_OF_WEEK, time);
            }
            //处理月K线
            if(dayOfMonth == 1){
                processor.generateKLineByKline(1, Calendar.DAY_OF_MONTH, time);
            }
        });
    }

}
