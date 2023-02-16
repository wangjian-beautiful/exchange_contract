package com.bjs.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjs.contract.entity.MaintenanceMarginRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 维持保证金率
 *
 * @author bjs code generator
 * @date 2022-11-11 11:01:31
 */
@Mapper
public interface MaintenanceMarginRateMapper extends BaseMapper<MaintenanceMarginRate> {

    String getMinRate(@Param("symbol") String symbol);
}
