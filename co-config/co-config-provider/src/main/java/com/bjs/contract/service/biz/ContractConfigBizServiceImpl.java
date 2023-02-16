package com.bjs.contract.service.biz;

import com.bjs.contract.proto.contractConfig.*;
import com.bjs.contract.service.ContractConfigService;
import com.bjs.contract.entity.ContractConfig;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-11 17:58:42
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter","ExtractTokenFilter"})
public class ContractConfigBizServiceImpl implements ContractConfigBizService {

    @Resource
    private ContractConfigService contractConfigService;

    @Override
    public ContractConfigPO getById(ContractConfigRequest request) {
        ContractConfig result = contractConfigService.getById(request.getId());

        ContractConfigPO po = entity2po(result);
        return po;
    }

    @Override
    public ContractConfigPO getBySymbol(ContractConfigSymbolRequest request) {
        ContractConfig result = contractConfigService.getBySymbol(request.getSymbol());

        ContractConfigPO po = entity2po(result);
        return po;
    }

    @Override
    public ContractConfigReply insertOne(ContractConfigRequest request) {
        ContractConfigPO po = request.getContractConfigPO();
        ContractConfig entity = po2entity(po);

        boolean result = contractConfigService.save(entity);
        ContractConfigPO res = entity2po(entity);
        return ContractConfigReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setContractConfigPO(res)
                .build();
    }

    @Override
    public ContractConfigReply updateById(ContractConfigRequest request) {
        ContractConfigPO po = request.getContractConfigPO();
        ContractConfig entity = po2entity(po);

        boolean b = contractConfigService.updateById(entity);
        return ContractConfigReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public ContractConfigReply removeById(ContractConfigRequest request) {
        boolean b = contractConfigService.removeById(request.getId());
        return ContractConfigReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public ContractConfigListReply selectListByIds(ContractConfigIdsRequest request) {
        List<ContractConfig> contractConfigList = contractConfigService.listByIds(request.getIdList());
        List<ContractConfigPO> contractConfigPOList = entityList2poList(contractConfigList);
        return ContractConfigListReply.newBuilder()
                .addAllContractConfigPO(contractConfigPOList)
                .build();
    }

    @Override
    public ContractConfigListReply selectAll(ContractConfigPageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<ContractConfig> contractConfigList = contractConfigService.list();
        PageInfo<ContractConfig> pageInfo = new PageInfo<>(contractConfigList);
        List<ContractConfigPO> contractConfigPOList = entityList2poList(pageInfo.getList());
        return ContractConfigListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllContractConfigPO(contractConfigPOList)
                .build();
    }

    @Override
    public ContractConfigListReply selectList(ContractConfigPageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        ContractConfigPO po = request.getContractConfigPO();
        ContractConfig contractConfig = po2entity(po);
        QueryWrapper<ContractConfig> queryWrapper = new QueryWrapper<>(contractConfig);
        List<ContractConfig> contractConfigList = contractConfigService.list(queryWrapper);
        PageInfo<ContractConfig> pageInfo = new PageInfo<>(contractConfigList);
        List<ContractConfigPO> contractConfigPOList = entityList2poList(pageInfo.getList());
        return ContractConfigListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllContractConfigPO(contractConfigPOList)
                .build();
    }

    @Override
    public ContractConfigListReply insertBatch (ContractConfigListRequest request) {
        List<ContractConfigPO> dataList = request.getContractConfigPOList();
        List<ContractConfig> entityList = poList2entityList(dataList);

        contractConfigService.saveBatch(entityList);

        List<ContractConfigPO> contractConfigPOList = entityList2poList(entityList);
        return ContractConfigListReply.newBuilder()
                .addAllContractConfigPO(contractConfigPOList)
                .build();
    }

    @Override
    public ContractConfigReply updateBatch (ContractConfigListRequest request) {
        List<ContractConfigPO> dataList = request.getContractConfigPOList();
        List<ContractConfig> entityList = poList2entityList(dataList);
        boolean b = contractConfigService.updateBatchById(entityList);
        return ContractConfigReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public ContractConfigReply removeAll (ContractConfigIdsRequest request) {
        boolean b = contractConfigService.removeByIds(request.getIdList());
        return ContractConfigReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<ContractConfig> poList2entityList (List<ContractConfigPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(ContractConfig.class, poList);
    }

    @SneakyThrows
    private List<ContractConfigPO> entityList2poList (List<ContractConfig> entityList) {
        return ProtoBeanUtils.toProtoBeanList(ContractConfigPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private ContractConfigPO entity2po (ContractConfig entity) {
        ContractConfigPO.Builder builder = ContractConfigPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private ContractConfig po2entity (ContractConfigPO po) {
        return ProtoBeanUtils.toPojoBean(ContractConfig.class, po);
    }
}
