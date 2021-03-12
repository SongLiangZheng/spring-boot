package com.slz.flashSale;

import com.slz.redis.starter.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zsl
 * @time: 2021/3/10 11:21
 */
@RestController
public class StockController {

    public static final String REDIS_KEY_STOCK = "redis_key:stock:";
    public static final long COMMODITY_ID = 1;
    @Resource
    private StockService stockService;


    @GetMapping("stock")
    public Object stock() {
        return stockService.stock(getRedisKey(COMMODITY_ID),
                60 * 60,
                1,
                () -> initStock(COMMODITY_ID));
    }

    /**
     * 获取初始的库存
     *
     * @return
     */
    private int initStock(long commodityId) {
        // TODO 这里做一些初始化库存的操作
        return 3;
    }

    @GetMapping("/getStock")
    public Object getStock() {
        return stockService.getStock(getRedisKey(COMMODITY_ID));
    }

    @GetMapping("/addStock")
    public Object addStock() {
        return stockService.addStock(getRedisKey(COMMODITY_ID), 1);
    }

    private String getRedisKey(long commodityId) {
        return REDIS_KEY_STOCK + commodityId;
    }
}