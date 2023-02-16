package com.bijinsuo.business.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Winter
 */
@Configuration
public class BusinessJdbcTemplateConfig {
  @Value("${spring.datasource.business.driver}")
  private String driverClassName;
  @Value("${spring.datasource.business.url}")
  private String url;
  @Value("${spring.datasource.business.username}")
  private String username;
  @Value("${spring.datasource.business.password}")
  private String password;

  @Bean
  public JdbcTemplate jdbcTemplate() {
    final BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
    return jdbcTemplate;
  }
}
