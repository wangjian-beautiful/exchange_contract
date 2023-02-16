package com.bijinsuo.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易流水
 *
 * @author bjs code generator
 * @date 2022-11-09 17:56:30
 */
@Data
@EqualsAndHashCode
public class TransactionDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 转出账户ID
     */
    private Long fromUid;

    /**
     * 转出账户type
     */
    private Integer fromType;

    /**
     * 转出后账户余额
     */
    private BigDecimal fromBalance;

    /**
     * 转入账户ID
     */
    private Long toUid;

    /**
     * 转入账户type
     */
    private Integer toType;

    /**
     * 转入后账户余额
     */
    private BigDecimal toBalance;

    /**
     * 发生额
     */
    private BigDecimal amount;

    /**
     * 转账说明，因为国际化，所以只能对应数字
     */
    private String meta;

    /**
     * 场景，用于连接上下文
     */
    private String scene;

    /**
     * 转账时涉及的主业务表名，或者特性名，不一定准确
     */
    private String refType;

    /**
     * 转账时涉及的主业务表ID
     */
    private Long refId;

    /**
     * 操作的UID，0：系统自动转账，主要用于记录后台转账使用
     */
    private Long opUid;

    /**
     * 操作的IP，‘’：系统自动转账，主要用于记录后台转账使用
     */
    private String opIp;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date mtime;

}
