package com.slz.rocketmq.delayMsg;

import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;

public class DemoMqContent extends RocketMqContent {
    private int id;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
