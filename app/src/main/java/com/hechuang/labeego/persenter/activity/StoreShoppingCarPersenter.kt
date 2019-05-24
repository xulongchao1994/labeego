package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.*
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IShoppingCarView
import com.hechuang.labeego.view.activity.IStoreShoppingCarView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class StoreShoppingCarPersenter(itemview: IStoreShoppingCarView, context: Context) : BasePersenter<IStoreShoppingCarView>(itemview, context) {
    fun getshoppinglist() {
        mView.showloading()
        mSubscription = mApi!!.GET_SHOPSHOPPING(UserData.USERNAME, UserData.USERTOKENID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StoreShoppingCarBean>() {
                    override fun onNext(t: StoreShoppingCarBean?) {
                        Log.d("shoppingcar", t.toString())
                        var name = ""
                        var shoppinglistitmearr = arrayListOf<StoreShoppingCarBean.DataBean.ListBean.ProductBean>()
                        for (i in 0 until t!!.data.list.size) {
                            name = t.data.list[i].name
                            for (j in 0 until t.data.list[i].product.size) {
                                t.data.list[i].product[j].isshowtitle = j == 0
                                t.data.list[i].product[j].storename = name
                                t.data.list[i].product[j].ischeck = false
                                t.data.list[i].product[j].isstorecheck = false
                                t.data.list[i].product[j].isbianji = false
                                shoppinglistitmearr.add(t.data.list[i].product[j])
                            }
                        }
                        mView.getshoppingdatascesses(shoppinglistitmearr)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("shoppingcar", e!!.message.toString())
                        var shoppinglistitmearr = arrayListOf<StoreShoppingCarBean.DataBean.ListBean.ProductBean>()
                        mView.getcar_error("", shoppinglistitmearr)
                    }
                })
    }


    fun shopnumber(styleid: String, type: String, position: Int) {
        mSubscription = mApi!!.STORESHOPPINGCAR_SHOPNUMBER(UserData.USERNAME, UserData.USERTOKENID, styleid, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StoreShoppingcar_shopingnumberBean>() {
                    override fun onNext(t: StoreShoppingcar_shopingnumberBean?) {
                        if (t!!.data.status == 1) {
                            mView.goodsnumber(t, position)
                        } else {
                            mView.goodsnumbererror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.goodsnumbererror(e!!.message.toString())
                    }
                })

    }

    fun deletegoods(styleid: String) {
        mSubscription = mApi!!.STORESHOPPINGCAR_DELETEGOODS(UserData.USERNAME, UserData.USERTOKENID, styleid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StoreShoppingcar_shopingnumberBean>() {
                    override fun onNext(t: StoreShoppingcar_shopingnumberBean?) {
                        if (t!!.data.status == 1) {
                            mView.delied_sessess(t.data.message)
                        } else {
                            mView.delete_eroor(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.delete_eroor(e!!.message.toString())
                    }
                })


    }
}