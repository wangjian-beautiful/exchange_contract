package com.bijinsuo.business.service;

import com.bijinsuo.business.entity.CoinPair;

import java.util.List;

/**
 * 撮合币对配置Service接口
 *
 * @author ruoyi
 * @date 2022-11-12
 */
public interface ICoinPairService
{
  /**
   * 查询撮合币对配置
   *
   * @param id 撮合币对配置主键
   * @return 撮合币对配置
   */
  public CoinPair selectCoinPairById(Long id);

  /**
   * 查询撮合币对配置列表
   *
   * @param coinPair 撮合币对配置
   * @return 撮合币对配置集合
   */
  public List<CoinPair> selectCoinPairList(CoinPair coinPair);

  /**
   * 新增撮合币对配置
   *
   * @param coinPair 撮合币对配置
   * @return 结果
   */
  public int insertCoinPair(CoinPair coinPair);

  /**
   * 修改撮合币对配置
   *
   * @param coinPair 撮合币对配置
   * @return 结果
   */
  public int updateCoinPair(CoinPair coinPair);

  /**
   * 批量删除撮合币对配置
   *
   * @param ids 需要删除的撮合币对配置主键集合
   * @return 结果
   */
  public int deleteCoinPairByIds(String ids);

  /**
   * 删除撮合币对配置信息
   *
   * @param id 撮合币对配置主键
   * @return 结果
   */
  public int deleteCoinPairById(Long id);
}