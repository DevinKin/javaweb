package cn.itcast.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * SimpleTagSupport它实现了SimpleTag接口.
 * 他已经把所有的tomcat传递的数据都保存起来了！而且还提供了get方法供子类调用
 * @author king
 */
public class MyTag2 extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        this.getJspContext().getOut().print("再Hello一次");
    }
}
