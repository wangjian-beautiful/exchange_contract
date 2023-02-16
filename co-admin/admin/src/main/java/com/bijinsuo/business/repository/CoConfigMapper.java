package com.bijinsuo.business.repository;

import com.bijinsuo.business.entity.CoConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 合约配置Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Repository
public interface CoConfigMapper {
  /**
   * 查询合约配置
   *
   * @param id 合约配置主键
   * @return 合约配置
   */
  public CoConfig selectCoConfigById(Long id);

  /**
   * 查询合约配置列表
   *
   * @param coConfig 合约配置
   * @return 合约配置集合
   */
  public List<CoConfig> selectCoConfigList(CoConfig coConfig);

  /**
   * 新增合约配置
   *
   * @param coConfig 合约配置
   * @return 结果
   */
  public int insertCoConfig(CoConfig coConfig);

  /**
   * 修改合约配置
   *
   * @param coConfig 合约配置
   * @return 结果
   */
  public int updateCoConfig(CoConfig coConfig);

  /**
   * 删除合约配置
   *
   * @param id 合约配置主键
   * @return 结果
   */
  public int deleteCoConfigById(Long id);

  /**
   * 批量删除合约配置
   *
   * @param ids 需要删除的数据主键集合
   * @return 结果
   */
  public int deleteCoConfigByIds(String[] ids);
}
