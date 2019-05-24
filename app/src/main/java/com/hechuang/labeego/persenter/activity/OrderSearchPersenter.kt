package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.bean.SearchOrderBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IOrderSearchView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class OrderSearchPersenter(Itemview: IOrderSearchView, context: Context) : BasePersenter<IOrderSearchView>(Itemview, context) {

    fun searchorderlist(Page: String, orderid: String, proname: String, isr: Boolean) {
        mSubscription = mApi!!.searchorder(UserData.USERNAME, UserData.USERTOKENID, Page, orderid, proname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<SearchOrderBean>() {
                    override fun onNext(t: SearchOrderBean?) {
                        mView.getdatasuccess(t!!.data.list, isr)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getdataeror(e!!.message.toString())
                    }
                })
    }

    fun postshouhuo(orderid: String) {
        mSubscription = mApi!!.postshouhuo(UserData.USERNAME, UserData.USERTOKENID, orderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Order_shouhuo_Bean>() {
                    override fun onNext(t: Order_shouhuo_Bean?) {
                        Log.d("allorder",t!!.data.toString())
                        if (t!!.data.status == 1) {
                            mView.shouhuo_seccess(t)
                        } else {
                            mView.shouhuo_error(t!!.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.shouhuo_error(e!!.message.toString())
                    }
                })
    }
}