package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.ShoplistBean
import com.hechuang.labeego.persenter.activity.ShopListPersenter
import com.hechuang.labeego.tools.ui.RoundCorner
import com.hechuang.labeego.view.activity.IShopListView
import kotlinx.android.synthetic.main.activity_shoplist.*

class ShopListActivity : BaseActivity<ShopListPersenter>(), IShopListView {
    override fun getlistdataerreo(string: String) {
        if (page == 1) {
            shoplist_refresh.isRefreshing = false
        } else {
            listadapter!!.loadMoreEnd()
        }
    }

    var datalist = arrayListOf<ShoplistBean.DataBean.ListBean>()
    override fun getlistdatasuccess(shoplistBean: ShoplistBean, isheatview: Boolean) {
        if (page == 1) {
            datalist.clear()
            datalist.addAll(shoplistBean.data.list)
            if (isheatview) {
                listadapter!!.setNewData(shoplistBean.data.list)

            }
            shoplist_refresh.isRefreshing = false
            if (shoplistBean.data.list.size < 6) {
                listadapter!!.loadMoreEnd()
            }
        } else {
            datalist.addAll(shoplistBean.data.list)
            listadapter!!.addData(shoplistBean.data.list)
            if (shoplistBean.data.list.size < 6) {
                listadapter!!.loadMoreEnd()
            } else {
                listadapter!!.loadMoreComplete()
            }
        }
        listadapter!!.setOnItemClickListener { adapter, view, position ->
//            var intent = Intent(this@ShopListActivity, ShopGoodsInfoActivity::class.java)
//            intent.putExtra("pid", datalist[position].proId)
//            intent.putExtra("proimg", datalist[position].proImg)
            var intent = Intent(this@ShopListActivity, WebsActivity::class.java)
            intent.putExtra("web", datalist[position].url)
            startActivity(intent)
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_shoplist
    }

    var page = 1
    var listadapter: BaseQuickAdapter<ShoplistBean.DataBean.ListBean, BaseViewHolder>? = null
    var classify = ""
    var seek = ""
    var prefecture = ""
    var classifyname = ""
    override fun initView() {
        if (intent.getStringExtra("classify") != null)
            classify = intent.getStringExtra("classify")
        if (intent.getStringExtra("seek") != null)
            seek = intent.getStringExtra("seek")
        if (intent.getStringExtra("prefecture") != null)
            prefecture = intent.getStringExtra("prefecture")
        if (intent.getStringExtra("classifyname") != null)
            classifyname = intent.getStringExtra("classifyname")
        else
            classifyname = "商品列表"
        shoplist_back.setOnClickListener { finish() }
        shoplist_title.text = classifyname
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        shoplist_recycler.layoutManager = layoutmanager
        listadapter = object : BaseQuickAdapter<ShoplistBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_shoplist_item) {
            override fun convert(helper: BaseViewHolder?, item: ShoplistBean.DataBean.ListBean?) {
                Glide.with(this@ShopListActivity).load(item!!.proImg)
                        .apply(RequestOptions.bitmapTransform(RoundCorner(this@ShopListActivity, 10f, 10f)))
                        .into(helper!!.getView(R.id.adapter_shoplist_icon))
                helper.setText(R.id.adapter_shoplist_name, item.proName)
                        .setText(R.id.adapter_shoplist_price, item.price)
//                        .setText(R.id.adapter_shoplist_shichangjia, item.marketprice)
                        .setText(R.id.adapter_shoplist_manjian, item.isshipping)
                        .setText(R.id.adapter_shoplist_zhekou, item.adbeefee)
                        .setText(R.id.adapter_shoplist_howpeople, item.proNum)
                        .setGone(R.id.adapter_shoplist_manjian, item.isshipping != "")
                        .setGone(R.id.adapter_shoplist_zhekou, item.adbeefee != "")
                        .setGone(R.id.adapter_shoplist_howpeople, item.proNum != "")
                helper.getView<TextView>(R.id.adapter_shoplist_goshop).setOnClickListener {
                    var intent = Intent(this@ShopListActivity, ShopInfoActivity::class.java)
                    intent.putExtra("id", datalist[helper.layoutPosition].supplierId)
                    startActivity(intent)
                }
//                helper.getView<TextView>(R.id.adapter_shopinfo_shichangjia).paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        shoplist_recycler.adapter = listadapter
        listadapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page++
                mPersenter!!.getlist(page.toString(), classify, seek, prefecture, false)
            }
        }, shoplist_recycler)
        shoplist_refresh.setOnRefreshListener {
            page = 1
            mPersenter!!.getlist(page.toString(), classify, seek, prefecture, false)
        }

        mPersenter!!.getlist(page.toString(), classify, seek, prefecture, true)
    }

    override fun initPersenter() {
        mPersenter = ShopListPersenter(this, this)
    }

    override fun showloading() {
    }

    override fun dismissloading() {
    }
}