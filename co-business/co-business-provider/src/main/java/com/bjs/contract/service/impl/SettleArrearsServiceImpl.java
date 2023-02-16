package com.bjs.contract.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bijinsuo.common.domain.AccountDTO;
import com.bijinsuo.common.domain.FundingRateSettleArrearsDTO;
import com.bijinsuo.common.redis.utils.RedisCacheUtil;
import com.bijinsuo.common.redis.utils.RedisLockUtil;
import com.bijinsuo.common.utils.entity.OrderCancelResultDTO;
import com.bijinsuo.common.utils.enums.AccountTypeEnum;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.service.CoOrderService;
import com.bjs.contract.service.CoPositionOrderService;
import com.bjs.contract.service.SettleArrearsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Watson
 */
@Service
@Slf4j
public class SettleArrearsServiceImpl implements SettleArrearsService {

    @Resource
    AccountAction accountAction;
    @Resource
    CoOrderService coOrderService;
    @Resource
    CoPositionOrderService coPositionOrderService;

    /**
     * 锁定用户 分布式锁的时间
     */
    private final Long USER_LOCK_LEASE_TIME_SECOND = 8 * 60l;

    /**
     * 等待撤销委托单子的最大时间
     */
    private final Integer WAIT_CANCEL_ORDER_TIME_SECOND = 3 * 60;
    /**
     * 等待平仓的最大时间
     */
    private final Integer WAIT_CLOSE_POSITION_TIME_SECOND = 3 * 60;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleArrears(FundingRateSettleArrearsDTO dto) {
        //查询一次account 是否真的还欠钱
        //如果还欠钱则锁定用户
        //判断用户是否有委托单 > 有则去撤销>等待撤销完毕>再查询用户是否还欠钱>还是欠钱则去给用户下一个平所有仓位的市价委托单
        //调用account
        AccountDTO arrearsAccount = getArrearsAccount(dto.getUid());
        if (arrearsAccount != null && arrearsAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            //真有欠钱,锁定账户
            String lockKey = RedisCacheUtil.getUserLockKey(dto.getUid());
            //只等待1秒，获取不到锁，就直接return。因为表示 其他的爆仓或者穿仓业务正在处理这个用户
            boolean locked = RedisLockUtil.instance().tryLock(lockKey, 1, USER_LOCK_LEASE_TIME_SECOND, TimeUnit.SECONDS);
            if (locked) {
                try {
                    afterLockUser(dto);
                } catch (Exception e) {
                    log.error("handleArrears error", e);
                    throw new RuntimeException(e);
                } finally {
                    RedisLockUtil.instance().unLock(lockKey);
                }

            } else {
                //获取锁失败，代表其他爆仓或者穿仓业务在加锁，那么这里放弃
                log.info("get user lock failed,other business has owned");
                return;
            }
        } else {
            log.info("uid {} arrears is zero");
        }
    }

    private void afterLockUser(FundingRateSettleArrearsDTO dto) throws InterruptedException {
        //尝试撤销委托单
        boolean isCancelUserOrdersSuc = cancelUserOrder(dto.getUid());
        if (isCancelUserOrdersSuc) {
            log.info("uid{} cancel entrust order suc");
            //撤销委托成功之后再去查询account.
            AccountDTO arrearsAccount = getArrearsAccount(dto.getUid());
            if (arrearsAccount != null && arrearsAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                //还是有欠款。那么开始把用户全部仓位平仓
                boolean isCloseAll = coPositionOrderService.sysCloseUserAllPosition(dto.getUid(), WAIT_CLOSE_POSITION_TIME_SECOND);
                if (isCloseAll) {
                    log.info("uid{} close user position suc,settle arrears finish");
                    return;
                } else {
                    throw new RuntimeException("settle arrears close user position failed");
                }
            } else {
                log.info("uid {} arrears is zero with after cancel entrust order");
                //如果欠款没有了，则代表撤销了的单子 回退的金额 把欠款 补齐了
                return;
            }
        } else {
            throw new RuntimeException("settle arrears cancel user entrust orders failed");
        }
    }


    private AccountDTO getArrearsAccount(Long uid) {
        return accountAction.getByUidAndType(uid, Long.valueOf(AccountTypeEnum.deductCapital.getCode()));
    }


    private boolean cancelUserOrder(Long uid) {
        List<OrderCancelResultDTO> cancelResultDTOList = coOrderService.cancelUserOrders(uid, null, null, WAIT_CANCEL_ORDER_TIME_SECOND);
        if (CollectionUtil.isEmpty(cancelResultDTOList)) {
            return true;
        }
        for (OrderCancelResultDTO resultDTO : cancelResultDTOList) {
            if (!resultDTO.isStatus()) {
                return false;
            }
        }
        return true;
    }
}


