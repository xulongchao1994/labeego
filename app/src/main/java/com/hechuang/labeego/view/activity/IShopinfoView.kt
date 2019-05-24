package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.ShopInfoBean

interface IShopinfoView : BaseView {
    fun getlistsuccess(shopinfoListBean: ShopInfoBean,refresh:Boolean)
    fun getlisterror(string: String)
}