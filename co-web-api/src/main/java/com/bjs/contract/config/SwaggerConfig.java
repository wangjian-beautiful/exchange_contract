package com.bjs.contract.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Winter
 * 默认访问地址是 http:{ip}:{port}/swagger-ui/index.html
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
  @Value("${swagger.enable}")
  private boolean enable;

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.OAS_30)
        .enable(enable)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .build();
  }
}
