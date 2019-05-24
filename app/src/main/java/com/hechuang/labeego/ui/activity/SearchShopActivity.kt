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
import com.hechuang.labeego.persenter.activity.SearchShopPersenter
import com.hechuang.labeego.tools.Util.KeyBoardUtils
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RoundCorner
import com.hechuang.labeego.view.activity.ISearchShopView
import kotlinx.android.synthetic.main.activity_searchshop.*
import kotlinx.android.synthetic.main.activity_shoplist.*

class SearchShopActivity : BaseActivity<SearchShopPersenter>(), ISearchShopView {
    var listdata = arrayListOf<ShoplistBean.DataBean.ListBean>()
    override fun getlistdatasuccess(shoplistBean: ShoplistBean) {
        searchshop_edit.setText("")
        if (page == 1) {
            listdata.clear()
            listdata.addAll(shoplistBean.data.list)
            listadapter!!.setNewData(listdata)
            searchshop_Refresh.isRefreshing = false
            if (shoplistBean.data.list.size < 6) {
                listadapter!!.loadMoreEnd()
            }
        } else {
            listdata.addAll(shoplistBean.data.list)
            listadapter!!.addData(shoplistBean.data.list)
            if (shoplistBean.data.list.size < 6) {
                listadapter!!.loadMoreEnd()
            } else {
                listadapter!!.loadMoreComplete()
            }
        }
        listadapter!!.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(this@SearchShopActivity, WebsActivity::class.java)
            intent.putExtra("web", listdata[position].url)
            startActivity(intent)
        }
    }

    override fun getlistdataerreo(string: String) {
        MyToast.showMsg(string)
        searchshop_edit.setText("")
        if (page == 1) {
            searchshop_Refresh.isRefreshing = false
        } else {
            listadapter!!.loadMoreEnd()
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_searchshop
    }

    var sousuo_str = ""
    var page = 1
    var listadapter: BaseQuickAdapter<ShoplistBean.DataBean.ListBean, BaseViewHolder>? = null
    override fun initView() {
        searchshop_back.setOnClickListener { finish() }
        searchshop_edit_img.setOnClickListener {
            KeyBoardUtils.closeKeyBoard(searchshop_edit)
            sousuo_str = searchshop_edit.text.toString()
            if (sousuo_str == "") {
                MyToast.showMsg("请输入搜索内容")
            } else {
                page = 1
                mPersenter!!.getlist(page.toString(), "", sousuo_str, "")
            }
        }
        searchshop_Refresh.setOnRefreshListener {
            page = 1
            mPersenter!!.getlist(page.toString(), "", sousuo_str, "")
        }
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        searchshop_recycler.layoutManager = layoutmanager
        listadapter = object : BaseQuickAdapter<ShoplistBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_shoplist_item) {
            override fun convert(helper: BaseViewHolder?, item: ShoplistBean.DataBean.ListBean?) {
                Glide.with(this@SearchShopActivity).load(item!!.proImg)
                        .apply(RequestOptions.bitmapTransform(RoundCorner(this@SearchShopActivity, 10f, 10f)))
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
//                helper.getView<TextView>(R.id.adapter_shopinfo_shichangjia).paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        searchshop_recycler.adapter = listadapter
        listadapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page++
                mPersenter!!.getlist(page.toString(), "", sousuo_str, "")
            }
        }, searchshop_recycler)
    }

    override fun initPersenter() {
        mPersenter = SearchShopPersenter(this, this)
    }

    override fun showloading() {
    }

    override fun dismissloading() {
    }
}