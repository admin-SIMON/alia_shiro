package com.alia.shiro.domain;

/**
 * 用户登录输入的参数类
 *
 * @author Simon
 */
public class UserQuery {
    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
