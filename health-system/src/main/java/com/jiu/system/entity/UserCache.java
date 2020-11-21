package com.jiu.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @program: cem
 * @description: 登录信息缓存
 * @author: peihao
 * @create: 2019-03-08 18:37
 **/
public class UserCache implements Serializable {

    private String ip;
    private List<String> role;
    private String userName;
    private String terminalType;
    private String browserInfo;

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }
}
