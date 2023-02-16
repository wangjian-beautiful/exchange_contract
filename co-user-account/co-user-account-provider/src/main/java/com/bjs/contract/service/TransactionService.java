package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.Transaction;
import com.bjs.contract.proto.account.AccountOperate;

import java.math.BigDecimal;

/**
 * 交易流水
 *
 * @author bjs code generator
 * @date 2022-11-11 10:51:39
 */
public interface TransactionService extends IService<Transaction> {

    void transactionInst(AccountOperate accountOperate, BigDecimal amount,
                         BigDecimal fromBalance, BigDecimal toBalance,
                         Integer fromType, Integer toType, String msg);
}
