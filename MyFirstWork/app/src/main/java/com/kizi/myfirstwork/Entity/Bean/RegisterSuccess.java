package com.kizi.myfirstwork.Entity.Bean;

/**
 * Created by ASUS on 2016/8/18.
 */
public class RegisterSuccess {

    private int status;

    private String message;

    private String id;

    private String token;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }

}
