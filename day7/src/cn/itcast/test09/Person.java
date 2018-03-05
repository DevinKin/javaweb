package cn.itcast.test09;

public class Person {
    //属性
    private String name;
    private String id;

    //没有参数的构造方法
    public Person() {

    }

    //有参数的构造方法
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
