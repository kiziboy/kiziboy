package com.kizi.myfirstwork.Entity.Bean;

import java.util.List;

/**
 * Created by ASUS on 2016/8/19.
 */
public class MainPage {

    /**
     * item_id : 70
     * created_at : 2016-08-17 01:32:20
     * customer_id : 10
     * location : 116.327176,39.952774
     * province : 北京市
     * city : Array
     * address :
     * content : j健健康康
     * topic_id : 0
     * is_help : 0
     * status : 0
     * type : 1
     * like_count : 2
     * review_count : 1
     * is_recommend : 0
     * cover_image : /i/m/image0_41.png
     * is_china : 1
     * is_sea : 0
     * tags : 壮阔
     * image_count : 6
     * flag_label :
     * cover_image_url : http://seesea.demo.evebit.cn/media/tidings/image/i/m/image0_41.png
     * published_at : 2016-08-17
     * customer_name : DULU
     * customer_portrait :
     * customer_portrait_url :
     * images : [{"image_id":"53","item_id":"70","position":"1","disabled":"0","label":"","file":"/i/m/image0_41.png","url":"http://seesea.demo.evebit.cn/media/tidings/image/i/m/image0_41.png"},{"image_id":"54","item_id":"70","position":"2","disabled":"0","label":"","file":"/i/m/image1_16.png","url":"http://seesea.demo.evebit.cn/media/tidings/image/i/m/image1_16.png"},{"image_id":"55","item_id":"70","position":"3","disabled":"0","label":"","file":"/i/m/image2_8.png","url":"http://seesea.demo.evebit.cn/media/tidings/image/i/m/image2_8.png"},{"image_id":"56","item_id":"70","position":"4","disabled":"0","label":"","file":"/i/m/image3_6.png","url":"http://seesea.demo.evebit.cn/media/tidings/image/i/m/image3_6.png"},{"image_id":"57","item_id":"70","position":"5","disabled":"0","label":"","file":"/i/m/image4_2.png","url":"http://seesea.demo.evebit.cn/media/tidings/image/i/m/image4_2.png"},{"image_id":"58","item_id":"70","position":"6","disabled":"0","label":"","file":"/i/m/image5_2.png","url":"http://seesea.demo.evebit.cn/media/tidings/image/i/m/image5_2.png"}]
     */
    private String item_id;
    private String created_at;
    private String customer_id;
    private String location;
    private String province;
    private String city;
    private String address;
    private String content;
    private String topic_id;
    private String is_help;
    private String status;
    private String type;
    private String like_count;
    private String review_count;
    private String is_recommend;
    private String cover_image;
    private String is_china;
    private String is_sea;
    private String tags;
    private String image_count;
    private String flag_label;
    private String cover_image_url;
    private String published_at;
    private String customer_name;
    private String customer_portrait;
    private String customer_portrait_url;
    /**
     * image_id : 53
     * item_id : 70
     * position : 1
     * disabled : 0
     * label :
     * file : /i/m/image0_41.png
     * url : http://seesea.demo.evebit.cn/media/tidings/image/i/m/image0_41.png
     */

    private List<ImagesBean> images;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getIs_help() {
        return is_help;
    }

    public void setIs_help(String is_help) {
        this.is_help = is_help;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getReview_count() {
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public String getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getIs_china() {
        return is_china;
    }

    public void setIs_china(String is_china) {
        this.is_china = is_china;
    }

    public String getIs_sea() {
        return is_sea;
    }

    public void setIs_sea(String is_sea) {
        this.is_sea = is_sea;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage_count() {
        return image_count;
    }

    public void setImage_count(String image_count) {
        this.image_count = image_count;
    }

    public String getFlag_label() {
        return flag_label;
    }

    public void setFlag_label(String flag_label) {
        this.flag_label = flag_label;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_portrait() {
        return customer_portrait;
    }

    public void setCustomer_portrait(String customer_portrait) {
        this.customer_portrait = customer_portrait;
    }

    public String getCustomer_portrait_url() {
        return customer_portrait_url;
    }

    public void setCustomer_portrait_url(String customer_portrait_url) {
        this.customer_portrait_url = customer_portrait_url;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        private String image_id;
        private String item_id;
        private String position;
        private String disabled;
        private String label;
        private String file;
        private String url;

        public String getImage_id() {
            return image_id;
        }

        public void setImage_id(String image_id) {
            this.image_id = image_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDisabled() {
            return disabled;
        }

        public void setDisabled(String disabled) {
            this.disabled = disabled;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
