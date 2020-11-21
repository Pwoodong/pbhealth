package com.jiu.system.controller;

import com.jiu.system.entity.SysMenuRole;
import com.jiu.system.service.SysMenuRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysMenuRole)表控制层
 *
 * @author makejava
 * @since 2020-11-18 20:22:29
 */
@RestController
@RequestMapping("sysMenuRole")
public class SysMenuRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuRoleService sysMenuRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysMenuRole selectOne(Integer id) {
        return this.sysMenuRoleService.queryById(id);
    }

}