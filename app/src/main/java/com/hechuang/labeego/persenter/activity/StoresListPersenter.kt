package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.GoodsListBean
import com.hechuang.labeego.view.activity.IStoresListView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class StoresListPersenter(context: Context, itemView: IStoresListView) : BasePersenter<IStoresListView>(itemView, context) {
    //    var goodslist = ArrayList<GoodsListBean.DataBean.ListBean>()
//    fun getlistdata(srt: String, Page: String, keyword: String, type: String, isrefsesh: Boolean) {
//        Log.d("goodslist-dfadfasf", "str=$srt page=$Page keyword=$keyword type=$type is=$isrefsesh")
//        mView.showloading()
//        mSubscription = mApi!!.goodslist(srt, Page, keyword, type)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<GoodsListBean>() {
//                    override fun onNext(t: GoodsListBean?) {
//                        Log.d("goodslist", t!!.data.toString())
//                        if (t!!.data!!.status == 1 && t!!.data!!.list.size > 0) {
////                            for (i in 0 until t.data!!.list.size) {
////                                goodslist.add(t.data!!.list[i])
////                            }
//                            mView.getlist_seccess(t!!.data.list, isrefsesh)
//                        } else {
//                            mView.getlist_error(Page, "暂无数据", isrefsesh)
//                        }
//                    }
//
//                    override fun onCompleted() {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        Log.d("goodslist-onerrror",e!!.message)
//                        mView.getlist_error(Page, "暂无数据", isrefsesh)
//                    }
//                })
//
//    }

    fun getstorelistdata(srt: String, Page: String, keyword: String, isrefsesh: Boolean, supid: String, sale: String) {
        mView.showloading()
        mSubscription = mApi!!.storegoodslist(srt, Page, keyword, supid, sale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<GoodsListBean>() {
                    override fun onNext(t: GoodsListBean?) {
                        Log.d("goodslist", t!!.data.toString())
                        if (t!!.data!!.status == 1 && t!!.data!!.list.size > 0) {
                            mView.getlist_seccess(t!!.data.list, isrefsesh)
                        } else {
                            mView.getlist_error(Page, "暂无数据", isrefsesh)
                        }
                    }

                    override fun onCompleted() {
                        Log.d("goodslist", "Completed")
                    }

                    override fun onError(e: Throwable?) {
                        mView.getlist_error(Page, "暂无数据", isrefsesh)
//                        Log.d("goodslist:error", e.toString())
//                        mView.getdataerror()
                    }
                })

    }
}