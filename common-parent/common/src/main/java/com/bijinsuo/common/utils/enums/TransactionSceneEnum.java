package com.bijinsuo.common.utils.enums;

/**
 * @author nike
 * @date 2022年11月14日 13:13
 */
public enum TransactionSceneEnum {

    SCRATCH_IN("SCRATCH_IN", "资金划进", 1, 0, 0),
    CROSS_OUT("CROSS_OUT", "资金划出", -1, 0, 0),
    OPEN_ORDER("OPEN_ORDER", "委托下单", -1, 1, 0),
    OPEN_POSITION("OPEN_POSITION", "开仓", 0, -1, 1),
    SCALE_IN("SCALE_IN", "加仓", 0, -1, 1),
    CLOSE_POSITION("CLOSE_POSITION", "平仓", 1, 0, -1),
    CORRECT_BOND("CORRECT_BOND", "平仓", 1, 0, -1),
    //    PROFIT_CLOSE_POSITION("PROFIT_CLOSE_POSITION","盈利平仓",1,0,-1),
//    LOSS_CLOSE_POSITION("LOSS_CLOSE_POSITION","亏损平仓"),
    CANCEL_ORDER("CANCEL_ORDER", "撤单", 1, -1, 0),
    LIQUIDATION("liquidation", "爆仓", -1, -1, -1),
    WEAR_POSITIONS("WEAR_POSITIONS", "穿仓", -1, -1, -1),
    AUTOMATIC_CLOSING("AUTOMATIC_CLOSING", "自动平仓", 0, 0, -1),

    CLOSE_FEE("CLOSE_FEE", "平仓手续费扣减", -1, 0, 0),
    REFUND_FEE("REFUND_FEE", "开仓手续费退回", 1, -1, 0),
    CLOSE_POSITION_MARGIN("CLOSE_POSITION_MARGIN", "完全平仓保证金退回", 1, 0, -1),

    UPDATE_LEVERAGE_INCREASE_LOCK_MARGIN("UPDATE_LEVERAGE_INCREASE_LOCK_MARGIN", "修改杠杠倍数增加冻结保证金", -1, 1, 0),
    UPDATE_LEVERAGE_DECREASE_LOCK_MARGIN("UPDATE_LEVERAGE_DECREASE_LOCK_MARGIN", "修改杠杠倍数减少冻结保证金", 1, -1, 0),
    UPDATE_LEVERAGE_INCREASE_MARGIN("UPDATE_LEVERAGE_INCREASE_MARGIN", "修改杠杠倍数增加保证金", -1, 0, 1),
    UPDATE_LEVERAGE_DECREASE_MARGIN("UPDATE_LEVERAGE_DECREASE_MARGIN", "修改杠杠倍数减少保证金", 1, 0, -1),


    ;

    private final String value;
    private final String msg;
    private final Integer normal;
    private final Integer frozen;
    private final Integer bond;

    TransactionSceneEnum(String value, String msg, Integer normal, Integer frozen, Integer bond) {
        this.value = value;
        this.msg = msg;
        this.normal = normal;
        this.frozen = frozen;
        this.bond = bond;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getNormal() {
        return normal;
    }

    public Integer getFrozen() {
        return frozen;
    }

    public Integer getBond() {
        return bond;
    }

    public static TransactionSceneEnum getOperation(String value) {
        for (TransactionSceneEnum t : TransactionSceneEnum.values()) {
            if (t.value.equals(value)) {
                return t;
            }
        }
        return null;
    }
}
