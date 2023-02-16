package com.bjs.contract.action;

import com.bijinsuo.common.domain.CoTriggerOrderDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.proto.coTriggerOrder.*;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoTriggerOrderAction {

    @DubboReference
    private CoTriggerOrderBizService coTriggerOrderBizService;

    @SneakyThrows
    public CoTriggerOrderDTO getById(Long id) {
        CoTriggerOrderRequest request = CoTriggerOrderRequest.newBuilder().setId(id).build();
        CoTriggerOrderPO po = coTriggerOrderBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(CoTriggerOrderDTO.class, po);
    }

    @SneakyThrows
    public List<CoTriggerOrderDTO> selectAll() {
        CoTriggerOrderPageRequest request = CoTriggerOrderPageRequest.newBuilder().build();
        CoTriggerOrderListReply listReply = coTriggerOrderBizService.selectAll(request);
        List<CoTriggerOrderPO> poList = listReply.getCoTriggerOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoTriggerOrderDTO.class, poList);
    }

    @SneakyThrows
    public List<CoTriggerOrderDTO> selectAllByPage(int page, int size) {
        CoTriggerOrderPageRequest request = CoTriggerOrderPageRequest.newBuilder().setPage(page).setSize(size).build();
        CoTriggerOrderListReply listReply = coTriggerOrderBizService.selectAll(request);
        List<CoTriggerOrderPO> poList = listReply.getCoTriggerOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoTriggerOrderDTO.class, poList);
    }

    @SneakyThrows
    public List<CoTriggerOrderDTO> selectList(CoTriggerOrderDTO entity) {
        CoTriggerOrderPO po = entity2po(entity);
        CoTriggerOrderPageRequest request = CoTriggerOrderPageRequest.newBuilder().setCoTriggerOrderPO(po).build();
        CoTriggerOrderListReply listReply = coTriggerOrderBizService.selectList(request);
        List<CoTriggerOrderPO> poList = listReply.getCoTriggerOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoTriggerOrderDTO.class, poList);
    }

    @SneakyThrows
    public List<CoTriggerOrderDTO> selectListByPage(CoTriggerOrderDTO entity, int page, int size) {
        CoTriggerOrderPO po = entity2po(entity);
        CoTriggerOrderPageRequest request = CoTriggerOrderPageRequest.newBuilder().setPage(page).setSize(size).setCoTriggerOrderPO(po).build();
        CoTriggerOrderListReply listReply = coTriggerOrderBizService.selectList(request);
        List<CoTriggerOrderPO> poList = listReply.getCoTriggerOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoTriggerOrderDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne(CoTriggerOrderDTO entity) {
        CoTriggerOrderPO po = entity2po(entity);
        CoTriggerOrderRequest request = CoTriggerOrderRequest.newBuilder().setCoTriggerOrderPO(po).build();
        CoTriggerOrderReply reply = coTriggerOrderBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            CoTriggerOrderPO resultPO = reply.getCoTriggerOrderPO();
            CoTriggerOrderDTO result = ProtoBeanUtils.toPojoBean(CoTriggerOrderDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<CoTriggerOrderDTO> insertBatch(List<CoTriggerOrderDTO> entityList) {
        List<CoTriggerOrderPO> poList = ProtoBeanUtils.toProtoBeanList(CoTriggerOrderPO.getDefaultInstance(), entityList);
        CoTriggerOrderListRequest request = CoTriggerOrderListRequest.newBuilder()
                .addAllCoTriggerOrderPO(poList)
                .build();
        CoTriggerOrderListReply listReply = coTriggerOrderBizService.insertBatch(request);
        List<CoTriggerOrderPO> reultList = listReply.getCoTriggerOrderPOList();
        return ProtoBeanUtils.toPojoBeanList(CoTriggerOrderDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById(CoTriggerOrderDTO entity) {
        CoTriggerOrderPO po = entity2po(entity);
        CoTriggerOrderRequest request = CoTriggerOrderRequest.newBuilder().setCoTriggerOrderPO(po).build();
        CoTriggerOrderReply reply = coTriggerOrderBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch(List<CoTriggerOrderDTO> entityList) {
        List<CoTriggerOrderPO> poList = ProtoBeanUtils.toProtoBeanList(CoTriggerOrderPO.getDefaultInstance(), entityList);
        CoTriggerOrderListRequest request = CoTriggerOrderListRequest.newBuilder()
                .addAllCoTriggerOrderPO(poList)
                .build();
        CoTriggerOrderReply reply = coTriggerOrderBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById(CoTriggerOrderDTO entity) {
        CoTriggerOrderPO po = entity2po(entity);
        CoTriggerOrderRequest request = CoTriggerOrderRequest.newBuilder().setId(entity.getId()).build();
        CoTriggerOrderReply reply = coTriggerOrderBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll(List<Long> ids) {
        CoTriggerOrderIdsRequest request = CoTriggerOrderIdsRequest.newBuilder().addAllId(ids).build();
        CoTriggerOrderReply reply = coTriggerOrderBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private CoTriggerOrderPO entity2po(CoTriggerOrderDTO entity) {
        CoTriggerOrderPO.Builder builder = CoTriggerOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    public void create(CoTriggerOrderDTO dto) {
        final var request = CoTriggerOrderRequest.newBuilder().
            setCoTriggerOrderPO(ProtoBeanUtils.toProtoBean(CoTriggerOrderPO.getDefaultInstance(), dto))
            .build();
        CoTriggerOrderReply reply = coTriggerOrderBizService.create(request);
        if (!reply.getStatus()) {
            throw new BizException(reply.getMessage(), reply.getMessage());
        }
    }


    public boolean cancel(Long triggerOrderId) {
        CancelTriggerOrderByIdRequest request = CancelTriggerOrderByIdRequest.newBuilder().setId(triggerOrderId).build();
        return coTriggerOrderBizService.cancelTriggerOrderById(request).getStatus();
    }

    public boolean cancelAll() {
        CancelAllTriggerOrderRequest request = CancelAllTriggerOrderRequest.newBuilder().build();
        return coTriggerOrderBizService.cancelAllTriggerOrder(request).getStatus();
    }
}
