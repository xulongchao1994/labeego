package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.CommentListBean
import com.hechuang.labeego.view.activity.ICommentListView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CommentListPersenter(itemview: ICommentListView, context: Context) : BasePersenter<ICommentListView>(itemview, context) {

    fun getlistdata(proid: String, page: String, isrefresh: Boolean) {
        Log.d("commentlist", "$proid $page")
        mSubscription = mApi!!.getcommentlistdata(proid, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CommentListBean>() {
                    override fun onNext(t: CommentListBean?) {
                        if (t!!.data.status == 1) {
                            mView.getdata_sccuss(t.data.list, isrefresh)
                        } else {
                            mView.getdata_error(t.data.message)
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