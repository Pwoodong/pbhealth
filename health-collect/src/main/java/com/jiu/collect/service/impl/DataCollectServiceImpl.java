package com.jiu.collect.service.impl;

import com.jiu.api.entity.RunningRecord;
import com.jiu.collect.entity.RunningTrack;
import com.jiu.collect.mapper.RunningRecordMapper;
import com.jiu.collect.mapper.RunningTrackMapper;
import com.jiu.api.service.DataCollectService;
import com.jiu.common.utils.GpxFileParseUtil;
import com.jiu.common.utils.ObjectToBeanUtil;
import com.jiu.common.utils.ObjectTransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Package com.jiu.collect.service.impl
 * ClassName DataCollectServiceImpl.java
 * Description 数据采集接口实现
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:26
 **/
@Slf4j
//@Service("dataCollectService")
@DubboService
@Component
public class DataCollectServiceImpl implements DataCollectService {

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    @Autowired
    private RunningTrackMapper runningTrackMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @see DataCollectService#selectRunningRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectRunningRecord(RunningRecord runningRecord) {
        String userId = StringUtils.isEmpty(runningRecord.getUserId()) ? "" : String.valueOf(runningRecord.getUserId());
        String key = userId;
        if(redisTemplate.hasKey(key)){
            log.info("redis中查找的信息");
            List<Object> list = redisTemplate.opsForList().range(key,0,-1);
            try {
                return ObjectToBeanUtil.objectToBean(list,RunningRecord.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<RunningRecord> list = runningRecordMapper.select(runningRecord);
        redisTemplate.opsForList().leftPushAll(key,list);
        log.info("mysql中查询的信息存入Redis中");
        return list;
        //return runningRecordMapper.select(runningRecord);
    }

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
    @Transactional(rollbackFor = Exception.class)
    public int upload(MultipartFile file){
        //TODO 解析文件并将数据放入记录表中
        int count = 0;
        try {
            InputStream inputStream = file.getInputStream();
            List<Map<String,Object>> mapList = GpxFileParseUtil.gpxParse(inputStream);
            List<RunningTrack> insertList = ObjectTransformUtil.setList(mapList,RunningTrack.class);
            count = runningTrackMapper.insertBatch(insertList);
        } catch (IOException e) {
            log.error("解析文件出现异常."+e.getMessage());
        }
        return count;
    }
}
