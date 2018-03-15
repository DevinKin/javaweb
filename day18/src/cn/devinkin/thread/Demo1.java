package cn.devinkin.thread;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal对象的用法
 */
public class Demo1 {
    @Test
    public void func1() {
        ThreadLocal<String> tl = new ThreadLocal<String>();
        //存
        tl.set("Hello");
        //取
        String s = tl.get();
        //删
        tl.remove();
        System.out.println(s);

        new Thread() {
            public void run() {
                System.out.println("内部类: " + tl.get());
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void func2() {
        Map<Thread, String> map = new HashMap<Thread,String>();
        map.put(Thread.currentThread(), "hello");
        System.out.println(Thread.currentThread());
        System.out.println(map.get(Thread.currentThread()));

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
                System.out.println(map.get(Thread.currentThread()));
            }
        }.start();
    }
}

/**
 * 它是一个内部是一个Map
 */
class TL<T> {
    private Map<Thread, T> map = new HashMap<Thread,T>();

    public void set(T data) {
        map.put(Thread.currentThread(), data);
    }

    public T get() {
        return map.get(Thread.currentThread());
    }

    public void remove() {
        map.remove(Thread.currentThread());
    }
}

/**
 * ThreadLocal通常用在一个类的成员上
 * 多个线程访问它时，每个线程都有自己的副本，互不干扰
 */
class User {
    private ThreadLocal<String> usernameT1 = new ThreadLocal<String>();
}