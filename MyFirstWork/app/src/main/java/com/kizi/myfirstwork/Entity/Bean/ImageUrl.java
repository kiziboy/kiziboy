package com.kizi.myfirstwork.Entity.Bean;

/**
 * Created by ASUS on 2016/8/11.
 */
public class ImageUrl {
    private String image_id;

    private String item_id;

    private String position;

    private String disabled;

    private String label;

    private String file;

    private String url;

    public void setImage_id(String image_id){
        this.image_id = image_id;
    }
    public String getImage_id(){
        return this.image_id;
    }
    public void setItem_id(String item_id){
        this.item_id = item_id;
    }
    public String getItem_id(){
        return this.item_id;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public String getPosition(){
        return this.position;
    }
    public void setDisabled(String disabled){
        this.disabled = disabled;
    }
    public String getDisabled(){
        return this.disabled;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }
    public void setFile(String file){
        this.file = file;
    }
    public String getFile(){
        return this.file;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
