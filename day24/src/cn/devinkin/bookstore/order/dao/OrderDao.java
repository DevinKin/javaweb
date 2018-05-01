package cn.devinkin.bookstore.order.dao;

import cn.devinkin.bookstore.book.domain.Book;
import cn.devinkin.bookstore.order.domain.Order;
import cn.devinkin.bookstore.order.domain.OrderItem;
import cn.devinkin.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 添加订单
     * @param order
     */
    public void addOrder(Order order) {
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?)";
        /**
         * 处理util的Date转换为sql的TimeStamp
         */
        Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
        Object[] params = {order.getOid(), timestamp, order.getTotal(),
                order.getState(), order.getOwner().getUid(), order.getAddress()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加订单条目
     * @param orderItemList
     */
    public void addOrderItemList(List<OrderItem> orderItemList) {
        /**
         * QueryRunner类的batch(String sql, Object[][] params);
         * 1. 其中params是多个一维数组！
         * 2. 每个一维数组都与sql在一起执行一次，多个一维数组就是就执行多次
         */
        String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
        /**
         * 把orderItemList转换成二维数组
         *  1. 把一个OrderItem对象转换成一个一维数组
         */
        Object[][] params = new Object[orderItemList.size()][];
        //循环遍历orderItemList，使用每个orderItem对象为params中每个一维数组赋值
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem item = orderItemList.get(i);
            params[i] = new Object[] {item.getIid(), item.getCount(),item.getSubtotal(),
                    item.getOrder().getOid(), item.getBook().getBid()};
        }
        try {
            qr.batch(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> findByUid(String uid) {
        /**
         * 1. 通过uid查询当前用户的所有List<Order>
         * 2. 循环遍历每个Order，为其加载它的所有OrderItem
         */
        String sql = "SELECT * FROM orders WHERE uid=?";
        try {
            /**
             * 1. 得到当前用户的所有订单
             */
            List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);

            /**
             * 2. 循环遍历每个Order，为其加载它自己所有的订单条目
             */
            for (Order order : orderList) {
                //为order对象添加它的所有订单条目
                loadOrderItems(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载指定的订单所有的订单条目
     * @param order
     */
    private void loadOrderItems(Order order) {
        /**
         * 查询两张表，orderitem、book
         */
        String sql = "SELECT * FROM orderitem i, book b WHERE i.bid = b.bid AND oid=?";
        try {
            /**
             * 因为一行结果集对应的不再是一个javabean，所以不能再使用beanListHandler，而是使用MapListHandler
             * mapList是多个map，每个map对应一行结果集。
             * 我们需要使用一个Map生成两个对象：OrderItem、Book，然后建立两者的关系(把Book设置给Item)
             */
            List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
            /**
             * 循环遍历每个Map，使用Map生成两个对象，然后建立关系，最终结果是一个OrderItem，把OrderItem保存起来
             */
            List<OrderItem> orderItemList = toOrderItemList(mapList);
            order.setOrderItemList(orderItemList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把mapList中每个Map转换成两个对象，并建立关系
     * @param mapList
     * @return
     */
    private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for (Map<String, Object> map : mapList) {
            OrderItem item = toOrderItem(map);
            orderItemList.add(item);
        }
        return orderItemList;
    }

    /**
     * 把一个map转换成一个OrderItem对象
     * @param map
     * @return
     */
    private OrderItem toOrderItem(Map<String, Object> map) {
        OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        orderItem.setBook(book);
        return orderItem;
    }

    /**
     * 加载订单
     * @param oid
     * @return
     */
    public Order load(String oid) {
        String sql = "SELECT * FROM orders WHERE oid=?";
        try {
            Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
            loadOrderItems(order);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过oid查询订单状态
     * @param oid
     * @return
     */
    public int getStateByOid(String oid) {
        String sql = "SELECT state FROM orders WHERE oid=?";
        try {
            return (Integer)qr.query(sql, new ScalarHandler(), oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 更新订单状态
     * @param oid
     * @param state
     */
    public void updateState(String oid, int state) {
        String sql = "UPDATE orders SET state=? WHERE oid=?";
        try {
            qr.update(sql, state,oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过订单id添加地址
     * @param oid
     * @param address
     */
    public void addAddressByOid(String oid, String address) {
        String sql = "UPDATE orders SET address=? WHERE oid=?";
        Object[] params = {address, oid};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找所有订单
     * @return
     */
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        List<Order> orderList = new ArrayList<Order>();
        try {
            orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Order order : orderList) {
            loadOrderItems(order);
            loadUser(order);
        }
        return orderList;
    }

    /**
     * 加载User对象到订单中
     * @param order
     */
    private void loadUser(Order order) {
        String sql = "SELECT u.* FROM tb_user u, orders o WHERE u.uid=o.uid AND o.oid=?";
        try {
            User user = qr.query(sql, new BeanHandler<User>(User.class), order.getOid());
            order.setOwner(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> findOrderByState(int state) {
        String sql = "SELECT * FROM orders WHERE state=?";
        List<Order> orderList = new ArrayList<Order>();
        try {
            orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Order order : orderList) {
            loadOrderItems(order);
            loadUser(order);
        }
        return orderList;
    }
}
