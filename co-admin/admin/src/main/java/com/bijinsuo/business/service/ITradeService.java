package com.bijinsuo.business.service;


import com.bijinsuo.business.entity.Trade;

import java.util.List;

public interface ITradeService {
  List<Trade> selectTradeList(Trade trade);
}
