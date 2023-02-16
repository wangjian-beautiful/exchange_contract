package com.bjs.contract.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nike
 * @date 2022年12月01日 11:05
 */
@ApiModel
@Data
@EqualsAndHashCode
public class CurrentOrderRequest {
    @ApiModelProperty("用户id")
    private Long uid;
    @ApiModelProperty("合约币队id")
    private Long contractId;
    @ApiModelProperty("查询页页数")
    private Integer limit;
    @ApiModelProperty("查询页码")
    private Integer page;
    @ApiModelProperty("合约类型 0: 市价止盈  1: 限价止盈 2:条件市价止盈 3条件限价止盈 4：市价止损 5限价止损 6 条件市价止盈 7条件限价止损 8强平 9穿仓 10市价开仓 11限价开仓 12市价平仓 13条件限价开仓 14条件市价开仓 15资金费率结算欠款市价平仓")
    private Integer type;
}
