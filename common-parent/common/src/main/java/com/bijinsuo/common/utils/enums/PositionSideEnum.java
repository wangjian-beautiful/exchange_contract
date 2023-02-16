package com.bijinsuo.common.utils.enums;

/**
 * @author Watson
 */
public enum PositionSideEnum {

    BUY,
    SELL;

    public PositionSideEnum opposite() {
        return this == PositionSideEnum.BUY ? SELL : BUY;
    }


    public static PositionSideEnum getCloseSide(String positionSide) {
        return PositionSideEnum.valueOf(positionSide).getCloseSide();
    }

    public PositionSideEnum getCloseSide() {
        return this == PositionSideEnum.BUY ? SELL : BUY;
    }

}
