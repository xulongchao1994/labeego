package com.hechuang.labeego.view.fragment

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.TheRaiseBean

interface ITheRaiseView : BaseView {

    /**
     * 获取数据成功
     */
    fun getorder_seccess(listdata: List<TheRaiseBean.DataBean.ListBean>, isrefresh: Boolean)

    /**
     * 获取数据失败
     */
    fun getorder_error(msg: String)


}