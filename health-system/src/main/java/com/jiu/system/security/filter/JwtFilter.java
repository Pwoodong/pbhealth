package com.jiu.system.security.filter;

import com.jiu.system.entity.UserCache;
import com.jiu.system.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Jwt过滤器
 *
 * @author wang.zhang
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private CacheManager cacheManager;

    private final static String TOKEN = "token";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader(TOKEN);
        
        //Excel导出token存在请求体中
        if(null == token){
        	token = (String)httpServletRequest.getParameter(TOKEN);
        }
        if (StringUtils.hasText(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            //从Token中解析出用户
            Claims claims;
            try {
                claims = JwtUtils.parse(token);
            } catch (Exception e) {
                throw new SessionAuthenticationException("会话已过期");
            }
            Cache userCache = cacheManager.getCache("userCache");
            String userId = claims.get("getId").toString();
            if(!claims.get("verifiId").toString().equals(userCache.get(userId,String.class))){
                //访问wu效
                throw new SessionAuthenticationException("会话已过期");
            }
            UserCache us = userCache.get(userId+"-",UserCache.class);
            if(!getIp(httpServletRequest).equals(us.getIp()) || !httpServletRequest.getHeader("User-Agent").equals(us.getBrowserInfo())){
                throw new SessionAuthenticationException("会话已过期");
            }
            httpServletRequest.setAttribute("username", us.getUserName());
            httpServletRequest.setAttribute("userId", userId);
            httpServletRequest.setAttribute("terminalType", us.getTerminalType());
            List<GrantedAuthority> authorities = new ArrayList<>();
            List roles = us.getRole();
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(us.getUserName(), null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getIp(HttpServletRequest request){
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
