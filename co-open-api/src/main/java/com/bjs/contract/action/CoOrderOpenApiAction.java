package com.bjs.contract.action;

import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.CoTradeDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.domain.SettlementDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.proto.coOrder.*;
import com.bjs.contract.proto.coTrade.CoTradeBizService;
import com.bjs.contract.proto.coTrade.CoTradeListReply;
import com.bjs.contract.proto.coTrade.CoTradePO;
import com.bjs.contract.proto.coTrade.CoTradePageRequest;
import com.bjs.contract.proto.contractConfig.ContractConfigBizService;
import com.bjs.contract.proto.contractConfig.ContractConfigListReply;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import com.bjs.contract.proto.contractConfig.ContractConfigPageRequest;
import com.bjs.contract.proto.settlement.*;
import com.google.protobuf.Int64Value;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CoOrderOpenApiAction {
    @DubboReference
    private CoOrderBizService coOrderBizService;
    @DubboReference
    private ContractConfigBizService contractConfigBizService;
    @DubboReference
    private SettlementBizService settlementBizService;

    @DubboReference
    private CoTradeBizService coTradeBizService;

    public CoOrderDTO getOrderByIdAndContractName(HttpServletRequest request) {
        CoOrderPO.Builder builder = CoOrderPO.newBuilder();
        builder.setSymbol(request.getParameter("contractName"));
        builder.setId(Int64Value.of(Long.valueOf(request.getParameter("orderId"))));

        CoOrderPageRequest coOrderPageRequest = CoOrderPageRequest.newBuilder().setCoOrderPO(builder.build()).build();
        CoOrderListReply listReply = coOrderBizService.selectList(coOrderPageRequest);
        List<CoOrderPO> poList = listReply.getCoOrderPOList();
        if(CollectionUtils.isNotEmpty(poList)){
            return ProtoBeanUtils.toPojoBeanList(CoOrderDTO.class, poList).get(0);
        }else {
            return null;
        }
    }

    public List<CoOrderDTO> getCurrentOrder(HttpServletRequest request) {
        CoOrderPO.Builder builder = CoOrderPO.newBuilder();
        builder.setSymbol(request.getParameter("contractName"));
        builder.setUid(Int64Value.of(UserContextHolder.user.get().getId()));
        CoOrderPageRequest coOrderPageRequest = CoOrderPageRequest.newBuilder().setCoOrderPO(builder.build()).build();
        CoOrderListReply listReply = coOrderBizService.selectList(coOrderPageRequest);
        List<CoOrderPO> poList = listReply.getCoOrderPOList();
        if(CollectionUtils.isNotEmpty(poList)){
            return ProtoBeanUtils.toPojoBeanList(CoOrderDTO.class, poList);
        }else {
            return null;
        }
    }

    public List<CoOrderDTO> getOrderHistorical(HttpServletRequest request) {
        CoOrderPO.Builder builder = CoOrderPO.newBuilder();
        builder.setSymbol(request.getParameter("contractName"));
        builder.setUid(Int64Value.of(UserContextHolder.user.get().getId()));
        CoOrderPageRequest coOrderPageRequest = CoOrderPageRequest.newBuilder().setCoOrderPO(builder.build()).build();
        CoOrderListReply listReply = coOrderBizService.selectList(coOrderPageRequest);
        List<CoOrderPO> poList = listReply.getCoOrderPOList();
        if(CollectionUtils.isNotEmpty(poList)){
            List<CoOrderDTO> list = ProtoBeanUtils.toPojoBeanList(CoOrderDTO.class, poList);
            if(StringUtils.isNotEmpty(request.getParameter("fromId"))){
                list = list.stream().filter(item -> item.getId() >= Long.valueOf(request.getParameter("fromId"))).collect(Collectors.toList());
            }
            if(StringUtils.isNotEmpty(request.getParameter("limit"))){
                list = list.stream().limit(Long.valueOf(request.getParameter("limit"))).collect(Collectors.toList());
            }
            list = list.stream().sorted(Comparator.comparing(CoOrderDTO::getId)).collect(Collectors.toList());
            return list;
        }else {
            return null;
        }
    }
    public List<CoTradeDTO> getTradeHistorical(HttpServletRequest request) {
        CoTradePO.Builder po = CoTradePO.newBuilder();
        po.setSymbol(request.getParameter("contractName"));
        po.setUid(Int64Value.of(UserContextHolder.user.get().getId()));
        CoTradePageRequest.Builder builder = CoTradePageRequest.newBuilder();
        if(StringUtils.isNotEmpty(request.getParameter("limit"))){
            String limit = request.getParameter("limit");
            builder.setSize(Integer.parseInt(limit));
        }
        CoTradePageRequest pageRequest = builder.setCoTradePO(po.build()).build();
        CoTradeListReply listReply = coTradeBizService.selectList(pageRequest);
        List<CoTradePO> poList = listReply.getCoTradePOList();
        if(CollectionUtils.isNotEmpty(poList)){
            List<CoTradeDTO> list = ProtoBeanUtils.toPojoBeanList(CoTradeDTO.class, poList);
            if(StringUtils.isNotEmpty(request.getParameter("fromId"))){
                list = list.stream().filter(item -> item.getId() >= Long.valueOf(request.getParameter("fromId"))).collect(Collectors.toList());
            }
            list = list.stream().sorted(Comparator.comparing(CoTradeDTO::getId)).collect(Collectors.toList());
            return list;
        }else {
            return null;
        }
    }
    public List<SettlementDTO> getSettlement(HttpServletRequest request) {
        SettlementPO.Builder po = SettlementPO.newBuilder();
        po.setSymbol(request.getParameter("contractName"));
        po.setUid(Int64Value.of(UserContextHolder.user.get().getId()));
        SettlementPageRequest.Builder builder = SettlementPageRequest.newBuilder();
        if(StringUtils.isNotEmpty(request.getParameter("limit"))){
            String limit = request.getParameter("limit");
            builder.setSize(Integer.parseInt(limit));
        }
        SettlementPageRequest pageRequest = builder.setSettlementPO(po.build()).build();
        SettlementListReply listReply = settlementBizService.selectList(pageRequest);
        List<SettlementPO> poList = listReply.getSettlementPOList();
        if(CollectionUtils.isNotEmpty(poList)){
            List<SettlementDTO> list = ProtoBeanUtils.toPojoBeanList(SettlementDTO.class, poList);
            if(StringUtils.isNotEmpty(request.getParameter("fromId"))){
                list = list.stream().filter(item -> item.getId() >= Long.valueOf(request.getParameter("fromId"))).collect(Collectors.toList());
            }
            list = list.stream().sorted(Comparator.comparing(SettlementDTO::getId)).collect(Collectors.toList());
            return list;
        }else {
            return null;
        }
    }



    public CoOrderDTO create(CoOrderDTO dto) {
        final var request = CoOrderRequest.newBuilder().
                setCoOrderPO(ProtoBeanUtils.toProtoBean(CoOrderPO.getDefaultInstance(), dto))
                .build();
        CoOrderReply reply = coOrderBizService.create(request);
        if (!reply.getStatus()) {
            throw new BizException(reply.getMessage(), reply.getMessage());
        }
        return ProtoBeanUtils.toPojoBean(CoOrderDTO.class,reply.getCoOrderPO());
    }

    public CoOrderDTO close(CoOrderDTO dto) {
        final var request = CoOrderRequest.newBuilder().
            setCoOrderPO(ProtoBeanUtils.toProtoBean(CoOrderPO.getDefaultInstance(), dto))
            .build();
        CoOrderReply reply = coOrderBizService.close(request);
        if (!reply.getStatus()) {
            throw new BizException(reply.getMessage(), reply.getMessage());
        }
        return ProtoBeanUtils.toPojoBean(CoOrderDTO.class,reply.getCoOrderPO());
    }

    public CoOrderReply cancelOrder(Long orderId) {
        return coOrderBizService.cancelOrder(CoOrderRequest.newBuilder().setId(orderId).build());
    }

    public CoOrderReply cancelUserOrdersNotFilled (Long uid) {
        return coOrderBizService.cancelUserOrdersNotFilled(CoOrderCancelRequest.newBuilder().setUid(uid).build());
    }

    public List<ContractConfigDTO> contracts() {
        ContractConfigPageRequest request = ContractConfigPageRequest.newBuilder().build();
        ContractConfigListReply listReply = contractConfigBizService.selectAll(request);
        List<ContractConfigPO> poList = listReply.getContractConfigPOList();
        return ProtoBeanUtils.toPojoBeanList(ContractConfigDTO.class, poList);
    }
}
