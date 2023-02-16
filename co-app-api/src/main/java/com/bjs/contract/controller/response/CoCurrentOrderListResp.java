package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author nike
 * @date 2022年12月01日 11:05
 */
@Data
@EqualsAndHashCode
@ApiModel("普通委托列表")
public class CoCurrentOrderListResp {
    @ApiModelProperty("成交均价")
    private String avgPrice;
    @ApiModelProperty("合约币队id")
    private Long contractId;
    @ApiModelProperty("合约名称")
    private String contractName;
    @ApiModelProperty("别名")
    private String contractOtherName;
    @ApiModelProperty("创建时间")
    private String ctime;
    @ApiModelProperty("成交数量(数据库)")
    private BigDecimal dealBase;
    @ApiModelProperty("成交数额(数据库)")
    private BigDecimal dealQuote;
    @ApiModelProperty("成交数量")
    private String dealVolume;
    @ApiModelProperty("订单id")
    private Long id;
    @ApiModelProperty("")
    private String liqPositionMsg;
    @ApiModelProperty("")
    private String memo;
    @ApiModelProperty("更新时间")
    private String mtime;
    @ApiModelProperty("开平仓方向(open 开仓，close 平仓)")
    private String open;
    @ApiModelProperty("")
    private String orderBalance;
    @ApiModelProperty("")
    private Integer positionType;
    @ApiModelProperty("下单价格")
    private String price;
    @ApiModelProperty("价格精度")
    private String pricePrecision;
    @ApiModelProperty("买卖方向（buy 买入，sell 卖出）")
    private String side;
    @ApiModelProperty("订单状态（订单状态（订单状态：0 init，1 new，2 filled，3 part_filled，4 canceled，5 pending_cancel")
    private Integer status;
    @ApiModelProperty("交易对")
    private String symbol;
    @ApiModelProperty("")
    private String tradeFee;
    @ApiModelProperty("0: 市价止盈  1: 限价止盈 2:条件市价止盈 3条件限价止盈 4：市价止损 5限价止损 6 条件市价止盈 7条件限价止损 8强平 9穿仓 10市价开仓 11限价开仓 12市价平仓 13条件限价开仓 14条件市价开仓 15资金费率结算欠款市价平仓")
    private Integer type;
    @ApiModelProperty("下单数量 quote")
    private BigDecimal volumeBase;
    @ApiModelProperty("下单数量 quote")
    private BigDecimal volumeQuote;
    @ApiModelProperty("下单数量")
    private String volume;
    @ApiModelProperty("止盈止损")
    private CoOtoOrderResp otoOrder;
}
