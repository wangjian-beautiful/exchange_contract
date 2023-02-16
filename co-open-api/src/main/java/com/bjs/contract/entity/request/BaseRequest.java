package com.bjs.contract.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bjs code generator
 * @date 2022-11-09 16:58:08
 */
@Data
@EqualsAndHashCode
@ApiModel
public class BaseRequest {
  /**
   * api_key
   */
  @ApiModelProperty(value = "api_key")
  private String api_key;
  /**
   * time
   */
  @ApiModelProperty(value = "time")
  private String time;
  /**
   * sign
   */
  @ApiModelProperty(value = "sign")
  private String sign;
}
