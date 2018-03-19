package cn.devinkin.cstm.dao;

import cn.devinkin.cstm.domain.Customer;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
    public List<Customer> findAll() {
        try {
            String sql = "SELECT * FROM t_customer";
            return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
