package com.hechuang.labeego.bean;

public class Allorder_TopBean {
    private int icon;
    private String name;

    public Allorder_TopBean(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
