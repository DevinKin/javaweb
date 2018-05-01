package cn.devinkin.bookstore.cart.domain;

import cn.devinkin.bookstore.book.domain.Book;

import java.math.BigDecimal;

/**
 * 商品条目类
 */
public class CartItem {
    private Book book;
    private int count;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 小计方法，没有对应的成员
     * @return
     * 处理了二进制运算误差问题
     */
    public double getSubtotal() {
        BigDecimal d1 = new BigDecimal(book.getPrice() + "");
        BigDecimal d2 = new BigDecimal(count + "");
        return d1.multiply(d2).doubleValue();
    }
}
