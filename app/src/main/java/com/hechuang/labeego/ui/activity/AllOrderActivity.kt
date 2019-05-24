package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.AllorderListBean
import com.hechuang.labeego.bean.Allorder_TopBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.persenter.activity.AllOrderPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IAllOrderView
import kotlinx.android.synthetic.main.activity_allorder.*
import org.json.JSONArray
import org.json.JSONObject

class AllOrderActivity : BaseActivity<AllOrderPersenter>(), IAllOrderView, View.OnClickListener {
    override fun shouhuo_seccess(data: Order_shouhuo_Bean) {
        //收货成功
        mPersenter!!.getorderall("1", "1", "")
    }

    override fun shouhuo_error(msg: String) {
        //收货失败
        MyToast.showMsg(msg)
    }

    var page: Int? = null
    var listdata_all = arrayListOf<AllorderListBean.DataBean.ListBean>()
    override fun getlistdataseccess(listdata: List<AllorderListBean.DataBean.ListBean>) {
        if (page == 1) {
            listdata_all.clear()
            adapter!!.setNewData(listdata)
        } else {
            adapter!!.addData(listdata)
        }
        listdata_all.addAll(listdata)
    }

    override fun getlistdataerror(msg: String) {
        MyToast.showMsg(msg)
    }

    var adapter: BaseQuickAdapter<AllorderListBean.DataBean.ListBean, BaseViewHolder>? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.allorder_title_img -> {//全部订单
                val order_intent = Intent(this@AllOrderActivity, OrderTypeActivity::class.java)
                order_intent.putExtra("ordertype", "全部订单")
                startActivity(order_intent)
            }
            R.id.allorder_title_text -> {//全部订单
                val order_intent = Intent(this@AllOrderActivity, OrderTypeActivity::class.java)
                order_intent.putExtra("ordertype", "全部订单")
                startActivity(order_intent)
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_allorder
    }

    override fun initView() {
        page = 1
        allorder_title_img.setOnClickListener(this)
        allorder_title_text.setOnClickListener(this)
        var linenerlayoutmanager = LinearLayoutManager(this)
        linenerlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        allorder_recyclerview.layoutManager = linenerlayoutmanager
        adapter = object : BaseQuickAdapter<AllorderListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allorder_item) {
            override fun convert(helper: BaseViewHolder?, item: AllorderListBean.DataBean.ListBean?) {
                val recyelr = helper!!.getView<RecyclerView>(R.id.adapter_allorder_recycler)
                val layoutli = LinearLayoutManager(this@AllOrderActivity)
                layoutli.orientation = LinearLayoutManager.VERTICAL
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
                        Glide.with(this@AllOrderActivity).load(item.proImg).into(icon)
                    }
                }

