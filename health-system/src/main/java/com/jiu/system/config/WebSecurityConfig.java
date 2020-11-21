package com.jiu.system.config;

import com.jiu.system.security.access.AccessDecisionManager;
import com.jiu.system.security.filter.JwtFilter;
import com.jiu.system.security.filter.SecurityInterceptor;
import com.jiu.system.security.filter.SecurityMetadataSource;
import com.jiu.system.security.handler.EntryPointUnauthorizedHandler;
import com.jiu.system.security.handler.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 权限配置
 *
 * @author wang.zhang
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private SecurityMetadataSource securityMetadataSource;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //禁用csrf
                .csrf().disable()
                //不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                //登录接口都可以访问
                .antMatchers("/login", "/sso/home", "/sso/otherLogin", "/ws/message", "/getVerificationCode").permitAll()
                //其他请求必须登录认证
                /*.anyRequest().authenticated()*/
                //禁用缓存
                .and().headers().cacheControl();

        //加入自定义的JWT过滤器
        //httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //设置401未认证处理器
        //httpSecurity.exceptionHandling().authenticationEntryPoint(new EntryPointUnauthorizedHandler());
        //设置403禁止访问处理器
        //httpSecurity.exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler());

        //URL资源拦截器
        /*SecurityInterceptor securityInterceptor = new SecurityInterceptor(securityMetadataSource);
        securityInterceptor.setAccessDecisionManager(new AccessDecisionManager());
        httpSecurity.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);*/
    }
}
