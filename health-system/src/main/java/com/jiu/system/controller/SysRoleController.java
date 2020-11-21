package com.jiu.system.controller;

import com.jiu.system.entity.SysRole;
import com.jiu.system.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysRole)表控制层
 *
 * @author makejava
 * @since 2020-11-18 20:22:04
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRole selectOne(Integer id) {
        return this.sysRoleService.queryById(id);
    }

}