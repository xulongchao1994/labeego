package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IOrderTypeView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class OrderTypePersenter(itemview: IOrderTypeView, context: Context) : BasePersenter<IOrderTypeView>(itemview, context) {

    fun getorderall(unm: String, page: String, orderstatus: String, isrefresh: Boolean) {
        Log.d("ordertype", "${UserData.USERNAME} ${UserData.USERTOKENID} $unm $page $orderstatus")
        mSubscription = mApi!!.getallorderlist(unm, UserData.USERNAME, UserData.USERTOKENID, page, orderstatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<AllorderListBean>() {
                    override fun onNext(t: AllorderListBean?) {
                        mView.getorder_seccess(t!!.data.list, isrefresh)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getorder_error(e!!.message.toString())
                    }
                })
    }

    fun postshouhuo(orderid: String, int: Int) {
        mSubscription = mApi!!.postshouhuo(UserData.USERNAME, UserData.USERTOKENID, orderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Order_shouhuo_Bean>() {
                    override fun onNext(t: Order_shouhuo_Bean?) {
                        if (t!!.data.status == 1) {
                            mView.shouhuo_seccess(t, int)
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