package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.MyHoneyBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IMyHoneyView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyHoneyPersenter(itemview: IMyHoneyView, context: Context) : BasePersenter<IMyHoneyView>(itemview, context) {
    fun getmyhoenydata(page: String, is_r: Boolean) {
        mView.showloading()
        mSubscription = mApi!!.getmyhoeny(UserData.USERNAME, UserData.USERTOKENID, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<MyHoneyBean>() {
                    override fun onNext(t: MyHoneyBean?) {
                        if (t!!.data.status == 1) {
                            mView.gethoenydatasuccess(t, is_r)
                        } else {
                            mView.gethoenydataerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("myhoney", e!!.message.toString())
                        mView.gethoenydataerror(e!!.message.toString())
                    }
                })
    }

}