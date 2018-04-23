package cn.devinkin.demo2;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示JSON-LIB小工具
 */
public class Demo2 {
    /**
     * 当map来用
     */
    @Test
    public void func1() {
        JSONObject map = new JSONObject();
        map.put("name", "zhangsan");
        map.put("age", 18);
        map.put("sex", "male");

        String s = map.toString();
        System.out.println(s);
    }

    /**
     * 当你已经有一个person对象时，可以把Person对象转换为JSONObject对象
     */
    @Test
    public void func2() {
        Person p = new Person("LISI", 32, "female");
        //把对象转换成JSONObject类型
        JSONObject  map = JSONObject.fromObject(p);
        System.out.println(map.toString());
    }

    /**
     * JSONArray
     */
    @Test
    public void func3() {
        Person p1 = new Person("zhangsan", 42, "male");
        Person p2 = new Person("lisi", 22, "female");
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

//        JSONArray list = new JSONArray();
//        list.add(p1);
//        list.add(p2);
//        System.out.println(list.toString());
        System.out.println(JSONArray.fromObject(list).toString());
    }
}
