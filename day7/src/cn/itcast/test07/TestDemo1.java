package cn.itcast.test07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDemo1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        //使用增强for循环
        for(String s : list) {
            System.out.println(s);
        }

        System.out.println("=========================");

        //使用迭代器实现
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
