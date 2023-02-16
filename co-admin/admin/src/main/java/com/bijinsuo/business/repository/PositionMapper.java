package com.bijinsuo.business.repository;

import com.bijinsuo.business.entity.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-11
 */
@Repository
public interface PositionMapper {

  /**
   * 查询【请填写功能名称】列表
   */
  List<Position> selectCoPositionOrderList(Position position);
}
