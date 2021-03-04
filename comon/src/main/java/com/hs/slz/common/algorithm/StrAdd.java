package com.hs.slz.common.algorithm;

import java.util.Stack;

public class StrAdd {
    public static void main(String[] args) {

        System.out.println(strAdd("123","456"));
        System.out.println(strAdd("1234","4567"));

        System.out.println(strAdd("1230456","4560789"));
    }

    private static String strAdd(String s1, String s2) {
        int i=s1.length()-1;
        int j=s2.length()-1;
        Stack<String> stack = new Stack<>();
        int supply=0;
        while (i >= 0 && j >= 0) {
            int num = char2Int(s1.charAt(i)) + char2Int(s2.charAt(j)) + supply;
            if (num >= 10) {
                supply = 1;
                stack.add(String.valueOf(num - 10));
            } else {
                supply = 0;
                stack.add(String.valueOf(num));
            }
            i--;
            j--;
        }
        while(i>=0){
            if(supply>0){
                stack.add(String.valueOf(char2Int(s1.charAt(i))+supply));
                supply=0;
            }else{
                stack.add(String.valueOf(char2Int(s1.charAt(i))));
            }
            i--;
        }
        while(j>=0){
            if(supply>0){
                stack.add(String.valueOf(char2Int(s2.charAt(j))+supply));
                supply=0;
            }else{
                stack.add(String.valueOf(char2Int(s2.charAt(j))));
            }
            j--;
        }
        StringBuilder sb = new StringBuilder();
        while (stack.size()>0){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static int char2Int(char c){
        return Integer.parseInt(String.valueOf(c));
    }
}
