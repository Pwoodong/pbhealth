package com.jiu.common.vo;

import com.jiu.common.constant.SystemCode;

/**
 * Package com.jiu.common.vo
 * ClassName ResponseVO.java
 * Description 消息响应实体
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:26
 **/
public class ResponseVO<T> {

    /**
     * 响应码
     */
    private String code;
    /**
     * 消息
     */
    private T message;

    /**
     * 构造方法
     *
     * @param systemCode 系统编码
     * @param message    消息体
     */
    public ResponseVO(SystemCode systemCode, T message) {
        this.code = systemCode.code;
        this.message = message;
    }

    /**
     * 使用此构造方法 代表返回成功
     *
     * @param message 消息体
     */
    public ResponseVO(T message) {
        this.code = SystemCode.SUCCESS.code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code='" + code + '\'' +
                ", message=" + message +
                '}';
    }
}
