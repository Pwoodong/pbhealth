package com.jiu.collect.service.impl;

import com.jiu.collect.entity.RunningRecord;
import com.jiu.collect.mapper.ds1.RunningRecordMapper;
import com.jiu.collect.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Package com.jiu.collect.service.impl
 * ClassName DataCollectServiceImpl.java
 * Description 数据采集接口实现
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:26
 **/
@Service("dataCollectService")
public class DataCollectServiceImpl implements DataCollectService {

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    /**
     * @see DataCollectService#insertRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRunningRecord(RunningRecord runningRecord) {
        return runningRecordMapper.insert(runningRecord);
    }

    /**
     * @see DataCollectService#updateRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRunningRecord(RunningRecord runningRecord) {
        return runningRecordMapper.update(runningRecord);
    }

    /**
     * @see DataCollectService#deleteRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRunningRecord(RunningRecord runningRecord) {
        return runningRecordMapper.delete(runningRecord);
    }

    /**
     * @see DataCollectService#upload(MultipartFile)
     */
    @Override
    public int upload(MultipartFile file){
        //TODO 解析文件并将数据放入记录表中
        return 0;
    }
}
