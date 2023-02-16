package com.bjs.contract.entity.vo;

import com.bijinsuo.common.utils.enums.ExchangeOrderType;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author nike
 * @date 2022年11月10日 10:43
 */
@Data
@Slf4j
public class TradePlate {
    private LinkedList<TradePlateItem> items;
    //最大深度
    private int maxDepth = 100;
    //方向
    private OperateSideEnum direction;
    private String symbol;
    public TradePlate(){

    }

    public TradePlate(String symbol, OperateSideEnum direction) {
        this.direction = direction;
        this.symbol = symbol;
        items = new LinkedList<>();
    }

    public boolean add(MatchOrderVO exchangeOrder) {
        //log.info("add TradePlate order={}",exchangeOrder);
        synchronized (this) {
            int index = 0;
            if (Objects.equals(exchangeOrder.getType(), ExchangeOrderType.MARKET_PRICE.value)) {
                return false;
            }
            if (exchangeOrder.getSide()!=direction) {
                return false;
            }
            if (items.size() > 0) {
                for (index = 0; index < items.size(); index++) {
                    TradePlateItem item = items.get(index);
                    if (direction==OperateSideEnum.BUY && item.getPrice().compareTo(exchangeOrder.getPrice()) > 0
                            || direction==OperateSideEnum.SELL && item.getPrice().compareTo(exchangeOrder.getPrice()) < 0) {
                        log.debug("");
                    } else if (item.getPrice().compareTo(exchangeOrder.getPrice()) == 0) {
                        BigDecimal deltaAmount = exchangeOrder.getVolume().subtract(exchangeOrder.getDealVolume());
                        item.setAmount(item.getAmount().add(deltaAmount));
                        return true;
                    } else {
                        break;
                    }
                }
            }
            if(index < maxDepth) {
                TradePlateItem newItem = new TradePlateItem();
                newItem.setAmount(exchangeOrder.getVolume().subtract(exchangeOrder.getDealVolume()));
                newItem.setPrice(exchangeOrder.getPrice());
                items.add(index, newItem);
            }
        }
        return true;
    }

    public void remove(MatchOrderVO order,BigDecimal amount) {
        synchronized (this) {
            //log.info("items>>init_size={},orderPrice={}",items.size(),order.getPrice());
            for (int index = 0; index < items.size(); index++) {
                TradePlateItem item = items.get(index);
                if (item.getPrice().compareTo(order.getPrice()) == 0) {
                    item.setAmount(item.getAmount().subtract(amount));
                    if (item.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                        items.remove(index);
                    }
                    //log.info("items>>final_size={},itemAmount={},itemPrice={}",items.size(),item.getAmount(),item.getPrice());
                    return;
                }
            }
            log.info("items>>return_size={}",items.size());
        }
    }

    public void remove(MatchOrderVO order){
        remove(order,order.getVolume().subtract(order.getDealVolume()));
    }

    public void setItems(LinkedList<TradePlateItem> items){
        this.items = items;
    }

    public BigDecimal getHighestPrice(){
        if(items.size() == 0) {
            return BigDecimal.ZERO;
        }
        if(direction==OperateSideEnum.BUY){
            return items.getFirst().getPrice();
        }
        else{
            return items.getLast().getPrice();
        }
    }

    public int getDepth(){
        return items.size();
    }


    public BigDecimal getLowestPrice(){
        if(items.size() == 0) {
            return BigDecimal.ZERO;
        }
        if(direction==OperateSideEnum.BUY){
            return items.getLast().getPrice();
        }
        else{
            return items.getFirst().getPrice();
        }
    }

    /**
     * 获取委托量最大的档位
     * @return
     */
    public BigDecimal getMaxAmount(){
        if(items.size() == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal amount = BigDecimal.ZERO;
        for(TradePlateItem item:items){
            if(item.getAmount().compareTo(amount)>0){
                amount = item.getAmount();
            }
        }
        return amount;
    }

    /**
     * 获取委托量最小的档位
     * @return
     */
    public BigDecimal getMinAmount(){
        if(items.size() == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal amount = items.getFirst().getAmount();
        for(TradePlateItem item:items){
            if(item.getAmount().compareTo(amount) < 0){
                amount = item.getAmount();
            }
        }
        return amount;
    }
}
