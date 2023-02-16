package com.bjs.contract.kline.handler;


import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bjs.contract.dto.KLine;

public interface MarketHandler {

    /**
     * 存储交易信息
     * @param trade
     */
    void handleTrade(String symbol, MatchTradeDetailsDTO trade);


    /**
     * 存储K线信息
     *
     * @param kLine
     */
    void handleKLine(String symbol, KLine kLine);
}
