package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjs.contract.entity.Transaction;
import com.bjs.contract.mapper.TransactionMapper;
import com.bjs.contract.proto.account.AccountOperate;
import com.bjs.contract.service.TransactionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易流水
 *
 * @author bjs code generator
 * @date 2022-11-11 10:51:39
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements TransactionService {

    @Resource
    private TransactionMapper transactionMapper;

    public void transactionInst(AccountOperate accountOperate, BigDecimal amount,
                                BigDecimal fromBalance, BigDecimal toBalance,
                                Integer fromType, Integer toType, String msg) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setFromBalance(fromBalance);
        transaction.setToBalance(toBalance);
        transaction.setFromUid(accountOperate.getUid());
        transaction.setFromType(fromType);
        transaction.setToType(toType);
        transaction.setMeta(msg);
        transaction.setRefId(accountOperate.getRefId());
        transaction.setRefType(accountOperate.getRefType());
        transaction.setOpUid(accountOperate.getUid());
        transaction.setScene(accountOperate.getScene());
        transaction.setOpIp("");
        transaction.setCtime(new Date());
        transaction.setMtime(new Date());
        transactionMapper.insert(transaction);
    }


}
