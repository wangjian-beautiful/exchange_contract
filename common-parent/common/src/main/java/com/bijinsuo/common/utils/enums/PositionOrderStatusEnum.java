package com.bijinsuo.common.utils.enums;

/**
 * @author Watson
 */
public enum PositionOrderStatusEnum {

    position (0),
    Settled(1);

    private Integer value;

    PositionOrderStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
