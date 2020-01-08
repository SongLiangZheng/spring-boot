package com.example.mybatis.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.mybatis.dto.User;

@Service
public interface IUserService {


	public User getUserByName(String name);

	public User getUserById(int id);

	public List<User> getUsers();

	public int updateA(User user);

	public int delete(int id);
}
