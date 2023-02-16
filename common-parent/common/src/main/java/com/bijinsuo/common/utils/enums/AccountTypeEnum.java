package com.bijinsuo.common.utils.enums;

/**
 * @author nike
 * @date 2022年11月07日 13:56
 */
public enum AccountTypeEnum {

    normal(0,"可用余额"),
    frozen(1,"冻结余额"),
    bond(2,"保证金"),
    ventureCapital(3,"风险准备金"),
    serviceCharge(4,"手续费"),
    deductCapital(5,"扣减资金");

    private final Integer code;

    private final String msg;

    AccountTypeEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }


    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
