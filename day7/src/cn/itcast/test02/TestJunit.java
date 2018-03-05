package cn.itcast.test02;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestJunit {

    public void testAdd(int a, int b) {
        System.out.println(a+b);
    }

    public void printTest() {
        System.out.println("test......");
    }

    @Test
    public void testAdd2() {
        testAdd(4,6);
    }

    @Before
    public void testBefore() {
        System.out.println("Before");
    }
    @Test
    public void testAdd1() {
        testAdd(3,4);
    }

    @Ignore
    public void test11() {
        printTest();
    }

    @After
    public void testAfter() {
        System.out.println("After");
    }
}
