package com.bijinsuo.common.utils.enums;

/**
 * @author nike
 * @date 2022年11月10日 15:24
 */
public enum MatchInfoTypeEnum {
    match_order(1,"正常撮合"),
    cancel_order(2,"撤销");


    public Integer value;

    public String msg;

    MatchInfoTypeEnum(Integer value,String msg){
        this.msg=msg;
        this.value=value;
    }





    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
