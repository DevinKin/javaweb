package cn.devinkin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet{
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
            String result = (String) method.invoke(this, req, resp);
            /**
             * 获取请求处理方法执行后返回的字符串，它表示转发或重定向的路径
             * 帮它完成重定向
             * 如果用户返回的字符串是null，或为""，那么我们什么都做！
             *
             * 查看返回的字符串是否包含冒号，如果没有，表示转发
             * 如果有，使用冒号分割字符串，得到前缀和后缀
             * 其中前缀如果是f，表示转发，如果是r表示重定向，后缀就是要转发或重定向的路径
             */
            if (result == null || result.trim().isEmpty())
                return;
            if (result.contains(":")) {
                //使用冒号分割字符串
                int index = result.indexOf(":");
                String prefix = result.substring(0,index);
                String path = result.substring(index + 1, result.length());
                if (prefix.equalsIgnoreCase("r")) {
                    resp.sendRedirect(req.getContextPath() +  path);
                } else if (prefix.equalsIgnoreCase("f")) {
                    req.getRequestDispatcher(path).forward(req, resp);
                } else {
                    throw new RuntimeException("你指定的操作：" + prefix + "，当前版本还不支持");
                }


            } else {
                req.getRequestDispatcher(result).forward(req,resp);
            }
        } catch (Exception e) {
            System.out.println("您调用的方法：" + methodName + "，它内部抛出了异常！");
            throw  new RuntimeException(e);
        }
    }

}