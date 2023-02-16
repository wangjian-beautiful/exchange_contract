package com.bjs.contract.domain;

import com.bjs.contract.entity.CoPositionOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPositionDetail {

    private boolean init;

    private CoPositionOrder coPositionOrder;
}
