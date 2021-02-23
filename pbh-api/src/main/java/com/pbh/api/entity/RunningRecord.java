package com.pbh.api.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.Date;

/**
 * Package com.pbh.api.entity
 * ClassName RunningRecord.java
 * Description 跑步记录实体
 *
 * @author Jy
 * @version V1.0
 * @date 2019-12-07 22:20
 **/
@Data
public class RunningRecord extends BaseEntity {
    /** ID */
    private String id;
    /** 用户ID */
    private Long userId;
    /** 用户名称 */
    private String userName;
    /** 类型 */
    private String type;
    /** 公里数 */
    private Double kilometre;
    /** 耗时 */
    private String consumeTime;
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

    public Double getDoubleConsumeTime(){
        Double result = 0.0;
        if(StrUtil.isNotBlank(this.consumeTime) && this.consumeTime.indexOf(":") != -1){
            String hours = this.consumeTime.split(":")[0];
            String minutes = this.consumeTime.split(":")[1];
            String seconds = this.consumeTime.split(":")[2];
            int total = Integer.valueOf(hours) * 3600 + Integer.valueOf(minutes) * 60 + Integer.valueOf(seconds);
            result = Double.valueOf(total / 60);
            return result;
        }
        return result;
    }
}
