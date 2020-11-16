package com.jiu.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Package com.jiu.common.vo
 * ClassName WebLogVo.java
 * Description 日志封装类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-16 20:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class WebLogVo {
    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 返回结果
     */
    private Object result;

}
