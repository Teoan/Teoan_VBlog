package com.teoan.blogserver.mapper;

import com.teoan.blogserver.entity.Role;
import com.teoan.blogserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author Toean
 * @since 2020-04-24 15:38:09
 */
@Mapper
public interface UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User getUserById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteUserById(Integer id);

    /**
     * 通过用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    User loadUserByUsername(@Param("username")String username);

    /**
     * 更新用户邮箱
     *
     * @param email 用户邮箱
     * @param id 用户id
     * @return 更新行数
     */
    int updateUserEmail(@Param("email") String email,@Param("id")Integer id);

    /**
     * 根据昵称获取用户
     *
     * @param nickname 昵称
     * @return 用户
     */
    List<User> getUserByNickname(@Param("nickname")String nickname);

    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<Role>  getAllRole();

    /**
     * 更新用户状态
     *
     * @param enable 状态
     * @param id 用户id
     * @return 影响行数
     */
    int updateUserEnable(@Param("enable")Boolean enable,@Param("id")Integer id);

    /**
     * 删除某用户的所有角色
     *
     * @param id 用户id
     * @return 影响行数
     */
    int deleteUserRolesByUid(Integer id);

    /**
     * 为某个用户设置角色
     *
     * @param rids 角色列表
     * @param id 用户id
     * @return
     */
    int setUserRoles(@Param("rids") Integer[] rids, @Param("id") Integer id);




}