package com.bjs.contract.action;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.bijinsuo.common.constants.CoOrderConstant;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.CoTriggerOrderDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.domain.SettlementDTO;
import com.bijinsuo.common.utils.BizExceptionUtil;
import com.bijinsuo.common.utils.DateUtils;
import com.bijinsuo.common.utils.ProtoBeanUtils;
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
import com.bjs.contract.proto.contractConfig.ContractConfigBizService;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import com.bjs.contract.proto.contractConfig.ContractConfigRequest;
import com.bjs.contract.proto.contractConfig.ContractConfigSymbolRequest;
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
import java.util.List;

@Component
public class CoOrderAction {
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

  public CoOrderDTO create(CoOrderDTO dto) {
    final var request = CoOrderRequest.newBuilder().
        setCoOrderPO(ProtoBeanUtils.toProtoBean(CoOrderPO.getDefaultInstance(), dto))
        .build();
    CoOrderReply reply = coOrderBizService.create(request);
    if (!reply.getStatus()) {
        throw BizExceptionUtil.createFromErrorCodeWithArgs(reply.getMessage());
    }
    return ProtoBeanUtils.toPojoBean(CoOrderDTO.class,reply.getCoOrderPO());
  }

  public CoOrderDTO close(CoOrderDTO dto) {
    final var request = CoOrderRequest.newBuilder().
        setCoOrderPO(ProtoBeanUtils.toProtoBean(CoOrderPO.getDefaultInstance(), dto))
        .build();
    CoOrderReply reply = coOrderBizService.close(request);
    if (!reply.getStatus()) {
        throw BizExceptionUtil.createFromErrorCodeWithArgs(reply.getMessage());
    }
    return ProtoBeanUtils.toPojoBean(CoOrderDTO.class, reply.getCoOrderPO());
  }

  @SneakyThrows
    public CoOrderDTO getById (Long id) {
        CoOrderRequest request = CoOrderRequest.newBuilder().setId(id).build();
        CoOrderPO po = coOrderBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(CoOrderDTO.class, po);
    }

