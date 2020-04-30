package com.example.mybatis.service;

import com.example.mybatis.dao.UserMapper;
import com.example.mybatis.dto.User;
import com.example.mybatis.service.IUserService;
import java.util.List;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        return userMapper.getAllUsers();
    }

    @Override
    public int updateA(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void require(String name) {
        createAndgetUser(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void requireCallNotRequire(String name) {
        User newUser = new User(name+"Outer", 13);
        userMapper.insert(newUser);
        IUserService proxy = (IUserService) AopContext.currentProxy();
        proxy.notRequire(name);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
    public void notRequire(String name) {
        createAndgetUser(name);
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void createRequire(String name) {
        createAndgetUser(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void createRequireCallNotRequire(String name) {
        User newUser = new User(name+"Outer", 13);
        userMapper.insert(newUser);
        IUserService proxy = (IUserService) AopContext.currentProxy();
        proxy.notRequire(name);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
    public void createNotRequire(String name) {
        createAndgetUser(name);
    }

    private void createAndgetUser(String name) {
        User newUser = new User(name, 13);
        userMapper.insert(newUser);
        User ii = userMapper.getUserByName(newUser.getName());
        System.out.println(ii.toString());
        throw new RuntimeException("自定义异常");
    }
}
