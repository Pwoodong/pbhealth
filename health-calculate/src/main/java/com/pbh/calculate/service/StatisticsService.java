package com.pbh.calculate.service;

import com.pbh.api.entity.RunningRecord;

import java.util.List;
import java.util.Map;

/**
 * Package com.running.report.service
 * ClassName ReportService.java
 * Description ${DESCRIPTION}
 *
 * @author Jy
 * @version V1.0
 * @date 2019-12-16 14:00
 **/
public interface StatisticsService {

    /**
     * 查询跑步记录
     *
     * @param runningRecord 入参
     * @return List<RunningRecord>     跑步记录列表
     */
    List<RunningRecord> selectRunningRecord(RunningRecord runningRecord);

    /**
     * 查询日记录
     *
     * @param runningRecord 入参
     * @return List<RunningRecord>     跑步记录列表
     */
    List<RunningRecord> selectDayRecord(RunningRecord runningRecord);

    /**
     * 查询周记录
     *
     * @param runningRecord 入参
     * @return List<RunningRecord>     跑步记录列表
     */
    Map<String,Object> selectWeekRecord(RunningRecord runningRecord);

    /**
     * 查询月记录
     *
     * @param runningRecord 入参
     * @return List<RunningRecord>     跑步记录列表
     */
    List<RunningRecord> selectMonthRecord(RunningRecord runningRecord);

    /**
     * 查询年记录
     *
     * @param runningRecord 入参
     * @return List<RunningRecord>     跑步记录列表
     */
    Map<String,Object> selectYearRecord(RunningRecord runningRecord);

    /**
     * 汇总计算
     *
     * @param runningRecord 入参
     * @return Map<String, Object>
     */
    Map<String, Object> summaryCalculation(RunningRecord runningRecord);

}
