package com.hechuang.labeego.persenter.activity

import android.content.Context
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.SetNameBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.ISetNameView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SetNamePersenter(itemview: ISetNameView, context: Context) : BasePersenter<ISetNameView>(itemview, context) {
    fun setname(name: String) {
        mSubscription = mApi!!.setnamepost(UserData.USERNAME, UserData.USERTOKENID, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<SetNameBean>() {
                    override fun onNext(t: SetNameBean?) {
                        if (t!!.data.status == 1) {
                            mView.setnamesuccess(t.data.message)
                        } else {
                            mView.setnameerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.setnameerror(e!!.message.toString())
                    }
                })
    }
}