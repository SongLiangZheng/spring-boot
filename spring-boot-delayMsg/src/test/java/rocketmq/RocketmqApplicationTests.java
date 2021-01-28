package rocketmq;

import com.slz.rocketmq.delayMsg.ApplicationStarter;
import com.slz.rocketmq.delayMsg.DemoRocketMqProducerExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class RocketmqApplicationTests {
    @Resource
    private DemoRocketMqProducerExample demoRocketMqProducerExample;
    @Test
    public void pushMessageToMQ() throws Exception {
        demoRocketMqProducerExample.execute();
    }

}
