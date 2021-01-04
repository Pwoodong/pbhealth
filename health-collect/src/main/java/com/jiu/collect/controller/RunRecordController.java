package com.jiu.collect.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.jiu.api.entity.RunningRecord;
import com.jiu.collect.service.RunRecordService;
import com.jiu.common.controller.BaseController;
import com.jiu.common.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/runRecord")
public class RunRecordController extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RunRecordService runRecordService;

    @GetMapping
    public ResponseEntity<Object> query(RunningRecord runningRecord, Pageable pageable){
        Long userId = getUserId();
        log.info("userId:"+userId);
        if(userId != null){
            runningRecord.setUserId(userId);
        }
        Page<RunningRecord> pageList = runRecordService.findByPage(runningRecord,pageable);
        PageInfo<RunningRecord> pageInfo = new PageInfo<>(pageList);
        return new ResponseEntity<>(PageUtil.toPage(pageInfo.getList(),pageInfo.getTotal()), HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, RunningRecord runningRecord) throws IOException {
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody RunningRecord runningRecord){
        Long userId = getUserId();
        log.info("userId:"+userId);
        if(userId != null){
            runningRecord.setUserId(userId);
            runningRecord.setCreateUserId(userId);
        }
        runRecordService.insertRunningRecord(runningRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody RunningRecord runningRecord){
        Long userId = getUserId();
        log.info("userId:"+userId);
        if(userId != null){
            runningRecord.setUserId(userId);
            runningRecord.setUpdateUserId(userId);
        }
        runRecordService.updateRunningRecord(runningRecord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        RunningRecord runningRecord = new RunningRecord();
        Long userId = getUserId();
        log.info("userId:"+userId);
        if(userId != null){
            runningRecord.setUserId(userId);
            runningRecord.setUpdateUserId(userId);
        }
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

    @PostMapping("upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file){
        Long userId = getUserId();
        log.info("userId:"+userId);
        runRecordService.upload(file,userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