    @SneakyThrows
    public List<CoOrderDTO> selectAll () {
        CoOrderPageRequest request = CoOrderPageRequest.newBuilder().build();
        CoOrderListReply listReply = coOrderBizService.selectAll(request);
        List<CoOrderPO> poList = listReply.getCoOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoOrderDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne (CoOrderDTO entity) {
        CoOrderPO po = entity2po(entity);
        CoOrderRequest request = CoOrderRequest.newBuilder().setCoOrderPO(po).build();
        CoOrderReply reply = coOrderBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            CoOrderPO resultPO = reply.getCoOrderPO();
            CoOrderDTO result = ProtoBeanUtils.toPojoBean(CoOrderDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<CoOrderDTO> insertBatch (List<CoOrderDTO> entityList) {
        List<CoOrderPO> poList = ProtoBeanUtils.toProtoBeanList(CoOrderPO.getDefaultInstance(), entityList);
        CoOrderListRequest request = CoOrderListRequest.newBuilder()
            .addAllCoOrderPO(poList)
            .build();
        CoOrderListReply listReply = coOrderBizService.insertBatch(request);
        List<CoOrderPO> reultList = listReply.getCoOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoOrderDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById (CoOrderDTO entity) {
        CoOrderPO po = entity2po(entity);
        CoOrderRequest request = CoOrderRequest.newBuilder().setCoOrderPO(po).build();
        CoOrderReply reply = coOrderBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch (List<CoOrderDTO> entityList) {
        List<CoOrderPO> poList = ProtoBeanUtils.toProtoBeanList(CoOrderPO.getDefaultInstance(), entityList);
        CoOrderListRequest request = CoOrderListRequest.newBuilder()
            .addAllCoOrderPO(poList)
            .build();
        CoOrderReply reply = coOrderBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById (CoOrderDTO entity) {
        CoOrderPO po = entity2po(entity);
        CoOrderRequest request = CoOrderRequest.newBuilder().setId(entity.getId()).build();
        CoOrderReply reply = coOrderBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll (List<Long> ids) {
        CoOrderIdsRequest request = CoOrderIdsRequest.newBuilder().addAllId(ids).build();
        CoOrderReply reply = coOrderBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private CoOrderPO entity2po (CoOrderDTO entity) {
            CoOrderPO.Builder builder = CoOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    public CoOrderReply cancelOrder(Long orderId) {
        return coOrderBizService.cancelOrder(CoOrderRequest.newBuilder().setId(orderId).build());
    }

    public CoOrderReply cancelUserOrdersNotFilled (Long uid) {
        return coOrderBizService.cancelUserOrdersNotFilled(CoOrderCancelRequest.newBuilder().setUid(uid).build());
    }

    @SneakyThrows
    public UserOrderCountResp getUserOrderCount(Long orderId) {
        CoUserOrderCountReply coUserOrderCountReply = coOrderBizService.getUserOrderCount(CoUserOrderRequest.newBuilder().setId(orderId).build());
        UserOrderCountResp userOrderCountResp = new UserOrderCountResp();
        userOrderCountResp.setOrderCount(coUserOrderCountReply.getOrderCount());
        userOrderCountResp.setTriggerOrderCount(coUserOrderCountReply.getTriggerOrderCount());
        return userOrderCountResp;
    }

    public CoCurrentOrderResp getCurrentOrderList(CurrentOrderRequest currentOrderRequest) {
        if(currentOrderRequest.getType() == null){
            currentOrderRequest.setType(CoOrderConstant.NO_VALUE_FLAG);
        }
        CoOrderPageByStatusRequest.Builder builder = CoOrderPageByStatusRequest.newBuilder();
        CoOrderPO.Builder builderPO = CoOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builderPO, currentOrderRequest);
        if(currentOrderRequest.getContractId() !=null){
            ContractConfigPO contractConfigPO = contractConfigBizService.getById(ContractConfigRequest.newBuilder().setId(currentOrderRequest.getContractId()).build());
            ContractConfigDTO contractConfigDTO = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, contractConfigPO);
            builderPO.setSymbol(contractConfigDTO.getSymbol());
        }
        if(currentOrderRequest.getLimit() !=null && currentOrderRequest.getPage() !=null){
            builder.setLimit(currentOrderRequest.getLimit());
            builder.setPage(currentOrderRequest.getPage());
        }
        builder.setCoOrderPO(builderPO.build());
        builder.addStatus(OrderStatus.INIT.getValue());
        builder.addStatus(OrderStatus.NEW_.getValue());
        builder.addStatus(OrderStatus.PART_FILLED.getValue());
        CoOrderListReply listReply = coOrderBizService.selectListByStatus(builder.build());
        List<CoOrderPO> poList = listReply.getCoOrderPOList();

        List<CoCurrentOrderListResp> coCurrentOrderListResps = ProtoBeanUtils.toPojoBeanList(CoCurrentOrderListResp.class, poList);
        CoCurrentOrderResp resp = new CoCurrentOrderResp();
        resp.setCount(listReply.getTotal());
        if(CollectionUtils.isNotEmpty(coCurrentOrderListResps)){
            for (CoCurrentOrderListResp item:coCurrentOrderListResps){
                ContractConfigPO contractConfigPO = contractConfigBizService.getBySymbol(ContractConfigSymbolRequest.newBuilder().setSymbol(item.getSymbol()).build());
                ContractConfigDTO contractConfigDTO = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, contractConfigPO);
                item.setContractId(contractConfigDTO.getId());
                item.setContractName(contractConfigDTO.getContractName());
                item.setContractOtherName(contractConfigDTO.getContractOtherName());
                CoTriggerOrderDTO entity = new CoTriggerOrderDTO();
                entity.setMasterId(item.getId());
                List<CoTriggerOrderDTO> coTriggerOrderDTOList = coTriggerOrderAction.selectList(entity);
                if(CollectionUtils.isNotEmpty(coTriggerOrderDTOList)){
                    CoOtoOrderResp coOtoOrderResp = new CoOtoOrderResp();
                    for (CoTriggerOrderDTO coTriggerOrderDTO:coTriggerOrderDTOList){
                        if(coTriggerOrderDTO.getTriggerType() == TriggerTypeEnum.STOP_LOSS.getCode()){
                            //止损单
                            coOtoOrderResp.setStopLossPrice(coTriggerOrderDTO.getPrice().toString());
                            if(coTriggerOrderDTO.getStatus() == TriggerStatusEnum.FINISH.getCode()){
                                coOtoOrderResp.setStopLossStatus(true);
                            }else {
                                coOtoOrderResp.setStopLossStatus(false);
                            }
                            coOtoOrderResp.setStopLossTrigger(coTriggerOrderDTO.getTriggerPrice().toString());
                        }else if(coTriggerOrderDTO.getTriggerType() == TriggerTypeEnum.TAKE_PROFIT.getCode()){
                            //止盈单
                            coOtoOrderResp.setTakerProfitPrice(coTriggerOrderDTO.getPrice().toString());
                            if(coTriggerOrderDTO.getStatus() == TriggerStatusEnum.FINISH.getCode()){
                                coOtoOrderResp.setTakerProfitStatus(true);
                            }else {
                                coOtoOrderResp.setTakerProfitStatus(false);
                            }
                            coOtoOrderResp.setTakerProfitTrigger(coTriggerOrderDTO.getTriggerPrice().toString());
                        }
                    }
                    item.setOtoOrder(coOtoOrderResp);
                }
                item.setAvgPrice(new BigDecimal(item.getAvgPrice()).setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setPrice(new BigDecimal(item.getPrice()).setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setDealVolume(item.getDealQuote().setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setVolume(item.getVolumeQuote().setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setCtime(String.valueOf(DateUtils.parseUTC(item.getCtime()).getTime()));
                item.setMtime(String.valueOf(DateUtils.parseUTC(item.getMtime()).getTime()));
            }
        }
        resp.setOrderList(coCurrentOrderListResps);
        return resp;
    }

    public TriggerOrderResp getTriggerOrderList(CurrentOrderRequest currentOrderRequest,Boolean statusFlag) {

        if(currentOrderRequest.getType() == null){
            currentOrderRequest.setType(CoOrderConstant.NO_VALUE_FLAG);
        }
        CoTriggerOrderPageByStatusRequest.Builder builder = CoTriggerOrderPageByStatusRequest.newBuilder();
        CoTriggerOrderPO.Builder builderPO = CoTriggerOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builderPO, currentOrderRequest);
        if(currentOrderRequest.getContractId() !=null){
            ContractConfigPO contractConfigPO = contractConfigBizService.getById(ContractConfigRequest.newBuilder().setId(currentOrderRequest.getContractId()).build());
            ContractConfigDTO contractConfigDTO = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, contractConfigPO);
            builder.setCoTriggerOrderPO(CoTriggerOrderPO.newBuilder().setSymbol(contractConfigDTO.getSymbol()).build());
        }
        if(statusFlag){
            //历史条件委托
            builder.addStatus(TriggerStatusEnum.CANCELED.getCode());
            builder.addStatus(TriggerStatusEnum.FINISH.getCode());
        }else {
            //条件委托
            builder.addStatus(TriggerStatusEnum.ACTIVE.getCode());
        }

        if(currentOrderRequest.getLimit() !=null && currentOrderRequest.getPage() !=null){
            builder.setLimit(currentOrderRequest.getLimit());
            builder.setPage(currentOrderRequest.getPage());
        }
        builder.setCoTriggerOrderPO(builderPO.build());

        CoTriggerOrderListReply listReply = coTriggerOrderBizService.selectListByStatus(builder.build());
        List<CoTriggerOrderPO> poList = listReply.getCoTriggerOrderPOList();

        List<CoTriggerOrderListResp> coTriggerOrderListResps = ProtoBeanUtils.toPojoBeanList(CoTriggerOrderListResp.class, poList);

        TriggerOrderResp resp = new TriggerOrderResp();
        resp.setCount(listReply.getTotal());
        if(CollectionUtils.isNotEmpty(coTriggerOrderListResps)){
            for (CoTriggerOrderListResp item:coTriggerOrderListResps){
                ContractConfigPO contractConfigPO = contractConfigBizService.getBySymbol(ContractConfigSymbolRequest.newBuilder().setSymbol(item.getSymbol()).build());
                ContractConfigDTO contractConfigDTO = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, contractConfigPO);
                item.setContractId(contractConfigDTO.getId());
                item.setContractName(contractConfigDTO.getContractName());
                item.setContractOtherName(contractConfigDTO.getContractOtherName());
                item.setPrice(new BigDecimal(item.getPrice()).setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setVolume(new BigDecimal(item.getVolume()).setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setCtime(String.valueOf(DateUtils.parseUTC(item.getCtime()).getTime()));
                item.setTriggerPrice(new BigDecimal(item.getTriggerPrice()).setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
//                item.setTriggerTime(String.valueOf(DateUtils.parseUTC(item.getTriggerTime()).getTime()));
                item.setMtime(String.valueOf(DateUtils.parseUTC(item.getMtime()).getTime()));
            }
        }
        resp.setTrigOrderList(coTriggerOrderListResps);
        return resp;
    }

    public HistoryOrderResp getHistoryOrderList(HistoryOrderRequest coHistoryOrderRequest) {

        if(coHistoryOrderRequest.getType() == null){
            coHistoryOrderRequest.setType(CoOrderConstant.NO_VALUE_FLAG);
        }
        CoOrderPageByStatusRequest.Builder builder = CoOrderPageByStatusRequest.newBuilder();
        CoOrderPO.Builder builderPO = CoOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builderPO, coHistoryOrderRequest);
        if(coHistoryOrderRequest.getContractId() !=null){
            ContractConfigPO contractConfigPO = contractConfigBizService.getById(ContractConfigRequest.newBuilder().setId(coHistoryOrderRequest.getContractId()).build());
            ContractConfigDTO contractConfigDTO = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, contractConfigPO);
            builder.setCoOrderPO(CoOrderPO.newBuilder().setSymbol(contractConfigDTO.getSymbol()).build());
        }
        if(coHistoryOrderRequest.getLimit() !=null && coHistoryOrderRequest.getPage() !=null){
            builder.setLimit(coHistoryOrderRequest.getLimit());
            builder.setPage(coHistoryOrderRequest.getPage());
        }
        builder.setCoOrderPO(builderPO.build());
        builder.addStatus(OrderStatus.FILLED.getValue());
        builder.addStatus(OrderStatus.CANCELED.getValue());
        CoOrderListReply listReply = coOrderBizService.selectListByStatus(builder.build());
        List<CoOrderPO> poList = listReply.getCoOrderPOList();

        List<HistoryOrderRespListResp> historyOrderRespListResps = ProtoBeanUtils.toPojoBeanList(HistoryOrderRespListResp.class, poList);
        HistoryOrderResp resp = new HistoryOrderResp();
        resp.setCount(listReply.getTotal());
        if(CollectionUtils.isNotEmpty(historyOrderRespListResps)){
            for (HistoryOrderRespListResp item:historyOrderRespListResps){
                ContractConfigPO contractConfigPO = contractConfigBizService.getBySymbol(ContractConfigSymbolRequest.newBuilder().setSymbol(item.getSymbol()).build());
                ContractConfigDTO contractConfigDTO = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, contractConfigPO);
                item.setContractId(contractConfigDTO.getId());
                item.setContractName(contractConfigDTO.getContractName());
                item.setContractOtherName(contractConfigDTO.getContractOtherName());
                CoTriggerOrderDTO entity = new CoTriggerOrderDTO();
                entity.setMasterId(item.getId());
                List<CoTriggerOrderDTO> coTriggerOrderDTOList = coTriggerOrderAction.selectList(entity);
                if(CollectionUtils.isNotEmpty(coTriggerOrderDTOList)){
                    CoOtoOrderResp coOtoOrderResp = new CoOtoOrderResp();
                    for (CoTriggerOrderDTO coTriggerOrderDTO:coTriggerOrderDTOList){
                        if(coTriggerOrderDTO.getTriggerType() == TriggerTypeEnum.STOP_LOSS.getCode()){
                            //止损单
                            coOtoOrderResp.setStopLossPrice(coTriggerOrderDTO.getPrice().toString());
                            if(coTriggerOrderDTO.getStatus() == TriggerStatusEnum.FINISH.getCode()){
                                coOtoOrderResp.setStopLossStatus(true);
                            }else {
                                coOtoOrderResp.setStopLossStatus(false);
                            }
                            coOtoOrderResp.setStopLossTrigger(coTriggerOrderDTO.getTriggerPrice().toString());
                        }else if(coTriggerOrderDTO.getTriggerType() == TriggerTypeEnum.TAKE_PROFIT.getCode()){
                            //止盈单
                            coOtoOrderResp.setTakerProfitPrice(coTriggerOrderDTO.getPrice().toString());
                            if(coTriggerOrderDTO.getStatus() == TriggerStatusEnum.FINISH.getCode()){
                                coOtoOrderResp.setTakerProfitStatus(true);
                            }else {
                                coOtoOrderResp.setTakerProfitStatus(false);
                            }
                            coOtoOrderResp.setTakerProfitTrigger(coTriggerOrderDTO.getTriggerPrice().toString());
                        }
                    }
                    item.setOtoOrder(coOtoOrderResp);
                }

                //手续费 盈亏
                SettlementListReply settlementListReply = settlementBizService.selectList(SettlementPageRequest.newBuilder()
                        .setSettlementPO(SettlementPO.newBuilder().setCoverOrderId(Int64Value.of(Long.valueOf(item.getId()))).build()).build());
                List<SettlementDTO> settlementDTOList = ProtoBeanUtils.toPojoBeanList(SettlementDTO.class, settlementListReply.getSettlementPOList());
                BigDecimal totalProfit = BigDecimal.ZERO;
                BigDecimal totalFee = BigDecimal.ZERO;
                if(CollectionUtils.isNotEmpty(settlementDTOList)){
                     totalProfit = settlementDTOList.stream().map(SettlementDTO::getProfit).reduce(BigDecimal::add).get();
                     totalFee = settlementDTOList.stream().map(SettlementDTO::getFee).reduce(BigDecimal::add).get();
                }
                item.setRealizedAmount(totalProfit.toPlainString());
                item.setTradeFee(totalFee.toPlainString());
                item.setAvgPrice(new BigDecimal(item.getAvgPrice()).setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setPrice(new BigDecimal(item.getPrice()).setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setDealVolume(item.getDealQuote().setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setVolume(item.getVolumeQuote().setScale(contractConfigDTO.getPricePrecision(), RoundingMode.HALF_DOWN).toPlainString());
                item.setCtime(String.valueOf(DateUtils.parseUTC(item.getCtime()).getTime()));
                item.setMtime(String.valueOf(DateUtils.parseUTC(item.getMtime()).getTime()));
            }
        }
        resp.setOrderList(historyOrderRespListResps);
        return resp;
    }
}
