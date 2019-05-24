package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView

interface ISetNameView : BaseView {
    /**
     * 设置姓名成功
     */
    fun setnamesuccess(msg: String)

    /**
     * 设置姓名成功
     */
    fun setnameerror(msg: String)
}