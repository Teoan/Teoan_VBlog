package com.teoan.blogserver.service.impl;

import com.teoan.blogserver.entity.Role;
import com.teoan.blogserver.entity.User;
import com.teoan.blogserver.mapper.RolesMapper;
import com.teoan.blogserver.mapper.UserMapper;
import com.teoan.blogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author Toean
 * @since 2020-04-24 15:38:09
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private RolesMapper rolesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User getUserById(Integer id) {
        return this.userMapper.getUserById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 0表示成功
     * 1表示用户名重复
     * 2表示失败
     */
    @Override
    public int insert(User user) {
        if(userMapper.loadUserByUsername(user.getUsername())!=null){
            return 1;
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setEnabled(true);
        int result=userMapper.insert(user);
        String[] roles = new String[]{"2"};
        int i = rolesMapper.addRoles(roles, user.getId());
        if(i==roles.length&&result==1){
            return 0;
        }else{
            return 2;
        }
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userMapper.update(user);
        return this.getUserById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userMapper.deleteUserById(id) > 0;
    }

    @Override
    public User loadUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }

    @Override
    public int updateUserEmail(String email) {
        return userMapper.updateUserEmail(email,User.getCurrentUser().getId());
    }

    @Override
    public List<User> getUserByNickname(String nickname) {
        return userMapper.getUserByNickname(nickname);
    }

    @Override
    public List<Role> getAllRole() {
        return userMapper.getAllRole();
    }

    @Override
    public int updateUserEnable(Boolean enable, Integer id) {
        return userMapper.updateUserEnable(enable,id);
    }

    @Override
    public int deleteUserRolesByUid(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int setUserRoles(Integer[] rids, Integer id) {
        userMapper.deleteUserRolesByUid(id);
        return userMapper.setUserRoles(rids,id);
    }
}