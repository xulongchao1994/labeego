package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.LoginBean
import com.hechuang.labeego.bean.Login_Bean
import com.hechuang.labeego.bean.V_bean

interface IMainView : BaseView {
    fun getv_o(v_bean: V_bean)
    fun getv_error(msg: String)
    fun login_ok(logindata: Login_Bean)


    /**
     * 登录成功
     */
    abstract fun login_success(loginBean: LoginBean)

    /**
     * 登录失败
     */
    abstract fun login_error(msg: String)
}