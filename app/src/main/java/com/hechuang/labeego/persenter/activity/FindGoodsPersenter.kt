package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.GoodsListBean
import com.hechuang.labeego.view.activity.IFindGoodsView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class FindGoodsPersenter(itemview: IFindGoodsView, context: Context) : BasePersenter<IFindGoodsView>(itemview, context) {

    fun getlietdata(page: String, keyword: String, isshuaxin: Boolean) {
        Log.d("findgoods", "$page $keyword $isshuaxin")
        mView.showloading()
        mSubscription = mApi!!.goodslist("2", page, keyword, "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<GoodsListBean>() {
                    override fun onNext(t: GoodsListBean?) {
                        Log.d("findgoods", t!!.data.toString())
                        mView.getgoodsdata(t!!.data.list, isshuaxin)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("findgoods", e!!.message)
//                        MyToast.showMsg("获取数据错误，请稍后重试")
                        mView.dismissloading()
                    }
                })
    }
}