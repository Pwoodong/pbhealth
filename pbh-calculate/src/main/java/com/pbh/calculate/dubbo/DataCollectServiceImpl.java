package com.pbh.calculate.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.pbh.api.entity.RunningRecord;
import com.pbh.api.service.DataCollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Package com.pbh.calculate.dubbo
 * ClassName DataCollectServiceImpl.java
 * Description
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-02-25 上午12:01
 **/
@Slf4j
@Service
public class DataCollectServiceImpl implements DataCollectService {

    @Override
    public Map<String,Object> queryAll(RunningRecord runningRecord, Pageable pageable) {
        return null;
    }

    /**
     * @see DataCollectService#selectRunningRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectRunningRecord(RunningRecord runningRecord) {
        return null;
    }

    /**
     * @see DataCollectService#insertRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRunningRecord(RunningRecord runningRecord) {
        return 0;
    }

    /**
     * @see DataCollectService#updateRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRunningRecord(RunningRecord runningRecord) {
        return 0;
    }

    /**
     * @see DataCollectService#deleteRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRunningRecord(RunningRecord runningRecord) {
        return 0;
    }

    /**
     * @see DataCollectService#upload(MultipartFile)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int upload(MultipartFile file){
        return 0;
    }
}

