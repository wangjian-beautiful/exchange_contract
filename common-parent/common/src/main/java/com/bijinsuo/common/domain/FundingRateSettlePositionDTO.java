package com.bijinsuo.common.domain;

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
public class FundingRateSettlePositionDTO {

    private Long uid;
    private BigDecimal amount;


}
