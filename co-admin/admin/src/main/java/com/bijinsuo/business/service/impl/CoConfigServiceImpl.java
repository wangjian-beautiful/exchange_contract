package com.bijinsuo.business.service.impl;

import java.util.List;

import com.bijinsuo.business.entity.CoConfig;
import com.bijinsuo.business.repository.CoConfigMapper;
import com.bijinsuo.business.service.ICoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bijinsuo.common.core.text.Convert;

/**
 * 合约配置Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Service
public class CoConfigServiceImpl implements ICoConfigService
{
  @Autowired
  private CoConfigMapper coConfigMapper;

  /**
   * 查询合约配置
   *
   * @param id 合约配置主键
   * @return 合约配置
   */
  @Override
  public CoConfig selectCoConfigById(Long id)
  {
    return coConfigMapper.selectCoConfigById(id);
  }

  /**
   * 查询合约配置列表
   *
   * @param coConfig 合约配置
   * @return 合约配置
   */
  @Override
  public List<CoConfig> selectCoConfigList(CoConfig coConfig)
  {
    return coConfigMapper.selectCoConfigList(coConfig);
  }

  /**
   * 新增合约配置
   *
   * @param coConfig 合约配置
   * @return 结果
   */
  @Override
  public int insertCoConfig(CoConfig coConfig)
  {
    return coConfigMapper.insertCoConfig(coConfig);
  }

  /**
   * 修改合约配置
   *
   * @param coConfig 合约配置
   * @return 结果
   */
  @Override
  public int updateCoConfig(CoConfig coConfig)
  {
    return coConfigMapper.updateCoConfig(coConfig);
  }

  /**
   * 批量删除合约配置
   *
   * @param ids 需要删除的合约配置主键
   * @return 结果
   */
  @Override
  public int deleteCoConfigByIds(String ids)
  {
    return coConfigMapper.deleteCoConfigByIds(Convert.toStrArray(ids));
  }

  /**
   * 删除合约配置信息
   *
   * @param id 合约配置主键
   * @return 结果
   */
  @Override
  public int deleteCoConfigById(Long id)
  {
    return coConfigMapper.deleteCoConfigById(id);
  }
}
