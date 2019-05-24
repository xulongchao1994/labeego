package com.hechuang.labeego.bean;

import java.util.List;

public class ShopClassify_RightBean {
    private String id;
    private String title;
    private List<Gridbean> gridbeans;

    public ShopClassify_RightBean(String id, String title, List<Gridbean> gridbeans) {
        this.id = id;
        this.title = title;
        this.gridbeans = gridbeans;
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

    public void setGridbeans(List<Gridbean> gridbeans) {
        this.gridbeans = gridbeans;
    }

    public String getTitle() {
        return title;
    }

    public List<Gridbean> getGridbeans() {
        return gridbeans;
    }

    public static class Gridbean {
        private String id;
        private String name;

        public Gridbean(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
