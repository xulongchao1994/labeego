package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.Allianceshop_classify_bean
import com.hechuang.labeego.view.activity.IShopClassifyView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ShopClassifyPersenter(itemview: IShopClassifyView, context: Context) : BasePersenter<IShopClassifyView>(itemview, context) {
    fun getdata() {
//        mView.getdata_success()
        mSubscription = mApi!!.post_shopclassify().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_classify_bean>() {
                    override fun onNext(t: Allianceshop_classify_bean?) {
                        if (t!!.data.status == 1) {
                            mView.getdata_success(t)
                        } else {
                            mView.getdata_error(t!!.data.msg)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getdata_error(e!!.message.toString())
                    }
                })

    }

}