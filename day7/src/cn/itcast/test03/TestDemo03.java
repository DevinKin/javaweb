package cn.itcast.test03;

import org.junit.Test;

import java.util.Arrays;

public class TestDemo03 {

    public static void main(String[] args)
    {
        //创建一个数组实现11和13位置交换
        Integer[] arr1 = {10, 11, 12, 13, 14};
        String[] arr2 = {"aaa", "bbb", "ccc", "ddd", "eee"};

        System.out.println(Arrays.toString(arr1));
        swap1(arr1, 1, 3);
        System.out.println(Arrays.toString(arr1));

        System.out.println("======================");

        //创建一个string类型的数组交换bb和dd位置交换
        System.out.println(Arrays.toString(arr2));
        swap1(arr2, 1, 3);
        System.out.println(Arrays.toString(arr2));


        System.out.println("======================");
        System.out.println(Arrays.toString(arr1));
        reverse(arr1, arr1.length);
        System.out.println(Arrays.toString(arr1));

    }

    public static <T> void swap1(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static <T> void reverse(T[] arr, int length) {
        T temp;

        for (int i = 0; i < length / 2; i++)
        {
            temp = arr[i];
            arr[i]=arr[length - i - 1];
            arr[length - i - 1] = temp;
        }
    }
    /*
    public static void swap1(int[] arr1, int i, int j) {
        int temp = arr1[i];
        arr1[i] = arr1[j];
        arr1[j] = temp;
    }

    private static void swap1(String[] arr2, int i, int j) {
        String temp = arr2[i];
        arr2[i] = arr2[j];
        arr2[j] = temp;
    }
    */
}
