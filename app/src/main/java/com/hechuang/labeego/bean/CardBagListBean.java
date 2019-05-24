package com.hechuang.labeego.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class CardBagListBean extends SectionEntity<CardBagBean.DataBean.ListBean.InfoBean> {
    private String img;
    private String title;
    private String mun;

    public CardBagListBean(boolean isHeader, String header, String img, String title, String mun) {
        super(isHeader, header);
        this.img = img;
        this.title = title;
        this.mun = mun;
    }

    public CardBagListBean(CardBagBean.DataBean.ListBean.InfoBean cardBagBean) {
        super(cardBagBean);
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMun(String mun) {
        this.mun = mun;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getMun() {
        return mun;
    }
}
