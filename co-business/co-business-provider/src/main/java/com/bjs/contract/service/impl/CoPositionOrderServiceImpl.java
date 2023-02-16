package com.bjs.contract.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.constants.PositionDataCacheFieldConstant;
import com.bijinsuo.common.constants.PrecisionConstant;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.domain.FundingRateSettlePositionDTO;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.PositionUtil;
import com.bijinsuo.common.utils.entity.*;
import com.bijinsuo.common.utils.enums.*;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.AccountSymbolConstant;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.action.ContractConfigAction;
import com.bjs.contract.domain.FeeRateDto;
import com.bjs.contract.domain.PositionDelaySyncCacheDTO;
import com.bjs.contract.domain.UserPositionDetail;
import com.bjs.contract.entity.*;
import com.bjs.contract.mapper.CoPositionOrderMapper;
import com.bjs.contract.proto.contractConfig.ContractConfigBizService;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRateBizService;
import com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRatePO;
import com.bjs.contract.proto.maintenanceMarginRate.NominalValueRequest;
import com.bjs.contract.service.*;
import com.bjs.contract.utils.ContractConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author bjs code generator
 * @date 2022-11-11 17:58:51
 */
@Service
@Slf4j
public class CoPositionOrderServiceImpl extends ServiceImpl<CoPositionOrderMapper, CoPositionOrder> implements CoPositionOrderService {

    @DubboReference
    ContractConfigBizService contractConfigBizService;
    @DubboReference
    MaintenanceMarginRateBizService maintenanceMarginRateBizService;
    @Resource
    private SettlementService settlementService;
    @Resource
    private CoTradeService coTradeService;
    @Resource
    private CoOrderService coOrderService;
    @Resource
    private CoTriggerOrderService coTriggerOrderService;
    @Resource
    AccountAction accountAction;
    @Resource
    RocketMQTemplate rocketMQTemplate;
    @Resource
    ContractConfigAction contractConfigAction;
    @Resource
    private UserLeverageService userLeverageService;

    @Override
    public CoPositionOrder getByUidAndStatusAndSide(Long uid, String symbol, PositionOrderStatusEnum positionOrderStatusEnum
            , PositionSideEnum positionSide) {
        QueryChainWrapper<CoPositionOrder> queryChainWrapper = super.query();
        return queryChainWrapper
                .eq("uid", uid)
                .eq("symbol", symbol)
                .eq("position_side", positionSide.name())
                .one();
    }


    @Override
    public CoPositionOrder getByUidAndStatusAndSideWithLock(Long uid, String symbol, PositionSideEnum positionSide) {
        QueryChainWrapper<CoPositionOrder> queryChainWrapper = super.query();
        return queryChainWrapper
                .eq("uid", uid)
                .eq("symbol", symbol)
                .eq("position_side", positionSide.name())
                .last(" for update ")
                .one();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPositionDetail initPosition(MatchTradeDetailsDTO matchTradeDetailsDTO, CoOrder coOrder, PositionSideEnum sideEnum, String tradeNumber) {
        ContractConfigPO contractConfigPO = ContractConfigUtil.getContractConfig(coOrder.getSymbol());
        //初始化持仓 数据库
        CoPositionOrder positionOrder = initPositionToDb(matchTradeDetailsDTO, coOrder, sideEnum);
        TransactionSceneEnum sceneEnum = TransactionSceneEnum.OPEN_POSITION;
        //操作trade 需要等position先操作完成才能做这个操作
        FeeRateDto feeRate = getFeeRateByTransactionSceneEnum(matchTradeDetailsDTO, coOrder, contractConfigPO, sceneEnum);
        //根据订单价格计算成交额
        BigDecimal turnover = getDeltaNominalValue(coOrder, matchTradeDetailsDTO);
        BigDecimal fee = NumberUtil.max(turnover.multiply(feeRate.getCloseFeeRate()).setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.DOWN), feeRate.getMinFee());
        buildTradeAndInsert(coOrder, matchTradeDetailsDTO, positionOrder, tradeNumber, fee);
        //修改委托单 主要是修改数量,金额，均价，状态
        handOrder(coOrder, matchTradeDetailsDTO, contractConfigPO);
        //修改account 把委托的冻结 变成持仓保证金冻结
        boolean result = accountAction.operateAccount(coOrder.getUid(),
                positionOrder.getMargin(),
                contractConfigPO.getQuote(),
                sceneEnum.getValue(),
                "co_order",
                coOrder.getId(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                fee);

        if (!result) {
            throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
        }
        refundFee(coOrder, matchTradeDetailsDTO, fee);

        initPositionCacheAndSendMq(positionOrder);
        UserPositionDetail userPositionDetail = new UserPositionDetail(true, positionOrder);
        return userPositionDetail;
    }

    private void refundFee(CoOrder coOrder, MatchTradeDetailsDTO dto, BigDecimal currentFee) {
        if (OperateTypeEnum.OPEN == OperateTypeEnum.valueOf(coOrder.getOperateType().toUpperCase()) && orderFinished(coOrder, dto)) {
            BigDecimal totalFee = getTotalFee(coOrder.getId());
            if (coOrder.getLockPotentialFee().compareTo(totalFee) > 0) {
                boolean result = accountAction.operateAccount(coOrder.getUid(),
                        coOrder.getLockPotentialFee().subtract(totalFee),
                        AccountSymbolConstant.USDT,
                        TransactionSceneEnum.REFUND_FEE.getValue(),
                        "co_order",
                        coOrder.getId(),
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO);
                if (!result) throw new BizException(CommonEnum.REFUND_FREEZE_FEE_FAIL);
            }
        }
    }

    private BigDecimal getTotalFee(Long orderId) {
        List<CoTrade> coTradeList = coTradeService.list(Wrappers.lambdaQuery(CoTrade.class).eq(CoTrade::getOrderId, orderId));
        BigDecimal total = coTradeList.stream().map(CoTrade::getFee).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }

    private void updateTriggerOrder(CoOrder coOrder) {
        List<CoTriggerOrder> list = coTriggerOrderService.list(Wrappers.lambdaQuery(CoTriggerOrder.class)
                .eq(CoTriggerOrder::getMasterId, coOrder.getId())
                .eq(CoTriggerOrder::getStatus, TriggerStatusEnum.NOT_ACTIVE));
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(coTriggerOrder -> coTriggerOrder.setStatus(TriggerStatusEnum.ACTIVE.getCode()));
            coTriggerOrderService.updateBatchById(list);
        }
    }

