package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.GoodsListBean

interface IGoodsListView : BaseView {
    fun getlist_seccess(mGoodsList: List<GoodsListBean.DataBean.ListBean>,isrefsesh:Boolean)
    fun getlist_error(page:String,msg:String ,isrefsesh: Boolean)
}