package com.bjs.contract.utils;

import com.bijinsuo.common.constants.PrecisionConstant;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.entity.RiskDataDTO;
import com.bijinsuo.common.utils.enums.OperateTypeEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.action.MaintenanceMarginRateAction;
import com.bjs.contract.entity.CoPositionOrder;
import com.bjs.contract.entity.CoTriggerOrder;
import com.bjs.contract.service.CoPositionOrderService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static com.bijinsuo.common.utils.enums.CommonEnum.*;

/**
 * @author Winter
 */
public class OrderValidator {
    private final ContractConfigDTO symbolConfig;

    public OrderValidator(ContractConfigDTO symbolConfig) {
        this.symbolConfig = symbolConfig;
        if (Objects.isNull(symbolConfig)) {
            throw new BizException(SYMBOL_NOT_EXISTS);
        }
        if (symbolConfig.getStatus() == 0) {
            throw new BizException(SYMBOL_IS_NOT_ACCESSIBLE);
        }
    }

    public void validateTriggerOrder(CoTriggerOrder order) {
        //校验价格精度
        validateTriggerPrice(order);

        var volumeQuote = order.getVolumeQuote();
        if (!isMarket(order.getPrice())) {
            if (order.getVolumeBase() == null || order.getVolumeBase().compareTo(BigDecimal.ZERO) < 0) {
                throw new BizException(VOLUME_OUT_OF_RANGE)
                    .withArgs(symbolConfig.getMinOpenBase(), symbolConfig.getMaxOpenBase());
            }
            volumeQuote = order.getPrice().multiply(order.getVolumeBase())
                .setScale(symbolConfig.getQuotePrecision(), RoundingMode.DOWN);
        }
        //金额精度
        validateOpenOrderAmount(order.getPrice(), order.getVolumeBase(), volumeQuote);
    }

    private boolean isMarket(BigDecimal price) {
        return price == null || price.compareTo(BigDecimal.ZERO) == 0;
    }

    private void validateTriggerPrice(CoTriggerOrder order) {
        // 下单价格精度判断
        validPricePrecision(order.getTriggerPrice(), symbolConfig.getPricePrecision());
        if (!isMarket(order.getPrice()))
            validPricePrecision(order.getPrice(), symbolConfig.getPricePrecision());
    }

    private void validPricePrecision(BigDecimal price, Integer pricePrecision) {
        if (pricePrecision < price.precision()) {
            throw new BizException(ORDER_PRICE_PRECISION_ERROR);
        }
    }

    public void validateCloseOrderAmount(BigDecimal closePrice, BigDecimal baseAmount){
        //平仓金额判断
        BigDecimal minOpenBase = symbolConfig.getMinOpenBase(); // 最小开仓数量限制（交易币种）
        BigDecimal maxOpenBase = symbolConfig.getMaxOpenBase(); // 最大开仓金额限制（交易币种）

        // 下单价格精度判断
        validPricePrecision(closePrice, symbolConfig.getPricePrecision());

        if (baseAmount.compareTo(minOpenBase) < 0 || baseAmount.compareTo(maxOpenBase) > 0) {
            throw new BizException(VOLUME_OUT_OF_RANGE).withArgs(minOpenBase, maxOpenBase);
        }
        // 开仓数量精度判断
        if (symbolConfig.getBasePrecision() < baseAmount.precision()) {
            throw new BizException(ORDER_VOLUME_PRECISION_ERROR);
        }
    }

    public void validateOpenOrderAmount(BigDecimal openPrice, BigDecimal baseAmount, BigDecimal quoteAmount) {
        //开仓金额判断
        BigDecimal minOpenBase = symbolConfig.getMinOpenBase(); // 最小开仓数量限制（交易币种）
        BigDecimal maxOpenBase = symbolConfig.getMaxOpenBase(); // 最大开仓金额限制（交易币种）
        BigDecimal minOpenQuote = symbolConfig.getMinOpenQuote(); // 最小开仓金额限制（定价币种U）
        BigDecimal maxOpenQuote = symbolConfig.getMaxOpenQuote(); // 最大开仓金额限制（定价币种U）

        if (quoteAmount == null || quoteAmount.compareTo(minOpenQuote) < 0 || quoteAmount.compareTo(maxOpenQuote) > 0) {
            throw new BizException(AMOUNT_OUT_OF_RANGE).withArgs(minOpenQuote, maxOpenQuote);
        }
        //市价不校验base和price
        if (isMarket(openPrice)) {
            return;
        }
        // 下单价格精度判断
        validPricePrecision(openPrice, symbolConfig.getPricePrecision());

        if (baseAmount.compareTo(minOpenBase) < 0 || baseAmount.compareTo(maxOpenBase) > 0) {
            throw new BizException(VOLUME_OUT_OF_RANGE).withArgs(minOpenBase, maxOpenBase);
        }
        // 开仓数量精度判断
        if (symbolConfig.getBasePrecision() < baseAmount.precision()) {
            throw new BizException(ORDER_VOLUME_PRECISION_ERROR);
        }
    }

