package com.bijinsuo.business.service.impl;

import java.util.List;

import com.bijinsuo.business.entity.Account;
import com.bijinsuo.business.entity.User;
import com.bijinsuo.business.repository.UserMapper;
import com.bijinsuo.business.service.IUserService;
import com.bijinsuo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bijinsuo.common.core.text.Convert;
import org.springframework.util.CollectionUtils;

/**
 * 用户Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-07
 */
@Service
public class UserServiceImpl implements IUserService
{
  @Autowired
  private UserMapper userMapper;

  /**
   * 查询用户
   *
   * @param uid 用户主键
   * @return 用户
   */
  @Override
  public User selectUserByUid(Long uid)
  {
    final var account = userMapper.findAccountByUid(uid);
    final var user = userMapper.selectUserByUid(uid);
    user.setAccount(account);
    return user;
  }

  /**
   * 查询用户列表
   *
   * @param user 用户
   * @return 用户
   */
  @Override
  public List<User> selectUserList(User user)
  {
    final var users = userMapper.selectUserList(user);
    if (!CollectionUtils.isEmpty(users)){
      users.forEach(u->u.setAccount(userMapper.findAccountByUid(u.getUid())));
    }
    return users;
  }

  /**
   * 新增用户
   *
   * @param user 用户
   * @return 结果
   */
  @Override
  public int insertUser(User user)
  {
    user.setCreateTime(DateUtils.getNowDate());
    return userMapper.insertUser(user);
  }

  /**
   * 修改用户
   *
   * @param user 用户
   * @return 结果
   */
  @Override
  public int updateUser(User user)
  {
    user.setUpdateTime(DateUtils.getNowDate());
    return userMapper.updateUser(user);
  }

  /**
   * 批量删除用户
   *
   * @param uids 需要删除的用户主键
   * @return 结果
   */
  @Override
  public int deleteUserByUids(String uids)
  {
    return userMapper.deleteUserByUids(Convert.toStrArray(uids));
  }

  /**
   * 删除用户信息
   *
   * @param uid 用户主键
   * @return 结果
   */
  @Override
  public int deleteUserByUid(Long uid)
  {
    return userMapper.deleteUserByUid(uid);
  }

  @Override
  public int switchTradeAuth(Long uid) {
    return userMapper.switchTradeAuthById(uid);
  }

  @Override
  public int switchTransferAuth(Long uid) {
    return userMapper.switchTransferAuthById(uid);
  }
}
