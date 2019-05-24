package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.BeeForceInfoBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IBeeForceInfoView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BeeForceInfoPersenter(itemview: IBeeForceInfoView, context: Context) : BasePersenter<IBeeForceInfoView>(itemview, context) {

    fun getinfodata(type: String, id: String) {

        mSubscription = mApi!!.getbeeinfodata(UserData.USERNAME, UserData.USERTOKENID, type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<BeeForceInfoBean>() {
                    override fun onNext(t: BeeForceInfoBean?) {
                        Log.d("beeforceinfo", t!!.data.toString())
                        if (t!!.data.status == 1) {
                            mView.getinfodatasuccess(t)
                        } else {
                            mView.getinfodataerror(t!!.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getinfodataerror(e!!.message.toString())
                    }
                })
    }
}