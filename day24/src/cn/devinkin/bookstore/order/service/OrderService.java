package cn.devinkin.bookstore.order.service;

import cn.devinkin.bookstore.order.dao.OrderDao;
import cn.devinkin.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public void pay(String oid) {
        /**
         * 1. 获取订单的状态
         *  1. 如果状态为1，那么执行下面代码
         *  2. 如果状态不为1，那么本方法什么都不做
         */
        int state = orderDao.getStateByOid(oid);
        if (state == 1) {
            //修改订单状态为2
            orderDao.updateState(oid, 2);
            /**
             * 加积分操作...
             */
        }
    }

    /**
     * 添加订单
     * 需要处理事务
     * @param order
     */
    public void add(Order order) {
        try {
            //开启事务
            JdbcUtils.beginTransaction();
            //插入订单
            orderDao.addOrder(order);
            //插入订单中的所有条目
            orderDao.addOrderItemList(order.getOrderItemList());
            //提交事务
            JdbcUtils.commitTransaction();
        } catch (Exception e) {
            //回滚事务
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取订单列表
     * @param uid
     * @return
     */
    public List<Order> myOrders(String uid) {
        return orderDao.findByUid(uid);
    }

    /**
     * 加载订单
     * @param oid
     * @return
     */
    public Order load(String oid) {
        return orderDao.load(oid);
    }

    /**
     * 确认收货
     * @param oid
     * @throws OrderException
     */
    public void confirm(String oid) throws OrderException {
        /**
         * 1. 通过oid校验订单状态，如果不是3，抛出异常
         */
        int state = orderDao.getStateByOid(oid);
        if (state != 3) throw new OrderException("订单确认失败，你不是什么好东西!");

        /**
         * 2. 修改订单状态为4，表示交易成功
         */
        orderDao.updateState(oid,4);
    }

    public void addAddressByOid(String oid, String address) {
        orderDao.addAddressByOid(oid, address);
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public List<Order> findOrderByState(int state) {
        return orderDao.findOrderByState(state);
    }

    public int getOrderState(String oid) {
        return orderDao.getStateByOid(oid);
    }

    public void updateState(String oid, int state) {
        orderDao.updateState(oid, state);
    }
}
