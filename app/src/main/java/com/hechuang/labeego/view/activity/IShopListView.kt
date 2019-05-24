package com.hechuang.labeego.view.activity

import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.ShoplistBean

interface IShopListView: BaseView {
    fun getlistdatasuccess(shoplistBean: ShoplistBean, isheatview: Boolean)
    fun getlistdataerreo(string: String)
}