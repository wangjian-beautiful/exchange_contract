package com.bijinsuo.common.utils.enums;

/**
 * 条件委托订单状态枚举
 *
 * @author Watson
 */
public enum TriggerStatusEnum {

    //-1 未生效 0有效，1已过期，2已完成，3触发失败4已撤销

    NOT_ACTIVE(-1, "未生效"),
    ACTIVE(0, "已生效"),
    EXPIRED(1, "已过期"),
    FINISH(2, "已完成"),
    TRIGGER_FAILED(3, "触发失败"),
    CANCELED(4, "已撤销");


    private Integer code;

    private String name;

    TriggerStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
