package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.ShoppingCarBean
import com.hechuang.labeego.bean.Shoppingcar_Bianji_bean
import com.hechuang.labeego.bean.Shoppingcar_Guige_bean

interface IShoppingCarView : BaseView {
    //获取列表数据成功
    fun getshoppingdatascesses(shoppinglist: List<ShoppingCarBean.DataBean.ListBean.ProductBean>)

    //获取商品规格成功
    fun getshoppingguigesessess(guige_bean: Shoppingcar_Guige_bean, position: Int)

    //s删除商成功
    fun delied_sessess(msg: String)

    //修改商品成功
    fun xiugai_sessess(yaunnumber: String, gaibiannumber: String, msg: Shoppingcar_Bianji_bean, position: Int)

    //修改数量失败
    fun xiggai_error(yaunnumber: String, gaibiannumber: String, msg: Shoppingcar_Bianji_bean, position: Int)

    //获取购物车数据出错
    fun getcar_error(msg: String, shoppinglist: List<ShoppingCarBean.DataBean.ListBean.ProductBean>)
}