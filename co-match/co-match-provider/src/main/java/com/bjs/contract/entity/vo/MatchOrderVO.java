package com.bjs.contract.entity.vo;

import com.bijinsuo.common.utils.enums.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author nike
 * @date 2022年11月09日 16:00
 */
@Data
public class MatchOrderVO {
    //订单id
    private Long id;
    //用户id
    private Long userId;
    //买卖方向
    private OperateSideEnum side;
    //限价单挂单价格
    private BigDecimal price;
    //挂单总数量
    private BigDecimal volume;
    //交易对符号
    private String symbol;
    //成交量
    private BigDecimal dealVolume;
    //已成交金额
    private BigDecimal dealMoney;
    //委托类型：2 limit，1 market
    private Integer type;
    //状态
    private Integer status;
    //是否是机器人 0 不是 1 是
    private Integer robot;
    //开平仓方向(open 开仓，close 平仓)
    private OperateTypeEnum operateType;

    //消息类型
    private MatchInfoTypeEnum matchInfoType;

    public boolean isCompleted() {
        if (status != OrderStatus.INIT.value &&
                status != OrderStatus.NEW_.value &&
                status != OrderStatus.PART_FILLED.value) {
            return true;
        } else {
            if (Objects.equals(type, ExchangeOrderType.MARKET_PRICE.value) && side==OperateSideEnum.BUY) {
                return volume.compareTo(dealMoney) <= 0;
            } else {
                return volume.compareTo(dealVolume) <= 0;
            }
        }
    }
}
