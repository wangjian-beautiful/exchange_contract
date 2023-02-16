package com.bjs.contract.service.impl;

import com.alibaba.nacos.api.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjs.contract.entity.OpenApiToken;
import com.bjs.contract.mapper.OpenApiTokenMapper;
import com.bjs.contract.service.OpenApiTokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 *
 * @author bjs code generator
 * @date 2022-12-12 17:14:01
 */
@Service
public class OpenApiTokenServiceImpl extends ServiceImpl<OpenApiTokenMapper, OpenApiToken> implements OpenApiTokenService {
    @Resource
    private OpenApiTokenMapper openApiTokenMapper;

    @Override
    public OpenApiToken findByOpenApiToken(String token) {
        if(StringUtils.isBlank(token)) {
            return null;
        }
        LambdaQueryWrapper<OpenApiToken> query = new LambdaQueryWrapper<>();
        query.eq(OpenApiToken::getToken,token);
        return openApiTokenMapper.selectOne(query);
    }

    @Override
    public String getSecretKey(String token) {
        if(StringUtils.isBlank(token)) {
            return null;
        }
        OpenApiToken openApiToken = findByOpenApiToken(token);
        if(null == openApiToken) {
            return null;
        }
        return openApiToken.getSecretKey();
    }
}
