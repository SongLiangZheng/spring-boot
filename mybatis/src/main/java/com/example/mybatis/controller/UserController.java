package com.example.mybatis.controller;

import com.example.mybatis.dto.User;
import com.example.mybatis.service.IUserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("")
    public List<User> getAllUsers(){
        String[] beanNamesForType = applicationContext
                .getBeanNamesForType(SqlSessionFactoryBean.class);
        System.out.println(beanNamesForType[0]);
        return userService.getUsers();
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }



    @GetMapping("update/{id}")
    public int updateUser(@PathVariable int id){
        User user = userService.getUserById(id);
        user.setName("update->"+user.getName());
        return userService.updateA(user);
    }

    @GetMapping("/delete/{id}")
    public int deleteUser(@PathVariable int id){
        return userService.delete(id);
    }



}
