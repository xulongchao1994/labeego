package com.hechuang.labeego.persenter.fragment

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.GoodsHomeBannerBean
import com.hechuang.labeego.bean.GoodsListBean
import com.hechuang.labeego.view.fragment.IGoodsHomeView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GoodsHomePersenter(itemview: IGoodsHomeView, context: Context) : BasePersenter<IGoodsHomeView>(itemview, context) {
    /**
     * 获取商品列表
     */
    fun getbanner_imges() {
        mView.showloading()
        mSubscription = mApi!!.post_goodshome_banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<GoodsHomeBannerBean>() {
                    override fun onNext(t: GoodsHomeBannerBean?) {
                        Log.d("goodshome:onnext", "beanimg" + t!!.data.toString())
                        mView.getbanner_imagee_seccess(t.data!!)
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        Log.d("goodshome:onerror", "beanimg" + e!!.message)
                    }
                })
    }

    fun getlietdata(page: String, isrefersh: Boolean) {
        mView.showloading()
        mSubscription = mApi!!.goodslist("", page, "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<GoodsListBean>() {
                    override fun onNext(t: GoodsListBean?) {
                        mView.getlistdata_seccess(t!!.data.list, isrefersh)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getdataerror(page, "", isrefersh)
                    }
                })
    }

}