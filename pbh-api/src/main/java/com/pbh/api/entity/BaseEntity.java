package com.pbh.api.entity;

import com.pbh.api.Query;
import lombok.Data;

import java.util.List;

/**
 * Package com.pbh.api.entity
 * ClassName BaseEntity.java
 * Description 基础数据实体
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-04 下午11:02
 **/
@Data
public class BaseEntity implements java.io.Serializable {
    /** 开始时间 */
    private String startTime;
    /** 结束时间 */
    private String endTime;

    @Query(type = Query.Type.BETWEEN)
    private List<String> queryTime;

}
