package com.pbh.calculate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package com.pbh.calculate.controller
 * ClassName TestController.java
 * Description 测试熔断器接口
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-03-03 下午4:05
 **/
@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello Sentinel";
    }

}
