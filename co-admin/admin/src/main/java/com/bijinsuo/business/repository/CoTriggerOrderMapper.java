package com.bijinsuo.business.repository;

import com.bijinsuo.business.entity.CoTriggerOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-10
 */
@Repository
public interface CoTriggerOrderMapper {

  /**
   * 查询【请填写功能名称】列表
   *
   * @param coTriggerOrder 【请填写功能名称】
   * @return 【请填写功能名称】集合
   */
  public List<CoTriggerOrder> selectCoTriggerOrderList(CoTriggerOrder coTriggerOrder);
}
