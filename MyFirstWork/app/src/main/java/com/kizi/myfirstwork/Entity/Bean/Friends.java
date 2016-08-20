package com.kizi.myfirstwork.Entity.Bean;

/**
 * Created by ASUS on 2016/8/19.
 */
public class Friends {
    private String entity_id;

    private String firstname;

    private String mobile;

    private String address;

    public void setEntity_id(String entity_id){
        this.entity_id = entity_id;
    }
    public String getEntity_id(){
        return this.entity_id;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getFirstname(){
        return this.firstname;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
}
