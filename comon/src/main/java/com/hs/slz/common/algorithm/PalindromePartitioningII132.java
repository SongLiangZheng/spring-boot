package com.hs.slz.common.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PalindromePartitioningII132 {
    public static void main(String[] args) {
        PalindromePartitioningII132 palindromePartitioningII132 = new PalindromePartitioningII132();
        System.out.println(palindromePartitioningII132.minCut("aab"));
    }

    public int minCut(String s) {
        int[] min = new int[]{Integer.MAX_VALUE};
        backtrace(s, min, 0, 0, new LinkedList<>());
        return min[0];
    }

    public void backtrace(String s, int[] min, int start, int times, LinkedList<String> strs) {
        if (start >= s.length()) {
            min[0] = Math.min(times - 1, min[0]);
        }
        for (int i = 1; i <= s.length(); i++) {
            if (start + i > s.length()) {
                break;
            }
            String substring = s.substring(start, start + i);
            if (isPalindrome(substring) && times <= min[0]) {
                strs.add(substring);
                times++;
                backtrace(s, min, start + i, times, strs);
                times--;
                strs.pop();
            }

        }
    }

    public boolean isPalindrome(String str) {
        if (str.length() == 1) {
            return true;
        }
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char c : str.toCharArray()) {
            linkedList.add(c);
        }
        while (linkedList.size() > 1) {
            if (!linkedList.pollFirst().equals(linkedList.pollLast())) {
                return false;
            }
        }
        return true;
    }
}
