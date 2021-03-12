package test.cool.slz;

import com.cool.slz.ApplicationStarter;
import com.cool.slz.client.entity.User;
import com.cool.slz.client.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description:
 * @author: zsl
 * @time: 2021/3/10 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
