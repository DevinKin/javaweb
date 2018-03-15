package cn.devinkin.dbUtils;

public class Stu {
    private int sid;
    private String sname;
    private int age;
    private String gender;

    public Stu(int sid, String sname, int age, String gender) {
        this.sid = sid;
        this.sname = sname;
        this.age = age;
        this.gender = gender;
    }

    public Stu() {
        super();
    }

    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
