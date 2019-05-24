package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.ShopInfoBean
import com.hechuang.labeego.view.activity.IShopinfoView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ShopInfoPersenter(itemview: IShopinfoView, context: Context) : BasePersenter<IShopinfoView>(itemview, context) {
    fun getlistdata(id: String, page: String,refresh:Boolean) {
        mSubscription = mApi!!.getshopinfolist(id, page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ShopInfoBean>() {
                    override fun onNext(t: ShopInfoBean?) {
                        if (t!!.data!!.status == 1) {
                            mView.getlistsuccess(t,refresh)
                        } else {
                            mView.getlisterror(t.data!!.msg!!)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getlisterror(e!!.message.toString())
                    }
                })

    }

}