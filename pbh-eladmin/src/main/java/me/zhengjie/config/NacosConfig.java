package me.zhengjie.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.pbh.common.utils.ListIp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Package me.zhengjie.config
 * ClassName NacosConfig.java
 * Description Nacos配置类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-03-17 下午10:25
 **/
@Configuration
public class NacosConfig {

    @Value("${server.web.url}")
    private String serverIp;
    @Value("${server.port}")
    private int serverPort;
    @Value("${dubbo.application.name}")
    private String applicationName;
    @NacosInjected
    private NamingService namingService;
    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, ListIp.getWebIp(serverIp), serverPort);
    }

}