    public void validatePrice(CoOrderDTO order) {
        if (isMarket(order.getPrice())) {
            return;
        }
        Double redisPrice = RedisUtil.instance().hmget(RedisCacheKey.LATEST_PRICE_KEY, order.getSymbol());
        if (redisPrice == null) {
            throw new BizException(NEW_PRICE_FAIL);
        }
        BigDecimal newPrice = new BigDecimal(redisPrice.toString());
        BigDecimal openPrice = order.getPrice();
        BigDecimal limitRate = symbolConfig.getPriceLimitRate();
        // 下单价格范围判断
        if (!this.validateOpenPrice(openPrice, newPrice, limitRate)) {
            BigDecimal rate100 = limitRate.multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
            throw new BizException(ORDER_PRICE_ERROR).withArgs(rate100);
        }
        // 下单价格精度判断
        validPricePrecision(openPrice, symbolConfig.getPricePrecision());
    }

    private boolean validateOpenPrice(BigDecimal openPrice, BigDecimal newPrice, BigDecimal limitRate) {
        if (openPrice.compareTo(BigDecimal.ZERO) <= 0) { // 开仓价必须大于0
            return false;
        }
        if (newPrice.compareTo(BigDecimal.ZERO) <= 0) { // 指数价格价必须大于0
            return false;
        }
        // 限价幅度必须大于0小于1
        if (limitRate.compareTo(BigDecimal.ZERO) <= 0 || limitRate.compareTo(BigDecimal.ONE) > 0) {
            return false;
        }
        // 最小开仓价格
        BigDecimal minOpenPrice = newPrice.multiply(BigDecimal.ONE.subtract(limitRate));
        // 最大开仓价格
        BigDecimal maxOpenPrice = newPrice.multiply(BigDecimal.ONE.add(limitRate));
        if (openPrice.compareTo(minOpenPrice) < 0) { // 开仓价必须大于最小限价,可以等于
            return false;
        }
        if (openPrice.compareTo(maxOpenPrice) > 0) { // 开仓价必须小于最大限价，可以等于
            return false;
        }

        return true;
    }

//    public void validateMargin(CoOrderDTO order) {
//        PositionCacheDTO positionCacheDTO = getUserSymbolMargin(order);
//        if (positionCacheDTO != null && positionCacheDTO.getMarginRate().compareTo(BigDecimal.valueOf(150)) < 0) {
//            throw new BizException(TOO_LOW_MARGIN);
//        }
//    }

//    private PositionCacheDTO getUserSymbolMargin(CoOrderDTO order) {
//        final var positionTypes = RedisUtil.instance().getSet(PositionUtil.getPositionListKey(RedisCacheKey.POSITION_SET_PREFIX, order.getUid()));
//        PositionCacheDTO positionCacheDTO = null;
//        for (Object type : positionTypes) {
//            if (type.toString().equalsIgnoreCase(order.getSymbol())) {
//                String dataKey = PositionUtil.getPositionDataKey(RedisCacheKey.POSITION_DATA_PREFIX,
//                        order.getUid(), order.getSymbol(), OperateSideEnum.getOpposite(order.getOperateSide().toUpperCase()).name());
//                BigDecimal positionMargin = new BigDecimal(RedisUtil.instance().hmget(dataKey, PositionDataCacheFieldConstant.MARGIN).toString());
//                BigDecimal marginRate = new BigDecimal(RedisUtil.instance().hmget(dataKey, PositionDataCacheFieldConstant.MARGIN_RATE).toString());
//                positionCacheDTO = new PositionCacheDTO();
//                positionCacheDTO.setMargin(positionMargin);
//                positionCacheDTO.setMarginRate(marginRate);
//            }
//        }
//        return positionCacheDTO;
//    }

    public void validatePositionForClose(CoOrderDTO order, CoPositionOrder position) {
        if (position.getDealBase().subtract(position.getFrozenBase()).compareTo(order.getVolumeBase()) < 0) {
            throw new BizException(NOT_ENOUGH_POSITION);
        }
    }

    public void validateOrderConfig(CoOrderDTO order) {
        initBaseQuote(order, order.getPrice());
        validatePrice(order);
        validateOpenOrderAmount(order.getPrice(), order.getVolumeBase(), order.getVolumeQuote());
    }

    private void initBaseQuote(CoOrderDTO order, BigDecimal openPrice) {
        if (!isMarket(order.getPrice())) {
            order.setVolumeQuote(order.getVolumeBase().multiply(openPrice).setScale(symbolConfig.getQuotePrecision()));
        }
        initOrderConfig(order);
    }

    private void initOrderConfig(CoOrderDTO order) {
        order.setOpenMakerFeeRate(symbolConfig.getOpenMakerFeeRate());
        order.setOpenTakerFeeRate(symbolConfig.getOpenTakerFeeRate());
        order.setCloseMakerFeeRate(symbolConfig.getCloseMakerFeeRate());
        order.setCloseTakerFeeRate(symbolConfig.getCloseTakerFeeRate());
        order.setMinMakerFee(symbolConfig.getMinMakerFee());
        order.setMinTakerFee(symbolConfig.getMinTakerFee());
    }

