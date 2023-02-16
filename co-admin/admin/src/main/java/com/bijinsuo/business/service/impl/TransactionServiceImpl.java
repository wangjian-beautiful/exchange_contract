package com.bijinsuo.system.service.impl;

import java.util.List;

import com.bijinsuo.business.entity.Transaction;
import com.bijinsuo.business.repository.TransactionMapper;
import com.bijinsuo.business.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bijinsuo.common.core.text.Convert;

/**
 * 交易流水Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-09
 */
@Service
public class TransactionServiceImpl implements ITransactionService
{
    @Autowired
    private TransactionMapper transactionMapper;

    /**
     * 查询交易流水
     *
     * @param id 交易流水主键
     * @return 交易流水
     */
    @Override
    public Transaction selectTransactionById(Long id)
    {
        return transactionMapper.selectTransactionById(id);
    }

    /**
     * 查询交易流水列表
     *
     * @param transaction 交易流水
     * @return 交易流水
     */
    @Override
    public List<Transaction> selectTransactionList(Transaction transaction)
    {
        return transactionMapper.selectTransactionList(transaction);
    }

    /**
     * 新增交易流水
     *
     * @param transaction 交易流水
     * @return 结果
     */
    @Override
    public int insertTransaction(Transaction transaction)
    {
        return transactionMapper.insertTransaction(transaction);
    }

    /**
     * 修改交易流水
     *
     * @param transaction 交易流水
     * @return 结果
     */
    @Override
    public int updateTransaction(Transaction transaction)
    {
        return transactionMapper.updateTransaction(transaction);
    }

    /**
     * 批量删除交易流水
     *
     * @param ids 需要删除的交易流水主键
     * @return 结果
     */
    @Override
    public int deleteTransactionByIds(String ids)
    {
        return transactionMapper.deleteTransactionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交易流水信息
     *
     * @param id 交易流水主键
     * @return 结果
     */
    @Override
    public int deleteTransactionById(Long id)
    {
        return transactionMapper.deleteTransactionById(id);
    }
}
