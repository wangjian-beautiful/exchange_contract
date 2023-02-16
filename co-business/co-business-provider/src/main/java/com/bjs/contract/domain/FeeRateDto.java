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
public class FeeRateDto {

    private BigDecimal closeFeeRate;
    private BigDecimal minFee;
}
