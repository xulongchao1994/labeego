package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.*
import com.hechuang.labeego.view.activity.ILoginView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

class LoginPersenter(ItemView: ILoginView, context: Context) : BasePersenter<ILoginView>(ItemView, context) {
    fun getviewdata() {
        mView.showloading()
        mSubscription = mApi!!.loginview()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<LoginviewBean>() {
                    override fun onNext(t: LoginviewBean?) {
                        mView.get_view_success(t!!.data)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.get_view_error(e!!.message.toString())
                    }
                })
    }

    fun login(name: String, psw: String) {
        mView.showloading()
        mSubscription = mApi!!.UserLogin(name, psw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Login_Bean>() {
                    override fun onNext(t: Login_Bean?) {
                        Log.d("login.onnext", t!!.data.toString())
                        if (t!!.data.status == 1) {
                            mView.login_seccess(t)
                        } else if (t.data.status == 2) {
                            mView.login_fmpw(t)
                        } else {
                            mView.login_error(t.data.msg)
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })
    }


    fun getauthcode(mobile: String) {
        mSubscription = mApi!!.post_authCode(mobile).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<AuthCodeBean>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
//                    Log.d("login", "authcode_onError: " + e.message);
                        mView.getautherror(e!!.message.toString())
                    }

                    override fun onNext(authCodeBean: AuthCodeBean) {
//                     Log.d("login", "authcode_onNext: " + authCodeBean.toString());
                        if (mView != null) {
                            if (authCodeBean.getData().getStatus() === 1) {
                                mView.getauthcode(authCodeBean)
                            } else {
                                mView.getautherror(authCodeBean.getData().getMsg())
                            }
                        }
                    }
                })
    }

    fun getuserlist(mobile: String, authcode: String) {
        val body = FormBody.Builder()
                .add("mobile", mobile)
                .add("vcode", authcode)
                .build()
        MyOkHttp.getInstance().post(ApiFactify.HOST + "Api/Home/token.php", body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
//                Log.d("login", "userlist__\n+$data")
                var data = data
                for (i in 0 until data!!.length) {
                    if (data.substring(i, i + 1) == "{") {
                        data = data.substring(i)
                        break
                    }
                }
                //                Log.d(TAG, "getuserlis-success: " + data);
                val listBeans = ArrayList<PhoneLoginBean.DataBean.ListBean>()
                try {
                    val jsonObject = JSONObject(data)
                    val datas = jsonObject.getJSONObject("data")
                    val status = datas.get("status").toString()
                    val msg = datas.get("msg").toString()
                    if (status == "1") {
                        val token = datas.get("token").toString()
                        val listarray = datas.getJSONArray("list")
                        if (listarray != null && listarray.length() > 0) {
                            for (i in 0 until listarray.length()) {
                                val item = listarray.getJSONObject(i)
                                val UserId = item.get("UserId").toString()
                                val TrueName = item.get("TrueName").toString()
                                val AvatarUrl = item.get("AvatarUrl").toString()
                                val listBean = PhoneLoginBean.DataBean.ListBean(UserId, TrueName, AvatarUrl)
                                listBeans.add(listBean)
                            }
                            val dataBean = PhoneLoginBean.DataBean(status, msg, token, listBeans)
                            val loginBean = PhoneLoginBean(dataBean)
                            mView.getphoneuserlist(loginBean)
                        }
                    } else {
                        mView.getphoneuserlisterror(msg)
                    }
                } catch (e: JSONException) {
                    //                    Log.d(TAG, "getuserlis-Exception: " + e.getMessage());
                    mView.getphoneuserlisterror(e.message.toString())
                }
            }

            override fun fail(request: Request?, e: java.lang.Exception?) {
                mView.getphoneuserlisterror(e!!.message.toString())
            }
        })

        //        setMSubscription(getMApi().post_phonelogin(mobile, authcode).subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Subscriber<PhoneLoginBean>() {
        //                    @Override
        //                    public void onCompleted() {
        //
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        Log.d(TAG, "onError: " + e.getMessage());
        //                        if (getMView() != null) {
        //                            getMView().getphoneuserlisterror(e.getMessage());
        //                        }
        //                    }
        //
        //                    @Override
        //                    public void onNext(PhoneLoginBean phoneLoginBean) {
        //                        Log.d(TAG, "onNext: " + phoneLoginBean.toString());
        //                        if (getMView() != null) {
        //                            if (phoneLoginBean.getData().getStatus().equals("1")) {
        //                                getMView().getphoneuserlist(phoneLoginBean);
        //                            } else {
        //                                getMView().getphoneuserlisterror(phoneLoginBean.getData().getMsg());
        //                            }
        //                        }
        //                    }
        //                })
        //        );
    }

    fun getphonelogin(token: String, userid: String) {
        //        Log.d(TAG, "getphonelogin: " + token + "  " + userid);
        //        RequestBody body = new FormBody.Builder()
        //                .add("token", token)
        //                .add("userid", userid)
        //                .build();
        //        MyOkHttp.getInstance().post(ApiFactory.HOST + "Api/Home/logon.php", body, new MyOkHttp.RequestCallBack() {
        //            @Override
        //            public void success(String data) {
        //                Log.d(TAG, "getphonelogin-success: " + data);
        //            }
        //
        //            @Override
        //            public void fail(Request request, Exception e) {
        //                Log.d(TAG, "getphonelogin-fail: " + e.getMessage());
        //            }
        //        }, null);
        mSubscription = mApi!!.post_phonelogin_success(token, userid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<PhoneSuccessBean>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        Log.d("login", "onError: " + e.message.toString())
                        if (mView != null) {
                            mView.getphoneloginerror(e.message.toString())
                        }
                    }

                    override fun onNext(phoneLoginBean: PhoneSuccessBean) {
                        Log.d("login", "onNext: " + phoneLoginBean.toString())
                        if (mView != null) {
                            if (phoneLoginBean.getData().getStatus() === 1) {
                                mView.getphoneloginsuccess(phoneLoginBean)
                            } else {
                                mView.getphoneloginerror(phoneLoginBean.getData().getMsg())
                            }
                        }
                    }
                })
    }
}