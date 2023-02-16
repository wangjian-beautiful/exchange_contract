package com.bijinsuo.business.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bijinsuo.common.annotation.Excel;
import com.bijinsuo.common.core.domain.BaseEntity;

/**
 * 交易流水对象 transaction
 *
 * @author ruoyi
 * @date 2022-11-09
 */
@Data
public class Transaction extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * $column.columnComment
   */
  private Long id;

  /**
   * 转出账户ID
   */
  @Excel(name = "转出账户ID")
  private Long fromUid;

  /**
   * 转出账户type
   */
  @Excel(name = "转出账户type")
  private Long fromType;

  /**
   * 转出后账户余额
   */
  @Excel(name = "转出后账户余额")
  private BigDecimal fromBalance;

  /**
   * 转入账户ID
   */
  @Excel(name = "转入账户ID")
  private Long toUid;

  /**
   * 转入账户type
   */
  @Excel(name = "转入账户type")
  private Long toType;

  /**
   * 转入后账户余额
   */
  @Excel(name = "转入后账户余额")
  private BigDecimal toBalance;

  /**
   * 发生额
   */
  @Excel(name = "发生额")
  private BigDecimal amount;

  /**
   * 转账说明，因为国际化，所以只能对应数字
   */
  @Excel(name = "转账说明，因为国际化，所以只能对应数字")
  private String meta;

  /**
   * 场景，用于连接上下文
   */
  @Excel(name = "场景，用于连接上下文")
  private String scene;

  /**
   * 转账时涉及的主业务表名，或者特性名，不一定准确
   */
  @Excel(name = "转账时涉及的主业务表名，或者特性名，不一定准确")
  private String refType;

  /**
   * 转账时涉及的主业务表ID
   */
  @Excel(name = "转账时涉及的主业务表ID")
  private Long refId;

  /**
   * 操作的UID，0：系统自动转账，主要用于记录后台转账使用
   */
  @Excel(name = "操作的UID，0：系统自动转账，主要用于记录后台转账使用")
  private Long opUid;

  /**
   * 操作的IP，‘’：系统自动转账，主要用于记录后台转账使用
   */
  @Excel(name = "操作的IP，‘’：系统自动转账，主要用于记录后台转账使用")
  private String opIp;

  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
  private Date ctime;

  /**
   * 修改时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
  private Date mtime;

  private Date startTime;
  private Date endTime;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setFromUid(Long fromUid) {
    this.fromUid = fromUid;
  }

  public Long getFromUid() {
    return fromUid;
  }

  public void setFromType(Long fromType) {
    this.fromType = fromType;
  }

  public Long getFromType() {
    return fromType;
  }

  public void setFromBalance(BigDecimal fromBalance) {
    this.fromBalance = fromBalance;
  }

  public BigDecimal getFromBalance() {
    return fromBalance;
  }

  public void setToUid(Long toUid) {
    this.toUid = toUid;
  }

  public Long getToUid() {
    return toUid;
  }

  public void setToType(Long toType) {
    this.toType = toType;
  }

  public Long getToType() {
    return toType;
  }

  public void setToBalance(BigDecimal toBalance) {
    this.toBalance = toBalance;
  }

  public BigDecimal getToBalance() {
    return toBalance;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setMeta(String meta) {
    this.meta = meta;
  }

  public String getMeta() {
    return meta;
  }

  public void setScene(String scene) {
    this.scene = scene;
  }

  public String getScene() {
    return scene;
  }

  public void setRefType(String refType) {
    this.refType = refType;
  }

  public String getRefType() {
    return refType;
  }

  public void setRefId(Long refId) {
    this.refId = refId;
  }

  public Long getRefId() {
    return refId;
  }

  public void setOpUid(Long opUid) {
    this.opUid = opUid;
  }

  public Long getOpUid() {
    return opUid;
  }

  public void setOpIp(String opIp) {
    this.opIp = opIp;
  }

  public String getOpIp() {
    return opIp;
  }

  public void setCtime(Date ctime) {
    this.ctime = ctime;
  }

  public Date getCtime() {
    return ctime;
  }

  public void setMtime(Date mtime) {
    this.mtime = mtime;
  }

  public Date getMtime() {
    return mtime;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", getId())
        .append("fromUid", getFromUid())
        .append("fromType", getFromType())
        .append("fromBalance", getFromBalance())
        .append("toUid", getToUid())
        .append("toType", getToType())
        .append("toBalance", getToBalance())
        .append("amount", getAmount())
        .append("meta", getMeta())
        .append("scene", getScene())
        .append("refType", getRefType())
        .append("refId", getRefId())
        .append("opUid", getOpUid())
        .append("opIp", getOpIp())
        .append("ctime", getCtime())
        .append("mtime", getMtime())
        .toString();
  }
}
