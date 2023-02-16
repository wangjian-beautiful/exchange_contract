package com.bijinsuo.business.web;

import java.util.List;

import com.bijinsuo.business.entity.CoinPair;
import com.bijinsuo.business.service.ICoinPairService;
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
 * 撮合币对配置Controller
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Controller
@RequestMapping("/business/coinPair")
public class CoinPairController extends BaseController {
  private String prefix = "business/coinPair";

  @Autowired
  private ICoinPairService coinPairService;

  @RequiresPermissions("business:coinPair:view")
  @GetMapping()
  public String coinPair() {
    return prefix + "/list";
  }

  /**
   * 查询撮合币对配置列表
   */
  @RequiresPermissions("business:coinPair:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(CoinPair coinPair) {
    startPage();
    List<CoinPair> list = coinPairService.selectCoinPairList(coinPair);
    return getDataTable(list);
  }

  /**
   * 导出撮合币对配置列表
   */
  @RequiresPermissions("business:coinPair:export")
  @Log(title = "撮合币对配置", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  @ResponseBody
  public AjaxResult export(CoinPair coinPair) {
    List<CoinPair> list = coinPairService.selectCoinPairList(coinPair);
    ExcelUtil<CoinPair> util = new ExcelUtil<CoinPair>(CoinPair.class);
    return util.exportExcel(list, "撮合币对配置数据");
  }

  /**
   * 新增撮合币对配置
   */
  @GetMapping("/add")
  public String add() {
    return prefix + "/add";
  }

  /**
   * 新增保存撮合币对配置
   */
  @RequiresPermissions("business:coinPair:add")
  @Log(title = "撮合币对配置", businessType = BusinessType.INSERT)
  @PostMapping("/add")
  @ResponseBody
  public AjaxResult addSave(CoinPair coinPair) {
    return toAjax(coinPairService.insertCoinPair(coinPair));
  }

  /**
   * 修改撮合币对配置
   */
  @RequiresPermissions("business:coinPair:edit")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    CoinPair coinPair = coinPairService.selectCoinPairById(id);
    mmap.put("coinPair", coinPair);
    return prefix + "/edit";
  }

  /**
   * 修改保存撮合币对配置
   */
  @RequiresPermissions("business:coinPair:edit")
  @Log(title = "撮合币对配置", businessType = BusinessType.UPDATE)
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(CoinPair coinPair) {
    return toAjax(coinPairService.updateCoinPair(coinPair));
  }

  /**
   * 删除撮合币对配置
   */
  @RequiresPermissions("business:coinPair:remove")
  @Log(title = "撮合币对配置", businessType = BusinessType.DELETE)
  @PostMapping("/remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
    return toAjax(coinPairService.deleteCoinPairByIds(ids));
  }
}
