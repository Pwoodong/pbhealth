package com.pbh.common.handler;

import com.pbh.common.constant.SystemCode;
import com.pbh.common.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Package com.pbh.common.handler
 * ClassName GlobalExceptionHandler.java
 * Description 全局异常处理
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-11 11:36
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数为空异常
     *
     * @param e 异常类
     * @return 异常消息
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVO handle(MissingServletRequestParameterException e) {
        log.error(e.getParameterName() + "参数为空", e);
        String message = "miss parameter " + e.getParameterName() + ":" + e.getParameterType();
        return new ResponseVO<>(SystemCode.E_BUSINESS, message);
    }

    /**
     * 参数类型不匹配异常
     *
     * @param e 异常类型
     * @return 异常消息
     */
    @ExceptionHandler(value = TypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVO handle(TypeMismatchException e) {
        log.error("参数类型错误", e);
        String message = "required type: " + e.getRequiredType() + ",but value:" + e.getValue();
        return new ResponseVO<>(SystemCode.E_BUSINESS, message);
    }

    /**
     * 请求方式异常处理
     *
     * @param e 异常类
     * @return 异常消息
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVO handler(HttpRequestMethodNotSupportedException e) {
        log.error("错误的请求方式", e);
        return new ResponseVO<>(SystemCode.E_BUSINESS, e.getMessage());
    }

    /**
     * 未知异常处理
     *
     * @param e 异常类
     * @return 异常消息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVO handle(Exception e) {
        log.error("异常处理", e);
        return new ResponseVO<>(SystemCode.E_ERROR, "操作失败");
    }

}
