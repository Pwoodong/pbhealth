package com.jiu.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysMenu)实体类
 *
 * @author makejava
 * @since 2020-11-18 20:21:04
 */
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -26765982481061392L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 类型
    */
    private String type;
    /**
    * 菜单编码
    */
    private String menuCode;
    /**
    * 菜单名称
    */
    private String menuName;
    /**
    * 菜单地址
    */
    private String menuUrl;
    /**
    * 父级ID
    */
    private Integer pId;
    /**
    * 方法
    */
    private String method;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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