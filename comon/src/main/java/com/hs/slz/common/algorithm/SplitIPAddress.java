package com.hs.slz.common.algorithm;

import cn.hutool.core.lang.Console;
import com.hs.slz.common.dto.Bond;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

public class SplitIPAddress {
    public static void main(String[] args) {
        List<String> validList = new ArrayList<>();
        backTrace("20319630113", 0, new Stack<>(), validList);
        Console.log(validList);
    }

    private static void backTrace(String str, int start, Stack<String> list, List<String> validList) {
        if (!str.matches("[0-9]*")) {
            return;
        }
        if (list.size() >= 4) {
            validList.add(String.join(".", list));
        }
        List<String> nums = new ArrayList<>();
        if (list.size() < 3) {
            for (int i = 1; i <= 3; i++) {
                nums.add(get(start, i, str));
            }
        } else {
            nums.add(get(start, str.length() - start, str));
        }
        for (String num : nums) {
            if (isValid(num)) {
                list.add(num);
                backTrace(str, start + num.length(), list, validList);
                list.pop();
            }
        }
    }


    private static boolean isValid(String num) {
        return StringUtils.isNotBlank(num)&& !num.startsWith("0") && Integer.valueOf(num) <= 256;
    }

    private static String get(int start, int i, String str) {
        return str.substring(start, start + i);
    }
}
