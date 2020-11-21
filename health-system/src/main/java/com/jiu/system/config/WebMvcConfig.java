package com.jiu.system.config;

import com.jiu.system.security.interceptor.CorsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Package com.jiu.system.config
 * ClassName WebMvcConfig.java
 * Description WEB配置
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-20 21:54
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public CorsInterceptor corsInterceptor() {
        CorsInterceptor corsInterceptor = new CorsInterceptor();
        return corsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor())
                .addPathPatterns("/**");
    }

}
