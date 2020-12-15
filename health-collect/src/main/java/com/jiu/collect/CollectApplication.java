package com.jiu.collect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author liaoyj
 * @date 2020-10-21
 **/
@MapperScan(basePackages = "com.jiu.collect.mapper")
@SpringBootApplication
public class CollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class, args);
    }

}
