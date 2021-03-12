package com.hs.slz.common;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: Stream转换工具类
 * @Author: echo
 * @Date: 2020/11/12 18:45
 * @Version: 1.0
 */
public class StreamUtils {

    /**
     * 将List转成指定Key,Value的Map
     *
     * @param list     list
     * @param keyField key
     * @param value    value
     * @return Map
     */
    public static <T, K, V> Map<K, V> toMap(List<T> list,
                                            Function<? super T, ? extends K> keyField,
                                            Function<? super T, ? extends V> value) {
        if (CollectionUtil.isEmpty(list)) {
            return Maps.newHashMapWithExpectedSize(0);
        }

        return list.stream().collect(Collectors.toMap(keyField, value, (o1, o2) -> o1));
    }

    /**
     * 将List转成指定Key,Value的Map
     *
     * @param list     list
     * @param keyField key
     * @return Map
     */
    public static <T, K> Map<K, T> toMap(List<T> list, Function<? super T, ? extends K> keyField) {
        if (CollectionUtil.isEmpty(list)) {
            return Maps.newHashMapWithExpectedSize(0);
        }

        return list.stream().collect(Collectors.toMap(keyField, Function.identity(), (o1, o2) -> o1));
    }

    /**
     * 通过指定条件过滤
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @return 已过滤集合
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * 根据对象的属性去重
     *
     * @param list         原列表
     * @param keyExtractor 属性映射
     * @param <T>          对象类型
     * @return 去重后的列表
     */
    public static <T> List<T> distinct(Collection<T> list, Function<? super T, Object> keyExtractor) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        return list.stream().filter(distinctByKey(keyExtractor)).collect(Collectors.toList());
    }

    /**
     * 将对象put到map中，如果map已存在该对象，则Predicate的结果为false，否则为true
     *
     * @param keyExtractor 被比较的对象
     * @param <T>          对象类型
     * @return 比对结果
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 根据对象的任一属性去重
     *
     * @param list          原列表
     * @param keyExtractors 属性映射列表
     * @param <T>           对象类型
     * @return 去重后的列表
     */
    public static <T> List<T> anyDistinct(Collection<T> list, Function<T, Object>... keyExtractors) {
        return list.stream().filter(anyDistinctByKey(keyExtractors)).collect(Collectors.toList());
    }

    /**
     * 具体去重判断
     *
     * @param functions 被比较的对象属性列表
     * @param <T>       对象类型
     * @return 判断结果，true表示无重复值，false表示有重复值
     */
    private static <T> Predicate<T> anyDistinctByKey(Function<T, Object>... functions) {
        int length = functions.length;

        // 初始化判断重复的集合
        List<Set<String>> list = Lists.newArrayListWithCapacity(length);
        for (int i = 0; i < length; i++) {
            list.add(Sets.newHashSet());
        }

        return t -> {
            // 判断是否与之前的数据的重复
            boolean existFlag = consumeLoop(functions, list, t, true, Set::contains);

            // 如果没有重复的数据，则将对应的值加入到集合中
            if (!existFlag) {
                consumeLoop(functions, list, t, false, Set::add);
            }

            return !existFlag;
        };
    }

    /**
     * 遍历Function判断条件是否成立
     *
     * @param functions   对象与成员属性的映射数组
     * @param list        存储set的集合，需与{@code functions}等长
     * @param obj         被操作的对象
     * @param trueExist   条件成立时是否退出循环
     * @param biPredicate 对集合与元素的逻辑操作
     * @return {@code biPredicate}只需一次布尔结果为true时则返回true，全为false时才返回false
     */
    private static <T> boolean consumeLoop(Function<T, Object>[] functions,
                                           List<Set<String>> list,
                                           T obj,
                                           boolean trueExist,
                                           BiPredicate<Set<String>, String> biPredicate) {
        int length = functions.length;
        boolean result = false;
        for (int i = 0; i < length; i++) {
            Function<? super T, Object> function = functions[i];
            Object property = function.apply(obj);
            if (Objects.isNull(property)) {
                continue;
            }

            String str = property.toString();
            if (StringUtils.isBlank(str)) {
                continue;
            }

            Set<String> set = list.get(i);
            if (biPredicate.test(set, str)) {
                result = true;
            }

            if (trueExist && result) {
                break;
            }
        }

        return result;
    }

    /**
     * 查找符合条件的第一个对象，如果没有符合条件的对象，则返回{@code null}
     *
     * <p>例：LambdaKit.findFirst(lstOtask, task -> task.getStatus().equals(taskStatus))
     *
     * @param list      集合
     * @param predicate 条件
     * @return 第一个对象
     */
    public static <T> T findFirst(List<T> list, Predicate<? super T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        list = list.stream().filter(predicate).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 获取无重复值的集合
     *
     * @param list   源集合
     * @param mapper 要返回的集合类型
     * @return 结果集
     */
    public static <T, R> List<R> mapUnique(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * 获取转换后的集合
     *
     * @param list   源集合
     * @param mapper 要返回的集合类型
     * @return 结果集
     */
    public static <T, R> List<R> map(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 根据指定条件分组
     *
     * @param list   源集合
     * @param mapper 映射条件
     * @param <T>    集合对象类型
     * @param <R>    映射后的Map的key类型
     * @return Map
     */
    public static <T, R> Map<R, List<T>> group(Collection<T> list, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        return list.stream().collect(Collectors.groupingBy(mapper));
    }

    /**
     * 根据指定条件分割
     *
     * @param list      源集合
     * @param predicate 映射条件
     * @param <T>       集合对象类型
     * @return Map
     */
    public static <T> Map<Boolean, List<T>> partition(Collection<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        return list.stream().collect(Collectors.partitioningBy(predicate));
    }

    /**
     * 通过指定条件过滤，并转换集合
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @param mapper    要返回的集合类型
     * @return 已过滤集合
     */
    public static <T, R> List<R> filterAndMap(List<T> list, Predicate<T> predicate, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        return list.stream()
                .filter(predicate)
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * 通过指定条件过滤，并分组
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @param mapper    分组逻辑
     * @return 分组后集合
     */
    public static <T, R> Map<R, T> filterAndToMap(List<T> list, Predicate<T> predicate, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }

        return list.stream()
                .filter(predicate)
                .collect(Collectors.toMap(mapper, Function.identity(), (o1, o2) -> o1));
    }

    /**
     * 通过指定条件过滤，并分组
     *
     * @param list      待过滤集合
     * @param predicate 过滤条件
     * @param mapper    分组逻辑
     * @return 分组后集合
     */
    public static <T, R> Map<R, List<T>> filterAndGroup(List<T> list, Predicate<T> predicate, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }

        return list.stream()
                .filter(predicate)
                .collect(Collectors.groupingBy(mapper));
    }

    /**
     * 排序
     */
    public static <T, U extends Comparable<? super U>> List<T> sort(List<T> list,
                                                                    Function<? super T, ? extends U> function) {
        return sort(list, function, false);
    }

    /**
     * 排序（是否倒序）
     */
    public static <T, U extends Comparable<? super U>> List<T> sort(List<T> list,
                                                                    Function<? super T, ? extends U> function,
                                                                    boolean reverse) {
        return sort(list, function, reverse, null, false);
    }

    /**
     * 排序（两级排序）
     */
    public static <T, U extends Comparable<? super U>> List<T> sort(List<T> list,
                                                                    Function<? super T, ? extends U> function1,
                                                                    boolean reverse1,
                                                                    Function<? super T, ? extends U> function2,
                                                                    boolean reverse2) {
        Comparator<T> comparing = Comparator.comparing(function1);
        if (reverse1) {
            comparing.reversed();
        }
        if (function2 != null) {
            comparing.thenComparing(function2);
            if (reverse2) {
                comparing.reversed();
            }
        }
        return list.stream()
                .sorted(comparing).collect(Collectors.toList());
    }

    /**
     * 合并字段
     *
     * @param list        集合
     * @param mapper      要合并的字段
     * @param joinContent 分隔符
     * @return 合并结果
     */
    public static <T> String join(List<T> list, Function<T, ? extends CharSequence> mapper, CharSequence joinContent) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }

        return list.stream()
                .map(mapper)
                .collect(Collectors.joining(joinContent));
    }

    /**
     * 通过指定条件过滤，并合并字段
     *
     * @param list        集合
     * @param predicate   过滤条件
     * @param mapper      要合并的字段
     * @param joinContent 分隔符
     * @return 合并结果
     */
    public static <T> String filterAndJoin(List<T> list, Predicate<T> predicate, Function<T, ? extends CharSequence> mapper, CharSequence joinContent) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }

        return list.stream()
                .filter(predicate)
                .map(mapper)
                .collect(Collectors.joining(joinContent));
    }

}
