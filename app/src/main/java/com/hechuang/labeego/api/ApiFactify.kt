@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.hechuang.labeego.api

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiFactify {
    @JvmField
//    val HOST = "http://lafeng.99xyg.com/"
    val HOST = "http://lafeng.hetianpay.com/"
//    val HOST = "http://www.labeego.com/"
//val HOST = "http://192.168.10.219:8066/"
    @JvmField
    val WEB_HOST = "http://lafeng.hetianpay.com/"
//    val WEB_HOST = "http://lafeng.99xyg.com/"
    @JvmField
    val HTF_HOST = "http://www.hetianpay.com/"
//    val HTF_HOST = "http://htf.99xyg.com/"
//    val HOST = "http://192.168.10.219:8060/"
    internal var mService: Api? = null
    internal var mOkHttpClient: OkHttpClient? = null

    fun getInstance(context: Context): Api? {
        if (mService == null) {
            createRetrofit(context)
        }
        return mService
    }

    private fun createRetrofit(mContext: Context) {
        mOkHttpClient = getOkHttpClient(mContext)
        val retrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HOST)
                .build()
        mService = retrofit.create(Api::class.java)
    }

    @JvmStatic
    fun getOkHttpClient(context: Context): OkHttpClient {
        val cache = Cache(File(context.cacheDir, "HttpCache"),
                (1024 * 1024 * 24).toLong())
        return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
//                .cache(cache)//开启缓存,还需要添加请求拦截器来配合,以达到网络缓存的作用
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context)))
                .build()
    }
}