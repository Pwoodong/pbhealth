package com.jiu.system.controller;

import com.jiu.system.entity.SysUserRole;
import com.jiu.system.service.SysUserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysUserRole)表控制层
 *
 * @author makejava
 * @since 2020-11-18 20:22:53
 */
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUserRole selectOne(Integer id) {
        return this.sysUserRoleService.queryById(id);
    }

}