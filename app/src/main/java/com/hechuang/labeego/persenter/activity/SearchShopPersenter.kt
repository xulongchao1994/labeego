package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.ShoplistBean
import com.hechuang.labeego.view.activity.ISearchShopView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SearchShopPersenter(itemview: ISearchShopView, context: Context) : BasePersenter<ISearchShopView>(itemview, context) {
    fun getlist(page: String, classify: String, seek: String, prefecture: String) {
//        mView.getlistdatasuccess()
        Log.d("searchshop", "page:$page calssify $classify seek:$seek prefecture:$prefecture")
        mSubscription = mApi!!.getshoplist(page, classify, seek, prefecture)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ShoplistBean>() {
                    override fun onNext(t: ShoplistBean?) {
                        if (t!!.data.status == 1) {
                            mView.getlistdatasuccess(t)
                        } else {
                            mView.getlistdataerreo(t.data.msg)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("searchshop", e!!.message.toString())
                        mView.getlistdataerreo(e!!.message.toString())
                    }
                })
    }

}