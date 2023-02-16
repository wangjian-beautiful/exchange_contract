package com.bijinsuo.business.service.impl;

import com.bijinsuo.business.entity.CoTriggerOrder;
import com.bijinsuo.business.repository.CoTriggerOrderMapper;
import com.bijinsuo.business.service.ICoTriggerOrderService;
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
public class CoTriggerOrderServiceImpl implements ICoTriggerOrderService {
  @Autowired
  private CoTriggerOrderMapper orderMapper;

  @Override
  public List<CoTriggerOrder> selectOrderList(CoTriggerOrder order) {
    return orderMapper.selectCoTriggerOrderList(order);
  }
}
