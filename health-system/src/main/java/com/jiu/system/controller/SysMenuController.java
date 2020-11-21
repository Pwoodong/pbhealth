package com.jiu.system.controller;

import com.jiu.system.entity.SysMenu;
import com.jiu.system.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysMenu)表控制层
 *
 * @author makejava
 * @since 2020-11-18 20:21:04
 */
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysMenu selectOne(Integer id) {
        return this.sysMenuService.queryById(id);
    }

}