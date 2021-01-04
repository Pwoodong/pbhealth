package com.jiu.api.entity;

import lombok.Data;

import java.util.Date;

/**
 * Package com.jiu.api.entity
 * ClassName RunningRecord.java
 * Description 跑步记录实体
 *
 * @author Jy
 * @version V1.0
 * @date 2019-12-07 22:20
 **/
@Data
public class RunningRecord {
    /** ID */
    private Long id;
    /** 用户ID */
    private Long userId;
    /** 用户名称 */
    private String userName;
    /** 类型 */
    private String type;
    /** 公里数 */
    private Double kilometre;
    /** 耗时 */
    private Double consumeTime;
    /** 配速 */
    private String pace;
    /** 卡路里 */
    private Double calorie;
    /** 爬升 */
    private Double climb;
    /** 心率 */
    private Double heartRate;
    /** 步频 */
    private String strideRate;
    /** 步幅 */
    private String stride;
    /** 跑步时间 */
    private String runningTime;
    /** 状态 */
    private String status;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private Long createUserId;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人 */
    private Long updateUserId;

    /** 扩展 */
    /** 开始时间 */
    private String startTime;
    /** 结束时间 */
    private String endTime;

}
