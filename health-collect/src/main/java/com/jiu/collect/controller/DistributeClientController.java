package com.jiu.collect.controller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package com.jiu.collect.controller
 * ClassName DistributeClient.java
 * Description 分布式客户端
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-30 下午10:55
 **/
@RestController
public class DistributeClientController {

    @Autowired
    CuratorFramework curatorFramework;

    @PostMapping("/stock/deduct")
    public Object reduceStock(Integer id) throws Exception {

        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/product_" + id);

        try {
            // ...
            interProcessMutex.acquire();
            //TODO 执行业务操作
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw e;
            }
        } finally {
            interProcessMutex.release();
        }
        return "ok:";
    }
}
