package com.pbh.collect.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.pbh.api.entity.RunningRecord;
import com.pbh.api.service.DataCollectService;
import com.pbh.collect.entity.RunningTrack;
import com.pbh.collect.mapper.RunningRecordMapper;
import com.pbh.collect.mapper.RunningTrackMapper;
import com.pbh.common.utils.GpxFileParseUtil;
import com.pbh.common.utils.ObjectTransformUtil;
import com.pbh.common.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Package com.pbh.collect.dubbo
 * ClassName DataCollectServiceImpl.java
 * Description 数据采集接口实现
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-02-24 下午11:15
 **/
@Slf4j
@Service
public class DataCollectServiceImpl implements DataCollectService {

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    @Autowired
    private RunningTrackMapper runningTrackMapper;

    @Override
    public Map<String,Object> queryAll(RunningRecord runningRecord, Pageable pageable) {
        List<RunningRecord> list = runningRecordMapper.select(runningRecord);
        Page<RunningRecord> page = (Page)list;
        return PageUtil.toPage(page,page.getTotalElements());
    }

    /**
     * @see DataCollectService#selectRunningRecord(RunningRecord)
     */
    @Override
    public List<RunningRecord> selectRunningRecord(RunningRecord runningRecord) {
        String userId = StringUtils.isEmpty(runningRecord.getUserId()) ? "" : String.valueOf(runningRecord.getUserId());
        String key = userId;
//        if(redisTemplate.hasKey(key)){
//            log.info("redis中查找的信息");
//            List<Object> list = redisTemplate.opsForList().range(key,0,-1);
//            try {
//                return ObjectToBeanUtil.objectToBean(list,RunningRecord.class);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        List<RunningRecord> list = runningRecordMapper.select(runningRecord);
//        redisTemplate.opsForList().leftPushAll(key,list);
//        log.info("mysql中查询的信息存入Redis中");
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
            //count = runningTrackMapper.insertBatch(insertList);
        } catch (IOException e) {
            log.error("解析文件出现异常."+e.getMessage());
        }
        return count;
    }
}
