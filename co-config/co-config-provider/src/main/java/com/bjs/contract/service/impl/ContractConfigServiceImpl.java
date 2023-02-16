package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjs.contract.entity.ContractConfig;
import com.bjs.contract.mapper.ContractConfigMapper;
import com.bjs.contract.service.ContractConfigService;
import org.springframework.stereotype.Service;

/**
 * 合约配置表
 *
 * @author bjs code generator
 * @date 2022-11-11 17:58:42
 */
@Service
public class ContractConfigServiceImpl extends ServiceImpl<ContractConfigMapper, ContractConfig> implements ContractConfigService {

    @Override
    public ContractConfig getBySymbol(String symbol) {
        QueryChainWrapper<ContractConfig> queryWrapper = super.query();
        return queryWrapper
                .eq("symbol", symbol)
                .one();
    }
}
