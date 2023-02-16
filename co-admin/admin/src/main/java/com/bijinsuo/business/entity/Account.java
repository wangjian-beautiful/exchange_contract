package com.bijinsuo.business.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Winter
 */
@Data
public class Account {
  private BigDecimal balance;
  private BigDecimal frozen;
  private BigDecimal margin;
}
