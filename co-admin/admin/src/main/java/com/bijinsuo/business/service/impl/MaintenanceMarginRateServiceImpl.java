package com.bijinsuo.business.service.impl;

import com.bijinsuo.business.entity.MaintenanceMarginRate;
import com.bijinsuo.business.repository.MaintenanceMarginRateMapper;
import com.bijinsuo.business.service.IMaintenanceMarginRateService;
import com.bijinsuo.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维持保证金率Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Service
public class MaintenanceMarginRateServiceImpl implements IMaintenanceMarginRateService {
  @Autowired
  private MaintenanceMarginRateMapper maintenanceMarginRateMapper;

  /**
   * 查询维持保证金率
   *
   * @param id 维持保证金率主键
   * @return 维持保证金率
   */
  @Override
  public MaintenanceMarginRate selectMaintenanceMarginRateById(Long id) {
    return maintenanceMarginRateMapper.selectMaintenanceMarginRateById(id);
  }

  /**
   * 查询维持保证金率列表
   *
   * @param maintenanceMarginRate 维持保证金率
   * @return 维持保证金率
   */
  @Override
  public List<MaintenanceMarginRate> selectMaintenanceMarginRateList(MaintenanceMarginRate maintenanceMarginRate) {
    return maintenanceMarginRateMapper.selectMaintenanceMarginRateList(maintenanceMarginRate);
  }

  /**
   * 新增维持保证金率
   *
   * @param maintenanceMarginRate 维持保证金率
   * @return 结果
   */
  @Override
  public int insertMaintenanceMarginRate(MaintenanceMarginRate maintenanceMarginRate) {
    return maintenanceMarginRateMapper.insertMaintenanceMarginRate(maintenanceMarginRate);
  }

  /**
   * 修改维持保证金率
   *
   * @param maintenanceMarginRate 维持保证金率
   * @return 结果
   */
  @Override
  public int updateMaintenanceMarginRate(MaintenanceMarginRate maintenanceMarginRate) {
    return maintenanceMarginRateMapper.updateMaintenanceMarginRate(maintenanceMarginRate);
  }

  /**
   * 批量删除维持保证金率
   *
   * @param ids 需要删除的维持保证金率主键
   * @return 结果
   */
  @Override
  public int deleteMaintenanceMarginRateByIds(String ids) {
    return maintenanceMarginRateMapper.deleteMaintenanceMarginRateByIds(Convert.toStrArray(ids));
  }

  /**
   * 删除维持保证金率信息
   *
   * @param id 维持保证金率主键
   * @return 结果
   */
  @Override
  public int deleteMaintenanceMarginRateById(Long id) {
    return maintenanceMarginRateMapper.deleteMaintenanceMarginRateById(id);
  }
}
