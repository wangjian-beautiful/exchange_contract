package com.bjs.contract.entity.vo;

import lombok.Data;

/**
 * @author nike
 * @date 2022年11月07日 14:35
 */
@Data
public class TransferVO {
    //用户id
    private Long uid;
    //变化金额
    private String amount;
    //币对
    private String symbol;
}
