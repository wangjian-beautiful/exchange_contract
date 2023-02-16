package com.bijinsuo.common.utils.enums;

/**
 * @author nike
 * @date 2022年11月09日 15:14
 */
public enum OperateSideEnum {
    BUY,
    SELL;

    public static OperateSideEnum getOpposite(String name) {
        if (OperateSideEnum.valueOf(name) == BUY) return SELL;
        return BUY;
    }


    public PositionSideEnum toPositionSide() {
        return PositionSideEnum.valueOf(name());
    }

    public OperateSideEnum opponent() {
        return this == BUY ? SELL : BUY;
    }
}
