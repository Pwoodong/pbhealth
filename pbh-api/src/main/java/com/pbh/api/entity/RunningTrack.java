package com.pbh.api.entity;

import lombok.Data;

import java.util.Date;

/**
 * Package com.pbh.api.entity
 * ClassName RunningTrack.java
 * Description 运动轨迹实体
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-15 11:09
 **/
@Data
public class RunningTrack {

    /** ID */
    private Long id;
    /** 用户ID */
    private Long userId;
    /** 类型 */
    private String type;
    /** 经度 */
    private String longitude;
    /** 纬度 */
    private String latitude;
    /** 心率 */
    private Double heartRate;
    /** 海拔 */
    private String elevation;
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
}
