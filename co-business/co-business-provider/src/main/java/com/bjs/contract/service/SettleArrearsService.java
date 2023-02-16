package com.bjs.contract.service;

import com.bijinsuo.common.domain.FundingRateSettleArrearsDTO;

/**
 * 结算欠款服务
 *
 * @author Watson
 */
public interface SettleArrearsService {


    void handleArrears(FundingRateSettleArrearsDTO dto);
}
