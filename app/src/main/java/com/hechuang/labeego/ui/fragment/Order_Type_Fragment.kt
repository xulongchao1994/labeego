package com.hechuang.labeego.ui.fragment

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
import com.hechuang.labeego.R.id.orderfragment_recycler
import com.hechuang.labeego.R.id.orderfragment_swipe
import com.hechuang.labeego.base.BaseViewpageFragment
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.persenter.fragment.Order_Type_Persenter
import com.hechuang.labeego.ui.activity.CommentActivity
import com.hechuang.labeego.ui.activity.OrderInfoActivity
import com.hechuang.labeego.ui.activity.OrderTureActivity
import com.hechuang.labeego.view.fragment.IOrder_Type_View
import kotlinx.android.synthetic.main.fragment_order_type.*
import org.json.JSONArray
import org.json.JSONObject

class Order_Type_Fragment : BaseViewpageFragment<Order_Type_Persenter>(), IOrder_Type_View, SwipeRefreshLayout.OnRefreshListener {
    override fun fetchData() {
        mPersenter!!.getorderall("2", page.toString(), orderstatus!!, false)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_order_type
    }

    override fun initViews() {
        ordertype = arguments!!.getString("ordertype")
        page = 1
        val linenerlayoutmanager = LinearLayoutManager(activity)
        linenerlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        orderfragment_recycler.layoutManager = linenerlayoutmanager
        when (ordertype) {
            "全部" -> {
                orderstatus = "0"
            }
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
        }
        adapter = object : BaseQuickAdapter<AllorderListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allorder_item) {
            override fun convert(helper: BaseViewHolder?, item: AllorderListBean.DataBean.ListBean?) {
                val recyelr = helper!!.getView<RecyclerView>(R.id.adapter_allorder_recycler)
                val layoutli = LinearLayoutManager(activity)
                recyelr.layoutManager = layoutli
                var recycler_adapter = object : BaseQuickAdapter<AllorderListBean.DataBean.ListBean.OrderdetailBean, BaseViewHolder>(R.layout.adapter_allorderzixiang_item) {
                    override fun convert(helper: BaseViewHolder?, item: AllorderListBean.DataBean.ListBean.OrderdetailBean?) {
                        helper!!.setText(R.id.adapter_allorder_goodsname, item!!.proName)
                                .setText(R.id.adapter_allorder_goodstype, item.styleName)
                                .setText(R.id.adapter_allorder_goodsnumber, "×${item.proNum}")
                                .setText(R.id.adapter_allorder_goodsprice, item.price)
                                .setText(R.id.adapter_allorder_goodszeng, "(${item.pv}")
                                .setText(R.id.adapter_allorder_storename, item.supplier)
                        if (item.pv == "") {
                            helper.setGone(R.id.adapter_allorder_goodszeng_layout, false)
                        } else {
                            helper.setGone(R.id.adapter_allorder_goodszeng_layout, true)
                        }
                        val icon = helper.getView<ImageView>(R.id.adapter_allorder_icon)
                        Glide.with(activity!!).load(item.proImg).into(icon)
                    }
                }
                recyelr.adapter = recycler_adapter
                recycler_adapter.setNewData(item!!.orderdetail)
                recycler_adapter.setOnItemClickListener { adapter, view, position ->
                    //跳转到订单详情
                    val intent = Intent(activity, OrderInfoActivity::class.java)
                    intent.putExtra("orderid", item.innerOrderId)
                    startActivity(intent)
                }
                val button = helper.getView<Button>(R.id.adapter_allorder_orderpay)
                val button_layout = helper.getView<LinearLayout>(R.id.adapter_allorder_pay_layout);
                when (item.status) {
                    "待付款" -> {
                        button_layout.visibility = View.VISIBLE
                        button.text = "确认付款"
                        button.setBackgroundResource(R.drawable.goodsinfo_shoppcarnumber)
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
                        button.setBackgroundResource(R.drawable.orderinfo_shouhuo)
                    }
                    "待收货" -> {
                        button_layout.visibility = View.VISIBLE
                        button.text = "确认收货"
                        button.setBackgroundResource(R.drawable.orderinfo_shouhuo)
                    }
                    "已完成" -> {
                        if (item.comment_status == "0") {
                            button_layout.visibility = View.VISIBLE
                            button.text = "去评价"
                            button.setBackgroundResource(R.drawable.orderinfo_pingjia)
                        } else {
                            button_layout.visibility = View.GONE
                        }
                    }
                    "已收货" -> {
                        if (item.comment_status == "0") {
                            button_layout.visibility = View.VISIBLE
                            button.text = "去评价"
                            button.setBackgroundResource(R.drawable.orderinfo_pingjia)
                        } else {
                            button_layout.visibility = View.GONE
                        }
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
                            var intent = Intent(activity, OrderTureActivity::class.java)
                            intent.putExtra("orderbianhao", json.toString())
                            startActivity(intent)
                        }
                        "确认收货" -> {
                            AlertDialog.Builder(activity!!).setTitle(R.string.app_name)
                                    .setMessage("是否确认收货")
                                    .setNegativeButton("确定") { dialog, which ->
                                        mPersenter!!.postshouhuo(item.innerOrderId, helper.layoutPosition)
                                    }
                                    .setPositiveButton("取消") { dialog, which -> }
                                    .show()
                        }
                        "去评价" -> {
                            var intent = Intent(activity, CommentActivity::class.java)
                            intent.putExtra("id", item.innerOrderId)
                            startActivity(intent)
                        }
                    }

                }
                helper.setText(R.id.adapter_allorder_tiem, "订单号:")
                        .setText(R.id.adapter_allorder_ordernumber, item.innerOrderId)
                        .setText(R.id.adapter_allorder_type, item.status)
                        .setText(R.id.adapter_allorder_goodsnumber_zong, item.zhongjg)
                        .setText(R.id.adapter_allorder_orderzeng, "(${item.zhongfl}")
                if (item.zhongfl == "") {
                    helper.setGone(R.id.adapter_allorder_orderzeng_layout, false)
                } else {
                    helper.setGone(R.id.adapter_allorder_orderzeng_layout, true)
                }
            }
        }
        orderfragment_recycler.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //跳转到订单详情
            val intent = Intent(activity, OrderInfoActivity::class.java)
            intent.putExtra("orderid", ordertype_listdata[position].innerOrderId)
            startActivity(intent)
        }
        adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page = page!! + 1
                mPersenter!!.getorderall("2", page.toString(), orderstatus!!, false)
            }
        }, orderfragment_recycler)
        orderfragment_swipe.setOnRefreshListener(this)
        orderfragment_swipe.setColorSchemeResources(R.color.colorPrimaryDark)
        adapter!!.emptyView = erroreview()
        adapter!!.disableLoadMoreIfNotFullPage(orderfragment_recycler)
    }

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
                orderfragment_swipe.isRefreshing = false
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

    override fun shouhuo_seccess(data: Order_shouhuo_Bean, int: Int) {
        //收货成功 通知列表更新
        ordertype_listdata[int].status = "已付款"
        adapter!!.notifyDataSetChanged()
    }

    override fun shouhuo_error(msg: String) {
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        page = 1
        mPersenter!!.getorderall("2", page.toString(), orderstatus!!, true)
    }

    override fun initPersenter() {
        mPersenter = Order_Type_Persenter(this, activity!!)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        if (mLoading != null) {
            mLoading!!.dismiss()
        }
    }

    var adapter: BaseQuickAdapter<AllorderListBean.DataBean.ListBean, BaseViewHolder>? = null
    var ordertype: String? = null
    var orderstatus: String? = null
    var page: Int? = null
    var ordertype_listdata = arrayListOf<AllorderListBean.DataBean.ListBean>()


    fun erroreview(): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.view_ordertype_dataerror, null)
        return view
    }

    override fun onResume() {
        super.onResume()
        page = 1
        mPersenter!!.getorderall("2", page.toString(), orderstatus!!, false)
    }

}
