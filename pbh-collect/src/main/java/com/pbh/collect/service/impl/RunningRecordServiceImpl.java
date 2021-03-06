package com.pbh.collect.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.pbh.api.entity.RunningRecord;
import com.pbh.collect.entity.RunningTrack;
import com.pbh.collect.mapper.RunningRecordMapper;
import com.pbh.collect.mapper.RunningTrackMapper;
import com.pbh.collect.service.OcrService;
import com.pbh.collect.service.RunRecordService;
import com.pbh.collect.utils.TimeUtil;
import com.pbh.common.algorithm.SnowFlake;
import com.pbh.common.utils.GpxFileParseUtil;
import com.pbh.common.utils.LatAndLonCalculateDistance;
import com.pbh.common.utils.ObjectTransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Package com.pbh.collect.service.impl
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

    @Autowired
    private OcrService ocrService;

    /**
     * @see RunRecordService#findByPage(RunningRecord,Pageable)
     */
    @Override
    public List<RunningRecord> findByPage(RunningRecord runningRecord,Pageable pageable) {
        log.info("分页入参："+(pageable.getPageNumber()+1)+","+pageable.getPageSize());
        PageHelper.startPage(pageable.getPageNumber()+1,pageable.getPageSize());
        return runningRecordMapper.findByPageNumSize(runningRecord);
    }

    /**
     * @see RunRecordService#insertRunningRecord(RunningRecord)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRunningRecord(RunningRecord runningRecord) {
        SnowFlake idWorker = new SnowFlake(0, 0);
        runningRecord.setId(String.valueOf(idWorker.nextId()));
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
     * @see RunRecordService#deleteRunningRecord(Long[])
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRunningRecord(Long[] array) {
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
                SnowFlake idWorker = new SnowFlake(0, 0);
                runningRecord.setId(String.valueOf(idWorker.nextId()));
                runningRecord.setUserId(userId);
                runningRecord.setType("01");
                BigDecimal b = new BigDecimal(kilometre);
                double f1 = b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                runningRecord.setKilometre(f1);
                /** 耗时匹配格式 01:01:01 */
                String consumeTimeStr = String.valueOf(consumeTime);
                consumeTimeStr = consumeTimeStr.replace(".",":");
                int len = consumeTimeStr.split(":").length;
                if(len == 2){
                    consumeTimeStr = "00:"+consumeTimeStr;
                }
                if(len == 1){
                    consumeTimeStr = "00:00:"+consumeTimeStr;
                }
                if(len == 0){
                    consumeTimeStr = "00:00:00";
                }
                runningRecord.setConsumeTime(consumeTimeStr);
                /** 配速匹配格式 6'00" */
                String paceStr = String.valueOf(pace);
                paceStr = paceStr.replace(".","'") + "\"";
                runningRecord.setPace(paceStr);
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

    /**
     * @see RunRecordService#uploadOcr(MultipartFile,Long)
     */
    @Override
    public int uploadOcr(MultipartFile file, Long userId){
        int count = 1;
        try {
            Map<String,Object> resultMap = ocrService.takePictureInformation(file);
            log.info("文件内容【"+ JSON.toJSONString(resultMap)+"】");
            RunningRecord runningRecord = new RunningRecord();
            SnowFlake idWorker = new SnowFlake(0, 0);
            runningRecord.setId(String.valueOf(idWorker.nextId()));
            runningRecord.setType("01");
            if(resultMap.containsKey("kilometre")){
                runningRecord.setKilometre(Double.parseDouble(resultMap.get("kilometre").toString()));
            }
            if(resultMap.containsKey("consumeTime")){
                /** 耗时匹配格式 01:01:01 */
                String consumeTimeStr = String.valueOf(resultMap.get("consumeTime").toString());
                consumeTimeStr = consumeTimeStr.replace(".",":");
                int len = consumeTimeStr.split(":").length;
                if(len == 2){
                    consumeTimeStr = "00:"+consumeTimeStr;
                }
                if(len == 1){
                    consumeTimeStr = "00:00:"+consumeTimeStr;
                }
                if(len == 0){
                    consumeTimeStr = "00:00:00";
                }
                runningRecord.setConsumeTime(consumeTimeStr);
            }
            if(resultMap.containsKey("pace")){
                /** 配速匹配格式 6'00" */
                String paceStr = String.valueOf(resultMap.get("pace").toString());
                StringBuffer stringBuffer = new StringBuffer(paceStr);
                for (int index = 2; index < paceStr.length(); index += 3) {
                    stringBuffer.insert(index, "'");
                }
                stringBuffer.append("\"");
                runningRecord.setPace(stringBuffer.toString());
            }
            if(resultMap.containsKey("calorie")){
                runningRecord.setCalorie(Double.parseDouble(resultMap.get("calorie").toString()));
            }
            if(resultMap.containsKey("heartRate")){
                runningRecord.setHeartRate(Double.parseDouble(resultMap.get("heartRate").toString()));
            }
            if(resultMap.containsKey("strideRate")){
                runningRecord.setStrideRate(resultMap.get("strideRate").toString());
            }
            if(resultMap.containsKey("stride")){
                runningRecord.setStride(resultMap.get("stride").toString());
            }
            if(resultMap.containsKey("runningTime")){
                String runningTime = TimeUtil.handleDateTimeByCn(resultMap.get("runningTime").toString());
                runningRecord.setRunningTime(runningTime);
            }
            runningRecord.setUserId(userId);
            runningRecord.setCreateUserId(userId);
            runningRecordMapper.insert(runningRecord);
        } catch (Exception e) {
            log.error("上传图片文件识别出现异常."+e.getMessage());
        }
        return count;
    }

}
