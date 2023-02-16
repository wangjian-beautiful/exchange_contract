package com.bjs.contract.service;

import com.bijinsuo.common.utils.entity.MatchTradeDTO;

/**
 * 撮合出来的聚合业务service
 *
 * @author Watson
 */
public interface MatchTradeAggregationService {

    void handle(MatchTradeDTO matchTradeDTO);

    void handleForRobot(MatchTradeDTO matchTradeDTO);
}
