package com.bjs.contract.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author nike
 * @date 2022年11月10日 10:44
 */
@Data
public class TradePlateItem {
    private BigDecimal price;
    private BigDecimal amount;
}