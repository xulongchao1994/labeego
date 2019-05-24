package com.hechuang.labeego.view.fragment

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.MineBean

interface IMineView : BaseView {

    /**
     * 获取数据成功
     */
    fun getdatesuccess(bean:MineBean)
    /**
     * 获取数据失败
     */
    fun getminedataerror(msg:String)
}