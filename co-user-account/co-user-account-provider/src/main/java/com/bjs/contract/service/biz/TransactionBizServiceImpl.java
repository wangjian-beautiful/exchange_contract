package com.bjs.contract.service.biz;

import com.bijinsuo.common.utils.DateUtils;
import com.bjs.contract.mapper.TransactionMapper;
import com.bjs.contract.proto.transaction.*;
import com.bjs.contract.service.TransactionService;
import com.bjs.contract.entity.Transaction;
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
 * @Date: 2022-11-11 10:51:39
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter", "ExtractTokenFilter"})
public class TransactionBizServiceImpl implements TransactionBizService {

    @Resource
    private TransactionService transactionService;
    @Resource
    private TransactionMapper transactionMapper;

    @Override
    public TransactionPO getById(TransactionRequest request) {
        Transaction result = transactionService.getById(request.getId());

        TransactionPO po = entity2po(result);
        return po;
    }

    @Override
    public TransactionReply insertOne(TransactionRequest request) {
        TransactionPO po = request.getTransactionPO();
        Transaction entity = po2entity(po);

        boolean result = transactionService.save(entity);
        TransactionPO res = entity2po(entity);
        return TransactionReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setTransactionPO(res)
                .build();
    }

    @Override
    public TransactionReply updateById(TransactionRequest request) {
        TransactionPO po = request.getTransactionPO();
        Transaction entity = po2entity(po);

        boolean b = transactionService.updateById(entity);
        return TransactionReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public TransactionReply removeById(TransactionRequest request) {
        boolean b = transactionService.removeById(request.getId());
        return TransactionReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public TransactionListReply selectListByIds(TransactionIdsRequest request) {
        List<Transaction> transactionList = transactionService.listByIds(request.getIdList());
        List<TransactionPO> transactionPOList = entityList2poList(transactionList);
        return TransactionListReply.newBuilder()
                .addAllTransactionPO(transactionPOList)
                .build();
    }

    @Override
    public TransactionListReply selectAll(TransactionPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<Transaction> transactionList = transactionService.list();
        PageInfo<Transaction> pageInfo = new PageInfo<>(transactionList);
        List<TransactionPO> transactionPOList = entityList2poList(pageInfo.getList());
        return TransactionListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllTransactionPO(transactionPOList)
                .build();
    }

    @Override
    public TransactionListReply selectList(TransactionPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        TransactionPO po = request.getTransactionPO();
        Transaction transaction = po2entity(po);
        QueryWrapper<Transaction> queryWrapper = new QueryWrapper<>(transaction);
        List<Transaction> transactionList = transactionService.list(queryWrapper);
        PageInfo<Transaction> pageInfo = new PageInfo<>(transactionList);
        List<TransactionPO> transactionPOList = entityList2poList(pageInfo.getList());
        return TransactionListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllTransactionPO(transactionPOList)
                .build();
    }

    @Override
    public TransactionListReply insertBatch(TransactionListRequest request) {
        List<TransactionPO> dataList = request.getTransactionPOList();
        List<Transaction> entityList = poList2entityList(dataList);

        transactionService.saveBatch(entityList);

        List<TransactionPO> transactionPOList = entityList2poList(entityList);
        return TransactionListReply.newBuilder()
                .addAllTransactionPO(transactionPOList)
                .build();
    }

    @Override
    public TransactionReply updateBatch(TransactionListRequest request) {
        List<TransactionPO> dataList = request.getTransactionPOList();
        List<Transaction> entityList = poList2entityList(dataList);
        boolean b = transactionService.updateBatchById(entityList);
        return TransactionReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public TransactionReply removeAll(TransactionIdsRequest request) {
        boolean b = transactionService.removeByIds(request.getIdList());
        return TransactionReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserTransactionReply getUserTransactionList(UserTransactionRequest request) {

        PageHelper.startPage(request.getPage(), request.getLimit(),false);
        List<Transaction> list = transactionMapper.getUserTransactionList(request.getBeginTime(),
                request.getBeginTime(), request.getSymbol(), request.getType(), request.getUid());
        if (list.isEmpty()) {
            return UserTransactionReply.newBuilder().setCount(0).addAllTransactionList(new ArrayList<>()).build();
        }
        List<UserTransactionList> lists = new ArrayList<>();
        for (Transaction transaction : list) {
            UserTransactionList userTransactionList = UserTransactionList.newBuilder().
                    setAmount(transaction.getAmount().toString()).
                    setCtime(DateUtils.format(transaction.getCtime())).
                    setType(transaction.getScene()).
                    setSymbol("USDT").
                    setContractName("").build();
            lists.add(userTransactionList);
        }

        return UserTransactionReply.newBuilder().setCount(transactionMapper.count(request.getBeginTime(),
                request.getBeginTime(), request.getSymbol(), request.getType(), request.getUid())).addAllTransactionList(lists).build();
    }

    @SneakyThrows
    private List<Transaction> poList2entityList(List<TransactionPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(Transaction.class, poList);
    }

    @SneakyThrows
    private List<TransactionPO> entityList2poList(List<Transaction> entityList) {
        return ProtoBeanUtils.toProtoBeanList(TransactionPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private TransactionPO entity2po(Transaction entity) {
        TransactionPO.Builder builder = TransactionPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private Transaction po2entity(TransactionPO po) {
        return ProtoBeanUtils.toPojoBean(Transaction.class, po);
    }
}
