package com.hechuang.labeego.persenter.activity

import android.content.Context
import android.util.Log
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.MyOkHttp
import com.hechuang.labeego.base.BasePersenter
import com.hechuang.labeego.bean.LoginBean
import com.hechuang.labeego.bean.Login_Bean
import com.hechuang.labeego.bean.V_bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.view.activity.IMainView
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainPersenter(ItemView: IMainView, context: Context) : BasePersenter<IMainView>(ItemView, context) {
    fun get_v() {
//        Log.d("mainactivity", "get_v")
        mSubscription = mApi!!.getapp_v().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<V_bean>() {
                    override fun onNext(t: V_bean?) {
//                        Log.d("mainactivity", t.toString())
                        mView.getv_o(t!!)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        mView.getv_error(e!!.message.toString())
                        Log.d("mainactivity", e!!.message.toString())

                    }
                })
    }

    fun logind_main(username: String, usertoken: String) {
        Log.d("mainactivity", "$username $usertoken")
//        mSubscription = mApi!!.UserLogin(username, password2).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Subscriber<Login_Bean>() {
//                    override fun onCompleted() {
//                        Log.d("mainactivity_login", "onCompleted: ")
//                    }
//
//                    override fun onError(e: Throwable) {
//                        Log.d("mainactivity_login", "onError: " + e.message.toString())
//                        mView.login_error("")
//                    }
//
//                    override fun onNext(loginBean: Login_Bean) {
//                        Log.d("mainactivity_login", "onNext: " + loginBean.data.toString())
//                        mView.login_ok(loginBean)
//
//                    }
//                })

//        val username: String?
//        val usertoken: String?
//        val sp = context.getSharedPreferences("userInfo", 0)
//        val isoutlogin = sp.getBoolean("islogin", false)
//        if (!isoutlogin) {
//            username = ""
//            usertoken = ""
//        } else {
//            username = sp.getString("username", "")
//            usertoken = sp.getString("token_id", "")
//        }
////        String password2 = MD5Builder.getMD5Str(password);
////        Log.d(TAG, "loginapp: " + username + "  " + usertoken);
//        val editor = sp.edit()
//        if (!username.equals("")) {
            val url = ApiFactify.HOST + "Api/login/automatic_login.php"
            val body = FormBody.Builder().add("userid", username)
                    .add("token", usertoken).build()
        MyOkHttp.getInstance().post(url, body, object : MyOkHttp.RequestCallBack {
            override fun success(data: String?) {
                Log.d("mainactivity", data)
                try {
                    val jsonObject = JSONObject(data)
                    val datas = jsonObject.getJSONObject("data")
                    val status = datas.get("status").toString()
                    val msg = datas.get("msg").toString()
                    if (status == "1") {
                        val listbean = datas.getJSONObject("list")
                        val username = listbean.get("username").toString()
                        val usertype = listbean.get("usertype").toString()
                        val userid = listbean.get("userid").toString()
                        val token = listbean.get("token").toString()
                        val servicefee = listbean.get("servicefee").toString()
                        val url = listbean.get("url").toString()
                        val sessionid = listbean.get("sessionid").toString()
                        val listBean = LoginBean.DataBean.ListBean(userid, username, usertype, servicefee, sessionid, "", url, token)
                        val dataBean = LoginBean.DataBean(listBean, status, msg, "", "")
                        val loginBean = LoginBean(dataBean)

//                        UserData.USERTOKENID = loginBean.getData().getList().getToken()
//                        UserData.USER_ID = loginBean.getData().getList().getUserid()
//                        UserData.USERTYPE = loginBean.getData().getList().getUsertype()
////                        UserData.serviceffe = loginBean.getData().getList().getServicefee()
//                        UserData.USERSESSIONID = loginBean.getData().getList().getSessionid()
//                        UserData.USERNAME = loginBean.getData().getList().getUsername()
//                        UserData.USERSTATUS = loginBean.getData().getList().getNamestatus()
//                        UserData.USERURL = loginBean.getData().getList().getUrl()
//                        editor.putString("token_id", UserData.USERTOKENID)
//                        editor.putString("urserid", UserData.USER_ID)
//                        editor.putString("usertype", UserData.USERTYPE)
//                        editor.putString("name", UserData.USERNAME)
//                        editor.putBoolean("islogin", true)
//                        editor.putBoolean("isforce", true)
//                        editor.apply()
//                        UserData.USERISLOGIN = true
                        mView.login_success(loginBean)
                    } else {
//                        editor.putBoolean("islogin", false)
//                        editor.putBoolean("isforce", false)
//                        UserData.USERISLOGIN = false
                        mView.login_error(msg)
                    }
                } catch (e: JSONException) {
                    mView.login_error(e.message.toString())
                }

            }


            override fun fail(request: Request?, e: java.lang.Exception?) {
                mView.login_error(e!!.message.toString())
            }
        })

    }
}