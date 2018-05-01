package cn.devinkin.bookstore.category.dao;

import cn.devinkin.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查询所有分类
     * @return
     */
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        try {
            return qr.query(sql, new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 添加分类
     * @param category
     */
    public void add(Category category) {
        String sql = "INSERT INTO category VALUES(?,?)";

        try {
            Object[] params = {category.getCid(), category.getCname()};
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除分类
     * @param cid
     */
    public void delete(String cid) {
        String sql = "DELETE FROM category WHERE cid=?";
        try {
            qr.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 加载分类
     * @param cid
     * @return
     */
    public Category load(String cid) {
        String sql = "SELECT * FROM category WHERE cid=?";
        try {
            return qr.query(sql, new BeanHandler<Category>(Category.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改分类
     * @param category
     */
    public void edit(Category category) {
        String sql = "UPDATE category SET cname=? WHERE cid=?";
        Object[] params = {category.getCname(), category.getCid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCategoryName(String cid) {
        String sql = "SELECT cname FROM category WHERE cid=?";
        try {
            String cname = (String)qr.query(sql, new ScalarHandler(), cid);
            return cname;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
