package com.slz.hystrix.test;

import cn.hutool.core.lang.Console;
import com.slz.hystrix.ApplicationStarter;
import com.slz.hystrix.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@SpringBootTest(classes = ApplicationStarter.class)
@RunWith(SpringRunner.class)
public class HelloTest {

    @Resource
    private HelloService helloService;

    @Test
    public void testHello() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = i % 2 == 0 ? "normal" + i : "user" + 2;
//            new Thread(() -> helloService.sayHello(name)).start();
            Future<String> submit = executorService.submit(() -> helloService.sayHello(name));
            results.add(submit);
        }
        awaitAfterShutdown(executorService);
        Console.log(results.stream().map(e-> {
            try {
                return e.get();
            } catch (InterruptedException | ExecutionException interruptedException) {
                interruptedException.printStackTrace();
            }
            return "";
        }).collect(Collectors.toList()));
        for (int i = 0; i < 10; i++){
            String name = i % 2 == 0 ? "normal" + i : "user" + 2;
            System.out.println(helloService.sayHello(name));
        }
    }

    public void awaitAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }
}
