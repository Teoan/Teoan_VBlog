package com.teoan.blogserver.controller.admin;

import com.teoan.blogserver.entity.RespBean;
import com.teoan.blogserver.entity.Role;
import com.teoan.blogserver.entity.User;
import com.teoan.blogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/5/1 16:22
 */
@RestController
@RequestMapping("/admin")
public class UserManaController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getUserByNickname(String nickname) {
        return userService.getUserByNickname(nickname);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return userService.getAllRole();
    }


    @PutMapping("/user/enabled")
    public RespBean updateUserEnabled(Boolean enabled, Integer uid) {
        if (userService.updateUserEnable(enabled,uid) == 1) {
            return new RespBean("success", "更新成功!");
        } else {
            return new RespBean("error", "更新失败!");
        }
    }

    @DeleteMapping("/user/{uid}")
    public RespBean deleteUserById(@PathVariable("uid")Integer uid){
        if(userService.deleteById(uid)){
            return new RespBean("success", "删除成功!");
        }else{
            return new RespBean("error", "删除失败!");
        }
    }

    @PutMapping("/user/role")
    public RespBean updateUserRoles(Integer[] rids, Integer id) {
        if (userService.setUserRoles(rids, id) == rids.length) {
            return new RespBean("success", "更新成功!");
        } else {
            return new RespBean("error", "更新失败!");
        }
    }

}
