package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.CommentAddBean
import com.hechuang.labeego.bean.Commentbean

interface ICommentView : BaseView {
    /**
     * 获取数据成功
     */
    fun getdatasuccess(databean: Commentbean)

    /**
     * 获取数据失败
     */
    fun getdataerror(msg: String)

    /**
     * 评价成功
     */
    fun postcomment_success(databean: CommentAddBean)

    /**
     * 评价失败
     */
    fun postcomment_error(str:String)
}