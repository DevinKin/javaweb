package cn.itcast.test08;

public class TestDemo1 {
    public static void main(String[] args) {
        add1(1,2,3,4,5,6,7,8);
    }

    /*

    public void add1(int a, int b) {
        int sum = a + b;
        System.out.println(sum);
    }

    public void add1(int a, int b, int c) {
        int sum = a + b +c;
        System.out.println(sum);
    }
     */
    public static void add1(int ...nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println(sum);
    }
}
