package com.bijinsuo.business.repository;

import com.bijinsuo.business.entity.Account;
import com.bijinsuo.business.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-07
 */
@Repository
public interface UserMapper {
  /**
   * 查询用户
   *
   * @param uid 用户主键
   * @return 用户
   */
  public User selectUserByUid(Long uid);

  /**
   * 查询用户列表
   *
   * @param user 用户
   * @return 用户集合
   */
  public List<User> selectUserList(User user);

  /**
   * 新增用户
   *
   * @param user 用户
   * @return 结果
   */
  public int insertUser(User user);

  /**
   * 修改用户
   *
   * @param user 用户
   * @return 结果
   */
  public int updateUser(User user);

  /**
   * 删除用户
   *
   * @param uid 用户主键
   * @return 结果
   */
  public int deleteUserByUid(Long uid);

  /**
   * 批量删除用户
   *
   * @param uids 需要删除的数据主键集合
   * @return 结果
   */
  public int deleteUserByUids(String[] uids);

  int switchTradeAuthById(Long uid);

  int switchTransferAuthById(Long uid);

  Account findAccountByUid(Long uid);
}
