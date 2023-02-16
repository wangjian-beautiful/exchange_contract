package com.bijinsuo.framework.shiro.service;

import com.bijinsuo.common.constant.Constants;
import com.bijinsuo.common.constant.UserConstants;
import com.bijinsuo.common.core.domain.entity.SysRole;
import com.bijinsuo.common.core.domain.entity.SysUser;
import com.bijinsuo.common.enums.UserStatus;
import com.bijinsuo.common.exception.user.UserBlockedException;
import com.bijinsuo.common.exception.user.UserDeleteException;
import com.bijinsuo.common.exception.user.UserNotExistsException;
import com.bijinsuo.common.exception.user.UserPasswordNotMatchException;
import com.bijinsuo.common.utils.*;
import com.bijinsuo.framework.manager.AsyncManager;
import com.bijinsuo.framework.manager.factory.AsyncFactory;
import com.bijinsuo.system.service.ISysMenuService;
import com.bijinsuo.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
  @Autowired
  private SysPasswordService passwordService;

  @Autowired
  private ISysUserService userService;

  @Autowired
  private ISysMenuService menuService;

  /**
   * 登录
   */
  public SysUser login(String username, String password) {
    // 验证码校验
//        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
    // 用户名或密码为空 错误
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
      throw new UserNotExistsException();
    }
    // 密码如果不在指定范围内 错误
    if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
        || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
      throw new UserPasswordNotMatchException();
    }

    // 用户名不在指定范围内 错误
    if (username.length() < UserConstants.USERNAME_MIN_LENGTH
        || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
      throw new UserPasswordNotMatchException();
    }

    // 查询用户信息
    SysUser user = userService.selectUserByLoginName(username);

    /**
     if (user == null && maybeMobilePhoneNumber(username))
     {
     user = userService.selectUserByPhoneNumber(username);
     }

     if (user == null && maybeEmail(username))
     {
     user = userService.selectUserByEmail(username);
     }
     */

    if (user == null) {
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
      throw new UserNotExistsException();
    }

    if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
      throw new UserDeleteException();
    }

    if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
      throw new UserBlockedException();
    }

    passwordService.validate(user, password);

    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
    setRolePermission(user);
    recordLoginInfo(user.getUserId());
    return user;
  }

  public void verifyQrCode(String username, String validateCode) {
    SysUser user = userService.selectUserByLoginName(username);
    if (StringUtils.isEmpty(user.getSecretKey())) {
      String secretKey = GoogleAuthenticator.createSecretKey();
      user.setSecretKey(secretKey);
      userService.updateUserInfo(user);
      throw new IllegalArgumentException();
    }
    GoogleAuthenticator.verify(user.getSecretKey(), validateCode);
  }

  public String createQrBase64String(String username) {
    String barCode = GoogleAuthenticator.getGoogleAuthenticatorBarCode(userService.selectUserByLoginName(username).getSecretKey(), username);
    return GoogleAuthenticator.createQRCodeBase64String(barCode);
  }

  /**
   * 设置角色权限
   *
   * @param user 用户信息
   */
  public void setRolePermission(SysUser user) {
    List<SysRole> roles = user.getRoles();
    if (!roles.isEmpty() && roles.size() > 1) {
      // 多角色设置permissions属性，以便数据权限匹配权限
      for (SysRole role : roles) {
        Set<String> rolePerms = menuService.selectPermsByRoleId(role.getRoleId());
        role.setPermissions(rolePerms);
      }
    }
  }

  /**
   * 记录登录信息
   *
   * @param userId 用户ID
   */
  public void recordLoginInfo(Long userId) {
    SysUser user = new SysUser();
    user.setUserId(userId);
    user.setLoginIp(ShiroUtils.getIp());
    user.setLoginDate(DateUtils.getNowDate());
    userService.updateUserInfo(user);
  }
}
