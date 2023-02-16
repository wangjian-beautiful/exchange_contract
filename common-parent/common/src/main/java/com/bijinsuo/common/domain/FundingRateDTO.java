package com.bijinsuo.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金费率
 *
 * @author bjs code generator
 * @date 2022-11-09 17:31:42
 */
@Data
@EqualsAndHashCode
public class FundingRateDTO {

    /**
     * id
     */
    private Long id;

    /**
     *  交易对 BTC-USDT
     */
    private String symbol;

    /**
     * 资金费率
     */
    private BigDecimal rate;

    /**
     * 期数时间
     */
    private Date settleTime;

    /**
     * mtime
     */
    private Date mtime;

    /**
     * ctime
     */
    private Date ctime;

}
