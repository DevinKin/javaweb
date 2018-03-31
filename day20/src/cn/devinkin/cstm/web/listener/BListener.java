package cn.devinkin.cstm.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class BListener implements ServletContextAttributeListener{
    @Override
    public void attributeAdded(ServletContextAttributeEvent scab) {
        System.out.println("你向application中添加一个名为" + scab.getName() +
                        "，值为：" + scab.getValue() + "的属性");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scab) {
        System.out.println("你已经移除了" + scab.getName() + "属性");
        System.out.println(scab.getName() + "=" + scab.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scab) {
        System.out.println(scab.getName() + "=" + scab.getValue() +
                ", " + scab.getServletContext().getAttribute(scab.getName())
        );
    }
}
