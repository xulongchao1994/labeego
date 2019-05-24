package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.UserInfoBean

interface ISettingView : BaseView {
    /**
     * 获取个人信息成功
     */
    fun getuserinfodatasuccess(userinfoddata:UserInfoBean)

    /**
     * 获取个人信息失败
     */
    fun getuserdatainfoerror(msg:String)
}