package com.jiu.system.entity;

import java.io.Serializable;

/**
 * (SysMenuRole)实体类
 *
 * @author makejava
 * @since 2020-11-18 20:22:29
 */
public class SysMenuRole implements Serializable {
    private static final long serialVersionUID = 790534652080272816L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 菜单ID
    */
    private Integer menuId;
    /**
    * 角色ID
    */
    private Integer roleId;
    /**
    * 状态
    */
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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

}