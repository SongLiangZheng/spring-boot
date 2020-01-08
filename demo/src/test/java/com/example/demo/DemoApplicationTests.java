package com.example.demo;

import com.example.demo.dao.IClientinfoDAO;
import com.example.demo.dto.Clientinfo;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IClientinfoDAO clientinfoDAO;

    @Test
    void testMybatis() {
        Clientinfo clientinfo = new Clientinfo();
        clientinfo.setClientId("64324257");
        List<Clientinfo> clientinfos = clientinfoDAO.queryClientinfoBean(clientinfo);
        System.out.println(clientinfos.get(0).getClientId());
    }

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("foo","aa");
        Object foo = redisTemplate.opsForValue().get("foo");
        System.out.println(foo);
    }
}
