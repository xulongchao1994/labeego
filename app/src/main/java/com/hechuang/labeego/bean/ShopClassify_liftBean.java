package com.hechuang.labeego.bean;

public class ShopClassify_liftBean {
    private String title;
    private boolean istrue;
    private String id;

    public ShopClassify_liftBean(String title, boolean istrue, String id) {
        this.title = title;
        this.istrue = istrue;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIstrue() {
        return istrue;
    }
}
