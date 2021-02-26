package com.pbh.collect.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Map;

/**
 * Package com.pbh.collect.config
 * ClassName MyApplicationConfig.java
 * Description 动态应用配置
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-02-26 13:09
 **/
@Component
public class MyApplicationConfig implements BeanPostProcessor {

    @NacosInjected
    ConfigService configService;

    @Override
    public Object postProcessBeforeInitialization(Object object, String beanName){
        Yaml yaml = new Yaml();
        FileWriter fileWriter = null;
        //层级map变量
        Map<String, Object> springMap, dataSourceMap, resultMap,helperDialect;
        try{
            String path = System.getProperty("user.dir");
            String src = path + "/pbh-collect/src/main/resources/application-dev.yml";
            //读取yaml文件，默认返回根目录结构
            FileSystemResource resource = new FileSystemResource("/pbh-collect/src/main/resources/application-dev.yml");
            File file = resource.getFile();

            String content = configService.getConfig("collect-dev.yml","DEFAULT_GROUP",5000);
            resultMap = (Map<String, Object>) yaml.load(content);
            //字符输出
            // fileWriter = new FileWriter(file);
            fileWriter = new FileWriter(new File(src));
            //用yaml方法把map结构格式化为yaml文件结构
            fileWriter.write(yaml.dumpAsMap(resultMap));
            //刷新
            fileWriter.flush();
            //关闭流
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("对不起，yaml文件修改失败！");
        }

        /*if(object instanceof ServerProperties){
            try{
                String content = configService.getConfig("collect-dev.yml","DEFAULT_GROUP",5000);
                ServerProperties newServerProperties = (ServerProperties) object;
                newServerProperties.setPort(Integer.valueOf(content));
            }catch (Exception e){

            }
        }*/
        return null;
    }
}
