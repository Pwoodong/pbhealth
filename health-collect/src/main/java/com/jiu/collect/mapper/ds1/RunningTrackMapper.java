package com.jiu.collect.mapper.ds1;

import com.jiu.collect.entity.RunningTrack;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package com.jiu.collect.mapper.ds1
 * ClassName RunningTrackMapper.java
 * Description  跑步轨迹数据接口
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-15 11:13
 **/
@Repository
public interface RunningTrackMapper {

    /**
     * 查询记录
     * @param   runningTrack   参数对象
     * @return  List<RunningRecord>
     */
    List<RunningTrack> select(RunningTrack runningTrack);

    /**
     * 新增记录
     * @param   runningTrack   数据
     * @return  int
     */
    int insert(RunningTrack runningTrack);

    /**
     * 批量新增记录
     * @param   list   数据
     * @return  int
     */
    int insertBatch(List<RunningTrack> list);

    /**
     * 修改记录
     * @param   runningTrack   数据
     * @return  int
     */
    int update(RunningTrack runningTrack);

    /**
     * 删除记录
     * @param   runningTrack   数据
     * @return  int
     */
    int delete(RunningTrack runningTrack);

}
