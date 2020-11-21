package com.jiu.system.service;

import com.jiu.system.entity.SysUserRole;
import java.util.List;

/**
 * (SysUserRole)表服务接口
 *
 * @author makejava
 * @since 2020-11-18 20:22:53
 */
public interface SysUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole queryById(Integer id);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole insert(SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}