package com.bijinsuo.common.utils.enums;

/**
 * @author Watson
 */
public enum OrderSourceEnum {

    WEB(1, "web"),
    APP(2, "app"),
    OPEN_API(3, "openApi"),
    SYS(4, "系统自身");

    public int value;

    public String description;

    OrderSourceEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }
}
