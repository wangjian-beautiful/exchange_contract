package com.bjs.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjs.contract.entity.CoPositionOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @author bjs code generator
 * @date 2022-11-11 17:58:51
 */
@Mapper
public interface CoPositionOrderMapper extends BaseMapper<CoPositionOrder> {

    @Update("update co_position_order set margin = margin - #{reduceMargin} where uid=#{uid} and symbol=#{symbol} and position_side =#{positionSide}  ")
    int reduceMargin(Long uid, String symbol, String positionSide, BigDecimal reduceMargin);

}
