package test.slz.flashSale;

import cn.hutool.core.lang.Console;
import com.slz.flashSale.FlashSaleStarter;
import com.slz.redis.starter.RedisOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zsl
 * @time: 2021/3/10 15:39
 */
@SpringBootTest(classes = FlashSaleStarter.class)
@RunWith(SpringRunner.class)
public class CommonTest {
    @Resource
    private RedisOperator redisOperator;

    @Test
    public void testSetNX() throws InterruptedException {
        String key="key";
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        List<Callable<Boolean>> callables=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Callable<Boolean> booleanCallable = () -> redisOperator.setNX(key, String.valueOf(finalI), 10000L);
            callables.add(booleanCallable);
        }
        List<Future<Boolean>> futures = executorService.invokeAll(callables);
        List<Boolean> collect = futures.stream().map(e -> {
            try {
                return e.get();
            } catch (InterruptedException | ExecutionException interruptedException) {
                interruptedException.printStackTrace();
            }
            return null;
        }).filter(e -> e).collect(Collectors.toList());
        Console.log(collect);
    }
}