    public BigDecimal getMargin(CoOrderDTO order) {
        return order.getVolumeQuote().divide(BigDecimal.valueOf(order.getLeverageLevel())).setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.UP);
    }

    public void validatePositionRisk(CoOrderDTO order, BigDecimal balance, BigDecimal totalAmount, MaintenanceMarginRateAction maintenanceMarginRateAction, CoPositionOrderService coPositionOrderService) {
        //合约价值
        final var amount = order.getVolumeQuote();
        // 杠杆倍数判断
        final var lever = order.getLeverageLevel();
        // 保证金
        final var margin = amount.divide(BigDecimal.valueOf(lever), PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.UP);
        // 手续费判断
        final var fee = getFee(order);
        // 可用金额判断
        if (balance.compareTo(margin.add(fee)) < 0) {
            throw new BizException(NOT_ENOUGH_BALANCE);
        }
        //保证金率的判断 保证金率 = （可用 + 浮动盈亏 + 保证金） / （维持保证金 + 爆仓手续费）
        if (OperateTypeEnum.OPEN.toString().equalsIgnoreCase(order.getOperateType())) {
            // 保证金率
            // 分子 可用+浮动盈亏+保证金-本次手续费 -> 用户总金额+浮动盈亏-本次手续费
            BigDecimal numerator = getUserEquity(order.getUid(), totalAmount, fee);
            // 分母 维持保证金+手续费
            BigDecimal denominator = getTotalMarginRatioMolecule(order, amount, maintenanceMarginRateAction, coPositionOrderService);

            BigDecimal marginRate = numerator.divide(denominator, PrecisionConstant.MARGIN_RATE_PRECISION, RoundingMode.DOWN);
            Integer riskAlarmWeak = symbolConfig.getRiskAlarmWeak();
            if (marginRate.compareTo(BigDecimal.valueOf(riskAlarmWeak)) <= 0) {
                throw new BizException(LOW_MAINTENANCE_MARGIN_RATIO);
            }
        }
    }

    //用户权益
    private BigDecimal getUserEquity (Long uid, BigDecimal totalAmount, BigDecimal fee) {
        final var profit = getUserPnl(uid);
        return totalAmount.add(profit).subtract(fee);
    }

    //所有持仓维持保证金+爆仓手续费 + 开仓维持保证金+爆仓手续费
    private BigDecimal getTotalMarginRatioMolecule (CoOrderDTO coOrder, BigDecimal amount, MaintenanceMarginRateAction maintenanceMarginRateAction, CoPositionOrderService coPositionOrderService) {
        // 根据汇总的合约价值计算维持保证金和手续费
        RiskDataDTO riskDataDTO = coPositionOrderService.getRiskData(coOrder.getUid());
        RiskDataDTO riskDataBySymbol = coPositionOrderService.getRiskDataBySymbol(coOrder.getUid(), coOrder.getSymbol());

        BigDecimal symbolNominalValue = riskDataBySymbol.getNominalValue();
        symbolNominalValue = symbolNominalValue.add(amount);

        final var mmr = maintenanceMarginRateAction.getByNominalValue(coOrder.getSymbol(), symbolNominalValue);
        // 最大杠杆判断
        if (mmr == null || coOrder.getLeverageLevel() < 0 || coOrder.getLeverageLevel() > mmr.getMaxLeverage()) {
            throw new BizException(ORDER_LEVER_EXCEED_THE_LIMIT_ERROR);
        }
        BigDecimal orderMaintenanceMargin = mmr.getMaintenanceMarginRate().multiply(amount).subtract(mmr.getMaintenanceAmount());
        BigDecimal orderCloseFee = coOrder.getCloseTakerFeeRate().multiply(amount);

        BigDecimal marginRatioMolecule = riskDataDTO.getMarginRatioMolecule().add(orderMaintenanceMargin).add(orderCloseFee);

        return marginRatioMolecule;
    }

    private BigDecimal getUserPnl(long uid) {
        Double pnl = RedisUtil.instance().zScore(RedisCacheKey.POSITION_SORT_PNL_KEY, uid);
        return pnl == null ? BigDecimal.ZERO : BigDecimal.valueOf(pnl);
    }

    public BigDecimal getFee(CoOrderDTO order) {
        BigDecimal feeRate;
        if (OperateTypeEnum.OPEN.toString().equalsIgnoreCase(order.getOperateType())) {
            feeRate = symbolConfig.getOpenMakerFeeRate();
            if (feeRate.compareTo(symbolConfig.getOpenTakerFeeRate()) < 0) {
                feeRate = symbolConfig.getOpenTakerFeeRate();
            }
        } else {
            feeRate = symbolConfig.getCloseMakerFeeRate();
            if (feeRate.compareTo(symbolConfig.getCloseTakerFeeRate()) < 0) {
                feeRate = symbolConfig.getCloseTakerFeeRate();
            }
        }
        return order.getVolumeQuote().multiply(feeRate).setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.UP);
    }
}
