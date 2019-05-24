package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean

interface IOrderTypeView : BaseView {
    /**
     * 获取数据成功
     */
    fun getorder_seccess(listdata: List<AllorderListBean.DataBean.ListBean>, isrefresh: Boolean)

    /**
     * 获取数据失败
     */
    fun getorder_error(msg: String)


    /**
     * 收货成功
     */
    fun shouhuo_seccess(data: Order_shouhuo_Bean,int: Int)

    /**
     * 收货失败
     */
    fun shouhuo_error(msg: String)

}