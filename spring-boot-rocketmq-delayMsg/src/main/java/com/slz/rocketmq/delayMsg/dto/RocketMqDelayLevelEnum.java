package com.slz.rocketmq.delayMsg.dto;

import java.util.*;

public enum RocketMqDelayLevelEnum {
    //    1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
    DELAY_LEVEL1(1, 1L),
    DELAY_LEVEL2(2, 5L),
    DELAY_LEVEL3(3, 10L),
    DELAY_LEVEL4(4, 30L),
    DELAY_LEVEL5(5, 1000L),
    DELAY_LEVEL6(6, 2 * 1000L),
    DELAY_LEVEL7(7, 3 * 1000L),
    DELAY_LEVEL8(8, 4 * 1000L),
    DELAY_LEVEL9(9, 5 * 1000L),
    DELAY_LEVEL10(10, 6 * 1000L),
    DELAY_LEVEL11(11, 7 * 1000L),
    DELAY_LEVEL12(12, 8 * 1000L),
    DELAY_LEVEL13(13, 9 * 1000L),
    DELAY_LEVEL14(14, 10 * 1000L),
    DELAY_LEVEL15(15, 20 * 1000L),
    DELAY_LEVEL16(16, 30 * 1000L),
    DELAY_LEVEL17(17, 60 * 1000L),
    DELAY_LEVEL18(18, 2 * 60 * 1000L),
    ;
    private final Integer level;
    private final Long delayTime;//second

    RocketMqDelayLevelEnum(Integer level, Long delayTime){
        this.level=level;
        this.delayTime=delayTime;
    }
    public Integer getLevel() {
        return level;
    }

    public Long getDelayTime() {
        return delayTime;
    }
    public static RocketMqDelayLevelEnum getByLevel(Integer level) {
        return Arrays.stream(values()).filter(e->e.getLevel().equals(level)).findFirst().orElse(null);
    }

    public static RocketMqDelayLevelEnum getNearestDelayLevel(Long delayTime) {
        int length = values().length;
        for (int i = length - 1; i >= 0; i--) {
            RocketMqDelayLevelEnum delayLevel = values()[i];
            if (delayTime >= delayLevel.getDelayTime()) {
                return delayLevel;
            }
        }
        return RocketMqDelayLevelEnum.DELAY_LEVEL1;
    }
}
