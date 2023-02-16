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
@ApiModel("普通委托列表")
public class CoTriggerOrderListResp {
    @ApiModelProperty("合约币队id")
    private Long contractId;
    @ApiModelProperty("合约名称")
    private String contractName;
    @ApiModelProperty("别名")
    private String contractOtherName;
    @ApiModelProperty("创建时间")
    private String ctime;
    @ApiModelProperty("过期时间")
    private String expireTime;
    @ApiModelProperty("订单id")
    private Long id;
    @ApiModelProperty("")
    private String memo;
    @ApiModelProperty("更新时间")
    private String mtime;
//    @ApiModelProperty("触发时间")
//    private String triggerTime;
    @ApiModelProperty("开平仓方向(open 开仓，close 平仓)")
    private String open;
    @ApiModelProperty("下单价格")
    private String price;
    @ApiModelProperty("价格精度")
    private String pricePrecision;
    @ApiModelProperty("买卖方向（buy 买入，sell 卖出）")
    private String side;
    @ApiModelProperty("有效状态（-1 未生效 0有效，1已过期，2已完成，3触发失败4已撤销）")
    private String status;
    @ApiModelProperty("交易对")
    private String symbol;
    @ApiModelProperty("")
    private String timeInForce;
    @ApiModelProperty("委托为限价方式的时候，限价价格")
    private String triggerPrice;
    @ApiModelProperty("条件单类型（1 stop loss 止损单，2 take profit 止盈单 3 normal 条件单）")
    private String triggerType;
    @ApiModelProperty("0: 市价止盈  1: 限价止盈 2:条件市价止盈 3条件限价止盈 4：市价止损 5限价止损 6 条件市价止盈 7条件限价止损 8强平 9穿仓 10市价开仓 11限价开仓 12市价平仓 13条件限价开仓 14条件市价开仓 15资金费率结算欠款市价平仓")
    private String type;
    @ApiModelProperty("下单数量 quote")
    private String volume;
}
