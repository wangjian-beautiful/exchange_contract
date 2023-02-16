package com.bjs.contract.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.constants.CoOrderConstant;
import com.bijinsuo.common.constants.PrecisionConstant;
import com.bijinsuo.common.domain.AccountDTO;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.PositionUtil;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.entity.*;
import com.bijinsuo.common.utils.enums.*;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.AccountSymbolConstant;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.action.ContractConfigAction;
import com.bjs.contract.action.MaintenanceMarginRateAction;
import com.bjs.contract.entity.*;
import com.bjs.contract.mapper.CoOrderMapper;
import com.bjs.contract.proto.coOrder.CoOrderRequest;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import com.bjs.contract.service.*;
import com.bjs.contract.utils.OrderValidator;
import com.bjs.contract.utils.ContractConfigUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;

/**
 * @author bjs code generator
 * @date 2022-11-12 14:25:01
 */
@Service
public class CoOrderServiceImpl extends ServiceImpl<CoOrderMapper, CoOrder> implements CoOrderService {

    @Resource
    private CoPositionOrderService coPositionOrderService;
    @Resource
    private CoTradeService coTradeService;
    @Resource
    private SettlementService settlementService;
    @Resource
    private AccountAction accountAction;
    @Resource
    private ContractConfigAction contractConfigAction;
    @Resource
    private MaintenanceMarginRateAction maintenanceMarginRateAction;
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private UserLeverageService userLeverageService;
    @Resource
    private CoTriggerOrderService coTriggerOrderService;
    @Resource
    private CoOrderMapper coOrderMapper;
    @Autowired
    private CoOrderServiceImpl thisProxy;
    @Resource
    private MongoTemplate mongoTemplate;

