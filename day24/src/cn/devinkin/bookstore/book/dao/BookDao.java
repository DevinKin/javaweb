package cn.devinkin.bookstore.book.dao;

import cn.devinkin.bookstore.book.domain.Book;
import cn.devinkin.bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<Book> findAll() {
        String sql = "SELECT * FROM book WHERE del=false";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByCategory(String cid) {
        String sql = "SELECT * FROM book WHERE cid=?";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Book load(String bid) {
        String sql = "SELECT * FROM book WHERE bid=?";
        try {
            return qr.query(sql, new BeanHandler<Book>(Book.class), bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  查询指定分类下的图书本数
     * @return
     */
    public int getCountByCid(String cid) {
        String sql = "SELECT COUNT(*) FROM book WHERE cid=?";
        try {
            Number cnt = (Number) qr.query(sql, new ScalarHandler(),cid);
            return cnt.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过bid加载图书
     * @param bid
     * @return
     */
    public Book findByBid(String bid) {
        String sql = "SELECT * FROM category c,book b WHERE bid=? and b.cid= c.cid";
        try {
            Map<String,Object> map = qr.query(sql, new MapHandler(), bid);
            Category category = CommonUtils.toBean(map, Category.class);
            Book book = CommonUtils.toBean(map, Book.class);
            book.setCategory(category);
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加图书
     * @param book
     */
    public void add(Book book) {
        String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?)";
        Object[] params = {book.getBid(), book.getBname(), book.getPrice(),
            book.getAuthor(), book.getImage(), book.getCategory().getCid(), book.isDel()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除图书，但是不是真的删除数据库的数据
     * @param bid
     */
    public void delete(String bid) {
        String sql = "UPDATE book SET del=true WHERE bid=?";
        try {
            qr.update(sql,bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Book book) {
        String sql = "UPDATE book SET bid=?,bname=?,price=?,author=?,image=?,cid=?,del=? WHERE bid=?";
        Object[] params = {book.getBid(), book.getBname(), book.getPrice(),
            book.getAuthor(), book.getImage(), book.getCategory().getCid(), book.isDel(), book.getBid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
