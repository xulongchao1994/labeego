package com.hechuang.labeego.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hechuang.labeego.R
import com.hechuang.labeego.bean.GoodsListBean

/**
 * 商品列表单排显示
 */
class GoodsGridAdapter(var mContext: Context, var mListdata: List<GoodsListBean.DataBean.ListBean>) : RecyclerView.Adapter<GoodsGridAdapter.GoodsGridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsGridViewHolder {
        return GoodsGridViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_goodsgrid, parent, false))
    }

    override fun getItemCount(): Int {
        return mListdata.size
    }

    override fun onBindViewHolder(holder: GoodsGridViewHolder, position: Int) {
        Glide.with(mContext).load(mListdata[position].proImg).apply(RequestOptions().override(100, 100)).into(holder.goodsgrid_icon)
        holder.goodsgrid_name.text = mListdata[position].proName
        if (mListdata[position].integral == "") {
            holder.goodsgrid_integral.visibility = View.GONE
        } else {
            holder.goodsgrid_integral.text = mListdata[position].integral
            holder.goodsgrid_integral.visibility = View.VISIBLE
        }
        holder.goodsgrid_paice.text = mListdata[position].price
        holder.goodsgrid_marketpaice.text = mListdata[position].marketprice
        holder.goodsgrid_marketpaice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    inner class GoodsGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var goodsgrid_icon: ImageView
        var goodsgrid_name: TextView
        var goodsgrid_integral: TextView
        var goodsgrid_paice: TextView
        var goodsgrid_marketpaice: TextView

        init {
            goodsgrid_icon = itemView.findViewById(R.id.goodsgrid_icon)
            goodsgrid_name = itemView.findViewById(R.id.goodsgrid_name)
            goodsgrid_integral = itemView.findViewById(R.id.goodsgrid_integral)
            goodsgrid_paice = itemView.findViewById(R.id.goodsgrid_peice)
            goodsgrid_marketpaice = itemView.findViewById(R.id.goodsgrid_marketpeice)
        }
    }

    interface GridLinener {
        fun OnGridLiener(position: Int)
    }

    var mGridLinener: GridLinener? = null
    fun setOnGridLinener(gridLinener: GridLinener) {
        this.mGridLinener = gridLinener
    }
}