package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.CommentAddBean
import com.hechuang.labeego.bean.Commentbean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.ICommentView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CommentPersenter(itemview: ICommentView, context: Context) : BasePersenter<ICommentView>(itemview, context) {

    fun getgoodsdata(innerorderid: String) {
        Log.d("comment", "${UserData.USERNAME} ${UserData.USERTOKENID} $innerorderid")
        mSubscription = mApi!!.comment_getgoodsdata(UserData.USERNAME, UserData.USERTOKENID, innerorderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Commentbean>() {
                    override fun onNext(t: Commentbean?) {
                        if (t!!.data.status == 1) {
                            for (i in 0 until t!!.data.list.size) {
                                t.data.list[i].isYincang = false
                                t.data.list[i].comment = "5"
                                t.data.list[i].comment_context = ""
                                t.data.list[i].comment_str = "非常好"
                                t.data.list[i].isIsniming = false
                            }
                            mView.getdatasuccess(t)
                        } else {
                            mView.getdataerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getdataerror(e!!.message.toString())
                    }
                })
    }

    fun postcomment(proid: String, grade: String, comment: String, anonymout: String, innerorderid: String) {
        Log.d("comment", "${UserData.USERNAME} ${UserData.USERTOKENID} $proid $grade $comment $anonymout $innerorderid")
        mSubscription = mApi!!.post_comment(UserData.USERNAME, UserData.USERTOKENID, proid, grade, comment, anonymout, innerorderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CommentAddBean>() {
                    override fun onNext(t: CommentAddBean?) {
                        Log.d("comment_onnext", t!!.toString())
                        if (t!!.data.status == 1) {
                            mView.postcomment_success(t!!)
                        } else {
                            mView.postcomment_error(t!!.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("comment_error", e!!.message.toString())
                        mView.postcomment_error(e!!.message.toString())
                    }
                })
    }
}