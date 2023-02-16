package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.OpenApiToken;

/**
 * 
 *
 * @author bjs code generator
 * @date 2022-12-12 17:14:01
 */
public interface OpenApiTokenService extends IService<OpenApiToken> {

    String getSecretKey(String api_key);

    OpenApiToken findByOpenApiToken(String token);
}
