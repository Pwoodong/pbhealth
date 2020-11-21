package com.jiu.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 控制器基类
 * @date 2018年9月10日 11:14:26
 * @author zhouyi
 */
public abstract class BaseController {
	
	/**
     * 获取Request对象
     * @return request对象
     * @date 2018年9月13日 15:07:40
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Request对象
     * @return request对象
     * @date 2018年9月13日 15:07:40
     */
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
	 /**
     * 获取操作人ID
     * @return 操作人ID
     * @date 2018年9月13日 14:54:08
     */
    protected Long getUserId() {
    	if(null != getRequest().getAttribute("userId")){
    		return Long.valueOf(getRequest().getAttribute("userId").toString());
    	} else {
    		return 0L;
    	}
    }
    
    /**
     * 获取请求客户端的真实IP
     * @return IP地址
     * @date 2018年9月13日 15:07:40
     */
    protected String getIp() {
        HttpServletRequest request = getRequest();
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
