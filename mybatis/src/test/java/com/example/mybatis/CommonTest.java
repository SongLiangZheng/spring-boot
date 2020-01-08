package com.example.mybatis;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommonTest {
    @Test
    public void testMap() {
        String[] strs=new String[10];
        strs[0]="aa";
        System.out.println(strs.length);
        for(String str:strs)
            System.out.println(str+" ===");
    }

}
