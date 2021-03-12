package com.slz.flashSale;

import cn.hutool.db.nosql.redis.RedisDS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: zsl
 * @time: 2021/3/10 11:18
 */
@SpringBootApplication
public class FlashSaleStarter {
    public static void main(String[] args) {
        SpringApplication.run(FlashSaleStarter.class,args);
    }
}
