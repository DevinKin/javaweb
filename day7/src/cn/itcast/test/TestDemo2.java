package cn.itcast.test;

public class TestDemo2 {
    public static void main(String[] args)
    {
        add1();
    }

    //实现1加到100
    public static void add1() {
        int sum = 0;
        for (int i=1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
