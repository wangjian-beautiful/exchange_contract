package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nike
 * @date 2022年12月01日 11:05
 */
@Data
@EqualsAndHashCode
@ApiModel("普通委托条件委托数量")
public class UserOrderCountResp {

    @ApiModelProperty("普通委托数量")
    private Integer orderCount;
    @ApiModelProperty("条件委托数量")
    private Integer triggerOrderCount;
}
