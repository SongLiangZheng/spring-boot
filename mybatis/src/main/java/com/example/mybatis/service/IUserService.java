package com.example.mybatis.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.mybatis.dto.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface IUserService {


	public User getUserByName(String name);

	public User getUserById(int id);

	public List<User> getUsers();

	public int updateA(User user);

	public int delete(int id);

	int insert(User user);

	void require(String name);

	void requireCallNotRequire(String name);

	void notRequire(String name);

	void createRequire(String name);

	void createRequireCallNotRequire(String name);

	void createNotRequire(String name);
}
