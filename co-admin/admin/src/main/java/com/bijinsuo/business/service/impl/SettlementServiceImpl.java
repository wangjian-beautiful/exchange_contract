package com.bijinsuo.business.service.impl;

import com.bijinsuo.business.entity.Settlement;
import com.bijinsuo.business.repository.SettlementMapper;
import com.bijinsuo.business.service.ISettlementService;
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
public class SettlementServiceImpl implements ISettlementService {
  @Autowired
  private SettlementMapper settlementMapper;

  @Override
  public List<Settlement> selectSettlementList(Settlement settlement) {
    return settlementMapper.selectSettlementList(settlement);
  }
}
