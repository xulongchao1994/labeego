package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.ShoplistBean

interface ISearchShopView : BaseView {
    fun getlistdatasuccess(shoplistBean: ShoplistBean)
    fun getlistdataerreo(string: String)
}