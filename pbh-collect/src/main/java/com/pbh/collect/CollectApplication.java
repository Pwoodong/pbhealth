package com.pbh.collect;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author liaoyj
 * @date 2020-10-21
 **/
//@EnableDubbo
@MapperScan(basePackages = "com.pbh.collect.mapper")
@SpringBootApplication
@NacosPropertySource(dataId = "collect-application", autoRefreshed = true)
public class CollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class, args);
    }

}
