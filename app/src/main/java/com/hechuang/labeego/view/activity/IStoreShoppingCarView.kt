package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.*

interface IStoreShoppingCarView : BaseView {
    //获取列表数据成功
    fun getshoppingdatascesses(shoppinglist: List<StoreShoppingCarBean.DataBean.ListBean.ProductBean>)

//    //获取商品规格成功
//    fun getshoppingguigesessess(guige_bean: Shoppingcar_Guige_bean, position: Int)

    //删除商成功
    fun delied_sessess(msg: String)

    //商品删除失败
    fun delete_eroor(msg: String)

    //修改商品数量
    fun goodsnumber(storeShoppingcar_shopingnumberBean: StoreShoppingcar_shopingnumberBean, position: Int)

    //修改商品数量出错
    fun goodsnumbererror(msg: String)

    //获取购物车数据出错
    fun getcar_error(msg: String, shoppinglist: List<StoreShoppingCarBean.DataBean.ListBean.ProductBean>)
}