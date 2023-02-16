package com.bijinsuo.common.utils.enums;

public enum TriggerTypeEnum {

    STOP_LOSS(1, "止损"),
    TAKE_PROFIT(2, "止盈"),

    NORMAL(3, "条件单");

    private Integer code;
    private String name;

    TriggerTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
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
}
