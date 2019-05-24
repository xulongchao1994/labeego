package com.hechuang.labeego.persenter.fragment

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.Allianceshop_BannerBean
import com.hechuang.labeego.bean.Allianceshop_Classify_ShopBean
import com.hechuang.labeego.bean.Allianceshop_shop_bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.fragment.IAllianceShopFragmentView
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AllianceShopPetenter(itemview: IAllianceShopFragmentView, context: Context) : BasePersenter<IAllianceShopFragmentView>(itemview, context) {

    fun getbannerdata() {
        mSubscription = mApi!!.post_allianceshop_banner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_BannerBean>() {
                    override fun onNext(t: Allianceshop_BannerBean?) {
                        if (t!!.data.status == 1) {
                            mView.getbannersuccess(t)
                        } else {
                            mView.getallianceshoperror(t.data.msg, 1)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getallianceshoperror(e!!.message.toString(), 1)
                    }
                })
    }

    fun getclassify() {
        mSubscription = mApi!!.post_allianceshop_classify().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_BannerBean>() {
                    override fun onNext(t: Allianceshop_BannerBean?) {
                        if (t!!.data.status == 1) {
                            mView.getclassifysuccess(t)
                        } else {
                            mView.getallianceshoperror(t.data.msg, 2)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getallianceshoperror(e!!.message.toString(), 2)
                    }
                })
    }

    fun getguanggao(userid: String, token: String) {
        mSubscription = mApi!!.post_allianceshop_guanggao(userid, token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_BannerBean>() {
                    override fun onNext(t: Allianceshop_BannerBean?) {
                        if (t!!.data.status == 1) {
                            mView.getguanggao(t)
                        } else {
                            mView.getallianceshoperror(t.data.msg, 3)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getallianceshoperror(e!!.message.toString(), 3)
                    }
                })
    }

    fun getscarebuying() {
        mView.getallianceshoperror("", 4)
    }

    fun getsuper() {
        mSubscription = mApi!!.post_allianceshop_super().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_shop_bean>() {
                    override fun onNext(t: Allianceshop_shop_bean?) {
                        if (t!!.data.status == 1) {
                            mView!!.getsuper(t)
                        } else {
                            mView.getallianceshoperror(t.data.msg, 5)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getallianceshoperror(e!!.message.toString(), 5)
                    }
                })
    }

    fun gethigh() {
        mSubscription = mApi!!.post_allianceshop_high().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_shop_bean>() {
                    override fun onNext(t: Allianceshop_shop_bean?) {
                        if (t!!.data.status == 1) {
                            mView!!.gethigh(t)
                        } else {
                            mView.getallianceshoperror(t.data.msg, 6)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getallianceshoperror(e!!.message.toString(), 6)
                    }
                })
    }

    fun getliske() {
        mSubscription = mApi!!.post_allianceshop_like().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_shop_bean>() {
                    override fun onNext(t: Allianceshop_shop_bean?) {
                        if (t!!.data.status == 1) {
                            mView!!.getlike(t)
                        } else {
                            mView.getallianceshoperror(t.data.msg, 7)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getallianceshoperror(e!!.message.toString(), 7)
                    }
                })
    }

    fun getshopclassify() {
        mSubscription = mApi!!.getallianceshopclassify(UserData.USERNAME, UserData.USERTOKENID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Allianceshop_Classify_ShopBean>() {
                    override fun onNext(t: Allianceshop_Classify_ShopBean?) {
                        if (t!!.data.status == 1) {
                            mView.getshopclassify(t)
                        } else {
                            mView.getallianceshoperror(t.data.msg, 8)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getallianceshoperror(e!!.message.toString(), 8)
                    }
                })
    }
}