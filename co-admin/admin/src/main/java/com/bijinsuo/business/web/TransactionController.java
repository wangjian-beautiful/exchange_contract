package com.bijinsuo.business.web;

import java.util.List;

import com.bijinsuo.business.entity.Transaction;
import com.bijinsuo.business.service.ITransactionService;
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
 * 交易流水Controller
 *
 * @author ruoyi
 * @date 2022-11-09
 */
@Controller
@RequestMapping("/business/transaction")
public class TransactionController extends BaseController
{
    private String prefix = "business/transaction";

    @Autowired
    private ITransactionService transactionService;

    @RequiresPermissions("business:transaction:view")
    @GetMapping()
    public String transaction()
    {
        return prefix + "/list";
    }

    /**
     * 查询交易流水列表
     */
    @RequiresPermissions("business:transaction:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Transaction transaction)
    {
        startPage();
        List<Transaction> list = transactionService.selectTransactionList(transaction);
        return getDataTable(list);
    }

    /**
     * 导出交易流水列表
     */
    @RequiresPermissions("business:transaction:export")
    @Log(title = "交易流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Transaction transaction)
    {
        List<Transaction> list = transactionService.selectTransactionList(transaction);
        ExcelUtil<Transaction> util = new ExcelUtil<Transaction>(Transaction.class);
        return util.exportExcel(list, "交易流水数据");
    }

    /**
     * 新增交易流水
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存交易流水
     */
    @RequiresPermissions("business:transaction:add")
    @Log(title = "交易流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Transaction transaction)
    {
        return toAjax(transactionService.insertTransaction(transaction));
    }

    /**
     * 修改交易流水
     */
    @RequiresPermissions("business:transaction:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Transaction transaction = transactionService.selectTransactionById(id);
        mmap.put("transaction", transaction);
        return prefix + "/edit";
    }

    /**
     * 修改保存交易流水
     */
    @RequiresPermissions("business:transaction:edit")
    @Log(title = "交易流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Transaction transaction)
    {
        return toAjax(transactionService.updateTransaction(transaction));
    }

    /**
     * 删除交易流水
     */
    @RequiresPermissions("business:transaction:remove")
    @Log(title = "交易流水", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(transactionService.deleteTransactionByIds(ids));
    }
}
