package com.example.mybatis.controller;

import com.example.mybatis.dto.User;
import com.example.mybatis.service.IUserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public List<User> getAllUsers(){
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

    @PostMapping("/post/users")
    public List<User> showUsers(@RequestBody List<User> users,String type){
        System.out.println(type);
        return users;
    }



}
