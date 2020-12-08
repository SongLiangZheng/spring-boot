package test.slz;


import com.test.slz.ApplicationStarter;
import com.test.slz.MethodTest;
import com.test.slz.UserService;
import com.test.slz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class SpyTest{
    @SpyBean
    private MethodTest spyTest;
    @MockBean
    private MethodTest mockTest;

    @MockBean
    private UserService userService;

    @Test
    public void test1() throws IllegalAccessException {
        when(userService.getUser(1L)).thenReturn(new User(1L,"李四",14));
        System.out.println(userService.getUser(1L));
    }

    @Test
    public void test3(){
        when(spyTest.Two(false)).thenReturn("test");
        System.err.println(spyTest.One(false));
    }

    @Test
    public void test4(){
        when(mockTest.One(false)).thenReturn("test");
        System.err.println(mockTest.One(false));
    }
}
