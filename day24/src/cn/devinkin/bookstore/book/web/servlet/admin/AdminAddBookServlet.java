package cn.devinkin.bookstore.book.web.servlet.admin;

import cn.devinkin.bookstore.book.domain.Book;
import cn.devinkin.bookstore.book.service.BookService;
import cn.devinkin.bookstore.category.domain.Category;
import cn.devinkin.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 使用上传三步，把表单数据封装到Book对象中
         *  1. 上传三步
         */
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory(20 * 1024, new File("/tmp/temp/"));
        //得到解析器
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置单个文件大小为20KB
        sfu.setFileSizeMax(20 * 1024);
        //使用解析器去解析request对象，得到List<FileItem>
        try {
            List<FileItem> fileItemList = sfu.parseRequest(request);
            /**
             * 把FileItemList的数据封装到Book对象中
             *  1. 把所有的普通表单字段数据先封装到map中
             *  2. 再把map中的数据封装到Book对象中
             */
            Map<String, String> map = new HashMap<String,String>();
            for (FileItem fileItem : fileItemList) {
                if (fileItem.isFormField())
                    map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
            }
            Book book = CommonUtils.toBean(map, Book.class);
            //为Book指定bid
            book.setBid(CommonUtils.uuid());
            /**
             * 需要把map中的cid封装到Category对象中，再把Category对象赋给Book
             */
            Category category = CommonUtils.toBean(map, Category.class);
            //添加cname到Category
            String categoryName = categoryService.getCategoryName(category.getCid());
            category.setCname(categoryName);
            book.setCategory(category);

            /**
             * 2. 保存上传的文件
             *  1. 保存的目录
             *  2. 保存文件的名称
             */
            //得到保存的目录
            String savePath = this.getServletContext().getRealPath("/book_img");
            //得到文件名称：给原来文件名称添加uuid_前缀！避免文件名冲突
            String fileName = CommonUtils.uuid() + "_" + fileItemList.get(1).getName();

            /**
             * 校验文件的扩展名
             */
            if (!fileName.toLowerCase().endsWith("jpg")) {
                request.setAttribute("msg", "您上传的图片不是JPG扩展名！");
                request.setAttribute("categoryList", categoryService.findAll());
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").
                        forward(request,response);
                return;
            }

            //使用目录和文件名称创建目标文件
            File destFile = new File(savePath, fileName);
            //保存上传文件到目标文件位置
            fileItemList.get(1).write(destFile);

            /**
             * 3. 设置Book对象的image，即把图片的路径设置给Book的image
             */
            int pos = savePath.lastIndexOf("/");
            String spb = savePath.substring(pos+1) + "/";
            book.setImage(spb + fileName);

            /**
             * 4. 使用BookService完成保存
             */
            book.setDel(false);
            bookService.add(book);

            /**
             * 校验图片的尺寸
             */
            Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
            if (image.getWidth(null) > 200 || image.getHeight(null) > 200) {
                //删除这个文件
                destFile.delete();
                request.setAttribute("msg", "您上传的图片尺寸超出了 200 * 200!");
                request.setAttribute("categoryList", categoryService.findAll());
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").
                        forward(request,response);
                return;
            }


            /**
             * 5. 返回到图书列表
             */
            request.getRequestDispatcher("/AdminBookServlet?method=findAll").
                    forward(request, response);
        } catch (Exception e) {
            if (e instanceof FileUploadBase.FileSizeLimitExceededException)
                request.setAttribute("msg", "您上传的图片超过20KB。");
            request.setAttribute("categoryList", categoryService.findAll());
            request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").
                    forward(request,response);
        }
    }


}
