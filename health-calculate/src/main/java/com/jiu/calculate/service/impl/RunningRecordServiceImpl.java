package com.jiu.calculate.service.impl;

import com.jiu.calculate.entity.RunningRecord;
import com.jiu.calculate.mapper.ds1.RunningRecordMapper;
import com.jiu.calculate.service.RunningRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Package com.running.report.service.impl
 * ClassName ReportServiceImpl.java
 * Description ${DESCRIPTION}
 *
 * @author Jy
 * @version V1.0
 * @date 2019-12-16 14:01
 **/
@Service("runningRecordService")
public class RunningRecordServiceImpl implements RunningRecordService {

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    /**
     * @see RunningRecordService#selectRunningRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectRunningRecord(RunningRecord runningRecord) {
        return runningRecordMapper.selectRunningRecord(runningRecord);
    }

    /**
     * @see RunningRecordService#selectDayRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectDayRecord(RunningRecord runningRecord) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toDayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime toDayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        runningRecord.setStartTime(df.format(toDayStart));
        runningRecord.setEndTime(df.format(toDayEnd));
        return runningRecordMapper.selectRunningRecord(runningRecord);
    }

    /**
     * @see RunningRecordService#selectWeekRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectWeekRecord(RunningRecord runningRecord) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toDayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime toDayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        runningRecord.setStartTime(df.format(toDayStart.minusWeeks(1)));
        runningRecord.setEndTime(df.format(toDayEnd));
        return runningRecordMapper.selectRunningRecord(runningRecord);
    }

    /**
     * @see RunningRecordService#selectMonthRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectMonthRecord(RunningRecord runningRecord) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toDayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime toDayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        runningRecord.setStartTime(df.format(toDayStart.minusMonths(1)));
        runningRecord.setEndTime(df.format(toDayEnd));
        return runningRecordMapper.selectRunningRecord(runningRecord);
    }

    /**
     * @see RunningRecordService#selectYearRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectYearRecord(RunningRecord runningRecord) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toDayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime toDayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        runningRecord.setStartTime(df.format(toDayStart.minusYears(1)));
        runningRecord.setEndTime(df.format(toDayEnd));
        return runningRecordMapper.selectRunningRecord(runningRecord);
    }

    /**
     * @see RunningRecordService#summaryCalculation(RunningRecord)
     */
    @Override
    public Map<String, Object> summaryCalculation(RunningRecord runningRecord){
        Map<String,Object> result = new HashMap<>(4);
        List<RunningRecord> list = runningRecordMapper.selectRunningRecord(runningRecord);
        Double sumKilometre = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getKilometre).sum();
        Double sumHeartRate = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getHeartRate).sum();
        Double sumCalorie = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getCalorie).sum();
        Double sumConsumeTime = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getConsumeTime).sum();
        Double sumClimb = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getClimb).sum();
        result.put("sumKilometre",sumKilometre);
        result.put("sumHeartRate",sumHeartRate);
        result.put("sumCalorie",sumCalorie);
        result.put("sumConsumeTime",sumConsumeTime);
        result.put("sumClimb",sumClimb);
        result.put("sumCount",list.size());
        return result;
    }

}
