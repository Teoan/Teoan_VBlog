package com.teoan.blogserver.service;

import com.teoan.blogserver.entity.Role;
import com.teoan.blogserver.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author Toean
 * @since 2020-04-24 15:38:09
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User getUserById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 通过用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    User loadUserByUsername(String username);

    /**
     * 更新用户邮箱
     *
     * @param email 用户邮箱
     * @return 更新行数
     */
    int updateUserEmail( String email);

    /**
     * 根据昵称获取用户
     *
     * @param nickname 昵称
     * @return 用户
     */
    List<User> getUserByNickname(String nickname);

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
    int updateUserEnable(Boolean enable,Integer id);

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
    int setUserRoles(Integer[] rids, Integer id);

}