package com.jiu.common.constant;

/**
 * Package com.jiu.common.constant
 * ClassName SystemCode.java
 * Description 系统结果码枚举类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:26
 **/
public enum SystemCode {

    /**
     * 业务错误
     */
    E_BUSINESS("1111", "","业务异常"),
    /**
     * 系统错误
     */
    E_ERROR("9999", "","系统异常"),
    /**
     * 成功代码
     */
    SUCCESS("0000", "","成功"),
    /**
     * 状态代码
     */
    OK("0000", "ok","成功");;

    /**
     * 返回码
     */
    public String code;
    /**
     * 状态码
     */
    public String status;
    /**
     * 描述
     */
    public String desc;

    SystemCode(String code,String status,String desc) {
        this.code = code;
        this.status = status;
        this.desc = desc;
    }
}
