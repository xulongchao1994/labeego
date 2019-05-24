package com.hechuang.labeego.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hechuang.labeego.R
/**
 * 商品详情fragment弃用
 */
class GoodsInfo_Shouhuo_Fragment : Fragment() {
    var shouhuo_info: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_goodsinfo_shouhuo, container, false)
        initview(view)
        return view
    }


    fun initview(mView: View) {
        var shouhuo_str = arguments!!.getString("shouhuo")
        shouhuo_info = mView.findViewById(R.id.goodsinfo_shouhuo_info)
        shouhuo_info!!.text = shouhuo_str
    }


//    override fun initPersenter() {
//        mPersenter = Goodsinfo_Shouhou_Persenter(this, activity!!)
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