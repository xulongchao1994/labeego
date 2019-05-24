package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.Confirmanorder_Shoppingcar_Bean
import com.hechuang.labeego.bean.Order_tijiao
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IConfirmAnOrderView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONObject

class ConfirmAnOrdrePersenter(itemView: IConfirmAnOrderView, context: Context) : BasePersenter<IConfirmAnOrderView>(itemView, context) {
    fun getdata_shoppingar(shoppingcarid: String, type: String) {
        mView.showloading()
        var shopcartid = ""
        var zong_item = ""
        var Agentd = ""
        var SupId = ""
        var message = ""
        var list_message = ""
        var body = FormBody.Builder()
                .add("num", type)
                .add("username", UserData.USERNAME)
                .add("token", UserData.USERTOKENID)
                .add("shopcartid", shoppingcarid)
                .build()
//        Log.d("confirm", "$type ${UserData.USERNAME} ${UserData.USERTOKENID} $shoppingcarid")
        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/Order/orderindex.php?act=orderindex", body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
                Log.d("confirm_index", data!!)
                var json = JSONObject(data)
                var datas = json.getJSONObject("data")
                val status = datas.get("status").toString()
                message = if (!datas.has("message")) {
                    datas.get("message").toString()
                } else {
                    ""
                }
                if (status == "1") {
                    val ReceiveName = datas.get("ReceiveName").toString()
                    val Id = datas.get("Id").toString()
                    val Mobile = datas.get("Mobile").toString()
                    val IsDefault = datas.get("IsDefault").toString()
                    val diqu = datas.get("diqu").toString()
                    val zongjia = datas.get("zongjia").toString()
                    val order_token = datas.get("order_token").toString()
                    val list = datas.getJSONArray("list")
                    var list_list = arrayListOf<Confirmanorder_Shoppingcar_Bean.DataBean.ListBean>()
                    for (i in 0 until list.length()) {
                        var listitem = list.getJSONObject(i)
                        val supname = listitem.get("supname").toString()
                        SupId = if (listitem.has("SupId")) listitem.get("SupId").toString() else ""
                        val zong = listitem.get("zong").toString()
                        list_message = if (listitem.has("message")) listitem.get("message").toString() else ""
                        val products = listitem.getJSONArray("products")
                        var productslist = arrayListOf<Confirmanorder_Shoppingcar_Bean.DataBean.ListBean.ProductsBean>()
                        for (j in 0 until products.length()) {
                            val productsitem = products.getJSONObject(j)
                            shopcartid = if (productsitem.has("var shopcartid")) {
                                productsitem.get("shopcartid").toString()
                            } else {
                                ""
                            }
                            val Supplier = productsitem.get("Supplier").toString()
                            val Pv = productsitem.get("Pv").toString()
                            val ProImg = productsitem.get("ProImg").toString()
                            val ProName = productsitem.get("ProName").toString()
                            val StyleName = productsitem.get("StyleName").toString()
                            val ProId = productsitem.get("ProId").toString()
                            Agentd = if (productsitem.has("Agentd")) {
                                productsitem.get("Agentd").toString()
                            } else {
                                ""
                            }
                            val StyleId = productsitem.get("StyleId").toString()
                            val ProNum = productsitem.get("ProNum").toString()
                            val SupId = productsitem.get("SupId").toString()
                            val money = productsitem.get("money").toString()
                            val ShopPrice = productsitem.get("ShopPrice").toString()
                            zong_item = if (productsitem.has("zong")) {
                                productsitem.get("zong").toString()
                            } else {
                                ""
                            }
                            val productsBean =
                                    Confirmanorder_Shoppingcar_Bean.DataBean.ListBean.ProductsBean(shopcartid,
                                            ProId, ProNum, StyleId, SupId, Pv, ShopPrice, Agentd, Supplier, ProImg, ProName, StyleName, money, zong)
                            productslist.add(productsBean)
                        }
                        var listbean = Confirmanorder_Shoppingcar_Bean.DataBean.ListBean(supname, zong, list_message, SupId, productslist)
                        list_list.add(listbean)
                    }

                    var databean = Confirmanorder_Shoppingcar_Bean.DataBean(status, message, ReceiveName, Id, Mobile, IsDefault, diqu, zongjia, order_token, list_list)
                    mView.shoppingcar_success(databean)
                } else {
//                    mView.getcar_error(message)
                }

            }

            override fun fail(request: Request?, e: Exception?) {
                Log.d("confirm_index", e!!.message)
                mView.dismissloading()
            }
        })
//        mSubscription = mApi!!.confirmanroder_shoppingcar(type, UserData.USERNAME, UserData.USERTOKENID, shoppingcarid)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<Confirmanorder_Shoppingcar_Bean>() {
//                    override fun onNext(t: Confirmanorder_Shoppingcar_Bean?) {
//                        for (i in 0 until t!!.data.list.size) {
//                            t.data.list[i].message = ""
//                        }
//                        Log.e("confirm", t!!.data.toString())
//                        mView.shoppingcar_success(t!!.data)
//                    }
//
//                    override fun onCompleted() {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        Log.e("confirm", e!!.message)
//                    }
//                })
    }

    fun ordr_tijiao(addressid: String, num: String, shopcartid: String, order_token: String, message: String, type: String) {
        mView!!.showloading()
        var body = FormBody.Builder()
                .add("addressid", addressid)
                .add("num", num)
                .add("username", UserData.USERNAME)
                .add("token", UserData.USERTOKENID)
                .add("shopcartid", shopcartid)
                .add("order_token", order_token)
                .add("message", message)
                .add("type", type)
                .build()
        Log.d("confirm_tijiao", "$addressid $num ${UserData.USERNAME} ${UserData.USERTOKENID} $shopcartid $order_token $message $type")
        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/Order/ordercommit.php?act=ordercommit", body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
                Log.d("confirm_tijiao", data!!)
                var json = JSONObject(data)
                var datas = json.getJSONObject("data")
                val status = datas.get("status").toString()
                val message = datas.get("message").toString()
                if (status == "1") {
                    val listarr = datas.getJSONArray("list")
                    var orderidlist = arrayListOf<String>()
                    for (i in 0 until listarr.length()) {
                        val orderid = listarr.get(i).toString()
                        orderidlist.add(orderid)
                    }
                    var datarequst = Order_tijiao.DataBean(status, message, orderidlist)
                    mView.order_tijiao_seccess(datarequst)
                } else {
                    mView.dismissloading()
                    MyToast.showMsg(message)
                }
            }

            override fun fail(request: Request?, e: java.lang.Exception?) {
                mView.dismissloading()
            }
        })
//        mSubscription = mApi!!.confirmanorder_tijia(addressid, num, UserData.USERNAME, UserData.USERTOKENID, shopcartid, order_token, message).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<Order_tijiao>() {
//                    override fun onNext(t: Order_tijiao?) {
//                        Log.d("confirm", t!!.data.toString())
//                        mView.order_tijiao_seccess(t!!.data)
//                    }
//
//                    override fun onCompleted() {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        //Expected BEGIN_ARRAY but was STRING at line 1 column 80 path $.data.list
//                        Log.d("confirm", e!!.message)
//                    }
//                })
//
    }
}