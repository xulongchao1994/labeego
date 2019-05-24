package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.Confirmanorder_Shoppingcar_Bean
import com.hechuang.labeego.bean.Order_tijiao

interface IConfirmAnOrderView : BaseView {

    //加入购物车
    fun shoppingcar_success(shoppingcar_data: Confirmanorder_Shoppingcar_Bean.DataBean)

    ///
    fun order_tijiao_seccess(ordertijiao: Order_tijiao.DataBean)

}