package com.bijinsuo.common.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Winter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelResultDTO {
  private long id;
  private boolean status;
}
