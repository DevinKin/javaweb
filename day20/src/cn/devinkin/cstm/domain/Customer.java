package cn.devinkin.cstm.domain;

/**
 * 领域对象
 * 与表单和数据库表对应
 * @author king
 */
public class Customer {
    /**
     * 对应数据库表
     cid		CHAR(32) PRIMARY KEY,
     cname		VARCHAR(40) NOT NULL,
     gender		VARCHAR(6) NOT NULL,
     birthday	DATE,
     cellphone	VARCHAR(15) NOT NULL,
     email		VARCHAR(40),
     description	VARCHAR(500)
     */

    private String cid;
    private String cname;
    private String gender;
    private String birthday;
    private String cellphone;
    private String email;
    private String description;

    public String getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
