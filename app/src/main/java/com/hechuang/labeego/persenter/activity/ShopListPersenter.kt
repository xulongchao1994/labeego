package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.ShoplistBean
import com.hechuang.labeego.view.activity.IShopListView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ShopListPersenter(itemview: IShopListView, context: Context) : BasePersenter<IShopListView>(itemview, context) {
    fun getlist(page: String, classify: String, seek: String, prefecture: String, isheatview: Boolean) {
//        mView.getlistdatasuccess()
        mSubscription = mApi!!.getshoplist(page, classify, seek, prefecture)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ShoplistBean>() {
                    override fun onNext(t: ShoplistBean?) {
                        if (t!!.data.status == 1) {
                            mView.getlistdatasuccess(t,isheatview)
                        } else {
                            mView.getlistdataerreo(t.data.msg)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getlistdataerreo(e!!.message.toString())
                    }
                })
    }
}