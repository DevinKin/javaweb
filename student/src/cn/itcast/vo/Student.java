package cn.itcast.vo;

public class Student {
    private String id;
    private String name;
    private String age;

    public Student(String id, String name, String age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public String getId() {
        return id;
    }

    public void setId(String nId) {
        id = nId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String nAge) {
        age = nAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
