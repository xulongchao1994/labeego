package com.hechuang.labeego.bean;

/**
 * Created by Administrator on 2017/2/25.
 */

public class GoodsViewGroupItem {
    private String key;
    private String value;
//    private String price;
//    private String kucun;
//    private String integral;
//    private String ProId;

    public GoodsViewGroupItem(String key, String value) {
        this.key = key;
        this.value = value;
//        this.price = price;
//        this.kucun = kucun;
//        this.integral = integral;
//        ProId = proId;
    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public void setKucun(String kucun) {
//        this.kucun = kucun;
//    }
//
//    public void setIntegral(String integral) {
//        this.integral = integral;
//    }
//
//    public void setProId(String proId) {
//        ProId = proId;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public String getKucun() {
//        return kucun;
//    }
//
//    public String getIntegral() {
//        return integral;
//    }
//
//    public String getProId() {
//        return ProId;
//    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GoodsViewGroupItem{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
