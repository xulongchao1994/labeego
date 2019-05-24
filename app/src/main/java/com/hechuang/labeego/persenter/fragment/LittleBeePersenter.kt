package com.hechuang.labeego.persenter.fragment

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.HoenyBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.fragment.ILittleBeeView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LittleBeePersenter(itemview: ILittleBeeView, context: Context) : BasePersenter<ILittleBeeView>(itemview, context) {
    fun getbeedata(bee_type: String, page: String, is_r: Boolean) {
        mSubscription = mApi!!.getbeedata(UserData.USERNAME, UserData.USERTOKENID, page, bee_type)
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