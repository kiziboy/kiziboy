package com.kizi.myfirstwork.Entity.Bean;

import java.io.Serializable;

/**
 * Created by ASUS on 2016/8/18.
 */
public class User implements Serializable {
    private String customNumber;
    private String password;
    private String code;
    private int type;//0:修改密码 1：注册

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCustomNumber() {
        return customNumber;
    }

    public void setCustomNumber(String customNumber) {
        this.customNumber = customNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
