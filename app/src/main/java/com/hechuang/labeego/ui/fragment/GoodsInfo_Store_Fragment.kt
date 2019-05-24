package com.hechuang.labeego.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import com.hechuang.labeego.R
import com.hechuang.labeego.tools.Util.GlideImageLoader
import com.hechuang.labeego.ui.activity.GoodsInfoActivity
import com.youth.banner.Banner
import com.youth.banner.BannerConfig

/**
 * 商品详情fragment弃用
 */
class GoodsInfo_Store_Fragment : Fragment(), View.OnClickListener {
    var Goodsactivity: GoodsInfoActivity? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.goodsinfo_store_guige_layout -> {//显示activity的弹出框
                Goodsactivity = activity as GoodsInfoActivity
                Goodsactivity!!.showpopup("2")
            }
        }
    }

    var banner: Banner? = null
    var store_name: TextView? = null
    var store_price: TextView? = null
    var store_integral: TextView? = null
    var store_id: TextView? = null
    var store_qidinglaing: TextView? = null
    var store_qidingbeishu: TextView? = null
    var store_storename: TextView? = null
    var guige: TextView? = null
    var guige_layout: LinearLayout? = null
    var store_info: WebView? = null
    //-------------------------------------
    var imgelist = arrayListOf<String>()
    var store_name_str: String? = null
    var store_price_str: String? = null
    var store_integral_str: String? = null
    var store_id_str: String? = null
    var store_qidingliang_str: String? = null
    var store_qidingbeishu_str: String? = null
    var store_storename_str: String? = null
    var store_info_str: String? = null
    var url: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_goodsinfo_store, container, false)
//        initview(view)
        return view
    }

    fun settext(text: String) {
        guige!!.text = text
    }

    var store_integral_layout: LinearLayout? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        banner = view.findViewById(R.id.goodsinfo_store_banner)
        store_name = view.findViewById(R.id.goodsinfo_store_name)
        store_price = view.findViewById(R.id.goodsinfo_store_price)
        store_integral = view.findViewById(R.id.goodsinfo_store_integral)
        store_integral_layout = view.findViewById(R.id.goodsinfo_store_integral_layout)
        store_id = view.findViewById(R.id.goodsinfo_store_id)
        store_qidinglaing = view.findViewById(R.id.goodsinfo_store_qidingliang)
        store_qidingbeishu = view.findViewById(R.id.goodsinfo_store_qidingbeishu)
        store_storename = view.findViewById(R.id.goodsinfo_store_storename)
        guige = view.findViewById(R.id.goodsinfo_store_guige_text)
        guige_layout = view.findViewById(R.id.goodsinfo_store_guige_layout)
        store_info = view.findViewById(R.id.goodsinfo_store_info)
    }

    internal inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view!!.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
//            //这个是一定要加上那个的,配合scrollView和WebView的height=wrap_content属性使用
            val w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED)
            val h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED)
            //重新测量
            store_info!!.measure(w, h)
            super.onPageFinished(view, url)
        }
    }

    override fun onResume() {
        super.onResume()
        imgelist = arguments!!.getStringArrayList("imgagelist")
        store_name_str = arguments!!.getString("store_name")
        store_price_str = arguments!!.getString("store_price")
        store_integral_str = arguments!!.getString("store_integ")
        store_id_str = arguments!!.getString("store_id")
        store_qidingliang_str = arguments!!.getString("store_qidingliang")
        store_qidingbeishu_str = arguments!!.getString("store_qidingbeishu")
        store_storename_str = arguments!!.getString("store_storename")
        store_info_str = arguments!!.getString("store_info")
        banner!!.setImageLoader(GlideImageLoader())
        banner!!.setBannerStyle(BannerConfig.NUM_INDICATOR)
        banner!!.setIndicatorGravity(BannerConfig.RIGHT)
        val display = getResources().displayMetrics
        val params = LinearLayout.LayoutParams(display.widthPixels, display.widthPixels)
        banner!!.layoutParams = params
        banner!!.setImages(imgelist)
        banner!!.isAutoPlay(false)
        banner!!.start()
        store_name!!.text = store_name_str
        store_price!!.text = store_price_str
        if (store_integral_str.equals("")) {
            store_integral_layout!!.visibility = View.GONE
        } else {
            store_integral_layout!!.visibility = View.VISIBLE
        }
        store_integral!!.text = store_integral_str
        store_id!!.text = "ID:$store_id_str"
        store_qidinglaing!!.text = "起订量：$store_qidingliang_str"
        store_qidingbeishu!!.text = "起订倍数:$store_qidingbeishu_str"
        store_storename!!.text = store_storename_str
        url = " <style type=\"text/css\"> img{ max-width: 100%; height: auto; } </style> $store_info_str"
        store_info!!.loadDataWithBaseURL(null, url, "text/html", "utf-8", null)
        val settings = store_info!!.getSettings()
//        settings.useWideViewPort = true
//        settings.loadWithOverviewMode = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.javaScriptEnabled = true
        settings.displayZoomControls = true
        store_info!!.webViewClient = MyWebViewClient()
        guige_layout!!.setOnClickListener(this)
    }

}