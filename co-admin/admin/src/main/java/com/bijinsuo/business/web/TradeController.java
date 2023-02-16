package com.bijinsuo.business.web;

import java.util.List;

import com.bijinsuo.business.entity.Trade;
import com.bijinsuo.business.service.ITradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bijinsuo.common.core.controller.BaseController;
import com.bijinsuo.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-11-10
 */
@Controller
@RequestMapping("/business/trade")
public class TradeController extends BaseController {
  private String prefix = "business/trade";

  @Autowired
  private ITradeService tradeService;

  @RequiresPermissions("business:trade:view")
  @GetMapping()
  public String trade() {
    return prefix + "/list";
  }

  /**
   * 查询【请填写功能名称】列表
   */
  @RequiresPermissions("business:trade:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(Trade trade) {
    startPage();
    List<Trade> list = tradeService.selectTradeList(trade);
    return getDataTable(list);
  }
}
