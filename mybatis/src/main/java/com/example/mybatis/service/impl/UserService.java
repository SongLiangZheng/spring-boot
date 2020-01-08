package com.example.mybatis.service.impl;

import com.example.mybatis.dao.UserMapper;
import com.example.mybatis.dto.User;
import com.example.mybatis.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public int updateA(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }
}
