package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.AddressInfoBean
import com.hechuang.labeego.bean.Address_morenBean
import com.hechuang.labeego.bean.NewAddress_seccess_bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.INewAddressView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NewAddressPersenter(itemview: INewAddressView, context: Context) : BasePersenter<INewAddressView>(itemview, context) {
    fun potaddress(name: String, mobile: String, province: String, city: String, county: String, address: String, isdef: String) {
        mView.showloading()
        mSubscription = mApi!!.post_address(UserData.USERNAME, UserData.USERTOKENID, name, mobile, province, city, county, address, isdef)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<NewAddress_seccess_bean>() {
                    override fun onNext(t: NewAddress_seccess_bean?) {
                        mView.postaddress_seccess(t!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {

                    }
                })
    }

    fun postbianji(adderssid: String, name: String, mobile: String, province: String, city: String, county: String, address: String, isdef: String) {
        mView.showloading()
        mSubscription = mApi!!.binajiaddress(adderssid, UserData.USERNAME, UserData.USERTOKENID, name, mobile, province, city, county, address, isdef)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Address_morenBean>() {
                    override fun onNext(t: Address_morenBean?) {
                        Log.d("newaddress", t!!.data.toString())
                        mView.bianji_success(t!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("newaddress", e!!.message.toString())
                        mView.bianji_error("修改失败")
                    }
                })
    }

    fun getaddressinfo(addressid: String) {
        mSubscription = mApi!!.getaddressinfo(UserData.USERNAME, UserData.USERTOKENID, addressid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<AddressInfoBean>() {
                    override fun onNext(t: AddressInfoBean?) {
                        mView.getaddressinfo_success(t!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getaddressinfo_error(e!!.message.toString())
                    }
                })
    }
}