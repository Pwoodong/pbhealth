package com.jiu.collect.mapper.ds1;

import com.jiu.collect.entity.RunningRecord;

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
public interface RunningRecordMapper {

    /**
     * 查询记录
     * @param   runningRecord   参数对象
     * @return  List<RunningRecord>
     */
    List<RunningRecord> select(RunningRecord runningRecord);

    /**
     * 新增记录
     * @param   runningRecord   数据
     * @return  int
     */
    int insert(RunningRecord runningRecord);

    /**
     * 修改记录
     * @param   runningRecord   数据
     * @return  int
     */
    int update(RunningRecord runningRecord);

    /**
     * 删除记录
     * @param   runningRecord   数据
     * @return  int
     */
    int delete(RunningRecord runningRecord);

}
