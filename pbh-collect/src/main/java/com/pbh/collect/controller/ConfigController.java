package com.pbh.collect.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Package com.pbh.collect.controller
 * ClassName ConfigController.java
 * Description 配置中心
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-02-26 12:55
 **/
@Controller
@RequestMapping("config")
public class ConfigController {

    private static final Logger log = LoggerFactory.getLogger(ConfigController.class);

    @NacosInjected
    ConfigService configService;

    @NacosValue(value = "${useLocalCache:xxx}", autoRefreshed = true)
    private String useLocalCache;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        // http://localhost:8880/collect/config/get
        return useLocalCache;
    }

    @RequestMapping(value = "/publish", method = GET)
    @ResponseBody
    public String doPublish() {
        // http://localhost:8880/collect/config/publish
        try {
            log.info("原始值{}", configService.getConfig("example", "DEFAULT_GROUP", 20000));
            configService.publishConfig("example", "DEFAULT_GROUP", "useLocalCache=api更改111");
            log.info("更改后的值{}", configService.getConfig("example", "DEFAULT_GROUP", 20000));
            return "SUCESS";
        } catch (NacosException e) {
            e.printStackTrace();
            return "FALSE";
        }
    }
}
