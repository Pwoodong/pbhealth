package com.jiu.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysUserRole)实体类
 *
 * @author makejava
 * @since 2020-11-18 20:22:53
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 206629878337378293L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 角色ID
    */
    private Integer roleId;
    /**
    * 状态
    */
    private String status;
    /**
    * 创建人
    */
    private Integer createUserId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改人
    */
    private Integer updateUserId;
    /**
    * 修改时间
    */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}