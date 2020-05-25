package com.example.mybatis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import com.example.mybatis.dao.UserMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Controller;
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
	@Autowired
	private UserMapper userMapper;

	@Test
	public void getAllUserByProcedure() {
		List<User> allUserByProcedure = userMapper.getAllUserByProcedure();
		System.out.println(new Gson().toJson(allUserByProcedure));
	}

	@Test
	public void testGetClass() throws IOException {
		Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation("com.example.mybatis", Controller.class);
		System.out.println(classes);
	}
}
