package com.hechuang.labeego.base

import android.content.Context
import com.hechuang.labeego.api.Api
import com.hechuang.labeego.api.ApiFactify
import rx.Subscription

abstract class BasePersenter<V : BaseView>(var mView: V, var mContext: Context) {
    protected var mApi: Api? = null
    protected var mSubscription: Subscription? = null

    init {
        if (mApi == null) {
            mApi = ApiFactify.getInstance(mContext.applicationContext)
        }
    }

    /**
     * 取消网络请求(这里是有RxJava,即为取消订阅结果)
     */

    fun cannelRequest() {
        if (mSubscription != null)
            mSubscription!!.unsubscribe()
    }

}