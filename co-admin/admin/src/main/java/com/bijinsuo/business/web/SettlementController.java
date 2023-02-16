package com.bijinsuo.business.web;

import com.bijinsuo.business.entity.Settlement;
import com.bijinsuo.business.service.ISettlementService;
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
 * @date 2022-11-07
 */
@Controller
@RequestMapping("/business/settlement")
public class SettlementController extends BaseController {
  private String prefix = "business/settlement";

  @Autowired
  private ISettlementService settlementService;

  @RequiresPermissions("business:settlement:view")
  @GetMapping()
  public String position() {
    return prefix + "/list";
  }

  @RequiresPermissions("business:settlement:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(Settlement settlement) {
    startPage();
    List<Settlement> list = settlementService.selectSettlementList(settlement);
    return getDataTable(list);
  }
}
