package com.bijinsuo.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.bijinsuo.business.util.jackson.serialize.EnumSerializer;
import com.bijinsuo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.Objects;

/**
 * 撮合币对配置对象 config_symbol_matching
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Data
public class CoinPair extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * $column.columnComment
   */
  private Long id;

  /**
   * 基准货币，symbol的前半段
   */
  private String base;

  /**
   * 计价货币，symbol的后半段
   */
  private String quote;

  /**
   * 是否开放，0否，1是
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Status isOpen;

  /**
   * 撮合所在服务器IP
   */
  private String server;

  /**
   * $column.columnComment
   */
  private Date ctime;

  /**
   * $column.columnComment
   */
  private Date mtime;

  public enum Status {
    FALSE(0,"否"),TRUE(1,"是");
    public final int tag;
    public final String desc;

    Status(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static Status from(int tag) {
      for (Status status : Status.values()) {
        if (Objects.equals(status.tag, tag)) {
          return status;
        }
      }
      return null;
    }

    public static Status from(String desc) {
      for (Status status : Status.values()) {
        if (Objects.equals(status.desc, desc)) {
          return status;
        }
      }
      return null;
    }
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", getId())
        .append("base", getBase())
        .append("quote", getQuote())
        .append("isOpen", getIsOpen())
        .append("server", getServer())
        .append("ctime", getCtime())
        .append("mtime", getMtime())
        .toString();
  }
}