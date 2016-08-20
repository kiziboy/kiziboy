package com.kizi.myfirstwork.Entity.Bean;

/**
 * Created by ASUS on 2016/8/17.
 */
public class KeyWordAdress implements SuperBean {
    private String id;

    private String name;

    private String district;

    private String adcode;

    private String location;

    private String address;

    private String typecode;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public String getDistrict(){
        return this.district;
    }
    public void setAdcode(String adcode){
        this.adcode = adcode;
    }
    public String getAdcode(){
        return this.adcode;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setTypecode(String typecode){
        this.typecode = typecode;
    }
    public String getTypecode(){
        return this.typecode;
    }
}
