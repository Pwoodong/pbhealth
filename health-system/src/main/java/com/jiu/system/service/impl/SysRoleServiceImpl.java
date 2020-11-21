package com.jiu.system.service.impl;

import com.jiu.system.entity.SysRole;
import com.jiu.system.dao.SysRoleDao;
import com.jiu.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-11-18 20:22:04
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Integer id) {
        return this.sysRoleDao.queryById(id);
    }

    @Override
    public List<SysRole> queryAllByUserId(Integer userId) {
        return this.sysRoleDao.queryAllByUserId(userId);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole insert(SysRole sysRole) {
        this.sysRoleDao.insert(sysRole);
        return sysRole;
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole update(SysRole sysRole) {
        this.sysRoleDao.update(sysRole);
        return this.queryById(sysRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysRoleDao.deleteById(id) > 0;
    }
}