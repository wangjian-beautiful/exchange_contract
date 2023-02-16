package com.bjs.contract.kline.job;

import com.bjs.contract.kline.handler.MarketHandler;
import com.bjs.contract.kline.handler.MongoService;
import com.bjs.contract.kline.processor.CoinProcessorFactory;
import com.bjs.contract.kline.processor.MongoProcessor;
import com.bjs.contract.kline.processor.Update24HTicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class CurrentKlinePushJob {

    @Autowired
    CoinProcessorFactory processorFactory;

    @Autowired
    @Qualifier("websocketMarketHandler")
    MarketHandler markerHandler;

    @Autowired
    MongoService mongoService;
    @Scheduled(fixedRate = 500)
    public void pushCurrentKline() {
        log.info("推送当前K线:{}", Calendar.getInstance().getTime());
        processorFactory.getProcessorMap().forEach((symbol, processor) -> {
            log.info("推送当前{}K线:", symbol);
            //当前1分钟
            processor.getCurrentKLineByTrade(1,Calendar.MINUTE);
            //当前5分钟
            processor.getCurrentKLineByTrade(5, Calendar.MINUTE);
            //当前10分钟
            processor.getCurrentKLineByTrade(10, Calendar.MINUTE);
            //当前15分钟
            processor.getCurrentKLineByTrade(15, Calendar.MINUTE);
            //当前30分钟
            processor.getCurrentKLineByTrade(30, Calendar.MINUTE);
            //当前1小时
            processor.getCurrentKLineByLine(1, Calendar.HOUR_OF_DAY);
            //当前4小时
            processor.getCurrentKLineByLine(4, Calendar.HOUR_OF_DAY);
            //当前日K线
            processor.getCurrentKLineByLine(1, Calendar.DAY_OF_YEAR);
            //当前周K线
            processor.getCurrentKLineByLine(1, Calendar.DAY_OF_WEEK);
            //当前月k线
            processor.getCurrentKLineByLine(1, Calendar.DAY_OF_MONTH);
            //推送24小时数据
            Update24HTicker.pushRedis(symbol);

        });
    }


    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        log.info("推送当前K线:{}", calendar.getTime());
        //将秒、微秒字段置为0
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long time = calendar.getTimeInMillis();
        MongoProcessor.TimeTick instance = MongoProcessor.TimeTick.currentTick(1, Calendar.DAY_OF_WEEK);
        System.out.println();
    }
}
