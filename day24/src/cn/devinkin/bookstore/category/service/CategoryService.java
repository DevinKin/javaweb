package cn.devinkin.bookstore.category.service;

import cn.devinkin.bookstore.book.dao.BookDao;
import cn.devinkin.bookstore.category.admin.web.servlet.CategoryException;
import cn.devinkin.bookstore.category.dao.CategoryDao;
import cn.devinkin.bookstore.category.domain.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    private BookDao bookDao = new BookDao();

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    /**
     * 添加分类
     * @param category
     */
    public void add(Category category) {
        categoryDao.add(category);
    }

    /**
     * 删除分类
     * @param cid
     */
    public void delete(String cid) throws CategoryException {
        /**
         * 1. 获取该分类下图书的本数
         */
        int count = bookDao.getCountByCid(cid);
        //如果该分类下存在图书，不让删除，我们抛出异常
        if (count > 0) throw new CategoryException("该分类下还有图书，不能删除");
        //该分类没有图书，删除该分类
        categoryDao.delete(cid);
    }

    /**
     * 加载分类
     * @param cid
     * @return
     */
    public Category load(String cid) {
        return categoryDao.load(cid);
    }

    /**
     * 修改分类
     * @param category
     */
    public void edit(Category category) {
        categoryDao.edit(category);
    }

    public String getCategoryName(String cid) {
        return categoryDao.getCategoryName(cid);
    }
}
