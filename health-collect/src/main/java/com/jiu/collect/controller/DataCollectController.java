package com.jiu.collect.controller;

import com.jiu.collect.entity.RunningRecord;
import com.jiu.collect.service.DataCollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package com.jiu.collect.controller
 * ClassName DataCollect.java
 * Description 数据采集接口
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:25
 **/
@Slf4j
@RestController
@RequestMapping("/dataCollect")
public class DataCollectController {

    @Autowired
    private DataCollectService dataCollectService;

    @GetMapping("getPageList")
    public Map<String,Object> getRecordList(String userId){
        Map<String,Object> result = new HashMap<>(4);
        RunningRecord record = new RunningRecord();
        List<RunningRecord> list = dataCollectService.selectRunningRecord(record);
        result.put("code","0");
        result.put("msg","成功");
        result.put("count",list.size());
        result.put("data",list);
        return result;
    }

    /**
     * 保存记录
     * @param    runningRecord      数据
     * @return
     **/
    @PostMapping(value = "/save")
    public Map<String, Object> save(RunningRecord runningRecord){
        Map<String,Object> resultMap = new HashMap<>(3);
        dataCollectService.insertRunningRecord(runningRecord);
        return resultMap;
    }

    @PostMapping(value = "/upload")
    public void upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String contentType = file.getContentType();
        dataCollectService.upload(file);
    }

}
