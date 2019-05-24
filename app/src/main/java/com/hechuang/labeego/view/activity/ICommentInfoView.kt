package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.CommentAddBean
import com.hechuang.labeego.bean.CommentinfoBean

interface ICommentInfoView:BaseView {
    /**
     * 获取数据成功
     */
    fun  getdatasuccess(datainfo:CommentinfoBean)
    /**
     * 获取数据失败
     */
    fun getdataerror(msg:String)

    /**
     * 评论成功
     */
    fun postzhuiping_success(databean:CommentAddBean)
    /**
     * 追评失败
     */
    fun postzhuiping_error(msg:String)


    /**
     * 删除成功
     */
    fun delete_success(msg:String)
    /**
     * 删除失败
     */
    fun delete_error(msg:String)
}