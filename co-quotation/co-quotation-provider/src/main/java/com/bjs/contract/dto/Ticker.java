package com.bjs.contract.dto;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class Ticker {
    private String symbol;
    private BigDecimal open = BigDecimal.ZERO;
    private BigDecimal high = BigDecimal.ZERO;
    private BigDecimal low = BigDecimal.ZERO;
    private BigDecimal close = BigDecimal.ZERO;
    private BigDecimal vol = BigDecimal.ZERO;
    private BigDecimal amount = BigDecimal.ZERO;
    //当天第一笔交易
    private BigDecimal firstPrice = BigDecimal.ZERO;
    private BigDecimal rose = BigDecimal.ZERO.setScale(2);

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("open", this.getOpen());
        map.put("high", this.getHigh());
        map.put("low", this.getLow());
        map.put("close", this.getClose());
        map.put("vol", this.getVol());
        map.put("amount", this.getAmount());
        map.put("firstPrice", this.getFirstPrice());
        map.put("rose", this.getRose());
        return map;
    }
}



