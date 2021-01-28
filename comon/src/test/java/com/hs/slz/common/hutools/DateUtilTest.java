package com.hs.slz.common.hutools;

import cn.hutool.core.date.*;
import cn.hutool.core.lang.Console;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateUtilTest {

    @Test
    public void str2Date(){
//        DateUtil.parse方法会自动识别一些常用格式，包括：
//        yyyy-MM-dd HH:mm:ss
//        yyyy-MM-dd
//        HH:mm:ss
//        yyyy-MM-dd HH:mm
//        yyyy-MM-dd HH:mm:ss.SSS
        String dateStr1="2021-01-22 12:10:00";
        String dateStr2="2021/01/22 12:10:00";
        String dateStr4="2021年01月22日 12:10:00";
        Assert.assertEquals("2021-01-22 12:10:00",DateUtil.parse(dateStr1).toString());
        Assert.assertEquals("2021-01-22 12:10:00",DateUtil.parse(dateStr2).toString());
        Assert.assertEquals("2021-01-22 12:10:00",DateUtil.parse(dateStr4).toString());
    }

    @Test
    public void testFormat() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        //结果 2017/03/01
        System.out.println(DateUtil.format(date, "yyyy/MM/dd"));


        //常用格式的格式化，结果：2017-03-01
        System.out.println(DateUtil.formatDate(date));
        //结果：2017-03-01 00:00:00
        System.out.println(DateUtil.formatDateTime(date));
        //结果：00:00:00
        System.out.println(DateUtil.formatTime(date));
    }

    @Test
    public void testGainPart() {
        Date date = DateUtil.date();//bad
        System.out.println(DateUtil.year(date)+" "+DateUtil.month(date)+" "+DateUtil.monthEnum(date));//2021 0 JANUARY
    }

    @Test
    public void testEdge() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        //一天的开始，结果：2017-03-01 00:00:00
        System.out.println(DateUtil.beginOfDay(date));
        //一天的结束，结果：2017-03-01 23:59:59
        System.out.println(DateUtil.endOfDay(date));
    }

    @Test
    public void testOffset() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);
        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);
        //昨天
        DateUtil.yesterday();
        //明天
        DateUtil.tomorrow();
        //上周
        DateUtil.lastWeek();
        //下周
        DateUtil.nextWeek();
        //上个月
        DateUtil.lastMonth();
        //下个月
        DateUtil.nextMonth();
    }

    @Test
    public void testDiff() {
        String dateStr1 = "2017-03-01 20:20:20";
        Date date1 = DateUtil.parse(dateStr1);
        String dateStr2 = "2017-03-02 23:23:23";
        Date date2 = DateUtil.parse(dateStr2);
        //27
        System.out.println(DateUtil.between(date1, date2, DateUnit.HOUR));

        //Level.SECOND表示精确到秒
        String formatBetween = DateUtil.formatBetween(date1,date2, BetweenFormatter.Level.SECOND);
        //输出：31天3小时3分3秒
        System.out.println(formatBetween);
    }

    @Test
    public void testTimer() throws InterruptedException {
        TimeInterval timer = DateUtil.timer();

        Thread.sleep(300);
        //花费毫秒数
        System.out.println(timer.interval());
        //返回花费时间，并重置开始时间
        System.out.println(timer.intervalRestart());
        //花费分钟数
        System.out.println(timer.intervalMinute());
    }
}
