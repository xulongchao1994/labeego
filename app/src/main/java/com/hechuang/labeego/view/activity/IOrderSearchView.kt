package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.bean.SearchOrderBean

interface IOrderSearchView : BaseView {
    /**
     * 获取数据成功
     */
    fun getdatasuccess(listdata: List<SearchOrderBean.DataBean.ListBean>, isr: Boolean)

    /**
     * 获取数据失败
     */
    fun getdataeror(msg: String)


    /**
     * 收货成功
     */
    fun shouhuo_seccess(data: Order_shouhuo_Bean)

    /**
     * 收货失败
     */
    fun shouhuo_error(msg: String)
}
