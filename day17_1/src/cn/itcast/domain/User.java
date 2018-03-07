package cn.itcast.domain;

import cn.itcast.vcode.utils.VerifyCode;

/**
 * 实体类
 * author Devinkn
 */
public class User {
    private String username;
    private String password;
    private int age;
    private String gender;
    private String verifyCode;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password
                + ", verifyCode=" + verifyCode + "]";
    }


}
