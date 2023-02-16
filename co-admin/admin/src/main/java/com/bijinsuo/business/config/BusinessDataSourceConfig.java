package com.bijinsuo.business.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Winter
 */
@Configuration
@MapperScan(basePackages = BusinessDataSourceConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = "businessSqlSessionFactory")
public class BusinessDataSourceConfig {
  static final String ENTITY_PACKAGE = "com.bijinsuo.business.entity";
  static final String MAPPER_PACKAGE = "com.bijinsuo.business.repository";
  static final String MAPPER_LOCATION = "classpath:repository/business/*.xml";

  @Value("${spring.datasource.business.url}")
  private String url;

  @Value("${spring.datasource.business.username}")
  private String user;

  @Value("${spring.datasource.business.password}")
  private String password;

  @Value("${spring.datasource.business.driver}")
  private String driverClass;

  @Bean(name = "businessDataSource")
  public DataSource clusterDataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(driverClass);
    dataSource.setUrl(url);
    dataSource.setUsername(user);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean(name = "businessTransactionManager")
  public DataSourceTransactionManager clusterTransactionManager() {
    return new DataSourceTransactionManager(clusterDataSource());
  }

  @Bean(name = "businessSqlSessionFactory")
  public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("businessDataSource") DataSource clusterDataSource)
      throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(clusterDataSource);
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources(BusinessDataSourceConfig.MAPPER_LOCATION));
    sessionFactory.setTypeAliasesPackage(ENTITY_PACKAGE);
    return sessionFactory.getObject();
  }
}
