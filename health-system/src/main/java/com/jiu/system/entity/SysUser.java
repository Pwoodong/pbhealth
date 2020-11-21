package com.jiu.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2020-11-18 20:21:37
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = 365551788218111202L;
    /**
    * ID
    */
    private Integer id;
    /**
    * 编码
    */
    private String code;
    /**
    * 账号
    */
    private String account;
    /**
    * 名称
    */
    private String name;
    /**
    * 密码
    */
    private String password;
    /**
    * 昵称
    */
    private String nickName;
    /**
    * 联系电话
    */
    private String telPhone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 状态
    */
    private String status;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 创建人
    */
    private Integer createUserId;
    /**
    * 修改时间
    */
    private Date updateTime;
    /**
    * 修改人
    */
    private Integer updateUserId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

}