package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author nike
 * @date 2022年12月01日 15:03
 */
@Data
@ApiModel("合约账户流水")
public class UserTransactionResp {

    private int count;

    private List<UserTransactionListResp> list;
}
