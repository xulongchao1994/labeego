package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView


/**
 * Created by Android_xu on 2017/12/4.
 * 强制修改密码
 */

interface IForceModifyPasswordView : BaseView {

    fun PwdSeccess(status: Int, msg: String)
}
