package com.jiu.system.controller;

import com.jiu.common.constant.SystemCode;
import com.jiu.common.controller.BaseController;
import com.jiu.common.vo.ResponseVO;
import com.jiu.system.entity.SysRole;
import com.jiu.system.entity.SysUser;
import com.jiu.system.entity.UserCache;
import com.jiu.system.service.SysRoleService;
import com.jiu.system.service.SysUserService;
import com.jiu.system.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package com.jiu.system.controller
 * ClassName LoginController.java
 * Description 登录
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-18 20:00
 **/
@RestController
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 登录
     *
     * @return 登录成功消息返回TOKEN
     */

    @PostMapping("login")
    public ResponseVO login(@RequestBody Map<String,String> reqMap) {
    	if (StringUtils.isEmpty(reqMap)){
    		return new ResponseVO<>(SystemCode.E_BUSINESS, "参数不能为空！");
    	}
    	if (StringUtils.isEmpty(reqMap.get("userName"))){
    		return new ResponseVO<>(SystemCode.E_BUSINESS, "用户账号或密码错误！");
    	}
    	if (StringUtils.isEmpty(reqMap.get("password"))){
    		return new ResponseVO<>(SystemCode.E_BUSINESS, "用户账号或密码错误！");
    	}
    	String userName = reqMap.get("userName");
    	String password = reqMap.get("password");
    	SysUser sysUser;
    	try{
    		sysUser = sysUserService.queryByAccount(userName);
    	} catch(Exception e){
    		e.printStackTrace();
    		return new ResponseVO<>(SystemCode.E_BUSINESS, "用户数据异常，请检查用户信息！");
    	}
        if (sysUser == null) {
            return new ResponseVO<>(SystemCode.E_BUSINESS, "用户账号或密码错误！");
        }
        if (!sysUser.getPassword().equals(password)) {
            return new ResponseVO<>(SystemCode.E_BUSINESS, "用户账号或密码错误！");
        }
        List<String> roleCodes = new ArrayList<>();
        List<SysRole> roles = sysRoleService.queryAllByUserId(sysUser.getId());
        if (roles == null || roles.size() == 0) {
            return new ResponseVO<>(SystemCode.E_BUSINESS, "用户未分配角色，请联系管理员！");
        }

        roles.forEach(role -> roleCodes.add(role.getRoleCode()));
        String userId = sysUser.getId().toString();
        Cache userCache = cacheManager.getCache("userCache");
        //系统时间，用做token
        Long rom = System.currentTimeMillis();
        UserCache cacheUser = userCache.get(userId+"-",UserCache.class);
        if ( cacheUser != null){
            //再次登录
            cacheUser.setIp(getIp());
            cacheUser.setBrowserInfo(getRequest().getHeader("User-Agent"));
            userCache.put(userId,rom.toString());
            userCache.put(userId+"-",cacheUser);
        }else {
            UserCache us = new UserCache();
            us.setIp(getIp());
            us.setUserName(sysUser.getName());
            us.setTerminalType("");
            us.setRole(roleCodes);
            us.setBrowserInfo(getRequest().getHeader("User-Agent"));
            userCache.put(userId,rom.toString());
            userCache.put(userId+"-",us);
        }

        Claims claims = new DefaultClaims();
        claims.put("verifiId",rom);
        claims.put("getId",userId);
        Map<String, Object> map = new HashMap<>(4);
        map.put("personalName", sysUser.getNickName());
        map.put("userId", sysUser.getId().toString());
        map.put("token", JwtUtils.sign(claims));
		return new ResponseVO<>(SystemCode.OK,SystemCode.OK,map);
    }
}
