package com.jiu.collect.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jiu.api.entity.RunningRecord;
import com.jiu.api.service.DataCollectService;
import com.jiu.collect.mapper.RunningRecordMapper;
import com.jiu.collect.service.RunRecordService;
import com.jiu.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Package com.jiu.collect.service.impl
 * ClassName RunningRecordServiceImpl.java
 * Description 运动记录接口实现
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-02 下午2:05
 **/
@Service
public class RunningRecordServiceImpl implements RunRecordService {

    @Resource
    private RunningRecordMapper runningRecordMapper;

    /**
     * @see RunRecordService#findByPage(RunningRecord,Pageable)
     */
    @Override
    public Page<RunningRecord> findByPage(RunningRecord runningRecord,Pageable pageable) {
        //PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        System.out.println("分页入参："+pageable.getPageNumber()+","+pageable.getPageSize());
        return runningRecordMapper.findByPageNumSize(runningRecord,pageable.getPageNumber(),pageable.getPageSize());
    }

    /**
     * @see RunRecordService#insertRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRunningRecord(RunningRecord runningRecord) {
        return runningRecordMapper.insert(runningRecord);
    }

    /**
     * @see RunRecordService#updateRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRunningRecord(RunningRecord runningRecord) {
        return runningRecordMapper.update(runningRecord);
    }

    /**
     * @see RunRecordService#deleteRunningRecord(String[])
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRunningRecord(String[] array) {
        return runningRecordMapper.deleteByBatch(array);
    }

}
