package com.teoan.blogserver.service;

import com.teoan.blogserver.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author Toean
 * @since 2020-04-24 15:33:03
 */
public interface RolesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 为某用户添加角色
     *
     * @param roles 角色列表
     * @param id 用户id
     * @return 影响行数
     */
    int addRole(@Param("roles") String[] roles, @Param("id")Integer id);


    /**
     * 获取某用户的角色列表
     *
     * @param uid 用户id
     * @return 角色列表
     */
    List<Role> getRoleByUid(Integer uid);

}