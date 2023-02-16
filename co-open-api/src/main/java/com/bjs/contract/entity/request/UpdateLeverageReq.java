package com.bjs.contract.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 修改杠杠请求
 *
 * @author Watson
 */
@Data
@EqualsAndHashCode
@ApiModel
public class UpdateLeverageReq {

    @NotBlank
    @ApiModelProperty(value = "币队", required = true, example = "BTCUSDT")
    private String symbol;

    @NotNull
    @Min(1)
    @Max(125)
    @ApiModelProperty(value = "杠杆倍数", required = true, example = "25")
    private Integer leverage;



}
