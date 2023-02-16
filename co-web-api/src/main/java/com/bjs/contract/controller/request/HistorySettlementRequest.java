package com.bjs.contract.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@ApiModel
@Data
@EqualsAndHashCode
public class HistorySettlementRequest {
    @ApiModelProperty("用户id")
    private Long uid;
    @ApiModelProperty("合约币队")
    private String symbol;
    @ApiModelProperty("查询页页数")
    private Integer limit;
    @ApiModelProperty("查询页码")
    private Integer page;
    @ApiModelProperty("起始ID")
    private Long fromId;

}
