package com.hechuang.labeego.server

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.hechuang.labeego.api.Api
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.bean.Login_Bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.tools.Util.MD5Builder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 自动登录
 * Created by Android_xu on 2018/1/9.
 */
class AutoLoginService : IntentService {

    constructor(name: String) : super(name) {}

    constructor() : super("com.hechuang.labeego.service.autologinservice") {}

    /**
     * 首次启动
     *
     * @param intent
     */
    override fun onHandleIntent(intent: Intent?) {
        val username: String?
        val password: String?
        val sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        val isoutlogin = sp.getBoolean("islogin", false)
        Log.d(TAG, "onHandleIntent: " + sp.getBoolean("remeberpsw", false))
        if (!isoutlogin) {
            username = ""
            password = ""
        } else {
            username = sp.getString("username", "")
            password = sp.getString("password", "")
        }
        val password2 = MD5Builder.getMD5Str(password!!)
        val editor = sp.edit()
        mOkHttpClient = getmOkHttpClient(applicationContext)
        val retrofit = Retrofit.Builder().baseUrl(ApiFactify.HOST)
                .client(mOkHttpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        mService = retrofit.create(Api::class.java)
        if (username != "") {
            mService!!.UserLogin(username!!, password2).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<Login_Bean>() {
                        override fun onCompleted() {
                            Log.d(TAG, "onCompleted: ")
                        }

                        override fun onError(e: Throwable) {
                            //                        startActivity(new Intent(LoginService.this, MainActivity.class));
                            editor.putBoolean("islogin", false)
                            editor.putBoolean("isforce", false)
                            UserData.USERISLOGIN = false
                            //                            MyToast.showMsg("网络连接错误，请稍后重新登录");
                        }

                        override fun onNext(loginBean: Login_Bean) {
                            Log.d(TAG, "onNext: " + loginBean.data.toString())
                            if (loginBean.data.status == 1) {
                                UserData.USERTOKENID = loginBean.data.list.token
                                UserData.USER_ID = loginBean.data.list.userid
                                UserData.USERTYPE = loginBean.data.list.usertype
                                UserData.USERSESSIONID = loginBean.data.list.sessionid
                                UserData.USERNAME = loginBean.data.list.username
                                editor.putString("token_id", UserData.USERTOKENID)
                                editor.putString("urserid", UserData.USER_ID)
                                editor.putString("usertype", UserData.USERTYPE)
                                editor.putString("name", UserData.USERNAME)
                                editor.putBoolean("islogin", true)
                                editor.putBoolean("isforce", true)
                                UserData.USERISLOGIN = true
                            } else {
                                editor.putBoolean("islogin", false)
                                editor.putBoolean("isforce", false)
                                UserData.USERISLOGIN = false
                            }
                        }
                    })
        } else {
        }
    }

    /**
     * 再次启动
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val username: String?
        val password: String?
        val sp = getSharedPreferences("USERINFO", 0)
        val isoutlogin = sp.getBoolean("islogin", false)
        if (!isoutlogin) {
            username = ""
            password = ""
        } else {
            username = sp.getString("username", "")
            password = sp.getString("password", "")
        }
        val password2 = MD5Builder.getMD5Str(password!!)
        val editor = sp.edit()
        mOkHttpClient = getmOkHttpClient(applicationContext)
        val retrofit = Retrofit.Builder().baseUrl(ApiFactify.HOST)
                .client(mOkHttpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        mService = retrofit.create(Api::class.java)
        if (username != "") {
            mService!!.UserLogin(username!!, password2).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<Login_Bean>() {
                        override fun onCompleted() {
                        }

                        override fun onError(e: Throwable) {
                        }

                        override fun onNext(loginBean: Login_Bean) {
                            if (loginBean.data.status == 1) {
                                UserData.USERTOKENID = loginBean.data.list.token
                                UserData.USER_ID = loginBean.data.list.userid
                                UserData.USERTYPE = loginBean.data.list.usertype
                                UserData.USERSESSIONID = loginBean.data.list.sessionid
                                UserData.USERNAME = loginBean.data.list.username
                                editor.putString("token_id", UserData.USERTOKENID)
                                editor.putString("urserid", UserData.USER_ID)
                                editor.putString("usertype", UserData.USERTYPE)
                                editor.putString("name", UserData.USERNAME)
                                editor.putBoolean("islogin", true)
                                editor.putBoolean("isforce", true)
                                UserData.USERISLOGIN = true
                            } else {
                                editor.putBoolean("islogin", false)
                                editor.putBoolean("isforce", false)
                                UserData.USERISLOGIN = false
                            }
                        }
                    })
        } else {
        }
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        private var mOkHttpClient: OkHttpClient? = null

        private var mService: Api? = null

        private val TAG = "AutoLoginService"

        private fun getmOkHttpClient(context: Context): OkHttpClient {
            val cache = Cache(File(context.cacheDir, "HttpCache"),
                    (1024 * 1024 * 100).toLong())
            return OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .cache(cache)
                    .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context)))
                    .build()
        }
    }
}
