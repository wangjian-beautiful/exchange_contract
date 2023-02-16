package com.bijinsuo.business.service.impl;

import java.util.Date;
import java.util.List;

import com.bijinsuo.business.entity.CoinPair;
import com.bijinsuo.business.repository.CoinPairMapper;
import com.bijinsuo.business.service.ICoinPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bijinsuo.common.core.text.Convert;

/**
 * 撮合币对配置Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Service
public class CoinPairServiceImpl implements ICoinPairService {
  @Autowired
  private CoinPairMapper coinPairMapper;

  /**
   * 查询撮合币对配置
   *
   * @param id 撮合币对配置主键
   * @return 撮合币对配置
   */
  @Override
  public CoinPair selectCoinPairById(Long id) {
    return coinPairMapper.selectCoinPairById(id);
  }

  /**
   * 查询撮合币对配置列表
   *
   * @param coinPair 撮合币对配置
   * @return 撮合币对配置
   */
  @Override
  public List<CoinPair> selectCoinPairList(CoinPair coinPair) {
    return coinPairMapper.selectCoinPairList(coinPair);
  }

  /**
   * 新增撮合币对配置
   *
   * @param coinPair 撮合币对配置
   * @return 结果
   */
  @Override
  public int insertCoinPair(CoinPair coinPair) {
    coinPair.setCtime(new Date());
    coinPair.setMtime(new Date());
    return coinPairMapper.insertCoinPair(coinPair);
  }

  /**
   * 修改撮合币对配置
   *
   * @param coinPair 撮合币对配置
   * @return 结果
   */
  @Override
  public int updateCoinPair(CoinPair coinPair) {
    coinPair.setMtime(new Date());
    return coinPairMapper.updateCoinPair(coinPair);
  }

  /**
   * 批量删除撮合币对配置
   *
   * @param ids 需要删除的撮合币对配置主键
   * @return 结果
   */
  @Override
  public int deleteCoinPairByIds(String ids) {
    return coinPairMapper.deleteCoinPairByIds(Convert.toStrArray(ids));
  }

  /**
   * 删除撮合币对配置信息
   *
   * @param id 撮合币对配置主键
   * @return 结果
   */
  @Override
  public int deleteCoinPairById(Long id) {
    return coinPairMapper.deleteCoinPairById(id);
  }
}
