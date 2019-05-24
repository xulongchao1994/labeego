package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.HoenyBean

/**
 * 我的蜂蜜
 */
interface IHoenyView : BaseView {
    /**
     * 获取数据成功
     */
    fun gethoenydatasuccess(beandata: HoenyBean, isr: Boolean)

    /**
     * 获取数据失败
     */
    fun gethoenydataerror(msg: String)
}