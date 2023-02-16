package com.bijinsuo.business.entity;

import com.bijinsuo.common.annotation.Excel;
import com.bijinsuo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户对象 user
 *
 * @author ruoyi
 * @date 2022-11-07
 */
@Data
public class User extends BaseEntity {
  private static final long serialVersionUID = 1L;

  private Long uid;

  @Excel(name = "open api 调用时使用的apiKey")
  private String apiKey;
  private Boolean tradable;
  private Boolean transferable;

  private Account account;

  private Integer delFlag;

  private Date startTime;
  private Date endTime;


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("uid", getUid())
        .append("apiKey", getApiKey())
        .append("delFlag", getDelFlag())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .toString();
  }
}
