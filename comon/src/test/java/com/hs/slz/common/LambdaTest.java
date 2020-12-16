package com.hs.slz.common;

import com.hs.slz.common.dto.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LambdaTest {

    List<User> userList = new ArrayList<>();

    {
        userList.add(new User("zhangsan", 13, 60D));
        userList.add(new User("lisi", 14, 80D));
        userList.add(new User("wangwu", 15, 80D));
    }

    @Test
    public void sum() {
//        java 8 stream 提供了下面几种类型的求和
//        Stream::mapToInt
//        Stream::mapToDouble
//        Stream::mapToLong
        Double a=userList.stream().mapToDouble(user->user.getAge()).sum();//userList不能为null或空
        System.out.println(a);
    }

    @Test
    public void filter(){
        List<User> userList = new ArrayList<>();
        long count = userList.stream().filter(user -> user.getAge() > 1000).count();
        System.out.println(count);
    }
}
