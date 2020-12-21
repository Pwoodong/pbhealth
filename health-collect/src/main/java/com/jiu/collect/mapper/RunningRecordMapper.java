package com.jiu.collect.mapper;

import com.jiu.api.entity.RunningRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package com.jiu.collect.mapper
 * ClassName RunningRecordMapper.java
 * Description 跑步记录数据接口
 *
 * @author Jy
 * @version V1.0
 * @date 2019-12-16 14:00
 **/
@Repository
public interface RunningRecordMapper {

    /**
     * 查询记录
     * @param   runningRecord   参数对象
     * @return  List<RunningRecord>
     */
    List<RunningRecord> select(RunningRecord runningRecord);

    /**
     * 新增记录
     * @param   runningRecord   数据
     * @return  int
     */
    int insert(RunningRecord runningRecord);

    /**
     * 修改记录
     * @param   runningRecord   数据
     * @return  int
     */
    int update(RunningRecord runningRecord);

    /**
     * 删除记录
     * @param   runningRecord   数据
     * @return  int
     */
    int delete(RunningRecord runningRecord);

}
