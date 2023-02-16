package com.bijinsuo.common.utils.enums;

/**
 * @author Watson
 */
public enum ContractConfigStatusEnum {

    CANNOT_TRADE(0, "不可交易"),

    CAN_TRADE(1, "可交易"),
    ;


    private Integer code;

    private String msg;

    ContractConfigStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
