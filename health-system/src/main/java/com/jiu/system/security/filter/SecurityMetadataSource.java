package com.jiu.system.security.filter;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.jiu.system.security.access.RestfulConfigAttribute;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

/**
 * 实现权限数据源
 *
 * @author wang.zhang
 */
@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final static String permiturl = "/login";
    private final static String permitur2 = "/getVerificationCode";
    private final static String permitur3 = "/sso/otherLogin";
    /**
     * 权限集合（一般是url为key,权限编码为value）
     */
    private Map<String, Collection<ConfigAttribute>> authority = new HashMap<>();

    private final AntPathMatcher matcher = new AntPathMatcher();

    public SecurityMetadataSource() {

    }

    /**
     * 根据URL，找到相关的权限配置
     *
     * @param o 实际上是一个URL
     * @return 配置权限
     * @throws IllegalArgumentException 异常
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        //获取请求的url
        String url = StringUtils.hasText(request.getPathInfo()) ? request.getServletPath() + request.getPathInfo() : request.getServletPath();

        //允许的url 直接放行
        if (matcher.match(permiturl, url)) {
            request.setAttribute("urlName", "登录");
            return null;
        }
        if (matcher.match(permitur2, url)) {
            request.setAttribute("urlName", "获取验证码");
            return null;
        }
        if (matcher.match(permitur3, url)) {
            request.setAttribute("urlName", "其他平台登录");
            return null;
        }

        Set<String> keys = authority.keySet();
        for (String key : keys) {
            if (matcher.match(key, url)) {
                return authority.get(key);
            }
        }

        //没配置的url，不放行
        List<ConfigAttribute> array = new ArrayList<>();
        array.add(new RestfulConfigAttribute(url, request.getMethod(), "无权限"));
//        return array;
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void resetMetadataSource() {

    }

}
