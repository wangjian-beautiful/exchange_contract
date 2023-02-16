package com.bjs.contract.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.domain.MaintenanceMarginRateDTO;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.action.MaintenanceMarginRateAction;
import com.bjs.contract.entity.UserLeverage;
import com.bjs.contract.mapper.UserLeverageMapper;
import com.bjs.contract.service.CoOrderService;
import com.bjs.contract.service.CoPositionOrderService;
import com.bjs.contract.service.UserLeverageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 用户个人币队杠杆倍数表
 *
 * @author bjs code generator
 * @date 2022-11-30 11:36:13
 */
@Service
public class UserLeverageServiceImpl extends ServiceImpl<UserLeverageMapper, UserLeverage> implements UserLeverageService {

    @Resource
    CoOrderService coOrderService;
    @Resource
    CoPositionOrderService coPositionOrderService;
    @Resource
    MaintenanceMarginRateAction maintenanceMarginRateAction;

    private final Integer DEFAULT_LEVERAGE = 20;
    private final Integer MIN_LEVERAGE = 1;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLeverage(Long uid, String symbol, Integer newLeverage) {
        //锁住杠杆
        UserLeverage curLeverage = findByUidAndSymbolWithLock(uid, symbol);
        if (curLeverage == null) {
            //不存在则初始化
            initOne(uid, symbol, newLeverage);
            return;
        } else {
            if (newLeverage.equals(curLeverage.getLeverage())) {
                return;
            }
            //锁住持仓
            BigDecimal totalPositionQuote = coPositionOrderService.selectPositionNominalValueWithLock(uid, symbol, null);

            BigDecimal totalOrderQuote = coOrderService.selectUserOnTheWayTotalQuote(uid, symbol);

            BigDecimal totalNominalValue = NumberUtil.add(totalOrderQuote, totalPositionQuote);
            MaintenanceMarginRateDTO dto = null;
            if (totalNominalValue.compareTo(BigDecimal.ZERO) == 0) {
                //查询这个币队最大可配置杠杆倍数 ,因为维持保证金率那里是 左闭右开 所以 0是查不到对应的配置的
                dto = maintenanceMarginRateAction.symbolMaxLeverage(symbol);
            } else {
                //查询这个量对应的杠杆配置区间
                dto = maintenanceMarginRateAction.getByNominalValue(symbol, totalNominalValue);
            }
            if (dto == null || newLeverage > dto.getMaxLeverage()) {
                throw new BizException(CommonEnum.ORDER_LEVER_EXCEED_THE_LIMIT_ERROR);
            }
            //处理现有持仓
            if (totalPositionQuote.compareTo(BigDecimal.ZERO) > 0)
                coPositionOrderService.updatePositionForLeverageChange(curLeverage.getLeverage(), newLeverage, uid, symbol);

            //处理在途委托单
            if (totalOrderQuote.compareTo(BigDecimal.ZERO) > 0)
                coOrderService.handleNewAmountForNewLeverage(newLeverage, curLeverage, uid, symbol);

            curLeverage.setLeverage(newLeverage);
            super.updateById(curLeverage);
        }
    }


    @Override
    public UserLeverage findByUidAndSymbol(Long uid, String symbol) {
        return super.query().eq("uid", uid).eq("symbol", symbol).one();
    }

    @Override
    public UserLeverage findByUidAndSymbolAndNotExistInit(Long uid, String symbol) {
        UserLeverage userLeverage = findByUidAndSymbol(uid, symbol);
        if (userLeverage == null) {
            userLeverage = initOne(uid, symbol);
        }
        return userLeverage;
    }

    @Override
    public UserLeverage findByUidAndSymbolWithLock(Long uid, String symbol) {
        return super.query().eq("uid", uid).eq("symbol", symbol).last("for update").one();

    }

    @Override
    public UserLeverage findByUidAndSymbolWithLockAndNotExistInit(Long uid, String symbol) {
        UserLeverage userLeverage = findByUidAndSymbolWithLock(uid, symbol);
        if (userLeverage == null) {
            userLeverage = initOne(uid, symbol);
        }
        return userLeverage;
    }

    private UserLeverage initOne(Long uid, String symbol) {
        return initOne(uid, symbol, null);
    }

    private UserLeverage initOne(Long uid, String symbol, Integer newLeverage) {
        UserLeverage userLeverage = new UserLeverage();
        userLeverage.setUid(uid);
        userLeverage.setSymbol(symbol);
        userLeverage.setLeverage((newLeverage == null || newLeverage < MIN_LEVERAGE) ? DEFAULT_LEVERAGE : newLeverage);
        super.save(userLeverage);
        return userLeverage;
    }
}
