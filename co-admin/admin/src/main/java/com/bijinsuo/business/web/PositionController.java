package com.bijinsuo.business.web;

import com.bijinsuo.business.entity.Position;
import com.bijinsuo.business.service.IPositionService;
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
@RequestMapping("/business/position")
public class PositionController extends BaseController {
  private String prefix = "business/position";

  @Autowired
  private IPositionService positionService;

  @RequiresPermissions("business:position:view")
  @GetMapping()
  public String position() {
    return prefix + "/list";
  }

  @RequiresPermissions("business:position:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(Position position) {
    startPage();
    List<Position> list = positionService.selectPositionList(position);
    return getDataTable(list);
  }
}
