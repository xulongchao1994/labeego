package com.hechuang.labeego.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.Log
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
class GoodsLinearAdapter(var mContext: Context, var mListdata: List<GoodsListBean.DataBean.ListBean>) : RecyclerView.Adapter<GoodsLinearAdapter.GoodsLinearViewHolder>() {
    var type: Int? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsLinearViewHolder {
        return GoodsLinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_goodslinener, parent, false))
    }

    override fun getItemCount(): Int {
        return mListdata.size
    }

    override fun onBindViewHolder(holder: GoodsLinearViewHolder, position: Int) {
        Log.d("goodshome", "holder")
        Glide.with(mContext).load(mListdata[position].proImg).apply(RequestOptions().override(100,100).error(R.mipmap.ic_launcher)).into(holder.goodslinener_icon)
        holder.goodslinener_name.text = mListdata[position].proName
        if (mListdata[position].integral == "") {
            holder.goodslinener_integral.visibility = View.GONE
        } else {
            holder.goodslinener_integral.text = mListdata[position].integral
            holder.goodslinener_integral.visibility = View.VISIBLE
        }
        holder.goodslinener_paice.text = mListdata[position].price
        holder.goodslinener_marketpaice.text = mListdata[position].marketprice
        holder.goodslinener_marketpaice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemView.setOnClickListener({
            if (mgriditmelinener != null) {
                mgriditmelinener!!.OnGridItemLinener(position)
            }
        })
    }

    inner class GoodsLinearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var goodslinener_icon: ImageView
        var goodslinener_name: TextView
        var goodslinener_integral: TextView
        var goodslinener_paice: TextView
        var goodslinener_marketpaice: TextView

        init {
            goodslinener_icon = itemView.findViewById(R.id.goodslinener_icon)
            goodslinener_name = itemView.findViewById(R.id.goodslinener_name)
            goodslinener_integral = itemView.findViewById(R.id.goodslinener_integral)
            goodslinener_paice = itemView.findViewById(R.id.goodslinener_peice)
            goodslinener_marketpaice = itemView.findViewById(R.id.goodslinener_marketpeice)
        }
    }

    interface GridItmeLinener {
        fun OnGridItemLinener(position: Int)
    }

    var mgriditmelinener: GridItmeLinener? = null

    fun setOnLinenerItemList(gridItmeLinener: GridItmeLinener) {
        this.mgriditmelinener = gridItmeLinener
    }
}