package com.bjs.contract.service.biz;

import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingPO;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingBizService;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingPageRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingIdsRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingListRequest;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingReply;
import com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingListReply;
import com.bjs.contract.service.ConfigSymbolMatchingService;
import com.bjs.contract.entity.ConfigSymbolMatching;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-09 17:55:48
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter","ExtractTokenFilter"})
public class ConfigSymbolMatchingBizServiceImpl implements ConfigSymbolMatchingBizService {

    @Resource
    private ConfigSymbolMatchingService configSymbolMatchingService;

    @Override
    public ConfigSymbolMatchingPO getById(ConfigSymbolMatchingRequest request) {
        ConfigSymbolMatching result = configSymbolMatchingService.getById(request.getId());

        ConfigSymbolMatchingPO po = entity2po(result);
        return po;
    }

    @Override
    public ConfigSymbolMatchingReply insertOne(ConfigSymbolMatchingRequest request) {
        ConfigSymbolMatchingPO po = request.getConfigSymbolMatchingPO();
        ConfigSymbolMatching entity = po2entity(po);

        boolean result = configSymbolMatchingService.save(entity);
        ConfigSymbolMatchingPO res = entity2po(entity);
        return ConfigSymbolMatchingReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setConfigSymbolMatchingPO(res)
                .build();
    }

    @Override
    public ConfigSymbolMatchingReply updateById(ConfigSymbolMatchingRequest request) {
        ConfigSymbolMatchingPO po = request.getConfigSymbolMatchingPO();
        ConfigSymbolMatching entity = po2entity(po);

        boolean b = configSymbolMatchingService.updateById(entity);
        return ConfigSymbolMatchingReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public ConfigSymbolMatchingReply removeById(ConfigSymbolMatchingRequest request) {
        boolean b = configSymbolMatchingService.removeById(request.getId());
        return ConfigSymbolMatchingReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public ConfigSymbolMatchingListReply selectListByIds(ConfigSymbolMatchingIdsRequest request) {
        List<ConfigSymbolMatching> configSymbolMatchingList = configSymbolMatchingService.listByIds(request.getIdList());
        List<ConfigSymbolMatchingPO> configSymbolMatchingPOList = entityList2poList(configSymbolMatchingList);
        return ConfigSymbolMatchingListReply.newBuilder()
                .addAllConfigSymbolMatchingPO(configSymbolMatchingPOList)
                .build();
    }

    @Override
    public ConfigSymbolMatchingListReply selectAll(ConfigSymbolMatchingPageRequest request) {
        List<ConfigSymbolMatching> configSymbolMatchingList = configSymbolMatchingService.list();
        List<ConfigSymbolMatchingPO> configSymbolMatchingPOList = entityList2poList(configSymbolMatchingList);
        return ConfigSymbolMatchingListReply.newBuilder()
                .addAllConfigSymbolMatchingPO(configSymbolMatchingPOList)
                .build();
    }

    @Override
    public ConfigSymbolMatchingListReply insertBatch (ConfigSymbolMatchingListRequest request) {
        List<ConfigSymbolMatchingPO> dataList = request.getConfigSymbolMatchingPOList();
        List<ConfigSymbolMatching> entityList = poList2entityList(dataList);

        configSymbolMatchingService.saveBatch(entityList);

        List<ConfigSymbolMatchingPO> configSymbolMatchingPOList = entityList2poList(entityList);
        return ConfigSymbolMatchingListReply.newBuilder()
                .addAllConfigSymbolMatchingPO(configSymbolMatchingPOList)
                .build();
    }

    @Override
    public ConfigSymbolMatchingReply updateBatch (ConfigSymbolMatchingListRequest request) {
        List<ConfigSymbolMatchingPO> dataList = request.getConfigSymbolMatchingPOList();
        List<ConfigSymbolMatching> entityList = poList2entityList(dataList);
        boolean b = configSymbolMatchingService.updateBatchById(entityList);
        return ConfigSymbolMatchingReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public ConfigSymbolMatchingReply removeAll (ConfigSymbolMatchingIdsRequest request) {
        boolean b = configSymbolMatchingService.removeByIds(request.getIdList());
        return ConfigSymbolMatchingReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<ConfigSymbolMatching> poList2entityList (List<ConfigSymbolMatchingPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(ConfigSymbolMatching.class, poList);
    }

    @SneakyThrows
    private List<ConfigSymbolMatchingPO> entityList2poList (List<ConfigSymbolMatching> entityList) {
        return ProtoBeanUtils.toProtoBeanList(ConfigSymbolMatchingPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private ConfigSymbolMatchingPO entity2po (ConfigSymbolMatching entity) {
        ConfigSymbolMatchingPO.Builder builder = ConfigSymbolMatchingPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private ConfigSymbolMatching po2entity (ConfigSymbolMatchingPO po) {
        return ProtoBeanUtils.toPojoBean(ConfigSymbolMatching.class, po);
    }
}
