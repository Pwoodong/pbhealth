package com.jiu.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Package com.jiu.system
 * ClassName SystemApplication.java
 * Description  系统管理模块
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-18 22:39
 **/
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.jiu.system"})
@MapperScan(basePackages = {"com.jiu.system.dao"})
public class SystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SystemApplication.class);
    }
}
