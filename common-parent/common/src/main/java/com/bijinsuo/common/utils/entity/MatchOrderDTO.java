package com.bijinsuo.common.utils.entity;

import com.bijinsuo.common.utils.enums.MatchInfoTypeEnum;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import com.bijinsuo.common.utils.enums.OperateTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author nike
 * @date 2022年11月09日 15:07
 */
@Data
public class MatchOrderDTO {
    //订单id
    private Long id;
    //用户id
    private Integer userId;
    //买卖方向
    private OperateSideEnum side;
    //限价单挂单价格
    private BigDecimal price;
    //挂单总数量
    private BigDecimal volume;
    //交易对符号
    private String symbol;
    //委托类型：1 limit，2 market
    private Integer type;
    //状态
    private Integer status;
    //是否是机器人 0 不是 1 是
    private Integer robot;
    //开平仓方向(open 开仓，close 平仓)
    private OperateTypeEnum operateType;
    //消息类型
    private MatchInfoTypeEnum matchInfoType;
}
