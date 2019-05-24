package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.OrderInfoBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IOrderInfoView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class OrderInfoPersenter(itemview: IOrderInfoView, context: Context) : BasePersenter<IOrderInfoView>(itemview, context) {
    fun getdata_srccess(orderid: String) {
        Log.d("orderinfo", "${UserData.USERNAME} ${UserData.USERTOKENID}  $orderid")
        mSubscription = mApi!!.getorderinfo(UserData.USERNAME, UserData.USERTOKENID, orderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<OrderInfoBean>() {
                    override fun onNext(t: OrderInfoBean?) {
                        if (t!!.data.status == 1) {
                            mView.getdataseccess(t!!)
                        } else {
                            mView.getdataerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("orderinfo",e!!.message.toString())
                        mView.getdataerror(e!!.message.toString())
                    }
                })
    }

    fun postshouhuo(orderid: String) {
        mSubscription = mApi!!.postshouhuo(UserData.USERNAME, UserData.USERTOKENID, orderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Order_shouhuo_Bean>() {
                    override fun onNext(t: Order_shouhuo_Bean?) {
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