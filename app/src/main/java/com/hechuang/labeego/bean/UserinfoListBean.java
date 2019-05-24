package com.hechuang.labeego.bean;

public class UserinfoListBean {
    private String username;
    private String turename;

    public UserinfoListBean(String username, String turename) {
        this.username = username;
        this.turename = turename;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTurename(String turename) {
        this.turename = turename;
    }

    public String getUsername() {
        return username;
    }

    public String getTurename() {
        return turename;
    }
}
