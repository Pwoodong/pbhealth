package com.jiu.calculate.config;

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
 * Package com.jiu.bigscreen.config
 * ClassName Ds2DataSourceConfig.java
 * Description 数据源二
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-10-22 15:37
 **/
@Configuration
@MapperScan(basePackages = "/", sqlSessionFactoryRef = "ds2SqlSessionFactory")
public class Ds2DataSourceConfig {

    /**
     * 配置多数据源 关键就在这里 这里配置了不同的数据源扫描不同mapper
     */
    static final String PACKAGE = "com.jiu.bigscreen.mapper.ds2";
    static final String MAPPER_LOCATION = "classpath:mybatis/ds2/mapper/*.xml";

    /**
     * 连接数据库信息 这个其实更好的是用配置中心完成
     */
    @Value("${ds2.datasource.url}")
    private String url;

    @Value("${ds2.datasource.username}")
    private String username;

    @Value("${ds2.datasource.password}")
    private String password;

    @Value("${ds2.datasource.driverClassName}")
    private String driverClassName;

    @Bean("ds2DataSource")
    public DataSource ds2DataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "ds2TransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(ds2DataSource());
    }

    @Bean(name = "ds2SqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("ds2DataSource") DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Ds2DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
