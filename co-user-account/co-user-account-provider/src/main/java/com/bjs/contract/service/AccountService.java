package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.Account;
import com.bjs.contract.proto.account.UserAccountList;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户明细
 *
 * @author bjs code generator
 * @date 2022-11-11 11:27:52
 */
public interface AccountService extends IService<Account> {

    /**
     * 划转金额
     *
     * @param uid    用户id
     * @param amount 数量
     * @param symbol 币对
     * @author nike
     * @date 2022/11/14 14:26
     */
    void accountTransfer(Long uid, BigDecimal amount, String symbol, String type);

    /**
     * 保存用户金额到redis
     *
     * @author nike
     * @date 2022/11/30 16:16
     * @param type 类型
     * @param amount 金额
     * @param uid   用户id
     */
    void redisInst(Integer type, BigDecimal amount, Long uid);

    /**
     * 查询用户所以账户信息
     *
     * @author nike
     * @date 2022/11/30 16:16
     * @param uid  用户id
     * @return java.util.List<com.bjs.contract.proto.account.UserAccountList>
     */
    List<UserAccountList> getUserAccountList(Long uid);
}
