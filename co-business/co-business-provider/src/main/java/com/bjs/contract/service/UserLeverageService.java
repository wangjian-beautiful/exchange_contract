package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.UserLeverage;

/**
 * 用户个人币队杠杆倍数表
 *
 * @author bjs code generator
 * @date 2022-11-30 11:36:13
 */
public interface UserLeverageService extends IService<UserLeverage> {


    void updateLeverage(Long uid, String symbol, Integer newLeverage);

    /**
     * 查询
     */
    UserLeverage findByUidAndSymbol(Long uid, String symbol);

    /**
     * 查询，不存在则初始化落库返回
     */
    UserLeverage findByUidAndSymbolAndNotExistInit(Long uid, String symbol);

    /**
     * 查询且加锁
     */
    UserLeverage findByUidAndSymbolWithLock(Long uid,String symbol);

    /**
     * 查询且加锁， 如果不存在则初始化一个落库且返回
     */
    UserLeverage findByUidAndSymbolWithLockAndNotExistInit(Long uid, String symbol);


}
