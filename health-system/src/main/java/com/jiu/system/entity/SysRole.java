package com.jiu.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysRole)实体类
 *
 * @author makejava
 * @since 2020-11-18 20:22:04
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = -84918901850977440L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 角色编码
    */
    private String roleCode;
    /**
    * 角色名称
    */
    private String roleName;
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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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