package com.hs.slz;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class ExceptionCheckTest {
    @Test
    public void testNP() {
        String param = "未读代码";
        Preconditions.checkNotNull(param);
    }

    @Test
    public void test() {
        String param2 = null;
        String name2 = Preconditions.checkNotNull(param2, "param2 is null");
    }

    @Test
    public void checkElementIndex() {
        // Guava 中快速创建ArrayList
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        Preconditions.checkElementIndex(5, list.size());
        // java.lang.IndexOutOfBoundsException: index (5) must be less than size (4)
    }
    @Test
    public void checkPositionIndex() {
        // Guava 中快速创建ArrayList
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        Preconditions.checkPositionIndex(5, list.size());
        // java.lang.IndexOutOfBoundsException: index (5) must be less than size (4)
    }
}
