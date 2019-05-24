package com.hechuang.labeego.bean;

public class AllorderBean {
    private String itme;
    private String ordernumber;
    private String ordertype;
    private String icon;
    private String goodsname;
    private String goodstype;
    private String goodsprice;
    private String goodszeng;
    private String godsnumber;
    private String sorename;
    private String ordernameber;
    private String orderprice;
    private String orderzeng;

    public AllorderBean(String itme, String ordernumber, String ordertype, String icon, String goodsname, String goodstype,
                        String goodsprice, String goodszeng, String godsnumber, String sorename, String ordernameber, String orderprice, String orderzeng) {
        this.itme = itme;
        this.ordernumber = ordernumber;
        this.ordertype = ordertype;
        this.icon = icon;
        this.goodsname = goodsname;
        this.goodstype = goodstype;
        this.goodsprice = goodsprice;
        this.goodszeng = goodszeng;
        this.godsnumber = godsnumber;
        this.sorename = sorename;
        this.ordernameber = ordernameber;
        this.orderprice = orderprice;
        this.orderzeng = orderzeng;
    }

    public void setGodsnumber(String godsnumber) {
        this.godsnumber = godsnumber;
    }

    public String getGodsnumber() {
        return godsnumber;
    }

    public void setItme(String itme) {
        this.itme = itme;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype;
    }

    public void setGoodsprice(String goodsprice) {
        this.goodsprice = goodsprice;
    }

    public void setGoodszeng(String goodszeng) {
        this.goodszeng = goodszeng;
    }

    public void setSorename(String sorename) {
        this.sorename = sorename;
    }

    public void setOrdernameber(String ordernameber) {
        this.ordernameber = ordernameber;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public void setOrderzeng(String orderzeng) {
        this.orderzeng = orderzeng;
    }

    public String getItme() {
        return itme;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public String getIcon() {
        return icon;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodstype() {
        return goodstype;
    }

    public String getGoodsprice() {
        return goodsprice;
    }

    public String getGoodszeng() {
        return goodszeng;
    }

    public String getSorename() {
        return sorename;
    }

    public String getOrdernameber() {
        return ordernameber;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public String getOrderzeng() {
        return orderzeng;
    }
}
