package com.hs.slz.mock;

import com.alibaba.testable.core.accessor.PrivateAccessor;
import com.alibaba.testable.core.annotation.MockMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 演示基本的Mock功能
 * Demonstrate basic mock functionality
 */
class DemoMockTest {

    @Test
    public void testPrivateAccessor() {
        System.out.println(PrivateAccessor.getStatic(User.class,"name").toString());
    }


    @MockMethod
    private String substring(String self,int i, int j) {
        return "sub_string";
    }

    @MockMethod
    public String helloWorld(DemoMock demoMock){
        return "hello kitty!";
    }

    @Test
    void should_able_to_mock_common_method() {
        System.out.println(new DemoMock().commonFunc());
        System.out.println(new DemoMock().helloWorld());
    }

}
