package com.bjs.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjs.contract.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 账户明细
 *
 * @author bjs code generator
 * @date 2022-11-11 11:27:52
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 修改用户金额
     *
     * @author nike
     * @date 2022/11/7 15:00
     * @param uid 用户id
     * @param symbol 币对
     * @param amount   金额
     */
   void updateUserBalance(@Param("uid") Long uid, @Param("symbol") String symbol,
                          @Param("amount") BigDecimal amount,@Param("type")Integer type);


   /**
    * 用户账户余额归零 用于爆仓 穿仓
    *
    * @author nike
    * @date 2022/11/15 10:18
    * @param uid   用户id
    */
   void updateUserBalanceZero(@Param("uid") Long uid);


   BigDecimal getUserTagAccount(@Param("uid") Long uid, @Param("tag") String tag);
}