                recyelr.adapter = recycler_adapter
                recycler_adapter.setNewData(item!!.orderdetail)
                recycler_adapter.setOnItemClickListener { adapter, view, position ->
                    //跳转到订单详情
                    val intent = Intent(this@AllOrderActivity, OrderInfoActivity::class.java)
                    intent.putExtra("orderid", item.innerOrderId)
                    startActivity(intent)
                }
                val button = helper.getView<Button>(R.id.adapter_allorder_orderpay)
                val button_layout = helper.getView<LinearLayout>(R.id.adapter_allorder_pay_layout)
                button.setOnClickListener {
                    //                    Log.d("allorder", button.text.toString())
                    when (button.text.toString()) {
                        "确认付款" -> {
                            var json = JSONObject()
                            var jsonarray = JSONArray()
                            jsonarray.put(item.innerOrderId)
                            json.put("json", jsonarray)
                            dismissloading()
                            var intent = Intent(this@AllOrderActivity, OrderTureActivity::class.java)
                            intent.putExtra("orderbianhao", json.toString())
                            startActivity(intent)
                        }
                        "确认收货" -> {
                            AlertDialog.Builder(this@AllOrderActivity).setTitle(R.string.app_name)
                                    .setMessage("是否确认收货")
                                    .setNegativeButton("确定") { dialog, which ->
                                        mPersenter!!.postshouhuo(item.innerOrderId)
                                    }
                                    .setPositiveButton("取消") { dialog, which -> }
                                    .show()
                        }
                    }
                }
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
                helper.setText(R.id.adapter_allorder_tiem, item!!.addDate)
                        .setText(R.id.adapter_allorder_ordernumber, item.innerOrderId)
                        .setText(R.id.adapter_allorder_type, item.status)
                        .setText(R.id.adapter_allorder_goodsnumber_zong, item.zhongjg)
                        .setText(R.id.adapter_allorder_orderzeng, "(${item.zhongfl}蜂币)")

            }
        }
        allorder_recyclerview.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //跳转到订单详情
            val intent = Intent(this@AllOrderActivity, OrderInfoActivity::class.java)
            intent.putExtra("orderid", listdata_all[position].innerOrderId)
            startActivity(intent)
        }
        adapter!!.setEnableLoadMore(false)
        adapter!!.addHeaderView(addtopview())
        adapter!!.addFooterView(addbuttonview())
        mPersenter!!.getorderall("1", page.toString(), "")
    }

    fun addtopview(): View {
        var view = LayoutInflater.from(this@AllOrderActivity).inflate(R.layout.view_allorder_top, null)
        var top_recycler = view.findViewById<RecyclerView>(R.id.allorder_top_recycler)
        val gridLayoutManager = GridLayoutManager(this, 4)
        top_recycler.layoutManager = gridLayoutManager
        var top_adapter = object : BaseQuickAdapter<Allorder_TopBean, BaseViewHolder>(R.layout.adapter_allorder_top) {
            override fun convert(helper: BaseViewHolder?, item: Allorder_TopBean?) {
                helper!!.setText(R.id.allorder_top_text, item!!.name)
                helper!!.setImageResource(R.id.allorder_top_icon, item.icon)
            }
        }
        var list1 = Allorder_TopBean(R.drawable.order_daifukuan, "待付款")
        var list2 = Allorder_TopBean(R.drawable.order_daifahuo, "待发货")
        var list3 = Allorder_TopBean(R.drawable.order_daishouhuo, "待收货")
        var list4 = Allorder_TopBean(R.drawable.order_yishouhuo, "已完成")
        var listdata = arrayListOf<Allorder_TopBean>()
        listdata.add(list1)
        listdata.add(list2)
        listdata.add(list3)
        listdata.add(list4)
        top_adapter.setNewData(listdata)
        top_adapter!!.setOnItemClickListener { adapter, view, position ->
            var ordertype = ""
            when (listdata[position].name) {
                "待付款" -> {
                    ordertype = "待付款"
                }
                "待发货" -> {
                    ordertype = "待发货"
                }
                "待收货" -> {
                    ordertype = "待收货"
                }
                "已完成" -> {
                    ordertype = "已完成"
                }
            }
            val order_intent = Intent(this@AllOrderActivity, OrderTypeActivity::class.java)
            order_intent.putExtra("ordertype", ordertype)
            startActivity(order_intent)
        }
        top_recycler.adapter = top_adapter
        top_adapter.setEnableLoadMore(false)
        return view
    }

    fun addbuttonview(): View {
        var view = LayoutInflater.from(this@AllOrderActivity).inflate(R.layout.view_allorder_footview, null)
        var button_text = view.findViewById<TextView>(R.id.allorder_foot_text)
        button_text.setOnClickListener {
            //跳转到全部订单
            val order_intent = Intent(this@AllOrderActivity, OrderTypeActivity::class.java)
            order_intent.putExtra("ordertype", "全部订单")
            startActivity(order_intent)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        page = 1
        mPersenter!!.getorderall("1", page.toString(), "")
    }

    override fun initPersenter() {
        mPersenter = AllOrderPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

}