package com.hechuang.labeego.ui.fragment

import android.content.Intent
import android.graphics.Paint
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.adapter.GoodsHomeGridAdapter
import com.hechuang.labeego.base.BaseFragment
import com.hechuang.labeego.base.BaseViewpageFragment
import com.hechuang.labeego.bean.GoodsHomeBannerBean
import com.hechuang.labeego.bean.GoodsListBean
import com.hechuang.labeego.persenter.fragment.GoodsHomePersenter
import com.hechuang.labeego.tools.Util.GlideImageLoader
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.ui.activity.*
import com.hechuang.labeego.view.fragment.IGoodsHomeView
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_goodshome.*

class GoodsHomeFragment : BaseViewpageFragment<GoodsHomePersenter>(), IGoodsHomeView, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    override fun fetchData() {
        page = 1
        mPersenter!!.getbanner_imges()
    }

    override fun onRefresh() {
        page = 1
        goodshomeadapter!!.setEnableLoadMore(true)
        mPersenter!!.getlietdata(page.toString(), true)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
        }
    }

    override fun getbanner_imagee_seccess(goodsHomeBannerBean: GoodsHomeBannerBean.DataBean) {
        this.goodsaddviewdata = goodsHomeBannerBean
        mPersenter!!.getlietdata(page.toString(), false)
    }

    var isheatview = false
    override fun getlistdata_seccess(goodslistbean: List<GoodsListBean.DataBean.ListBean>, isrefresh: Boolean) {
        if (isrefresh) {
            goodsdata.clear()
        }
        goodsdata.addAll(goodslistbean)
        if (!isheatview) {
            goodshomeadapter = goodsadapter()
            fragment_goodshome_recycler.adapter = goodshomeadapter
            goodshomeadapter!!.addHeaderView(addview())
            goodshomeadapter!!.setNewData(goodslistbean)
            isheatview = true
        } else {
            if (isrefresh) {
                goodshomeadapter!!.setNewData(goodslistbean)
            } else {
                goodshomeadapter!!.addData(goodslistbean)
            }
        }
        goodshomeadapter!!.setOnItemClickListener { adapter, view, position ->
            var ini = Intent(activity, GoodsInfoActivity::class.java)
            ini.putExtra("proimg", goodsdata[position].proImg)
            ini.putExtra("pid", goodsdata[position].proId)
            startActivity(ini)
        }
        goodshomeadapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page = page!! + 1
//                Log.d("goodshome", page.toString())
                mPersenter!!.getlietdata(page.toString(), false)
            }
        }, fragment_goodshome_recycler)
        if (isrefresh) {
            fragment_goodshome_swiperefresh.isRefreshing = false
        } else {
            goodshomeadapter!!.loadMoreComplete()
        }
        dismissloading()
    }

    override fun getdataerror(page: String, msg: String, isrefresh: Boolean) {
        if (page.toInt() > 1) {
            goodshomeadapter!!.loadMoreEnd()
        } else {
            if (isrefresh) {//刷新无数据
                fragment_goodshome_swiperefresh!!.isRefreshing = false
            } else {
                MyToast.showMsg("加载出错")
            }
        }
        dismissloading()
    }

    override fun initPersenter() {
        mPersenter = GoodsHomePersenter(this, activity!!)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_goodshome
    }

    var goodsdata = arrayListOf<GoodsListBean.DataBean.ListBean>()
    var goodshomeadapter: BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>? = null
    var page: Int? = null
    var goodsaddviewdata: GoodsHomeBannerBean.DataBean? = null
    override fun initViews() {
        page = 1
        fragment_goodshome_swiperefresh.setOnRefreshListener(this)
        fragment_goodshome_swiperefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        var LayoutManager = LinearLayoutManager(activity)
        LayoutManager.orientation = OrientationHelper.VERTICAL
        fragment_goodshome_recycler.layoutManager = LayoutManager
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    private fun addview(): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.recycler_goodshome_add, null)
        var banner = view.findViewById<Banner>(R.id.goodshome_banner)
        var recycler_add = view.findViewById<RecyclerView>(R.id.goodshome_recycler)
        val sousuo_layout = view.findViewById<LinearLayout>(R.id.goodshome_sousuo_layout)
        sousuo_layout.setOnClickListener({
            startActivity(Intent(activity, FindGoodsActivity::class.java))
        })
        banner.setImageLoader(GlideImageLoader())
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR)
        banner.setIndicatorGravity(BannerConfig.RIGHT)
        banner.isAutoPlay(true)
        val display = getResources().displayMetrics
        val params = LinearLayout.LayoutParams(display.widthPixels, display.widthPixels / 2)
        banner.layoutParams = params
        var images = arrayListOf<String>()
        for (i in 0 until goodsaddviewdata!!.list!!.banner!!.size) {
            images.add(goodsaddviewdata!!.list!!.banner!![i].imgs!!)
        }
        banner.setImages(images)
        banner.setOnBannerListener(object : OnBannerListener {
            override fun OnBannerClick(position: Int) {
                Log.d("goodshome_sta", "${goodsaddviewdata!!.list!!.banner!![position].style} $position")
                when (goodsaddviewdata!!.list!!.banner!![position].style) {
                    "0" -> {//列表
                        var intent = Intent(activity, StoreListActivity::class.java)
                        intent.putExtra("supid", goodsaddviewdata!!.list!!.banner!![position].id)
                        //TODO
//                        intent.putExtra("type", "0")
                        startActivity(intent)
                    }
                    "1" -> {//详情
                        var ini = Intent(activity, GoodsInfoActivity::class.java)
                        ini.putExtra("pid", goodsaddviewdata!!.list!!.banner!![position].id)
                        startActivity(ini)
                    }
                    "2" -> {//跳转链接
                        var ini = Intent(activity, WebsActivity::class.java)
                        ini.putExtra("web_url", goodsaddviewdata!!.list!!.banner!![position].url)
                        startActivity(ini)
                    }
                    "3" -> {//不跳转

                    }
                }
            }
        })
        banner.start()
        var recyclerlist = arrayListOf<GoodsHomeBannerBean.DataBean.ListBean.IconBean>()
        for (i in 0 until goodsaddviewdata!!.list!!.icon!!.size) {
            recyclerlist.add(goodsaddviewdata!!.list!!.icon!![i])
        }
        //******************************************************
        var grid = GridLayoutManager(activity, 5)
        recycler_add.layoutManager = grid
        var adapter = GoodsHomeGridAdapter(activity!!, recyclerlist)
        recycler_add.adapter = adapter
        adapter.setOnItemLisener_Grid(object : GoodsHomeGridAdapter.OnItemLisener_Grid {
            override fun OnItemLisner_Grid(position: Int, imagid: String, name: String) {
                if (recyclerlist[position].name == "久零商城") {
                    var intent = Intent(activity, JLWebActivity::class.java)
                    intent.putExtra("web", recyclerlist[position].categoryid)
                    startActivity(intent)
                } else {
                    var intent = Intent(activity, GoodsListActivity::class.java)
                    intent.putExtra("str", recyclerlist[position].categoryid)
//                    intent.putExtra("type", "1")
                    startActivity(intent)
                }
            }
        })
        return view
    }

    /**
     * type: 1 linener  2 grid
     */
    fun goodsadapter(): BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>? {
        var goodshome_adapter = object : BaseQuickAdapter<GoodsListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_goodslinener) {
            override fun convert(helper: BaseViewHolder?, item: GoodsListBean.DataBean.ListBean?) {
                var img_icon = helper!!.getView<ImageView>(R.id.goodslinener_icon)
                Glide.with(mContext).load(item!!.proImg).apply(RequestOptions().override(250, 250).error(R.mipmap.applogo)).into(img_icon)
                helper.setText(R.id.goodslinener_name, item.proName)
                val integral = helper!!.getView<LinearLayout>(R.id.goodslinener_integral_layout)
//                if (item.integral == "") {
                integral.visibility = View.GONE
//                } else {
//                    integral.visibility = View.VISIBLE
//                }
                helper.setText(R.id.goodslinener_peice, item.price)
                        .setText(R.id.goodslinener_yishou, item.yishou)
                        .setText(R.id.goodslinener_integral, item.integral)
                val marketpaice = helper.getView<TextView>(R.id.goodslinener_marketpeice)
                marketpaice.text = item.marketprice
                marketpaice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        goodshome_adapter.disableLoadMoreIfNotFullPage(fragment_goodshome_recycler)
        return goodshome_adapter
    }

    override fun onResume() {
        super.onResume()
        isheatview = false
        page = 1
        mPersenter!!.getbanner_imges()
    }
}