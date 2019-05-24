package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.Order_true_bean
import com.hechuang.labeego.bean.Order_ture_list_bean
import com.hechuang.labeego.bean.Orderture_fuwuren

interface IOrder_tureView:BaseView {
    /**
     * 获取数据
     */
    fun getdataok(lisdata:Order_ture_list_bean)
    /**
     * 获取数据失败
     */
    fun getdataerror(msg:String)
    /**
     * 支付完成
     */
    fun pay_ok(paydata:Order_true_bean)

    //支付失败
    fun pay_error(paydata:Order_true_bean)
    fun getfuwuren_seccess(fuwuren:Orderture_fuwuren)
}