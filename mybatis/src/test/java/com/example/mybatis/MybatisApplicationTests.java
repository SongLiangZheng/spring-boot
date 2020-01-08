package com.example.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mybatis.dto.User;
import com.example.mybatis.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

	@Autowired
	private IUserService userService;
	@Test
	public void contextLoads() {
		User user = userService.getUserByName("张三");
		System.out.println(user.getName());
	}

}
