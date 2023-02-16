package com.bjs.contract.action;

import com.bijinsuo.common.domain.CoPositionOrderDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.proto.coPositionOrder.*;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoPositionOrderAction {

    @DubboReference
    private CoPositionOrderBizService coPositionOrderBizService;

    @SneakyThrows
    public CoPositionOrderDTO getById (Long id) {
        CoPositionOrderRequest request = CoPositionOrderRequest.newBuilder().setId(id).build();
        CoPositionOrderPO po = coPositionOrderBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(CoPositionOrderDTO.class, po);
    }

    @SneakyThrows
    public List<CoPositionOrderDTO> selectAll () {
        CoPositionOrderPageRequest request = CoPositionOrderPageRequest.newBuilder().build();
        CoPositionOrderListReply listReply = coPositionOrderBizService.selectAll(request);
        List<CoPositionOrderPO> poList = listReply.getCoPositionOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrderDTO.class, poList);
    }

    @SneakyThrows
    public List<CoPositionOrderDTO> selectAllByPage (int page, int size) {
            CoPositionOrderPageRequest request = CoPositionOrderPageRequest.newBuilder().setPage(page).setSize(size).build();
            CoPositionOrderListReply listReply = coPositionOrderBizService.selectAll(request);
        List<CoPositionOrderPO> poList = listReply.getCoPositionOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrderDTO.class, poList);
    }

    @SneakyThrows
    public List<CoPositionOrderDTO> selectList (CoPositionOrderDTO entity) {
        CoPositionOrderPO po = entity2po(entity);
        CoPositionOrderPageRequest request = CoPositionOrderPageRequest.newBuilder().setCoPositionOrderPO(po).build();
        CoPositionOrderListReply listReply = coPositionOrderBizService.selectList(request);
        List<CoPositionOrderPO> poList = listReply.getCoPositionOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrderDTO.class, poList);
    }

    @SneakyThrows
    public List<CoPositionOrderDTO> selectListByPage (CoPositionOrderDTO entity, int page, int size) {
        CoPositionOrderPO po = entity2po(entity);
        CoPositionOrderPageRequest request = CoPositionOrderPageRequest.newBuilder().setPage(page).setSize(size).setCoPositionOrderPO(po).build();
        CoPositionOrderListReply listReply = coPositionOrderBizService.selectList(request);
        List<CoPositionOrderPO> poList = listReply.getCoPositionOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrderDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne (CoPositionOrderDTO entity) {
        CoPositionOrderPO po = entity2po(entity);
        CoPositionOrderRequest request = CoPositionOrderRequest.newBuilder().setCoPositionOrderPO(po).build();
        CoPositionOrderReply reply = coPositionOrderBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            CoPositionOrderPO resultPO = reply.getCoPositionOrderPO();
            CoPositionOrderDTO result = ProtoBeanUtils.toPojoBean(CoPositionOrderDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<CoPositionOrderDTO> insertBatch (List<CoPositionOrderDTO> entityList) {
        List<CoPositionOrderPO> poList = ProtoBeanUtils.toProtoBeanList(CoPositionOrderPO.getDefaultInstance(), entityList);
        CoPositionOrderListRequest request = CoPositionOrderListRequest.newBuilder()
            .addAllCoPositionOrderPO(poList)
            .build();
        CoPositionOrderListReply listReply = coPositionOrderBizService.insertBatch(request);
        List<CoPositionOrderPO> reultList = listReply.getCoPositionOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrderDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById (CoPositionOrderDTO entity) {
        CoPositionOrderPO po = entity2po(entity);
        CoPositionOrderRequest request = CoPositionOrderRequest.newBuilder().setCoPositionOrderPO(po).build();
        CoPositionOrderReply reply = coPositionOrderBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch (List<CoPositionOrderDTO> entityList) {
        List<CoPositionOrderPO> poList = ProtoBeanUtils.toProtoBeanList(CoPositionOrderPO.getDefaultInstance(), entityList);
        CoPositionOrderListRequest request = CoPositionOrderListRequest.newBuilder()
            .addAllCoPositionOrderPO(poList)
            .build();
        CoPositionOrderReply reply = coPositionOrderBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById (CoPositionOrderDTO entity) {
        CoPositionOrderPO po = entity2po(entity);
        CoPositionOrderRequest request = CoPositionOrderRequest.newBuilder().setId(entity.getId()).build();
        CoPositionOrderReply reply = coPositionOrderBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll (List<Long> ids) {
        CoPositionOrderIdsRequest request = CoPositionOrderIdsRequest.newBuilder().addAllId(ids).build();
        CoPositionOrderReply reply = coPositionOrderBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private CoPositionOrderPO entity2po (CoPositionOrderDTO entity) {
            CoPositionOrderPO.Builder builder = CoPositionOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }
}
