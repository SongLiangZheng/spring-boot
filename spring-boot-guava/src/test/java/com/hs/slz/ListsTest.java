package com.hs.slz;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListsTest {

    public static void main(String[] args) {
        List<String> personList = Lists.newArrayList("a", "b", "c", "d");

        // 拆分成[person1, person2, person3], [person4]   第二个参数为拆分长度
        List<List<String>> subList = Lists.partition(personList, 3);
        System.out.println(subList);

        List<String> reverse = Lists.reverse(personList);
        System.out.println(reverse);

        List<Character> qwer = Lists.charactersOf("qwer");
        System.out.println(qwer);

    }

    @Test
    public void testImmutableList() {
        ImmutableList<Integer> list1 = ImmutableList.of(1, 2);
        System.out.println(list1);

        ImmutableList<Integer> list2 = ImmutableList.<Integer>builder()
                .add(12)
                .add(23)
                .add(34)
                .build();

        List<String> list4 = new ArrayList<>();
        list4.add("1");
        list4.add("2");
        list4.add("3");
        //copy数组list4的一个副本
        List<String> list5 = ImmutableList.copyOf(list4);
    }

    @Test
    public void testSets() {
        /*求差集，返回结果Set是一个视图，不支持插入、删除操作*/
        Set<Character> first = Sets.newHashSet('a', 'b', 'c');
        Set<Character> second = Sets.newHashSet('b', 'c', 'd');
        System.out.println(Sets.union(first, second));
        System.out.println(Sets.difference(first, second));
        System.out.println(Sets.intersection(first, second));
    }
}