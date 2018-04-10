package cn.devinkin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebFilter(filterName = "StaticFilter")
public class StaticFilter implements Filter {
    private FilterConfig config;
    private long lastModified = 0;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /**
         * 1. 第一次访问时，查找请求对应的html页面是否存在，如果存在重定向到html
         * 2. 如果不存在，放行！把servlet访问数据库后，输出给客户端的数据保存到一个html文件中
         * 再重定向到html
         */

        /**
         * 一、获取category参数！
         * category有四种可能：
         *  null -> null.html
         *  1 -> 1.html
         *  2 -> 2.html
         *  3 -> 3.thml
         * html页面的保存路径，htmls目录下
         * 判断对应的html文件是否存在，如果存在，直接重定向
         */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        ServletContext servletContext = config.getServletContext();

        String category = req.getParameter("cid");
        //得到对应的文件名称
        String htmlPage = category + ".html";
        //得到文件的存放目录
        String htmlPath = servletContext.getRealPath("/htmls");
        File destFile = new File(htmlPath, htmlPage);

        System.out.println(lastModified==destFile.lastModified());
        if (destFile.exists()) {
            if (lastModified == destFile.lastModified()) {
                // 重定向到这个文件
                response.sendRedirect(request.getContextPath() + "/htmls/" + htmlPage);
                return;
            }
        }

        /**
         * 二、如果html文件不存在，我们要创建html文件
         * 1. 放行，show.jsp会做出很多的输出，我们要让它别输出给客户端，而是
         * 输出到我们指定的一个html文件中
         * 2. 完成：
         * * 调包response，让它的getWriter()与一个html文件绑定，那么show.jsp的输出就到了html
         */
        System.out.println(destFile.getAbsolutePath());
        StaticResponse sr = new StaticResponse(response, destFile.getAbsolutePath());

        //放行，既生成了html文件
        chain.doFilter(req, sr);

        //这时页面已经存在，重定向到html文件
        response.sendRedirect(request.getContextPath() + "/htmls/" + htmlPage);

        lastModified = destFile.lastModified();
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
