package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.ConfirmanOrder_AlipayBean
import com.hechuang.labeego.bean.Order_tijiao
import com.hechuang.labeego.bean.StoreConfirmanorder_Shoppingcar_Bean

interface IStoreConfirmAnOrderView : BaseView {

    //加入购物车
    fun shoppingcar_success(shoppingcar_data: StoreConfirmanorder_Shoppingcar_Bean.DataBean)

    //订单提交成功
    fun order_tijiao_seccess(ordertijiao: Order_tijiao.DataBean)

    //订单提交失败
    fun  order_tijiao_error(msg:String)
    /**
     * 支付宝支付
     */
    fun alipay_data(confirmanOrder_AlipayBean: ConfirmanOrder_AlipayBean)

    /**
     * 获取支付宝信息失败
     */
    fun getalipay_error(string: String)

}