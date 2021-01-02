package com.jiu.collect.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.jiu.api.entity.RunningRecord;
import com.jiu.collect.service.RunRecordService;
import com.jiu.common.utils.PageUtil;
import com.jiu.common.utils.RsaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Set;

/**
 * Package com.jiu.collect.controller
 * ClassName RunRecordController.java
 * Description 跑步记录接口
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-02 上午11:19
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/runRecord")
public class RunRecordController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RunRecordService runRecordService;

    @GetMapping
    public ResponseEntity<Object> query(RunningRecord runningRecord, Pageable pageable){
        String token = request.getHeader("Authorization");
        System.out.println("token:"+token);
        String Cookie = request.getHeader("Cookie");
        System.out.println("Cookie:"+Cookie);

        // 密码解密
        String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+Ue/gUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";
        try {
            String password = RsaUtils.decryptByPrivateKey(privateKey, "gry35EtIDzyNI9iQwX8HLCv/m/1xf9OO9LVUc9eukL55SG4ywdZQE9x/LiSAdL27nJboFi9JTu1jFzhrv6q2Cw==");
            System.out.println("password:"+password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Page<RunningRecord> pageList = runRecordService.findByPage(runningRecord,pageable);
        PageInfo<RunningRecord> pageInfo = new PageInfo<>(pageList);
        return new ResponseEntity<>(PageUtil.toPage(pageInfo.getList(),pageInfo.getTotal()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody RunningRecord runningRecord){
        runRecordService.insertRunningRecord(runningRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody RunningRecord runningRecord){
        runRecordService.updateRunningRecord(runningRecord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        String[] array = new String[ids.size()];
        Iterator<Long> it = ids.iterator();
        int i = 0;
        while (it.hasNext()) {
            array[i] = it.next().toString();
            i++;
        }
        runRecordService.deleteRunningRecord(array);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
