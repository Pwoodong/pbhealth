package com.pbh.collect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Package com.pbh.collect.aspect
 * ClassName LogRecordAspect.java
 * Description 日志记录
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-11 13:21
 **/
@Component
@Aspect
public class LogRecordAspect {

    @Pointcut("execution(* com.pbh.collect.service.impl.DataCollectServiceImpl.upload(..))")
    public void logRecordPointcut() {

    }

    @Before(value = "logRecordPointcut()")
    public void logRecordBefore(JoinPoint joinPoint) {
    }

    @AfterReturning(value = "logRecordPointcut()",returning = "ret")
    public void logRecordAfterReturning(JoinPoint joinPoint,Object ret) {
        /*Object[] args = joinPoint.getArgs();
        String userId = StringUtils.isEmpty(args[0]) ? "" : (String)args[0];
        String content = StringUtils.isEmpty(args[2]) ? "" : (String)args[2];
        List<Map<String,Object>> receiveList = new ArrayList<>();
        if(!StringUtils.isEmpty(args[3])){
            receiveList = (List)args[3];
        }
        String recipient = receiveList.toString();*/
    }

    @AfterThrowing(value = "logRecordPointcut()",throwing = "e")
    public void logRecordAfterThrowing(JoinPoint joinPoint,Exception e){
        /*Object[] args = joinPoint.getArgs();
        String userId = StringUtils.isEmpty(args[0]) ? "" : (String)args[0];
        String content = StringUtils.isEmpty(args[2]) ? "" : (String)args[2];
        List<Map<String,Object>> receiveList = new ArrayList<>();
        if(!StringUtils.isEmpty(args[3])){
            receiveList = (List)args[3];
        }
        String recipient = receiveList.toString();*/
    }

}
