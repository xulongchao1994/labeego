package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.BindingBean
import com.hechuang.labeego.bean.LoginBean


interface IBindingPhoneView : BaseView {

    /**
     * 绑定成功
     */
    fun Binding_seccess(bindingBean: BindingBean)

    /**
     *
     */
    fun binding_error(msg: String)


    /**
     * 登录成功
     */
    fun login_success(loginBean: LoginBean)

    /**
     * 登录失败
     */
    fun login_error(msg: String)
}