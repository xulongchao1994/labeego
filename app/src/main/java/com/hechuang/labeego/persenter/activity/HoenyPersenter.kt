package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.HoenyBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IHoenyView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HoenyPersenter(itemview: IHoenyView, context: Context) : BasePersenter<IHoenyView>(itemview, context) {
    fun gethoenydata(page: String, is_r: Boolean) {
        mView.showloading()
        mSubscription = mApi!!.gethoeny(UserData.USERNAME, UserData.USERTOKENID, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HoenyBean>() {
                    override fun onNext(t: HoenyBean?) {
                        if (t!!.data.status == 1) {
                            mView.gethoenydatasuccess(t, is_r)
                        } else {
                            mView.gethoenydataerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.gethoenydataerror(e!!.message.toString())
                    }
                })
    }

}