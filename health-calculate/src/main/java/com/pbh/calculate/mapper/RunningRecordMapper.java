package com.pbh.calculate.mapper;

import com.pbh.api.entity.RunningRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package com.running.report.dao
 * ClassName ReportDao.java
 * Description ${DESCRIPTION}
 *
 * @author Jy
 * @version V1.0
 * @date 2019-12-16 14:00
 **/
@Mapper
public interface RunningRecordMapper {

    /**
     * 查询跑步记录
     * @param   runningRecord   跑步记录
     * @return  List<RunningRecord>
     */
    List<RunningRecord> selectRunningRecord(RunningRecord runningRecord);

    /**
     * 查询年度跑步记录
     * @param   runningRecord   跑步记录
     * @return  List<RunningRecord>
     */
    List<RunningRecord> selectMonthRunningRecord(RunningRecord runningRecord);

}
