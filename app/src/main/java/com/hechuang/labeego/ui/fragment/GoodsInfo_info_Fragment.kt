package com.hechuang.labeego.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.hechuang.labeego.R

/**
 * 商品详情fragment弃用
 */
class GoodsInfo_info_Fragment : Fragment() {
    var url: String? = null
    var webView: android.webkit.WebView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_goodsinfo_info, container, false)
        initview(view)
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initview(mView: View) {
        var into = arguments!!.getString("info")
        var id = arguments!!.getString("store_id")
        var danwei = arguments!!.getString("sotre_unit")
        var liang = arguments!!.getString("store_qidingliang")
        var beishu = arguments!!.getString("store_qidingbeishu")
        Log.d("goodsinfo_info", "$id $danwei $liang $beishu")
        url = " <style type=\"text/css\"> img{ max-width: 100%; height: auto; } </style> $into"
        webView = mView.findViewById(R.id.goodsinfo_info_webview)
        webView!!.loadDataWithBaseURL(null, url, "text/html", "utf-8", null)
        val settings = webView!!.getSettings()
//        settings.useWideViewPort = true
//        settings.loadWithOverviewMode = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.javaScriptEnabled = true
//        webView!!.isVerticalScrollBarEnabled = false
//        webView!!.setVerticalScrollbarOverlay(false)
//        webView!!.isHorizontalScrollBarEnabled = false
//        webView!!.setHorizontalScrollbarOverlay(false)
        webView!!.webViewClient = MyWebViewClient()
        val storeid = mView.findViewById<TextView>(R.id.goodsinfo_info_number)
        val storebinama = mView.findViewById<TextView>(R.id.goodsinfo_info_bianma)
        val storedanwei = mView.findViewById<TextView>(R.id.goodsinfo_info_danwei)
        val qidinglaing = mView.findViewById<TextView>(R.id.goodsinfo_info_liang)
        val qidingbeishu = mView.findViewById<TextView>(R.id.goodsinfo_info_qidingbeishu)
        storeid.text = id
        storedanwei.text = danwei
        qidinglaing.text = liang
        qidingbeishu.text = beishu
    }

    internal inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view!!.loadUrl(url)
            return true
        }


    }


//    override fun initPersenter() {
//        mPersenter = Goodsinfo_Info_Persenter(this, activity!!)
//    }
//
//    override fun showloading() {
//    }
//
//    override fun dismissloading() {
//    }
//
//    override fun getdataerror() {
//    }
}