    private static final int DEFAULT_LEVERAGE_LEVEL = 20;
    private static final String DEFAULT_IP = "0.0.0.0";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CoOrder createOrder(CoOrderRequest request){
        final var now = new Date();
        final var orderDTO = ProtoBeanUtils.toPojoBean(CoOrderDTO.class, request.getCoOrderPO());
        ContractConfigDTO symbolConfig = contractConfigAction.getBySymbol(orderDTO.getSymbol());
        BeanUtils.copyProperties(symbolConfig, orderDTO);
        orderDTO.setOperateType(OperateTypeEnum.OPEN.name());
        orderDTO.setId(null);
        changeMarketOrderParams(orderDTO);
        orderDTO.setLeverageLevel(userLeverageService.findByUidAndSymbolWithLockAndNotExistInit(orderDTO.getUid(), orderDTO.getSymbol()).getLeverage());
        orderDTO.setLockPotentialFee(BigDecimal.ZERO);
        OrderValidator orderValidator = new OrderValidator(symbolConfig);
        orderValidator.validateOrderConfig(orderDTO);
        AccountDTO accountDTO = accountAction.getByUidAndType(orderDTO.getUid(), AccountTypeEnum.normal.getCode().longValue());
        BigDecimal totalAmount = accountAction.getUserTotalAmount(orderDTO.getUid());
        if (Objects.isNull(accountDTO) || totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException(CommonEnum.NO_ACCOUNT);
        }
        orderValidator.validatePositionRisk(orderDTO, accountDTO.getBalance(), totalAmount, maintenanceMarginRateAction, coPositionOrderService);
        BigDecimal lockFee = orderValidator.getFee(orderDTO);
        BigDecimal totalLock = lockFee.add(orderValidator.getMargin(orderDTO));
        final var order = new CoOrder();
        BeanUtils.copyProperties(orderDTO, order);
        order.setCtime(now);
        order.setMtime(now);
        order.setLockPotentialFee(lockFee);
        boolean saved = super.save(order);
        boolean frozen = accountAction.operateAccount(orderDTO.getUid(), totalLock, symbolConfig.getQuote(), TransactionSceneEnum.OPEN_ORDER.getValue(), "co_order", order.getId(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        if (!frozen) {
            throw new BizException(CommonEnum.FREEZE_ACCOUNT_BALANCE_FAIL);
        }
        if (!saved) {
            throw new BizException(CommonEnum.CREATE_ORDER_ERROR);
        }
        if (orderDTO.getTakeProfitPriceTrigger() != null && orderDTO.getTakeProfitPriceTrigger().compareTo(BigDecimal.ZERO) > 0) {
            coTriggerOrderService.save(createTriggerOrder(orderDTO.getTakeProfitPriceTrigger(), TriggerTypeEnum.TAKE_PROFIT.getCode(), order));
        }
        if (orderDTO.getStopLossPriceTrigger() != null && orderDTO.getStopLossPriceTrigger().compareTo(BigDecimal.ZERO) > 0) {
            coTriggerOrderService.save(createTriggerOrder(orderDTO.getStopLossPriceTrigger(), TriggerTypeEnum.STOP_LOSS.getCode(), order));
        }
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CoOrder cancelOrder(Long orderId) {
        CoOrder coOrder = getById(orderId);
        if (coOrder == null || !UserContextHolder.user.get().getId().equals(coOrder.getUid())) {
            throw new BizException(CommonEnum.CANCEL_ORDER_ERROR);
        }
        List<CoTriggerOrder> coTriggerOrders = coTriggerOrderService.list(Wrappers.lambdaQuery(CoTriggerOrder.class)
                .eq(CoTriggerOrder::getMasterId, orderId)
                .eq(CoTriggerOrder::getStatus, TriggerStatusEnum.NOT_ACTIVE.getCode()));
        coOrder.setStatus(OrderStatus.PENDING_CANCEL.value);
        coOrder.setMtime(new Date());
        boolean b = super.updateById(coOrder);
        if (!com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isEmpty(coTriggerOrders)) {
            coTriggerOrders.forEach(coTriggerOrder -> coTriggerOrder.setStatus(TriggerStatusEnum.CANCELED.getCode()));
            coTriggerOrderService.updateBatchById(coTriggerOrders);
        }
        if (!b) throw new BizException(CommonEnum.CANCEL_ORDER_ERROR);

        return coOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrderAfterMatch(MatchTradeDTO dto) {
        CoOrder coOrder = getById(dto.getOrderId());
        coOrder.setStatus(OrderStatus.CANCELED.value);
        coOrder.setMtime(new Date());
        UserLeverage userLeverage = userLeverageService.findByUidAndSymbolWithLockAndNotExistInit(coOrder.getUid(), coOrder.getSymbol());
        ContractConfigPO contractConfig = ContractConfigUtil.getContractConfig(coOrder.getSymbol());
        if (OperateTypeEnum.valueOf(coOrder.getOperateType()) == OperateTypeEnum.OPEN) {
            //计算待释放冻结
            BigDecimal notDealQuote = coOrder.getVolumeQuote().subtract(coOrder.getDealQuote());
            BigDecimal frozenMargin = notDealQuote.divide(new BigDecimal(userLeverage.getLeverage()), contractConfig.getQuotePrecision().getValue(), RoundingMode.HALF_UP);
            BigDecimal frozenFee = getFrozenFee(notDealQuote, contractConfig);
            BigDecimal frozen = frozenMargin.add(frozenFee);
            //更新账户冻结
            accountAction.operateAccount(coOrder.getUid(),
                    frozen,
                    contractConfig.getQuote(),
                    TransactionSceneEnum.CANCEL_ORDER.getValue(),
                    "co_order",
                    coOrder.getId(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        } else {
            //更新持仓冻结
            coPositionOrderService.cancelOrder(coOrder);
        }

        //更新order
        updateById(coOrder);
    }


    private void changeMarketOrderParams (CoOrderDTO orderDTO) {
        if (orderDTO.getPrice() != null && orderDTO.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            orderDTO.setMatchType(ExchangeOrderType.LIMIT_PRICE.value);
        } else {
            orderDTO.setMatchType(ExchangeOrderType.MARKET_PRICE.value);
            orderDTO.setPrice(BigDecimal.ZERO);
            if(OperateTypeEnum.OPEN.name().equalsIgnoreCase(orderDTO.getOperateType())) {
                orderDTO.setVolumeBase(BigDecimal.ZERO);
            }
        }
    }

    private BigDecimal getFrozenFee(BigDecimal notDealQuote, ContractConfigPO contractConfig) {
        if (notDealQuote.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        return notDealQuote.multiply(new BigDecimal(contractConfig.getOpenTakerFeeRate()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelUserOrdersNotFilled(Long uid) {
        List<CoOrder> list = list(Wrappers.lambdaQuery(CoOrder.class)
                .eq(CoOrder::getUid, uid)
                .in(CoOrder::getStatus, Arrays.asList(OrderStatus.NEW_.value, OrderStatus.INIT.value, OrderStatus.PART_FILLED)));
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(coOrder -> coOrder.setStatus(OrderStatus.PENDING_CANCEL.value));
            boolean b = updateBatchById(list);
            if (b) {
                list.forEach(coOrder -> rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_ORDER, JSON.toJSONString(createMatchOrder(coOrder, MatchInfoTypeEnum.cancel_order)), coOrder.getSymbol()));
            }
            return b;
        }
        return true;
    }

    @Override
    public List<OrderCancelResultDTO> cancelUserOrders(long uid, String symbol, String side, int timeout) {
        final var orders = thisProxy.doCancelUserOrders(uid, symbol, side);
        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyList();
        }
        // 设定方法超时时间
        int timeoutBySeconds = timeout > 0 ? timeout : 60 * 3;
        // 等待撤销委托单完成
        final var orderIdsToCancel = orders.stream()
                .map(CoOrder::getId)
                .collect(Collectors.toCollection(HashSet::new));
        List<OrderCancelResultDTO> cancelledOrders = new ArrayList<>();
        final var instantToStop = Instant.ofEpochSecond(Instant.now().getEpochSecond() + timeoutBySeconds);
        while (Instant.now().isBefore(instantToStop)) {
            var onWayOrder = list(Wrappers.lambdaQuery(CoOrder.class)
                    .in(CoOrder::getStatus, OrderStatus.getOnTheWayValueList()));
            if (CollectionUtils.isEmpty(onWayOrder)) {
                orderIdsToCancel.forEach(id -> cancelledOrders.add(new OrderCancelResultDTO(id, true)));
                break;
            }
            final var onWayIds = onWayOrder.stream().map(CoOrder::getId).collect(Collectors.toSet());
            orderIdsToCancel.forEach(id -> {
                if (!onWayIds.contains(id)) {
                    cancelledOrders.add(new OrderCancelResultDTO(id, true));
                    orderIdsToCancel.remove(id);
                }
            });
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
        }
        // 拼装返回结果
        if (orders.size() != cancelledOrders.size()) {
            for (Long id : orderIdsToCancel) {
                cancelledOrders.add(new OrderCancelResultDTO(id, false));
            }
        }
        return cancelledOrders;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CoOrder> doCancelUserOrders(long uid, String symbol, String side) {
        // 查询需要撤销的委托单
        final var orders = findOrdersToCancel(uid, symbol, side);
        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyList();
        }
        // 撤销委托单
        final var date = new Date();
        orders.forEach(o -> {
            o.setStatus(OrderStatus.PENDING_CANCEL.value);
            o.setMtime(date);
        });
        updateBatchById(orders);
        for (CoOrder order : orders) {
            rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_ORDER,
                    JSON.toJSONString(createMatchOrder(order,
                            MatchInfoTypeEnum.cancel_order)),
                    order.getSymbol());
        }
        return orders;
    }

    @Override
    public MatchOrderDTO createMatchOrder(CoOrder order, MatchInfoTypeEnum matchType) {
        final int NOT_ROBOT = 0;
        MatchOrderDTO matchOrderDTO = new MatchOrderDTO();
        matchOrderDTO.setId(order.getId());
        matchOrderDTO.setUserId(order.getUid().intValue());
        matchOrderDTO.setSide(OperateSideEnum.valueOf(order.getOperateSide().toUpperCase()));
        matchOrderDTO.setPrice(order.getPrice());
        matchOrderDTO.setSymbol(order.getSymbol());
        if (null == order.getPrice()) {
            matchOrderDTO.setType(ExchangeOrderType.MARKET_PRICE.value);
            matchOrderDTO.setVolume(order.getVolumeQuote());
        } else {
            matchOrderDTO.setType(ExchangeOrderType.LIMIT_PRICE.value);
            matchOrderDTO.setVolume(order.getVolumeBase());
        }
        matchOrderDTO.setStatus(matchType == MatchInfoTypeEnum.cancel_order ? OrderStatus.PENDING_CANCEL.value : OrderStatus.NEW_.value);
        matchOrderDTO.setRobot(NOT_ROBOT);
        matchOrderDTO.setOperateType(OperateTypeEnum.valueOf(order.getOperateType().toUpperCase()));
        matchOrderDTO.setMatchInfoType(matchType);
        return matchOrderDTO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLiquidationOrdersAndSettlement(List<CoPositionOrder> list, PositionLiquidationDTO positionLiquidationDTO) {
        final long SYS_UID = -1L;
        for (CoPositionOrder coPositionOrder : list) {
            //查询contract config
            ContractConfigDTO contractConfigDTO = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, ContractConfigUtil.getContractConfig(coPositionOrder.getSymbol()));
            CoOrder userOrder = initSysClosePositionMarketOrderWIthInMatch(coPositionOrder, contractConfigDTO, OrderTypeEnum.LIQUIDATION);
            userOrder.setPrice(positionLiquidationDTO.getSymbolPriceMap().get(coPositionOrder.getSymbol()));
            userOrder.setStatus(OrderStatus.FILLED.value);
            CoOrder sysOrder = new CoOrder();
            BeanUtils.copyProperties(userOrder, sysOrder);
            sysOrder.setUid(SYS_UID);
            sysOrder.setOperateSide(OperateSideEnum.getOpposite(userOrder.getOperateSide().toUpperCase()).name());
            sysOrder.setType(OrderTypeEnum.CLOSE_POSITION_MARKET.getCode());

            save(userOrder);
            save(sysOrder);

            CoTrade userTrade = initSysClosePositionTrade(userOrder, coPositionOrder.getId());
            CoTrade sysTrade = initSysClosePositionTrade(sysOrder, -1l);
            coTradeService.save(userTrade);
            coTradeService.save(sysTrade);

            MatchTradeDetailsDTO matchTradeDetailsDTO = initSysMatchTradeDetail(userOrder, sysOrder);
            settlementService.settlement(userOrder, coPositionOrder, matchTradeDetailsDTO, null, null, TransactionSceneEnum.LIQUIDATION);
        }
    }


    private CoTrade initSysClosePositionTrade(CoOrder coOrder, Long positionId) {
        Date now = new Date();
        CoTrade coTrade = new CoTrade();
        coTrade.setSymbol(coOrder.getSymbol());
        coTrade.setTradeNo(UUID.randomUUID().toString());
        coTrade.setUid(coOrder.getUid());
        coTrade.setPositionId(positionId);
        coTrade.setOrderId(coOrder.getId());
        coTrade.setOperateType(coOrder.getOperateType());
        coTrade.setOperateSide(coOrder.getOperateSide());
        coTrade.setPrice(coOrder.getPrice());
        coTrade.setVolumeBase(coOrder.getDealBase());
        coTrade.setVolumeQuote(coOrder.getDealQuote());
        coTrade.setTrendSide(coOrder.getOperateSide());
        coTrade.setFee(BigDecimal.ZERO);
        coTrade.setCtime(now);
        coTrade.setMtime(now);
        return coTrade;
    }

    private MatchTradeDetailsDTO initSysMatchTradeDetail(CoOrder userOrder, CoOrder sysOrder) {
        boolean buy = OperateSideEnum.valueOf(userOrder.getOperateSide()) == OperateSideEnum.BUY;
        MatchTradeDetailsDTO matchTradeDetailsDTO = new MatchTradeDetailsDTO();
        matchTradeDetailsDTO.setPrice(userOrder.getPrice());
        matchTradeDetailsDTO.setTurnover(userOrder.getDealQuote());
        matchTradeDetailsDTO.setAmount(userOrder.getDealBase());
        matchTradeDetailsDTO.setTrendSide(OperateSideEnum.valueOf(userOrder.getOperateSide()));
        matchTradeDetailsDTO.setBuyOrderId(buy ? userOrder.getId() : sysOrder.getId());
        matchTradeDetailsDTO.setSellOrderId(buy ? sysOrder.getId() : userOrder.getId());
        matchTradeDetailsDTO.setBuyFinish(true);
        matchTradeDetailsDTO.setSellFinish(true);
        return matchTradeDetailsDTO;
    }

    @Override
    public CoOrder initSysClosePositionMarketOrderWIthInMatch(CoPositionOrder positionOrder, ContractConfigDTO configDTO, OrderTypeEnum orderTypeEnum) {
        Date now = new Date();
        CoOrder coOrder = new CoOrder();
        coOrder.setSymbol(positionOrder.getSymbol());
        coOrder.setUid(positionOrder.getUid());
        coOrder.setPositionType(PositionTypeEnum.CROSS_MARGIN.getValue());
        coOrder.setOperateType(OperateTypeEnum.CLOSE.name());
        coOrder.setOperateSide(PositionSideEnum.getCloseSide(positionOrder.getPositionSide().toUpperCase()).name());
        coOrder.setType(orderTypeEnum.getCode());
        coOrder.setMatchType(orderTypeEnum.getMatchType());
        coOrder.setVolumeBase(positionOrder.getDealBase());
        //coOrder.setVolumeQuote(positionOrder.getDealQuote());
        coOrder.setIp("");
        coOrder.setSource(OrderSourceEnum.SYS.getValue());
        coOrder.setCtime(now);
        coOrder.setMtime(now);
        initOrderConfig(coOrder, configDTO);
        return coOrder;
    }

    @Override
    public CoOrderDTO initSysClosePositionMarketDtoOrderWithInMatch(CoPositionOrder positionOrder, ContractConfigDTO configDTO, OrderTypeEnum orderTypeEnum) {
        CoOrder coOrder = initSysClosePositionMarketOrderWIthInMatch(positionOrder, configDTO, orderTypeEnum);
        CoOrderDTO result = new CoOrderDTO();
        BeanUtils.copyProperties(coOrder, result);
        return result;
    }

    private void initOrderConfig(CoOrder order, ContractConfigDTO configDTO) {
        order.setOpenMakerFeeRate(configDTO.getOpenMakerFeeRate());
        order.setOpenTakerFeeRate(configDTO.getOpenTakerFeeRate());
        order.setCloseMakerFeeRate(configDTO.getCloseMakerFeeRate());
        order.setCloseTakerFeeRate(configDTO.getCloseTakerFeeRate());
        order.setMinMakerFee(configDTO.getMinMakerFee());
        order.setMinTakerFee(configDTO.getMinTakerFee());
    }

    @Override
    public List<CoOrder> selectUserOnTheWayOrderList(Long uid, String symbol) {
        return super.query()
                .eq("uid", uid)
                .eq("symbol", symbol)
                .eq("operate_type", OperateTypeEnum.OPEN.name())
                .in("status", OrderStatus.getOnTheWayValueList())
                .list();
    }

    @Override
    public BigDecimal selectUserOnTheWayTotalQuote(Long uid, String symbol) {
        List<CoOrder> coOrderList = selectUserOnTheWayOrderList(uid, symbol);
        if (CollectionUtils.isEmpty(coOrderList)) {
            return BigDecimal.ZERO;
        }
        BigDecimal totalQuote = BigDecimal.ZERO;
        for (CoOrder coOrder : coOrderList) {
            totalQuote = NumberUtil.add(totalQuote, coOrder.getVolumeQuote());
        }
        return totalQuote;
    }

    @Override
    public void handleNewAmountForNewLeverage(Integer newLeverage, UserLeverage curLeverage, Long uid, String
            symbol) {
//        //查询还未成交完毕的 开仓订单
        List<CoOrder> coOrderList = selectUserOnTheWayOrderList(uid, symbol);
        if (CollectionUtils.isEmpty(coOrderList)) {
            return;
        }
        ContractConfigDTO configDTO = contractConfigAction.getBySymbol(symbol);
        boolean isIncreaseLockMargin = curLeverage.getLeverage() > newLeverage;
        //计算老杠杆倍数的保证金
        //计算新杠杠倍数的保证金
        //计算两者之余
        BigDecimal totalDifference = BigDecimal.ZERO;
        for (CoOrder coOrder : coOrderList) {
            BigDecimal oldMargin = PositionUtil.calculateMargin(coOrder.getVolumeQuote()
                    , curLeverage.getLeverage());
            BigDecimal newMargin = PositionUtil.calculateMargin(coOrder.getVolumeQuote()
                    , newLeverage);

            BigDecimal difference = NumberUtil.sub(newMargin, oldMargin).abs();
            totalDifference = NumberUtil.add(totalDifference, difference);
        }


        TransactionSceneEnum sceneEnum = isIncreaseLockMargin ? TransactionSceneEnum.UPDATE_LEVERAGE_INCREASE_LOCK_MARGIN :
                TransactionSceneEnum.UPDATE_LEVERAGE_DECREASE_LOCK_MARGIN;
        if (sceneEnum.equals(TransactionSceneEnum.UPDATE_LEVERAGE_INCREASE_LOCK_MARGIN)) {
            AccountDTO normalBalance = accountAction.getByUidAndType(uid, Long.valueOf(AccountTypeEnum.normal.getCode()));
            if (normalBalance == null || normalBalance.getBalance().compareTo(totalDifference) < 0) {
                throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
            }
        }
        boolean suc = accountAction.operateAccount(uid
                , totalDifference
                , AccountSymbolConstant.USDT
                , sceneEnum.getValue()
                , "user_leverage"
                , curLeverage.getId()
                , BigDecimal.ZERO
                , BigDecimal.ZERO
                , BigDecimal.ZERO
        );

        if (!suc) {
            throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
        }

    }

    @Override
    public int getOrderCount(long id) {
        LambdaQueryWrapper<CoOrder> query = new LambdaQueryWrapper<>();
        // 查询订单状态
        List<Integer> coOrderStatus = new ArrayList<>();
        coOrderStatus.add(OrderStatus.INIT.getValue());
        coOrderStatus.add(OrderStatus.NEW_.getValue());
        coOrderStatus.add(OrderStatus.PART_FILLED.getValue());

        query.eq(CoOrder::getUid, id);
        query.in(CoOrder::getStatus, coOrderStatus);
        return coOrderMapper.selectCount(query).intValue();
    }

    @Override
    public void refundOrderFeeAmount(Long orderId) {
        CoOrder coOrder = baseMapper.selectById(orderId);
        List<CoTrade> coTradeList = coTradeService.list(Wrappers.lambdaQuery(CoTrade.class).eq(CoTrade::getOrderId, orderId));
        BigDecimal lockPotentialFee = coOrder.getLockPotentialFee();
        BigDecimal total = coTradeList.stream().map(CoTrade::getFee).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (lockPotentialFee.compareTo(total) > 0) {
            boolean result = accountAction.operateAccount(coOrder.getUid(),
                    lockPotentialFee.subtract(total).setScale(PrecisionConstant.ACCOUNT_PRECISION),
                    AccountSymbolConstant.USDT,
                    TransactionSceneEnum.REFUND_FEE.getValue(),
                    "co_order",
                    orderId,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO);
            if (!result) throw new BizException(CommonEnum.REFUND_FREEZE_FEE_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CoOrder orderClose(CoOrderDTO orderDTO, ContractConfigDTO symbolConfig) {
        final var now = new Date();
        if (orderDTO.getVolumeBase() == null) {
            throw new BizException(CommonEnum.BODY_NOT_MATCH);
        }

        orderDTO.setOperateType(OperateTypeEnum.CLOSE.name());
        BeanUtils.copyProperties(symbolConfig, orderDTO);
        orderDTO.setId(null);
        orderDTO.setLockPotentialFee(BigDecimal.ZERO);

        changeMarketOrderParams(orderDTO);

        OrderValidator orderValidator = new OrderValidator(symbolConfig);
        //限价平仓需要校验价格
        orderValidator.validatePrice(orderDTO);

        orderValidator.validateCloseOrderAmount(orderDTO.getPrice(), orderDTO.getVolumeBase());
        // 校验与冻结持仓
        CoPositionOrder position = coPositionOrderService.selectPositionWithLock(orderDTO.getUid(), orderDTO.getSymbol(),
                PositionSideEnum.getCloseSide(orderDTO.getOperateSide().toUpperCase()).name());
        if (position == null) {
            throw new BizException(CommonEnum.NOT_ENOUGH_POSITION);
        }
        if(ExchangeOrderType.LIMIT_PRICE.value.equals(orderDTO.getMatchType())) {
            orderDTO.setVolumeQuote(orderDTO.getVolumeBase().multiply(position.getAvgPrice()));
        }
        orderValidator.validatePositionForClose(orderDTO, position);
        position.setFrozenBase(NumberUtil.add(position.getFrozenBase(), orderDTO.getVolumeBase()));

        coPositionOrderService.updateById(position);
        // 保存订单
        final var order = new CoOrder();
        BeanUtils.copyProperties(orderDTO, order);
        order.setCtime(now);
        order.setMtime(now);
        boolean saved = super.save(order);
        if (!saved) {
            throw new BizException(CommonEnum.CREATE_ORDER_ERROR);
        }
        return order;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CoOrderDTO orderCloseForNewTransaction(CoOrderDTO coOrderDTO, ContractConfigDTO contractConfigDTO) {
        orderClose(coOrderDTO, contractConfigDTO);
        return coOrderDTO;
    }

    private CoTriggerOrder createTriggerOrder(BigDecimal triggerPrice, Integer triggerType, CoOrder order) {
        if (Objects.isNull(triggerPrice)) return null;
        final var triggerOrder = new CoTriggerOrder();
        BeanUtils.copyProperties(order, triggerOrder);
        triggerOrder.setVolumeBase(order.getVolumeBase());
        triggerOrder.setVolumeQuote(order.getVolumeQuote());
        triggerOrder.setId(null);
        triggerOrder.setStatus(TriggerStatusEnum.NOT_ACTIVE.getCode());
        triggerOrder.setTriggerType(triggerType);
        triggerOrder.setTriggerPrice(triggerPrice);
        triggerOrder.setCtime(new Date());
        triggerOrder.setType(0);
        triggerOrder.setMasterId(order.getId());
        return triggerOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleCompleteOrders(List<MatchOrderDTO> completedOrders) {
        Date now = new Date();
        List<Long> ids = completedOrders.stream().map(matchOrderDTO -> matchOrderDTO.getId()).collect(Collectors.toList());
//        Map<Long, MatchOrderDTO> completedOrdersMap = completedOrders.stream().collect(Collectors.toMap(MatchOrderDTO::getId, matchOrderDTO -> matchOrderDTO));
        List<CoOrder> list = list(Wrappers.lambdaQuery(CoOrder.class).in(CoOrder::getId, ids));
        for (CoOrder order : list) {
            order.setStatus(OrderStatus.FILLED.value);
            order.setMtime(now);
        }
        updateBatchById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleMatchForRobot(MatchTradeDTO dto) {
        String tradeNumber = UUID.randomUUID().toString();
        Long buyOrderId = dto.getDetailsDTO().getBuyOrderId();
        Long sellOrderId = dto.getDetailsDTO().getSellOrderId();

        CoOrder buyOrder = getOrderFromMongo(buyOrderId, dto, OperateSideEnum.BUY);
        CoOrder sellOrder = getOrderFromMongo(sellOrderId, dto, OperateSideEnum.SELL);

        super.save(buyOrder);
        super.save(sellOrder);

        buildTradeAndInsert(buyOrder, dto.getDetailsDTO(), tradeNumber);
        buildTradeAndInsert(sellOrder, dto.getDetailsDTO(), tradeNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CoOrder> createOrders(List<CoOrder> coOrderList) {
        final var now = new Date();
        ContractConfigDTO symbolConfig = null;
        for (CoOrder order : coOrderList) {
            if (symbolConfig == null) {
                symbolConfig = contractConfigAction.getBySymbol(order.getSymbol());
            }
            BeanUtils.copyProperties(symbolConfig, order);
            order.setOperateType(OperateTypeEnum.OPEN.name());
            order.setId(null);
            if (order.getPrice() != null && order.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                order.setMatchType(ExchangeOrderType.LIMIT_PRICE.value);
            } else {
                order.setMatchType(ExchangeOrderType.MARKET_PRICE.value);
            }
            order.setLeverageLevel(userLeverageService.findByUidAndSymbolWithLockAndNotExistInit(order.getUid(), order.getSymbol()).getLeverage());
            order.setLockPotentialFee(BigDecimal.ZERO);


            order.setCtime(now);
            order.setMtime(now);
        }
        return null;
    }

    @Override
    public CoOrder getOrderFromMongo(Long orderId, MatchTradeDTO dto, OperateSideEnum sideEnum) {
        CoOrder order;
        Date now = new Date();

        Query query = new Query(Criteria.where("id").is(orderId));
        List<CoOrder> coOrderList = mongoTemplate.find(query, CoOrder.class, CoOrderConstant.MONGO_COLLECTION_NAME);
        if (CollectionUtils.isEmpty(coOrderList)) {
            order = crateVirtualOrder(dto, sideEnum, now);
        } else {
            order = coOrderList.get(0);
        }
        order.setId(null);
        order.setCtime(now);
        order.setMtime(now);
        return order;
    }

    private CoOrder crateVirtualOrder(MatchTradeDTO dto, OperateSideEnum sideEnum, Date now) {
        ContractConfigPO contractConfig = ContractConfigUtil.getContractConfig(dto.getSymbol());
        CoOrder order = new CoOrder();
        BeanUtils.copyProperties(contractConfig, order);
        order.setId(null);
        order.setSymbol(dto.getSymbol());
        order.setUid(-1l);
        order.setPositionType(PositionTypeEnum.CROSS_MARGIN.getValue());
        order.setMatchType(OrderMatchTypeEnum.LIMIT.value);
        order.setOperateType(OperateTypeEnum.OPEN.name());
        order.setOperateSide(sideEnum.name());
        order.setType(OrderTypeEnum.OPEN_LIMIT.getCode());
        order.setLeverageLevel(DEFAULT_LEVERAGE_LEVEL);
        order.setPrice(dto.getDetailsDTO().getPrice());
        order.setVolumeBase(dto.getDetailsDTO().getAmount());
        order.setVolumeQuote(dto.getDetailsDTO().getTurnover());
        order.setStatus(OrderStatus.FILLED.value);
        order.setIp(DEFAULT_IP);
        order.setSource(OrderSourceEnum.SYS.value);
        order.setDealBase(order.getVolumeBase());
        order.setAvgPrice(order.getPrice());
        order.setDealQuote(order.getVolumeQuote());
        order.setCtime(now);
        order.setMtime(now);
        order.setLockPotentialFee(BigDecimal.ZERO);
        return order;
    }

    private CoTrade buildTradeAndInsert(CoOrder order, MatchTradeDetailsDTO dto, String tradeNumber) {
        Date now = new Date();
        CoTrade coTrade = new CoTrade();
        coTrade.setSymbol(order.getSymbol());
        coTrade.setTradeNo(tradeNumber);
        coTrade.setOrderId(order.getId());
        coTrade.setPositionId(-1l);
        coTrade.setUid(order.getUid());
        //方向
        coTrade.setOperateType(order.getOperateType());
        coTrade.setOperateSide(order.getOperateSide());
        coTrade.setTrendSide(dto.getTrendSide().toString());
        //金额
        coTrade.setVolumeBase(dto.getAmount());
        coTrade.setVolumeQuote(dto.getTurnover());
        coTrade.setPrice(dto.getPrice());
        coTrade.setFee(BigDecimal.ZERO);
        coTrade.setCtime(now);
        coTrade.setMtime(now);
        coTradeService.save(coTrade);
        return coTrade;
    }
}
