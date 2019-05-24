package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.*
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IGoodsInfoView
import com.hechuang.labeego.view.activity.IShopGoodsInfoView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ShopGoodsInfoPersenter(context: Context, ItemView: IShopGoodsInfoView) : BasePersenter<IShopGoodsInfoView>(ItemView, context) {

    fun getgoodsinfodata(pid: String) {
        Log.d("goodsinfo", "$pid")
        mSubscription = mApi!!.getshopgoodinfo(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ShopGoodInfoBean>() {
                    override fun onNext(t: ShopGoodInfoBean?) {
                        Log.d("shopgoodsinfo", t!!.data.toString())
                        if (t!!.data.status == 1) {
                            mView.getgooodsinfo_seccess(t)
                        } else {
                            mView.getdoodsinfo_error(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("shopgoodsinfo_error", e!!.message.toString())
//                        mView.getdoodsinfo_error("商品状态未知或已下架")
                    }
                })
    }

    fun buy(num: String, proid: String, styleid: String, pnum: String) {
        Log.d("goodsinfo_addshopping", "$proid ${UserData.USERNAME} ${UserData.USERTOKENID} $styleid $num $pnum")
        mSubscription = mApi!!.shopgoodsinfo_addshopping(proid, UserData.USERNAME, UserData.USERTOKENID, styleid, num, pnum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StoreGoodsinfo_buy_bean>() {
                    override fun onNext(t: StoreGoodsinfo_buy_bean?) {
                        Log.d("goodsinfo_addshopping", "data \n ${t!!.data!!.msg}  ${t!!.data!!.status}")
                        if (t!!.data!!.status == "1") {
                            when (num) {
                                "1" -> {//立即购买
                                    mView.buy_seccess("1", t)
                                }
                                "2" -> {//加入购物车
                                    mView.buy_seccess("2", t)
                                }
                            }
                        } else {
                            if (t!!.data!!.msg == "未登录") {//跳转到登录页面
                            }
                            MyToast.showMsg(t!!.data!!.msg!!)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("goodsinfo_addshopping", "error" + e!!.message)
                    }
                })


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