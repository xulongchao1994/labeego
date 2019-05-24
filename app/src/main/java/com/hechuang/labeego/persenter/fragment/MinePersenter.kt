package com.hechuang.labeego.persenter.fragment

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.MineBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.fragment.IMineView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MinePersenter(itemview: IMineView, context: Context) : BasePersenter<IMineView>(itemview, context) {
    /**
     * 获取个人中心数据
     */
    fun getdata() {
        Log.d("mine", UserData.USERNAME + "   " + UserData.USERTOKENID)
        mSubscription = mApi!!.getminedata(UserData.USERNAME, UserData.USERTOKENID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<MineBean>() {
                    override fun onNext(t: MineBean?) {
                        Log.d("mine", t.toString())
                        if (t!!.data.status == 1) {
                            mView.getdatesuccess(t)
                        } else {
                            mView.getminedataerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("mine", e!!.message)
                        mView.getminedataerror(e!!.message.toString())
                    }
                })
    }
}