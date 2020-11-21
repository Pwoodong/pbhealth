package com.jiu.system.service.impl;

import com.jiu.system.entity.SysMenuRole;
import com.jiu.system.dao.SysMenuRoleDao;
import com.jiu.system.service.SysMenuRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysMenuRole)表服务实现类
 *
 * @author makejava
 * @since 2020-11-18 20:22:29
 */
@Service("sysMenuRoleService")
public class SysMenuRoleServiceImpl implements SysMenuRoleService {
    @Resource
    private SysMenuRoleDao sysMenuRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysMenuRole queryById(Integer id) {
        return this.sysMenuRoleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysMenuRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenuRole insert(SysMenuRole sysMenuRole) {
        this.sysMenuRoleDao.insert(sysMenuRole);
        return sysMenuRole;
    }

    /**
     * 修改数据
     *
     * @param sysMenuRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenuRole update(SysMenuRole sysMenuRole) {
        this.sysMenuRoleDao.update(sysMenuRole);
        return this.queryById(sysMenuRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysMenuRoleDao.deleteById(id) > 0;
    }
}