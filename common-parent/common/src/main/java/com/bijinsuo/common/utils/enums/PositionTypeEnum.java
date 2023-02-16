package com.bijinsuo.common.utils.enums;

/**
 * @author Watson
 */
public enum PositionTypeEnum {
    CROSS_MARGIN(1, "全仓"),
    ISOLATED_MARGIN(2, "逐仓");

    private Integer value;
    private String name;

    PositionTypeEnum (Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
