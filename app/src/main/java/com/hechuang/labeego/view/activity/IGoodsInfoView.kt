package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.GoodsInfoBean
import com.hechuang.labeego.bean.Goodsinfo_buy_bean
import com.hechuang.labeego.bean.ShoppingCarNumberBean

interface IGoodsInfoView : BaseView {
    fun getgooodsinfo_seccess(goodsInfoBean: GoodsInfoBean)
    fun getdoodsinfo_error(msg: String)
    /**
     * 购买或者加入购物车成功
     */
    fun buy_seccess(type: String, buy_Bean: Goodsinfo_buy_bean)

    /**
     * 购买或者加入购物车失败
     */
    fun buy_error(msg: String)

    /**
     * 获取购物车数量
     */
    fun getnumber(numberbena: ShoppingCarNumberBean)
}