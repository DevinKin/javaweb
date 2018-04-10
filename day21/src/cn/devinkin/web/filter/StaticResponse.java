package cn.devinkin.web.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class StaticResponse extends HttpServletResponseWrapper{
    private PrintWriter pw;

    /**
     * 调包response
     * @param response
     * @param path html文件路径！
     */
    public StaticResponse(HttpServletResponse response, String path) throws FileNotFoundException, UnsupportedEncodingException {
        super(response);

        //创建一个与html文件绑定在一起的流对象
        pw = new PrintWriter(path, "UTF-8");
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        //返回一个与html绑定在一起的printWriter对象
        // jsp会使用它进行输出，这样的数据都输出到html文件中
        return pw;
    }
}
