package com.bjs.contract.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.enums.AccountTypeEnum;
import com.bijinsuo.common.utils.enums.TransactionSceneEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.entity.Account;
import com.bjs.contract.entity.Transaction;
import com.bjs.contract.entity.vo.TransferVO;
import com.bjs.contract.mapper.AccountMapper;
import com.bjs.contract.mapper.TransactionMapper;
import com.bjs.contract.proto.account.UserAccountList;
import com.bjs.contract.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账户明细
 *
 * @author bjs code generator
 * @date 2022-11-11 11:27:52
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private TransactionMapper transactionMapper;

    @Resource
    private RedisUtil redisUtil;

    public Account getUserAccount(Long uid, String symbol) {
        LambdaQueryWrapper<Account> query = new LambdaQueryWrapper<>();
        query.eq(Account::getUid, uid);
        query.eq(Account::getTag, symbol);
        return accountMapper.selectOne(query);
    }


    @Transactional
    public void accountTransfer(Long uid, BigDecimal amount,
                                String symbol, String type) {
        TransactionSceneEnum transactionSceneEnum = TransactionSceneEnum.getOperation(type);
        if (null == transactionSceneEnum) {
            throw new BizException("找不到对应的类型");
        }

        Transaction transaction = new Transaction();

        //查询该币对余额是否存在 不存在新增  存在修改
        Account account = getUserAccount(uid, symbol);
        if (type.equals(TransactionSceneEnum.CROSS_OUT.getValue())) {
            if (null == account) {
                throw new BizException("账户余额不足");
            } else {
                if (amount.compareTo(account.getBalance()) > 0) {
                    throw new BizException("账户余额不足");
                }
            }
            transaction.setFromUid(uid);
            transaction.setFromType(AccountTypeEnum.normal.getCode());
            transaction.setFromBalance(account.getBalance().subtract(amount));
            transaction.setToUid(uid);
            transaction.setToType(0);
            transaction.setToBalance(account.getBalance().add(amount));
        } else {
            if (null == account) {
                account = new Account();
                account.setBalance(amount);
                account.setUid(uid);
                account.setTag(symbol);
                account.setType(AccountTypeEnum.normal.getCode());
                account.setCtime(new Date());
                accountMapper.insert(account);
                return;
            }
            transaction.setFromUid(uid);
            transaction.setFromType(0);
            transaction.setFromBalance(account.getBalance().add(amount));
            transaction.setToUid(uid);
            transaction.setToType(AccountTypeEnum.normal.getCode());
            transaction.setToBalance(account.getBalance().subtract(amount));
        }
        amount = amount.multiply(new BigDecimal(transactionSceneEnum.getNormal()));
        accountMapper.updateUserBalance(uid, symbol, amount, AccountTypeEnum.normal.getCode());

        redisInst(account.getType(), account.getBalance().add(amount), account.getUid());

        transaction.setAmount(amount);
        transaction.setMeta(transactionSceneEnum.getValue());
        transaction.setScene(transactionSceneEnum.getMsg());
        transaction.setRefType("account");
        transaction.setRefId(account.getId());
        transaction.setOpUid(uid);
        transaction.setOpIp("");
        transaction.setCtime(new Date());
        transaction.setMtime(new Date());
        transactionMapper.insert(transaction);
    }

    /**
     * 将用户金额保存到redis
     *
     * @param type   类型
     * @param amount 金额
     * @param uid    用户id
     * @author nike
     * @date 2022/11/29 17:40
     */
    public void redisInst(Integer type, BigDecimal amount, Long uid) {
        if (type.equals(AccountTypeEnum.normal.getCode())) {
            redisUtil.set(RedisCacheKey.USER_ACCOUNT_NORMAL + uid, amount);
        }
        if (type.equals(AccountTypeEnum.frozen.getCode())) {
            redisUtil.set(RedisCacheKey.USER_ACCOUNT_FROZEN + uid, amount);
        }
        if (type.equals(AccountTypeEnum.bond.getCode())) {
            redisUtil.set(RedisCacheKey.USER_ACCOUNT_BOND + uid, amount);
        }
    }


    public List<UserAccountList> getUserAccountList(Long uid) {
        LambdaQueryWrapper<Account> query = new LambdaQueryWrapper<>();
        query.eq(Account::getUid, uid);
        query.eq(Account::getType, AccountTypeEnum.normal.getCode());
        List<Account> account = accountMapper.selectList(query);
        List<UserAccountList> list = new ArrayList<>();
        for (Account model : account) {
            BigDecimal frozen = getUserAccount(uid, model.getTag(), AccountTypeEnum.frozen.getCode());
            BigDecimal bond = getUserAccount(uid, model.getTag(), AccountTypeEnum.bond.getCode());

            list.add(UserAccountList.newBuilder().setSymbol(model.getTag()).
                    setCanUseAmount(model.getBalance().toString()).
                    setIsolateMargin("0").
                    setLockAmount(frozen.toString()).setTotalMargin(bond.toString()).
                    setTotalAmount(model.getBalance().add(frozen).add(bond).toString())
                    .build());
        }
        return list;
    }

    private BigDecimal getUserAccount(Long uid, String tag, Integer type) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUid, uid);
        queryWrapper.eq(Account::getType, type);
        queryWrapper.eq(Account::getTag, tag);
        Account account = accountMapper.selectOne(queryWrapper);

        return account == null ? BigDecimal.ZERO : account.getBalance().compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO : account.getBalance();
    }
}

