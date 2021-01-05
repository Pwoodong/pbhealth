package com.jiu.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * 控制器基类
 * @date 2018年9月10日 11:14:26
 * @author zhouyi
 */
@Slf4j
public abstract class BaseController {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
	
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
//    	if(null != getRequest().getAttribute("userId")){
//    		return Long.valueOf(getRequest().getAttribute("userId").toString());
//    	} else {
//    		return 0L;
//    	}

        if(null != getRequest().getHeader("Authorization")){
            String token = getRequest().getHeader("Authorization");
            String key = "online-token-" + token.split(" ")[1];
            log.info("key:"+key);
            Object obj = null;
            log.info("key is exist:"+redisTemplate.hasKey(key));
            if(redisTemplate.hasKey(key)){
                obj = redisTemplate.opsForValue().get(key);
            }
            log.info("obj:"+obj);
            Map<String,Object> map = (Map)obj;
            log.info("map:"+map);
            return Long.valueOf(map.get("userId").toString());
        }else{
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
