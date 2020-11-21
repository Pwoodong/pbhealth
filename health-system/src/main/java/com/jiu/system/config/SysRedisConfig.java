package com.jiu.system.config;

import com.jiu.common.config.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 * Created by macro on 2020/3/2.
 */
@EnableCaching
@Configuration
public class SysRedisConfig extends RedisConfig {

}
