package com.jiu.collect.service;

import com.github.pagehelper.Page;
import com.jiu.api.entity.RunningRecord;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Package com.jiu.collect.service
 * ClassName RunningRecordService.java
 * Description 运动记录接口
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-02 下午2:08
 **/
public interface RunRecordService {

    /**
     * 分页查询记录
     *
     * @param  pageable 数据
     * @return List
     */
    Page<RunningRecord> findByPage(RunningRecord runningRecord,Pageable pageable);

    int insertRunningRecord(RunningRecord runningRecord);

    int updateRunningRecord(RunningRecord runningRecord);

    int deleteRunningRecord(String[] array);

}
