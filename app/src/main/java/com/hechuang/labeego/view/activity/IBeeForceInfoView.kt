package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.BeeForceInfoBean

interface IBeeForceInfoView : BaseView {
    /**
     * 获取数据成功
     */
    fun getinfodatasuccess(databean:BeeForceInfoBean)
    /**
     * 获取数据失败
     */
    fun getinfodataerror(msg:String)
}