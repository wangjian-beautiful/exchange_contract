package com.bijinsuo.business.service.impl;

import java.util.List;

import com.bijinsuo.business.entity.Trade;
import com.bijinsuo.business.repository.TradeMapper;
import com.bijinsuo.business.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class TradeServiceImpl implements ITradeService {
  @Autowired
  private TradeMapper tradeMapper;


  @Override
  public List<Trade> selectTradeList(Trade trade) {
    return tradeMapper.selectTradeList(trade);
  }
}
