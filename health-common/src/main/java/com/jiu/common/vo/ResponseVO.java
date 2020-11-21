package com.jiu.common.vo;

import com.jiu.common.constant.SystemCode;
import lombok.Data;

/**
 * Package com.jiu.common.vo
 * ClassName ResponseVO.java
 * Description 消息响应实体
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:26
 **/
@Data
public class ResponseVO<T> {

    /**
     * 响应码
     */
    private String code;

    /**
     * 状态码
     */
    private String status;

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
     * 构造方法
     *
     * @param systemCode 系统编码
     * @param message    消息体
     */
    public ResponseVO(SystemCode systemCode,SystemCode statusCode, T message) {
        this.code = systemCode.code;
        this.status = statusCode.status;
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

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code='" + code + '\'' +
                ", message=" + message +
                '}';
    }
}
