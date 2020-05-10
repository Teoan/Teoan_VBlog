package com.teoan.blogserver.service.impl;

import com.teoan.blogserver.entity.Role;
import com.teoan.blogserver.mapper.RolesMapper;
import com.teoan.blogserver.service.RolesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author Toean
 * @since 2020-04-24 15:33:03
 */
@Service("rolesService")
@Transactional
public class RolesServiceImpl implements RolesService {
    @Resource
    private RolesMapper rolesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer id) {
        return this.rolesMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.rolesMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.rolesMapper.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.rolesMapper.update(role);
        return this.queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.rolesMapper.deleteById(id) > 0;
    }


    @Override
    public int addRole(String[] roles, Integer id) {
        return rolesMapper.addRoles(roles,id);
    }

    @Override
    public List<Role> getRoleByUid(Integer uid) {
        return rolesMapper.getRoleByUid(uid);
    }
}