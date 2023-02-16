package com.bijinsuo.business.service;


import com.bijinsuo.business.entity.Order;

import java.util.List;

public interface IOrderService {
  List<Order> selectOrderList(Order coOrder);
}
