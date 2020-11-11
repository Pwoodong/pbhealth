package com.jiu.collect.mapper.ds1;

import com.jiu.collect.entity.RunningRecord;
import org.springframework.stereotype.Repository;

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
