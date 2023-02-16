package com.bijinsuo.common.utils.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nike
 * @date 2022年11月09日 16:03
 */
public enum OrderStatus {
    INIT(0, "初始订单"),
    NEW_(1, "新订单"),
    FILLED(2, "完全成交"),
    PART_FILLED(3, "部分成交"),
    CANCELED(4, "已撤单"),
    PENDING_CANCEL(5, "待撤单");

    public int value;

    public String description;

    OrderStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static OrderStatus fromValue(int value) {
        for (OrderStatus t : OrderStatus.values()) {
            if (t.value == value) {
                return t;
            }
        }
        return null;
    }

    public static OrderStatus fromName(String name) {
        for (OrderStatus t : OrderStatus.values()) {
            if (t.name().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public static List<Integer> getCanCancelValueList(){
        List<Integer> onTheWayList = new ArrayList<>();
        onTheWayList.add(INIT.getValue());
        onTheWayList.add(NEW_.getValue());
        onTheWayList.add(PART_FILLED.getValue());
        return onTheWayList;
    }


    public static List<Integer> getOnTheWayValueList() {
        List<Integer> onTheWayList = new ArrayList<>();
        onTheWayList.add(INIT.getValue());
        onTheWayList.add(NEW_.getValue());
        onTheWayList.add(PART_FILLED.getValue());
        onTheWayList.add(PENDING_CANCEL.getValue());
        return onTheWayList;
    }
}
