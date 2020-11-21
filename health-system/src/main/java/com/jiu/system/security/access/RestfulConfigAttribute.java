package com.jiu.system.security.access;

import lombok.Data;
import org.springframework.security.access.ConfigAttribute;

/**
 * Restful风格的权限配置
 *
 * @author wang.zhang
 */
@Data
public class RestfulConfigAttribute implements ConfigAttribute {

    /**
     * 权限编码
     */
    private String code;
    /**
     * 请求方式
     */
    private String method;

    /**
     * 资源名
     */
    private String urlName;

    /**
     * 权限名称
     */
    private String authorityName;
    /**
     * 权限ID
     */
    private String authorityId;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源ID
     */
    private String resourceId;

    /**
     * 登陆名
     */
    private String userName;

    private String operationFunction;

    /**
     * 构造方法
     *
     * @param code   权限码
     * @param method 请求方式
     */
    public RestfulConfigAttribute(String code, String method, String urlName) {
        this.code = code;
        this.method = method;
        this.urlName = urlName;
    }

    public RestfulConfigAttribute(String code, String method, String urlName, String authorityName, String operationFunction) {
        this.code = code;
        this.method = method;
        this.urlName = urlName;
        this.authorityName = authorityName;
        this.operationFunction = operationFunction;
    }

    @Override
    public String getAttribute() {
        return this.code;
    }

}
