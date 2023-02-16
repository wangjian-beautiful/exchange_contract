package com.bijinsuo.business.service;

import com.bijinsuo.business.entity.Transaction;

import java.util.List;

/**
 * 交易流水Service接口
 *
 * @author ruoyi
 * @date 2022-11-09
 */
public interface ITransactionService
{
    /**
     * 查询交易流水
     *
     * @param id 交易流水主键
     * @return 交易流水
     */
    public Transaction selectTransactionById(Long id);

    /**
     * 查询交易流水列表
     *
     * @param transaction 交易流水
     * @return 交易流水集合
     */
    public List<Transaction> selectTransactionList(Transaction transaction);

    /**
     * 新增交易流水
     *
     * @param transaction 交易流水
     * @return 结果
     */
    public int insertTransaction(Transaction transaction);

    /**
     * 修改交易流水
     *
     * @param transaction 交易流水
     * @return 结果
     */
    public int updateTransaction(Transaction transaction);

    /**
     * 批量删除交易流水
     *
     * @param ids 需要删除的交易流水主键集合
     * @return 结果
     */
    public int deleteTransactionByIds(String ids);

    /**
     * 删除交易流水信息
     *
     * @param id 交易流水主键
     * @return 结果
     */
    public int deleteTransactionById(Long id);
}
