package com.bjs.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjs.contract.entity.CoOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author nike
 * @date 2022年11月10日 13:51
 */
@Mapper
public interface MatchCoOrderMapper extends BaseMapper<CoOrder> {

    List<CoOrder> getCoOrderAll(@Param("symbol") String symbol);
}
