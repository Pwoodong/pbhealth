package com.jiu.system.security.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * 权限拦截器
 *
 * @author wang.zhang
 */
public class SecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    /**
     * 权限数据
     */
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    /**
     * 构造方法注入权限数据
     *
     * @param securityMetadataSource 权限数据
     */
    public SecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation invocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        InterceptorStatusToken token = super.beforeInvocation(invocation);
        try {
            invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}