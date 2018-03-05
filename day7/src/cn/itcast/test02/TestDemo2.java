package cn.itcast.test02;

import org.junit.Assert;
import org.junit.Test;

public class TestDemo2 {

    @Test
    public void test02() {
        int a = 3;
        int b = 5;
        int sum = a+b;

        //使用断言
        //Assert.assertEquals("测试期望的值", "方法运行的实际值");
        Assert.assertEquals(80, sum);
    }
}
