package com.teoan.blogserver.mapper;

import com.teoan.blogserver.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author Toean
 * @since 2020-04-24 15:33:02
 */
@Mapper
public interface RolesMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    List<Role> queryAll(Role role);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * 为某用户添加角色
     *
     * @param roles 角色列表
     * @param id 用户id
     * @return 影响行数
     */
    int addRoles(@Param("roles") String[] roles,@Param("id")Integer id);


    /**
     * 获取某用户的角色列表
     *
     * @param uid 用户id
     * @return 角色列表
     */
    List<Role> getRoleByUid(Integer uid);

}