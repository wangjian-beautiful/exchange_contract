package com.bjs.contract.controller.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class OldTradeDto {
    private String symbol;
    private BigDecimal pricePrecision;
    private String contractOtherName;
    private String side;
    private String role;
    private BigDecimal fee;
    private BigDecimal feeCoinPrecision;
    private BigDecimal volume;
    private String feeCoin;
    private String quote;
    private BigDecimal price;
    private int contractId;
    private long ctime;
    private String contractName;
    private Long id;
    private String open;
    private String base;


}
