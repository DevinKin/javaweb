package cn.itcast.customer.domain;

import java.util.List;

public class PageBean<T> {
	private int pc;// 当前页码
	private int tr;// 总记录数
	private int ps;// 每页记录数
	private String url;// 基本url
	private List<T> beanList;// 当前页记录
	
	public PageBean(int pc, int tr, int ps) {
		this.pc = pc;
		this.tr = tr;
		this.ps = ps;
	}
	
	// 返回当前页首行的下标
	public int getIndex() {
		return (pc - 1) * ps;
	}
	
	// 返回总页数
	public int getTp() {
		int tp = tr / ps;
		if(tr % ps != 0) tp++;
		return tp;
	}

	public PageBean() {
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
