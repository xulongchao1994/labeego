package com.hechuang.labeego.persenter.activity


import android.content.Context
import android.util.Log
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.ForceModifyPwdBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IForceModifyPasswordView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Android_xu on 2017/12/4.
 */

class ForceModifyPasswordPersenter(view: IForceModifyPasswordView, context: Context) : BasePersenter<IForceModifyPasswordView>(view, context) {

    fun settwopwd( phone: String, onepwd: String, twopwd: String) {
        mView.showloading()
        Log.d(TAG, "settwopwd: " + UserData.USERNAME + UserData.USERTOKENID + phone  + onepwd + twopwd)
        mSubscription = mApi!!.Post_modifytowpwd(UserData.USERNAME, UserData.USERTOKENID, phone, onepwd, twopwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ForceModifyPwdBean>() {
                    override fun onCompleted() {
                        Log.d(TAG, "onCompleted: ")
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: " + e.message)
                    }

                    override fun onNext(modifyTwoPwd: ForceModifyPwdBean) {
                        Log.d(TAG, "onNext: " + modifyTwoPwd.data.toString())
                        if (mView != null) {
                            mView.PwdSeccess(modifyTwoPwd.data.status, modifyTwoPwd.data.msg)
                        }
                    }
                })
    }

    companion object {

        private val TAG = "ModifyTowPasswordPersen"
    }
}
