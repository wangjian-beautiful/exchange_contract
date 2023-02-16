package com.bijinsuo.business.service.impl;

import com.bijinsuo.business.entity.Position;
import com.bijinsuo.business.repository.PositionMapper;
import com.bijinsuo.business.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-07
 */
@Service
public class PositionServiceImpl implements IPositionService {
  @Autowired
  private PositionMapper positionMapper;

  @Override
  public List<Position> selectPositionList(Position position) {
    return positionMapper.selectCoPositionOrderList(position);
  }
}
