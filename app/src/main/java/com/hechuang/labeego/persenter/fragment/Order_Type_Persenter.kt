package com.hechuang.labeego.persenter.fragment

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.fragment.IOrder_Type_View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Order_Type_Persenter(itemview: IOrder_Type_View, context: Context) : BasePersenter<IOrder_Type_View>(itemview, context) {
    fun getorderall(unm: String?, page: String?, orderstatus: String?, isrefresh: Boolean?) {
//        Log.d("ordertype", "${UserData.USERNAME} ${UserData.USERTOKENID} $unm $page $orderstatus")
        mSubscription = mApi!!.getallorderlist(unm!!, UserData.USERNAME, UserData.USERTOKENID, page!!, orderstatus!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<AllorderListBean>() {
                    override fun onNext(t: AllorderListBean?) {
//                        Log.d("ordertype", t!!.data.toString())
                        mView.getorder_seccess(t!!.data.list, isrefresh!!)
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
                            mView.shouhuo_error(t.data.message)
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