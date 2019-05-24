package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.HoenyBean
import com.hechuang.labeego.bean.HoneyListbean
import com.hechuang.labeego.persenter.activity.HoenyPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RecycleViewDivider
import com.hechuang.labeego.view.activity.IHoenyView
import kotlinx.android.synthetic.main.activity_hoeny.*

class HoenyActivity : BaseActivity<HoenyPersenter>(), IHoenyView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    override fun onLoadMoreRequested() {
        page += 1
        mPersenter!!.gethoenydata(page.toString(), false)
    }

    override fun onRefresh() {
        page = 1
        mPersenter!!.gethoenydata(page.toString(), true)
    }

    var myhoneylistbean: HoneyListbean? = null
    var yearlsit = arrayListOf<String>()
    override fun gethoenydatasuccess(beandata: HoenyBean, isr: Boolean) {
        if (beandata.data.list.record != null && beandata.data.list.record.size > 0) {
            var listdata_one = arrayListOf<HoneyListbean>()
            if (isr) {
                yearlsit.clear()
            }
            for (i in 0 until beandata.data.list.record.size) {
                yearlsit.add(beandata.data.list.record[i].y)
                if (yearlsit.size > 1) {
                    if (yearlsit[yearlsit.size - 1] !=
                            yearlsit[yearlsit.size - 2]) {
                        myhoneylistbean = HoneyListbean(true, beandata.data.list.record[i].y)
                        listdata_one.add(myhoneylistbean!!)
                    }
                } else {
                    myhoneylistbean = HoneyListbean(true, beandata.data.list.record[i].y)
                    listdata_one.add(myhoneylistbean!!)
//                        }
//                    }
                }
                for (j in 0 until beandata.data.list.record[i].list.size) {
                    listdata_one.add(HoneyListbean(beandata.data.list.record[i].list[j]))
                }
            }
            if (isr) listdata.clear()
            listdata.addAll(listdata_one)
            if (page == 1) {
                if (!seraadapter!!.isHeaderViewAsFlow) {
                    seraadapter!!.addHeaderView(topview(beandata, true))
                    seraadapter!!.isHeaderViewAsFlow = true
                } else {
                    honeyprice!!.text = beandata.data.list.header.count
                }
                listdata.clear()
                seraadapter!!.setNewData(listdata_one)
                listdata.addAll(listdata_one)
                if (isr) {
                    hoeny_refresh.isRefreshing = false
                } else {
                    if (listdata_one.size < 9) {
                        seraadapter!!.loadMoreEnd()
                    } else {
                        seraadapter!!.loadMoreComplete()
                    }
                }
            } else {
                if (isr) {
                    listdata.clear()
                    seraadapter!!.setNewData(listdata_one)
                    hoeny_refresh.isRefreshing = false
                } else {
                    seraadapter!!.addData(listdata_one!!)
                    listdata.addAll(listdata_one)
                    if (listdata_one.size < 6) {
                        seraadapter!!.loadMoreEnd()
                    } else {
                        seraadapter!!.loadMoreComplete()
                    }
                }
            }
        } else {
            if (beandata.data.list.header != null) {
                if (!seraadapter!!.isHeaderViewAsFlow) {
                    seraadapter!!.addHeaderView(topview(beandata, false))
                    seraadapter!!.isHeaderViewAsFlow = true
                } else {
                    honeyprice!!.text = beandata.data.list.header.count
                }
            }
            if (isr) {
                hoeny_refresh.isRefreshing = false
            } else {
                seraadapter!!.loadMoreEnd()
            }
        }
        dismissloading()
    }

    override fun gethoenydataerror(msg: String) {
        MyToast.showMsg(msg)
        dismissloading()
        hoeny_refresh.isRefreshing = false
        seraadapter!!.loadMoreEnd()
    }

    override fun initlayout(): Int {
        return R.layout.activity_hoeny
    }

    //页码
    var page = 1
    //列表适配器
    var seraadapter: BaseSectionQuickAdapter<HoneyListbean, BaseViewHolder>? = null
    //保存所有的列表数据，用于点击事件
    var listdata = arrayListOf<HoneyListbean>()

    override fun initView() {
        hoeny_back.setOnClickListener { finish() }
        hoeny_refresh.setOnRefreshListener(this)
        page = 1
        var linearlayoutmanager = LinearLayoutManager(this)
        linearlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        hoeny_recycler.layoutManager = linearlayoutmanager
        hoeny_recycler.addItemDecoration(RecycleViewDivider(this, LinearLayoutManager.VERTICAL))
        seraadapter = getadapter()
        hoeny_recycler.adapter = seraadapter
        seraadapter!!.setOnItemClickListener { adapter, view, position ->
            if (listdata[position].t != null) {
                var intent = Intent(this@HoenyActivity, BeeForceInfoActivity::class.java)
                intent.putExtra("beeid", listdata[position].t.id)
                intent.putExtra("beetype", "3")
                startActivity(intent)
            }
        }
        seraadapter!!.disableLoadMoreIfNotFullPage(hoeny_recycler)
        seraadapter!!.setOnLoadMoreListener(this, hoeny_recycler)
    }

    override fun initPersenter() {
        mPersenter = HoenyPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    fun getadapter(): BaseSectionQuickAdapter<HoneyListbean, BaseViewHolder>? {
        var Myadapter = object : BaseSectionQuickAdapter<HoneyListbean, BaseViewHolder>(R.layout.adapter_honey, R.layout.adapter_honeytitle, listdata) {
            override fun convertHead(helper: BaseViewHolder?, item: HoneyListbean?) {
                helper!!.setText(R.id.honey_title_text, item!!.header)
            }

            override fun convert(helper: BaseViewHolder?, item: HoneyListbean?) {
                helper!!.setText(R.id.honey_time, item!!.t.addTime)
                        .setText(R.id.honey_moeny, item.t.amount)
                        .setText(R.id.honey_memo, item.t.memo)
                        .setText(R.id.honey_typename, item.t.typeName)
            }
        }
        return Myadapter

    }

    var honeyprice: TextView? = null
    fun topview(beandata: HoenyBean, isshow: Boolean): View {
        var view = LayoutInflater.from(this@HoenyActivity).inflate(R.layout.view_honeytop, null)
        var honeyprice_text = view.findViewById<TextView>(R.id.hoeny_top_price_text)
        honeyprice = view.findViewById<TextView>(R.id.hoeny_top_honeynumber)
        var hoenynoting = view.findViewById<TextView>(R.id.hoeny_top_noting)
        if (isshow) {
            hoenynoting.visibility = View.GONE
        } else {
            hoenynoting.visibility = View.VISIBLE
        }
        honeyprice!!.text = beandata.data.list.header.count
        honeyprice_text.text = beandata.data.list.header.title
        var honeyrecycler = view.findViewById<RecyclerView>(R.id.hoeny_top_recycler)
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        honeyrecycler.layoutManager = layoutmanager
        var adapter = object : BaseQuickAdapter<HoenyBean.DataBean.ListBeanX.CentreBean, BaseViewHolder>(R.layout.adapter_honeytop) {
            override fun convert(helper: BaseViewHolder?, item: HoenyBean.DataBean.ListBeanX.CentreBean?) {
                helper!!.setText(R.id.adapter_honey_recycler_item, item!!.title)
            }
        }
        honeyrecycler.adapter = adapter
        adapter.setEnableLoadMore(false)
        adapter.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(this@HoenyActivity, WebsActivity::class.java)
            intent.putExtra("web", beandata.data.list.centre[position].url)
            startActivity(intent)
        }
        if (beandata.data.list.centre != null && beandata.data.list.centre.size > 0) {
            adapter.setNewData(beandata.data.list.centre)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        mPersenter!!.gethoenydata(page.toString(), false)
    }
}