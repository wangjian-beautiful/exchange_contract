package com.bjs.contract.action;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.bijinsuo.common.domain.CoTradeDTO;
import com.bijinsuo.common.domain.SettlementDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.controller.request.HistorySettlementRequest;
import com.bjs.contract.controller.request.OrderTradeRequest;
import com.bjs.contract.proto.coTrade.CoTradeBizService;
import com.bjs.contract.proto.coTrade.CoTradeListReply;
import com.bjs.contract.proto.coTrade.CoTradePO;
import com.bjs.contract.proto.coTrade.CoTradePageRequest;
import com.bjs.contract.proto.settlement.SettlementBizService;
import com.bjs.contract.proto.settlement.SettlementListReply;
import com.bjs.contract.proto.settlement.SettlementPO;
import com.bjs.contract.proto.settlement.SettlementPageRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoOrderTradeAction {

    @DubboReference
    private CoTradeBizService coTradeBizService;
    @DubboReference
    private SettlementBizService settlementBizService;

    public List<CoTradeDTO> selectByUid(OrderTradeRequest request, Long uid) {
        Integer page = request.getPage();
        Integer limit = request.getLimit();
        CoTradeDTO coTradeDTO = new CoTradeDTO();
        coTradeDTO.setUid(uid);
        CoTradePO.Builder builder = CoTradePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, coTradeDTO);
        CoTradePageRequest build = CoTradePageRequest.newBuilder().setCoTradePO(builder.build()).setPage(page).setSize(limit).build();
        CoTradeListReply listReply = coTradeBizService.selectAll(build);
        List<CoTradePO> poList = listReply.getCoTradePOList();
        return ProtoBeanUtils.toPojoBeanList(CoTradeDTO.class, poList);
    }

    public List<SettlementDTO> getSettlement(HistorySettlementRequest request) {
        SettlementDTO paramDTO = new SettlementDTO();
        paramDTO.setUid(request.getUid());
        paramDTO.setSymbol(request.getSymbol());
        SettlementPageRequest.Builder builder = SettlementPageRequest.newBuilder();
        if(request.getLimit() != null){
            builder.setSize(request.getLimit());
        }
        if(request.getPage() != null){
            builder.setPage(request.getPage());
        }
        SettlementPageRequest pageRequest = builder.setSettlementPO(entity2po(paramDTO)).build();
        SettlementListReply listReply = settlementBizService.selectList(pageRequest);
        List<SettlementPO> poList = listReply.getSettlementPOList();
        if(CollectionUtils.isNotEmpty(poList)){
            List<SettlementDTO> list = ProtoBeanUtils.toPojoBeanList(SettlementDTO.class, poList);
            if(request.getFromId()!= null){
                list = list.stream().filter(item -> item.getId() >= Long.valueOf(request.getFromId())).collect(Collectors.toList());
            }
            list = list.stream().sorted(Comparator.comparing(SettlementDTO::getId)).collect(Collectors.toList());
            return list;
        }else {
            return null;
        }
    }

    private SettlementPO entity2po (SettlementDTO entity) {
        SettlementPO.Builder builder = SettlementPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

}
