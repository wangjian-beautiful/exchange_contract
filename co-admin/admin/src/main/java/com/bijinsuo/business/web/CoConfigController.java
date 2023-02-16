package com.bijinsuo.business.web;

import java.util.List;

import com.bijinsuo.business.entity.CoConfig;
import com.bijinsuo.business.service.ICoConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bijinsuo.common.annotation.Log;
import com.bijinsuo.common.enums.BusinessType;
import com.bijinsuo.common.core.controller.BaseController;
import com.bijinsuo.common.core.domain.AjaxResult;
import com.bijinsuo.common.utils.poi.ExcelUtil;
import com.bijinsuo.common.core.page.TableDataInfo;

/**
 * 合约配置Controller
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Controller
@RequestMapping("/business/config")
public class CoConfigController extends BaseController
{
  private String prefix = "business/config";

  @Autowired
  private ICoConfigService coConfigService;

  @RequiresPermissions("business:config:view")
  @GetMapping()
  public String config()
  {
    return prefix + "/list";
  }

  /**
   * 查询合约配置列表
   */
  @RequiresPermissions("business:config:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(CoConfig coConfig)
  {
    startPage();
    List<CoConfig> list = coConfigService.selectCoConfigList(coConfig);
    return getDataTable(list);
  }

  /**
   * 导出合约配置列表
   */
  @RequiresPermissions("business:config:export")
  @Log(title = "合约配置", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  @ResponseBody
  public AjaxResult export(CoConfig coConfig)
  {
    List<CoConfig> list = coConfigService.selectCoConfigList(coConfig);
    ExcelUtil<CoConfig> util = new ExcelUtil<CoConfig>(CoConfig.class);
    return util.exportExcel(list, "合约配置数据");
  }

  /**
   * 新增合约配置
   */
  @GetMapping("/add")
  public String add()
  {
    return prefix + "/add";
  }

  /**
   * 新增保存合约配置
   */
  @RequiresPermissions("business:config:add")
  @Log(title = "合约配置", businessType = BusinessType.INSERT)
  @PostMapping("/add")
  @ResponseBody
  public AjaxResult addSave(CoConfig coConfig)
  {
    return toAjax(coConfigService.insertCoConfig(coConfig));
  }

  /**
   * 修改合约配置
   */
  @RequiresPermissions("business:config:edit")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap mmap)
  {
    CoConfig coConfig = coConfigService.selectCoConfigById(id);
    mmap.put("coConfig", coConfig);
    return prefix + "/edit";
  }

  /**
   * 修改保存合约配置
   */
  @RequiresPermissions("business:config:edit")
  @Log(title = "合约配置", businessType = BusinessType.UPDATE)
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(CoConfig coConfig)
  {
    return toAjax(coConfigService.updateCoConfig(coConfig));
  }

  /**
   * 删除合约配置
   */
  @RequiresPermissions("business:config:remove")
  @Log(title = "合约配置", businessType = BusinessType.DELETE)
  @PostMapping( "/remove")
  @ResponseBody
  public AjaxResult remove(String ids)
  {
    return toAjax(coConfigService.deleteCoConfigByIds(ids));
  }
}
