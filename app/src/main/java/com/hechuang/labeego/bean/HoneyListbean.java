package com.hechuang.labeego.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class HoneyListbean extends SectionEntity<HoenyBean.DataBean.ListBeanX.RecordBean.ListBean> {
    @Override
    public String toString() {
        return "HoneyListbean{" +
                "isHeader=" + isHeader +
                ", t=" + t +
                ", header='" + header + '\'' +
                '}';
    }

    public HoneyListbean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public HoneyListbean(HoenyBean.DataBean.ListBeanX.RecordBean.ListBean bean) {
        super(bean);
    }

}
