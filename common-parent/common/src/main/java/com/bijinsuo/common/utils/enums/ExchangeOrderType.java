package com.bijinsuo.common.utils.enums;

/**
 * @author nike
 * @date 2022年11月09日 16:05
 */
public enum ExchangeOrderType {
    /**
     * 撮合市价和限价类型
     */
    MARKET_PRICE(1, "市价单"), LIMIT_PRICE(2, "现价单");


    public Integer value;

    public String msg;

    ExchangeOrderType(Integer value, String msg) {
        this.msg = msg;
        this.value = value;
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
