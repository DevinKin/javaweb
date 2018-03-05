package cn.itcast.test04;

import org.junit.Test;

public class testEnum3 {

    //知道枚举对象,得到枚举名称和下标
    @Test
    public void test1() {
        // 得到枚举
        Color100 c100 = Color100.RED;
        // 得到枚举的名称
        System.out.println(c100.name());
        // 得到枚举的下标
        System.out.println(c100.ordinal());

    }

    //知道枚举的名称,得到枚举的对象和下标
    @Test
    public void test2() {
        String name = "GREEN";
        //得到枚举对象
        Color100 c1 = Color100.valueOf(name);
        System.out.println(c1.ordinal());
    }

    //知道枚举的下标,得到枚举的对象和名称
    @Test
    public void test3() {
        int idx2 = 2;
        //得到枚举对象
        Color100[] cs = Color100.values();
        //根据下标得到对象
        Color100 c100 = cs[idx2];
        System.out.println(c100.name());
    }
}

enum Color100 {
    RED,GREEN,YELLOW;
}