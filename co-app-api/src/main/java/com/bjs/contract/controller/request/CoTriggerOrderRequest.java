package com.bjs.contract.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author bjs code generator
 * @date 2022-11-09 16:57:53
 */
@ApiModel
@Data
@EqualsAndHashCode
public class CoTriggerOrderRequest {
  /**
   * 交易对
   */
  @ApiModelProperty(value = "订单交易对", required = true, example = "BTCUSDT")
  @NotBlank
  private String symbol;

  /**
   * 触发价格
   */
  @NotNull
  @ApiModelProperty(value = "触发价格", required = true, example = "15000.00")
  private BigDecimal triggerPrice;

  /**
   * 持仓类型(1 全仓，2 逐仓)
   * 先对前端屏蔽该属性，后续有更多条件单了再引入
   */
//  @Min(1)
//  @Max(2)
  @ApiModelProperty(value = "持仓类型", hidden = true)
  private Integer positionType = 1;

  /**
   * 开平仓方向(open 开仓，close 平仓)
   */
  @ApiModelProperty(value = "开平仓方向(open 开仓，close 平仓)", required = true, example = "open")
  private String operateType;

  /**
   * 买卖方向（buy 买入，sell 卖出）
   */
  @ApiModelProperty(value = "买卖方向（buy 买入，sell 卖出）", required = true, example = "buy")
  @NotBlank
  private String operateSide;

  /**
   * 下单价格
   */
  @ApiModelProperty(value = "下单价格，不传入该参数时，表示按市价开/平仓", example = "16500.00")
  private BigDecimal price;

  /**
   * 下单数量
   */
  @ApiModelProperty(value = "下单数量（限价单需要该参数）", example = "10")
  private BigDecimal volumeBase;
  /**
   * 下单数额
   */
  @ApiModelProperty(value = "下单数额（市价单需要该参数）", example = "200000")
  private BigDecimal volumeQuote;

  /**
   * 订单状态备注
   */
  @ApiModelProperty(value = "订单状态备注", hidden = true)
  private Integer memo;

  /**
   * 0 普通条件单，1 OTO类型条件单
   * 先对前端屏蔽该属性，后续有更多条件单了再引入
   */
  @ApiModelProperty(value = "条件单类型，0 普通条件单，1 OTO类型条件单", hidden = true)
  private Integer type = 0;
}
