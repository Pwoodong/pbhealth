package com.jiu.system.service;

import com.jiu.system.entity.SysMenuRole;
import java.util.List;

/**
 * (SysMenuRole)表服务接口
 *
 * @author makejava
 * @since 2020-11-18 20:22:29
 */
public interface SysMenuRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysMenuRole queryById(Integer id);

    /**
     * 新增数据
     *
     * @param sysMenuRole 实例对象
     * @return 实例对象
     */
    SysMenuRole insert(SysMenuRole sysMenuRole);

    /**
     * 修改数据
     *
     * @param sysMenuRole 实例对象
     * @return 实例对象
     */
    SysMenuRole update(SysMenuRole sysMenuRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}