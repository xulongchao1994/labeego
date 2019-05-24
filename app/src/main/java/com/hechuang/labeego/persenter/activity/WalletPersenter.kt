package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.WalletBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IWalletView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class WalletPersenter(itemview: IWalletView, context: Context) : BasePersenter<IWalletView>(itemview, context) {

    /**
     * 获取数据
     */
    fun getwalletdata() {
        mSubscription = mApi!!.getwalletdata(UserData.USERNAME, UserData.USERTOKENID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<WalletBean>() {
                    override fun onNext(t: WalletBean?) {
                        if (t!!.data.status == 1) {
                            mView.getdatasuccess(t)
                        } else {
                            mView.getdataerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getdataerror(e!!.message.toString())
                    }
                })
    }
}