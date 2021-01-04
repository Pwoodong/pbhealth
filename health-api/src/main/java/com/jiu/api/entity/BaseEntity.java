package com.jiu.api.entity;

import lombok.Data;

/**
 * Package com.jiu.api.entity
 * ClassName BaseEntity.java
 * Description 基础数据实体
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-04 下午11:02
 **/
@Data
public class BaseEntity {
    /** 开始时间 */
    private String startTime;
    /** 结束时间 */
    private String endTime;
}
