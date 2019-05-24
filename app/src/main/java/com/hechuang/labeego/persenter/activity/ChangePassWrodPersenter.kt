package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.SetNameBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IChangePswView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ChangePassWrodPersenter(itemview: IChangePswView, context: Context) : BasePersenter<IChangePswView>(itemview, context) {

    fun setpsw(pwd: String, new_pwd: String, new_repwd: String) {
        mView.showloading()
        mSubscription = mApi!!.setpsw(UserData.USERNAME, UserData.USERTOKENID, pwd, new_pwd, new_repwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<SetNameBean>() {
                    override fun onNext(t: SetNameBean?) {
                        Log.d("changepass", t!!.toString())
                        if (t!!.data.status == 1) {
                            mView.setpswsuccess(t.data.message)
                        } else {
                            mView.setpswerror(t.data.message)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.setpswerror(e!!.message.toString())
                    }
                })
    }
}