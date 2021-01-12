package com.pbh.calculate.service.impl;

import com.pbh.api.entity.RunningRecord;
import com.pbh.api.service.DataCollectService;
import com.pbh.calculate.mapper.RunningRecordMapper;
import com.pbh.calculate.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
@Slf4j
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    //@DubboReference
    DataCollectService dataCollectService;

    @Resource
    private RunningRecordMapper runningRecordMapper;

    /**
     * @see StatisticsService#selectRunningRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectRunningRecord(RunningRecord runningRecord) {
        return runningRecordMapper.selectRunningRecord(runningRecord);
    }

    /**
     * @see StatisticsService#selectDayRecord(RunningRecord)
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
     * @see StatisticsService#selectWeekRecord(RunningRecord)
     */
    @Override
    public Map<String,Object> selectWeekRecord(RunningRecord runningRecord) {
        Map<String,Object> result = new HashMap<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toDayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime toDayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime oldDateTime = toDayStart.minusWeeks(1);
        LocalDate oldDate = oldDateTime.toLocalDate();
        runningRecord.setStartTime(df.format(oldDateTime));
        runningRecord.setEndTime(df.format(toDayEnd));
        List<RunningRecord> weekList = runningRecordMapper.selectRunningRecord(runningRecord);
        int[] xAxis = new int[7];
        double[] yAxisKilometre = new double[7];
        double[] yAxisConsumeTime = new double[7];
        for(int i = 7;i > 0;i--){
            int counter = 0;
            for(RunningRecord record:weekList){
                LocalDateTime runDateTime = LocalDateTime.parse(record.getRunningTime(),df);
                LocalDate runDate = runDateTime.toLocalDate();
                if(i == (oldDate.until(runDate, ChronoUnit.DAYS))){
                    yAxisKilometre[i-1] = record.getKilometre();
                    yAxisConsumeTime[i-1] = record.getConsumeTime();
                    counter ++;
                }
            }
            if(0 == counter){
                yAxisKilometre[i-1] = 0.0;
                yAxisConsumeTime[i-1] = 0.0;
            }
            LocalDate xDate = oldDate.plusDays(7-i+1);
            xAxis[7-i] = xDate.getDayOfMonth();
        }
        result.put("xAxis",xAxis);
        result.put("yAxisKilometre",yAxisKilometre);
        result.put("yAxisConsumeTime",yAxisConsumeTime);
        return result;
    }

    /**
     * @see StatisticsService#selectMonthRecord(RunningRecord)
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
     * @see StatisticsService#selectYearRecord(RunningRecord)
     */
    @Override
    public Map<String,Object> selectYearRecord(RunningRecord runningRecord) {
        Map<String,Object> result = new HashMap<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime toDayStart = LocalDateTime.of(LocalDate.now().withDayOfYear(1), LocalTime.MIN);
        LocalDateTime toDayEnd = LocalDateTime.of(LocalDate.now().withDayOfYear(365), LocalTime.MAX);
        LocalDate oldDate = toDayStart.toLocalDate();
        runningRecord.setStartTime(df.format(toDayStart));
        runningRecord.setEndTime(df.format(toDayEnd));
        int[] xAxis = new int[12];
        double[] yAxisKilometre = new double[12];
        double[] yAxisConsumeTime = new double[12];
        double[] yAxisCalorie = new double[12];
        List<RunningRecord> yearList = runningRecordMapper.selectMonthRunningRecord(runningRecord);
        for(int i = 0;i < 12;i++){
            int counter = 0;
            for(RunningRecord record:yearList){
                DateTimeFormatter dfm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate runDate = LocalDate.parse(record.getRunningTime()+"-01",dfm);
                int diffMonth = Period.between(oldDate,runDate).getMonths();
                log.debug("相差月份："+diffMonth);
                if(i == diffMonth){
                    yAxisKilometre[i] = record.getKilometre();
                    yAxisConsumeTime[i] = record.getConsumeTime();
                    yAxisCalorie[i] = record.getCalorie();
                    counter ++;
                }
            }
            if(0 == counter){
                yAxisKilometre[i] = 0.0;
                yAxisConsumeTime[i] = 0.0;
                yAxisCalorie[i] = 0.0;
            }
            if(i == 0){
                xAxis[0] = oldDate.getMonthValue();
            }else {
                LocalDate xDate = oldDate.plusMonths(i);
                xAxis[i] = xDate.getMonthValue();
            }
        }
        result.put("xAxis",xAxis);
        result.put("yAxisKilometre",yAxisKilometre);
        result.put("yAxisConsumeTime",yAxisConsumeTime);
        result.put("yAxisCalorie",yAxisCalorie);
        return result;
    }

    /**
     * @see StatisticsService#summaryCalculation(RunningRecord)
     */
    @Override
    public Map<String, Object> summaryCalculation(RunningRecord runningRecord){
        Map<String,Object> result = new HashMap<>(4);
        List<RunningRecord> list = runningRecordMapper.selectRunningRecord(runningRecord);
        Double sumKilometre = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getKilometre).sum();
        Double sumCalorie = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getCalorie).sum();
        Double sumConsumeTime = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getConsumeTime).sum();
        Double sumClimb = list.stream().collect(Collectors.toList()).stream().mapToDouble(RunningRecord::getClimb).sum();
        result.put("sumKilometre",sumKilometre);
        result.put("sumCalorie",sumCalorie);
        result.put("sumConsumeTime",sumConsumeTime);
        result.put("sumClimb",sumClimb);
        result.put("sumCount",list.size());
        return result;
    }

}
