package com.bijinsuo.business.web;

import com.bijinsuo.business.entity.User;
import com.bijinsuo.business.service.IUserService;
import com.bijinsuo.common.annotation.Log;
import com.bijinsuo.common.core.controller.BaseController;
import com.bijinsuo.common.core.domain.AjaxResult;
import com.bijinsuo.common.core.page.TableDataInfo;
import com.bijinsuo.common.enums.BusinessType;
import com.bijinsuo.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 *
 * @author ruoyi
 * @date 2022-11-07
 */
@Controller
@RequestMapping("/business/user")
public class UserController extends BaseController {
  private String prefix = "business/user";

  @Autowired
  private IUserService userService;

  @RequiresPermissions("business:user:view")
  @GetMapping()
  public String user() {
    return prefix + "/list";
  }

  /**
   * 查询用户列表
   */
  @RequiresPermissions("business:user:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(User user) {
    startPage();
    List<User> list = userService.selectUserList(user);
    return getDataTable(list);
  }

  /**
   * 导出用户列表
   */
  @RequiresPermissions("business:user:export")
  @Log(title = "用户", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  @ResponseBody
  public AjaxResult export(User user) {
    List<User> list = userService.selectUserList(user);
    ExcelUtil<User> util = new ExcelUtil<User>(User.class);
    return util.exportExcel(list, "用户数据");
  }

  /**
   * 新增用户
   */
  @GetMapping("/add")
  public String add() {
    return prefix + "/add";
  }

  /**
   * 新增保存用户
   */
  @RequiresPermissions("business:user:add")
  @Log(title = "用户", businessType = BusinessType.INSERT)
  @PostMapping("/add")
  @ResponseBody
  public AjaxResult addSave(User user) {
    return toAjax(userService.insertUser(user));
  }

  /**
   * 修改用户
   */
  @RequiresPermissions("business:user:edit")
  @GetMapping("/edit/{uid}")
  public String edit(@PathVariable("uid") Long uid, ModelMap mmap) {
    User user = userService.selectUserByUid(uid);
    mmap.put("user", user);
    return prefix + "/edit";
  }

  @RequiresPermissions("business:user:edit")
  @PostMapping("/{uid}/switch/trade")
  @ResponseBody
  public AjaxResult switchTradeAuth(@PathVariable("uid") Long uid) {
    return toAjax(userService.switchTradeAuth(uid));
  }

  @RequiresPermissions("business:user:edit")
  @PostMapping("/{uid}/switch/transfer")
  @ResponseBody
  public AjaxResult switchTransferAuth(@PathVariable("uid") Long uid) {
    return toAjax(userService.switchTransferAuth(uid));
  }

  /**
   * 修改保存用户
   */
  @RequiresPermissions("business:user:edit")
  @Log(title = "用户", businessType = BusinessType.UPDATE)
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(User user) {
    return toAjax(userService.updateUser(user));
  }

  /**
   * 删除用户
   */
  @RequiresPermissions("business:user:remove")
  @Log(title = "用户", businessType = BusinessType.DELETE)
  @PostMapping("/remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
    return toAjax(userService.deleteUserByUids(ids));
  }
}
