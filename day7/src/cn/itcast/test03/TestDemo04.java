package cn.itcast.test03;

public class TestDemo04<T> {

    //在类里面直接使用T的类型
    T aa;
    public void test11(T bb) {
    }

    //写一个静态方法,在类上面定义的泛型不能在静态方法里面使用
//    public static void test12(T cc) {

  //  }
}
