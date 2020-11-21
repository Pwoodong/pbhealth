package com.jiu.system.service.impl;

import com.jiu.system.entity.SysRole;
import com.jiu.system.entity.SysUserRole;
import com.jiu.system.dao.SysUserRoleDao;
import com.jiu.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-11-18 20:22:53
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRole queryById(Integer id) {
        return this.sysUserRoleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRole insert(SysUserRole sysUserRole) {
        this.sysUserRoleDao.insert(sysUserRole);
        return sysUserRole;
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRole update(SysUserRole sysUserRole) {
        this.sysUserRoleDao.update(sysUserRole);
        return this.queryById(sysUserRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserRoleDao.deleteById(id) > 0;
    }
}