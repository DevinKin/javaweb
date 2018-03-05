package cn.itcast.test03;

import org.junit.Test;

import java.util.*;

public class TestDemo02 {
    /**
     * 泛型在集合上的使用
     * @author DevinKin
     */

    @Test
    public void testList() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        //便利list集合有几种方式 3种
        //普通for循环 迭代器 增强for
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
        }
        System.out.println("===========================");

        //增强for循环
        for (String s1 : list)
        {
            System.out.println(s1);
        }
        System.out.println("===========================");
        //使用迭代器遍历
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    //泛型使用set集合上
    @Test
    public void testSet() {
        Set<String> set = new HashSet<String>();
        set.add("www");
        set.add("aaa");
        set.add("qqq");
        set.add("qqq");

        //遍历set 有两种方式
        //迭代器 增强for
        //增强for
        //不能有重复元素
        for (String s2 : set) {
            System.out.println(s2);
        }
        System.out.println("=============");

        Iterator<String> it = set.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aaa", "111");
        map.put("bbb", "222");
        map.put("ccc", "333");

        // 遍历map有两种方式
        // 1. 获取所有的key 通过key得到value,使用get方法
        // 2. 获取key和value的关系,entry

        //使用第一种方式遍历
        Set<String> sets = map.keySet();
        for (String key : sets)
        {
            System.out.println(key + " : " + map.get(key));
        }

        System.out.println("====================");
        //使用第二种方式遍历
        Set<Map.Entry<String, String>> set1 = map.entrySet();
        for (Map.Entry<String, String> entry : set1)
        {
            //enter是key和value关系
            String keyvv = entry.getKey();
            String valuev = entry.getValue();
            System.out.println(keyvv + " : " + valuev);
        }
    }
}
