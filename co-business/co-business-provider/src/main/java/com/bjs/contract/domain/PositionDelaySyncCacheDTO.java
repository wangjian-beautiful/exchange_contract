package com.bjs.contract.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Watson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDelaySyncCacheDTO {

    private Long positionId;

    private Long uid;

    private String side;

//    private Boolean isCanFinish = false;
}
