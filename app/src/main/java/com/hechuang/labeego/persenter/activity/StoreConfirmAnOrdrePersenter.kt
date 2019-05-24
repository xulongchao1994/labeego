package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.ConfirmanOrder_AlipayBean
import com.hechuang.labeego.bean.Confirmanorder_Shoppingcar_Bean
import com.hechuang.labeego.bean.Order_tijiao
import com.hechuang.labeego.bean.StoreConfirmanorder_Shoppingcar_Bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IConfirmAnOrderView
import com.hechuang.labeego.view.activity.IStoreConfirmAnOrderView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class StoreConfirmAnOrdrePersenter(itemView: IStoreConfirmAnOrderView, context: Context) : BasePersenter<IStoreConfirmAnOrderView>(itemView, context) {
    fun getdata_shoppingar(shoppingcarid: String, type: String) {
        mView.showloading()
        Log.d("confirm", "$type ${UserData.USERNAME} ${UserData.USERTOKENID} $shoppingcarid")
        mSubscription = mApi!!.GET_CONFIRMANORDERDATA(UserData.USERNAME, UserData.USERTOKENID, shoppingcarid, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StoreConfirmanorder_Shoppingcar_Bean>() {
                    override fun onNext(t: StoreConfirmanorder_Shoppingcar_Bean?) {
                        for (i in 0 until t!!.data.list.size) {
                            t.data.list[i].message = ""
                        }
                        Log.e("confirm_next", t!!.data.toString())
                        mView.shoppingcar_success(t!!.data)
                        mView.dismissloading()
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("confirm_error", e!!.message)
                        mView.dismissloading()
                    }
                })
    }

    fun ordr_tijiao(num: String, shopcartid: String, order_token: String, message: String, type: String) {
        mView!!.showloading()
        Log.d("confirm_tijiao", " $num ${UserData.USERNAME} ${UserData.USERTOKENID} $shopcartid $order_token $message $type")
        mSubscription = mApi!!.POST_CONFIRMANORDER(UserData.USERNAME, UserData.USERTOKENID, shopcartid, num, type, message, order_token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Order_tijiao>() {
                    override fun onNext(t: Order_tijiao?) {
                        Log.d("confirm_tijiao_next", t!!.data.toString())
                        if (t!!.data.status == "1") {
                            mView.order_tijiao_seccess(t!!.data)
                        } else {
                            mView.order_tijiao_error(t!!.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("confirm_tijiao_error", e!!.message)
                        mView.order_tijiao_error(e!!.message.toString())
                        mView.dismissloading()
                    }
                })
//
    }

    fun getalipaydata(orderid: String) {
        mSubscription = mApi!!.GET_ALIPAY(UserData.USERNAME, UserData.USERTOKENID, orderid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<ConfirmanOrder_AlipayBean>() {
                    override fun onNext(t: ConfirmanOrder_AlipayBean?) {
                        if (t!!.data.status == 1) {
                            mView.alipay_data(t)
                        } else {
                            mView.getalipay_error(t.data.msg)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getalipay_error(e!!.message.toString())
                    }
                })
    }
}