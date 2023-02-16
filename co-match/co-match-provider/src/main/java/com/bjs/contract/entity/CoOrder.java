package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author nike
 * @date 2022年11月10日 13:19
 */
@Data
@TableName("co_order")
@EqualsAndHashCode
public class CoOrder {


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;



    /**
     * 交易对
     */
    private String symbol;



    /**
     * 条件委托id
     */
    private Long triggerOrderId;



    /**
     * 用户id
     */
    private Long uid;



    /**
     * 持仓类型(1 全仓，2 仓逐)
     */
    private Integer positionType;



    /**
     * 开平仓方向(open 开仓，close 平仓)
     */
    private String positionSide;



    /**
     * 买卖方向（buy 买入，sell 卖出）
     */
    private OperateSideEnum operateSide;



    /**
     * 0: 市价止盈  1: 限价止盈 2:条件市价止盈 3条件限价止盈 4：市价止损 5限价止损 6 条件市价止盈 7条件限价止损 8强平 9穿仓 10市价开仓 11限价开仓
     */
    private Integer type;



    /**
     * 杠杆倍数
     */
    private Integer leverageLevel;



    /**
     * 下单价格
     */
    private BigDecimal price;



    /**
     * 下单数量 base
     */
    private BigDecimal volumeBase;



    /**
     * 下单数额 quote
     */
    private BigDecimal volumeQuote;



    /**
     * 订单状态（订单状态（订单状态：0 init，1 new，2 filled，3 part_filled，4 canceled，5 pending_cancel
     */
    private Integer status;



    /**
     * 订单来源ip
     */
    private String ip;



    /**
     * 订单来源（订单来源：1web，2app，3api，4其它）
     */
    private Integer source;



    /**
     * 成交数量
     */
    private BigDecimal dealBase;



    /**
     * 成交均价
     */
    private BigDecimal avgPrice;



    /**
     * 成交数额
     */
    private BigDecimal dealQuote;



    /**
     * 创建时间
     */
    private Date ctime;


    /**
     * 订单类型 1 市价 2 现价
     */
    private Integer matchType;
}
