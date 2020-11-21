package com.jiu.system.dao;

import com.jiu.system.entity.SysRole;
import com.jiu.system.entity.SysUserRole;
import java.util.List;

/**
 * (SysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-18 20:22:53
 */
public interface SysUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRole 实例对象
     * @return 对象列表
     */
    List<SysUserRole> queryAll(SysUserRole sysUserRole);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int insert(SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}