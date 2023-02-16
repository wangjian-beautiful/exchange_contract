package com.bijinsuo.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户明细
 *
 * @author bjs code generator
 * @date 2022-11-09 16:58:21
 */
@Data
@EqualsAndHashCode
public class AccountDTO {

    /**
     * id
     */
    private Long id;

    /**
     * UID，10000以内保留，作为公司账户
     */
    private Long uid;

    /**
     * 类型 0 账户余额 1冻结 2保证金 
     */
    private Integer type;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 标签，冗余，只帮助直观反馈
     */
    private String tag;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date mtime;

}
