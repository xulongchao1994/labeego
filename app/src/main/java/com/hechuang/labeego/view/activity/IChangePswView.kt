package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView

interface IChangePswView : BaseView {
    /**
     * 设置密码成功
     */
    fun setpswsuccess(msg: String)

    /**
     * 设置密码失败
     */
    fun setpswerror(msg: String)
}