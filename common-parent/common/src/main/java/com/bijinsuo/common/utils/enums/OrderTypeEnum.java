package com.bijinsuo.common.utils.enums;

public enum OrderTypeEnum {
    TAKE_PROFIT_MARKET(0, "市价止盈", true, 1),
    TAKE_PROFIT_LIMIT(1, "限价止盈", false, 2),
    TAKE_PROFIT_TRIGGER_MARKET(2, "条件市价止盈", true, 1),
    TAKE_PROFIT_TRIGGER_LIMIT(3, "条件限价止盈", false, 2),
    STOP_LOSS_MARKET(4, "市价止损", true, 1),
    STOP_LOSS_LIMIT(5, "限价止损", false, 2),
    STOP_LOSS_TRIGGER_MARKET(6, "条件市价止损", true, 1),
    STOP_LOSS_TRIGGER_LIMIT(7, "条件限价止损", false, 2),
    LIQUIDATION(8, "强平", true, 0),
    WEARING(9, "穿仓", true, 0),
    OPEN_MARKET(10, "市价开仓", true, 1),
    OPEN_LIMIT(11, "限价开仓", false, 2),
    CLOSE_POSITION_MARKET(12, "市价平仓", true, 1),
    CLOSE_POSITION_TRIGGER_LIMIT(13, "条件限价开仓", false, 2),
    CLOSE_POSITION_TRIGGER_MARKET(14, "条件市价开仓", true, 1),
    SETTLE_ARREARS_MARKET_CLOSE(15, "资金费率结算欠款市价平仓", true, 1);

    private Integer code;
    private String name;
    private Boolean isMarket;
    /**
     * 撮合 类型  0：不进撮合 1:市价 :2限价
     */
    private Integer matchType;

    OrderTypeEnum(Integer code, String name, Boolean isMarket, Integer matchType) {
        this.code = code;
        this.name = name;
        this.isMarket = isMarket;
        this.matchType = matchType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMarket() {
        return isMarket;
    }



    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public static OrderTypeEnum getEnumByCode(Integer code) throws RuntimeException {
        OrderTypeEnum[] values = values();
        for (OrderTypeEnum value : values) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new RuntimeException("Invalid code for OrderTypeEnum:" + code);
    }
}
