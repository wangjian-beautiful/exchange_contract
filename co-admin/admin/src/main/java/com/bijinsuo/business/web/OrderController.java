package com.bijinsuo.business.web;

import com.bijinsuo.business.entity.Order;
import com.bijinsuo.business.service.IOrderService;
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
@RequestMapping("/business/order")
public class OrderController extends BaseController {
  private String prefix = "business/order";

  @Autowired
  private IOrderService orderService;

  @RequiresPermissions("business:order:view")
  @GetMapping()
  public String order() {
    return prefix + "/list";
  }

  @RequiresPermissions("business:order:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(Order order) {
    startPage();
    List<Order> list = orderService.selectOrderList(order);
    return getDataTable(list);
  }
}
