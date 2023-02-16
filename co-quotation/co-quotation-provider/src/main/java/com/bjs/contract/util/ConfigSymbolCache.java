package com.bjs.contract.util;

import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.proto.contractConfig.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConfigSymbolCache {
    @DubboReference
    private ContractConfigBizService contractConfigBizService;

    private static final ConcurrentHashMap<String, ContractConfigDTO> cache = new ConcurrentHashMap();

    private static final ConcurrentHashMap<String, Long> cacheExpire = new ConcurrentHashMap();
    /**
     * 过期时间
     */
    public static final long EXPIRE_MILLS = 1000 * 60 * 5;

    private static ConfigSymbolCache instance = null;

    public static ConfigSymbolCache instance() {
        if (instance == null) {
            instance = SpringBeanUtils.getBean(ConfigSymbolCache.class);
        }
        return instance;
    }

    public ContractConfigDTO getConfigSymbol(String symbol) {
        if (StringUtils.isNotBlank(symbol)) {
            symbol = symbol.toLowerCase();
            if (cache.containsKey(symbol) && cacheExpire.containsKey(symbol)) {
                //是否过期
                if (System.currentTimeMillis() - cacheExpire.get(symbol) > EXPIRE_MILLS) {
                    ContractConfigDTO configSymbol = getBySymbol(symbol);
                    cache.put(symbol, configSymbol);
                    cacheExpire.put(symbol, System.currentTimeMillis());
                }
                return cache.get(symbol);
            } else {
                ContractConfigDTO configSymbol = getBySymbol(symbol);
                cache.put(symbol, configSymbol);
                cacheExpire.put(symbol, System.currentTimeMillis());
                return configSymbol;
            }
        }
        return null;
    }

    private static void cacheSymbolConfig(ContractConfigDTO dto){
        String symbol = dto.getSymbol().toLowerCase();
        cache.put(symbol, dto);
        cacheExpire.put(symbol, System.currentTimeMillis());
    }

    public ContractConfigDTO getBySymbol(String symbol) {
        ContractConfigSymbolRequest request = ContractConfigSymbolRequest.newBuilder().setSymbol(symbol.toUpperCase()).build();
        ContractConfigPO po = contractConfigBizService.getBySymbol(request);
        return ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, po);
    }

    public List<ContractConfigDTO> getAllSymbolConfig () {
        ContractConfigPageRequest request = ContractConfigPageRequest.newBuilder().build();
        ContractConfigListReply listReply = contractConfigBizService.selectAll(request);
        List<ContractConfigDTO> list = ProtoBeanUtils.toPojoBeanList(ContractConfigDTO.class, listReply.getContractConfigPOList());
        list.forEach(ConfigSymbolCache::cacheSymbolConfig);
        return list;
    }
}
