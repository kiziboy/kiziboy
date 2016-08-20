package com.kizi.myfirstwork.Entity.Bean;

/**
 * Created by ASUS on 2016/8/17.
 */
public class Adress_gps implements SuperBean{
    private String id;

    private String name;

    private String type;

    private String direction;

    private String distance;

    private String location;

    private String address;

    private String poiweight;


    private String district;
    private String adcode;
    private String typecode;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

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
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return this.direction;
    }
    public void setDistance(String distance){
        this.distance = distance;
    }
    public String getDistance(){
        return this.distance;
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
    public void setPoiweight(String poiweight){
        this.poiweight = poiweight;
    }
    public String getPoiweight(){
        return this.poiweight;
    }
}
