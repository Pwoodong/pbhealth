package com.jiu.system.dao;

import com.jiu.system.entity.SysMenuRole;
import java.util.List;

/**
 * (SysMenuRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-18 20:22:29
 */
public interface SysMenuRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysMenuRole queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysMenuRole 实例对象
     * @return 对象列表
     */
    List<SysMenuRole> queryAll(SysMenuRole sysMenuRole);

    /**
     * 新增数据
     *
     * @param sysMenuRole 实例对象
     * @return 影响行数
     */
    int insert(SysMenuRole sysMenuRole);

    /**
     * 修改数据
     *
     * @param sysMenuRole 实例对象
     * @return 影响行数
     */
    int update(SysMenuRole sysMenuRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}