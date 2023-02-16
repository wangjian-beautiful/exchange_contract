package com.bjs.contract.domain;

import lombok.Data;

/**
 * @author Watson
 */
@Data
public class FundingRateDTO {
    /**
     * 当期资金费率
     */
    private String currentFundRate;
    /**
     * 预测下期资金费率
     */
    private String nextFundRate;
}
