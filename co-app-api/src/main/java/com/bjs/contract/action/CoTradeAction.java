package com.bjs.contract.action;

import com.bijinsuo.common.domain.CoTradeDTO;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.DubboReference;
import com.bjs.contract.proto.coTrade.CoTradePO;
import com.bjs.contract.proto.coTrade.CoTradeBizService;
import com.bjs.contract.proto.coTrade.CoTradeRequest;
import com.bjs.contract.proto.coTrade.CoTradePageRequest;
import com.bjs.contract.proto.coTrade.CoTradeIdsRequest;
import com.bjs.contract.proto.coTrade.CoTradeListRequest;
import com.bjs.contract.proto.coTrade.CoTradeReply;
import com.bjs.contract.proto.coTrade.CoTradeListReply;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoTradeAction {

    @DubboReference
    private CoTradeBizService coTradeBizService;

    @SneakyThrows
    public CoTradeDTO getById (Long id) {
        CoTradeRequest request = CoTradeRequest.newBuilder().setId(id).build();
        CoTradePO po = coTradeBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(CoTradeDTO.class, po);
    }

    @SneakyThrows
    public List<CoTradeDTO> selectAll () {
        CoTradePageRequest request = CoTradePageRequest.newBuilder().build();
        CoTradeListReply listReply = coTradeBizService.selectAll(request);
        List<CoTradePO> poList = listReply.getCoTradePOList();
        return ProtoBeanUtils.toPojoBeanList(CoTradeDTO.class, poList);
    }

    @SneakyThrows
    public List<CoTradeDTO> selectAllByPage (int page, int size) {
            CoTradePageRequest request = CoTradePageRequest.newBuilder().setPage(page).setSize(size).build();
            CoTradeListReply listReply = coTradeBizService.selectAll(request);
        List<CoTradePO> poList = listReply.getCoTradePOList();
        return ProtoBeanUtils.toPojoBeanList(CoTradeDTO.class, poList);
    }

    @SneakyThrows
    public List<CoTradeDTO> selectList (CoTradeDTO entity) {
        CoTradePO po = entity2po(entity);
        CoTradePageRequest request = CoTradePageRequest.newBuilder().setCoTradePO(po).build();
        CoTradeListReply listReply = coTradeBizService.selectList(request);
        List<CoTradePO> poList = listReply.getCoTradePOList();
        return ProtoBeanUtils.toPojoBeanList(CoTradeDTO.class, poList);
    }

    @SneakyThrows
    public List<CoTradeDTO> selectListByPage (CoTradeDTO entity, int page, int size) {
        CoTradePO po = entity2po(entity);
        CoTradePageRequest request = CoTradePageRequest.newBuilder().setPage(page).setSize(size).setCoTradePO(po).build();
        CoTradeListReply listReply = coTradeBizService.selectList(request);
        List<CoTradePO> poList = listReply.getCoTradePOList();
        return ProtoBeanUtils.toPojoBeanList(CoTradeDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne (CoTradeDTO entity) {
        CoTradePO po = entity2po(entity);
        CoTradeRequest request = CoTradeRequest.newBuilder().setCoTradePO(po).build();
        CoTradeReply reply = coTradeBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            CoTradePO resultPO = reply.getCoTradePO();
            CoTradeDTO result = ProtoBeanUtils.toPojoBean(CoTradeDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<CoTradeDTO> insertBatch (List<CoTradeDTO> entityList) {
        List<CoTradePO> poList = ProtoBeanUtils.toProtoBeanList(CoTradePO.getDefaultInstance(), entityList);
        CoTradeListRequest request = CoTradeListRequest.newBuilder()
            .addAllCoTradePO(poList)
            .build();
        CoTradeListReply listReply = coTradeBizService.insertBatch(request);
        List<CoTradePO> reultList = listReply.getCoTradePOList();
        return ProtoBeanUtils.toPojoBeanList(CoTradeDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById (CoTradeDTO entity) {
        CoTradePO po = entity2po(entity);
        CoTradeRequest request = CoTradeRequest.newBuilder().setCoTradePO(po).build();
        CoTradeReply reply = coTradeBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch (List<CoTradeDTO> entityList) {
        List<CoTradePO> poList = ProtoBeanUtils.toProtoBeanList(CoTradePO.getDefaultInstance(), entityList);
        CoTradeListRequest request = CoTradeListRequest.newBuilder()
            .addAllCoTradePO(poList)
            .build();
        CoTradeReply reply = coTradeBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById (CoTradeDTO entity) {
        CoTradePO po = entity2po(entity);
        CoTradeRequest request = CoTradeRequest.newBuilder().setId(entity.getId()).build();
        CoTradeReply reply = coTradeBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll (List<Long> ids) {
        CoTradeIdsRequest request = CoTradeIdsRequest.newBuilder().addAllId(ids).build();
        CoTradeReply reply = coTradeBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private CoTradePO entity2po (CoTradeDTO entity) {
            CoTradePO.Builder builder = CoTradePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }
}
