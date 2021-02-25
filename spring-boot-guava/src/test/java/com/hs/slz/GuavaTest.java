package com.hs.slz;

import cn.hutool.core.lang.Console;
import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class GuavaTest {
    //连接器
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    //分割器
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();

    @Test
    public void testJoinerAndSplitter() {
        //把集合/数组中的元素join在一起
        Console.log(joiner.join(Lists.newArrayList("a", null, "b")));
        Console.log(splitter.split("a,,b,,"));
    }

    public static void main(String[] args) {
        System.out.println(Joiner.on("-").skipNulls().join(new String[]{"a", null}));
        System.out.println(Joiner.on(",").skipNulls().join(new String[]{null,"a"}));
        System.out.println(Joiner.on(",").skipNulls().join(new String[]{"a", "b"}));

    }

    public static final CharMatcher CHAR_MATCHER_DIGIT = CharMatcher.DIGIT;
    public static final CharMatcher CHAR_MATCHER_ANY = CharMatcher.ANY;

    @Test
    public void testCharMatcher() {
        //只保留匹配的字符
        System.out.println(CHAR_MATCHER_DIGIT.retainFrom("abc2def134f~"));
        //移除匹配的字符
        System.out.println(CHAR_MATCHER_DIGIT.removeFrom("yes,i love you 1314"));
    }

    @Test
    public void testBaseTypeEnhance() {
        List<Integer> list = Ints.asList(1, 3, 5, 7);
        String join = Ints.join(",", 1, 3, 5);

        int[] newIntArray = Ints.concat(new int[]{1, 2}, new int[]{2, 3, 4});
        Console.log(newIntArray);

        System.out.println(Ints.max(newIntArray));

        Console.log(Ints.contains(newIntArray, 6));
        int[] intArray = Ints.toArray(list);
    }

    @Test
    public void testMultiset() {//无序的，但是可以重复的集合
        HashMultiset<Object> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        Console.log(multiset.count("a"));
        Console.log(multiset);
    }

    @Test
    public void testImmutable() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        //这种视图不够安全，不是真正意义上的快照 怎么能随着变化呢？
        List<String> unsafeList = Collections.unmodifiableList(list);// 不安全
        List<String> readOnlyList = Collections.unmodifiableList(new ArrayList<>(list));//安全
        List<String> guavaReadOnlyList = ImmutableList.copyOf(list);//安全
//        readOnlyList.add("c");//java.lang.UnsupportedOperationException
        list.add("c");
        Console.log(unsafeList);// [a, b, c]
        Console.log(readOnlyList);// [a, b]
        Console.log(guavaReadOnlyList);// [a, b]
    }

    @Test
    public void testMultimap() {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("name", "zhangsan");
        multimap.put("name", "lisi");
        multimap.put("age", "13");
        Console.log(multimap);//{name=[zhangsan, lisi], age=[13]}
        List<String> names = multimap.get("name");
        Console.log(names);//["zhangsan",“lisi”]
    }

    @Test
    public void testBiMap() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("name", "zhangsan");
//        biMap.put("nick","zhangsan");//java.lang.IllegalArgumentException: value already present: zhangsan
        biMap.forcePut("nick", "zhangsan");
        biMap.put("深圳市龙岗区", "1234@qq.com");
        Console.log(biMap.inverse().get("1234@qq.com"));
        Console.log(biMap);
        //biMap.inverse()  != biMap ； biMap.inverse().inverse() == biMap
    }

    @Test
    public void testTable() {
        HashBasedTable<String, String, Integer> table = HashBasedTable.create();
        table.put("张三", "计算机", 80);
        table.put("张三", "数学", 90);
        table.put("张三", "语文", 100);
        table.put("李四", "计算机", 30);
        table.put("李四", "数学", 40);
        table.put("李四", "语文", 50);

        //最小单位cell
        Set<Table.Cell<String, String, Integer>> cellSet = table.cellSet();
        Console.log(cellSet);//[(李四,数学)=40, (李四,计算机)=30, (李四,语文)=50, (张三,数学)=90, (张三,计算机)=80, (张三,语文)=1000]
        //row set
        Console.log(table.rowKeySet());//[李四, 张三]
        //column set
        Console.log(table.columnKeySet());//[数学, 计算机, 语文]
        //根据rowKey获取信息
        Console.log(table.row("张三"));//{数学=90, 计算机=80, 语文=100}
        //根据column获取信息
        Console.log(table.column("计算机"));//{李四=30, 张三=80}
    }

    @Test
    public void testFunctions() {
        ArrayList<String> list = Lists.newArrayList("helloWorld", "yes", "zhangSan");
        Function<String, String> f1 = input -> input.length() <= 5 ? input : input.substring(0, 5);
        Function<String, String> f2 = String::toUpperCase;
        Function<String, String> f3 = Functions.compose(f1, f2);
        Console.log(Collections2.transform(list,f3));//[HELLO, YES, ZHANG]
    }
}