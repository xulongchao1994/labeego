package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.*

interface IShopGoodsInfoView : BaseView {
    fun getgooodsinfo_seccess(goodsInfoBean: ShopGoodInfoBean)
    fun getdoodsinfo_error(msg: String)
    /**
     * 购买或者加入购物车成功
     */
    fun buy_seccess(type: String, buy_Bean: StoreGoodsinfo_buy_bean)

    /**
     * 购买或者加入购物车失败
     */
    fun buy_error(msg: String)

    /**
     * 获取购物车数量
     */
    fun getnumber(numberbena: ShoppingCarNumberBean)
}