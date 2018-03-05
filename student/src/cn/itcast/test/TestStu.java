package cn.itcast.test;

import cn.itcast.vo.Student;
import cn.itcast.service.StuService;

public class TestStu {
    public static void main(String[] args) throws Exception {
        //testAdd();
        //testDel();
        testGet();
    }

    //测试添加方法
    public static void testAdd() throws Exception {
        //设置值
        Student stu = new Student("102","zhangsan","19");
        StuService.addStu(stu);
    }

    public static void testDel() throws Exception {
        StuService.delStu("102");
    }

    public static void testGet() throws Exception {
        Student stu = StuService.getStu("101");
        System.out.println(stu);
    }

}
