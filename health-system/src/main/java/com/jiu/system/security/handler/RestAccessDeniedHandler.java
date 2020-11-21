package com.jiu.system.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限时的处理
 *
 * @author wang.zhang
 */

public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final String RESULT = "{\"code\":\"403\",\"message\":\"没有访问该资源的权限，请联系管理员\",\"data\":\"没有访问该资源的权限，请联系管理员\"}";

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getWriter().write(RESULT);
    }
}
