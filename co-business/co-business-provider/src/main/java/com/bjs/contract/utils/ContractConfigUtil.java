package com.bjs.contract.utils;

import com.bjs.contract.proto.contractConfig.ContractConfigPO;

import java.util.concurrent.ConcurrentHashMap;

public class ContractConfigUtil {

    private static final ConcurrentHashMap<String, ContractConfigPO> mapContractConfig = new ConcurrentHashMap<>();

    public static ContractConfigPO getContractConfig(String symbol) {
        return mapContractConfig.get(symbol.toUpperCase());
    }

    public static void addContractConfig(ContractConfigPO contractConfigPO) {
        mapContractConfig.put(contractConfigPO.getSymbol().toUpperCase(), contractConfigPO);
    }

    public static void delPairConfig(String symbol) {
        mapContractConfig.entrySet().removeIf(entry -> (entry.getKey()).equalsIgnoreCase(symbol));
    }
}
