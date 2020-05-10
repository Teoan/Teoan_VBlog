package com.teoan.blogserver;

import com.teoan.blogserver.config.CustomUserDetailsService;
import com.teoan.blogserver.entity.Article;
import com.teoan.blogserver.entity.Role;
import com.teoan.blogserver.entity.User;
import com.teoan.blogserver.mapper.UserMapper;
import com.teoan.blogserver.service.ArticleService;
import com.teoan.blogserver.service.UserService;
import com.teoan.blogserver.service.impl.CategoryServiceImpl;
import com.teoan.blogserver.service.impl.RolesServiceImpl;
import com.teoan.blogserver.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogserverApplicationTests {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RolesServiceImpl rolesServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;
    @Test
    void contextLoads() {
        List<User> users=userService.getUserByNickname("");
        System.out.println(users);
    }

}
