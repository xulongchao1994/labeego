package com.hechuang.labeego.persenter.fragment

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.TheRaiseBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.fragment.ITheRaiseView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TheRaisePersenter(itemview: ITheRaiseView, context: Context) : BasePersenter<ITheRaiseView>(itemview, context) {
    fun gettheraisedata(page: String?, isrefresh: Boolean?) {
        mSubscription = mApi!!.gettheraisedata(UserData.USERNAME, UserData.USERTOKENID, page!!, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<TheRaiseBean>() {
                    override fun onNext(t: TheRaiseBean?) {
                        mView.getorder_seccess(t!!.data.list, isrefresh!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getorder_error(e!!.message.toString())
                    }
                })
    }
}