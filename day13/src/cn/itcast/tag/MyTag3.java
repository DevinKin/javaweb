package cn.itcast.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;

public class MyTag3 extends SimpleTagSupport{
    @Override
    public void doTag() throws JspException, IOException {
        //获取当前jsp页面的输出流
        Writer out = this.getJspContext().getOut();
        //执行标签体的内容，把结果写到指定的流中,即页面上。
        out.write("*************<br/>");
        this.getJspBody().invoke(out);
        out.write("<br/>*************");
    }
}
