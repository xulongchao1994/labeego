package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.MyHoneyBean
import com.hechuang.labeego.bean.MyHoneyListbean
import com.hechuang.labeego.persenter.activity.MyHoneyPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RecycleViewDivider
import com.hechuang.labeego.view.activity.IMyHoneyView
import kotlinx.android.synthetic.main.activity_myhoney.*

/**
 * 我的蜂浆
 */
class MyHoenyAcativity : BaseActivity<MyHoneyPersenter>(), IMyHoneyView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    override fun onLoadMoreRequested() {
        page += 1
        mPersenter!!.getmyhoenydata(page.toString(), false)
    }

    override fun onRefresh() {
        page = 1
        mPersenter!!.getmyhoenydata(page.toString(), true)
    }

    var yearlsit = arrayListOf<String>()
    var myhoneylistbean: MyHoneyListbean? = null
    override fun gethoenydatasuccess(beandata: MyHoneyBean, isr: Boolean) {

//        if (beandata.data.list.record != null && beandata.data.list.record.size > 0) {
//            //报错单页的列表数据，用于给adapter添加数据
////            var listdata_one = arrayListOf<MyHoneyListbean>()
////            for (i in 0 until beandata.data.list.record.size) {  //添加分组的title
////                if (isr) {
////                    yearlsit.clear()
////                }
////                for (i in 0 until beandata.data.list.record.size) {
////                    yearlsit.add(beandata.data.list.record[i].y)
////                    if (yearlsit.size > 1) {
////                        if (yearlsit[yearlsit.size - 1] !=
////                                yearlsit[yearlsit.size - 2]) {
////                            myhoneylistbean = MyHoneyListbean(true, beandata.data.list.record[i].y)
////                            listdata_one.add(myhoneylistbean!!)
////                        }
////                    } else {
////                        myhoneylistbean = MyHoneyListbean(true, beandata.data.list.record[i].y)
////                        listdata_one.add(myhoneylistbean!!)
////                    }
////                }
////                for (j in 0 until beandata.data.list.record[i].list.size) {
////                    listdata_one.add(MyHoneyListbean(beandata.data.list.record[i].list[j]))
////                }
////            }
////
////            if (isr) {//isr为true是刷新    如果刷新的话  清空原来的数据
////                listdata.clear()
////            }
////            listdata.addAll(listdata_one)  //添加数据
////            if (page == 1) {//为空时  添加adapter
////                if (!adapter!!.isHeaderViewAsFlow) {
////                    adapter!!.addHeaderView(topview(beandata, true))
////                    adapter!!.isHeaderViewAsFlow = true
////                } else {
////                    honeyprice!!.text = beandata.data.list.header.count
////                }
////                listdata.clear()
////                adapter!!.setNewData(listdata_one)
////                listdata.addAll(listdata_one)
////                if (isr) {
////                    myhoney_refresh.isRefreshing = false
////                } else {
////                    if (listdata_one.size < 9) {
////                        adapter!!.loadMoreEnd()
////                    } else {
////                        adapter!!.loadMoreComplete()
////                    }
////                }
////            } else {
////                if (isr) {  //adapter不为空时 正常的流程
////                    listdata.clear()
////                    adapter!!.setNewData(listdata_one)
////                    myhoney_refresh.isRefreshing = false
////                } else {
////                    adapter!!.addData(listdata_one)
////                    listdata.addAll(listdata_one)
////                    if (listdata_one.size < 9) {
////                        adapter!!.loadMoreEnd()
////                    } else {
////                        adapter!!.loadMoreComplete()
////                    }
////                }
////            }
////        } else {
////            if (beandata.data.list.header != null) {
////                if (!adapter!!.isHeaderViewAsFlow) {
////                    adapter!!.addHeaderView(topview(beandata, false))
////                    adapter!!.isHeaderViewAsFlow = true
////                } else {
////                    honeyprice!!.text = beandata.data.list.header.count
////                }
////            }
////            if (isr) {
////                myhoney_refresh.isRefreshing = false
////            } else {
////                adapter!!.loadMoreEnd()
////            }
////        }

        if (beandata.data.list.record != null && beandata.data.list.record.size > 0) {
            var listdata_one = arrayListOf<MyHoneyListbean>()
            if (isr) {
                yearlsit.clear()
            }
            for (i in 0 until beandata.data.list.record.size) {
                yearlsit.add(beandata.data.list.record[i].y)
                if (yearlsit.size > 1) {
                    if (yearlsit[yearlsit.size - 1] !=
                            yearlsit[yearlsit.size - 2]) {
                        myhoneylistbean = MyHoneyListbean(true, beandata.data.list.record[i].y)
                        listdata_one.add(myhoneylistbean!!)
                    }
                } else {
                    myhoneylistbean = MyHoneyListbean(true, beandata.data.list.record[i].y)
                    listdata_one.add(myhoneylistbean!!)
//                        }
//                    }
                }
                for (j in 0 until beandata.data.list.record[i].list.size) {
                    listdata_one.add(MyHoneyListbean(beandata.data.list.record[i].list[j]))
                }
            }
            if (isr) listdata.clear()
            listdata.addAll(listdata_one)
            if (page == 1) {
                if (!adapter!!.isHeaderViewAsFlow) {
                    adapter!!.addHeaderView(topview(beandata, true))
                    adapter!!.isHeaderViewAsFlow = true
                } else {
                    honeyprice!!.text = beandata.data.list.header.count
                }
                listdata.clear()
                adapter!!.setNewData(listdata_one)
                listdata.addAll(listdata_one)
                if (isr) {
                    myhoney_refresh.isRefreshing = false
                } else {
                    if (listdata_one.size < 9) {
                        adapter!!.loadMoreEnd()
                    } else {
                        adapter!!.loadMoreComplete()
                    }
                }
            } else {
                if (isr) {
                    listdata.clear()
                    adapter!!.setNewData(listdata_one)
                    myhoney_refresh.isRefreshing = false
                } else {
                    adapter!!.addData(listdata_one!!)
                    listdata.addAll(listdata_one)
                    if (listdata_one.size < 6) {
                        adapter!!.loadMoreEnd()
                    } else {
                        adapter!!.loadMoreComplete()
                    }
                }
            }
        } else {
            if (beandata.data.list.header != null) {
                if (!adapter!!.isHeaderViewAsFlow) {
                    adapter!!.addHeaderView(topview(beandata, false))
                    adapter!!.isHeaderViewAsFlow = true
                } else {
                    honeyprice!!.text = beandata.data.list.header.count
                }
            }
            if (isr) {
                myhoney_refresh.isRefreshing = false
            } else {
                adapter!!.loadMoreEnd()
            }
        }
        dismissloading()

    }

    override fun gethoenydataerror(msg: String) {
        MyToast.showMsg(msg)
        dismissloading()
        myhoney_refresh.isRefreshing = false
        adapter!!.loadMoreEnd()
    }

    override fun initlayout(): Int {
        return R.layout.activity_myhoney
    }

    //页码    dengy    dengyixia
    var page = 1
    //列表适配器
    var adapter: BaseSectionQuickAdapter<MyHoneyListbean, BaseViewHolder>? = null
    //保存所有的列表数据，用于点击事件
    var listdata = arrayListOf<MyHoneyListbean>()

    override fun initView() {
        page = 1
        myhoney_back.setOnClickListener { finish() }
        var linearlayoutmanager = LinearLayoutManager(this)
        linearlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        myhoney_recycler.layoutManager = linearlayoutmanager
        myhoney_refresh.setOnRefreshListener(this)
        myhoney_recycler.addItemDecoration(RecycleViewDivider(this, LinearLayoutManager.VERTICAL))
        adapter = getadapter()
        myhoney_recycler.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (listdata[position].t != null) {
                var intent = Intent(this@MyHoenyAcativity, BeeForceInfoActivity::class.java)
                intent.putExtra("beeid", listdata[position].t.id)
                intent.putExtra("beetype", "5")
                startActivity(intent)
            }
        }
        adapter!!.disableLoadMoreIfNotFullPage(myhoney_recycler)
        adapter!!.setOnLoadMoreListener(this, myhoney_recycler)
    }

    override fun initPersenter() {
        mPersenter = MyHoneyPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    var honeyprice: TextView? = null
    fun topview(beandata: MyHoneyBean, isshow: Boolean): View {
        var view = LayoutInflater.from(this@MyHoenyAcativity).inflate(R.layout.view_myhoneytop, null)
        view.findViewById<ImageView>(R.id.myhoney_top_zoushi_img)
                .setOnClickListener {
                    if (beandata.data.list.price_trend.url != "") {
                        var intent = Intent(this@MyHoenyAcativity, WebsActivity::class.java)
                        intent.putExtra("web", beandata.data.list.price_trend.url)
                        startActivity(intent)
                    }
                }
        view.findViewById<TextView>(R.id.myhoney_top_zoushi_text)
                .setOnClickListener {
                    if (beandata.data.list.price_trend.url != "") {
                        var intent = Intent(this@MyHoenyAcativity, WebsActivity::class.java)
                        intent.putExtra("web", beandata.data.list.price_trend.url)
                        startActivity(intent)
                    }
                }
        var hoenynoting = view.findViewById<TextView>(R.id.myhoney_top_noting)
        var honeyprice_text = view.findViewById<TextView>(R.id.myhoney_top_price_text)
        honeyprice = view.findViewById<TextView>(R.id.myhoney_top_honeynumber)
        honeyprice!!.text = beandata.data.list.header.count
        honeyprice_text.text = beandata.data.list.header.title
        var honeyrecycler = view.findViewById<RecyclerView>(R.id.myhoney_top_recycler)
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        honeyrecycler.layoutManager = layoutmanager
        var adapter = object : BaseQuickAdapter<MyHoneyBean.DataBean.ListBeanX.CentreBean, BaseViewHolder>(R.layout.adapter_myhoneytop) {
            override fun convert(helper: BaseViewHolder?, item: MyHoneyBean.DataBean.ListBeanX.CentreBean?) {
                helper!!.setText(R.id.adapter_myhoney_recycler_item, item!!.title)
            }
        }
        honeyrecycler.adapter = adapter
        adapter.setEnableLoadMore(false)
        adapter.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(this@MyHoenyAcativity, WebsActivity::class.java)
            intent.putExtra("web", beandata.data.list.centre[position].url)
            startActivity(intent)
        }
        if (beandata.data.list.centre != null && beandata.data.list.centre.size > 0) {
            adapter.setNewData(beandata.data.list.centre)
        }
        if (isshow) {
            hoenynoting.visibility = View.GONE
        } else {
            hoenynoting.visibility = View.VISIBLE
        }
        return view
    }

    fun getadapter(): BaseSectionQuickAdapter<MyHoneyListbean, BaseViewHolder>? {
        var Myadapter = object : BaseSectionQuickAdapter<MyHoneyListbean, BaseViewHolder>(R.layout.adapter_myhoney, R.layout.adapter_myhoneytitle, listdata) {
            override fun convertHead(helper: BaseViewHolder?, item: MyHoneyListbean?) {
                helper!!.setText(R.id.myhoney_title_text, item!!.header)
            }

            override fun convert(helper: BaseViewHolder?, item: MyHoneyListbean?) {
                helper!!.setText(R.id.myhoney_time, item!!.t.addTime)
                        .setText(R.id.myhoney_moeny, item.t.amount)
                        .setText(R.id.myhoney_memo, item.t.memo)
                        .setText(R.id.myhoney_typename, item.t.typeName)
            }
        }
        return Myadapter

    }

    override fun onResume() {
        super.onResume()
        mPersenter!!.getmyhoenydata(page.toString(), false)
    }
}