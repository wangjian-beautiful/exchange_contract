package com.bijinsuo.business.service;


import com.bijinsuo.business.entity.Settlement;

import java.util.List;

public interface ISettlementService {
  List<Settlement> selectSettlementList(Settlement settlement);
}
