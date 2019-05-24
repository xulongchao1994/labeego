package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.GoodsHomeBannerBean
import com.hechuang.labeego.bean.GoodsListBean

interface IGoodsHomeView : BaseView {
    /**
     * 获取轮播图和快速入口数据成功
     */
    fun getbanner_imagee_seccess(goodsHomeBannerBean: GoodsHomeBannerBean.DataBean)

    /**
     * 获取列表数据成功
     */
    fun getlistdata_seccess(goodslistbean: List<GoodsListBean.DataBean.ListBean>, isrefresh: Boolean)

    /**
     * 获取数据错误
     */
    fun getdataerror(page: String, msg: String,isrefresh: Boolean)
}