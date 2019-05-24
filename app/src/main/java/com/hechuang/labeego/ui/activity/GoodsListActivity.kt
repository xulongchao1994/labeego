package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.GoodsListBean
import com.hechuang.labeego.persenter.activity.GoodsListPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IGoodsListView
import kotlinx.android.synthetic.main.activity_goodslist.*


class GoodsListActivity : BaseActivity<GoodsListPersenter>(), IGoodsListView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    //    var supid: String? = null
//    var s_type: String? = null
    override fun getlist_error(page: String, msg: String, isrefsesh: Boolean) {
        if (page.toInt() > 1) {
            if (!list_change!!) {
                linener_adapter!!.loadMoreEnd()
            } else {
                grid_adapter!!.loadMoreEnd()
            }
        } else {
            if (isrefsesh) {
                goodslist_refresh.isRefreshing = false
            } else {
                if (!list_change!!) {
                    linener_adapter!!.loadMoreEnd()
                } else {
                    grid_adapter!!.loadMoreEnd()
                }
                MyToast.showMsg(msg)
            }
        }
        dismissloading()
    }

    var type_store: String? = null
    override fun onRefresh() {
        page = 1
//        if (s_type.equals("0"))//商家
//            mPersenter!!.getstorelistdata(str!!, page.toString(), keyword!!, true, supid!!)
//        else
        mPersenter!!.getlistdata(str!!, page.toString(), keyword!!, type_store!!, true)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.goodslist_back -> finish()
            R.id.goodslist_paixu_layout -> {//排序按钮
                Glide.with(this@GoodsListActivity).load(R.drawable.top_g).into(paicu_img!!)
                showpopup()
            }
            R.id.goodslist_edit -> {
                if (popupWindow != null && popupWindow!!.isShowing)
                    popupWindow!!.dismiss()
                startActivity(Intent(this@GoodsListActivity, FindGoodsActivity::class.java))
            }
            R.id.goodslist_edit_img -> {//开始搜索
                if (popupWindow != null && popupWindow!!.isShowing)
                    popupWindow!!.dismiss()
                startActivity(Intent(this@GoodsListActivity, FindGoodsActivity::class.java))
            }
            R.id.goodslist_zonghe -> {//综合
                if (popupWindow != null && popupWindow!!.isShowing)
                    popupWindow!!.dismiss()
                str = ""
                page = 1
//                if (s_type.equals("0"))//商家
//                    mPersenter!!.getstorelistdata(str!!, page.toString(), keyword!!, true, supid!!)
//                else
                mPersenter!!.getlistdata(str!!, page.toString(), keyword!!, type_store!!, true)
            }
            R.id.goodslist_gaodi -> {//从高到低
                if (popupWindow != null && popupWindow!!.isShowing)
                    popupWindow!!.dismiss()
                str = "1"
                page = 1
//                if (s_type.equals("0"))//商家
//                    mPersenter!!.getstorelistdata(str!!, page.toString(), keyword!!, true, supid!!)
//                else
                mPersenter!!.getlistdata(str!!, page.toString(), keyword!!, type_store!!, true)

            }
            R.id.goodslist_digao -> {//从低到高
                if (popupWindow != null && popupWindow!!.isShowing)
                    popupWindow!!.dismiss()
                str = "2"
                page = 1
//                if (s_type.equals("0"))//商家
//                    mPersenter!!.getstorelistdata(str!!, page.toString(), keyword!!, true, supid!!)
//                else
                mPersenter!!.getlistdata(str!!, page.toString(), keyword!!, type_store!!, true)
            }
        }
    }

    var popupWindow: PopupWindow? = null
    private fun showpopup() {
        popupWindow = PopupWindow(this)
        var view = LayoutInflater.from(this).inflate(R.layout.view_googslist_popup, null)
        var zonghe = view.findViewById<View>(R.id.goodslist_zonghe) as TextView
        var gaodi = view.findViewById<View>(R.id.goodslist_gaodi) as TextView
        var digao = view.findViewById<View>(R.id.goodslist_digao) as TextView
        zonghe.setOnClickListener(this)
        gaodi.setOnClickListener(this)
        digao.setOnClickListener(this)
        popupWindow!!.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.contentView = view
        popupWindow!!.isFocusable = true
        popupWindow!!.isTouchable = true
        popupWindow!!.isSplitTouchEnabled = true
        popupWindow!!.setBackgroundDrawable(ColorDrawable(0))
        popupWindow!!.setOnDismissListener {
            Glide.with(this@GoodsListActivity).load(R.drawable.down_g).into(paicu_img!!)
        }
        popupWindow!!.showAsDropDown(paixu)
    }

    override fun getlist_seccess(mGoodsList: List<GoodsListBean.DataBean.ListBean>, isrefsesh: Boolean) {
        if (isrefsesh) {
            goodslist.clear()
        }
        goodslist.addAll(mGoodsList)
        if (!list_change!!) {
            if (linener_adapter == null) {
                linener_adapter = gridadapter(1)
                goodslist_xrectcyler.adapter = linener_adapter
                linener_adapter!!.setNewData(mGoodsList)
            } else {
                if (isrefsesh) {
                    goodslist_refresh.isRefreshing = false
                    linener_adapter!!.setNewData(mGoodsList)
                } else {
                    linener_adapter!!.loadMoreComplete()
                    linener_adapter!!.addData(mGoodsList)
                }
            }

            if (mGoodsList != null && mGoodsList.size < 9) {
                linener_adapter!!.setEnableLoadMore(false)
            } else {
                linener_adapter!!.setEnableLoadMore(true)
            }
        } else {
            if (grid_adapter == null) {
                grid_adapter = gridadapter(2)
                goodslist_xrectcyler.adapter = grid_adapter
                grid_adapter!!.setNewData(mGoodsList)
            } else {
                if (isrefsesh) {
                    goodslist_refresh.isRefreshing = false
                    grid_adapter!!.setNewData(mGoodsList)
                } else {
                    grid_adapter!!.addData(mGoodsList)
                    grid_adapter!!.loadMoreComplete()
                }
            }
            if (mGoodsList != null && mGoodsList.size < 5) {
                grid_adapter!!.setEnableLoadMore(false)
            } else {
                grid_adapter!!.setEnableLoadMore(true)
            }
        }
        dismissloading()
    }

    /**
     * type: 1 linener  2 grid
     */
    fun gridadapter(type: Int): BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>? {
        if (type == 1) {
            linener_adapter = object : BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_goodslinener) {
                override fun convert(helper: BaseViewHolder?, item: GoodsListBean.DataBean.ListBean?) {
                    var img_icon = helper!!.getView<ImageView>(R.id.goodslinener_icon)
                    Glide.with(mContext).load(item!!.proImg)
                            .apply(RequestOptions().override(300,300).error(R.mipmap.ic_launcher))
                            .into(img_icon)
                    if (item.integral == "") {
                        helper.setGone(R.id.goodslinener_integral_layout, false)
                    } else {
                        helper.setGone(R.id.goodslinener_integral_layout, true)
                    }
                    Log.d("goodslist", item.integral)
                    helper.setText(R.id.goodslinener_peice, item.price)
                            .setText(R.id.goodslinener_integral, item.integral)
                            .setText(R.id.goodslinener_name, item.proName)
                            .setText(R.id.goodslinener_yishou, item.yishou)
                    val marketpaice = helper.getView<TextView>(R.id.goodslinener_marketpeice)
                    marketpaice.text = item.marketprice
                    marketpaice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }
            linener_adapter!!.setOnItemClickListener { _, _, position ->
                var intent = Intent(this@GoodsListActivity, GoodsInfoActivity::class.java)
                intent.putExtra("proimg", goodslist[position].proImg)
                intent.putExtra("pid", goodslist[position].proId)
                startActivity(intent)
            }
            linener_adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
                override fun onLoadMoreRequested() {
                    page = page!! + 1
//                    Log.d("goodslist","$page")
//                    if (s_type.equals("0"))//商家
//                        mPersenter!!.getstorelistdata(str!!, page.toString(), keyword!!, false, supid!!)
//                    else
                    mPersenter!!.getlistdata(str!!, page.toString(), keyword!!, type_store!!, false)
//                    linener_adapter!!.loadMoreComplete()
                }
            }, goodslist_xrectcyler)
            linener_adapter!!.disableLoadMoreIfNotFullPage(goodslist_xrectcyler)
            return linener_adapter
        } else {
            grid_adapter = object : BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_goodsgrid) {
                override fun convert(helper: BaseViewHolder?, item: GoodsListBean.DataBean.ListBean?) {
                    val img_icon = helper!!.getView<ImageView>(R.id.goodsgrid_icon)
                    Glide.with(mContext).load(item!!.proImg)
                            .apply(RequestOptions().override(300,300))
                          .into(img_icon)
                    helper.setText(R.id.goodsgrid_name, item.proName)
                    val integral = helper.getView<LinearLayout>(R.id.goodsgrid_integral_layout)
                    if (item.integral == "") {
                        integral.visibility = View.GONE
                    } else {
                        integral.visibility = View.VISIBLE
                    }
                    helper.setText(R.id.goodsgrid_peice, item.price)
                            .setText(R.id.goodsgrid_yishou, item.yishou)
                            .setText(R.id.goodsgrid_integral, item.integral)
                    val marketpaice = helper.getView<TextView>(R.id.goodsgrid_marketpeice)
                    marketpaice.text = item.marketprice
                    marketpaice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }
            grid_adapter!!.setOnItemClickListener { adapter, view, position ->
                var intent = Intent(this@GoodsListActivity, GoodsInfoActivity::class.java)
                intent.putExtra("proimg", goodslist[position].proImg)
                intent.putExtra("pid", goodslist[position].proId)
                startActivity(intent)
            }
            grid_adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
                override fun onLoadMoreRequested() {
                    page = page!! + 1
//                    if (s_type.equals("0"))//商家
//                        mPersenter!!.getstorelistdata(str!!, page.toString(), keyword!!, false, supid!!)
//                    else
                    mPersenter!!.getlistdata(str!!, page.toString(), keyword!!, type_store!!, false)
                    grid_adapter!!.loadMoreComplete()
                }
            }, goodslist_xrectcyler)
            grid_adapter!!.disableLoadMoreIfNotFullPage(goodslist_xrectcyler)
            return grid_adapter
        }

    }

    var linener_adapter: BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>? = null
    var grid_adapter: BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>? = null
    var list_change: Boolean? = false//用于改变列表的显示形式 true:linear形式  false： grid形式
    var goodslist = arrayListOf<GoodsListBean.DataBean.ListBean>()
    var page: Int? = null
    var str: String? = null//记录价格是升序还是降序 1：从高到低  2 ：从低到高
    var keyword: String? = null //搜索的关键字
    override fun initlayout(): Int {
        return R.layout.activity_goodslist
    }

    var paicu_img: ImageView? = null
    var paixu: RelativeLayout? = null
    override fun initView() {
        type_store = intent.getStringExtra("str")
        if (type_store == null) type_store = ""
//        supid = intent.getStringExtra("supid")
//        if (supid == null) supid = ""
//        s_type = intent.getStringExtra("type")
        str = ""
        keyword = ""
        page = 1
//        if (s_type.equals("0"))//商家
//            mPersenter!!.getstorelistdata(str!!, page.toString(), keyword!!, true, supid!!)
//        else
        mPersenter!!.getlistdata(str!!, page.toString(), keyword!!, type_store!!, true)
//        list_change = true
        goodslist_back.setOnClickListener(this)
        paixu = findViewById(R.id.goodslist_paixu)
        paicu_img = findViewById(R.id.goodslist_paixu_img)
        goodslist_refresh.setOnRefreshListener(this)
        goodslist_refresh.setColorSchemeResources(R.color.colorPrimaryDark)
        val linenerlayoutmass = LinearLayoutManager(this@GoodsListActivity)
        linenerlayoutmass.orientation = OrientationHelper.VERTICAL
        goodslist_xrectcyler.layoutManager = linenerlayoutmass
        goodslist_cb_change.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                list_change = isChecked
                if (!isChecked) {
                    val linenerlayoutmass = LinearLayoutManager(this@GoodsListActivity)
                    linenerlayoutmass.orientation = OrientationHelper.VERTICAL
                    goodslist_xrectcyler.layoutManager = linenerlayoutmass
                    linener_adapter = gridadapter(1)
                    goodslist_xrectcyler.adapter = linener_adapter
                    linener_adapter!!.setNewData(goodslist)
                } else {
                    val GridLayoutManager = GridLayoutManager(this@GoodsListActivity, 2)
                    goodslist_xrectcyler.layoutManager = GridLayoutManager
                    grid_adapter = gridadapter(2)
                    goodslist_xrectcyler.adapter = grid_adapter
                    grid_adapter!!.setNewData(goodslist)
                }
            }
        })
        goodslist_paixu_layout.setOnClickListener(this)
        goodslist_edit.setOnClickListener(this)
        goodslist_edit_img.setOnClickListener(this)
    }

    override fun initPersenter() {
        mPersenter = GoodsListPersenter(this, this)
    }

    override fun showloading() {
        if (mLoading != null)
            mLoading!!.show()
    }

    override fun dismissloading() {
        if (mLoading != null && mLoading!!.isShowing)
            mLoading!!.dismiss()
    }


    override fun onResume() {
        super.onResume()
    }

}