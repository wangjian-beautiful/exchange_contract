package com.bjs.contract.action;

import com.bijinsuo.common.domain.UserLeverageDTO;
import com.bjs.contract.controller.request.UpdateLeverageReq;
import com.bjs.contract.proto.userLeverage.*;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.DubboReference;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;

import java.util.List;

@Component
public class UserLeverageAction {

    @DubboReference
    private UserLeverageBizService userLeverageBizService;

    @SneakyThrows
    public UserLeverageDTO getById(Long id) {
        UserLeverageRequest request = UserLeverageRequest.newBuilder().setId(id).build();
        UserLeveragePO po = userLeverageBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(UserLeverageDTO.class, po);
    }

    @SneakyThrows
    public UserLeverageDTO getBySymbol(String symbol) {
        GetBySymbolRequest request = GetBySymbolRequest.newBuilder().setSymbol(symbol).build();
        UserLeveragePO po = userLeverageBizService.getBySymbol(request);
        return ProtoBeanUtils.toPojoBean(UserLeverageDTO.class, po);
    }

    @SneakyThrows
    public List<UserLeverageDTO> selectAll() {
        UserLeveragePageRequest request = UserLeveragePageRequest.newBuilder().build();
        UserLeverageListReply listReply = userLeverageBizService.selectAll(request);
        List<UserLeveragePO> poList = listReply.getUserLeveragePOList();
        return ProtoBeanUtils.toPojoBeanList(UserLeverageDTO.class, poList);
    }

    @SneakyThrows
    public List<UserLeverageDTO> selectAllByPage(int page, int size) {
        UserLeveragePageRequest request = UserLeveragePageRequest.newBuilder().setPage(page).setSize(size).build();
        UserLeverageListReply listReply = userLeverageBizService.selectAll(request);
        List<UserLeveragePO> poList = listReply.getUserLeveragePOList();
        return ProtoBeanUtils.toPojoBeanList(UserLeverageDTO.class, poList);
    }

    @SneakyThrows
    public List<UserLeverageDTO> selectList(UserLeverageDTO entity) {
        UserLeveragePO po = entity2po(entity);
        UserLeveragePageRequest request = UserLeveragePageRequest.newBuilder().setUserLeveragePO(po).build();
        UserLeverageListReply listReply = userLeverageBizService.selectList(request);
        List<UserLeveragePO> poList = listReply.getUserLeveragePOList();
        return ProtoBeanUtils.toPojoBeanList(UserLeverageDTO.class, poList);
    }

    @SneakyThrows
    public List<UserLeverageDTO> selectListByPage(UserLeverageDTO entity, int page, int size) {
        UserLeveragePO po = entity2po(entity);
        UserLeveragePageRequest request = UserLeveragePageRequest.newBuilder().setPage(page).setSize(size).setUserLeveragePO(po).build();
        UserLeverageListReply listReply = userLeverageBizService.selectList(request);
        List<UserLeveragePO> poList = listReply.getUserLeveragePOList();
        return ProtoBeanUtils.toPojoBeanList(UserLeverageDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne(UserLeverageDTO entity) {
        UserLeveragePO po = entity2po(entity);
        UserLeverageRequest request = UserLeverageRequest.newBuilder().setUserLeveragePO(po).build();
        UserLeverageReply reply = userLeverageBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            UserLeveragePO resultPO = reply.getUserLeveragePO();
            UserLeverageDTO result = ProtoBeanUtils.toPojoBean(UserLeverageDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<UserLeverageDTO> insertBatch(List<UserLeverageDTO> entityList) {
        List<UserLeveragePO> poList = ProtoBeanUtils.toProtoBeanList(UserLeveragePO.getDefaultInstance(), entityList);
        UserLeverageListRequest request = UserLeverageListRequest.newBuilder()
                .addAllUserLeveragePO(poList)
                .build();
        UserLeverageListReply listReply = userLeverageBizService.insertBatch(request);
        List<UserLeveragePO> reultList = listReply.getUserLeveragePOList();
        return ProtoBeanUtils.toPojoBeanList(UserLeverageDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById(UserLeverageDTO entity) {
        UserLeveragePO po = entity2po(entity);
        UserLeverageRequest request = UserLeverageRequest.newBuilder().setUserLeveragePO(po).build();
        UserLeverageReply reply = userLeverageBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch(List<UserLeverageDTO> entityList) {
        List<UserLeveragePO> poList = ProtoBeanUtils.toProtoBeanList(UserLeveragePO.getDefaultInstance(), entityList);
        UserLeverageListRequest request = UserLeverageListRequest.newBuilder()
                .addAllUserLeveragePO(poList)
                .build();
        UserLeverageReply reply = userLeverageBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById(UserLeverageDTO entity) {
        UserLeveragePO po = entity2po(entity);
        UserLeverageRequest request = UserLeverageRequest.newBuilder().setId(entity.getId()).build();
        UserLeverageReply reply = userLeverageBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll(List<Long> ids) {
        UserLeverageIdsRequest request = UserLeverageIdsRequest.newBuilder().addAllId(ids).build();
        UserLeverageReply reply = userLeverageBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private UserLeveragePO entity2po(UserLeverageDTO entity) {
        UserLeveragePO.Builder builder = UserLeveragePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }


    public boolean updateLeverage(UpdateLeverageReq request) {
        UpdateLeverageRequest updateLeverageRequest = UpdateLeverageRequest.newBuilder()
                .setLeverage(request.getLeverage())
                .setSymbol(request.getSymbol()).build();
        UserLeverageReply reply = userLeverageBizService.updateLeverage(updateLeverageRequest);
        return reply.getStatus();
    }
}
