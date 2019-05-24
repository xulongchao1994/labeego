package com.hechuang.labeego.adapter

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.bean.GoodsListBean

class GoodsHomeAdapter() : BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_goodslinener) {
    override fun convert(helper: BaseViewHolder?, item: GoodsListBean.DataBean.ListBean?) {
        Log.d("goodshome", helper!!.position.toString())
        val img_icon = helper!!.getView<ImageView>(R.id.goodslinener_icon)
        Glide.with(mContext).load(item!!.proImg).apply(RequestOptions().override(100, 100).error(R.mipmap.ic_launcher)).into(img_icon)
        helper.setText(R.id.goodslinener_name, item.proName)
        val integral = helper.getView<TextView>(R.id.goodslinener_integral)
        if (item.integral == "") {
            integral.visibility = View.GONE
        } else {
            integral.visibility = View.VISIBLE
            integral.text = item.integral
        }
        helper.setText(R.id.goodslinener_peice, item.price)
        val marketpaice = helper.getView<TextView>(R.id.goodslinener_marketpeice)
        marketpaice.text = item.marketprice
        marketpaice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}