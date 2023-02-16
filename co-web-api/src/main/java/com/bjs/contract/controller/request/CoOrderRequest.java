package com.bjs.contract.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @author bjs code generator
 * @date 2022-11-09 16:58:08
 */
@Data
@EqualsAndHashCode
@ApiModel
public class CoOrderRequest {
  /**
   * 交易对
   */
  @ApiModelProperty(value = "订单交易对", required = true, example = "BTCUSDT")
  private String symbol;
  /**
   * 持仓类型(1 全仓，2 仓逐)
   */
  @Min(1)
  @Max(2)
  @ApiModelProperty(value = "持仓类型（1 全仓，2 仓逐）", example = "1", hidden = true)
  private Integer positionType;
  /**
   * 买卖方向（buy 买入，sell 卖出）
   */
  @NotBlank
  @ApiModelProperty(value = "买卖方向（buy 买入，sell 卖出）", required = true, example = "buy")
  private String operateSide;
  /**
   * 下单价格
   */
  @ApiModelProperty(value = "下单价格", example = "18000")
  private BigDecimal price;
  /**
   * 下单数量 base
   */
  @DecimalMin("0.00")
  @ApiModelProperty(value = "下单数量 base", example = "10")
  private BigDecimal volumeBase;
  /**
   * 下单数额 quote
   */
  @DecimalMin("0.00")
  @ApiModelProperty(value = "下单数额 quote(下单数量*下单价格)", example = "180000")
  private BigDecimal volumeQuote;
  /**
   * 止盈触发价
   */
  @ApiModelProperty(value = "止盈触发价格", example = "17000")
  private BigDecimal takeProfitPriceTrigger;
  /**
   * 止损触发价
   */
  @ApiModelProperty(value = "止损触发价格", example = "19000")
  private BigDecimal stopLossPriceTrigger;
}
