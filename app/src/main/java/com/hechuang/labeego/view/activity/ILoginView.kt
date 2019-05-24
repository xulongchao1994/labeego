package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.*

interface ILoginView : BaseView {
    fun login_seccess(mLogindata: Login_Bean)
    fun login_fmpw(mLogindata: Login_Bean)
    fun login_error(msg: String)

    /**
     * 获取登录页面数据成功
     */
    fun get_view_success(dataBean: LoginviewBean.DataBean)

    /**
     * 获取登录页面数据失败
     */
    fun get_view_error(msg: String)


    /**
     * 获取验证码成功
     */
    abstract fun getauthcode(codeBean: AuthCodeBean)

    /**
     * 获取验证码
     */
    abstract fun getautherror(msg: String)

    /**
     * 短信验证获取用户列表成功
     */
    abstract fun getphoneuserlist(phoneLoginBean: PhoneLoginBean)

    /**
     * 短信验证获取用户列表失败
     */
    abstract fun getphoneuserlisterror(msg: String)


    /**
     * 短信登录成功
     */
    abstract fun getphoneloginsuccess(phoneSuccessBean: PhoneSuccessBean)

    /**
     * 短信登录失败
     */
    abstract fun getphoneloginerror(msg: String)
}