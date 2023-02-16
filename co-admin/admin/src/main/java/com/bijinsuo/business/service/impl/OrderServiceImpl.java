package com.bijinsuo.business.service.impl;

import com.bijinsuo.business.entity.Order;
import com.bijinsuo.business.repository.OrderMapper;
import com.bijinsuo.business.service.IOrderService;
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
public class OrderServiceImpl implements IOrderService {
  @Autowired
  private OrderMapper orderMapper;

  @Override
  public List<Order> selectOrderList(Order coOrder) {
    return orderMapper.selectOrderList(coOrder);
  }
}
