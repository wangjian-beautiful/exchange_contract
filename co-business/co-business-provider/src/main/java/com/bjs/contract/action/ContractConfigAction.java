package com.bjs.contract.action;

import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.proto.contractConfig.*;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import com.bjs.contract.proto.contractConfig.*;
import com.bjs.contract.utils.ContractConfigUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ContractConfigAction {

    @DubboReference
    private ContractConfigBizService contractConfigBizService;

    @SneakyThrows
    public ContractConfigDTO getById (Long id) {
        ContractConfigRequest request = ContractConfigRequest.newBuilder().setId(id).build();
        ContractConfigPO po = contractConfigBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, po);
    }

    public ContractConfigDTO getBySymbol (String symbol) {
        ContractConfigSymbolRequest request = ContractConfigSymbolRequest.newBuilder().setSymbol(symbol).build();
        ContractConfigPO po = contractConfigBizService.getBySymbol(request);
        return ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, po);
    }

    @SneakyThrows
    public List<ContractConfigDTO> selectAll () {
        ContractConfigPageRequest request = ContractConfigPageRequest.newBuilder().build();
        ContractConfigListReply listReply = contractConfigBizService.selectAll(request);
        List<ContractConfigPO> poList = listReply.getContractConfigPOList();
        return ProtoBeanUtils.toPojoBeanList(ContractConfigDTO.class, poList);
    }

    @SneakyThrows
    public List<ContractConfigDTO> selectAllByPage (int page, int size) {
            ContractConfigPageRequest request = ContractConfigPageRequest.newBuilder().setPage(page).setSize(size).build();
            ContractConfigListReply listReply = contractConfigBizService.selectAll(request);
        List<ContractConfigPO> poList = listReply.getContractConfigPOList();
        return ProtoBeanUtils.toPojoBeanList(ContractConfigDTO.class, poList);
    }

    @SneakyThrows
    public List<ContractConfigDTO> selectList (ContractConfigDTO entity) {
        ContractConfigPO po = entity2po(entity);
        ContractConfigPageRequest request = ContractConfigPageRequest.newBuilder().setContractConfigPO(po).build();
        ContractConfigListReply listReply = contractConfigBizService.selectList(request);
        List<ContractConfigPO> poList = listReply.getContractConfigPOList();
        return ProtoBeanUtils.toPojoBeanList(ContractConfigDTO.class, poList);
    }

    @SneakyThrows
    public List<ContractConfigDTO> selectListByPage (ContractConfigDTO entity, int page, int size) {
        ContractConfigPO po = entity2po(entity);
        ContractConfigPageRequest request = ContractConfigPageRequest.newBuilder().setPage(page).setSize(size).setContractConfigPO(po).build();
        ContractConfigListReply listReply = contractConfigBizService.selectList(request);
        List<ContractConfigPO> poList = listReply.getContractConfigPOList();
        return ProtoBeanUtils.toPojoBeanList(ContractConfigDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne (ContractConfigDTO entity) {
        ContractConfigPO po = entity2po(entity);
        ContractConfigRequest request = ContractConfigRequest.newBuilder().setContractConfigPO(po).build();
        ContractConfigReply reply = contractConfigBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            ContractConfigPO resultPO = reply.getContractConfigPO();
            ContractConfigDTO result = ProtoBeanUtils.toPojoBean(ContractConfigDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<ContractConfigDTO> insertBatch (List<ContractConfigDTO> entityList) {
        List<ContractConfigPO> poList = ProtoBeanUtils.toProtoBeanList(ContractConfigPO.getDefaultInstance(), entityList);
        ContractConfigListRequest request = ContractConfigListRequest.newBuilder()
            .addAllContractConfigPO(poList)
            .build();
        ContractConfigListReply listReply = contractConfigBizService.insertBatch(request);
        List<ContractConfigPO> reultList = listReply.getContractConfigPOList();
        return ProtoBeanUtils.toPojoBeanList(ContractConfigDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById (ContractConfigDTO entity) {
        ContractConfigPO po = entity2po(entity);
        ContractConfigRequest request = ContractConfigRequest.newBuilder().setContractConfigPO(po).build();
        ContractConfigReply reply = contractConfigBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch (List<ContractConfigDTO> entityList) {
        List<ContractConfigPO> poList = ProtoBeanUtils.toProtoBeanList(ContractConfigPO.getDefaultInstance(), entityList);
        ContractConfigListRequest request = ContractConfigListRequest.newBuilder()
            .addAllContractConfigPO(poList)
            .build();
        ContractConfigReply reply = contractConfigBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById (ContractConfigDTO entity) {
        ContractConfigPO po = entity2po(entity);
        ContractConfigRequest request = ContractConfigRequest.newBuilder().setId(entity.getId()).build();
        ContractConfigReply reply = contractConfigBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll (List<Long> ids) {
        ContractConfigIdsRequest request = ContractConfigIdsRequest.newBuilder().addAllId(ids).build();
        ContractConfigReply reply = contractConfigBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private ContractConfigPO entity2po (ContractConfigDTO entity) {
        ContractConfigPO.Builder builder = ContractConfigPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @PostConstruct
    private void init () {
        ContractConfigListReply contractConfigListReply = contractConfigBizService.selectAll(ContractConfigPageRequest.newBuilder().build());
        List<ContractConfigPO> contractConfigPOList = contractConfigListReply.getContractConfigPOList();

        for (ContractConfigPO contractConfigPO : contractConfigPOList) {
            ContractConfigUtil.addContractConfig(contractConfigPO);
        }
    }

    public void checkContractConfig (String symbol) {
        ContractConfigPO contractConfig = ContractConfigUtil.getContractConfig(symbol);
        if (contractConfig == null) {
            ContractConfigPO contractConfigPO = contractConfigBizService.getBySymbol(ContractConfigSymbolRequest.newBuilder().setSymbol(symbol).build());
            if (StringUtils.isEmpty(contractConfigPO.toString())) {
                throw new RuntimeException("contractConfig is empty, symbol is " + symbol);
            }
            ContractConfigUtil.addContractConfig(contractConfigPO);
        }
    }
}
