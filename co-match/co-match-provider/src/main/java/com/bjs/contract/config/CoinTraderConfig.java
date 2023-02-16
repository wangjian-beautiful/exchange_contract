package com.bjs.contract.config;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.C;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bjs.contract.entity.ConfigSymbolMatching;
import com.bjs.contract.entity.ContractConfig;
import com.bjs.contract.mapper.ConfigSymbolMatchingMapper;
import com.bjs.contract.mapper.ContractConfigMapper;
import com.bjs.contract.match.CoinTrader;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author nike
 * @date 2022年11月10日 9:51
 */
@Slf4j
@Configuration
public class CoinTraderConfig {

    @Resource
    private ConfigSymbolMatchingMapper configSymbolMatchingMapper;
    @Resource
    private ContractConfigMapper contractConfigMapper;
    @Resource
    private RedisUtil redisUtil;

    @Bean
    public CoinTraderFactory getCoinTrader(RocketMQTemplate rocketMQTemplate) {
        CoinTraderFactory factory = new CoinTraderFactory();
        //更具ip查询对应的币对
        LambdaQueryWrapper<ConfigSymbolMatching> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(ConfigSymbolMatching::getServer, getServerIp());
        queryWrapper.eq(ConfigSymbolMatching::getIsOpen,1);
        List<ConfigSymbolMatching> matchingList= configSymbolMatchingMapper.selectList(queryWrapper);

        if (!matchingList.isEmpty()){
            for (ConfigSymbolMatching matching : matchingList) {
                //查询币对的相应信息，用于获取精度
                LambdaQueryWrapper<ContractConfig> query = new LambdaQueryWrapper<>();
                query.eq(ContractConfig::getBase,matching.getBase());
                query.eq(ContractConfig::getQuote,matching.getQuote());
                ContractConfig coin=contractConfigMapper.selectOne(query);
                if (coin!=null){
                    CoinTrader trader = new CoinTrader(coin.getSymbol(), coin.getPricePrecision());
                    trader.setRocketMQTemplate(rocketMQTemplate);
                    trader.setRedisUtil(redisUtil);
                    factory.addTrader(coin.getSymbol(), trader);
                }

            }
        }else {
            log.debug("暂无币对");
        }
        return factory;
    }

    private String getServerIp() {
        String localIP = null;
        try {
            localIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("ex-memory-match start error : get server ip error");
        }
        return localIP;
    }
}
