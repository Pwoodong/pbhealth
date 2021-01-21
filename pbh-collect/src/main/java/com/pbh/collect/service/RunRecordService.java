package com.pbh.collect.service;

import com.pbh.api.entity.RunningRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Package com.pbh.collect.service
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
     * @param  runningRecord 数据
     * @param  pageable 分页数据
     * @return List
     */
    List<RunningRecord> findByPage(RunningRecord runningRecord, Pageable pageable);

    /**
     * 新增记录
     *
     * @param  runningRecord 数据
     * @return int
     */
    int insertRunningRecord(RunningRecord runningRecord);

    /**
     * 修改记录
     *
     * @param  runningRecord 数据
     * @return int
     */
    int updateRunningRecord(RunningRecord runningRecord);

    /**
     * 删除记录
     *
     * @param  array 数据
     * @return int
     */
    int deleteRunningRecord(String[] array);

    /**
     * 上传记录
     *
     * @param  file   文件数据
     * @param  userId 用户Id
     * @return int
     */
    int upload(MultipartFile file, Long userId);

    /**
     * 上传图片记录
     *
     * @param  file   文件数据
     * @param  userId 用户Id
     * @return int
     */
    int uploadOcr(MultipartFile file, Long userId);

}
