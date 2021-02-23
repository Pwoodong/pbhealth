package com.pbh.api.vo;

import cn.hutool.core.util.StrUtil;
import com.pbh.api.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * Package com.pbh.api.vo
 * ClassName RunningRecordVo.java
 * Description 运动记录视图实体
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-24 下午4:47
 **/
@Data
public class RunningRecordVo extends BaseEntity {

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
    /** 耗时-时 */
    private String consumeTimeHours;
    /** 耗时-分 */
    private String consumeTimeMinutes;
    /** 耗时-秒 */
    private String consumeTimeSeconds;
    /** 配速 */
    private String pace;
    /** 配速-分 */
    private String paceMinutes;
    /** 配速-秒 */
    private String paceSeconds;
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

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getConsumeTime() {
        if(StrUtil.isNotBlank(this.consumeTimeHours) && StrUtil.isNotBlank(this.consumeTimeMinutes) && StrUtil.isNotBlank(this.consumeTimeSeconds)){
            return this.consumeTimeHours + ":" + this.consumeTimeMinutes + ":" + this.consumeTimeSeconds;
        }
        return this.consumeTime;
    }

    public void setPace(String pace) {
        this.pace =  pace;
    }

    public String getPace() {
        if(StrUtil.isNotBlank(this.paceMinutes) && StrUtil.isNotBlank(this.paceSeconds)){
            return this.paceMinutes + "'" + this.paceSeconds + "\"";
        }
        return this.pace;
    }

    public String getConsumeTimeHours() {
        if(StrUtil.isNotBlank(this.consumeTime)){
            return this.consumeTime.split(":")[0];
        }
        return this.consumeTimeHours;
    }

    public void setConsumeTimeHours(String consumeTimeHours) {
        this.consumeTimeHours = consumeTimeHours;
    }

    public String getConsumeTimeMinutes() {
        if(StrUtil.isNotBlank(this.consumeTime)){
            return this.consumeTime.split(":")[1];
        }
        return this.consumeTimeMinutes;
    }

    public void setConsumeTimeMinutes(String consumeTimeMinutes) {
        this.consumeTimeMinutes = consumeTimeMinutes;
    }

    public String getConsumeTimeSeconds() {
        if(StrUtil.isNotBlank(this.consumeTime)){
            return this.consumeTime.split(":")[2];
        }
        return this.consumeTimeSeconds;
    }

    public void setConsumeTimeSeconds(String consumeTimeSeconds) {
        this.consumeTimeSeconds = consumeTimeSeconds;
    }

    public String getPaceMinutes() {
        if(StrUtil.isNotBlank(this.pace)){
            return this.pace.split("'")[0];
        }
        return this.paceMinutes;
    }

    public void setPaceMinutes(String paceMinutes) {
        this.paceMinutes = paceMinutes;
    }

    public String getPaceSeconds() {
        if(StrUtil.isNotBlank(this.pace)){
            return this.pace.split("'")[1].split("\"")[0];
        }
        return this.paceSeconds;
    }

    public void setPaceSeconds(String paceSeconds) {
        this.paceSeconds = paceSeconds;
    }
}
