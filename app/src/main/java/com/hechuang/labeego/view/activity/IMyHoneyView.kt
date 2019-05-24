package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.MyHoneyBean

interface IMyHoneyView : BaseView {
    /**
     * 获取数据成功
     */
    fun gethoenydatasuccess(beandata: MyHoneyBean, isr: Boolean)

    /**
     * 获取数据失败
     */
    fun gethoenydataerror(msg: String)
}