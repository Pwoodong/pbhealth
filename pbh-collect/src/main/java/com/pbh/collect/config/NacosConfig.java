package com.pbh.collect.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Package com.pbh.collect.config
 * ClassName NacosConfig.java
 * Description Nacos配置类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-25 下午11:27
 **/
@Configuration
public class NacosConfig {

    @Value("${server.ip}")
    private String serverIp;
    @Value("${server.port}")
    private int serverPort;
    @Value("${dubbo.application.name}")
    private String applicationName;
    @NacosInjected
    private NamingService namingService;
    @PostConstruct
    public void registerInstance() throws NacosException {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = "127.0.0.1";
        if(address != null){
            ip = address.getHostAddress();
        }
        namingService.registerInstance(applicationName, serverIp, serverPort);
    }

}
