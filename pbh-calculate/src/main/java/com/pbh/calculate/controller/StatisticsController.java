package com.pbh.calculate.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pbh.api.entity.RunningRecord;
import com.pbh.api.service.DataCollectService;
import com.pbh.calculate.service.StatisticsService;
import com.pbh.common.controller.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package com.running.report.controller
 * ClassName ReportController.java
 * Description 报表
 *
 * @author Jy
 * @version V1.0
 * @date 2019-12-16 13:51
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;

    @Reference
    DataCollectService dataCollectService;

    @PostMapping("getRecordList")
    public List<RunningRecord> getRecordList(String userId){
        RunningRecord record = new RunningRecord();
        //List<RunningRecord> list = statisticsService.selectRunningRecord(record);
        List<RunningRecord> list = dataCollectService.selectRunningRecord(record);
        return list;
    }

    @PostMapping("healthReport")
    public Map<String, Object> getHealthReport(){
        Map<String,Object> resultMap = new HashMap<>(3);
        RunningRecord record = new RunningRecord();
        Long userId = getUserId();
        log.info("userId:"+userId);
        if(userId != null){
            record.setUserId(userId);
        }
        record.setUserId(userId);
        Map<String,Object> weekList = statisticsService.selectWeekRecord(record);
        Map<String,Object> monthList = statisticsService.selectYearRecord(record);
        Map<String, Object> sumMap = statisticsService.summaryCalculation(record);
        resultMap.put("weekList",weekList);
        resultMap.put("monthList",monthList);
        resultMap.put("sum",sumMap);
        return resultMap;
    }

    @PostMapping("sum")
    public Map<String, Object> sum(){
        RunningRecord record = new RunningRecord();
        Long userId = getUserId();
        log.info("userId:"+userId);
        if(userId != null){
            record.setUserId(userId);
        }
        Map<String, Object> list = statisticsService.summaryCalculation(record);
        return list;
    }

}
