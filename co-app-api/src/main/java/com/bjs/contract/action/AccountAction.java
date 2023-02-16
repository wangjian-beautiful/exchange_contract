package com.bjs.contract.action;

import com.bijinsuo.common.domain.AccountDTO;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.DubboReference;

import com.bjs.contract.proto.account.AccountPO;
import com.bjs.contract.proto.account.AccountBizService;
import com.bjs.contract.proto.account.AccountRequest;
import com.bjs.contract.proto.account.AccountPageRequest;
import com.bjs.contract.proto.account.AccountIdsRequest;
import com.bjs.contract.proto.account.AccountListRequest;
import com.bjs.contract.proto.account.AccountReply;
import com.bjs.contract.proto.account.AccountListReply;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountAction {
    @DubboReference
    private AccountBizService accountBizService;

    @SneakyThrows
    public AccountDTO getById (Long id) {
        AccountRequest request = AccountRequest.newBuilder().setId(id).build();
        AccountPO po = accountBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(AccountDTO.class, po);
    }

    @SneakyThrows
    public List<AccountDTO> selectAll () {
        AccountPageRequest request = AccountPageRequest.newBuilder().build();
        AccountListReply listReply = accountBizService.selectAll(request);
        List<AccountPO> poList = listReply.getAccountPOList();
        return ProtoBeanUtils.toPojoBeanList(AccountDTO.class, poList);
    }

    @SneakyThrows
    public List<AccountDTO> selectAllByPage (int page, int size) {
            AccountPageRequest request = AccountPageRequest.newBuilder().setPage(page).setSize(size).build();
            AccountListReply listReply = accountBizService.selectAll(request);
        List<AccountPO> poList = listReply.getAccountPOList();
        return ProtoBeanUtils.toPojoBeanList(AccountDTO.class, poList);
    }

    @SneakyThrows
    public List<AccountDTO> selectList (AccountDTO entity) {
        AccountPO po = entity2po(entity);
        AccountPageRequest request = AccountPageRequest.newBuilder().setAccountPO(po).build();
        AccountListReply listReply = accountBizService.selectList(request);
        List<AccountPO> poList = listReply.getAccountPOList();
        return ProtoBeanUtils.toPojoBeanList(AccountDTO.class, poList);
    }

    @SneakyThrows
    public List<AccountDTO> selectListByPage (AccountDTO entity, int page, int size) {
        AccountPO po = entity2po(entity);
        AccountPageRequest request = AccountPageRequest.newBuilder().setPage(page).setSize(size).setAccountPO(po).build();
        AccountListReply listReply = accountBizService.selectList(request);
        List<AccountPO> poList = listReply.getAccountPOList();
        return ProtoBeanUtils.toPojoBeanList(AccountDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne (AccountDTO entity) {
        AccountPO po = entity2po(entity);
        AccountRequest request = AccountRequest.newBuilder().setAccountPO(po).build();
        AccountReply reply = accountBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            AccountPO resultPO = reply.getAccountPO();
            AccountDTO result = ProtoBeanUtils.toPojoBean(AccountDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<AccountDTO> insertBatch (List<AccountDTO> entityList) {
        List<AccountPO> poList = ProtoBeanUtils.toProtoBeanList(AccountPO.getDefaultInstance(), entityList);
        AccountListRequest request = AccountListRequest.newBuilder()
            .addAllAccountPO(poList)
            .build();
        AccountListReply listReply = accountBizService.insertBatch(request);
        List<AccountPO> reultList = listReply.getAccountPOList();
        return ProtoBeanUtils.toPojoBeanList(AccountDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById (AccountDTO entity) {
        AccountPO po = entity2po(entity);
        AccountRequest request = AccountRequest.newBuilder().setAccountPO(po).build();
        AccountReply reply = accountBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch (List<AccountDTO> entityList) {
        List<AccountPO> poList = ProtoBeanUtils.toProtoBeanList(AccountPO.getDefaultInstance(), entityList);
        AccountListRequest request = AccountListRequest.newBuilder()
            .addAllAccountPO(poList)
            .build();
        AccountReply reply = accountBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById (AccountDTO entity) {
        AccountPO po = entity2po(entity);
        AccountRequest request = AccountRequest.newBuilder().setId(entity.getId()).build();
        AccountReply reply = accountBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll (List<Long> ids) {
        AccountIdsRequest request = AccountIdsRequest.newBuilder().addAllId(ids).build();
        AccountReply reply = accountBizService.removeAll(request);
        return reply.getStatus();
    }

    @SneakyThrows
    private AccountPO entity2po (AccountDTO entity) {
            AccountPO.Builder builder = AccountPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }
}
