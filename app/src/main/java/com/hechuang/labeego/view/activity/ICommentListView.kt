package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.CommentListBean

interface ICommentListView : BaseView {
    /**
     * 获取数据成功
     */
    fun getdata_sccuss(lsitdata: List<CommentListBean.DataBean.ListBean>,isrefresh:Boolean)

    /**
     * 获取数据失败
     */
    fun getdata_error(msg: String)
}