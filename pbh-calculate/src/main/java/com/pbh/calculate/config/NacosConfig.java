package com.pbh.calculate.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

/**
 * Package com.pbh.calculate.config
 * ClassName NacosConfig.java
 * Description Nacos配置类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-02-24 下午11:32
 **/
//@Configuration
public class NacosConfig {

    @Value("${server.port}")
    private int serverPort;
    @Value("${dubbo.application.name}")
    private String applicationName;
    @NacosInjected
    private NamingService namingService;
    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
    }

}
