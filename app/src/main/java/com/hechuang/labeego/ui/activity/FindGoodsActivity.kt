package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.graphics.Paint
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.GoodsListBean
import com.hechuang.labeego.persenter.activity.FindGoodsPersenter
import com.hechuang.labeego.view.activity.IFindGoodsView
import kotlinx.android.synthetic.main.activity_findgoods.*

class FindGoodsActivity : BaseActivity<FindGoodsPersenter>(), IFindGoodsView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        page = 1
        mPersenter!!.getlietdata(page.toString(), key_str, true)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.findgoods_back -> finish()
            R.id.findgoods_edit_img -> {
                key_str = findgoods_edit.text.toString()
                page = 1
                mPersenter!!.getlietdata(page.toString(), key_str, true)
            }
        }
    }

    var key_str = ""
    var page: Int? = null
    var goodshomeadapter: BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>? = null
    var goodsdata = arrayListOf<GoodsListBean.DataBean.ListBean>()
    override fun getgoodsdata(goodslistbean: List<GoodsListBean.DataBean.ListBean>, isshuaxin: Boolean) {
        if (isshuaxin) {
            goodsdata.clear()
        }
        goodsdata.addAll(goodslistbean)
        if (page == 1) {
            goodshomeadapter!!.setNewData(goodslistbean)
            findgoods_recycler.adapter = goodshomeadapter
            if (!isshuaxin) {
                goodshomeadapter!!.loadMoreComplete()
            } else {
                findgoods_refress.isRefreshing = false
            }
        } else {
            goodshomeadapter!!.addData(goodslistbean)
            goodshomeadapter!!.loadMoreComplete()
        }
        if (goodslistbean.size >= 9) {
            goodshomeadapter!!.setEnableLoadMore(true)
        } else {
            goodshomeadapter!!.setEnableLoadMore(false)
        }
        dismissloading()
    }

    override fun initlayout(): Int {
        return R.layout.activity_findgoods
    }

    override fun initView() {
        page = 1
        key_str = ""
        val linearmanager = LinearLayoutManager(this)
        linearmanager.orientation = OrientationHelper.VERTICAL
        findgoods_recycler.layoutManager = linearmanager
        findgoods_edit_img.setOnClickListener(this)
        findgoods_back.setOnClickListener(this)
        findgoods_refress.setOnRefreshListener(this)
        findgoods_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                key_str = findgoods_edit.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        findgoods_edit.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                if (findgoods_edit.text.toString() != "") {
                    key_str = findgoods_edit.text.toString()
                    page = 1
                    mPersenter!!.getlietdata(page.toString(), key_str, true)
                    return@OnEditorActionListener true
                }
            }
            false
        })
        goodshomeadapter = goodsadapter()
        goodshomeadapter!!.setOnItemClickListener { adapter, view, position ->
            var ini = Intent(this@FindGoodsActivity, GoodsInfoActivity::class.java)
            ini.putExtra("proimg", goodsdata[position].proImg)
            ini.putExtra("pid", goodsdata[position].proId)
            startActivity(ini)
        }
        goodshomeadapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page = page!! + 1
//                Log.d("goodshome", page.toString())
                mPersenter!!.getlietdata(page.toString(), key_str, false)
            }
        }, findgoods_recycler)
        goodshomeadapter!!.disableLoadMoreIfNotFullPage(findgoods_recycler)
        mPersenter!!.getlietdata(page!!.toString(), key_str, false)
    }

    override fun initPersenter() {
        mPersenter = FindGoodsPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    /**
     * type: 1 linener  2 grid
     */
    fun goodsadapter(): BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>? {
        var goodshome_adapter = object : BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_goodslinener) {
            override fun convert(helper: BaseViewHolder?, item: GoodsListBean.DataBean.ListBean?) {
                var img_icon = helper!!.getView<ImageView>(R.id.goodslinener_icon)
                Glide.with(mContext).load(item!!.proImg).apply(RequestOptions().override(250,250).error(R.mipmap.applogo)).into(img_icon)
                helper.setText(R.id.goodslinener_name, item.proName)
                        .setText(R.id.goodslinener_yishou, item.yishou)
                        .setText(R.id.goodslinener_peice, item.price)
                        .setText(R.id.goodslinener_integral, item.integral)
//                if (item.integral == "") {
                    helper.setGone(R.id.goodslinener_integral_layout, false)
//                } else {
//                    helper.setGone(R.id.goodslinener_integral_layout, true)
//                }
                val marketpaice = helper.getView<TextView>(R.id.goodslinener_marketpeice)
                marketpaice.text = item.marketprice
                marketpaice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        return goodshome_adapter

    }
}