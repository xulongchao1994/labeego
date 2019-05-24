package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.GoodsListBean

interface IFindGoodsView : BaseView {
    fun getgoodsdata(goodslistbean: List<GoodsListBean.DataBean.ListBean>,isshuaxin:Boolean)
}