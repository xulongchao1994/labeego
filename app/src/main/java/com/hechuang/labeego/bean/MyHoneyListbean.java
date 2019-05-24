package com.hechuang.labeego.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class MyHoneyListbean extends SectionEntity<MyHoneyBean.DataBean.ListBeanX.RecordBean.ListBean> {

    public MyHoneyListbean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyHoneyListbean(MyHoneyBean.DataBean.ListBeanX.RecordBean.ListBean bean) {
        super(bean);
    }

    @Override
    public String toString() {
        return "MyHoneyListbean{" +
                "isHeader=" + isHeader +
                ", t=" + t +
                ", header='" + header + '\'' +
                '}';
    }
}
