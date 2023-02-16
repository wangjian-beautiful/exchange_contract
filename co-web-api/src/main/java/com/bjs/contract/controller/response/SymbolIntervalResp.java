package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Watson
 */

@Data
@EqualsAndHashCode
@ApiModel("交易对的杠杆区间")
public class SymbolIntervalResp {

    private Integer maxLeverage;

    private Integer minLeverage;
}
