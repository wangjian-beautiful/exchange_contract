package com.bjs.contract.action;

import com.bijinsuo.common.domain.UserDTO;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.DubboReference;
import com.bjs.contract.proto.user.UserPO;
import com.bjs.contract.proto.user.UserBizService;
import com.bjs.contract.proto.user.UserRequest;
import com.bjs.contract.proto.user.UserPageRequest;
import com.bjs.contract.proto.user.UserIdsRequest;
import com.bjs.contract.proto.user.UserListRequest;
import com.bjs.contract.proto.user.UserReply;
import com.bjs.contract.proto.user.UserListReply;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAction {

    @DubboReference
    private UserBizService userBizService;

    @SneakyThrows
    public UserDTO getById (Long id) {
        UserRequest request = UserRequest.newBuilder().setId(id).build();
        UserPO po = userBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(UserDTO.class, po);
    }

    @SneakyThrows
    public List<UserDTO> selectAll () {
        UserPageRequest request = UserPageRequest.newBuilder().build();
        UserListReply listReply = userBizService.selectAll(request);
        List<UserPO> poList = listReply.getUserPOList();
        return ProtoBeanUtils.toPojoBeanList(UserDTO.class, poList);
    }

    @SneakyThrows
    public List<UserDTO> selectAllByPage (int page, int size) {
            UserPageRequest request = UserPageRequest.newBuilder().setPage(page).setSize(size).build();
            UserListReply listReply = userBizService.selectAll(request);
        List<UserPO> poList = listReply.getUserPOList();
        return ProtoBeanUtils.toPojoBeanList(UserDTO.class, poList);
    }

    @SneakyThrows
    public List<UserDTO> selectList (UserDTO entity) {
        UserPO po = entity2po(entity);
        UserPageRequest request = UserPageRequest.newBuilder().setUserPO(po).build();
        UserListReply listReply = userBizService.selectList(request);
        List<UserPO> poList = listReply.getUserPOList();
        return ProtoBeanUtils.toPojoBeanList(UserDTO.class, poList);
    }

    @SneakyThrows
    public List<UserDTO> selectListByPage (UserDTO entity, int page, int size) {
        UserPO po = entity2po(entity);
        UserPageRequest request = UserPageRequest.newBuilder().setPage(page).setSize(size).setUserPO(po).build();
        UserListReply listReply = userBizService.selectList(request);
        List<UserPO> poList = listReply.getUserPOList();
        return ProtoBeanUtils.toPojoBeanList(UserDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne (UserDTO entity) {
        UserPO po = entity2po(entity);
        UserRequest request = UserRequest.newBuilder().setUserPO(po).build();
        UserReply reply = userBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            UserPO resultPO = reply.getUserPO();
            UserDTO result = ProtoBeanUtils.toPojoBean(UserDTO.class, resultPO);
            entity.setUid(result.getUid());
        }
        return b;
    }

    @SneakyThrows
    public List<UserDTO> insertBatch (List<UserDTO> entityList) {
        List<UserPO> poList = ProtoBeanUtils.toProtoBeanList(UserPO.getDefaultInstance(), entityList);
        UserListRequest request = UserListRequest.newBuilder()
            .addAllUserPO(poList)
            .build();
        UserListReply listReply = userBizService.insertBatch(request);
        List<UserPO> reultList = listReply.getUserPOList();
        return ProtoBeanUtils.toPojoBeanList(UserDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById (UserDTO entity) {
        UserPO po = entity2po(entity);
        UserRequest request = UserRequest.newBuilder().setUserPO(po).build();
        UserReply reply = userBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch (List<UserDTO> entityList) {
        List<UserPO> poList = ProtoBeanUtils.toProtoBeanList(UserPO.getDefaultInstance(), entityList);
        UserListRequest request = UserListRequest.newBuilder()
            .addAllUserPO(poList)
            .build();
        UserReply reply = userBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById (UserDTO entity) {
        UserPO po = entity2po(entity);
        UserRequest request = UserRequest.newBuilder().setId(entity.getUid()).build();
        UserReply reply = userBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll (List<Long> ids) {
        UserIdsRequest request = UserIdsRequest.newBuilder().addAllId(ids).build();
        UserReply reply = userBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private UserPO entity2po (UserDTO entity) {
            UserPO.Builder builder = UserPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }
}
