package com.bjs.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjs.contract.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 交易流水
 *
 * @author bjs code generator
 * @date 2022-11-11 10:51:39
 */
@Mapper
public interface TransactionMapper extends BaseMapper<Transaction> {

    List<Transaction>  getUserTransactionList(@Param("beginTime") String beginTime, @Param("endTime") String endTime,
                                              @Param("type") String type, @Param("symbol") String symbol, @Param("uid") Long uid);

    int count(@Param("beginTime") String beginTime, @Param("endTime") String endTime,
              @Param("type") String type, @Param("symbol") String symbol, @Param("uid") Long uid);
}
