package com.hechuang.labeego.ui.fragment

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.R.id.theraise_recycler
import com.hechuang.labeego.R.id.theraise_refresh
import com.hechuang.labeego.base.BaseViewpageFragment
import com.hechuang.labeego.bean.TheRaiseBean
import com.hechuang.labeego.persenter.fragment.TheRaisePersenter
import com.hechuang.labeego.ui.activity.WebsActivity
import com.hechuang.labeego.view.fragment.ITheRaiseView
import kotlinx.android.synthetic.main.fragment_theraise.*

/**
 * 众筹订单
 */
class TheRaiseFragment : BaseViewpageFragment<TheRaisePersenter>(), ITheRaiseView, SwipeRefreshLayout.OnRefreshListener {
    override fun getorder_seccess(listdata: List<TheRaiseBean.DataBean.ListBean>, isrefresh: Boolean) {
        if (listdata.isNotEmpty()) {
            if (page == 1) {
                ordertype_listdata.clear()
            }
            ordertype_listdata.addAll(listdata)
        }
        if (page == 1) {
            adapter!!.setNewData(listdata)
            if (isrefresh) {
                theraise_refresh.isRefreshing = false
                if (listdata.size < 10) {
                    adapter!!.loadMoreEnd()
                } else {
                    adapter!!.loadMoreComplete()
                }
            } else {
                if (listdata.size < 10) {
                    adapter!!.loadMoreEnd()
                } else {
                    adapter!!.loadMoreComplete()
                }
            }
        } else {
            adapter!!.addData(listdata)
            adapter!!.loadMoreComplete()
        }
    }

    override fun getorder_error(msg: String) {
        adapter!!.loadMoreEnd(true)
    }

    override fun onRefresh() {
        page = 1
        mPersenter!!.gettheraisedata(page.toString(), true)
    }

    override fun initPersenter() {
        mPersenter = TheRaisePersenter(this, activity!!)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_theraise
    }

    var adapter: BaseQuickAdapter<TheRaiseBean.DataBean.ListBean, BaseViewHolder>? = null
    var page: Int? = null
    var ordertype_listdata = arrayListOf<TheRaiseBean.DataBean.ListBean>()
    override fun initViews() {
        page = 1
        var linearLayoutmanager = LinearLayoutManager(activity)
        linearLayoutmanager.orientation = LinearLayoutManager.VERTICAL
        theraise_recycler.layoutManager = linearLayoutmanager
        theraise_refresh.setOnRefreshListener(this)
        adapter = object : BaseQuickAdapter<TheRaiseBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_theraise_item) {
            override fun convert(helper: BaseViewHolder?, item: TheRaiseBean.DataBean.ListBean?) {
                val recyelr = helper!!.getView<RecyclerView>(R.id.adapter_theraise_recycler)
                val layoutli = LinearLayoutManager(activity)
                recyelr.layoutManager = layoutli
                var recycler_adapter = object : BaseQuickAdapter<TheRaiseBean.DataBean.ListBean.OrderdetailBean, BaseViewHolder>(R.layout.adapter_theraisezixiang_item) {
                    override fun convert(helper: BaseViewHolder?, item: TheRaiseBean.DataBean.ListBean.OrderdetailBean?) {
                        helper!!.setText(R.id.adapter_theraise_goodsname, item!!.proName)
                                .setText(R.id.adapter_theraise_goodsnumber, "×${item.pronum}")
                                .setText(R.id.adapter_theraise_goodsprice, item.price)
                                .setText(R.id.adapter_theraise_goodszeng, item.pv)
                        val icon = helper.getView<ImageView>(R.id.adapter_theraise_icon)
                        Glide.with(activity!!).load(item.proImg).into(icon)
                    }
                }
                recyelr.adapter = recycler_adapter
                recycler_adapter.setNewData(item!!.orderdetail)
                recycler_adapter.setOnItemClickListener { adapter, view, position ->
                    //跳转到订单详情
                    if (item.info_url != "") {
                        val intent = Intent(activity, WebsActivity::class.java)
                        intent.putExtra("web", item.info_url)
                        startActivity(intent)
                    }
                }
                val button = helper.getView<Button>(R.id.adapter_theraise_orderpay)
                val button_layout = helper.getView<LinearLayout>(R.id.adapter_theraise_pay_layout)
                when (item.status) {
                    "待付款" -> {
                        button_layout.visibility = View.VISIBLE
                        button.text = "确认付款"
                        button.setBackgroundResource(R.drawable.goodsinfo_shoppcarnumber)
                    }
                    "已完成" -> {//显示待发货
                        button_layout.visibility = View.GONE
                    }
                    "已过期" -> {
                        button_layout.visibility = View.GONE
                    }
                }
                button.setOnClickListener {
                    when (button.text.toString()) {
                        "确认付款" -> {
                            if (item.pay_url != "") {
                                val intent = Intent(activity, WebsActivity::class.java)
                                intent.putExtra("web", item.pay_url)
                                startActivity(intent)
                            }
                        }
                    }
                }
                helper.setText(R.id.adapter_theraise_tiem, "订单号:")
                        .setText(R.id.adapter_theraise_ordernumber, item.innerOrderId)
                        .setText(R.id.adapter_theraise_type, item.status)
                        .setText(R.id.adapter_theraise_goodsnumber_zong, item.zhongjg)
                        .setText(R.id.adapter_theraise_orderzeng, "(${item.zhongfl}")
            }
        }
        theraise_recycler.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //跳转到订单详情
            if (ordertype_listdata[position].info_url != "") {
                val intent = Intent(activity, WebsActivity::class.java)
                intent.putExtra("web", ordertype_listdata[position].info_url)
                startActivity(intent)
            }
        }
        adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page = page!! + 1
                mPersenter!!.gettheraisedata(page.toString(), false)
            }
        }, theraise_recycler)
        theraise_refresh.setOnRefreshListener(this)
        theraise_refresh.setColorSchemeResources(R.color.colorPrimaryDark)
        adapter!!.emptyView = erroreview()
        adapter!!.disableLoadMoreIfNotFullPage(theraise_recycler)
    }

    override fun fetchData() {
        mPersenter!!.gettheraisedata(page.toString(), false)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    fun erroreview(): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.view_ordertype_dataerror, null)
        return view
    }

    override fun onResume() {
        super.onResume()
        page = 1
        mPersenter!!.gettheraisedata(page.toString(), false)
    }
}