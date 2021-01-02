package com.jiu.collect.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.jiu.api.entity.RunningRecord;
import com.jiu.collect.service.RunRecordService;
import com.jiu.collect.utils.RedisUtils;
import com.jiu.common.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private RunRecordService runRecordService;

    @GetMapping
    public ResponseEntity<Object> query(RunningRecord runningRecord, Pageable pageable){
        String token = request.getHeader("Authorization");
        System.out.println("token:"+token);
        String key = "online-token-" + token.split(" ")[1];
        Object keyValue = "";

        if(redisUtils.hasKey(key)){
            keyValue = redisUtils.get(key);
        }
        System.out.println("keyValue{}{}"+keyValue);
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
