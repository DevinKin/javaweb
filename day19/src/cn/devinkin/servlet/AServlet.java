package cn.devinkin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 这里给出多个请求处理方法
 * 请求处理方法：除了名称意外，都与service方法相同！
 */
public class AServlet extends javax.servlet.http.HttpServlet {
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 获取参数，用来识别用户想要请求的方法
         * 2. 然后判断是哪一个方法，是哪一个就调用哪一个
         */
        String methodName = req.getParameter("method");
        /**
         * 得到方法名称，是否可通过反射来调用方法？
         * 1. 得到方法名，通过方法名再得到Method类的对象
         *  - 需要得到Class，然后调用它的方法进行查询！得到Method
         *  - 我们要查询的是当前类的方法，所以我们需要得到当前类的Class
         */
        if (methodName == null || methodName.isEmpty()) {
            throw new RuntimeException("你没有传递method参数！无法确定你想要调用的方法！");
        }
        Class clzz = this.getClass();
        Method method = null;
        try {
            method = clzz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("你要调用的方法：" + methodName + "(HttpServletRequest req,HttpServletResponse resp)，它不存在");
        }
        /**
         * 调用method表示的方法
         */
        try {
            //this.addUser(req,resp); -> method.invoke(this,req,resp);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            System.out.println("您调用的方法：" + methodName + "，它内部抛出了异常！");
            throw  new RuntimeException(e);
        }
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("addUser()...");
    }


    public void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("editUser()...");
    }

    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("deleteUser()...");
    }
}
