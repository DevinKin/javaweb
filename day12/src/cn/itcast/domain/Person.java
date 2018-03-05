package cn.itcast.domain;

/**
 * JavaBean必须要为成员提供get/set方法(两者中只提供一个也是可以的！
 * 必须要有一个默认构造器(无参的！)
 * 一般对于具有get/set方法的成员变量,我们称之为属性
 *
 * 其实就算一个属性没有对应的成员变量,只有get/set方法也是可以的
 * 属性的名称就是get/set方法去除get/set后，再把字母小写了！
 */
public class Person {
    private String name;
    private int age;
    private String gender;

    public String getId() {
        return "Adfadfadf";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
    }

    public Person() {
        super();
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
