package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.Address_bean
import com.hechuang.labeego.bean.Address_morenBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IAddressView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AddressPersenter(itemview: IAddressView, context: Context) : BasePersenter<IAddressView>(itemview, context) {

    fun getaddress(page: String, isbinaji: Boolean) {
        mSubscription = mApi!!.getaddress_user(UserData.USERNAME, UserData.USERTOKENID, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Address_bean>() {
                    override fun onNext(t: Address_bean?) {
                        for (i in 0 until t!!.data.list.size) {
                            t.data.list[i].isbianji = isbinaji
                        }
                        mView.getaddress_seccess(t!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })
    }

    /**
     * 设置默认
     */
    fun setmoren(adressid: String, position: Int) {
        mSubscription = mApi!!.setmorenaddress(UserData.USERNAME, UserData.USERTOKENID, adressid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Address_morenBean>() {
                    override fun onNext(t: Address_morenBean?) {
                        if (t!!.data.status == 1) {
                            mView.setaddressmoren_success(t, position)
                        } else {
                            mView.setaddressmoren_error(t.data.msg)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.setaddressmoren_error(e!!.message.toString())
                    }
                })
    }

    /**
     * 删除地址
     */
    fun deleteaddress(addressid: String, position: Int) {
        mSubscription = mApi!!.deleteaddress(UserData.USERNAME, UserData.USERTOKENID, addressid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Address_morenBean>() {
                    override fun onNext(t: Address_morenBean?) {
                        if (t!!.data.status == 1) {
                            mView.deleteaddress_success(t, position)
                        } else {
                            mView.deleteaddress_error(t!!.data.msg)
                        }
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        mView.deleteaddress_error(e!!.message.toString())

                    }
                })
    }
}