package com.bjs.contract.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author bjs code generator
 * @date 2022-11-09 16:58:08
 */
@Data
@EqualsAndHashCode
@ApiModel
public class CoOrderCloseRequest {
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
    private  Integer positionType = 1;
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
    @NotNull
    @ApiModelProperty(value = "下单数量 base", example = "10")
    private BigDecimal volumeBase;

}
