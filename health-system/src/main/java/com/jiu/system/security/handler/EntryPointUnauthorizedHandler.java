package com.jiu.system.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败的处理
 *
 * @author wang.zhsng
 */

public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    private static final String RESULT = "{\"code\":\"401\",\"message\":\"认证失败，未获取到Token请重新登录\"}";

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write(RESULT);
    }
}
