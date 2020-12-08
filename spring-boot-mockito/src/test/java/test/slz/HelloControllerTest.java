package test.slz;

import com.test.slz.ApplicationStarter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@WebAppConfiguration
public class HelloControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(new URI("/hello")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHello1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello1/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHello2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello2").param("par1", "admin").param("par2", "100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHello3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hello3").param("par", "admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHello4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hello4")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("par"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHello5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hello5")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"sid\":1,\"name\":\"admin\",\"birthday\":\"1983-10-22\",\"salary\":\"1000\",\"bonus\":100}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}