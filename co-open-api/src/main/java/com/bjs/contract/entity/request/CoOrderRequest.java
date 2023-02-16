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
public class CoOrderRequest extends BaseRequest{
  /**
   * 合约名称
   */
  @ApiModelProperty(value = "合约名称", required = true, example = "BTCUSDT")
  private String contractName;
  /**
   * 订单id
   */
  @ApiModelProperty(value = "订单id", required = true, example = "BTCUSDT")
  private String orderId;
  /**
   * 客户端唯一标识
   */
  @ApiModelProperty(value = "客户端唯一标识")
  private String clientOrderId;
}
