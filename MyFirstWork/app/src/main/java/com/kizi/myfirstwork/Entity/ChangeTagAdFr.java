package com.kizi.myfirstwork.Entity;

import java.io.Serializable;

/**
 * Created by ASUS on 2016/8/16.
 */
public class ChangeTagAdFr implements Serializable {
    private int type;
    private String content;

    public ChangeTagAdFr(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
