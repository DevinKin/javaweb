package cn.itcast.customer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.customer.domain.Customer;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * Customer持久层
 * 
 * @author qdmmy6
 * 
 */
public class CustomerDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 添加客户
	 * 
	 * @param c
	 */
	public void add(Customer c) {
		String sql = "insert into t_customer values(?,?,?,?,?,?,?)";
		Object[] params = { c.getCid(), c.getCname(), c.getGender(),
				c.getBirthday(), c.getCellphone(), c.getEmail(),
				c.getDescription() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改客户
	 * 
	 * @param c
	 */
	public void update(Customer c) {
		String sql = "update t_customer set cname=?,gender=?,birthday=?,cellphone=?,email=?,description=? where cid=?";
		Object[] params = {c.getCname(), c.getGender(),
				c.getBirthday(), c.getCellphone(), c.getEmail(),
				c.getDescription(),c.getCid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除客户
	 * 
	 * @param cid
	 */
	public void delete(String cid) {
		String sql = "delete from t_customer where cid=?";
		Object[] params = {cid};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载客户
	 * 
	 * @param cid
	 * @return
	 */
	public Customer load(String cid) {
		String sql = "select * from t_customer where cid=?";
		Object[] params = {cid};
		try {
			return qr.query(sql, new BeanHandler<Customer>(Customer.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 所有客户记录数
	 * @return
	 */
	public int countFindAll() {
		String sql = "select count(*) from t_customer";
		try {
			Long cnt = (Long)qr.query(sql, new ScalarHandler());
			return cnt.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 所有客户分页
	 * @param index
	 * @param len
	 * @return
	 */
	public List<Customer> findAllByPager(int index, int len) {
		String sql = "select * from t_customer limit ?,?";
		try {
			return qr.query(sql, new BeanListHandler<Customer>(Customer.class), index, len);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 组合查询记录数
	 * @param criteria
	 * @return
	 */
	public int countQuery(Customer criteria) {
		StringBuilder sql = new StringBuilder("select count(*) from t_customer where 1=1");
		List<Object> params = new ArrayList<Object>();
		String cname = criteria.getCname();
		if(cname != null && !cname.trim().isEmpty()) {
			sql.append(" and cname like ?");
			params.add("%" + cname + "%");
		}
		
		String gender = criteria.getGender();
		if(gender != null && !gender.trim().isEmpty()) {
			sql.append(" and gender=?");
			params.add(gender);
		}
		
		String cellphone = criteria.getCellphone();
		if(cellphone != null && !cellphone.trim().isEmpty()) {
			sql.append(" and cellphone like ?");
			params.add("%" + cellphone + "%");
		}
		
		String email = criteria.getEmail();
		if(email != null && !email.trim().isEmpty()) {
			sql.append(" and email like ?");
			params.add("%" + email + "%");
		}
		
		try {
			Long cnt = (Long)qr.query(sql.toString(), new ScalarHandler(), params.toArray());
			return cnt.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
 	}

	/**
	 * 组合查询分页
	 * 
	 * @param criteria
	 * @return
	 */
	public List<Customer> queryByPager(Customer criteria, int index, int len) {
		StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
		List<Object> params = new ArrayList<Object>();
		String cname = criteria.getCname();
		if(cname != null && !cname.trim().isEmpty()) {
			sql.append(" and cname like ?");
			params.add("%" + cname + "%");
		}
		
		String gender = criteria.getGender();
		if(gender != null && !gender.trim().isEmpty()) {
			sql.append(" and gender=?");
			params.add(gender);
		}
		
		String cellphone = criteria.getCellphone();
		if(cellphone != null && !cellphone.trim().isEmpty()) {
			sql.append(" and cellphone like ?");
			params.add("%" + cellphone + "%");
		}
		
		String email = criteria.getEmail();
		if(email != null && !email.trim().isEmpty()) {
			sql.append(" and email like ?");
			params.add("%" + email + "%");
		}
		
		sql.append(" limit ?,?");
		params.add(index);
		params.add(len);
		
		try {
			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), params.toArray());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
 	}
}
