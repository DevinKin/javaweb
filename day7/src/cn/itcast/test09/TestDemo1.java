package cn.itcast.test09;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取Class类
        Class clazz1 = Person.class;
        Class clazz2 = new Person().getClass();
        Class clazz3 = Class.forName("cn.itcast.test09.Person");


    }

    //操作无参数的构造方法
    @Test
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //得到Class
        Class clazz3 = Class.forName("cn.itcast.test09.Person");
        Person p = (Person)clazz3.newInstance();
        p.setName("张三");
        System.out.println(p.getName());
    }

    //操作有参数的构造方法
    @Test
    public void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //得到Class
        Class c1 = Class.forName("cn.itcast.test09.Person");
        //使用有参数的构造方法
        c1.getConstructors();       //获取所有的构造方法
        //传递是有参数的构造方法里面参数类型，类型使用class形式传递
        Constructor cs = c1.getConstructor(String.class, String.class);
        //通过有参数的构造设置值
        Person p1 = (Person)cs.newInstance("张三", "100");
        System.out.println(p1.getName() + " : " + p1.getId());
    }

    //操作name属性
    @Test
    public void test3() {
        //得到Class类
        try {
            Class c2 = Class.forName("cn.itcast.test09.Person");
            //得到name属性
            //c2.getDeclaredFields();         //表示得到所有的属性
            //通过这个方法得到属性，参数是属性的名称
            Field f1 = c2.getDeclaredField("name");
            Person p11 = (Person)c2.newInstance();
            //设置可以操作私有属性
            f1.setAccessible(true);
            f1.set(p11,"wangwu");       //相当于p11.name = "wangwu";
            System.out.println(f1.get(p11));    //相当于p11.name
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用反射操作普通方法
    @Test
    public void test4() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //得到Class类
        Class c4 = Class.forName("cn.itcast.test09.Person");
        //得到普通方法
        //c4.getDeclaredMethods();      //得到所有普通方法
        Method m1 = c4.getDeclaredMethod("setName", String.class);
        //得到Person实例
        Person p4 = (Person)c4.newInstance();

        //让setName方法执行,执行设置值
        //传递两个参数，第一个参数,方法名称,第二个参数,方法的参数,可变参数
        m1.invoke(p4, "李四");
        System.out.println(p4.getName());
    }
}
