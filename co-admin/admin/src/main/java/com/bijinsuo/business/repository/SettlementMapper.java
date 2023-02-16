package com.bijinsuo.business.repository;

import com.bijinsuo.business.entity.Settlement;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-11
 */
@Repository
public interface SettlementMapper {

  /**
   * 查询【请填写功能名称】列表
   *
   * @param settlement 【请填写功能名称】
   * @return 【请填写功能名称】集合
   */
  public List<Settlement> selectSettlementList(Settlement settlement);
}
