package cn.devinkin.cstm.domain;

import java.util.List;

public class PageBean<T> {
    //当前页码，page code
    private int pc;
    //总页数，total page
    //private int tp;

    //总记录数，total record
    private int tr;
    //每页记录数，page size
    private int ps;
    //当前页的记录
    private List<T> beanList;
    //它就是url后的条件
    private String url;

    public int getPc() {
        return pc;
    }

    public int getTp() {
        int tp = tr/ps;
        return tr % ps == 0?tp:tp+1;
    }

    public int getTr() {
        return tr;
    }

    public int getPs() {
        return ps;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public String getUrl() {
        return url;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
