package com.bijinsuo.business.service;



import com.bijinsuo.business.entity.CoTriggerOrder;

import java.util.List;

public interface ICoTriggerOrderService {
  List<CoTriggerOrder> selectOrderList(CoTriggerOrder order);
}
