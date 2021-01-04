package com.jiu.collect.service.impl;

import com.github.pagehelper.Page;
import com.jiu.api.entity.RunningRecord;
import com.jiu.collect.entity.RunningTrack;
import com.jiu.collect.mapper.RunningRecordMapper;
import com.jiu.collect.mapper.RunningTrackMapper;
import com.jiu.collect.service.RunRecordService;
import com.jiu.common.utils.GpxFileParseUtil;
import com.jiu.common.utils.LatAndLonCalculateDistance;
import com.jiu.common.utils.ObjectTransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Package com.jiu.collect.service.impl
 * ClassName RunningRecordServiceImpl.java
 * Description 运动记录接口实现
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-02 下午2:05
 **/
@Slf4j
@Service
public class RunningRecordServiceImpl implements RunRecordService {

    @Resource
    private RunningRecordMapper runningRecordMapper;

    @Resource
    private RunningTrackMapper runningTrackMapper;

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

    /**
     * @see RunRecordService#upload(MultipartFile,Long)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int upload(MultipartFile file, Long userId){
        int count = 1;
        try {
            InputStream inputStream = file.getInputStream();
            List<Map<String,Object>> mapList = GpxFileParseUtil.gpxParse(inputStream);
            List<RunningTrack> insertList = ObjectTransformUtil.setList(mapList,RunningTrack.class);
            //count = runningTrackMapper.insertBatch(userId,insertList);
            log.info("上传数据条数:"+count);
            if(count > 0){
                double kilometre = 0.0;
                double climb = 0.0;
                for(int i = 0;i < insertList.size();i++){
                    if(i < insertList.size() - 1){
                        double latCenterRad = Double.valueOf(insertList.get(i).getLatitude());
                        double lonCenterRad = Double.valueOf(insertList.get(i).getLongitude());
                        double latVal = Double.valueOf(insertList.get(i+1).getLatitude());
                        double lonVal = Double.valueOf(insertList.get(i+1).getLongitude());
                        double distance = LatAndLonCalculateDistance.getDistance(latCenterRad,lonCenterRad,latVal,lonVal);
                        log.info("当前位置:"+i+",距离:"+distance);
                        kilometre = kilometre + distance;

                        BigDecimal climbEnd = new BigDecimal(insertList.get(i+1).getElevation());
                        BigDecimal climbStart = new BigDecimal(insertList.get(i).getElevation());
                        climb += climbEnd.subtract(climbStart).abs().doubleValue();
                    }
                }
                kilometre = (double) Math.round(kilometre * 100) / 100;
                log.info("总距离:"+kilometre);
                climb = (double) Math.round(climb * 100) / 100;
                log.info("爬升海拔:"+climb);
                String runStartTime = insertList.get(0).getRunningTime();
                LocalDateTime beginDateTime = LocalDateTime.parse(runStartTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                log.info("开始时间:"+runStartTime+"，转换后时间："+beginDateTime);
                String runEndTime = insertList.get(insertList.size()-1).getRunningTime();
                LocalDateTime endDateTime = LocalDateTime.parse(runEndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                log.info("结束时间:"+runEndTime+"，转换后时间："+endDateTime);
                Duration duration = Duration.between(beginDateTime,endDateTime);
                Double consumeTime = (double)duration.toMinutes();
                log.info("耗时:"+consumeTime);
                double pace = consumeTime / kilometre;
                pace = (double) Math.round(pace * 100) / 100;
                log.info("配速:"+pace);
                RunningRecord runningRecord = new RunningRecord();
                runningRecord.setUserId(userId);
                runningRecord.setType("01");
                runningRecord.setKilometre(kilometre);
                runningRecord.setConsumeTime(consumeTime);
                runningRecord.setPace(String.valueOf(pace));
                runningRecord.setRunningTime(runStartTime);
                runningRecord.setClimb(climb);
                runningRecord.setCreateUserId(userId);
                runningRecordMapper.insert(runningRecord);
            }
        } catch (IOException e) {
            log.error("解析文件出现异常."+e.getMessage());
        }
        return count;
    }

}
