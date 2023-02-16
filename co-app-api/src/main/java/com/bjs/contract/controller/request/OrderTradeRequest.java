package com.bjs.contract.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
@ApiModel
public class OrderTradeRequest {


    @ApiModelProperty(value = "开始时间")
    private Long beginTime;

    @ApiModelProperty(value = "结束时间")
    private Long endTime;

    @ApiModelProperty(value = "每页数量")
    private Integer limit = 20;
    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "合约ID")
    private Integer contractId;

    @ApiModelProperty(value = "合约类型")
    private String type;

    @ApiModelProperty(value = "时间")
    private String uaTime;

    private String uid;

}
