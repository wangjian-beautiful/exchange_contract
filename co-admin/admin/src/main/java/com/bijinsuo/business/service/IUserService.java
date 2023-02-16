package com.bijinsuo.business.service;

import com.bijinsuo.business.entity.User;

import java.util.List;

/**
 * 用户Service接口
 *
 * @author ruoyi
 * @date 2022-11-07
 */
public interface IUserService {
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
   * 批量删除用户
   *
   * @param uids 需要删除的用户主键集合
   * @return 结果
   */
  public int deleteUserByUids(String uids);

  /**
   * 删除用户信息
   *
   * @param uid 用户主键
   * @return 结果
   */
  public int deleteUserByUid(Long uid);

  int switchTradeAuth(Long uid);
  int switchTransferAuth(Long uid);
}
