package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.UserInfoBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IUserView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UserPeersenter(itemview: IUserView, context: Context) : BasePersenter<IUserView>(itemview, context) {
    fun getuserinfodata() {
        mSubscription = mApi!!.getuserinfo(UserData.USERNAME, UserData.USERTOKENID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<UserInfoBean>() {
                    override fun onNext(t: UserInfoBean?) {
                        if (t!!.data.status == 1) {
                            mView.getuserinfodatasuccess(t)
                        } else {
                            mView.getuserdatainfoerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getuserdatainfoerror(e!!.message.toString())
                    }
                })
    }
}