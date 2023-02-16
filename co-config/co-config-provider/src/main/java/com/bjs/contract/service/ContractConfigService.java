package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.ContractConfig;

/**
 * 合约配置表
 *
 * @author bjs code generator
 * @date 2022-11-11 17:58:42
 */
public interface ContractConfigService extends IService<ContractConfig> {

    ContractConfig getBySymbol(String symbol);

}
