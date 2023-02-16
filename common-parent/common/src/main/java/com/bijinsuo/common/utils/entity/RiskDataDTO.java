package com.bijinsuo.common.utils.entity;

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
public class RiskDataDTO {

    /**
     * 总保证金
     */
    private BigDecimal margin = BigDecimal.ZERO;


    /**
     * 维持保证金+爆仓手续费
     */
    private BigDecimal marginRatioMolecule = BigDecimal.ZERO;

    private BigDecimal closeFee = BigDecimal.ZERO;


    private BigDecimal nominalValue = BigDecimal.ZERO;
}
