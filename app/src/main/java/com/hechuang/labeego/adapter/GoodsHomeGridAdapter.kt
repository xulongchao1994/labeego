package com.hechuang.labeego.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hechuang.labeego.R
import com.hechuang.labeego.bean.GoodsHomeBannerBean

class GoodsHomeGridAdapter(var context: Context, var imagelist: List<GoodsHomeBannerBean.DataBean.ListBean.IconBean>) : RecyclerView.Adapter<GoodsHomeGridAdapter.GoodsHomeGridViewHOlder>(
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsHomeGridViewHOlder {
        return GoodsHomeGridViewHOlder(LayoutInflater.from(context).inflate(R.layout.adapter_homegoodsgrid, null))
    }

    override fun getItemCount(): Int {
        return imagelist.size
    }

    override fun onBindViewHolder(holder: GoodsHomeGridViewHOlder, position: Int) {
        Glide.with(context).load(imagelist[position].imgs)
                .apply(RequestOptions().override(60, 60).error(R.mipmap.applogo))
                .into(holder.icon!!)
        holder.text!!.text = imagelist[position].name
        holder.itemView.setOnClickListener {
            if (monItemLisener_Grid != null) {
                monItemLisener_Grid!!.OnItemLisner_Grid(position, imagelist[position].categoryid!!, imagelist[position].name!!)
            }
        }
    }

    inner class GoodsHomeGridViewHOlder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView? = null
        var text: TextView? = null

        init {
            icon = itemView.findViewById(R.id.homegoodsgrid_image)
            text = itemView.findViewById(R.id.homegoodsgrid_text)
        }
    }

    interface OnItemLisener_Grid {
        fun OnItemLisner_Grid(position: Int, imagid: String, name: String)
    }

    var monItemLisener_Grid: OnItemLisener_Grid? = null

    fun setOnItemLisener_Grid(onItemLisener_Grid: OnItemLisener_Grid) {
        this.monItemLisener_Grid = onItemLisener_Grid
    }
}