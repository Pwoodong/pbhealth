package com.jiu.system.security.access;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.extra.spring.SpringUtil;
import com.jiu.system.security.filter.SecurityMetadataSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

/**
 * 自定义权限决策管理器
 *
 * @author zhang.wang
 */
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        Boolean boo = decideRule(authentication, request, collection);
        if (boo) {
            return;
        } else {
            //对于不同服务重置后，再次校验
            SecurityMetadataSource securityMetadataSource = SpringUtil.getBean(SecurityMetadataSource.class);
            securityMetadataSource.resetMetadataSource();
            Collection<ConfigAttribute> list = securityMetadataSource.getAttributes(o);
            boo = decideRule(authentication, request, list);
        }
        if (!boo) {
            throw new AccessDeniedException("no right");
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private boolean decideRule(Authentication authentication, HttpServletRequest request,
                               Collection<ConfigAttribute> collection) {
        for (ConfigAttribute configAttribute : collection) {
            // 遍历用户拥有的权限
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // 与需要的权限对比，如存在则放行
                authority.getAuthority();
                if (configAttribute.getAttribute().equals(authority.getAuthority())) {
                    if (configAttribute instanceof RestfulConfigAttribute) {
                        RestfulConfigAttribute attribute = (RestfulConfigAttribute) configAttribute;
                        if ("ALL".equals(attribute.getMethod()) || request.getMethod().equals(attribute.getMethod())) {
                            request.setAttribute("urlName", attribute.getUrlName());
                            request.setAttribute("authorityName", attribute.getAuthorityName());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
