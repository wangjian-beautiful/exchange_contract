package com.bjs.contract.service.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bijinsuo.common.utils.enums.AccountTypeEnum;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.enums.TransactionSceneEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.entity.Transaction;
import com.bjs.contract.mapper.AccountMapper;
import com.bjs.contract.mapper.TransactionMapper;
import com.bjs.contract.proto.account.*;
import com.bjs.contract.service.AccountService;
import com.bjs.contract.entity.Account;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-11 11:27:52
 * @Description:
 */
@Slf4j
@DubboService(filter = {"SetTokenFilter", "ExtractTokenFilter"})
public class AccountBizServiceImpl implements AccountBizService {

    @Resource
    private AccountService accountService;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private TransactionMapper transactionMapper;

    @Override
    public AccountPO getById(AccountRequest request) {
        Account result = accountService.getById(request.getId());

        return entity2po(result);
    }

    @Override
    public AccountPO getByUidAndType(AccountRequest request) {
        return entity2po(accountService.getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getUid, request.getAccountPO().getUid())
                .eq(Account::getType, request.getAccountPO().getType())));
    }

    @Override
    public AccountReply freeze(AccountRequest request) {
        Account account = po2entity(request.getAccountPO());
        Account balanceAccount = accountService.getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getUid, account.getUid())
                .eq(Account::getType, 0));
        balanceAccount.setBalance(balanceAccount.getBalance().subtract(account.getBalance()));
        accountService.updateById(balanceAccount);
        Account freezeAccount = accountService.getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getUid, account.getUid())
                .eq(Account::getType, 1));
        if (freezeAccount == null) {
            freezeAccount = new Account();
            freezeAccount.setUid(account.getUid());
            freezeAccount.setType(1);
            freezeAccount.setBalance(account.getBalance());
            accountService.save(freezeAccount);
        } else {
            balanceAccount.setBalance(balanceAccount.getBalance().subtract(account.getBalance()));
            accountService.updateById(freezeAccount);
        }
        return AccountReply.newBuilder().setStatus(true).build();
    }


    /**
     * 处理资金费率扣减
     *
     * @param request 请求参数
     * @return com.bjs.contract.proto.account.UserAccountReply
     * @author nike
     * @date 2022/11/17 16:27
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserAccountReply accountRateDeduction(UserAccountTransferListRequest request) {
        List<UserAccountTransferPO> list = request.getUserAccountTransferPOList();

        List<UserAccountTransferResponse> responseList = new ArrayList<>();

        if (!list.isEmpty()) {
            for (UserAccountTransferPO po : list) {
                UserAccountTransferResponse response;
                //查询可用
                Account normal = getUserAccount(po.getUid(), po.getSymbol(),
                        AccountTypeEnum.normal.getCode());

                Account deductCapital = getUserAccount(po.getUid(), po.getSymbol(),
                        AccountTypeEnum.deductCapital.getCode());
//
                Transaction transaction = new Transaction();


                Account systemNormal = getUserAccount(-1L, po.getSymbol(),
                        AccountTypeEnum.normal.getCode());
                if (null == systemNormal) {
                    systemNormal = new Account();
                    systemNormal.setBalance(new BigDecimal(po.getAmount()));
                    systemNormal.setUid(-1L);
                    systemNormal.setType(AccountTypeEnum.normal.getCode());
                    systemNormal.setTag(po.getSymbol());
                    systemNormal.setCtime(new Date());

                    transaction.setToBalance(new BigDecimal(po.getAmount()));
                    accountMapper.insert(systemNormal);
                } else {
                    transaction.setToBalance(systemNormal.getBalance().add(new BigDecimal(po.getAmount())));
                    accountMapper.updateUserBalance(-1L, po.getSymbol(), new BigDecimal(po.getAmount()),
                            AccountTypeEnum.normal.getCode());
                }

                transaction.setFromUid(po.getUid());
                transaction.setToUid(-1L);
                transaction.setToType(AccountTypeEnum.normal.getCode());

                transaction.setMeta("资金费率扣减");
                transaction.setScene("FUNDING_RATE_DEDUCTION");
                transaction.setRefType("account");
                transaction.setOpUid(po.getUid());
                transaction.setOpIp("");
                transaction.setCtime(new Date());
                transaction.setMtime(new Date());

                BigDecimal amount = new BigDecimal(po.getAmount());

                if (amount.compareTo(normal.getBalance()) > 0) {

                    //扣减可用余额
                    transaction.setFromType(AccountTypeEnum.normal.getCode());
                    transaction.setFromBalance(BigDecimal.ZERO);
                    transaction.setAmount(amount);
                    transaction.setRefId(normal.getId());
                    transactionMapper.insert(transaction);
                    accountMapper.updateUserBalance(po.getUid(), po.getSymbol(), normal.getBalance().negate(),
                            AccountTypeEnum.normal.getCode());


                    //添加欠款账户余额
//                    transaction.setMeta("资金费率扣减-欠款");
//                    transaction.setScene("FUNDING_RATE_DEDUCTION_ARREARS");
//                    transaction.setFromType(AccountTypeEnum.normal.getCode());
//                    transaction.setToType(AccountTypeEnum.deductCapital.getCode());
//                    transaction.setToUid(po.getUid());
//
//                    transaction.setAmount(amount);
                    if (null == deductCapital) {
                        deductCapital = new Account();
                        deductCapital.setBalance(amount.subtract(normal.getBalance()));
                        deductCapital.setUid(po.getUid());
                        deductCapital.setType(AccountTypeEnum.deductCapital.getCode());
                        deductCapital.setTag(po.getSymbol());
                        deductCapital.setCtime(new Date());
                        accountMapper.insert(deductCapital);
//                        transaction.setFromBalance(amount);
                    } else {
//                        transaction.setToBalance(deductCapital.getBalance().add(amount));
                        accountMapper.updateUserBalance(po.getUid(), po.getSymbol(), amount, AccountTypeEnum.deductCapital.getCode());
                    }
//                    transaction.setRefId(deductCapital.getId());
//                    transactionMapper.insert(transaction);
                    response = UserAccountTransferResponse.newBuilder().setUid(po.getUid()).
                            setArrears(amount.subtract(normal.getBalance()).toString()).build();
                    responseList.add(response);
                } else {
                    transaction.setFromType(AccountTypeEnum.normal.getCode());
                    transaction.setFromBalance(normal.getBalance().subtract(amount));
                    transaction.setAmount(amount);
                    transaction.setRefId(normal.getId());
                    transactionMapper.insert(transaction);
                    accountMapper.updateUserBalance(po.getUid(), po.getSymbol(), new BigDecimal(po.getAmount()).negate(),
                            AccountTypeEnum.normal.getCode());
                }

            }
        }
        return UserAccountReply.newBuilder().addAllUserAccountTransferResponse(responseList).build();
    }


    /**
     * 处理资金费率增加
     *
     * @param request 请求参数
     * @return com.bjs.contract.proto.account.AccountReply
     * @author nike
     * @date 2022/11/17 16:27
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AccountReply accountRateAdd(UserAccountTransferListRequest request) {
        List<UserAccountTransferPO> list = request.getUserAccountTransferPOList();
        if (!list.isEmpty()) {
            for (UserAccountTransferPO po : list) {


                //查询可用
                Account normal = getUserAccount(po.getUid(), po.getSymbol(),
                        AccountTypeEnum.normal.getCode());

                Account systemNormal = getUserAccount(-1L, po.getSymbol(),
                        AccountTypeEnum.normal.getCode());

                Transaction transaction = new Transaction();
                if (null == systemNormal) {
                    systemNormal = new Account();
                    systemNormal.setBalance(new BigDecimal(po.getAmount()).negate());
                    systemNormal.setUid(-1L);
                    systemNormal.setType(AccountTypeEnum.normal.getCode());
                    systemNormal.setTag(po.getSymbol());
                    systemNormal.setCtime(new Date());

                    transaction.setToBalance(new BigDecimal(po.getAmount()));
                    accountMapper.insert(systemNormal);
                } else {
                    transaction.setToBalance(systemNormal.getBalance().subtract(new BigDecimal(po.getAmount())));
                    accountMapper.updateUserBalance(-1L, po.getSymbol(), new BigDecimal(po.getAmount()).negate(),
                            AccountTypeEnum.normal.getCode());
                }

                BigDecimal amount = deductCapital(po.getUid(), new BigDecimal(po.getAmount()), po.getSymbol());

                transaction.setFromUid(-1L);
                transaction.setToUid(po.getUid());
                transaction.setToType(AccountTypeEnum.normal.getCode());
                transaction.setToBalance(normal.getBalance().add(amount));
                transaction.setMeta("资金费率增加");
                transaction.setScene("FUNDING_RATE_ADD");
                transaction.setRefType("account");
                transaction.setOpUid(po.getUid());
                transaction.setOpIp("");
                transaction.setCtime(new Date());
                transaction.setMtime(new Date());
                transaction.setFromType(AccountTypeEnum.normal.getCode());
                transaction.setFromBalance(new BigDecimal(po.getAmount()));
                transaction.setAmount(new BigDecimal(po.getAmount()));
                transaction.setRefId(normal.getId());
                transactionMapper.insert(transaction);
                accountMapper.updateUserBalance(po.getUid(), po.getSymbol(), new BigDecimal(po.getAmount()),
                        AccountTypeEnum.normal.getCode());
            }
        }
        return AccountReply.newBuilder()
                .setStatus(true)
                .setMessage("success")
                .build();
    }

    private BigDecimal deductCapital(Long uid, BigDecimal amount, String symbol) {
        Account account = getUserAccount(uid, symbol,
                AccountTypeEnum.deductCapital.getCode());
        BigDecimal deductCapital;
        if (account != null) {
            deductCapital = account.getBalance();
            BigDecimal surplus = amount.subtract(deductCapital);
            if (surplus.compareTo(BigDecimal.ZERO) > 0) {
                accountMapper.updateUserBalance(uid, symbol, amount.negate(),
                        AccountTypeEnum.deductCapital.getCode());
                return surplus;
            } else {
                accountMapper.updateUserBalance(uid, symbol, amount.negate(),
                        AccountTypeEnum.deductCapital.getCode());
                return BigDecimal.ZERO;
            }
        }
        return amount;
    }


    @Override
    public AccountReply insertOne(AccountRequest request) {
        AccountPO po = request.getAccountPO();
        Account entity = po2entity(po);

        boolean result = accountService.save(entity);
        AccountPO res = entity2po(entity);
        return AccountReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setAccountPO(res)
                .build();
    }

    @Override
    public AccountReply updateById(AccountRequest request) {
        AccountPO po = request.getAccountPO();
        Account entity = po2entity(po);

        boolean b = accountService.updateById(entity);
        return AccountReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public AccountReply removeById(AccountRequest request) {
        boolean b = accountService.removeById(request.getId());
        return AccountReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public AccountListReply selectListByIds(AccountIdsRequest request) {
        List<Account> accountList = accountService.listByIds(request.getIdList());
        List<AccountPO> accountPOList = entityList2poList(accountList);
        return AccountListReply.newBuilder()
                .addAllAccountPO(accountPOList)
                .build();
    }

    @Override
    public AccountListReply selectAll(AccountPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<Account> accountList = accountService.list();
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);
        List<AccountPO> accountPOList = entityList2poList(pageInfo.getList());
        return AccountListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllAccountPO(accountPOList)
                .build();
    }

    @Override
    public AccountListReply selectList(AccountPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        AccountPO po = request.getAccountPO();
        Account account = po2entity(po);
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>(account);
        List<Account> accountList = accountService.list(queryWrapper);
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);
        List<AccountPO> accountPOList = entityList2poList(pageInfo.getList());
        return AccountListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllAccountPO(accountPOList)
                .build();
    }

    @Override
    public AccountListReply insertBatch(AccountListRequest request) {
        List<AccountPO> dataList = request.getAccountPOList();
        List<Account> entityList = poList2entityList(dataList);

        accountService.saveBatch(entityList);

        List<AccountPO> accountPOList = entityList2poList(entityList);
        return AccountListReply.newBuilder()
                .addAllAccountPO(accountPOList)
                .build();
    }

    @Override
    public AccountReply updateBatch(AccountListRequest request) {
        List<AccountPO> dataList = request.getAccountPOList();
        List<Account> entityList = poList2entityList(dataList);
        boolean b = accountService.updateBatchById(entityList);
        return AccountReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public AccountReply removeAll(AccountIdsRequest request) {
        boolean b = accountService.removeByIds(request.getIdList());
        return AccountReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }


    /**
     * 操作用户资金
     *
     * @param accountOperate 参数
     * @return com.bjs.contract.proto.account.AccountReply
     * @author nike
     * @date 2022/11/14 18:24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountReply accountOperate(AccountOperate accountOperate) {
        TransactionSceneEnum transactionSceneEnum = TransactionSceneEnum.getOperation(accountOperate.getScene());

        //判断是否有类型
        if (null == transactionSceneEnum) {
            return AccountReply.newBuilder()
                    .setStatus(false)
                    .setMessage("fail")
                    .build();
        }
        //查询可用
        Account normal = getUserAccount(accountOperate.getUid(), accountOperate.getSymbol(),
                AccountTypeEnum.normal.getCode());
        //查询冻结
        Account frozen = getUserAccount(accountOperate.getUid(), accountOperate.getSymbol(),
                AccountTypeEnum.frozen.getCode());
        //查询保证金
        Account bond = getUserAccount(accountOperate.getUid(), accountOperate.getSymbol(),
                AccountTypeEnum.bond.getCode());

        //处理爆仓和穿仓类型 直接将用户余额变成0
        if (accountOperate.getScene().equals(TransactionSceneEnum.LIQUIDATION.getValue())
                || accountOperate.getScene().equals(TransactionSceneEnum.WEAR_POSITIONS.getValue())) {

            burstWarehouse(accountOperate, normal, bond);
            ventureCapital(accountOperate);
            accountService.redisInst(AccountTypeEnum.normal.getCode(), BigDecimal.ZERO, accountOperate.getUid());
            return AccountReply.newBuilder()
                    .setStatus(true)
                    .setMessage("success")
                    .build();
        }


        //判断手续费
        if (StringUtils.isNotBlank(accountOperate.getServiceCharge())) {
            BigDecimal serviceCharge = new BigDecimal(accountOperate.getServiceCharge());
            //需要添加的手续费大于0 就给系统账户添加手续费
            if (serviceCharge.compareTo(BigDecimal.ZERO) > 0) {
                serviceCharge(serviceCharge, accountOperate,transactionSceneEnum,normal,frozen,bond);
            }
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(accountOperate.getAmount()));
        transaction.setFromUid(accountOperate.getUid());
        transaction.setToUid(accountOperate.getUid());
        transaction.setMeta(transactionSceneEnum.getMsg());
        if (transactionSceneEnum.getNormal() != 0) {
            if (null == normal) {
                return AccountReply.newBuilder()
                        .setStatus(false)
                        .setMessage("fail")
                        .build();
            }
            try {
                normalAccount(accountOperate, transactionSceneEnum, normal, transaction);
            } catch (Exception e) {
                log.error("操作可用余额失败:", e);
                log.error("操作可用余额失败，操作信息：{}", accountOperate.getAllFields());
                return AccountReply.newBuilder()
                        .setStatus(false)
                        .setMessage("fail")
                        .build();
            }
        }
        if (transactionSceneEnum.getFrozen() != 0) {
            try {
                frozenAccount(accountOperate, transactionSceneEnum, frozen, transaction);
            } catch (Exception e) {
                log.error("操作冻结余额失败:", e);
                log.error("操作信息:{}", accountOperate.getAllFields());
                return AccountReply.newBuilder()
                        .setStatus(false)
                        .setMessage("fail")
                        .build();
            }
        }

        if (transactionSceneEnum.getBond() != 0) {
            try {
                bondAccount(accountOperate, transactionSceneEnum, bond, transaction);
            } catch (Exception e) {
                log.error("操作保证金余额失败:", e);
                log.error("操作信息:{}", accountOperate.getAllFields());
                return AccountReply.newBuilder()
                        .setStatus(false)
                        .setMessage("fail")
                        .build();
            }
        }
        transaction.setScene(transactionSceneEnum.getValue());
        transaction.setCtime(new Date());
        transaction.setMtime(new Date());
        transaction.setRefId(accountOperate.getRefId());
        transaction.setRefType(accountOperate.getRefType());
        transaction.setOpUid(accountOperate.getUid());
        transaction.setOpIp("");
        transactionMapper.insert(transaction);
        return AccountReply.newBuilder()
                .setStatus(true)
                .setMessage("success")
                .build();
    }

    private void serviceCharge(BigDecimal amount, AccountOperate accountOperate,TransactionSceneEnum transactionSceneEnum,
                               Account normal ,Account frozen,Account bond) {

        Account serviceCharge = getUserAccount(-1L, accountOperate.getSymbol(),
                AccountTypeEnum.serviceCharge.getCode());

        Transaction transaction = new Transaction();
        if (null == serviceCharge) {
            serviceCharge = new Account();
            serviceCharge.setUid(-1L);
            serviceCharge.setType(AccountTypeEnum.serviceCharge.getCode());
            serviceCharge.setBalance(amount);
            serviceCharge.setTag(accountOperate.getSymbol());
            serviceCharge.setCtime(new Date());
            accountMapper.insert(serviceCharge);
            transaction.setToBalance(amount);
        } else {
            accountMapper.updateUserBalance(-1L, accountOperate.getSymbol(),
                    amount, AccountTypeEnum.serviceCharge.getCode());
           BigDecimal totalAmount = amount.add(serviceCharge.getBalance());
            transaction.setToBalance(totalAmount);
        }

        transaction.setFromUid(accountOperate.getUid());

        if (transactionSceneEnum.getNormal()==-1){
            transaction.setFromType(AccountTypeEnum.normal.getCode());

            transaction.setFromBalance(normal.getBalance().subtract(amount));
        }

        if (transactionSceneEnum.getFrozen()==-1){
            transaction.setFromType(AccountTypeEnum.frozen.getCode());

            transaction.setFromBalance(frozen.getBalance().subtract(amount));
        }

        if (transactionSceneEnum.getBond()==-1){
            transaction.setFromType(AccountTypeEnum.bond.getCode());

            transaction.setFromBalance(bond.getBalance().subtract(amount));
        }

        transaction.setToUid(-1L);
        transaction.setToType(AccountTypeEnum.serviceCharge.getCode());
        transaction.setMeta("手续费");
        transaction.setAmount(amount);
        transaction.setScene(accountOperate.getScene());
        transaction.setRefType(accountOperate.getRefType());
        transaction.setRefId(accountOperate.getRefId());
        transaction.setOpUid(-1L);
        transaction.setOpIp("");
        transaction.setCtime(new Date());
        transaction.setMtime(new Date());
        transactionMapper.insert(transaction);
    }


    /**
     * 操作用户的可用账户
     *
     * @param accountOperate       请求参数
     * @param transactionSceneEnum 枚举
     * @param normal               可用账户
     * @param transaction          流水记录
     * @author nike
     * @date 2022/11/16 14:51
     */
    private void normalAccount(AccountOperate accountOperate, TransactionSceneEnum transactionSceneEnum,
                               Account normal, Transaction transaction) {
        BigDecimal amount = new BigDecimal(accountOperate.getAmount());
        if (StringUtils.isNotBlank(accountOperate.getServiceCharge())) {
            if (transactionSceneEnum.getNormal() == -1) {
                //加上需要扣除的手续费
                amount = amount.add(new BigDecimal(accountOperate.getServiceCharge()));
            } else {
                //减掉需要扣除的手续费
                amount = amount.subtract(new BigDecimal(accountOperate.getServiceCharge()));
            }
        }
        amount = amount.multiply(new BigDecimal(transactionSceneEnum.getNormal()));

        //判断是否是平仓 加上盈
        if (accountOperate.getScene().equals(TransactionSceneEnum.CLOSE_POSITION.getValue())) {
            amount = amount.add(new BigDecimal(accountOperate.getProfitAndLoss()));
        }

        //如果为扣减的情况 判断可用是否可用
        if (transactionSceneEnum.getNormal() == -1) {
            if (amount.negate().compareTo(normal.getBalance()) > 0) {
                throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
            }
            transaction.setFromType(AccountTypeEnum.normal.getCode());
            transaction.setFromBalance(amount.add(normal.getBalance()));
        } else {
            //检查用户是否有欠款
            amount = deductCapital(accountOperate.getUid(), amount, accountOperate.getSymbol());
            transaction.setToType(AccountTypeEnum.normal.getCode());
            transaction.setToBalance(amount.add(normal.getBalance()));
        }
        accountService.redisInst(normal.getType(), normal.getBalance().add(amount), normal.getUid());
        accountMapper.updateUserBalance(accountOperate.getUid(), accountOperate.getSymbol(),
                amount, AccountTypeEnum.normal.getCode());
    }


    /**
     * 操作用户的冻结账户
     *
     * @param accountOperate       请求参数
     * @param transactionSceneEnum 枚举
     * @param frozen               冻结账户
     * @param transaction          流水记录
     * @author nike
     * @date 2022/11/16 14:54
     */
    private void frozenAccount(AccountOperate accountOperate, TransactionSceneEnum transactionSceneEnum,
                               Account frozen, Transaction transaction) {

        BigDecimal amount = new BigDecimal(accountOperate.getAmount());
        if (StringUtils.isNotBlank(accountOperate.getServiceCharge())) {
            if (transactionSceneEnum.getFrozen() == -1) {
                //加上需要扣除的手续费
                amount = amount.add(new BigDecimal(accountOperate.getServiceCharge()));
            } else {
                //减掉需要扣除的手续费
                amount = amount.subtract(new BigDecimal(accountOperate.getServiceCharge()));
            }
        }

        amount = amount.multiply(new BigDecimal(transactionSceneEnum.getFrozen()));
        //操作为减 进入判断
        if (transactionSceneEnum.getFrozen() == -1) {
            //账户为null  无法减返回失败
            if (null == frozen) {
                throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
            }
            if (amount.negate().compareTo(frozen.getBalance()) > 0) {
                throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
            }

            transaction.setFromType(AccountTypeEnum.frozen.getCode());
            transaction.setFromBalance(amount.add(frozen.getBalance()));
            accountMapper.updateUserBalance(accountOperate.getUid(), accountOperate.getSymbol(),
                    amount, AccountTypeEnum.frozen.getCode());
        } else {
            //判断冻结是否为null 并且操作类型为加的话 就新增冻结账户
            if (null == frozen) {
                frozen = new Account();
                frozen.setUid(accountOperate.getUid());
                frozen.setType(AccountTypeEnum.frozen.getCode());
                frozen.setTag(accountOperate.getSymbol());
                frozen.setBalance(amount);
                frozen.setCtime(new Date());
                accountMapper.insert(frozen);
                transaction.setToBalance(amount);
            } else {
                accountMapper.updateUserBalance(accountOperate.getUid(), accountOperate.getSymbol(),
                        amount, AccountTypeEnum.frozen.getCode());
                transaction.setToBalance(amount.add(frozen.getBalance()));
            }
            accountService.redisInst(frozen.getType(), frozen.getBalance().add(amount), frozen.getUid());
            transaction.setToType(AccountTypeEnum.frozen.getCode());
        }
    }


    private void bondAccount(AccountOperate accountOperate, TransactionSceneEnum transactionSceneEnum,
                             Account bond, Transaction transaction) {
        BigDecimal amount = new BigDecimal(accountOperate.getAmount());

        amount = amount.multiply(new BigDecimal(transactionSceneEnum.getBond()));
        //操作为减 进入判断
        if (transactionSceneEnum.getBond() == -1) {
            //账户为null  无法减返回失败
            if (null == bond) {
                throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
            }
            if (amount.negate().compareTo(bond.getBalance()) > 0) {
                throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
            }
            transaction.setFromType(AccountTypeEnum.bond.getCode());
            transaction.setFromBalance(amount.add(bond.getBalance()));
            accountMapper.updateUserBalance(accountOperate.getUid(), accountOperate.getSymbol(),
                    amount, AccountTypeEnum.bond.getCode());
        } else {
            //判断保证金是否为null 并且操作类型为加的话 就新增保证金账户
            if (null == bond) {
                bond = new Account();
                bond.setUid(accountOperate.getUid());
                bond.setTag(accountOperate.getSymbol());
                bond.setBalance(amount);
                bond.setType(AccountTypeEnum.bond.getCode());
                bond.setCtime(new Date());
                accountMapper.insert(bond);
                transaction.setToBalance(amount);
            } else {
                accountMapper.updateUserBalance(accountOperate.getUid(), accountOperate.getSymbol(),
                        amount, AccountTypeEnum.bond.getCode());
                transaction.setToBalance(amount.add(bond.getBalance()));
            }
            accountService.redisInst(bond.getType(), bond.getBalance().add(amount), bond.getUid());
            transaction.setToType(AccountTypeEnum.bond.getCode());
        }
    }


    /**
     * 处理爆仓资金穿仓逻辑
     *
     * @param accountOperate 请求参数
     * @param normal         可用账户
     * @param bond           保证金账户
     * @author nike
     * @date 2022/11/16 13:43
     */
    private void burstWarehouse(AccountOperate accountOperate, Account normal, Account bond) {

        accountMapper.updateUserBalanceZero(accountOperate.getUid());

        if (normal != null) {
            Transaction transaction = new Transaction();
            burstWarehouseTransaction(transaction,accountOperate);
            transaction.setFromType(AccountTypeEnum.normal.getCode());
            transaction.setFromBalance(normal.getBalance());
            transaction.setToBalance(normal.getBalance());
            transaction.setAmount(normal.getBalance());
            transactionMapper.insert(transaction);
        }
        if (bond != null) {
            Transaction transaction = new Transaction();
            burstWarehouseTransaction(transaction,accountOperate);
            transaction.setFromType(AccountTypeEnum.bond.getCode());
            transaction.setFromBalance(bond.getBalance());
            transaction.setToBalance(bond.getBalance());
            transaction.setAmount(bond.getBalance());
            transactionMapper.insert(transaction);
        }
    }


    private void burstWarehouseTransaction(Transaction transaction,AccountOperate accountOperate){

        transaction.setFromUid(accountOperate.getUid());
        transaction.setToUid(0L);
        transaction.setToType(-1);
        if (accountOperate.getScene().equals(TransactionSceneEnum.LIQUIDATION.getValue())) {
            transaction.setMeta(TransactionSceneEnum.LIQUIDATION.getMsg());
        }

        if (accountOperate.getScene().equals(TransactionSceneEnum.WEAR_POSITIONS.getValue())) {
            transaction.setMeta(TransactionSceneEnum.WEAR_POSITIONS.getMsg());
        }
        transaction.setScene(accountOperate.getScene());
        transaction.setRefType(accountOperate.getRefType());
        transaction.setRefId(accountOperate.getRefId());
        transaction.setOpUid(accountOperate.getUid());
        transaction.setOpIp("");
        transaction.setCtime(new Date());
        transaction.setMtime(new Date());

    }


    /**
     * 处理爆仓的尾巴加到系统风险金里面
     *
     * @param accountOperate 请求参数
     * @author nike
     * @date 2022/11/16 14:23
     */
    private void ventureCapital(AccountOperate accountOperate) {

        Account ventureCapital = getUserAccount(-1L, accountOperate.getSymbol(),
                AccountTypeEnum.ventureCapital.getCode());

        Transaction transaction = new Transaction();

        BigDecimal amount = new BigDecimal(accountOperate.getVentureCapital());

        if (null == ventureCapital) {
            ventureCapital = new Account();
            ventureCapital.setUid(-1L);
            ventureCapital.setType(AccountTypeEnum.ventureCapital.getCode());
            ventureCapital.setBalance(amount);
            ventureCapital.setTag(accountOperate.getSymbol());
            ventureCapital.setCtime(new Date());
            transaction.setToBalance(amount);

        } else {
            accountMapper.updateUserBalance(-1L, accountOperate.getSymbol(),
                    amount, AccountTypeEnum.ventureCapital.getCode());
            amount = amount.add(ventureCapital.getBalance());
            transaction.setToBalance(amount);
        }

        transaction.setFromUid(accountOperate.getUid());
        transaction.setFromType(AccountTypeEnum.normal.getCode());
        transaction.setFromBalance(BigDecimal.ZERO);
        transaction.setToUid(-1L);
        transaction.setToType(AccountTypeEnum.ventureCapital.getCode());

        if (accountOperate.getScene().equals(TransactionSceneEnum.LIQUIDATION.getValue())) {
            transaction.setMeta(TransactionSceneEnum.LIQUIDATION.getMsg());
        }

        if (accountOperate.getScene().equals(TransactionSceneEnum.WEAR_POSITIONS.getValue())) {
            transaction.setMeta(TransactionSceneEnum.WEAR_POSITIONS.getMsg());
        }
        transaction.setAmount(amount);
        transaction.setScene(accountOperate.getScene());
        transaction.setRefType(accountOperate.getRefType());
        transaction.setRefId(accountOperate.getRefId());
        transaction.setOpUid(-1L);
        transaction.setOpIp("");
        transaction.setCtime(new Date());
        transaction.setMtime(new Date());
        transactionMapper.insert(transaction);
    }


    /**
     * 查询用户对应币对账户信息
     *
     * @param request 请求参数
     * @return com.bjs.contract.proto.account.AccountReply
     * @author nike
     * @date 2022/11/7 17:34
     */
    public AccountReply getUserBalance(AccountRequest request) {
        AccountPO po = request.getAccountPO();
        AccountPO account = entity2po(getUserAccount(po.getUid().getValue(), po.getTag(), po.getType().getValue()));
        accountService.redisInst(po.getType().getValue(), new BigDecimal(account.getBalance()), account.getUid().getValue());
        return AccountReply.newBuilder()
                .setStatus(true)
                .setMessage("success")
                .setAccountPO(account)
                .build();
    }

    @Override
    public AccountReply getUidBalance(AccountUserIdRequest request) {
        LambdaQueryWrapper<Account> query = new LambdaQueryWrapper<>();
        query.eq(Account::getUid, request.getUid());
        query.eq(Account::getType, request.getType());
        query.eq(Account::getTag, "USDT");
        Account account = accountMapper.selectOne(query);
        if (null == account) {
            return AccountReply.newBuilder()
                    .setStatus(false)
                    .setMessage("fail")
                    .build();
        } else {

            accountService.redisInst(account.getType(), account.getBalance(), account.getUid());
            AccountPO po = entity2po(account);
            return AccountReply.newBuilder()
                    .setStatus(true)
                    .setMessage("success")
                    .setAccountPO(po)
                    .build();
        }
    }


    private Account getUserAccount(Long uid, String symbol, Integer type) {
        LambdaQueryWrapper<Account> query = new LambdaQueryWrapper<>();
        query.eq(Account::getUid, uid);
        query.eq(Account::getTag, symbol);
        query.eq(Account::getType, type);
        return accountMapper.selectOne(query);
    }


    @SneakyThrows
    private List<Account> poList2entityList(List<AccountPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(Account.class, poList);
    }

    @SneakyThrows
    private List<AccountPO> entityList2poList(List<Account> entityList) {
        return ProtoBeanUtils.toProtoBeanList(AccountPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private AccountPO entity2po(Account entity) {
        AccountPO.Builder builder = AccountPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private Account po2entity(AccountPO po) {
        return ProtoBeanUtils.toPojoBean(Account.class, po);
    }


    public UserAccountListReply getUserAccountList(AccountUidRequest request) {
        return UserAccountListReply.newBuilder().
                setTotalBalance("0").
                setTotalBalanceSymbol("BTC").
                addAllAccountList(accountService.getUserAccountList(request.getUid())).build();
    }

    @Override
    public UserTagResponse getUserTagAccount(AccountUserTagRequest request) {

        BigDecimal amount=accountMapper.getUserTagAccount(request.getUid(),request.getTag());


        return UserTagResponse.newBuilder().setAmount(amount.toString()).build();
    }


    public AccountReply accountTransfer(AccountTransferReq req){
        accountService.accountTransfer(req.getUid(),new BigDecimal(req.getAmount()),req.getSymbol(),
                req.getType());

        return AccountReply.newBuilder().setStatus(true).build();
    }
}
