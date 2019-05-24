package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean

interface IAllOrderView : BaseView {
    /**
     * 获取数据成功
     */
    fun getlistdataseccess(listdata: List<AllorderListBean.DataBean.ListBean>)

    /**
     * 获取数据失败
     */
    fun getlistdataerror(msg: String)


    /**
     * 收货成功
     */
    fun shouhuo_seccess(data: Order_shouhuo_Bean)

    /**
     * 收货失败
     */
    fun shouhuo_error(msg: String)
}