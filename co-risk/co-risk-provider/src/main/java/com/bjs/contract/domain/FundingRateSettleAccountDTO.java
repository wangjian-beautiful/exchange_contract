package com.bjs.contract.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Watson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundingRateSettleAccountDTO {

    private Long uid;
    private BigDecimal amount;
    private String symbol;


}
