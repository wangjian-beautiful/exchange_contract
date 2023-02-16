package com.bjs.contract.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.bijinsuo.common.constants.CoOrderConstant;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.CoTriggerOrderDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.domain.SettlementDTO;
import com.bijinsuo.common.utils.DateUtils;
import com.bijinsuo.common.utils.LangUtils;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.OrderStatus;
import com.bijinsuo.common.utils.enums.TriggerStatusEnum;
import com.bijinsuo.common.utils.enums.TriggerTypeEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.controller.request.CurrentOrderRequest;
import com.bjs.contract.controller.request.HistoryOrderRequest;
import com.bjs.contract.controller.response.*;
import com.bjs.contract.proto.coOrder.*;
import com.bjs.contract.proto.coTriggerOrder.CoTriggerOrderBizService;
import com.bjs.contract.proto.coTriggerOrder.CoTriggerOrderListReply;
import com.bjs.contract.proto.coTriggerOrder.CoTriggerOrderPO;
import com.bjs.contract.proto.coTriggerOrder.CoTriggerOrderPageByStatusRequest;
import com.bjs.contract.proto.contractConfig.*;
import com.bjs.contract.proto.settlement.SettlementBizService;
import com.bjs.contract.proto.settlement.SettlementListReply;
import com.bjs.contract.proto.settlement.SettlementPO;
import com.bjs.contract.proto.settlement.SettlementPageRequest;
import com.google.protobuf.Int64Value;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PublicAction {

    @Resource
    private MaintenanceMarginRateAction maintenanceMarginRateAction;
    @DubboReference
    private CoOrderBizService coOrderBizService;
    @DubboReference
    private CoTriggerOrderBizService coTriggerOrderBizService;
    @DubboReference
    private ContractConfigBizService contractConfigBizService;
    @DubboReference
    private SettlementBizService settlementBizService;
    @Resource
    private CoTriggerOrderAction coTriggerOrderAction;

    public PublicInfoResp getPublicInfo() {

        PublicInfoResp publicInfoResp = new PublicInfoResp();
        publicInfoResp.setWsUrl("ws://cows.bjs20.com/ws");
        publicInfoResp.setContractProInfo("https://futuresdoc.gitbook.io/help-center/v/cn/");
        publicInfoResp.setCurrentTimeMillis(new Date().getTime());
        List<String> coinList = new ArrayList<>();
        coinList.add("USDT");
        publicInfoResp.setMarginCoinList(coinList);
        publicInfoResp.setOriginalCoinList(coinList);
        List<LangResp> langRespList = new ArrayList<>();
        langRespList.add(JSON.parseObject(LangUtils.langCn, LangResp.class));
        langRespList.add(JSON.parseObject(LangUtils.langEn, LangResp.class));
        publicInfoResp.setLangList(langRespList);
        ContractConfigListReply listReply = contractConfigBizService.selectAll(ContractConfigPageRequest.newBuilder().build());
        List<ContractConfigPO> poList = listReply.getContractConfigPOList();
        List<ContractResp> contractList = ProtoBeanUtils.toPojoBeanList(ContractResp.class, poList);
        if(CollectionUtils.isNotEmpty(contractList)){
            int count = 1;
            for (ContractResp item:contractList){
                item.setBrokerId(Long.valueOf(1));
                item.setCapitalFrequency(8);
                item.setCapitalStartTime(0);
                item.setClassification(4);
                item.setCoType("E");
                item.setContractShowType("USDT合约");
                item.setContractSide(1);
                item.setContractType("E");
                item.setDeliveryKind("0");
                item.setMarginCoin("USDT");
                item.setMarginRate(1);
                SymbolIntervalResp symbolInterval = maintenanceMarginRateAction.getSymbolInterval(item.getSymbol());
                if (symbolInterval != null) {
                    item.setMinLever(symbolInterval.getMinLeverage());
                    item.setMaxLever(symbolInterval.getMaxLeverage());
                }
                item.setMultiplier(item.getMinOpenBase());
                item.setMultiplierCoin(item.getBase());
                item.setOriginalCoin(item.getQuote());
                item.setSettlementFrequency(1);
                item.setSort(count);
                count += 1;
                CoinResultVoResp coinResultVoResp = new CoinResultVoResp();
                coinResultVoResp.setFundsInStatus(1);
                coinResultVoResp.setFundsOutStatus(1);
                List<String> depth = new ArrayList<>();
//                depth.add("0.1");
                depth.add("1");
                coinResultVoResp.setDepth(depth);
                coinResultVoResp.setMarginCoinPrecision(3);
                coinResultVoResp.setMaxLimitMoney(item.getMaxOpenQuote());
                coinResultVoResp.setMaxLimitVolume(item.getMaxOpenBase());
                coinResultVoResp.setMaxMarketMoney(item.getMaxOpenQuote());
                coinResultVoResp.setMaxMarketVolume(item.getMaxOpenBase());
                coinResultVoResp.setSymbolPricePrecision(item.getPricePrecision());
                coinResultVoResp.setMinOrderMoney(11);
                coinResultVoResp.setMinOrderVolume(1);
                coinResultVoResp.setPriceRange("0.5");
                item.setCoinResultVo(coinResultVoResp);
            }
        }
        publicInfoResp.setContractList(contractList);
        return publicInfoResp;
    }
}
