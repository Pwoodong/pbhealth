package com.jiu.system.controller;

import com.jiu.system.entity.SysUser;
import com.jiu.system.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2020-11-18 20:21:37
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param reqMap 主键
     * @return 单条数据
     */
    @PostMapping("selectOne")
    public Map<String,Object> selectOne(@RequestBody Map<String,String> reqMap) {
        Map<String,Object> result = new HashMap<>();
        SysUser sysUser = this.sysUserService.queryById(Integer.valueOf(reqMap.get("userId")));
        result.put("avatar",sysUser.getNickName());
        result.put("name",sysUser.getNickName());
        result.put("title","");
        result.put("group","");
        result.put("signature","");
        result.put("email",sysUser.getEmail());
        result.put("unreadCount",sysUser.getAccount());
        result.put("userid",sysUser.getId());
        result.put("phone",sysUser.getTelPhone());
        return result;
    }

}