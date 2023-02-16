package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author nike
 * @date 2022年12月01日 11:05
 */
@Data
@EqualsAndHashCode
@ApiModel("普通委托列表")
public class CoCurrentOrderResp {
    @ApiModelProperty("普通委托列表数量")
    private Long count;
    @ApiModelProperty("普通委托列表")
    private List<CoCurrentOrderListResp> orderList;
}
