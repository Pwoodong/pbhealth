package com.jiu.calculate.controller;

import com.jiu.calculate.entity.RunningRecord;
import com.jiu.calculate.service.RunningRecordService;
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
@RestController
@RequestMapping("runningRecordController")
public class RunningRecordController {

    @Autowired
    private RunningRecordService runningRecordService;

    @PostMapping("getRecordList")
    public List<RunningRecord> getRecordList(String userId){
        RunningRecord record = new RunningRecord();
        List<RunningRecord> list = runningRecordService.selectRunningRecord(record);
        return list;
    }

    @PostMapping("healthReport")
    public Map<String, Object> getHealthReport(Long userId){
        Map<String,Object> resultMap = new HashMap<>(3);
        RunningRecord record = new RunningRecord();
        record.setUserId(userId);
        List<RunningRecord> weekList = runningRecordService.selectWeekRecord(record);
        List<RunningRecord> monthList = runningRecordService.selectMonthRecord(record);
        Map<String, Object> sumMap = runningRecordService.summaryCalculation(record);
        resultMap.put("weekList",weekList);
        resultMap.put("monthList",monthList);
        resultMap.put("sum",sumMap);
        return resultMap;
    }

    @PostMapping("sum")
    public Map<String, Object> sum(Long userId){
        RunningRecord record = new RunningRecord();
        record.setUserId(userId);
        Map<String, Object> list = runningRecordService.summaryCalculation(record);
        return list;
    }

}
