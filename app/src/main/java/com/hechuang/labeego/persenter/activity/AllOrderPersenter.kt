package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IAllOrderView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AllOrderPersenter(itemview: IAllOrderView, context: Context) : BasePersenter<IAllOrderView>(itemview, context) {

    fun getorderall(unm: String, page: String, orderstatus: String) {
        mSubscription = mApi!!.getallorderlist(unm, UserData.USERNAME, UserData.USERTOKENID, page, orderstatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<AllorderListBean>() {
                    override fun onNext(t: AllorderListBean?) {
                        mView.getlistdataseccess(t!!.data.list)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getlistdataerror("网络错误，请稍后重试")
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