package com.bijinsuo.common.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Watson
 */
@Data
public class FundingRateSettleArrearsDTO {

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 欠款
     */
    private BigDecimal arrears;
    /**
     * 结算时间
     */
    private Date settleTime;
}
