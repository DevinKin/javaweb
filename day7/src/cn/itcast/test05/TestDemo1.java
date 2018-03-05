package cn.itcast.test05;

/**
 * 演示静态导入
 * @author DevinKin
 */


import java.util.Arrays;

import static java.lang.System.out;
import static java.util.Arrays.sort;

public class TestDemo1 {
    public static void main(String[] args) {
        out.println("hello");


        int[] arr1 = {10,1,3,20,15};
        sort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
