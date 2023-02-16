package com.bijinsuo.common.utils.entity;

import com.bijinsuo.common.utils.enums.OperateSideEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author nike
 * @date 2022年11月10日 15:44
 */
@Data
public class MatchTradeDetailsDTO {

    //价格
    private BigDecimal price;
    //数量
    private BigDecimal amount;
    //成交额
    private BigDecimal turnover;
    //主动单方向
    private OperateSideEnum trendSide;
    //买订单号
    private Long buyOrderId;
    //卖订单号
    private Long sellOrderId;
    //是否完成
    private Boolean buyFinish=false;
    //是否完成
    private Boolean sellFinish=false;
    //成交时间戳
    private Long time;
}
