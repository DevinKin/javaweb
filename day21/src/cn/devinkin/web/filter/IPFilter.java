package cn.devinkin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;

/**
 * 从application中获取Map
 * 从request中得到当前客户端的IP
 * 进行统计工作，结果保存到Map中
 */
@WebFilter(filterName = "IPFilter")
public class IPFilter implements Filter {
    private FilterConfig config;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /**
         * 1. 得到application中的map
         * 2. 从request中获取当前客户端的ip地址
         * 3. 查看map中是否存在这个ip对应访问次数，如果存在，把次数+1保存回去
         * 4. 如果不存在这个ip，那么说明是第一次访问本站，设置访问次数为1
         */

        ServletContext app = config.getServletContext();
        Map<String,Integer> ipMap = (Map<String, Integer>) app.getAttribute("ipMap");
        System.out.println(ipMap == null);
        String ip = req.getRemoteAddr();
        if (ipMap.containsKey(ip)) {
            int count = ipMap.get(ip);
            ipMap.put(ip, count+1);
        } else {
            ipMap.put(ip, 1);
        }
        app.setAttribute("ipMap", ipMap);
        chain.doFilter(req, resp);
    }

    /**
     * 在服务器启动时会执行本方法，并且只执行一次
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
