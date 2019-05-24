package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.ShopInfoBean
import com.hechuang.labeego.persenter.activity.ShopInfoPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RoundCorner
import com.hechuang.labeego.view.activity.IShopinfoView
import kotlinx.android.synthetic.main.activity_shopinfo.*

class ShopInfoActivity : BaseActivity<ShopInfoPersenter>(), IShopinfoView {
    override fun getlisterror(string: String) {
        MyToast.showMsg(string)
    }

    var listdata = arrayListOf<ShopInfoBean.DataBean.ListBean.ProductBean>()
    override fun getlistsuccess(shopinfoListBean: ShopInfoBean, refresh: Boolean) {
        if (page == 1) {
            listdata.clear()
            if (refresh) {
                gridadapter!!.addHeaderView(gettopview(shopinfoListBean))
            }
            listdata.addAll(shopinfoListBean.data!!.list!!.product)
            gridadapter!!.setNewData(listdata)
            shopinfo_swiperefresh.isRefreshing = false
        } else {
            gridadapter!!.loadMoreComplete()
            listdata.addAll(shopinfoListBean.data!!.list!!.product)
            gridadapter!!.addData(shopinfoListBean.data!!.list!!.product)
        }
        if (shopinfoListBean.data.list.product.size < 8) {
            gridadapter!!.loadMoreEnd()
        }
        gridadapter!!.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(this@ShopInfoActivity, WebsActivity::class.java)
            intent.putExtra("web", listdata[position].url)
            startActivity(intent)
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_shopinfo
    }

    var page = 1
    var gridadapter: BaseQuickAdapter<ShopInfoBean.DataBean.ListBean.ProductBean, BaseViewHolder>? = null
    var id = ""
    override fun initView() {
        id = intent.getStringExtra("id")
        shopinfo_back_img.setOnClickListener { finish() }
        shopinfo_back_text.setOnClickListener { finish() }
        var gordlayoutmanager = GridLayoutManager(this, 2)
        shopinfo_recycler.layoutManager = gordlayoutmanager
        gridadapter = object : BaseQuickAdapter<ShopInfoBean.DataBean.ListBean.ProductBean, BaseViewHolder>(R.layout.adapter_shopinfo_item) {
            override fun convert(helper: BaseViewHolder?, item: ShopInfoBean.DataBean.ListBean.ProductBean?) {
                Glide.with(this@ShopInfoActivity).load(item!!.proImg)
                        .apply(RequestOptions.bitmapTransform(RoundCorner(this@ShopInfoActivity, 10f, 10f)))
                        .into(helper!!.getView(R.id.adapter_shopinfo_icon))
                helper.setText(R.id.adapter_shopinfo_name, item.proName)
                        .setText(R.id.adapter_shopinfo_price, item.price)
                        .setText(R.id.adapter_shopinfo_shichangjia, item.marketprice)
                        .setText(R.id.adapter_shopinfo_manjian, item.isshipping)
                        .setText(R.id.adapter_shopinfo_zhekou, item.adbeefee)
                        .setText(R.id.adapter_shopinfo_howpeople, item.proNum)
                        .setGone(R.id.adapter_shopinfo_manjian, item.isshipping != "")
                        .setGone(R.id.adapter_shopinfo_zhekou, item.adbeefee != "")
                        .setGone(R.id.adapter_shopinfo_howpeople, item.proNum != "")
                helper.getView<TextView>(R.id.adapter_shopinfo_shichangjia).paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        shopinfo_recycler.adapter = gridadapter
        gridadapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page++
                mPersenter!!.getlistdata(id, page.toString(), false)
            }
        }, shopinfo_recycler)
        shopinfo_swiperefresh.setOnRefreshListener {
            page = 1
            mPersenter!!.getlistdata(id, page.toString(), false)
        }
        mPersenter!!.getlistdata(id, page.toString(), true)
    }

    override fun initPersenter() {
        mPersenter = ShopInfoPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    fun gettopview(shopinfoListBean: ShopInfoBean): View {
        var view = LayoutInflater.from(this).inflate(R.layout.topview_shopinfo_item, null)
        var icon = view.findViewById<ImageView>(R.id.shopinfo_icon)
        Glide.with(this).load(shopinfoListBean.data.list.imgpath).into(icon)
        var name = view.findViewById<TextView>(R.id.shopinfo_name)
        name.text = shopinfoListBean.data.list.name
        var address = view.findViewById<TextView>(R.id.shopinfo_address)
        address.text = shopinfoListBean.data.list.address
        return view
    }
}