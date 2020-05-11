package com.example.mybatis;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
@Ignore
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
