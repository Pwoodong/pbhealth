package com.pbh.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Package com.pbh.gateway
 * ClassName GatewayServerApplication.java
 * Description 网关服务
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-02-28 下午11:14
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class, args);
    }

}
