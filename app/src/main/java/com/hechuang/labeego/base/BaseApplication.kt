package com.hechuang.labeego.base

import android.app.Application
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.UiDensity
import com.mob.MobSDK
import com.tencent.smtt.sdk.QbSdk

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyToast.init(this)
        QbSdk.initX5Environment(this, null)
        UiDensity.setDensity(this)
        MobSDK.init(this)
        MyOkHttp.initContext(this)
        ApiFactify.getInstance(this)
    }

}