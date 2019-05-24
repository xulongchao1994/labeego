package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.ShoppingCarBean
import com.hechuang.labeego.bean.Shoppingcar_Bianji_bean
import com.hechuang.labeego.bean.Shoppingcar_Guige_bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IShoppingCarView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ShoppingCarPersenter(itemview: IShoppingCarView, context: Context) : BasePersenter<IShoppingCarView>(itemview, context) {
    fun getshoppinglist() {
        Log.d("shopping", "getshoppinglist")
        mView.showloading()
        mSubscription = mApi!!.shoppingcar(UserData.USERNAME, UserData.USERTOKENID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ShoppingCarBean>() {
                    override fun onNext(t: ShoppingCarBean?) {
                        Log.d("shopping", t!!.toString())
                        var name = ""
                        var shoppinglistitmearr = arrayListOf<ShoppingCarBean.DataBean.ListBean.ProductBean>()
                        for (i in 0 until t!!.data.list.size) {
                            name = t.data.list[i].name
                            for (j in 0 until t.data.list[i].product.size) {
                                t.data.list[i].product[j].isshowtitle = j == 0
                                t.data.list[i].product[j].storename = name
                                t.data.list[i].product[j].ischeck = false
                                t.data.list[i].product[j].isstorecheck = false
                                t.data.list[i].product[j].isbianji = false
                                shoppinglistitmearr.add(t.data.list[i].product[j])
                            }
                        }
                        mView.getshoppingdatascesses(shoppinglistitmearr)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        var shoppinglistitmearr = arrayListOf<ShoppingCarBean.DataBean.ListBean.ProductBean>()
                        mView.getcar_error("", shoppinglistitmearr)
                        Log.d("shopping", e!!.message)
                    }
                })
//        var msg = ""
//        var body = FormBody.Builder()
//                .add("username", UserData.USERNAME)
//                .add("token", UserData.USERTOKENID)
//                .build()
//        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/ShopCart/shopcartlist.php?act=shopcartlist", body, object : MyOkHttp.RequestCallBack {
//            override fun success(data: String?) {
//                Log.d("shoppingcar", data)
//                var shoppinglistitmearr = arrayListOf<ShoppingCarBean.DataBean.ListBean.ProductBean>()
//                var name: String
//                var fastjson = JSONObject(data)
//                var datas = fastjson.getJSONObject("data")
//                var status = datas.get("status").toString()
//
//                if (status == "1") {
//                    var msg = datas.get("message").toString()
//                    var list = datas.getJSONArray("list")
//                    for (i in 0 until list.length()) {
//                        val listitem = list.getJSONObject(i)
//                        name = listitem.get("name").toString()
//                        val product = listitem.getJSONArray("product")
//                        for (j in 0 until product.length()) {
//                            val productitme = product.getJSONObject(j)
//                            val shopcartid = productitme.get("shopcartid").toString()
//                            val ProId = productitme.get("ProId").toString()
//                            val ProNum = productitme.get("ProNum").toString()
//                            val StyleId = productitme.get("StyleId").toString()
//                            val SupId = productitme.get("SupId").toString()
//                            val Pv = productitme.get("Pv").toString()
//                            val ShopPrice = productitme.get("ShopPrice").toString()
//                            val ProName = productitme.get("ProName").toString()
//                            val MinPurchase = productitme.get("MinPurchase").toString()
//                            val OrderStep = productitme.get("OrderStep").toString()
//                            val ProImg = productitme.get("ProImg").toString()
//                            val StyleName = productitme.get("StyleName").toString()
//                            val Kucun = productitme.get("Kucun").toString()
//                            val Supplier = productitme.get("Supplier").toString()
//                            val money = productitme.get("money").toString()
//                            val storename = name
//                            val isshowtitle: Boolean = j == 0
//                            val shoppinglistitembean = ShoppingCarBean.DataBean.ListBean.ProductBean(
//                                    shopcartid, ProId, ProNum, StyleId, SupId, Pv, ShopPrice, ProName, MinPurchase, OrderStep, ProImg, StyleName, Kucun, Supplier, money
//                                    , storename, isshowtitle, false, false, false)
//                            shoppinglistitmearr.add(shoppinglistitembean)
//                        }
//                    }
//                    mView.getshoppingdatascesses(shoppinglistitmearr)
//                } else {
//                    msg = datas.get("msg").toString()
//                    mView.getdataerror()
//                }
//            }
//
//            override fun fail(request: Request?, e: Exception?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
    }

    fun getguige(pid: String, position: Int) {
        Log.d("shoppingcar", "$pid $position")
        mSubscription = mApi!!.shoppingcar_guige(UserData.USERNAME, UserData.USERTOKENID, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Shoppingcar_Guige_bean>() {
                    override fun onNext(t: Shoppingcar_Guige_bean?) {
                        mView.getshoppingguigesessess(t!!, position)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("shoppingcar:onerror", e!!.message)
                    }
                })
    }

    fun bianji(number: String, pronum: String, styid: String, shoppingcarid: String, delied: String, price: String, isdelied: Boolean, position: Int) {
        Log.d("shopping", "$pronum $styid $shoppingcarid $delied $price $isdelied")
        mSubscription = mApi!!.bianji(UserData.USERNAME, UserData.USERTOKENID, pronum, styid, shoppingcarid, delied, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Shoppingcar_Bianji_bean>() {
                    override fun onNext(t: Shoppingcar_Bianji_bean?) {
                        Log.d("shopping", t!!.data.toString())
                        if (t!!.data.status == 1) {
                            if (isdelied) {
                                mView.delied_sessess(t.data.message)
                            } else {
                                mView.xiugai_sessess(number, pronum, t, position)
                            }
                        } else {
                            mView.xiggai_error(number, pronum, t, position)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })

    }
}