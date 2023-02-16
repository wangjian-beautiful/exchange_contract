package com.bijinsuo.common.utils;

import cn.hutool.core.util.NumberUtil;
import com.bijinsuo.common.constants.PrecisionConstant;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import com.bijinsuo.common.utils.enums.OperateTypeEnum;
import com.bijinsuo.common.utils.enums.PositionSideEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 持仓工具类，此工具类是服务于持仓业务，如果其他业务需要此工具类的服务，可以使用，但不可以修改。
 *
 * @author Watson
 */
public class PositionUtil {


    public static PositionSideEnum getSide(String operateType, String operateSide) {
        return getSide(OperateTypeEnum.valueOf(operateType), OperateSideEnum.valueOf(operateSide));
    }


    public static PositionSideEnum getSide(OperateTypeEnum operateTypeEnum, OperateSideEnum operateSideEnum) {
        if (operateTypeEnum == OperateTypeEnum.OPEN) {
            if (operateSideEnum == OperateSideEnum.BUY) {
                return PositionSideEnum.BUY;
            } else {
                return PositionSideEnum.SELL;
            }
        } else {
            if (operateSideEnum == OperateSideEnum.BUY) {
                return PositionSideEnum.SELL;
            } else {
                return PositionSideEnum.BUY;
            }
        }
    }

    /**
     * 计算名义价值
     * 1、等于撮合搓出来的成交额，直接使用撮合成交额相加
     * 2、或者等于撮合搓来的 数量*价格
     * ps:这里使用第一种方式,因为使用第二种方式 还需要同步撮合那边的精度问题
     *
     * @param previousNominalValue    之前的持仓价值 这个值在初始化持仓的是为零
     * @param accumulatedNominalValue 需要累积的名义价值，有可能是负数，平仓的时候传负数进来
     * @return
     */
    public static BigDecimal calculateNominalValue(BigDecimal previousNominalValue, BigDecimal accumulatedNominalValue) {
        return NumberUtil.add(previousNominalValue, accumulatedNominalValue);
    }

    /**
     * 计算保证金
     * 保证金计算方式 名义价值/杠杆倍数
     *
     * @return
     */
    public static BigDecimal calculateMargin(BigDecimal nominalValue, Integer leverageLevel) {
        return calculateMargin(nominalValue, leverageLevel, PrecisionConstant.ACCOUNT_PRECISION);
    }

    private static BigDecimal calculateMargin(BigDecimal nominalValue, Integer leverageLevel, Integer precision) {
        return NumberUtil.div(nominalValue, leverageLevel, precision, RoundingMode.UP);
    }

    /**
     * 计算维持保证金
     * 维持保证金=名义价值 * 维持保证金比率 - 维持保证金速算额
     *
     * @return
     */
    public static BigDecimal calculateMaintenanceMargin(BigDecimal nominalValue, String maintenanceMarginRate
            , String maintenanceAmount, Integer precision) {
        return calculateMaintenanceMargin(nominalValue, new BigDecimal(maintenanceMarginRate)
                , new BigDecimal(maintenanceAmount), precision);
    }

    public static BigDecimal calculateMaintenanceMargin(BigDecimal nominalValue, BigDecimal maintenanceMarginRate
            , BigDecimal maintenanceAmount, Integer precision) {
        BigDecimal temp = NumberUtil.mul(nominalValue, maintenanceMarginRate).setScale(precision, RoundingMode.UP);
        return NumberUtil.sub(temp, maintenanceAmount);
    }


    /**
     * 计算爆仓手续费
     * max(合约价值*手续费率,最小taker手续费)
     *
     * @param nominalValue      合约名义价值
     * @param closeTakerFeeRate 平仓taker 手续费率
     * @param minTakerFee       最小taker手续费
     * @return
     */
    public static BigDecimal calculateCloseFee(BigDecimal nominalValue, BigDecimal closeTakerFeeRate
            , BigDecimal minTakerFee, Integer precision) {
        BigDecimal temp = NumberUtil.mul(nominalValue, closeTakerFeeRate).setScale(precision, RoundingMode.UP);
        return temp.compareTo(minTakerFee) > 0 ? temp : minTakerFee;
    }

    /**
     * @param symbol       交易对
     * @param uid          用户id
     * @param positionSide 持仓方向
     * @return
     */
    public static String getPositionField(String symbol, Long uid, String positionSide) {
        return symbol + uid + positionSide;
    }

    public static String getPositionsKey(String prefix, Long uid) {
        return prefix + uid;
    }

    public static String getPositionsField(String symbol, String positionSide) {
        return symbol + positionSide;
    }


    public static String getPositionListKey(String prefix, Long uid) {
        return prefix + uid;
    }

    public static String getPositionDataKey(String prefix, Long uid, String symbol, String positionSide) {
        return prefix + uid + symbol + positionSide;
    }
}
