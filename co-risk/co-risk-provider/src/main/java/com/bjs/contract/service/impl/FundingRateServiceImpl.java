package com.bjs.contract.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.constants.PrecisionConstant;
import com.bijinsuo.common.domain.AccountDTO;
import com.bijinsuo.common.domain.CoPositionOrderDTO;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.enums.AccountTypeEnum;
import com.bijinsuo.common.utils.enums.ContractConfigStatusEnum;
import com.bijinsuo.common.utils.enums.PositionOrderStatusEnum;
import com.bijinsuo.common.utils.enums.PositionSideEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.domain.FundingRateDTO;
import com.bjs.contract.domain.FundingRateSettleAccountDTO;
import com.bijinsuo.common.domain.FundingRateSettleArrearsDTO;
import com.bjs.contract.domain.OKXFundingRateDTO;
import com.bjs.contract.entity.FundingRate;
import com.bjs.contract.mapper.FundingRateMapper;
import com.bjs.contract.proto.account.*;
import com.bjs.contract.proto.coPositionOrder.*;
import com.bjs.contract.proto.contractConfig.ContractConfigBizService;
import com.bjs.contract.proto.contractConfig.ContractConfigListReply;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import com.bjs.contract.proto.contractConfig.ContractConfigPageRequest;
import com.bjs.contract.service.FundingRateService;
import com.google.protobuf.Int32Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tech.powerjob.worker.core.processor.TaskContext;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 资金费率
 *
 * @author bjs code generator
 * @date 2022-11-11 11:13:47
 */
@Service
@Slf4j
public class FundingRateServiceImpl extends ServiceImpl<FundingRateMapper, FundingRate> implements FundingRateService {

    @DubboReference
    ContractConfigBizService contractConfigBizService;
    @DubboReference
    CoPositionOrderBizService coPositionOrderBizService;
    @DubboReference
    AccountBizService accountBizService;
    @Resource
    FundingRateService self;
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    AccountAction accountAction;


    private RestTemplate restTemplate = new RestTemplate();
    //instId=BTC-USDT-SWAP
    private static final String OKX_FUNDING_RATE_URL = "https://www.okx.com/api/v5/public/funding-rate?instId=";

    private static final ExecutorService getFundingRateExecutor = new ThreadPoolExecutor(0, 5, 120L
            , TimeUnit.SECONDS, new ArrayBlockingQueue<>(500));

    private static final ExecutorService settleExecutor = new ThreadPoolExecutor(0, 5, 60L
            , TimeUnit.SECONDS, new ArrayBlockingQueue<>(500));

