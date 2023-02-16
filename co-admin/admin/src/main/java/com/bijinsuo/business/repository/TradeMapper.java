package com.bijinsuo.business.repository;

import com.bijinsuo.business.entity.Trade;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-10
 */
@Repository
public interface TradeMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Trade selectTradeById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param coTrade 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Trade> selectTradeList(Trade coTrade);
}
