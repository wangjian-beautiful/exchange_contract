package com.bjs.contract.kline;

import com.bijinsuo.common.domain.ConfigSymbolMatchingDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bjs.contract.dto.Ticker;
import com.bjs.contract.kline.handler.MongoMarketHandler;
import com.bjs.contract.kline.handler.WebsocketMarketHandler;
import com.bjs.contract.kline.processor.*;
import com.bjs.contract.kline.handler.MongoService;
import com.bjs.contract.util.ConfigSymbolCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CoinProcessorFactory coinProcessorFactory;

    @Autowired
    MongoMarketHandler mongoMarketHandler;

    @Autowired
    WebsocketMarketHandler websocketMarketHandler;

    @Autowired
    private MongoService mongoService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /**
         *系统存在两个容器，一个是root application context,另一个是我们自己的projectName application context
         * (root application context 的子容器)，这种情况会造成onApplicationEvent执行两次。为了避免执行两次的情况，
         * 我们可以只在root application context初始化完成后调用此逻辑代码，其他容器初始化完成不做任何处理。
         */
        if (event.getApplicationContext().getParent() == null) {
            List<ContractConfigDTO> symbolConfig = ConfigSymbolCache.instance().getAllSymbolConfig();
            symbolConfig.forEach(configDTO -> {
                String symbol = configDTO.getSymbol().toLowerCase();
                CoinProcessor processor = new MongoProcessor(symbol);
                processor.addHandler(mongoMarketHandler);
                processor.addHandler(websocketMarketHandler);
                processor.setMarketService(mongoService);
                coinProcessorFactory.addProcessor(symbol, processor);
            });
            //添加一批测试数据
            addRedisReview();
        }
    }


    public static void addRedisReview( ){
        RedisUtil redis = RedisUtil.instance();
        double priced = new Random().nextDouble();
        double vold = new Random().nextDouble();
        BigDecimal price = BigDecimal.valueOf(priced*1000).setScale(2, RoundingMode.DOWN);
        BigDecimal vol = BigDecimal.valueOf(vold).setScale(2,RoundingMode.DOWN);
        BigDecimal turnover = BigDecimal.valueOf(vold*10).setScale(2,RoundingMode.DOWN);
        String symbolstr = " btcusdt\n" +
                " ethusdt\n" +
                " ltcusdt\n" +
                " bchusdt\n" +
                " etcusdt\n" +
                " ethbtc\n" +
                " ltcbtc\n" +
                " bchbtc\n" +
                " eosusdt\n" +
                " trxusdt\n" +
                " xrpusdt\n" +
                " dashusdt\n" +
                " dogeusdt\n" +
                " bsvusdt\n" +
                " adausdt\n" +
                " adabep20usdt\n" +
                " eoseth\n" +
                " dogeeth\n" +
                " bscusdt\n" +
                " shibusdt\n" +
                " uniusdt\n" +
                " maticusdt\n" +
                " matic1usdt";
        for (String symbol : symbolstr.split("\n")) {
            String key = Update24HTicker.getTickerRedisKey(symbol.trim());
            if (!redis.exists(key)) {
                Ticker ticker = Ticker.builder().amount(turnover).close(price)
                        .open(price).low(price).high(price)
                        .rose(vol)
                        .firstPrice(price).vol(vol).build();
                redis.hmset(key, ticker.toMap());
            }
        }
    }

}
