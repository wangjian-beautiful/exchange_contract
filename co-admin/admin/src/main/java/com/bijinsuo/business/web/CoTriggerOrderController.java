package com.bijinsuo.business.web;

import com.bijinsuo.business.entity.CoTriggerOrder;
import com.bijinsuo.business.service.ICoTriggerOrderService;
import com.bijinsuo.common.core.controller.BaseController;
import com.bijinsuo.common.core.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-11-10
 */
@Controller
@RequestMapping("/business/triggerOrder")
public class CoTriggerOrderController extends BaseController {
  private String prefix = "business/triggerOrder";
  @Autowired
  private ICoTriggerOrderService coTriggerOrderService;

  @RequiresPermissions("business:order:view")
  @GetMapping()
  public String order() {
    return prefix + "/list";
  }

  /**
   * 查询【请填写功能名称】列表
   */
  @RequiresPermissions("business:order:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(CoTriggerOrder coTriggerOrder) {
    startPage();
    List<CoTriggerOrder> list = coTriggerOrderService.selectOrderList(coTriggerOrder);
    return getDataTable(list);
  }
}
