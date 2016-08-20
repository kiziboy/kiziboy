package com.kizi.myfirstwork.Entity;

import java.io.Serializable;

/**
 * Created by ASUS on 2016/8/15.
 */
public class IntoPublishActivity implements Serializable {
    private String topic_id;

    public IntoPublishActivity(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }
}
