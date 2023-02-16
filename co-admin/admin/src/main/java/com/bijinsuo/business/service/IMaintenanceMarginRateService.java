package com.bijinsuo.business.service;

import com.bijinsuo.business.entity.MaintenanceMarginRate;

import java.util.List;

/**
 * 维持保证金率Service接口
 *
 * @author ruoyi
 * @date 2022-11-12
 */
public interface IMaintenanceMarginRateService {
  /**
   * 查询维持保证金率
   *
   * @param id 维持保证金率主键
   * @return 维持保证金率
   */
  public MaintenanceMarginRate selectMaintenanceMarginRateById(Long id);

  /**
   * 查询维持保证金率列表
   *
   * @param maintenanceMarginRate 维持保证金率
   * @return 维持保证金率集合
   */
  public List<MaintenanceMarginRate> selectMaintenanceMarginRateList(MaintenanceMarginRate maintenanceMarginRate);

  /**
   * 新增维持保证金率
   *
   * @param maintenanceMarginRate 维持保证金率
   * @return 结果
   */
  public int insertMaintenanceMarginRate(MaintenanceMarginRate maintenanceMarginRate);

  /**
   * 修改维持保证金率
   *
   * @param maintenanceMarginRate 维持保证金率
   * @return 结果
   */
  public int updateMaintenanceMarginRate(MaintenanceMarginRate maintenanceMarginRate);

  /**
   * 批量删除维持保证金率
   *
   * @param ids 需要删除的维持保证金率主键集合
   * @return 结果
   */
  public int deleteMaintenanceMarginRateByIds(String ids);

  /**
   * 删除维持保证金率信息
   *
   * @param id 维持保证金率主键
   * @return 结果
   */
  public int deleteMaintenanceMarginRateById(Long id);
}
