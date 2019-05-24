package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.GoodsHomeBannerBean
import com.hechuang.labeego.bean.GoodsListBean
import com.hechuang.labeego.view.activity.IGoodsHomeView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GoodsHomePersenter(itemView: IGoodsHomeView, context: Context) : BasePersenter<IGoodsHomeView>(itemView, context) {
    /**
     * 获取商品列表
     */
    fun getbanner_imges() {
//        val bannerlist = arrayListOf<GoodsHomeBannerBean.DataBean.ListBean.BannerBean>()
//        val iconlist = arrayListOf<GoodsHomeBannerBean.DataBean.ListBean.IconBean>()
//        var body = FormBody.Builder().build()
        mView.showloading()
//        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/Product/goodsindex.php", body, object : MyOkHttp.RequestCallBack {
//            override fun success(data: String?) {
//                val json = JSONObject(data)
//                val datas = json.getJSONObject("data")
//                val starus = datas.get("status").toString()
//                val msg = datas.get("message").toString()
//                val list = datas.getJSONObject("list")
//                val banner = list.getJSONArray("banner")
//                for (i in 0 until banner.length()) {
//                    var banneritem = banner.getJSONObject(i)
//                    val style = banneritem.get("style").toString()
//                    val url = banneritem.get("url").toString()
//                    val urlid = banneritem.get("urlid").toString()
//                    val imgs = banneritem.get("imgs").toString()
//                    val bannerBean = GoodsHomeBannerBean.DataBean.ListBean.BannerBean(style, url, urlid, imgs)
//                    bannerlist.add(bannerBean)
//                }
//                val icons = list.getJSONArray("icon")
//                for (i in 0 until icons.length()) {
//                    val iconsitem = icons.getJSONObject(i)
//                    val categoryid = iconsitem.get("categoryid").toString()
//                    val name = iconsitem.get("name").toString()
//                    val imgs = iconsitem.get("imgs").toString()
//                    val iconsBean = GoodsHomeBannerBean.DataBean.ListBean.IconBean(categoryid, name, imgs)
//                    iconlist.add(iconsBean)
//                }
//                val listbean = GoodsHomeBannerBean.DataBean.ListBean(bannerlist, iconlist)
//                val databean = GoodsHomeBannerBean.DataBean(starus, msg, listbean)
//                mView.getbanner_imagee_seccess(databean)
////                mView.dismissloading()
//            }
//
//            override fun fail(request: Request?, e: Exception?) {
//                mView.dismissloading()
//            }
//        })

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
                        Log.d("goodshome" + "getlietdata", "onnext" + t!!.data.message + t.data.status)
                        mView.getlistdata_seccess(t!!.data.list, isrefersh)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getdataerror(page, "", isrefersh)
                        Log.d("goodshome:getlietdata", "onerror" + e!!.message)
                    }
                })
    }
}