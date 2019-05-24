package com.hechuang.labeego.view.fragment

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.HoenyBean

interface ILittleBeeView : BaseView {

    /**
     * 获取数据成功
     */
    fun gethoenydatasuccess(beandata: HoenyBean, isr: Boolean)

    /**
     * 获取数据失败
     */
    fun gethoenydataerror(msg: String)
}