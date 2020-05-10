package com.teoan.blogserver.config;

import com.teoan.blogserver.entity.Role;
import com.teoan.blogserver.entity.User;
import com.teoan.blogserver.service.RolesService;
import com.teoan.blogserver.service.UserService;
import com.teoan.blogserver.service.impl.RolesServiceImpl;
import com.teoan.blogserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/4/25 12:18
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    RolesService rolesService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user= userService.loadUserByUsername(s);
        if(user==null){
            return new User();
        }
        List<Role> roles = rolesService.getRoleByUid(user.getId());
        user.setRoles(roles);
        return user;
    }
}
