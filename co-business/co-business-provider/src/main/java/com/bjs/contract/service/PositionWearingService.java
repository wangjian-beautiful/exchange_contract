package com.bjs.contract.service;

import com.bijinsuo.common.utils.entity.PositionLiquidationDTO;

/**
 * @author Winter
 */
public interface PositionWearingService {
  void wear(PositionLiquidationDTO dto);

    void liquidation(PositionLiquidationDTO dto);
}
