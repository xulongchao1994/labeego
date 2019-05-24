package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
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
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.persenter.activity.OrderTypePersenter
import com.hechuang.labeego.view.activity.IOrderTypeView
import kotlinx.android.synthetic.main.activity_ordertype.*
import org.json.JSONArray
import org.json.JSONObject

class OrderTypeActivity : BaseActivity<OrderTypePersenter>(), IOrderTypeView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    override fun shouhuo_seccess(data: Order_shouhuo_Bean, position: Int) {
        //收货成功 通知列表更新
        ordertype_listdata[position].status = "已付款"
        adapter!!.notifyDataSetChanged()
    }

    override fun shouhuo_error(msg: String) {
        //收货失败  不做操作
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        page = 1
        mPersenter!!.getorderall("2", page.toString(), orderstatus!!, true)
    }

    /**
     * 获取数据成功
     */

    override fun getorder_seccess(listdata: List<AllorderListBean.DataBean.ListBean>, isrefresh: Boolean) {
        if (listdata.isNotEmpty()) {
            if (page == 1) {
                ordertype_listdata.clear()
            }
            ordertype_listdata.addAll(listdata)
        }
        if (page == 1) {
            adapter!!.setNewData(listdata)
            if (isrefresh) {
                ordertype_swiperefresh.isRefreshing = false
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

    /**
     * 获取数据错误
     */
    override fun getorder_error(msg: String) {
        adapter!!.loadMoreEnd(true)
    }

    var adapter: BaseQuickAdapter<AllorderListBean.DataBean.ListBean, BaseViewHolder>? = null
    var ordertype: String? = null
    var orderstatus: String? = null
    var page: Int? = null
    var ordertype_listdata = arrayListOf<AllorderListBean.DataBean.ListBean>()
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ordertype_back -> finish()
        }
    }

    /**
     * 初始化布局
     */
    override fun initlayout(): Int {
        return R.layout.activity_ordertype
    }

    /**
     * 加载布局 初始化通用数据
     */
    override fun initView() {
        page = 1
        ordertype = intent.getStringExtra("ordertype")
        ordertype_back.setOnClickListener(this)
        val linenerlayoutmanager = LinearLayoutManager(this)
        linenerlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        ordertype_recyclerview.layoutManager = linenerlayoutmanager
        if (ordertype != null && !ordertype.equals("")) {
            ordertype_ordertype.text = ordertype
            when (ordertype) {
                "待付款" -> {
                    orderstatus = "1"
                }
                "待发货" -> {
                    orderstatus = "2"
                }
                "待收货" -> {
                    orderstatus = "3"
                }
                "已完成" -> {
                    orderstatus = "4"
                }
                "全部订单" -> {
                    orderstatus = "0"
                }
            }
        } else {
            ordertype_ordertype.text = "订单列表"
        }
        adapter = object : BaseQuickAdapter<AllorderListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allorder_item) {
            override fun convert(helper: BaseViewHolder?, item: AllorderListBean.DataBean.ListBean?) {
                val recyelr = helper!!.getView<RecyclerView>(R.id.adapter_allorder_recycler)
                val layoutli = LinearLayoutManager(this@OrderTypeActivity)
                recyelr.layoutManager = layoutli
                var recycler_adapter = object : BaseQuickAdapter<AllorderListBean.DataBean.ListBean.OrderdetailBean, BaseViewHolder>(R.layout.adapter_allorderzixiang_item) {
                    override fun convert(helper: BaseViewHolder?, item: AllorderListBean.DataBean.ListBean.OrderdetailBean?) {
                        helper!!.setText(R.id.adapter_allorder_goodsname, item!!.proName)
                                .setText(R.id.adapter_allorder_goodstype, item.styleName)
                                .setText(R.id.adapter_allorder_goodsnumber, "×${item.proNum}")
                                .setText(R.id.adapter_allorder_goodsprice, item.price)
                                .setText(R.id.adapter_allorder_goodszeng, item.pv)
                                .setText(R.id.adapter_allorder_storename, item.supplier)
                        val icon = helper.getView<ImageView>(R.id.adapter_allorder_icon)
                        Glide.with(this@OrderTypeActivity).load(item.proImg).into(icon)
                    }
                }

                recyelr.adapter = recycler_adapter
                recycler_adapter.setNewData(item!!.orderdetail)
                recycler_adapter.setOnItemClickListener { adapter, view, position ->
                    //跳转到订单详情
                    val intent = Intent(this@OrderTypeActivity, OrderInfoActivity::class.java)
                    intent.putExtra("orderid", item.innerOrderId)
                    startActivity(intent)
                }
                val button = helper.getView<Button>(R.id.adapter_allorder_orderpay)
                val button_layout = helper.getView<LinearLayout>(R.id.adapter_allorder_pay_layout);
                when (item.status) {
                    "待付款" -> {
                        button_layout.visibility = View.VISIBLE
                        button.text = "确认付款"
                    }
                    "已付款" -> {//显示待发货
                        button_layout.visibility = View.GONE
                    }
                    "配货中" -> {//显示待发货
                        button_layout.visibility = View.GONE
                    }
                    "待发货" -> {//显示待发货
                        button_layout.visibility = View.GONE
                    }
                    "已发货" -> {
                        button_layout.visibility = View.VISIBLE
                        button.text = "确认收货"
                    }
                    "待收货" -> {
                        button_layout.visibility = View.VISIBLE
                        button.text = "确认收货"
                    }
                    "已完成" -> {
                        button_layout.visibility = View.GONE
                    }
                    "过期订单" -> {
                        button_layout.visibility = View.GONE
                    }
                    "已取消" -> {
                        button_layout.visibility = View.GONE
                    }
                    "后台取消" -> {
                        button_layout.visibility = View.GONE
                    }
                    "取消待处理" -> {
                        button_layout.visibility = View.GONE
                    }
                }
                button.setOnClickListener {
                    when (button.text.toString()) {
                        "确认付款" -> {
                            var json = JSONObject()
                            var jsonarray = JSONArray()
                            jsonarray.put(item.innerOrderId)
                            json.put("json", jsonarray)
                            dismissloading()
                            var intent = Intent(this@OrderTypeActivity, OrderTureActivity::class.java)
                            intent.putExtra("orderbianhao", json.toString())
                            startActivity(intent)
                        }
                        "确认收货" -> {
                            AlertDialog.Builder(this@OrderTypeActivity).setTitle(R.string.app_name)
                                    .setMessage("是否确认收货")
                                    .setNegativeButton("确定") { dialog, which ->
                                        mPersenter!!.postshouhuo(item.innerOrderId, helper.layoutPosition)
                                    }
                                    .setPositiveButton("取消") { dialog, which -> }
                                    .show()
                        }
                    }

                }
                helper.setText(R.id.adapter_allorder_tiem, item!!.addDate)
                        .setText(R.id.adapter_allorder_ordernumber, item.innerOrderId)
                        .setText(R.id.adapter_allorder_type, item.status)
                        .setText(R.id.adapter_allorder_goodsnumber_zong, item.zhongjg)
                        .setText(R.id.adapter_allorder_orderzeng, "(${item.zhongfl}蜂力)")
            }
        }
        ordertype_recyclerview.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //跳转到订单详情
            val intent = Intent(this@OrderTypeActivity, OrderInfoActivity::class.java)
            intent.putExtra("orderid", ordertype_listdata[position].innerOrderId)
            startActivity(intent)
        }
        adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page = page!! + 1
                mPersenter!!.getorderall("2", page.toString(), orderstatus!!, false)
            }
        }, ordertype_recyclerview)
        ordertype_swiperefresh.setOnRefreshListener(this)
        ordertype_swiperefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        adapter!!.emptyView = erroreview()
        adapter!!.disableLoadMoreIfNotFullPage(ordertype_recyclerview)
        mPersenter!!.getorderall("2", page.toString(), orderstatus!!, false)
    }

    fun erroreview(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.view_ordertype_dataerror, null)
        return view
    }

    /***
     * 初始化persenter
     */
    override fun initPersenter() {
        mPersenter = OrderTypePersenter(this, this)
    }

    override fun onResume() {
        super.onResume()
        page = 1
        mPersenter!!.getorderall("2", page.toString(), orderstatus!!, false)
    }

    /**
     * 显示loading
     */
    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

}