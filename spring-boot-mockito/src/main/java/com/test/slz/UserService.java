package com.test.slz;

import com.test.slz.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public User getUser(Long id) throws IllegalAccessException {
        if(id!=null){
            throw new IllegalAccessException("mock 失败了");
        }
        return new User();
    }


}