    /**
     * 更新持仓redis缓存
     * 直接使用数据库的数据覆盖redis数据
     * 合并仓位也可以使用这个方法
     *
     * @param coPositionOrder
     */
    @Override
    public void initPositionCacheAndSendMq(CoPositionOrder coPositionOrder) {
        sendDelaySyncCacheMsg(coPositionOrder);
        //分为两个数据结构
        //1、 uid > list(symbol+side)
        //2、map   uid+symbol+side>每个属性>每个属性的值
        //第一个数据结构
        RedisUtil.instance().addSet(this.getCacheSetKey(coPositionOrder),
                this.getCacheSetValue(coPositionOrder));
        //第二个数据结构
        updatePositionDataCache(coPositionOrder);
        //初始化 浮动盈亏的排序,这里初始化这个 主要是保证金率计算分发那边可以从这里获取到所有有持仓的用户，用于分发保证金率计算
        RedisUtil.instance().sortZadd(RedisCacheKey.POSITION_SORT_PNL_KEY, 0, coPositionOrder.getUid());
    }

    private void updatePositionDataCache(CoPositionOrder coPositionOrder) {
        String dataKey = this.getCacheDataKey(coPositionOrder);
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.ID, coPositionOrder.getId());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.SYMBOL, coPositionOrder.getSymbol());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.UID, coPositionOrder.getUid());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.POSITION_SIDE, coPositionOrder.getPositionSide());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.MARGIN, coPositionOrder.getMargin());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.CLOSE_FEE, coPositionOrder.getCloseFee());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.MARGIN_RATIO_MOLECULE, coPositionOrder.getMarginRatioMolecule());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.AVG_PRICE, coPositionOrder.getAvgPrice());
        RedisUtil.instance().hmput(dataKey, PositionDataCacheFieldConstant.DEAL_BASE, coPositionOrder.getDealBase());
    }


    private String getCacheSetKey(CoPositionOrder coPositionOrder) {
        return PositionUtil.getPositionListKey(RedisCacheKey.POSITION_SET_PREFIX, coPositionOrder.getUid());
    }

    private String getCacheDataKey(CoPositionOrder coPositionOrder) {
        return PositionUtil.getPositionDataKey(RedisCacheKey.POSITION_DATA_PREFIX,
                coPositionOrder.getUid(), coPositionOrder.getSymbol(), coPositionOrder.getPositionSide());
    }

    private String getCacheSetValue(CoPositionOrder coPositionOrder) {
        return coPositionOrder.getSymbol() + coPositionOrder.getPositionSide();
    }

    private CoTrade buildTradeAndInsert(CoOrder order, MatchTradeDetailsDTO dto, CoPositionOrder coPositionOrder, String tradeNumber, BigDecimal fee) {
        Date now = new Date();
        CoTrade coTrade = new CoTrade();
        coTrade.setSymbol(order.getSymbol());
        coTrade.setTradeNo(tradeNumber);
        coTrade.setOrderId(order.getId());
        coTrade.setPositionId(coPositionOrder.getId());
        coTrade.setUid(order.getUid());
        //方向
        coTrade.setOperateType(order.getOperateType());
        coTrade.setOperateSide(order.getOperateSide());
        coTrade.setTrendSide(dto.getTrendSide().toString());
        //金额
        coTrade.setVolumeBase(dto.getAmount());
        coTrade.setVolumeQuote(dto.getTurnover());
        coTrade.setPrice(dto.getPrice());
        coTrade.setFee(fee);
        coTrade.setCtime(now);
        coTrade.setMtime(now);
        coTradeService.save(coTrade);
        return coTrade;
    }

    private void handOrder(CoOrder coOrder, MatchTradeDetailsDTO detailsDTO, ContractConfigPO contractConfigPO) {
        coOrder.setDealBase(coOrder.getDealBase().add(detailsDTO.getAmount()));
        coOrder.setDealQuote(coOrder.getDealQuote().add(detailsDTO.getTurnover()));
        coOrder.setAvgPrice(coOrder.getDealQuote().divide(coOrder.getDealBase(), RoundingMode.HALF_UP).setScale(contractConfigPO.getPricePrecision().getValue()));
        coOrder.setStatus(orderFinished(coOrder, detailsDTO) ? OrderStatus.FILLED.value : OrderStatus.PART_FILLED.value);
        coOrder.setMtime(new Date());
        coOrderService.updateById(coOrder);
    }

    private boolean orderFinished(CoOrder coOrder, MatchTradeDetailsDTO detailsDTO) {
        boolean isComplete = detailsDTO.getSellFinish();
        if (coOrder.getOperateSide().equalsIgnoreCase(OperateSideEnum.BUY.toString())) {
            isComplete = detailsDTO.getBuyFinish();
        }
        return isComplete;
    }

    //更新redis仓位 与 利润
    @Override
    public void updatePositionCacheAndSendMq(CoPositionOrder coPositionOrder) {
        sendDelaySyncCacheMsg(coPositionOrder);
        updatePositionCache(coPositionOrder);
    }

    /**
     * 发送延时消息
     */
    private void sendDelaySyncCacheMsg(CoPositionOrder coPositionOrder) {
        String msg = JSON.toJSONString(coPositionOrder);
        //1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h  分别对应等级1-18
        //所以 5 对应 1分钟
        int delayLevel = 5;
        rocketMQTemplate.syncSend(TopicConstant.POSITION_DELAY_SYNC_CACHE, MessageBuilder.withPayload(msg).build()
                , rocketMQTemplate.getProducer().getSendMsgTimeout(), delayLevel);
    }

    /**
     * 这个逻辑多个业务场景 会使用到
     * 如果修改判断保证金为0去清除缓存这个逻辑，会涉及多个地方
     */
    private void updatePositionCache(CoPositionOrder coPositionOrder) {
        //持仓为0 ，删除redis
        if (coPositionOrder.getMargin().compareTo(BigDecimal.ZERO) == 0) {
            deletePositionCache(coPositionOrder);
            return;
        }
        updatePositionDataCache(coPositionOrder);
    }

    @Override
    public void deletePositionCache(CoPositionOrder coPositionOrder) {
        String setKey = this.getCacheSetKey(coPositionOrder);
        RedisUtil.instance().removeSet(setKey,
                this.getCacheSetValue(coPositionOrder));
        //删除具体持仓数据
        RedisUtil.instance().remove(this.getCacheDataKey(coPositionOrder));
        //这里不能直接删除 浮动盈亏的，要判断set size 为 0时才证明没有任何持仓了，才删除这个
        if (RedisUtil.instance().getSetSize(setKey) == 0)
            RedisUtil.instance().sortZremove(RedisCacheKey.POSITION_SORT_PNL_KEY, coPositionOrder.getUid());

        rocketMQTemplate.syncSend(TopicConstant.POSITION_CLEAR, JSON.toJSONString(coPositionOrder.getUid()));
    }

    private CoPositionOrder initPositionToDb(MatchTradeDetailsDTO detailsDTO, CoOrder coOrder, PositionSideEnum sideEnum) {
        UserLeverage userLeverage = userLeverageService.findByUidAndSymbolWithLockAndNotExistInit(coOrder.getUid(), coOrder.getSymbol());
        //初始化分为数据库初始化和Redis初始化
        CoPositionOrder coPositionOrder = new CoPositionOrder();
        coPositionOrder.setSymbol(coOrder.getSymbol());
        coPositionOrder.setUid(coOrder.getUid());
        coPositionOrder.setPositionSide(sideEnum.name());
        coPositionOrder.setLeverageLevel(userLeverage.getLeverage());
        coPositionOrder.setStatus(PositionOrderStatusEnum.position.getValue());
        coPositionOrder.setFrozenBase(BigDecimal.ZERO);
        coPositionOrder.setFrozenQuote(BigDecimal.ZERO);
        //这里 ip和source 只能使用开仓的订单来源数据，因为每次加减仓可能会改变的，这里跟着一直改貌似没有意义。
        coPositionOrder.setIp(coOrder.getIp());
        coPositionOrder.setSource(coOrder.getSource());
        //金额相关
        this.initPositionForAmountRelated(coPositionOrder, coOrder, detailsDTO);
        //这里save之后，实体应该就是注入自增的id
        super.save(coPositionOrder);
        return coPositionOrder;
    }

    /**
     * 初始化持仓 计算金额相关
     */
    private void initPositionForAmountRelated(CoPositionOrder target, CoOrder coOrder, MatchTradeDetailsDTO detailsDTO) {
        ContractConfigPO contractConfigPO = ContractConfigUtil.getContractConfig(coOrder.getSymbol());
        Integer quotePrecision = contractConfigPO.getQuotePrecision().getValue();
        //保证金
        BigDecimal turnover = detailsDTO.getTurnover();
        if (coOrder.getMatchType() == OrderMatchTypeEnum.LIMIT.value) {
            turnover = coOrder.getPrice().multiply(detailsDTO.getAmount());
        }

        target.setMargin(PositionUtil.calculateMargin(turnover
                , target.getLeverageLevel()));

        //合约价值
        BigDecimal nominalValue = PositionUtil.calculateNominalValue(BigDecimal.ZERO, detailsDTO.getTurnover());
        target.setNominalValue(nominalValue);

        this.calculateAndSetPositionProperties(target, coOrder, quotePrecision);
        //三个金额
        target.setDealBase(detailsDTO.getAmount());
        target.setAvgPrice(detailsDTO.getPrice());
        target.setDealQuote(detailsDTO.getTurnover());

    }

    public void calculateAndSetPositionProperties(CoPositionOrder target, CoOrder coOrder, Integer quotePrecision) {
        //维持保证金
        BigDecimal maintenanceMargin = calculateMaintenanceMargin(coOrder.getSymbol(), target.getNominalValue());
        target.setMaintenanceMargin(maintenanceMargin);
        //平仓手续费（爆仓手续费）
        BigDecimal closeFee = PositionUtil.calculateCloseFee(target.getNominalValue(), coOrder.getCloseTakerFeeRate()
                , coOrder.getMinTakerFee(), PrecisionConstant.ACCOUNT_PRECISION);
        target.setCloseFee(closeFee);
        //维持保证金+平仓手续费（保证金率直接使用这个值）
        target.setMarginRatioMolecule(NumberUtil.add(maintenanceMargin, closeFee));
    }


    private BigDecimal calculateMaintenanceMargin(String symbol, BigDecimal nominalValue) {
        MaintenanceMarginRatePO maintenanceMarginRatePO = maintenanceMarginRateBizService.getByNominalValue(
                NominalValueRequest.newBuilder()
                        .setNominalValue(nominalValue.toString())
                        .setSymbol(symbol).build());
        return PositionUtil.calculateMaintenanceMargin(nominalValue, maintenanceMarginRatePO.getMaintenanceMarginRate()
                , maintenanceMarginRatePO.getMaintenanceAmount(), PrecisionConstant.ACCOUNT_PRECISION);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPositionDetail mergePositionForTrade(CoOrder coOrder, CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO, String tradeNumber) {
        UserLeverage userLeverage = userLeverageService.findByUidAndSymbolWithLockAndNotExistInit(coOrder.getUid(), coOrder.getSymbol());
        CoPositionOrder positionOrder = getOne(Wrappers.lambdaQuery(CoPositionOrder.class)
                .eq(CoPositionOrder::getId, coPositionOrder.getId())
                .last(" for update"));
        checkLeverageLevelAndUpdate(userLeverage.getLeverage(), coOrder.getSymbol(), positionOrder);
        ContractConfigPO contractConfigPO = ContractConfigUtil.getContractConfig(coOrder.getSymbol());
        //如果是平仓，增量系数为负数
        BigDecimal factor = getFactor(coOrder.getOperateType());
        //结算-账户操作类型
        TransactionSceneEnum sceneEnum = factor.compareTo(BigDecimal.ZERO) > 0 ? TransactionSceneEnum.SCALE_IN : TransactionSceneEnum.CLOSE_POSITION;

        //根据订单价格计算成交额
        BigDecimal deltaNominalValue = getDeltaNominalValue(coOrder, matchTradeDetailsDTO);
        //保证金计算
        BigDecimal deltaMargin = getDeltaMargin(coOrder, positionOrder, deltaNominalValue);
        //手续费计算
        FeeRateDto feeRate = getFeeRateByTransactionSceneEnum(matchTradeDetailsDTO, coOrder, contractConfigPO, sceneEnum);
        BigDecimal fee = NumberUtil.max(deltaNominalValue.multiply(feeRate.getCloseFeeRate()).setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.DOWN), feeRate.getMinFee());
        //更新数据库
        updatePositionDb(coOrder, positionOrder, matchTradeDetailsDTO, deltaNominalValue, deltaMargin, factor);
        //保存trade
        buildTradeAndInsert(coOrder, matchTradeDetailsDTO, positionOrder, tradeNumber, fee);
        //结算
        settlementService.settlement(coOrder, positionOrder, matchTradeDetailsDTO, deltaMargin, fee, sceneEnum);
        //修改委托单 主要是修改数量,金额，均价，状态
        handOrder(coOrder, matchTradeDetailsDTO, contractConfigPO);
        refundFee(coOrder, matchTradeDetailsDTO, fee);
        updatePositionCacheAndSendMq(positionOrder);
        UserPositionDetail userPositionDetail = new UserPositionDetail(false, positionOrder);
        return userPositionDetail;
    }

    private BigDecimal getDeltaNominalValue(CoOrder coOrder, MatchTradeDetailsDTO matchTradeDetailsDTO) {
        BigDecimal turnover = matchTradeDetailsDTO.getTurnover();
        if (OperateTypeEnum.OPEN == OperateTypeEnum.valueOf(coOrder.getOperateType().toUpperCase())) {
            if (coOrder.getMatchType() == OrderMatchTypeEnum.LIMIT.value) {
                turnover = coOrder.getPrice().multiply(matchTradeDetailsDTO.getAmount());
            }
        }
        return turnover;
    }

    private BigDecimal getDeltaMargin(CoOrder coOrder, CoPositionOrder positionOrder, BigDecimal deltaNominalValue) {
        ContractConfigPO contractConfigPO = ContractConfigUtil.getContractConfig(coOrder.getSymbol());
        if (OperateTypeEnum.OPEN == OperateTypeEnum.valueOf(coOrder.getOperateType().toUpperCase())) {
            return PositionUtil.calculateMargin(deltaNominalValue
                    , positionOrder.getLeverageLevel());
        } else {
            BigDecimal dbNominalValue = positionOrder.getNominalValue();
            BigDecimal closePercent = deltaNominalValue.divide(dbNominalValue, contractConfigPO.getQuotePrecision().getValue(), RoundingMode.HALF_UP);
            return positionOrder.getMargin().multiply(closePercent);
        }
    }

    private void checkLeverageLevelAndUpdate(Integer newLeverage, String symbol, CoPositionOrder coPositionOrder) {
        if (newLeverage == coPositionOrder.getLeverageLevel()) {
            return;
        }
        updatePositionAndAccount(coPositionOrder, newLeverage, symbol);
    }

    private void updatePositionDb(CoOrder coOrder, CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO, BigDecimal deltaNominalValue, BigDecimal deltaMargin, BigDecimal factor) {
        ContractConfigPO contractConfigPO = ContractConfigUtil.getContractConfig(coOrder.getSymbol());
        deltaNominalValue = deltaNominalValue.multiply(factor);
        deltaMargin = deltaMargin.multiply(factor);
        BigDecimal deltaAmount = matchTradeDetailsDTO.getAmount().multiply(factor);
        //合并采用数据库数据合并，然后合并之后的数据覆盖Redis数据
        //如果全平仓，直接删除数据
        if (OperateTypeEnum.CLOSE == OperateTypeEnum.valueOf(coOrder.getOperateType().toUpperCase()) && coPositionOrder.getMargin().compareTo(deltaMargin.abs()) <= 0) {
            removeById(coPositionOrder.getId());
            coPositionOrder.setMargin(BigDecimal.ZERO);
            return;
        } else {
            coPositionOrder.setMargin(coPositionOrder.getMargin().add(deltaMargin));
            coPositionOrder.setNominalValue(PositionUtil.calculateNominalValue(coPositionOrder.getNominalValue(), deltaNominalValue));
        }
        coPositionOrder.setDealBase(coPositionOrder.getDealBase().add(deltaAmount));
        coPositionOrder.setDealQuote(coPositionOrder.getNominalValue());
        coPositionOrder.setAvgPrice(coPositionOrder.getDealQuote().divide(coPositionOrder.getDealBase(), contractConfigPO.getPricePrecision().getValue(), RoundingMode.HALF_UP));
        coPositionOrder.setMtime(new Date());
        this.calculateAndSetPositionProperties(coPositionOrder, coOrder, contractConfigPO.getQuotePrecision().getValue());
        if (factor.compareTo(BigDecimal.ZERO) < 0) {
            if (!validFrozenBase(coPositionOrder.getFrozenBase(), deltaAmount)) {
                throw new BizException(CommonEnum.FREEZE_POSITION_FAIL);
            }
            coPositionOrder.setFrozenBase(PositionUtil.calculateNominalValue(coPositionOrder.getFrozenBase(), deltaAmount));
//            coPositionOrder.setFrozenQuote(PositionUtil.calculateNominalValue(coPositionOrder.getFrozenQuote(), deltaNominalValue));
        }

        updateById(coPositionOrder);
    }

    //校验冻结金额
    private boolean validFrozenBase(BigDecimal frozenBase, BigDecimal deltaAmount) {
        return frozenBase.compareTo(deltaAmount.abs()) >= 0;
    }

    private FeeRateDto getFeeRateByTransactionSceneEnum(MatchTradeDetailsDTO detailsDTO, CoOrder coOrder, ContractConfigPO contractConfigPO, TransactionSceneEnum sceneEnum) {
        FeeRateDto feeRateDto = null;
        switch (sceneEnum) {
            case OPEN_POSITION:
            case SCALE_IN:
                feeRateDto = getOpenFeeRate(detailsDTO, coOrder, contractConfigPO);
                break;
            case CLOSE_POSITION:
                feeRateDto = getCloseFeeRate(detailsDTO, coOrder, contractConfigPO);
                break;
            default:
                feeRateDto = new FeeRateDto(BigDecimal.ZERO, BigDecimal.ZERO);
                break;
        }

        return feeRateDto;
    }

    private FeeRateDto getOpenFeeRate(MatchTradeDetailsDTO detailsDTO, CoOrder coOrder, ContractConfigPO contractConfigPO) {
        //主动单方向为buy
        if (detailsDTO.getTrendSide() == OperateSideEnum.valueOf(coOrder.getOperateSide().toUpperCase())) {
            return new FeeRateDto(new BigDecimal(contractConfigPO.getOpenTakerFeeRate()), new BigDecimal(contractConfigPO.getMinTakerFee()));
        } else {
            return new FeeRateDto(new BigDecimal(contractConfigPO.getOpenMakerFeeRate()), new BigDecimal(contractConfigPO.getMinMakerFee()));
        }
    }

    private FeeRateDto getCloseFeeRate(MatchTradeDetailsDTO detailsDTO, CoOrder coOrder, ContractConfigPO contractConfigPO) {
        OperateTypeEnum operateTypeEnum = OperateTypeEnum.valueOf(coOrder.getOperateType());
        //主动单方向为buy
        if (detailsDTO.getTrendSide() == OperateSideEnum.BUY) {
            //taker
            if (coOrder.getId().equals(detailsDTO.getBuyOrderId())) {
                return this.getCloseFeeRate(operateTypeEnum, contractConfigPO, true);
            } else {
                //maker
                return this.getCloseFeeRate(operateTypeEnum, contractConfigPO, false);
            }
        } else {
            //taker
            if (coOrder.getId().equals(detailsDTO.getSellOrderId())) {
                return this.getCloseFeeRate(operateTypeEnum, contractConfigPO, true);
            } else {
                //maker
                return this.getCloseFeeRate(operateTypeEnum, contractConfigPO, false);
            }
        }
    }

    private FeeRateDto getCloseFeeRate(OperateTypeEnum operateTypeEnum, ContractConfigPO contractConfigPO, boolean isTaker) {
        BigDecimal closeFeeRate;
        BigDecimal minFee;
        if (isTaker) {
            closeFeeRate = operateTypeEnum == OperateTypeEnum.OPEN ? new BigDecimal(contractConfigPO.getOpenTakerFeeRate())
                    : new BigDecimal(contractConfigPO.getCloseTakerFeeRate());
            minFee = new BigDecimal(contractConfigPO.getMinTakerFee());
        } else {
            closeFeeRate = operateTypeEnum == OperateTypeEnum.OPEN ? new BigDecimal(contractConfigPO.getOpenMakerFeeRate())
                    : new BigDecimal(contractConfigPO.getCloseMakerFeeRate());
            minFee = new BigDecimal(contractConfigPO.getMinMakerFee());
        }
        return new FeeRateDto(closeFeeRate, minFee);
    }

    private BigDecimal getFactor(String operateType) {
        return OperateTypeEnum.OPEN.toString().equalsIgnoreCase(operateType) ? BigDecimal.ONE : BigDecimal.ONE.negate();
    }

    @Override
    public boolean updatePositionForFundingRateSettle(String symbol, String positionSide
            , List<FundingRateSettlePositionDTO> settlePositionDTOList) {
        return false;
    }

    @Override
    public void settleMarginDb(String symbol, String positionSide, List<FundingRateSettlePositionDTO> settlePositionDTOList) {

    }

    @Override
    public List<CoPositionOrder> findAllInSymbolList(Integer status, List<String> symbolList) {
        QueryChainWrapper<CoPositionOrder> queryChainWrapper = super.query();
        return queryChainWrapper
                //.eq("status", status)  status 废弃了
                .in("symbol", symbolList)
                .list();
    }

    @Override
    public void cancelOrder(CoOrder coOrder) {
        //计算待释放冻结
        BigDecimal frozenBase = coOrder.getVolumeBase().subtract(coOrder.getDealBase());

        CoPositionOrder positionOrder = getByUidAndStatusAndSide(coOrder.getUid(), coOrder.getSymbol()
                , PositionOrderStatusEnum.position, PositionSideEnum.getCloseSide(coOrder.getOperateSide().toUpperCase()));

        //更新用户持仓DB
        if (positionOrder != null) {
            positionOrder.setFrozenBase(positionOrder.getFrozenBase().subtract(frozenBase));
            positionOrder.setMtime(new Date());
            updateById(positionOrder);
        }
    }

    private BigDecimal getFrozenFee(BigDecimal frozenQuote, BigDecimal feeRate) {
        if (frozenQuote.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        return frozenQuote.multiply(feeRate);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean sysCloseUserAllPosition(Long uid, Integer waitTimeSecond) throws InterruptedException {
        QueryChainWrapper<CoPositionOrder> chainWrapper = super.query();
        List<CoPositionOrder> positionOrderList = chainWrapper.eq("uid", uid)
                .eq("status", PositionOrderStatusEnum.position.getValue())
                .list();
        //为所有持仓下市价平仓单
        if (CollectionUtil.isEmpty(positionOrderList)) {
            //没有持仓直接返回true
            return true;
        }
        Map<String, List<CoPositionOrder>> group = groupingBySymbol(positionOrderList);
        for (Map.Entry<String, List<CoPositionOrder>> entry : group.entrySet()) {
            String symbol = entry.getKey();
            List<CoPositionOrder> singleSymbolPositionList = entry.getValue();
            for (CoPositionOrder coPositionOrder : singleSymbolPositionList) {
                //查询contract config
                ContractConfigDTO contractConfigDTO = contractConfigAction.getBySymbol(symbol);
                //构建委托单
                //循环间隔等待 平仓结果 。要么在有限时间拿到结果，要么时间超时
                CoOrderDTO coOrderDTO = coOrderService.initSysClosePositionMarketDtoOrderWithInMatch(coPositionOrder, contractConfigDTO
                        , OrderTypeEnum.SETTLE_ARREARS_MARKET_CLOSE);
                coOrderService.orderCloseForNewTransaction(coOrderDTO, contractConfigDTO);
            }
        }
        //开启等待
        long timeOut = System.currentTimeMillis() + waitTimeSecond * 1000;
        while (System.currentTimeMillis() < timeOut) {
            Long count = super.query()
                    .eq("uid", uid)
                    //.eq("status", PositionOrderStatusEnum.position.getValue())
                    .count();
            if (count == 0) {
                return true;
            }

            Thread.sleep(300);
        }
        return false;
    }


    private Map<String, List<CoPositionOrder>> groupingBySymbol(List<CoPositionOrder> positionOrderList) {
        Map<String, List<CoPositionOrder>> group = new HashedMap();
        for (CoPositionOrder item : positionOrderList) {
            String symbol = item.getSymbol();
            if (group.containsKey(symbol)) {
                group.get(symbol).add(item);
            } else {
                List<CoPositionOrder> init = new ArrayList<>();
                init.add(item);
                group.put(symbol, init);
            }
        }
        return group;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserPositionDetail> handlePositionForTrade(MatchTradeDTO dto) {
        //todo 这个tradeNumber后面在改成其他的，现在图方便使用UUID
        String tradeNumber = UUID.randomUUID().toString();
        MatchTradeDetailsDTO detailsDTO = dto.getDetailsDTO();
        List<UserPositionDetail> list = new ArrayList<>();
        if (detailsDTO.getBuyOrderId() > 0) {
            list.add(handleUserPosition(detailsDTO.getBuyOrderId(), detailsDTO, tradeNumber));
        } else {
            handleRobot(detailsDTO.getBuyOrderId(), dto, OperateSideEnum.BUY, tradeNumber);
        }
        if (detailsDTO.getSellOrderId() > 0) {
            list.add(handleUserPosition(detailsDTO.getSellOrderId(), detailsDTO, tradeNumber));
        } else {
            handleRobot(detailsDTO.getSellOrderId(), dto, OperateSideEnum.SELL, tradeNumber);
        }
        return list;
    }

    //把机器人的委托单从mongo 同步到数据库，后把机器人的order 和trade 写到数据库。不操作账户
    private void handleRobot(Long orderId, MatchTradeDTO dto, OperateSideEnum sideEnum, String tradeNumber) {
        CoOrder order = coOrderService.getOrderFromMongo(orderId, dto, sideEnum);
        coOrderService.save(order);
        CoPositionOrder positionOrder = new CoPositionOrder();
        positionOrder.setId(-1l);
        positionOrder.setPositionSide(order.getOperateSide());
        buildTradeAndInsert(order, dto.getDetailsDTO(), positionOrder, tradeNumber, BigDecimal.ZERO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPositionDetail handleUserPosition(Long orderId, MatchTradeDetailsDTO dto, String tradeNumber) {
        CoOrder coOrder = coOrderService.getById(orderId);
        //判断这个委托单是对哪个方向的持仓进行操作
        PositionSideEnum positionSideEnum = PositionUtil.getSide(coOrder.getOperateType(), coOrder.getOperateSide());

        CoPositionOrder positionOrder = getByUidAndStatusAndSide(coOrder.getUid(), coOrder.getSymbol()
                , PositionOrderStatusEnum.position, positionSideEnum);
        UserPositionDetail userPositionDetail;
        if (positionOrder == null) {
            //代表用户现在还没有持仓，这个方向
            userPositionDetail = initPosition(dto, coOrder, positionSideEnum, tradeNumber);
        } else {
            userPositionDetail = mergePositionForTrade(coOrder, positionOrder, dto, tradeNumber);
        }
        //触发订单止盈止损状态修改
        updateTriggerOrder(coOrder);

//        if (OperateTypeEnum.OPEN == OperateTypeEnum.valueOf(coOrder.getOperateType().toUpperCase()) && orderFinished(coOrder, dto)) {
//            rocketMQTemplate.syncSend(TopicConstant.ORDER_COMPLETE_CLEAR, coOrder.getId().toString());
//        }
        return userPositionDetail;
    }

    /**
     * 爆仓清算
     *
     * @param positionLiquidationDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void liquidation(PositionLiquidationDTO positionLiquidationDTO) {
        //更新持仓
        List<CoPositionOrder> list = list(Wrappers.lambdaQuery(CoPositionOrder.class).eq(CoPositionOrder::getUid, positionLiquidationDTO.getUid()));
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //直接删除持仓
        removeByIds(list);
        //构建交易对手order，结算信息
        coOrderService.createLiquidationOrdersAndSettlement(list, positionLiquidationDTO);

        BigDecimal ventureCapital = BigDecimal.ZERO;
        for (CoPositionOrder coPositionOrder : list) {
            //仓位维持保证金
            ventureCapital = ventureCapital.add(coPositionOrder.getMaintenanceMargin()).setScale(PrecisionConstant.ACCOUNT_PRECISION);
            //删除持仓的set
            String setKey = this.getCacheSetKey(coPositionOrder);
            RedisUtil.instance().removeSet(setKey,
                    this.getCacheSetValue(coPositionOrder));
            //删除具体持仓数据
            RedisUtil.instance().remove(this.getCacheDataKey(coPositionOrder));
            RedisUtil.instance().sortZremove(RedisCacheKey.POSITION_SORT_PNL_KEY, coPositionOrder.getUid());
        }

        if (ventureCapital.compareTo(BigDecimal.ZERO) > 0) {
            //爆仓后将用户所有仓位维持保证金转入公司风险准备金
            boolean result = accountAction.operateAccount(positionLiquidationDTO.getUid(),
                    BigDecimal.ZERO,
                    AccountSymbolConstant.USDT,
                    TransactionSceneEnum.LIQUIDATION.getValue(),
                    "user",
                    positionLiquidationDTO.getUid(),
                    BigDecimal.ZERO,
                    ventureCapital,
                    BigDecimal.ZERO);

            if (!result) {
                log.error("CoPositionOrderServiceImpl -> liquidation operateAccount error");
                throw new BizException(CommonEnum.ACCOUNT_LIQUIDATION_FAIL);
            }
        }
    }

    @Override
    @Transactional
    public BigDecimal selectPositionNominalValueWithLock(Long uid, String symbol, String side) {
        LambdaQueryWrapper<CoPositionOrder> queryWrapper = Wrappers.lambdaQuery(CoPositionOrder.class)
                .eq(CoPositionOrder::getUid, uid)
                .eq(CoPositionOrder::getSymbol, symbol);
        if (Objects.nonNull(side)) {
            queryWrapper.eq(CoPositionOrder::getPositionSide, side.toUpperCase());
        }
        List<CoPositionOrder> positionOrders = list(queryWrapper);
        if (CollectionUtil.isEmpty(positionOrders)) {
            return BigDecimal.ZERO;
        }
        List<Long> ids = positionOrders.stream().map(CoPositionOrder::getId).collect(Collectors.toList());
        List<CoPositionOrder> list = list(Wrappers.lambdaQuery(CoPositionOrder.class)
                .in(CoPositionOrder::getId, ids)
                .last(" for update"));

        BigDecimal total = list.stream().map(CoPositionOrder::getDealQuote).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }

    @Override
    public CoPositionOrder selectPositionWithLock(Long uid, String symbol, String side) {
        return getOne(Wrappers.lambdaQuery(CoPositionOrder.class)
                .eq(CoPositionOrder::getUid, uid)
                .eq(CoPositionOrder::getSymbol, symbol)
                .eq(CoPositionOrder::getPositionSide, side.toUpperCase())
                .last(" for update "));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePositionForLeverageChange(Integer oldLeverage, Integer newLeverage, Long uid, String symbol) {
        if (oldLeverage == newLeverage) return;
        List<CoPositionOrder> positionOrders = list(Wrappers.lambdaQuery(CoPositionOrder.class).eq(CoPositionOrder::getUid, uid)
                .eq(CoPositionOrder::getSymbol, symbol));
        List<Long> ids = positionOrders.stream().map(CoPositionOrder::getId).collect(Collectors.toList());
        List<CoPositionOrder> list = list(Wrappers.lambdaQuery(CoPositionOrder.class)
                .in(CoPositionOrder::getId, ids)
                .last(" for update"));
        for (CoPositionOrder coPositionOrder : list) {
            updatePositionAndAccount(coPositionOrder, newLeverage, symbol);
        }
    }

    private void updatePositionAndAccount(CoPositionOrder coPositionOrder, Integer newLeverage, String symbol) {
        ContractConfigPO contractConfig = ContractConfigUtil.getContractConfig(symbol);
        BigDecimal nominalValue = coPositionOrder.getDealQuote();
        MaintenanceMarginRatePO maintenanceMarginRatePO = maintenanceMarginRateBizService.getByNominalValue(NominalValueRequest.newBuilder().setSymbol(symbol).setNominalValue(nominalValue.toPlainString()).build());
        BigDecimal newMargin = nominalValue.divide(BigDecimal.valueOf(newLeverage), contractConfig.getQuotePrecision().getValue(), RoundingMode.HALF_UP);
        BigDecimal deltaMargin = newMargin.subtract(coPositionOrder.getMargin());
        BigDecimal maintenanceMargin = nominalValue.multiply(new BigDecimal(maintenanceMarginRatePO.getMaintenanceMarginRate())).subtract(new BigDecimal(maintenanceMarginRatePO.getMaintenanceAmount()));
        BigDecimal closeFee = nominalValue.multiply(new BigDecimal(contractConfig.getCloseTakerFeeRate()));
        BigDecimal marginRatioMolecule = maintenanceMargin.add(closeFee);
        coPositionOrder.setLeverageLevel(newLeverage);
        coPositionOrder.setMargin(newMargin);
        coPositionOrder.setMaintenanceMargin(maintenanceMargin);
        coPositionOrder.setCloseFee(closeFee);
        coPositionOrder.setMarginRatioMolecule(marginRatioMolecule);
        boolean b = updateById(coPositionOrder);
        if (!b) {
            log.error(CommonEnum.UPDATE_POSITION_ERROR.getResultMsg() + "id=" + coPositionOrder.getId().toString());
            throw new BizException(CommonEnum.UPDATE_POSITION_ERROR);
        }
        TransactionSceneEnum marginSceneEnum = TransactionSceneEnum.UPDATE_LEVERAGE_DECREASE_MARGIN;
        if (deltaMargin.compareTo(BigDecimal.ZERO) > 0) {
            marginSceneEnum = TransactionSceneEnum.UPDATE_LEVERAGE_INCREASE_MARGIN;
        }
        boolean margin_result = accountAction.operateAccount(coPositionOrder.getUid(),
                deltaMargin.abs(),
                contractConfig.getQuote(),
                marginSceneEnum.getValue(),
                "co_position_order",
                coPositionOrder.getId(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
        if (!margin_result) {
            log.error(CommonEnum.UPDATE_ACCOUNT_MARGIN_ERROR.getResultMsg()
                    + ",uid=" + coPositionOrder.getUid()
                    + ",amount=" + deltaMargin.toPlainString()
                    + ",TransactionSceneEnum=" + marginSceneEnum);
            throw new BizException(CommonEnum.UPDATE_ACCOUNT_MARGIN_ERROR);
        }
    }

    @Override
    public RiskDataDTO getRiskData(Long uid) {
        return getRiskDataBySymbol(uid, null);
    }


    @Override
    public RiskDataDTO getRiskDataBySymbol(Long uid, String symbol) {
        List<CoPositionOrder> positionOrderList = list(Wrappers.lambdaQuery(CoPositionOrder.class)
                .eq(CoPositionOrder::getUid, uid)
                .eq(StrUtil.isNotBlank(symbol), CoPositionOrder::getSymbol, symbol));

        RiskDataDTO result = new RiskDataDTO();
        if (CollectionUtil.isNotEmpty(positionOrderList)) {
            for (CoPositionOrder item : positionOrderList) {
                result.setMargin(NumberUtil.add(item.getMargin(), result.getMargin()));
                result.setMarginRatioMolecule(NumberUtil.add(item.getMarginRatioMolecule(), result.getMarginRatioMolecule()));
                result.setCloseFee(NumberUtil.add(item.getCloseFee(), result.getCloseFee()));
                result.setNominalValue(NumberUtil.add(item.getNominalValue(), result.getNominalValue()));
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delaySyncCache(CoPositionOrder coPositionOrder) {
        CoPositionOrder positionOrder = getByUidAndStatusAndSideWithLock(coPositionOrder.getUid(), coPositionOrder.getSymbol()
                , PositionSideEnum.valueOf(coPositionOrder.getPositionSide()));
        if (positionOrder != null) {
            updatePositionCache(positionOrder);
        } else {
            deletePositionCache(coPositionOrder);
        }
    }
}
