package com.bjs.contract.entity.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author nike
 * @date 2022年11月10日 10:42
 */
public class MergeOrder {
    private List<MatchOrderVO> orders = new ArrayList<>();

    //最后位置添加一个
    public void add(MatchOrderVO order){
        orders.add(order);
    }


    public MatchOrderVO get(){
        return orders.get(0);
    }

    public int size(){
        return orders.size();
    }

    public BigDecimal getPrice(){
        return orders.get(0).getPrice();
    }

    public Iterator<MatchOrderVO> iterator(){
        return orders.iterator();
    }

    public BigDecimal getTotalAmount() {
        BigDecimal total = new BigDecimal(0);
        for(MatchOrderVO item : orders) {
            total = total.add(item.getVolume());
        }
        return total;
    }
}
