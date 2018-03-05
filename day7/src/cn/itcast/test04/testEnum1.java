package cn.itcast.test04;

import java.awt.*;

public class testEnum1 {
    //传统的方式
    private int color;

    //第二种方式
    private Color2 color2;

    //第三宗方式 jdk5.0新特性 使用枚举
    private Color3 color3;
    public void test() {
        this.color = 1000; // Color.RED
//        this.color2 = new Color2.RED;
        this.color3 = Color3.GREEN;
    }
}

enum Color3 {
    RED,GREEN,YELLOW;
}

class Color2 {
    //构造方法私有化
    private Color2 (String name) { }
    public static final Color2 RED = new Color2("RED");
    public static final Color2 GREEN = new Color2("YELLOW");
    public static final Color2 YELLOW = new Color2("GREEN");

}
class Color1 {
   public static final int RED = 1;
   public static final int GREEN = 2;
   public static final int YELLOW = 3;
}