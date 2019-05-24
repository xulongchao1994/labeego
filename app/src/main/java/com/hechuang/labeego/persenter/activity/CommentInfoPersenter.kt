package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.CommentAddBean
import com.hechuang.labeego.bean.CommentinfoBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.ICommentInfoView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CommentInfoPersenter(itemview: ICommentInfoView, context: Context) : BasePersenter<ICommentInfoView>(itemview, context) {
    /**
     * 获取评论数据
     */
    fun getinfodata(id: String) {
        Log.d("commentinfo",id)
        var body = FormBody.Builder().add("id", id).build()

        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/comment/commentdetails.php", body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
                Log.d("commentinfo", data)
                var clmment_list = arrayListOf<CommentinfoBean.DataBean.ListBean.CommentListBean>()
                var datajsong = JSONObject(data)
                var datasjsong = datajsong.getJSONObject("data")
                var status = datasjsong.get("status").toString()
                var message = datasjsong.get("message").toString()
                if (status.equals("1")) {
                    var listdata = datasjsong.getJSONObject("list")
                    var goodsdata = listdata.getJSONObject("good")
                    var proid = goodsdata.get("proid").toString()
                    var stylename = goodsdata.get("stylename").toString()
                    var Price = goodsdata.get("Price").toString()
                    var ProName = goodsdata.get("ProName").toString()
                    var ProImg = goodsdata.get("ProImg").toString()
                    val integral = goodsdata.get("integral").toString()
                    var comment_headerdata = listdata.getJSONObject("comment_header")
                    var comment = comment_headerdata.get("comment").toString()
                    var truename = comment_headerdata.get("truename").toString()
                    var avatarurl = comment_headerdata.get("avatarurl").toString()
                    var id = comment_headerdata.get("id").toString()
                    var userid = comment_headerdata.get("userid").toString()
                    var time = comment_headerdata.get("time").toString()
                    if (listdata.has("comment_list")) {
                        var comment_lsit = listdata.getJSONArray("comment_list")
                        if (comment_lsit != null && comment_lsit.length() > 0) {
                            for (i in 0 until comment_lsit.length()) {
                                val comment_listitem = comment_lsit.getJSONObject(i)
                                val stype = comment_listitem.get("stype").toString()
                                val p_userid = comment_listitem.get("p_userid").toString()
                                val comment = comment_listitem.get("comment").toString()
                                val truename = comment_listitem.get("truename").toString()
                                val avatarurl = comment_listitem.get("avatarurl").toString()
                                val id = comment_listitem.get("id").toString()
                                val userid = comment_listitem.get("userid").toString()
                                val time = comment_listitem.get("time").toString()
                                val p_name = comment_listitem.get("p_name").toString()
                                val commentlistbean =
                                        CommentinfoBean.DataBean.ListBean.CommentListBean(stype, p_userid, comment, truename, avatarurl, id, userid, time, p_name)
                                clmment_list.add(commentlistbean)
                            }
                        }
                    }
                    var commentheadbean = CommentinfoBean.DataBean.ListBean.CommentHeaderBean(comment, truename, avatarurl, id, userid, time)
                    var commentgoodsbean = CommentinfoBean.DataBean.ListBean.GoodBean(proid, stylename, Price, ProName, ProImg, integral)
                    var commentlisteban = CommentinfoBean.DataBean.ListBean(commentgoodsbean, commentheadbean, clmment_list)
                    var commentdatabean = CommentinfoBean.DataBean(status.toInt(), message, commentlisteban)
                    var commentinfobean = CommentinfoBean(commentdatabean)
                    mView.getdatasuccess(commentinfobean)
                } else {
                    mView.getdataerror(message)
                }
            }

            override fun fail(request: Request?, e: Exception?) {
                mView.getdataerror(e!!.message.toString())
            }
        })
//        mSubscription = mApi!!.getcommentinfodata(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<CommentinfoBean>() {
//                    override fun onNext(t: CommentinfoBean?) {
//                        if (t!!.data.status == 1) {
//                            mView.getdatasuccess(t)
//                        } else {
//                            mView.getdataerror(t.data.message)
//                        }
//                    }
//
//                    override fun onCompleted() {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        mView.getdataerror(e!!.message.toString())
//                    }
//                })
    }

    fun post_zuuiping(proid: String, pid: String, p_userid: String, comment: String) {
        Log.d("commentinfo", "${UserData.USERNAME} ${UserData.USERTOKENID} $proid $pid $p_userid $comment")
        mSubscription = mApi!!.commenginfo_zhuijia(UserData.USERNAME, UserData.USERTOKENID, proid, pid, p_userid, comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CommentAddBean>() {
                    override fun onNext(t: CommentAddBean?) {
                        if (t!!.data.status == 1) {
                            mView.postzhuiping_success(t)
                        } else {
                            mView.postzhuiping_error(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.postzhuiping_error(e!!.message.toString())
                    }
                })
    }

    fun delete_pinjia(id: String) {
        mSubscription = mApi!!.delete_pingjia(UserData.USERNAME, UserData.USERTOKENID, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CommentAddBean>() {
                    override fun onNext(t: CommentAddBean?) {
                        if (t!!.data.status == 1) {
                            mView.delete_success(t.data.message)
                        } else {
                            mView.delete_error(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.delete_error(e!!.message.toString())
                    }
                })
    }
}