package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.GoodsInfoBean
import com.hechuang.labeego.bean.Goodsinfo_buy_bean
import com.hechuang.labeego.bean.ShopGoodInfoBean
import com.hechuang.labeego.bean.ShoppingCarNumberBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IGoodsInfoView
import com.hechuang.labeego.view.activity.IShopGoodsInfoView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GoodsInfoPersenter(context: Context, ItemView: IGoodsInfoView) : BasePersenter<IGoodsInfoView>(ItemView, context) {

    fun getgoodsinfodata(pid: String) {
        var body = FormBody.Builder().add("proid", pid).build()
        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/comment/gooddetails.php", body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
//                Log.d("goodsinfo", data)
                var datajson = JSONObject(data)
                var userlsit = arrayListOf<GoodsInfoBean.DataBean.ListBean.UserinfoBean>()
                var imageliset = arrayListOf<String>()
                var goodsstylelist = arrayListOf<GoodsInfoBean.DataBean.ListBean.GoodsstyleBean>()
                var datasjsong = datajson.getJSONObject("data")
                var status = datasjsong.get("status").toString()
                var message = datasjsong.get("message").toString()
                if (status == "1") {
                    var listdata = datasjsong.getJSONObject("list")
                    var proid = listdata.get("ProId").toString()
                    var ProName = listdata.get("ProName").toString()
                    var Unit = listdata.get("Unit").toString()
                    var MinPurchase = listdata.get("MinPurchase").toString()
                    var OrderStep = listdata.get("OrderStep").toString()
                    var ProContent = listdata.get("ProContent").toString()
                    var shouhou = listdata.get("shouhou").toString()
                    var goodsnum = listdata.get("goodsnum").toString()
                    var price = listdata.get("price").toString()
                    var integral = listdata.get("integral").toString()
                    var Supplier = listdata.get("Supplier").toString()
                    var avg = listdata.get("avg").toString()
                    var count = listdata.get("count").toString()
                    var imgarr = listdata.getJSONArray("imgs")
                    if (imgarr != null && imgarr.length() > 0) {
                        for (i in 0 until imgarr.length()) {
                            var strimg = imgarr.get(i).toString()
                            imageliset.add(strimg)
                        }
                    }
                    var goodsstyle = listdata.getJSONArray("goodsstyle")
                    if (goodsstyle != null && goodsstyle.length() > 0) {
                        for (i in 0 until goodsstyle.length()) {
                            var stylejson = goodsstyle.getJSONObject(i)
                            var integral = stylejson.get("integral").toString()
                            var price = stylejson.get("price").toString()
                            var Kucun = stylejson.get("Kucun").toString()
                            var StyleName = stylejson.get("StyleName").toString()
                            var StyleId = stylejson.get("StyleId").toString()
                            var ProImg = stylejson.get("ProImg").toString()
                            var stylebean = GoodsInfoBean.DataBean.ListBean.GoodsstyleBean(Kucun, StyleName, StyleId, ProImg, integral, price)
                            goodsstylelist.add(stylebean)
                        }
                    }
                    var userinfo = listdata.getJSONArray("userinfo")
                    if (userinfo != null && userinfo.length() > 0) {
                        for (i in 0 until userinfo.length()) {
                            var userinfoitem = userinfo.getJSONObject(i)
                            var truename = userinfoitem.get("truename").toString()
                            var avatarurl = userinfoitem.get("avatarurl").toString()
                            var comment = userinfoitem.get("comment").toString()
                            var userbean = GoodsInfoBean.DataBean.ListBean.UserinfoBean(truename, avatarurl, comment)
                            userlsit.add(userbean)
                        }
                    }
                    var listbnean = GoodsInfoBean.DataBean.ListBean(proid, ProName, Unit, MinPurchase, OrderStep, ProContent, shouhou, goodsnum, price
                            , integral, Supplier, imageliset, goodsstylelist, avg, userlsit, count)
                    var databnean = GoodsInfoBean.DataBean(status.toInt(), message, listbnean)
                    var goodsinfobean = GoodsInfoBean(databnean)
                    mView.getgooodsinfo_seccess(goodsinfobean)
                } else {
                    mView.getdoodsinfo_error(message)
                }
            }

            override fun fail(request: Request?, e: java.lang.Exception?) {
            }
        })

