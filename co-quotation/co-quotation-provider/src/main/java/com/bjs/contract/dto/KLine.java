package com.bjs.contract.dto;


import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bjs.contract.util.ConfigSymbolCache;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class KLine {


    //交易对 ETH/USDT
    private String symbol;
    //开盘价
    private BigDecimal open = BigDecimal.ZERO;
    //最高价
    private BigDecimal high = BigDecimal.ZERO;
    //最低价
    private BigDecimal low = BigDecimal.ZERO;
    // 收盘价
    private BigDecimal close = BigDecimal.ZERO;
    //时间戳
    private long time;
    private long id;
    //单位 1min 5min
    private String period;

    /**
     * 成交笔数
     */
    private int count;
    /**
     * 成交量
     */
    private BigDecimal vol = BigDecimal.ZERO;
    /**
     * 成交额
     */
    private BigDecimal amount = BigDecimal.ZERO;

    /**
     * 聚合KLine时 最后一根K线的时间
     */
    private long lastKlineTime;

    private KLine() {
    }

    public static KLine createNewKLine(String symbol) {
        KLine kLine = new KLine();
        kLine.setSymbol(symbol);
        ContractConfigDTO configDTO = ConfigSymbolCache.instance().getBySymbol(symbol);
        Integer basePrecision = configDTO.getBasePrecision();
        Integer quotePrecision = configDTO.getQuotePrecision();
        kLine.open = BigDecimal.ZERO.setScale(quotePrecision, RoundingMode.DOWN);
        kLine.close = BigDecimal.ZERO.setScale(quotePrecision, RoundingMode.DOWN);
        kLine.high = BigDecimal.ZERO.setScale(quotePrecision, RoundingMode.DOWN);
        kLine.low = BigDecimal.ZERO.setScale(quotePrecision, RoundingMode.DOWN);
        kLine.amount = BigDecimal.ZERO.setScale(quotePrecision, RoundingMode.DOWN);
        kLine.vol = BigDecimal.ZERO.setScale(basePrecision, RoundingMode.DOWN);
        return kLine;
    }

    public void setTime(long time) {
        this.time = time;
        this.id = time / 1000;
    }
}
