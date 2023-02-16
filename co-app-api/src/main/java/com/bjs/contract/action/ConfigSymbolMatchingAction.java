package com.bjs.contract.action;

import com.bijinsuo.common.domain.ConfigSymbolMatchingDTO;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.DubboReference;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingPO;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingBizService;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingPageRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingIdsRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingListRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingReply;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingListReply;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfigSymbolMatchingAction {
    @DubboReference
    private ConfigSymbolMatchingBizService configSymbolMatchingBizService;

    @SneakyThrows
    public ConfigSymbolMatchingDTO getById (Long id) {
        ConfigSymbolMatchingRequest request = ConfigSymbolMatchingRequest.newBuilder().setId(id).build();
        ConfigSymbolMatchingPO po = configSymbolMatchingBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(ConfigSymbolMatchingDTO.class, po);
    }

    @SneakyThrows
    public List<ConfigSymbolMatchingDTO> selectAll () {
        ConfigSymbolMatchingPageRequest request = ConfigSymbolMatchingPageRequest.newBuilder().build();
        ConfigSymbolMatchingListReply listReply = configSymbolMatchingBizService.selectAll(request);
        List<ConfigSymbolMatchingPO> poList = listReply.getConfigSymbolMatchingPOList();
        return ProtoBeanUtils.toPojoBeanList(ConfigSymbolMatchingDTO.class, poList);
    }

    @SneakyThrows
    public List<ConfigSymbolMatchingDTO> selectAllByPage (int page, int size) {
            ConfigSymbolMatchingPageRequest request = ConfigSymbolMatchingPageRequest.newBuilder().setPage(page).setSize(size).build();
            ConfigSymbolMatchingListReply listReply = configSymbolMatchingBizService.selectAll(request);
        List<ConfigSymbolMatchingPO> poList = listReply.getConfigSymbolMatchingPOList();
        return ProtoBeanUtils.toPojoBeanList(ConfigSymbolMatchingDTO.class, poList);
    }

    @SneakyThrows
    public List<ConfigSymbolMatchingDTO> selectList (ConfigSymbolMatchingDTO entity) {
        ConfigSymbolMatchingPO po = entity2po(entity);
        ConfigSymbolMatchingPageRequest request = ConfigSymbolMatchingPageRequest.newBuilder().setConfigSymbolMatchingPO(po).build();
        ConfigSymbolMatchingListReply listReply = configSymbolMatchingBizService.selectList(request);
        List<ConfigSymbolMatchingPO> poList = listReply.getConfigSymbolMatchingPOList();
        return ProtoBeanUtils.toPojoBeanList(ConfigSymbolMatchingDTO.class, poList);
    }

    @SneakyThrows
    public List<ConfigSymbolMatchingDTO> selectListByPage (ConfigSymbolMatchingDTO entity, int page, int size) {
        ConfigSymbolMatchingPO po = entity2po(entity);
        ConfigSymbolMatchingPageRequest request = ConfigSymbolMatchingPageRequest.newBuilder().setPage(page).setSize(size).setConfigSymbolMatchingPO(po).build();
        ConfigSymbolMatchingListReply listReply = configSymbolMatchingBizService.selectList(request);
        List<ConfigSymbolMatchingPO> poList = listReply.getConfigSymbolMatchingPOList();
        return ProtoBeanUtils.toPojoBeanList(ConfigSymbolMatchingDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne (ConfigSymbolMatchingDTO entity) {
        ConfigSymbolMatchingPO po = entity2po(entity);
        ConfigSymbolMatchingRequest request = ConfigSymbolMatchingRequest.newBuilder().setConfigSymbolMatchingPO(po).build();
        ConfigSymbolMatchingReply reply = configSymbolMatchingBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            ConfigSymbolMatchingPO resultPO = reply.getConfigSymbolMatchingPO();
            ConfigSymbolMatchingDTO result = ProtoBeanUtils.toPojoBean(ConfigSymbolMatchingDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<ConfigSymbolMatchingDTO> insertBatch (List<ConfigSymbolMatchingDTO> entityList) {
        List<ConfigSymbolMatchingPO> poList = ProtoBeanUtils.toProtoBeanList(ConfigSymbolMatchingPO.getDefaultInstance(), entityList);
        ConfigSymbolMatchingListRequest request = ConfigSymbolMatchingListRequest.newBuilder()
            .addAllConfigSymbolMatchingPO(poList)
            .build();
        ConfigSymbolMatchingListReply listReply = configSymbolMatchingBizService.insertBatch(request);
        List<ConfigSymbolMatchingPO> reultList = listReply.getConfigSymbolMatchingPOList();
        return ProtoBeanUtils.toPojoBeanList(ConfigSymbolMatchingDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById (ConfigSymbolMatchingDTO entity) {
        ConfigSymbolMatchingPO po = entity2po(entity);
        ConfigSymbolMatchingRequest request = ConfigSymbolMatchingRequest.newBuilder().setConfigSymbolMatchingPO(po).build();
        ConfigSymbolMatchingReply reply = configSymbolMatchingBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch (List<ConfigSymbolMatchingDTO> entityList) {
        List<ConfigSymbolMatchingPO> poList = ProtoBeanUtils.toProtoBeanList(ConfigSymbolMatchingPO.getDefaultInstance(), entityList);
        ConfigSymbolMatchingListRequest request = ConfigSymbolMatchingListRequest.newBuilder()
            .addAllConfigSymbolMatchingPO(poList)
            .build();
        ConfigSymbolMatchingReply reply = configSymbolMatchingBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById (ConfigSymbolMatchingDTO entity) {
        ConfigSymbolMatchingPO po = entity2po(entity);
        ConfigSymbolMatchingRequest request = ConfigSymbolMatchingRequest.newBuilder().setId(entity.getId()).build();
        ConfigSymbolMatchingReply reply = configSymbolMatchingBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll (List<Long> ids) {
        ConfigSymbolMatchingIdsRequest request = ConfigSymbolMatchingIdsRequest.newBuilder().addAllId(ids).build();
        ConfigSymbolMatchingReply reply = configSymbolMatchingBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private ConfigSymbolMatchingPO entity2po (ConfigSymbolMatchingDTO entity) {
            ConfigSymbolMatchingPO.Builder builder = ConfigSymbolMatchingPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }
}