//        mSubscription = mApi!!.getshopgoodinfo(pid)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<ShopGoodInfoBean>() {
//                    override fun onNext(t: ShopGoodInfoBean?) {
//                        Log.d("goodsinfo", t!!.data.toString())
//                        if (t!!.data.status == 1) {
//                            mView.getgooodsinfo_seccess(t)
//                        } else {
//                            mView.getdoodsinfo_error(t.data.message)
//                        }
//                    }
//
//                    override fun onCompleted() {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        Log.d("goodsinfo_error", e!!.message.toString())
////                        mView.getdoodsinfo_error("商品状态未知或已下架")
//                    }
//                })
    }

    fun buy(num: String, proid: String, styleid: String, pnum: String) {
//        Log.d("goodsinfo", "$num $proid ${UserData.USERNAME} ${UserData.USERTOKENID} $styleid $pnum")
        var body = FormBody.Builder()
                .add("num", num)
                .add("proid", proid)
                .add("username", UserData.USERNAME)
                .add("token", UserData.USERTOKENID)
                .add("styleid", styleid)
                .add("pnum", pnum)
                .build()
        MyOkHttp.getInstance().post(ApiFactify.HOST + "/Api/ShopCart/index.php?act=index", body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
                Log.d("goodsinfo", data!!)
                var paydata: Goodsinfo_buy_bean? = null
                var jsong = JSONObject(data)
                var datas = jsong.getJSONObject("data")
                val status = datas.get("status").toString()
                val msg = datas.get("msg").toString()
                if (status == "1") {
                    if (num == "2") {
                        var daadfa = Goodsinfo_buy_bean.DataBean.ListBean("")
                        var dddd = Goodsinfo_buy_bean.DataBean(status, msg, daadfa)
                        paydata = Goodsinfo_buy_bean(dddd)
                        mView.buy_seccess("2", paydata)
                    } else if (num == "1") {
                        val list = datas.getJSONObject("list")
                        val shopcartid = list.get("shopcartid").toString()
                        var daadfa = Goodsinfo_buy_bean.DataBean.ListBean(shopcartid)
                        var dddd = Goodsinfo_buy_bean.DataBean(status, msg, daadfa)
                        paydata = Goodsinfo_buy_bean(dddd)
                        mView.buy_seccess("1", paydata)
                    }
                } else {
                    mView.buy_error(msg)
                }
            }

            override fun fail(request: Request?, e: Exception?) {

            }
        })
//        mSubscription = mApi!!.buy(num, proid, UserData.USERNAME, UserData.USERTOKENID, styleid, pnum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<Goodsinfo_buy_bean>() {
//                    override fun onNext(t: Goodsinfo_buy_bean?) {
//                        Log.d("goodsinfo", "${t!!.data!!.msg}  ${t!!.data!!.status}")
//                        if (t!!.data!!.status == "1") {
//                            when (num) {
//                                "1" -> {//立即购买
//                                    mView.buy_seccess("1", t)
//                                }
//                                "2" -> {//加入购物车
//                                    mView.buy_seccess("2", t)
//                                }
//                            }
//                        } else {
//                            if (t!!.data!!.msg == "未登录") {//跳转到登录页面
//                            }
//                            MyToast.showMsg(t!!.data!!.msg!!)
//                        }
//                    }
//
//                    override fun onCompleted() {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        Log.d("goodsinfo", e!!.message)
//                    }
//                })


    }

    fun getshoppingcarnumber() {
        mSubscription = mApi!!.getshoppingcarnumber(UserData.USERNAME, UserData.USERTOKENID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ShoppingCarNumberBean>() {
                    override fun onNext(t: ShoppingCarNumberBean?) {
                        mView.getnumber(t!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })
    }
}