package com.pbh.collect.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.pbh.api.entity.RunningRecord;
import com.pbh.api.vo.RunningRecordVo;
import com.pbh.collect.service.OcrService;
import com.pbh.collect.service.RunRecordService;
import com.pbh.common.controller.BaseController;
import com.pbh.common.utils.ConvertBeanUtils;
import com.pbh.common.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Package com.pbh.collect.controller
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

    @Autowired
    private OcrService ocrService;

    @GetMapping
    public ResponseEntity<Object> query(RunningRecord runningRecord, Pageable pageable){
        Long userId = getUserId();
        log.info("userId:"+userId);
        if(userId != null){
            runningRecord.setUserId(userId);
        }
        if(runningRecord != null && runningRecord.getQueryTime() != null && runningRecord.getQueryTime().size() > 0){
            runningRecord.setStartTime(runningRecord.getQueryTime().get(0));
            runningRecord.setEndTime(runningRecord.getQueryTime().get(1));
        }
        List<RunningRecord> list = runRecordService.findByPage(runningRecord,pageable);
        List<RunningRecordVo> result = list.stream().map(e -> ConvertBeanUtils.translate(e, RunningRecordVo.class, "RunningRecord转RunningRecordVo")).collect(Collectors.toList());
        PageInfo<RunningRecord> pageInfo = new PageInfo(result);
        return new ResponseEntity<>(PageUtil.toPage(pageInfo.getList(),pageInfo.getTotal()), HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, RunningRecord runningRecord) throws IOException {
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody RunningRecordVo runningRecordVo){
        Long userId = getUserId();
        log.info("userId:"+userId);
        RunningRecord runningRecord = ConvertBeanUtils.translate(runningRecordVo, RunningRecord.class, "RunningRecordVo转RunningRecord");
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
        Long[] array = new Long[ids.size()];
        Iterator<Long> it = ids.iterator();
        int i = 0;
        while (it.hasNext()) {
            array[i] = it.next();
            i++;
        }
        runRecordService.deleteRunningRecord(array);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file){
        Long userId = getUserId();
        log.info("userId:"+userId);

        String contentType = file.getContentType();
        if(contentType.startsWith("image")){
            runRecordService.uploadOcr(file,userId);
        }else {
            runRecordService.upload(file,userId);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("ocrUpload")
    public ResponseEntity<Object> ocrUpload(@RequestParam("file") MultipartFile file){
        Long userId = getUserId();
        log.info("userId:"+userId);
        String contentType = file.getContentType();
        System.out.println("File ContentType【"+contentType+"】");

        if(!contentType.contains("image")){
            return new ResponseEntity<>("上传文件不为图片",HttpStatus.CREATED);
        }
        try {
            Map<String,Object> resultMap = ocrService.takePictureInformation(file);
            if(StringUtils.isEmpty(resultMap)){
                return new ResponseEntity<>("图片识别为空",HttpStatus.CREATED);
            }
            System.out.println("文件内容【"+ JSON.toJSONString(resultMap)+"】");
            RunningRecord runningRecord = new RunningRecord();
            runningRecord.setType("01");
            if(resultMap.containsKey("kilometre")){
                runningRecord.setKilometre(Double.parseDouble(resultMap.get("kilometre").toString()));
            }
            if(resultMap.containsKey("pace")){
                runningRecord.setPace(resultMap.get("pace").toString());
            }
            if(resultMap.containsKey("calorie")){
                runningRecord.setCalorie(Double.parseDouble(resultMap.get("calorie").toString()));
            }
            if(resultMap.containsKey("heartRate")){
                runningRecord.setHeartRate(Double.parseDouble(resultMap.get("heartRate").toString()));
            }
            if(resultMap.containsKey("strideRate")){
                runningRecord.setStrideRate(resultMap.get("strideRate").toString());
            }
            if(resultMap.containsKey("stride")){
                runningRecord.setStride(resultMap.get("stride").toString());
            }
            runRecordService.insertRunningRecord(runningRecord);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("图片识别出现异常"+e.getMessage(),HttpStatus.CREATED);
        }
    }

}
