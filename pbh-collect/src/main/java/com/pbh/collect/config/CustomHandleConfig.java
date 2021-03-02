package com.pbh.collect.config;

import com.alibaba.boot.nacos.config.properties.NacosConfigProperties;
import com.alibaba.boot.nacos.discovery.properties.NacosDiscoveryProperties;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MonitorConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.spring.boot.autoconfigure.DubboConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.devtools.autoconfigure.DevToolsProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * Package com.pbh.collect.config
 * ClassName MyApplicationConfig.java
 * Description 启动项目获取配置中心配置
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-02-26 13:09
 **/
@Slf4j
@Component
public class CustomHandleConfig implements BeanPostProcessor {

    @NacosInjected
    ConfigService configService;

    @Value(value = "${spring.config.dataid}")
    private String dataId;

    @Value(value = "${spring.config.group}")
    private String group;

    @Override
    public Object postProcessAfterInitialization(Object object, String beanName){
        Yaml yaml = new Yaml();
        try{
            String content = configService.getConfig(dataId,group,5000);
            if(StringUtils.isEmpty(content)){
                log.info("获取配置文件内容为空.");
                return null;
            }
            Map<String, Object> resultMap = yaml.load(content);

            if(object instanceof ServerProperties){
                updateServerProperties(object,resultMap);
            }

//            if(object instanceof MybatisProperties){
//                MybatisProperties mybatisProperties = (MybatisProperties) object;
//                if(resultMap.containsKey("mybatis")){
//                    updateMybatisProperties(mybatisProperties,resultMap);
//                }
//            }

            if(object instanceof PageHelperProperties){
                updatePageHelperProperties(object,resultMap);
            }

//            if(object instanceof DevToolsProperties){
//                updateDevToolsProperties(object,resultMap);
//            }

            if(object instanceof SpringApplication){
                updateSpringApplication(object,resultMap);
            }

            if(object instanceof RedisProperties){
                updateRedisProperties(object,resultMap);
            }

            if(object instanceof SpringBootMasterSlaveRuleConfigurationProperties){
                updateSpringBootMasterSlaveRuleConfigurationProperties(object,resultMap);
            }

            if(object instanceof SpringBootPropertiesConfigurationProperties){
                updateSpringBootPropertiesConfigurationProperties(object,resultMap);
            }

            if(object instanceof NacosConfigProperties){
                updateNacosConfigProperties(object,resultMap);
            }

            if(object instanceof NacosDiscoveryProperties){
                updateNacosDiscoveryProperties(object,resultMap);
            }

            if(object instanceof ApplicationConfig){
                updateApplicationConfig(object,resultMap);
            }

            if(object instanceof RegistryConfig){
                updateRegistryConfig(object,resultMap);
            }

            if(object instanceof ProtocolConfig){
                updateProtocolConfig(object,resultMap);
            }

            if(object instanceof MonitorConfig){
                updateMonitorConfig(object,resultMap);
            }

            if(object instanceof DubboConfigurationProperties){
                updateDubboConfigurationProperties(object,resultMap);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("yaml文件修改失败.");
        }
        return null;
    }

    /**
     * 更新ServerProperties配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateServerProperties(Object object, Map<String, Object> resultMap){
        ServerProperties properties = (ServerProperties) object;
        if(resultMap.containsKey("server")){
            Map<String, Object> serverMap = (Map<String, Object>) resultMap.get("server");
            if(serverMap.containsKey("port")){
                properties.setPort(Integer.valueOf(serverMap.get("port").toString()));
            }
            if(serverMap.containsKey("servlet")){
                Map<String, Object> servletMap = (Map<String, Object>)serverMap.get("servlet");
                if(servletMap.containsKey("context-path")){
                    properties.getServlet().setContextPath(servletMap.get("context-path").toString());
                }
            }
        }
    }

    /**
     * 更新MybatisProperties配置
     * @param   properties      配置类
     * @param   resultMap       配置数据
     * */
    private void updateMybatisProperties(Object object,MybatisProperties properties,Map<String, Object> resultMap){
        Map<String, Object> mybatisMap = (Map<String, Object>) resultMap.get("mybatis");
        if(mybatisMap.containsKey("type-aliases-package")){
            properties.setTypeAliasesPackage(mybatisMap.get("type-aliases-package").toString());
        }
        if(mybatisMap.containsKey("mapper-locations")){
            String[] locations = mybatisMap.get("mapper-locations").toString().split(":");
            properties.setMapperLocations(locations);
        }
    }

    /**
     * 更新PageHelperProperties配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updatePageHelperProperties(Object object,Map<String, Object> resultMap){
        PageHelperProperties properties = (PageHelperProperties) object;
        if(resultMap.containsKey("pagehelper")){
            Map<String, Object> pageHelperMap = (Map<String, Object>) resultMap.get("pagehelper");
            if(pageHelperMap.containsKey("helperDialect")){
                properties.setHelperDialect(pageHelperMap.get("helperDialect").toString());
            }
            if(pageHelperMap.containsKey("reasonable")){
                Boolean reasonable = (Boolean) pageHelperMap.get("reasonable");
                properties.setReasonable(reasonable);
            }
            if(pageHelperMap.containsKey("supportMethodsArguments")){
                Boolean supportMethodsArguments = (Boolean) pageHelperMap.get("supportMethodsArguments");
                properties.setSupportMethodsArguments(supportMethodsArguments);
            }
            if(pageHelperMap.containsKey("params")){
                properties.setParams(pageHelperMap.get("params").toString());
            }
        }
    }

    /**
     * 更新RedisProperties配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateRedisProperties(Object object,Map<String, Object> resultMap){
        RedisProperties properties = (RedisProperties) object;
        if(resultMap.containsKey("spring")){
            Map<String, Object> springMap = (Map<String, Object>) resultMap.get("spring");
            if(springMap.containsKey("redis")){
                Map<String, Object> redisMap = (Map<String, Object>) springMap.get("redis");
                if(redisMap.containsKey("host")){
                    properties.setHost(redisMap.get("host").toString());
                }
                if(redisMap.containsKey("database")){
                    int database = (int)redisMap.get("database");
                    properties.setDatabase(database);
                }
                if(redisMap.containsKey("port")){
                    int port = (int)redisMap.get("port");
                    properties.setPort(port);
                }
                if(redisMap.containsKey("password") && !StringUtils.isEmpty(redisMap.get("password"))){
                    properties.setPassword(redisMap.get("password").toString());
                }
                if(redisMap.containsKey("timeout")){
                    Long timeout = Long.valueOf(redisMap.get("timeout").toString());
                    properties.setTimeout(Duration.ofMillis(timeout));
                }
            }
        }
    }

    /**
     * 更新NacosConfigProperties配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateNacosConfigProperties(Object object,Map<String, Object> resultMap){
        NacosConfigProperties properties = (NacosConfigProperties) object;
        if(resultMap.containsKey("nacos")){
            Map<String, Object> nacosMap = (Map<String, Object>) resultMap.get("nacos");
            if(nacosMap.containsKey("config")){
                Map<String, Object> configMap = (Map<String, Object>)nacosMap.get("config");
                if(configMap.containsKey("server-addr")){
                    properties.setServerAddr(configMap.get("server-addr").toString());
                }
            }
            if(nacosMap.containsKey("discovery")){
                Map<String, Object> configMap = (Map<String, Object>)nacosMap.get("config");
                if(configMap.containsKey("server-addr")){
                    properties.setServerAddr(configMap.get("server-addr").toString());
                }
            }
        }
    }

    /**
     * 更新NacosDiscoveryProperties配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateNacosDiscoveryProperties(Object object,Map<String, Object> resultMap){
        NacosDiscoveryProperties properties = (NacosDiscoveryProperties) object;
        if(resultMap.containsKey("nacos")){
            Map<String, Object> nacosMap = (Map<String, Object>) resultMap.get("nacos");
            if(nacosMap.containsKey("discovery")){
                Map<String, Object> discoveryMap = (Map<String, Object>)nacosMap.get("discovery");
                if(discoveryMap.containsKey("server-addr")){
                    properties.setServerAddr(discoveryMap.get("server-addr").toString());
                }
            }
        }
    }

    /**
     * 更新ApplicationConfig配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateApplicationConfig(Object object,Map<String, Object> resultMap){
        ApplicationConfig properties = (ApplicationConfig) object;
        if(resultMap.containsKey("dubbo")){
            Map<String, Object> dubboMap = (Map<String, Object>) resultMap.get("dubbo");
            if(dubboMap.containsKey("application")){
                Map<String, Object> applicationMap = (Map<String, Object>)dubboMap.get("application");
                if(applicationMap.containsKey("name")){
                    properties.setName(applicationMap.get("name").toString());
                }
            }
        }
    }

    /**
     * 更新RegistryConfig配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateRegistryConfig(Object object,Map<String, Object> resultMap){
        RegistryConfig properties = (RegistryConfig) object;
        if(resultMap.containsKey("dubbo")){
            Map<String, Object> dubboMap = (Map<String, Object>) resultMap.get("dubbo");
            if(dubboMap.containsKey("registry")){
                Map<String, Object> registryMap = (Map<String, Object>)dubboMap.get("registry");
                if(registryMap.containsKey("address")){
                    properties.setAddress(registryMap.get("address").toString());
                }
            }
        }
    }

    /**
     * 更新ProtocolConfig配置
     * @param   object         配置类
     * @param   resultMap      配置数据
     * */
    private void updateProtocolConfig(Object object,Map<String, Object> resultMap){
        ProtocolConfig properties = (ProtocolConfig) object;
        if(resultMap.containsKey("dubbo")){
            Map<String, Object> dubboMap = (Map<String, Object>) resultMap.get("dubbo");
            if(dubboMap.containsKey("protocol")){
                Map<String, Object> protocolMap = (Map<String, Object>)dubboMap.get("protocol");
                if(protocolMap.containsKey("name")){
                    properties.setName(protocolMap.get("name").toString());
                }
                if(protocolMap.containsKey("port")){
                    int port = (int)protocolMap.get("port");
                    properties.setPort(port);
                }
            }
        }
    }

    /**
     * 更新MonitorConfig配置
     * @param   object         配置类
     * @param   resultMap      配置数据
     * */
    private void updateMonitorConfig(Object object,Map<String, Object> resultMap){
        MonitorConfig properties = (MonitorConfig) object;
        if(resultMap.containsKey("dubbo")){
            Map<String, Object> dubboMap = (Map<String, Object>) resultMap.get("dubbo");
            if(dubboMap.containsKey("monitor")){
                Map<String, Object> monitorMap = (Map<String, Object>)dubboMap.get("monitor");
                if(monitorMap.containsKey("protocol")){
                    properties.setProtocol(monitorMap.get("protocol").toString());
                }
            }
        }
    }

    /**
     * 更新DubboConfigurationProperties配置
     * @param   object         配置类
     * @param   resultMap      配置数据
     * */
    private void updateDubboConfigurationProperties(Object object,Map<String, Object> resultMap){
        DubboConfigurationProperties properties = (DubboConfigurationProperties) object;
        if(resultMap.containsKey("dubbo")){
            Map<String, Object> dubboMap = (Map<String, Object>) resultMap.get("dubbo");
            if(dubboMap.containsKey("scan")){
                Map<String, Object> scanMap = (Map<String, Object>)dubboMap.get("scan");
                if(scanMap.containsKey("base-packages")){

                }
            }
        }
    }

    /**
     * 更新DevToolsProperties配置
     * @param   object         配置类
     * @param   resultMap      配置数据
     * */
    private void updateDevToolsProperties(Object object,Map<String, Object> resultMap){
//        DevToolsProperties properties = (DevToolsProperties) object;
//        if(resultMap.containsKey("spring")){
//            Map<String, Object> springMap = (Map<String, Object>) resultMap.get("spring");
//            if(springMap.containsKey("devtools")){
//                Map<String, Object> devtoolsMap = (Map<String, Object>)springMap.get("devtools");
//                if(devtoolsMap.containsKey("restart")){
//                    Map<String, Object> restartMap = (Map<String, Object>)devtoolsMap.get("restart");
//                    if(restartMap.containsKey("enabled")){
//                        boolean enabled = (boolean) restartMap.get("enabled");
//                        properties.getRestart().setEnabled(enabled);
//                    }
//                }
//            }
//        }
    }

    /**
     * 更新SpringApplication配置
     * @param   object        配置类
     * @param   resultMap     配置数据
     * */
    private void updateSpringApplication(Object object,Map<String, Object> resultMap){
        SpringApplication properties = (SpringApplication) object;
        if(resultMap.containsKey("spring")){
            Map<String, Object> springMap = (Map<String, Object>) resultMap.get("spring");
            if(springMap.containsKey("main")){
                Map<String, Object> mainMap = (Map<String, Object>)springMap.get("main");
                if(mainMap.containsKey("allow-bean-definition-overriding")){
                    boolean allowBeanDefinitionOverriding = (boolean)mainMap.get("allow-bean-definition-overriding");
                    properties.setAllowBeanDefinitionOverriding(allowBeanDefinitionOverriding);
                }
            }
        }
    }

    /**
     * 更新SpringBootConfiguration配置
     * @param   object       配置类
     * @param   resultMap    配置数据
     * */
    private void updateSpringBootConfiguration(Object object,Map<String, Object> resultMap){
        SpringBootConfiguration properties = (SpringBootConfiguration) object;
        if(resultMap.containsKey("spring")){
            Map<String, Object> springMap = (Map<String, Object>) resultMap.get("spring");
            if(springMap.containsKey("shardingsphere")){
                Map<String, Object> shardingsphereMap = (Map<String, Object>)springMap.get("shardingsphere");
                if(shardingsphereMap.containsKey("datasource")){

                }
            }
        }
    }

    /**
     * 更新SpringBootMasterSlaveRuleConfigurationProperties配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateSpringBootMasterSlaveRuleConfigurationProperties(Object object,Map<String, Object> resultMap){
        SpringBootMasterSlaveRuleConfigurationProperties properties = (SpringBootMasterSlaveRuleConfigurationProperties) object;
        if(resultMap.containsKey("spring")){
            Map<String, Object> springMap = (Map<String, Object>) resultMap.get("spring");
            if(springMap.containsKey("shardingsphere")){
                Map<String, Object> shardingsphereMap = (Map<String, Object>)springMap.get("shardingsphere");
                if(shardingsphereMap.containsKey("masterslave")){
                    Map<String, Object> masterslaveMap = (Map<String, Object>)shardingsphereMap.get("masterslave");
                    if(masterslaveMap.containsKey("load-balance-algorithm-type")){
                        properties.setLoadBalanceAlgorithmType(masterslaveMap.get("load-balance-algorithm-type").toString());
                    }
                    if(masterslaveMap.containsKey("name")){
                        properties.setName(masterslaveMap.get("name").toString());
                    }
                    if(masterslaveMap.containsKey("master-data-source-name")){
                        properties.setMasterDataSourceName(masterslaveMap.get("master-data-source-name").toString());
                    }
                    if(masterslaveMap.containsKey("slave-data-source-names")){
                        Collection<String> slaveDataSourceNames = new ArrayList<>();
                        slaveDataSourceNames.add(masterslaveMap.get("slave-data-source-names").toString());
                        properties.setSlaveDataSourceNames(slaveDataSourceNames);
                    }
                }
            }
        }
    }

    /**
     * 更新SpringBootPropertiesConfigurationProperties配置
     * @param   object          配置类
     * @param   resultMap       配置数据
     * */
    private void updateSpringBootPropertiesConfigurationProperties(Object object,Map<String, Object> resultMap){
        SpringBootPropertiesConfigurationProperties properties = (SpringBootPropertiesConfigurationProperties) object;
        if(resultMap.containsKey("spring")){
            Map<String, Object> springMap = (Map<String, Object>) resultMap.get("spring");
            if(springMap.containsKey("shardingsphere")){
                Map<String, Object> shardingsphereMap = (Map<String, Object>)springMap.get("shardingsphere");
                if(shardingsphereMap.containsKey("datasource")){
                    Map<String, Object> datasourceMap = (Map<String, Object>)shardingsphereMap.get("datasource");
                    Properties properties1 = new Properties();
                    ByteArrayOutputStream os = null;
                    ObjectOutputStream oos = null;
                    InputStream in = null;
                    try{
                        os = new ByteArrayOutputStream();
                        oos = new ObjectOutputStream(os);
                        oos.writeObject(datasourceMap);
                        in = new ByteArrayInputStream(os.toByteArray());
                        properties1.load(in);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    properties.setProps(properties1);
                }
            }
        }
    }

}
