package com.jiu.collect.service;

import com.jiu.collect.entity.RunningRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Package com.jiu.collect.service
 * ClassName DataCollectService.java
 * Description 数据采集接口
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:26
 **/
public interface DataCollectService {

    /**
     * 新增记录
     *
     * @param runningRecord 数据
     * @return int
     */
    List<RunningRecord> selectRunningRecord(RunningRecord runningRecord);

    /**
     * 新增记录
     *
     * @param runningRecord 数据
     * @return int
     */
    int insertRunningRecord(RunningRecord runningRecord);

    /**
     * 修改记录
     *
     * @param runningRecord 数据
     * @return int
     */
    int updateRunningRecord(RunningRecord runningRecord);

    /**
     * 删除记录
     *
     * @param runningRecord 数据
     * @return int
     */
    int deleteRunningRecord(RunningRecord runningRecord);

    /**
     * 上传记录
     *
     * @param file 数据
     * @return int
     */
    int upload(MultipartFile file);

}
