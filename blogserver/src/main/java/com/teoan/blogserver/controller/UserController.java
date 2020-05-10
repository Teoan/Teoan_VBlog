package com.teoan.blogserver.controller;

import com.teoan.blogserver.entity.RespBean;
import com.teoan.blogserver.entity.User;
import com.teoan.blogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author Toean
 * @since 2020-04-24 15:38:10
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.getUserById(id);
    }

    /**
     * 获取当前用户名字
     *
     * @return 用户名字
     */
    @RequestMapping("/currentUserName")
    public String currentUserName(){
        return User.getCurrentUser().getUsername();
    }

    /**
     * 获取当前用户id
     *
     * @return 用户id
     */
    @RequestMapping("/currentUserId")
    public Integer currentUserId(){
        return User.getCurrentUser().getId();
    }

    /**
     * 获取当前用户邮箱
     *
     * @return 用户邮箱
     */
    @RequestMapping("/currentUserEmail")
    public String currentUserEmail(){
        return User.getCurrentUser().getEmail();
    }

    /**
     * 判断当前用户是否为管理员
     *
     * @return 是否为管理员
     */
    @RequestMapping("/isAdmin")
    public Boolean isAdmin(){
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) User.getCurrentUser().getAuthorities();
        for(GrantedAuthority grantedAuthority:authorities){
            if(grantedAuthority.getAuthority().contains("超级管理员")){
                return true;
            }
        }
        return false;
    }

    /**
     * 更新当前用户邮箱
     *
     * @param
     * @return
     */
    @PutMapping("/updateUserEmail")
    public RespBean updateUserEmail(String email){
        if(userService.updateUserEmail(email)==1){
            return new RespBean("success", "开启成功!");
        }
        return new RespBean("error", "开启失败!");
    }

}