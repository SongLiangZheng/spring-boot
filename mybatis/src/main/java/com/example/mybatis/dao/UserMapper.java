package com.example.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.mybatis.dto.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface UserMapper {
	@Select("select id,name,age from user where name=#{name}")
    User getUserByName(String name);

	@Select("select id,name,age from user where id=#{id}")
    User getUserById(int id);
	
	@Select("select * from user")
	@Transactional
    List<User> getUsers();

	@Update("update user set name = #{name} where id = #{id}")
    int updateUser(User user);

	@Delete("delete from user where id=#{id}")
    int delete(int id);

	@Insert("insert into user(name,age) values(#{name},#{age})")
    int insert(User user);

	List<User> getAllUsers();

	List<User> getAllUserByProcedure();
}
