package com.bijinsuo.business.web;

import com.bijinsuo.business.entity.MaintenanceMarginRate;
import com.bijinsuo.business.service.IMaintenanceMarginRateService;
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
 * 维持保证金率Controller
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Controller
@RequestMapping("/business/maintenance")
public class MaintenanceMarginRateController extends BaseController {
  private String prefix = "business/maintenance";

  @Autowired
  private IMaintenanceMarginRateService maintenanceMarginRateService;

  @RequiresPermissions("business:maintenance:view")
  @GetMapping()
  public String rate() {
    return prefix + "/list";
  }

  /**
   * 查询维持保证金率列表
   */
  @RequiresPermissions("business:maintenance:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(MaintenanceMarginRate maintenanceMarginRate) {
    startPage();
    List<MaintenanceMarginRate> list = maintenanceMarginRateService.selectMaintenanceMarginRateList(maintenanceMarginRate);
    return getDataTable(list);
  }

  /**
   * 导出维持保证金率列表
   */
  @RequiresPermissions("business:maintenance:export")
  @Log(title = "维持保证金率", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  @ResponseBody
  public AjaxResult export(MaintenanceMarginRate maintenanceMarginRate) {
    List<MaintenanceMarginRate> list = maintenanceMarginRateService.selectMaintenanceMarginRateList(maintenanceMarginRate);
    ExcelUtil<MaintenanceMarginRate> util = new ExcelUtil<MaintenanceMarginRate>(MaintenanceMarginRate.class);
    return util.exportExcel(list, "维持保证金率数据");
  }

  /**
   * 新增维持保证金率
   */
  @GetMapping("/add")
  public String add() {
    return prefix + "/add";
  }

  /**
   * 新增保存维持保证金率
   */
  @RequiresPermissions("business:maintenance:add")
  @Log(title = "维持保证金率", businessType = BusinessType.INSERT)
  @PostMapping("/add")
  @ResponseBody
  public AjaxResult addSave(MaintenanceMarginRate maintenanceMarginRate) {
    return toAjax(maintenanceMarginRateService.insertMaintenanceMarginRate(maintenanceMarginRate));
  }

  /**
   * 修改维持保证金率
   */
  @RequiresPermissions("business:maintenance:edit")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    MaintenanceMarginRate maintenanceMarginRate = maintenanceMarginRateService.selectMaintenanceMarginRateById(id);
    mmap.put("maintenanceMarginRate", maintenanceMarginRate);
    return prefix + "/edit";
  }

  /**
   * 修改保存维持保证金率
   */
  @RequiresPermissions("business:maintenance:edit")
  @Log(title = "维持保证金率", businessType = BusinessType.UPDATE)
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(MaintenanceMarginRate maintenanceMarginRate) {
    return toAjax(maintenanceMarginRateService.updateMaintenanceMarginRate(maintenanceMarginRate));
  }

  /**
   * 删除维持保证金率
   */
  @RequiresPermissions("business:maintenance:remove")
  @Log(title = "维持保证金率", businessType = BusinessType.DELETE)
  @PostMapping("/remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
    return toAjax(maintenanceMarginRateService.deleteMaintenanceMarginRateByIds(ids));
  }
}
