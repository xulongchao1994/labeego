package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.Order_true_bean
import com.hechuang.labeego.bean.Order_ture_list_bean
import com.hechuang.labeego.bean.Orderture_fuwuren
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IOrder_tureView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class OrderturePersenter(itemview: IOrder_tureView, context: Context) : BasePersenter<IOrder_tureView>(itemview, context) {
    fun getorderpay(orderbianhao: String) {
        mView.showloading()
        Log.d("orderture", UserData.USERNAME + "  " + UserData.USERTOKENID + "  " + orderbianhao)
        mSubscription = mApi!!.order_turepay(UserData.USERNAME, UserData.USERTOKENID, orderbianhao).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Order_ture_list_bean>() {
                    override fun onNext(t: Order_ture_list_bean?) {
                        mView.getdataok(t!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.dismissloading()
                        mView.getdataerror(e!!.message.toString())
                    }
                })
    }

    fun postorderpay(orderbianhao: String, pay_token: String, serviceid: String, isxiaofeiquan: String) {
        Log.d("orderture", "orderbaihao:$orderbianhao pay_token:$pay_token serviceid:$serviceid ")
        mView.showloading()
        mSubscription = mApi!!.post_order(UserData.USERNAME, UserData.USERTOKENID, orderbianhao, pay_token, serviceid, isxiaofeiquan, "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Order_true_bean>() {
                    override fun onNext(t: Order_true_bean?) {
                        Log.d("orderture", t!!.data.toString())
                        if (t.data.status == 1) {
                            mView.pay_ok(t)
                        } else {
                            mView.pay_error(t)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.dismissloading()
                    }
                })
    }

    fun getfuwuren(serviceid: String) {
        var body = FormBody.Builder().add("serviceid", serviceid)
                .build()
        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/Order/checkservice.php?act=checkservice", body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
                Log.d("orderture", data)
                var json = JSONObject(data)
                var datas = json.getJSONObject("data")
                var status = datas.get("status").toString()
                var message = datas.get("message").toString()
                if (status == "1") {
                    var listdata = datas.getJSONObject("list")
                    var truename = listdata.get("TrueName").toString()
                    var Mobile = listdata.get("Mobile").toString()
                    var orderture = Orderture_fuwuren.DataBean.ListBean(truename, Mobile)
                    var turedfs = Orderture_fuwuren.DataBean(status, message, orderture)
                    var fdsfa = Orderture_fuwuren(turedfs)
                    mView.getfuwuren_seccess(fdsfa)
                } else {
                    var orderture = Orderture_fuwuren.DataBean.ListBean("", "")
                    var turedfs = Orderture_fuwuren.DataBean(status, message, orderture)
                    var fdsfa = Orderture_fuwuren(turedfs)
                    mView.getfuwuren_seccess(fdsfa)
                }
//                mView.getfuwuren_seccess(t!!)
            }

            override fun fail(request: Request?, e: Exception?) {
            }
        })
//        mSubscription = mApi!!.ordertrue_fuwuren(serviceid)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<Orderture_fuwuren>() {
//                    override fun onNext(t: Orderture_fuwuren?) {
//                        mView.getfuwuren_seccess(t!!)
//                    }
//
//                    override fun onCompleted() {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        Log.d("orderture:getfuwuren", e!!.message)
//                    }
//                })
    }

}