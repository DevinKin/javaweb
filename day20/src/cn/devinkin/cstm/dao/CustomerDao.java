package cn.devinkin.cstm.dao;

import cn.devinkin.cstm.domain.Customer;
import cn.devinkin.cstm.domain.PageBean;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 持久层
 * @author king
 */
public class CustomerDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 添加客户
     * @param c
     */
    public void add(Customer c) {
        try {
            String sql = "INSERT INTO t_customer VALUES(?,?,?,?,?,?,?)";
            Object[] params = {c.getCid(), c.getCname(), c.getGender(),
                    c.getBirthday(), c.getCellphone(), c.getEmail(),
                    c.getDescription()};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有
     * @return
     */
    /*
    public List<Customer> findAll() {
        try {
            String sql = "SELECT * FROM t_customer";
            return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     */

    public PageBean<Customer> findAll(int pc, int ps) {
        /**
         * 1. 创建PageBean对象
         * 2. 设置pb的pc和ps
         * 3. 得到tr，设置给pb
         * 4. 得到BeanList，设置给pb
         * 5. 返回pb
         */
        PageBean<Customer> pb = new PageBean<Customer>();
        pb.setPc(pc);
        pb.setPs(ps);
        /**
         * 得到tr
         */
        String sql = "SELECT COUNT(*) FROM t_customer;";
        try {
            Number num = (Number)qr.query(sql, new ScalarHandler());
            //得到tr
            int tr = num.intValue();
            pb.setTr(tr);

            /**
             * 得到BeanList
             */
            sql = "SELECT * from t_customer ORDER BY cname limit ?,? ";
            List<Customer> beanList = qr.query(sql,
                    new BeanListHandler<Customer>(Customer.class),
                    (pc-1)*ps,ps);
            pb.setBeanList(beanList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pb;
    }

    /**
     * 加载客户
     * @param cid
     */
    public Customer load(String cid) {
        try {
            String sql = "SELECT * FROM t_customer WHERE cid=?";
            return qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Customer c) {
        try {
            String sql = "UPDATE t_customer SET cname=?, gender=?, birthday=?, cellphone=?, email=?,description=? WHERE cid=?";
            Object[] params = {c.getCname(), c.getGender(), c.getBirthday(),
                    c.getCellphone(), c.getEmail(), c.getDescription(), c.getCid()};
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String cid) {
        try {
            String sql = "DELETE FROM t_customer WHERE cid=?";
            Object[] params = {cid};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 /*
    public List<Customer> query(Customer c) {
        String cname = c.getCname();
        String gender = c.getGender();
        String cellphone = c.getCellphone();
        String email = c.getEmail();
        List<Object> params = new ArrayList<Object>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1 ");
            if (cname != null && !cname.trim().isEmpty()) {
                sql.append("AND cname=?");
                params.add(cname);
            }

            if (gender != null && !gender.trim().isEmpty()) {
                sql.append("AND gender=?");
                params.add(gender);
            }

            if (cellphone != null && !cellphone.trim().isEmpty()) {
                sql.append("AND cellphone=?");
                params.add(cellphone);
            }

            if (email != null && !email.trim().isEmpty()) {
                sql.append("AND email=?");
                params.add(email);
            }
            System.out.println(sql);
            System.out.println(c);

            return qr.query(sql.toString(),new BeanListHandler<Customer>(Customer.class),
                    params.toArray());
        } catch (SQLException e)  {
            throw new RuntimeException(e);
        }
    }
    */

    public PageBean<Customer> query(Customer criteria, int pc, int ps) {
        List<Object> params = new ArrayList<Object>();
        try {
            /**
             * 1. 创建PageBean对象
             * 2. 设置已有属性
             * 3. 得到tr
             * 4. 得到BeanList
             */
            PageBean<Customer> pb = new PageBean<Customer>();
            pb.setPc(pc);
            pb.setPs(ps);

            /**
             * 得到tr
             */
            StringBuilder cntSql = new StringBuilder("SELECT COUNT(*) FROM t_customer ");
            StringBuilder whereSql = new StringBuilder("WHERE 1=1 ");

            String cname = criteria.getCname();
            if (cname != null && !cname.trim().isEmpty()) {
                whereSql.append("AND cname LIKE ? ");
                params.add("%" + cname + "%");
            }

            String gender = criteria.getGender();
            if (gender != null && !gender.trim().isEmpty()) {
                whereSql.append("AND gender=? ");
                params.add(gender);
            }

            String cellphone = criteria.getCellphone();
            if (cellphone != null && !cellphone.trim().isEmpty()) {
                whereSql.append("AND cellphone LIKE ? ");
                params.add("%" + cellphone + "%");
            }

            String email = criteria.getEmail();
            if (email != null && !email.trim().isEmpty()) {
                whereSql.append("AND email LIKE ? ");
                params.add("%" + email + "%");
            }

            Number num = (Number)qr.query(cntSql.append(whereSql).toString(),
                    new ScalarHandler(),
                    params.toArray());
            int tr = num.intValue();
            pb.setTr(tr);

            /**
             * 得到beanList，并设置beanList
             */
            StringBuilder bSql = new StringBuilder("SELECT * FROM t_customer ");
            // 我们查询BeanList这一步还需要给出limit子句
            StringBuilder limitSql = new StringBuilder("LIMIT ?,?");
            //params中需要给出limit后两个问号对应的值
            System.out.println(pc);
            params.add((pc-1)*ps);
            params.add(ps);
            //执行之
            List<Customer> beanList = qr.query(bSql.append(whereSql).append(limitSql).toString(),
                    new BeanListHandler<Customer>(Customer.class),
                    params.toArray());
            pb.setBeanList(beanList);

            return pb;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
