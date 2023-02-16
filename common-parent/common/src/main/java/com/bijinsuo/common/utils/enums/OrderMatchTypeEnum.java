package com.bijinsuo.common.utils.enums;

public enum OrderMatchTypeEnum {
    NO_MATCH(0, "不进撮合"),
    MARKET(1, "市价单"),
    LIMIT(2, "限价单");

    public Integer value;
    public String msg;

    OrderMatchTypeEnum (Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
