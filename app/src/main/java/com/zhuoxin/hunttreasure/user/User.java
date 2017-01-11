package com.zhuoxin.hunttreasure.user;

/**
 * Created by Administrator on 2017/1/10.
 */

public class User {

    public User(String name, String password) {
        this.UserName = name;
        Password = password;
    }

    /**
     * UserName : qjd
     * Password : 654321
     *
     * GsonFormat
     */

    private String UserName;

    private String Password;

    public String getName() {
        return UserName;
    }

    public void setName(String name) {
        this.UserName = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
