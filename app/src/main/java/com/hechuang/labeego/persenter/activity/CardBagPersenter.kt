package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.CardBagBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.ICardBagView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CardBagPersenter(itemview: ICardBagView, context: Context) : BasePersenter<ICardBagView>(itemview, context) {
    fun getdata() {
        mSubscription = mApi!!.getcardbagdata(UserData.USERNAME, UserData.USERTOKENID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CardBagBean>() {
                    override fun onNext(t: CardBagBean?) {
                        Log.d("cardbag", t!!.data.toString())
                        if (t!!.data.status == 1) mView.getcarddatasuccess(t) else mView.getcardtataerror(t.data.message)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("cardbag", e!!.message.toString())
                        mView.getcardtataerror(e!!.message.toString())
                    }
                })
    }
}