    @Override
    public void getFundingRate(TaskContext taskContext) throws InterruptedException {
        //查询所有开启的交易对
        List<ContractConfigPO> contractConfigPOList = getAllContractConfig();
        if (CollectionUtil.isNotEmpty(contractConfigPOList)) {
            //循环拉取所有交易队的资金费率
            CountDownLatch countDownLatch = new CountDownLatch(contractConfigPOList.size());
            for (ContractConfigPO contractConfigPO : contractConfigPOList) {
                getFundingRateExecutor.execute(() -> {
                    try {
                        handOne(contractConfigPO);
                    } catch (Exception e) {
                        log.error("get funding rate error", e);
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await(3, TimeUnit.MINUTES);
        }
    }

    public void handOne(ContractConfigPO contractConfigPO) {
        String symbol = contractConfigPO.getBase().toUpperCase() + "-" + contractConfigPO.getQuote().toUpperCase() + "-SWAP";
        String url = OKX_FUNDING_RATE_URL + symbol;
        OKXFundingRateDTO okxFundingRateDTO = restTemplate.getForObject(url, OKXFundingRateDTO.class);
        log.info("{} get funding rate result {}", symbol, okxFundingRateDTO);
        if (okxFundingRateDTO.getCode().equals("0") &&
                CollectionUtil.isNotEmpty(okxFundingRateDTO.getData())) {
            OKXFundingRateDTO.DataBean data = okxFundingRateDTO.getData().get(0);
            handleCur(data, contractConfigPO);
            handleRedis(data, contractConfigPO);
        } else {
            log.error("get funding rate failed");
        }
    }


    public static void main(String[] args) {
        String url = OKX_FUNDING_RATE_URL + "BTC-USDT-SWAP";
        System.out.println(url);
        System.out.println(HttpUtil.get(url));
        System.out.println(HttpUtil.get("www.baidu.com"));
        System.out.println(HttpUtil.get(url));
        System.out.println(HttpUtil.get("https://fapi.binance.com/fapi/v1/fundingRate?symbol=BTCUSDT&limit=1"));
    }

    private void handleRedis(OKXFundingRateDTO.DataBean data, ContractConfigPO contractConfigPO) {
        FundingRateDTO fundingRateDTO = new FundingRateDTO();
        fundingRateDTO.setNextFundRate(data.getNextFundingRate());
        fundingRateDTO.setCurrentFundRate(data.getFundingRate());
        String json = JSON.toJSONString(fundingRateDTO);
        RedisUtil.instance().hmput(RedisCacheKey.FUNDING_RATE_KEY, contractConfigPO.getSymbol(), json);

    }

    private void handleCur(OKXFundingRateDTO.DataBean data, ContractConfigPO contractConfigPO) {
        Date curFundingTime = new Date(Long.parseLong(data.getFundingTime()));
        //查询当期数据是否存在
        if (exitFundingTime(contractConfigPO.getSymbol(), curFundingTime)) {
            return;
        }
        FundingRate fundingRate = new FundingRate();
        fundingRate.setSymbol(contractConfigPO.getSymbol());
        fundingRate.setRate(NumberUtil.toBigDecimal(data.getFundingRate()));
        fundingRate.setSettleTime(curFundingTime);
        super.save(fundingRate);
    }

    private boolean exitFundingTime(String symbol, Date settleTime) {
        QueryWrapper<FundingRate> wrapper = new QueryWrapper<>();
        wrapper.eq("symbol", symbol);
        wrapper.eq("settle_time", settleTime);
        return super.count(wrapper) > 0;
    }

    private List<ContractConfigPO> getAllContractConfig() {
        ContractConfigPO po = ContractConfigPO
                .newBuilder()
                .setStatus(Int32Value.newBuilder().setValue(ContractConfigStatusEnum.CAN_TRADE.getCode()).build())
                .build();
        ContractConfigPageRequest request = ContractConfigPageRequest.newBuilder()
                .setPage(0)
                .setSize(1000)
                .setContractConfigPO(po)
                .build();
        ContractConfigListReply contractConfigListReply = contractConfigBizService.selectList(request);
        return contractConfigListReply.getContractConfigPOList();
    }

    @Override
    public void settleDistribute(Date settleTime, TaskContext taskContext) throws InterruptedException {
        //资金费率计算
        List<ContractConfigPO> contractConfigPOList = getAllContractConfig();
        if (CollectionUtil.isNotEmpty(contractConfigPOList)) {
            CountDownLatch countDownLatch = new CountDownLatch(contractConfigPOList.size());
            for (ContractConfigPO contractConfigPO : contractConfigPOList) {
                settleExecutor.execute(() -> {
                    try {
                        self.settle(contractConfigPO, settleTime, taskContext);
                    } catch (Exception e) {
                        log.error("settle error", e);
                    } finally {
                        countDownLatch.countDown();

                    }
                });
            }
            countDownLatch.await(1, TimeUnit.HOURS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void settle(ContractConfigPO contractConfigPO, Date settleTime, TaskContext taskContext) {
        String symbol = contractConfigPO.getSymbol();
        //先查当期资金费率
        QueryChainWrapper<FundingRate> queryChainWrapper = super.query();
        queryChainWrapper.eq("symbol", symbol)
                .eq("settle_time", settleTime);
        FundingRate fundingRate = queryChainWrapper.one();
        if (fundingRate == null) {
            throw new RuntimeException(settleTime + " funding rate not exit");
        }
        BigDecimal rate = fundingRate.getRate();
        log.info("symbol:{} settle rate:{}", symbol, rate);
        if (rate.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        boolean isBuySide = rate.compareTo(BigDecimal.ZERO) > 0 ? true : false;

        //先查需要扣钱的持仓方向 持仓
        List<CoPositionOrderDTO> needDeductPositionList = getAllPositionOrder(symbol, isBuySide);
        if (CollectionUtil.isEmpty(needDeductPositionList)) {
            log.info("symbol:{} need deduct position is empty", symbol);
            return;
        }
        //加钱的持仓方向 持仓
        List<CoPositionOrderDTO> needIncreasePositionList = getAllPositionOrder(symbol, !isBuySide);
        //名义价值*资金费率
        BigDecimal absoluteRate = rate.abs();
        //去扣金额
        BigDecimal totalDeductAmount = deductForResult(needDeductPositionList, absoluteRate, symbol, isBuySide);
        //去加金额
        if (CollectionUtil.isNotEmpty(needIncreasePositionList) && totalDeductAmount.compareTo(BigDecimal.ZERO) > 0) {
            //加钱方向
            increaseAccount(needIncreasePositionList, totalDeductAmount, symbol);
        }
        log.info("");
        //计算每个用户需要扣除多少资金费率
    }

    private BigDecimal deductForResult(List<CoPositionOrderDTO> needDeductPositionList, BigDecimal absoluteRate
            , String symbol, boolean isBuySide) {
        //分为两步
        //1、调用account
        //2、如果account有扣减保证金，那么调用持仓去修改保证金
        List<FundingRateSettleAccountDTO> settleDTOList = new ArrayList<>();
        for (CoPositionOrderDTO needDeduct : needDeductPositionList) {
            if (needDeduct.getUid() != null && needDeduct.getNominalValue() != null
                    && needDeduct.getNominalValue().compareTo(BigDecimal.ZERO) > 0) {
                FundingRateSettleAccountDTO settleDTO = new FundingRateSettleAccountDTO();
                settleDTO.setSymbol(symbol);
                settleDTO.setUid(needDeduct.getUid());
                //扣除金额向上取
                settleDTO.setAmount(NumberUtil.mul(needDeduct.getNominalValue(), absoluteRate)
                        .setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.HALF_UP));
                settleDTOList.add(settleDTO);
            }
        }
        //rpc 调用account 扣除金额
        List<UserAccountTransferPO> userAccountTransferPOList = ProtoBeanUtils
                .toProtoBeanList(UserAccountTransferPO.getDefaultInstance(), settleDTOList);

        UserAccountTransferListRequest request = UserAccountTransferListRequest.newBuilder()
                .addAllUserAccountTransferPO(userAccountTransferPOList).build();
        //组装数据 调用account
        UserAccountReply userAccountReply = accountBizService.accountRateDeduction(request);


        List<UserAccountTransferResponse> deductResponse = userAccountReply.getUserAccountTransferResponseList();
        // return getTotalDeductedAndCallPosition(deductResponse, symbol, isBuySide);
        return null;
    }

//    private BigDecimal getTotalDeductedAndCallPosition(List<UserAccountTransferResponse> deductResponse
//            , String symbol, boolean isBuySide) {
//        if (CollectionUtil.isEmpty(deductResponse)) {
//            return BigDecimal.ZERO;
//        }
//        BigDecimal totalDeducted = BigDecimal.ZERO;
//        List<FundingRateSettlePositionDTO> positionMarginSettleDTOList = new ArrayList<>();
//        //这里 一边统计总金额，一边统计需要修改持仓保证金的逻辑
//        for (UserAccountTransferResponse response : deductResponse) {
//            BigDecimal usable = new BigDecimal(response.getNormal());
//            BigDecimal margin = new BigDecimal(response.getBond());
//            //总金额
//            totalDeducted = NumberUtil.add(totalDeducted, margin, usable);
//            if (margin.compareTo(BigDecimal.ZERO) > 0) {
//                FundingRateSettlePositionDTO positionMarginSettle = new FundingRateSettlePositionDTO();
//                positionMarginSettle.setUid(response.getUid());
//                positionMarginSettle.setAmount(margin);
//                positionMarginSettleDTOList.add(positionMarginSettle);
//            }
//        }
//        //如果此次结算 扣除了一部分用户的 保证金
//        if (CollectionUtil.isNotEmpty(positionMarginSettleDTOList)) {
//            PositionSideEnum positionSideEnum = isBuySide ? PositionSideEnum.BUY : PositionSideEnum.SELL;
//
//            List<FundingRateSettleMarginItemPO> fundingRateSettleMarginPOList = ProtoBeanUtils
//                    .toProtoBeanList(FundingRateSettleMarginItemPO.getDefaultInstance(), positionMarginSettleDTOList);
//
//            FundingRateSettleMarginPO fundingRateSettleMarginPO = FundingRateSettleMarginPO.newBuilder()
//                    .setSymbol(symbol)
//                    .setSide(positionSideEnum.name())
//                    .addAllFundingRateSettleMarginItemPO(fundingRateSettleMarginPOList)
//                    .build();
//
//            FundingRateSettleMarginRequest request = FundingRateSettleMarginRequest.newBuilder()
//                    .setFundingRateSettleMarginPO(fundingRateSettleMarginPO).build();
//            //组装数据 调用account
//            CoPositionOrderReply coPositionOrderReply = coPositionOrderBizService.fundingRateSettleMargin(request);
//            if (!coPositionOrderReply.getStatus()) {
//                throw new RuntimeException("rpc call fundingRateSettleMargin result status is false");
//            }
//        }
//        return totalDeducted;
//    }

    private void increaseAccount(List<CoPositionOrderDTO> needIncreasePositionList, BigDecimal totalIncreaseAmount
            , String symbol) {
        //已扣减金额
        BigDecimal increased = BigDecimal.ZERO;
        //持仓总金额
        BigDecimal totalNominalAmount = BigDecimal.ZERO;
        for (CoPositionOrderDTO curNeedIncrease : needIncreasePositionList) {
            totalNominalAmount = NumberUtil.add(totalNominalAmount, curNeedIncrease.getNominalValue());
        }
        List<FundingRateSettleAccountDTO> settleDTOList = new ArrayList<>();
        for (int i = 0; i < needIncreasePositionList.size(); i++) {
            //金额都向下取整，然后最后的金额给最后一个用户,这样子刚好把扣得金额全部分配出去
            FundingRateSettleAccountDTO settleDTO = new FundingRateSettleAccountDTO();
            CoPositionOrderDTO curNeedIncrease = needIncreasePositionList.get(i);
            settleDTO.setSymbol(symbol);
            settleDTO.setUid(curNeedIncrease.getUid());
            //最后一个用户，幸运儿，直接得到剩下的金额
            if (i == needIncreasePositionList.size() - 1) {
                settleDTO.setAmount(NumberUtil.sub(totalIncreaseAmount, increased));
            } else {
                //持仓占比比例
                BigDecimal proportion = NumberUtil.div(curNeedIncrease.getNominalValue(), totalNominalAmount
                        , 4, RoundingMode.HALF_DOWN);
                //比例*总的可以结算的金额
                BigDecimal amount = NumberUtil.mul(totalIncreaseAmount, proportion)
                        .setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.HALF_DOWN);
                increased = NumberUtil.add(increased, amount);
                settleDTO.setAmount(amount);
            }
            settleDTOList.add(settleDTO);
        }

        List<UserAccountTransferPO> userAccountTransferPOList = ProtoBeanUtils
                .toProtoBeanList(UserAccountTransferPO.getDefaultInstance(), settleDTOList);

        UserAccountTransferListRequest request = UserAccountTransferListRequest.newBuilder()
                .addAllUserAccountTransferPO(userAccountTransferPOList).build();
        //组装数据 调用account
        AccountReply accountRateAdd = accountBizService.accountRateAdd(request);
        if (!accountRateAdd.getStatus()) {
            throw new RuntimeException("rpc call accountRateAdd result status is false");
        }

    }

    private List<CoPositionOrderDTO> getAllPositionOrder(String symbol, boolean isBuySide) {
        PositionSideEnum positionSideEnum = isBuySide ? PositionSideEnum.BUY : PositionSideEnum.SELL;
        CoPositionOrderPO coPositionOrderPO = CoPositionOrderPO.newBuilder()
                .setStatus(Int32Value.newBuilder().setValue(PositionOrderStatusEnum.position.getValue()).build())
                .setSymbol(symbol)
                .setPositionSide(positionSideEnum.name())
                .build();
        CoPositionOrderPageRequest request = CoPositionOrderPageRequest.newBuilder()
                .setCoPositionOrderPO(coPositionOrderPO)
                .build();
        CoPositionOrderListReply coPositionOrderListReply = coPositionOrderBizService.selectList(request);
        List<CoPositionOrderPO> coPositionOrderPOList = coPositionOrderListReply.getCoPositionOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrderDTO.class, coPositionOrderPOList);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void settleDistributeV2(Date settleTime, TaskContext taskContext) throws InterruptedException {
        Map<Long, BigDecimal> uidSettleAmountMap = calculateUserSettleAmount(settleTime, taskContext);
        if (CollectionUtil.isNotEmpty(uidSettleAmountMap)) {
            //调用account，
            callIncrease(uidSettleAmountMap);
            callDeduct(uidSettleAmountMap, settleTime);
        }
    }


    private Map<Long, BigDecimal> calculateUserSettleAmount(Date settleTime, TaskContext taskContext) {
        //1、把用户 所有交易对的需要 + 和 - 的 资金费率 之和 加起来，然后去调用account操作
        //2、如果某个用户之和是负数 且account 扣除不足，那么account那边就扣除可以扣除的，然后记录还剩下多少没有扣除(当用户有其他加可用余额的时候
        // ，需要先扣除这部分还欠的资金费率，然后写一条资金费率的流水) >判断是否有委托单
        // ，有委托单就去撤销委托单 > 撤销完了再去扣除一次，如果还不够，就走系统平仓逻辑。


        //查询当期所有币队的 资金费率，如果出现一些币队的费率没有，那么就过滤掉，以有当期费率的为准
        List<FundingRate> fundingRate = getCanSettleFundingRate(settleTime);
        if (CollectionUtil.isEmpty(fundingRate)) {
            log.info("funding rate list is empty");
            return null;
        }
        log.info("settle funding rate data:{}", fundingRate);
        List<String> needSettleSymbolList = fundingRate.stream()
                .map(fundingRate1 -> fundingRate1.getSymbol())
                .collect(Collectors.toList());
        //查询所有用户持仓
        List<CoPositionOrderDTO> coPositionOrderDTOList = getAllPositionOrder(needSettleSymbolList);
        if (CollectionUtil.isEmpty(coPositionOrderDTOList)) {
            log.info("user position order is empty");
            return null;
        }
        //把持仓按照交易对分组
        Map<String, List<CoPositionOrderDTO>> grouping = groupingBySymbol(coPositionOrderDTOList);
        //这里直接循环了，等以后量真的很大很大了再做优化，不做提前优化,这块的整体流程都不知道以后会不会修改，
        Map<Long, BigDecimal> uidSettleAmountMap = new HashMap<>();
        for (FundingRate rate : fundingRate) {
            List<CoPositionOrderDTO> curSymbolPositionList = grouping.get(rate.getSymbol());
            if (CollectionUtil.isEmpty(curSymbolPositionList)) {
                continue;
            }
            calculateOneSymbol(uidSettleAmountMap, rate, curSymbolPositionList);
        }
        log.info("calculate User Settle Amount result{}", uidSettleAmountMap);
        return uidSettleAmountMap;
    }


    private void callIncrease(Map<Long, BigDecimal> uidSettleAmountMap) {
        List<UserAccountTransferPO> needIncrease = new ArrayList<>();
        for (Map.Entry<Long, BigDecimal> entry : uidSettleAmountMap.entrySet()) {
            //金额大于0
            if (entry.getValue().compareTo(BigDecimal.ZERO) > 0) {
                UserAccountTransferPO item = UserAccountTransferPO
                        .newBuilder()
                        .setUid(entry.getKey())
                        .setAmount(entry.getValue().toPlainString())
                        .setSymbol("USDT")
                        .build();
                needIncrease.add(item);
            }
        }

        UserAccountTransferListRequest request = UserAccountTransferListRequest.newBuilder()
                .addAllUserAccountTransferPO(needIncrease).build();
        //组装数据 调用account
        AccountReply accountReply = accountBizService.accountRateAdd(request);
        if (!accountReply.getStatus()) {
            log.error("accountRateAdd failed", accountReply.getMessage());
            throw new BizException("accountRateAdd failed" + accountReply.getMessage());
        }
    }

    private void callDeduct(Map<Long, BigDecimal> uidSettleAmountMap, Date settleTime) {
        List<UserAccountTransferPO> needDeduct = new ArrayList<>();
        for (Map.Entry<Long, BigDecimal> entry : uidSettleAmountMap.entrySet()) {
            //金额小于0
            if (entry.getValue().compareTo(BigDecimal.ZERO) < 0) {
                UserAccountTransferPO item = UserAccountTransferPO
                        .newBuilder()
                        .setUid(entry.getKey())
                        .setAmount(entry.getValue().abs().toPlainString()) //这里取绝对值
                        .setSymbol("USDT")
                        .build();
                needDeduct.add(item);
            }
        }

        UserAccountTransferListRequest request = UserAccountTransferListRequest.newBuilder()
                .addAllUserAccountTransferPO(needDeduct).build();
        //组装数据 调用account
        UserAccountReply userAccountReply = accountBizService.accountRateDeduction(request);
        log.info("account Rate Deduction result:{}", userAccountReply);
        List<UserAccountTransferResponse> responseList = userAccountReply.getUserAccountTransferResponseList();
        if (CollectionUtil.isNotEmpty(responseList)) {
            for (UserAccountTransferResponse response : responseList) {
                if (response.getArrears() != null) {
                    BigDecimal arrears = new BigDecimal(response.getArrears());
                    if (arrears.compareTo(BigDecimal.ZERO) > 0) {
                        log.info("uid{} settle arrears {}", response.getUid(), arrears.toPlainString());
                        FundingRateSettleArrearsDTO dto = new FundingRateSettleArrearsDTO();
                        dto.setUid(response.getUid());
                        dto.setArrears(arrears);
                        dto.setSettleTime(settleTime);
                        rocketMQTemplate.convertAndSend(TopicConstant.FUNDING_RATE_SETTLE_ARREARS, JSON.toJSON(dto));
                    }
                }
            }
        }
    }


    private void calculateOneSymbol(Map<Long, BigDecimal> uidSettleAmountMap, FundingRate fundingRate, List<CoPositionOrderDTO> curSymbolPositionList) {
        BigDecimal rate = fundingRate.getRate();
        log.info("{} funding rate is{}", fundingRate.getSymbol(), rate.toPlainString());
        if (rate.compareTo(BigDecimal.ZERO) == 0) {
            log.info("{} funding rate is zero", fundingRate.getSymbol());
            return;
        }
        boolean isBuyToSell = rate.compareTo(BigDecimal.ZERO) > 0;
        BigDecimal absoluteRate = rate.abs();

        Map<PositionSideEnum, List<CoPositionOrderDTO>> splitSidePosition = splitSideFromAll(curSymbolPositionList);

        List<CoPositionOrderDTO> buyPositionList = splitSidePosition.get(PositionSideEnum.BUY);

        List<CoPositionOrderDTO> sellPositionList = splitSidePosition.get(PositionSideEnum.SELL);
        //总扣除金额
        BigDecimal curSymbolTotalDeduct = BigDecimal.ZERO;
        if (isBuyToSell) {
            //多给空，那么扣多的钱。给空头加钱
            curSymbolTotalDeduct = calculateOneSymbolDeduct(buyPositionList, uidSettleAmountMap, absoluteRate);
            calculateIncreaseOneSymbol(sellPositionList, curSymbolTotalDeduct, uidSettleAmountMap);
        } else {
            //空给多，那么扣空的钱。给多头加钱
            curSymbolTotalDeduct = calculateOneSymbolDeduct(sellPositionList, uidSettleAmountMap, absoluteRate);
            calculateIncreaseOneSymbol(buyPositionList, curSymbolTotalDeduct, uidSettleAmountMap);
        }
    }

    private BigDecimal calculateOneSymbolDeduct(List<CoPositionOrderDTO> needDeduct, Map<Long, BigDecimal> uidSettleAmountMap
            , BigDecimal absoluteRate) {
        BigDecimal curSymbolTotalDeduct = BigDecimal.ZERO;
        for (CoPositionOrderDTO deductPosition : needDeduct) {
            BigDecimal positiveAmount = NumberUtil.mul(deductPosition.getNominalValue(), absoluteRate)
                    .setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.HALF_UP);
            //累积扣减金额
            curSymbolTotalDeduct = NumberUtil.add(curSymbolTotalDeduct, positiveAmount);
            //这里要去累积这个用户扣的钱，所以取负数
            userAccumulate(uidSettleAmountMap, deductPosition.getUid(), positiveAmount.negate());
        }
        return curSymbolTotalDeduct;
    }

    private void calculateIncreaseOneSymbol(List<CoPositionOrderDTO> needIncrease, BigDecimal totalIncreaseAmount
            , Map<Long, BigDecimal> uidSettleAmountMap) {
        BigDecimal totalNominalAmount = getOneSymbolTotalNominalAmount(needIncrease);
        //已经累加的发放金额
        BigDecimal increased = BigDecimal.ZERO;
        for (int i = 0; i < needIncrease.size(); i++) {
            CoPositionOrderDTO increasePosition = needIncrease.get(i);
            Long uid = increasePosition.getUid();
            if (i == needIncrease.size() - 1) {
                //最后一个用户，幸运儿，直接得到剩下的金额
                userAccumulate(uidSettleAmountMap, uid, NumberUtil.sub(totalIncreaseAmount, increased));
            } else {
                //持仓占比比例
                BigDecimal proportion = NumberUtil.div(increasePosition.getNominalValue(), totalNominalAmount
                        , 4, RoundingMode.HALF_DOWN);
                //比例*总的可以结算的金额
                BigDecimal amount = NumberUtil.mul(totalIncreaseAmount, proportion)
                        .setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.HALF_DOWN);
                increased = NumberUtil.add(increased, amount);
                userAccumulate(uidSettleAmountMap, uid, amount);
            }
        }
    }


    private BigDecimal getOneSymbolTotalNominalAmount(List<CoPositionOrderDTO> needIncrease) {
        BigDecimal totalNominalAmount = BigDecimal.ZERO;
        for (CoPositionOrderDTO coPositionOrderDTO : needIncrease) {
            totalNominalAmount = NumberUtil.add(totalNominalAmount, coPositionOrderDTO.getNominalValue());
        }
        return totalNominalAmount;
    }


    /**
     * 讲数据 拆分成 多空两个方向
     *
     * @param curSymbolPosition
     * @return
     */
    private Map<PositionSideEnum, List<CoPositionOrderDTO>> splitSideFromAll
    (List<CoPositionOrderDTO> curSymbolPosition) {
        List<CoPositionOrderDTO> buy = new ArrayList<>();
        List<CoPositionOrderDTO> sell = new ArrayList<>();
        for (CoPositionOrderDTO dto : curSymbolPosition) {
            if (PositionSideEnum.BUY.name().equals(dto.getPositionSide())) {
                buy.add(dto);
            } else {
                sell.add(dto);
            }
        }
        Map<PositionSideEnum, List<CoPositionOrderDTO>> result = new HashedMap();
        result.put(PositionSideEnum.BUY, buy);
        result.put(PositionSideEnum.SELL, sell);
        return result;
    }

    private void userAccumulate(Map<Long, BigDecimal> uidSettleAmountMap, Long uid, BigDecimal curAmount) {
        if (uidSettleAmountMap.containsKey(uid)) {
            BigDecimal oldAmount = uidSettleAmountMap.get(uid);
            BigDecimal newAmount = NumberUtil.add(oldAmount, curAmount);
            uidSettleAmountMap.put(uid, newAmount);
        } else {
            uidSettleAmountMap.put(uid, curAmount);
        }
    }


    private Map<String, List<CoPositionOrderDTO>> groupingBySymbol
            (List<CoPositionOrderDTO> coPositionOrderDTOList) {
        Map<String, List<CoPositionOrderDTO>> grouping = new HashedMap();
        for (CoPositionOrderDTO item : coPositionOrderDTOList) {
            String symbol = item.getSymbol();
            if (grouping.containsKey(symbol)) {
                List<CoPositionOrderDTO> existGroup = grouping.get(symbol);
                existGroup.add(item);
            } else {
                List<CoPositionOrderDTO> initGroup = new ArrayList<>();
                initGroup.add(item);
                grouping.put(symbol, initGroup);
            }

        }
        return grouping;
    }

    private List<FundingRate> getCanSettleFundingRate(Date settleTime) {
        List<ContractConfigPO> contractConfigPOList = getAllContractConfig();
        if (CollectionUtil.isEmpty(contractConfigPOList)) {
            log.info("contract config list is empty");
            return null;
        }
        List<String> symbolList = contractConfigPOList
                .stream()
                .map(contractConfigPO -> contractConfigPO.getSymbol())
                .collect(Collectors.toList());

        QueryChainWrapper<FundingRate> queryChainWrapper = super.query();
        queryChainWrapper.in("symbol", symbolList)
                .eq("settle_time", settleTime);
        List<FundingRate> fundingRate = queryChainWrapper.list();
        return fundingRate;
    }

    private List<CoPositionOrderDTO> getAllPositionOrder(List<String> symbolList) {
        FindAllInSymbolListRequest request = FindAllInSymbolListRequest.newBuilder()
                .setStatus(PositionOrderStatusEnum.position.getValue())
                .addAllSymbolList(symbolList)
                .build();
        CoPositionOrderListReply coPositionOrderListReply = coPositionOrderBizService.findAllInSymbolList(request);
        List<CoPositionOrderPO> coPositionOrderPOList = coPositionOrderListReply.getCoPositionOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrderDTO.class, coPositionOrderPOList);
    }


    @Override
    public void fundingForecast(Date settleTime, TaskContext taskContext) {
        Map<Long, BigDecimal> uidSettleAmountMap = calculateUserSettleAmount(settleTime, taskContext);
        if (CollectionUtil.isNotEmpty(uidSettleAmountMap)) {
            for (Map.Entry<Long, BigDecimal> entry : uidSettleAmountMap.entrySet()) {
                BigDecimal settleAmount = entry.getValue();
                if (settleAmount.compareTo(BigDecimal.ZERO) < 0) {
                    Long uid = entry.getKey();
                    AccountDTO accountDTO = accountAction.getByUidAndType(uid
                            , Long.valueOf(AccountTypeEnum.normal.getCode()));
                    if (accountDTO != null && accountDTO.getBalance() != null) {
                        if (NumberUtil.sub(accountDTO.getBalance(), settleAmount.abs()).compareTo(BigDecimal.ZERO) < 0) {
                            sendMsg(entry.getKey());
                        }
                    }

                }
            }
        }
    }

    private void sendMsg(Long uid) {
        //todo send msg
        log.info("todo" + uid);
    }
}
