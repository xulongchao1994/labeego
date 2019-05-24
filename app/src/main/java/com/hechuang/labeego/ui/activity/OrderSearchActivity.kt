package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.adapter.SearchTypeAdapter
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.bean.SearchOrderBean
import com.hechuang.labeego.persenter.activity.OrderSearchPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IOrderSearchView
import kotlinx.android.synthetic.main.activity_ordersearch.*
import org.json.JSONArray
import org.json.JSONObject

class OrderSearchActivity : BaseActivity<OrderSearchPersenter>(), IOrderSearchView, View.OnClickListener {
    override fun shouhuo_seccess(data: Order_shouhuo_Bean) {
        page = 1
        mPersenter!!.searchorderlist(page.toString(), orderid!!, searchstr!!, false)
    }

    override fun shouhuo_error(msg: String) {
        MyToast.showMsg(msg)
    }

    var listdata_search = arrayListOf<SearchOrderBean.DataBean.ListBean>()
    override fun getdatasuccess(listdata: List<SearchOrderBean.DataBean.ListBean>, isr: Boolean) {
        if (page == 1) {
            listdata_search.clear()
            if (isr) {
                searchorder_refresh.isRefreshing = false
            } else {
                if (listdata.size < 10) {
                    adapter!!.loadMoreEnd()
                } else {
                    adapter!!.loadMoreComplete()
                }
            }
            adapter!!.setNewData(listdata)
        } else {
            adapter!!.addData(listdata)
            adapter!!.loadMoreComplete()
        }
        listdata_search.addAll(listdata)
    }

    override fun getdataeror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.searchorder_back -> {
                finish()
            }
            R.id.searchorder_orderclass -> {
                //搜索
                page = 1
                mPersenter!!.searchorderlist(page.toString(), orderid!!, searchstr!!, false)
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_ordersearch
    }

    private val search_ordertype = arrayOf("订单号", "商品名称")
    var searchstr: String? = null
    var searchtype: Int? = null
    var adapter: BaseQuickAdapter<SearchOrderBean.DataBean.ListBean, BaseViewHolder>? = null
    var page: Int? = null
    var orderid: String? = null
    override fun initView() {
        searchtype = 1
        page = 1
        searchstr = ""
        searchorder_back.setOnClickListener(this)
        searchorder_orderclass.setOnClickListener(this)
        searchorder_search_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when (searchtype) {
                    1 -> {
                        orderid = searchorder_search_et.text.toString()
                        searchstr = ""
                    }
                    2 -> {
                        searchstr = searchorder_search_et.text.toString()
                        orderid = ""
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        searchorder_search_et.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                //开始搜索
                page = 1
                mPersenter!!.searchorderlist(page.toString(), orderid!!, searchstr!!, false)
            }
            false
        })
        var search_adapter = SearchTypeAdapter(search_ordertype, this)
        searchorder_spiner.adapter = search_adapter
        searchorder_spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var str = search_ordertype[position]
                when (str) {
                    "订单号" -> {
                        searchtype = 1
                    }
                    "商品名称" -> {
                        searchtype = 2
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        searchorder_refresh.setOnRefreshListener {
            page = 1
            mPersenter!!.searchorderlist(page.toString(), orderid!!, searchstr!!, true)
        }
        val layoutli = LinearLayoutManager(this@OrderSearchActivity)
        layoutli.orientation = LinearLayoutManager.VERTICAL
        searchorder_recycler.layoutManager = layoutli
        adapter = object : BaseQuickAdapter<SearchOrderBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allorder_item) {
            override fun convert(helper: BaseViewHolder?, item: SearchOrderBean.DataBean.ListBean?) {
                val recyelr = helper!!.getView<RecyclerView>(R.id.adapter_allorder_recycler)
                val layoutli = LinearLayoutManager(this@OrderSearchActivity)
                layoutli.orientation = LinearLayoutManager.VERTICAL
                recyelr.layoutManager = layoutli
                var recycler_adapter = object : BaseQuickAdapter<SearchOrderBean.DataBean.ListBean.OrderdetailBean, BaseViewHolder>(R.layout.adapter_allorderzixiang_item) {
                    override fun convert(helper: BaseViewHolder?, item: SearchOrderBean.DataBean.ListBean.OrderdetailBean?) {
                        helper!!.setText(R.id.adapter_allorder_goodsname, item!!.proName)
                                .setText(R.id.adapter_allorder_goodstype, item.styleName)
                                .setText(R.id.adapter_allorder_goodsnumber, "×${item.proNum}")
                                .setText(R.id.adapter_allorder_goodsprice, item.price)
                                .setText(R.id.adapter_allorder_goodszeng, item.pv)
                                .setText(R.id.adapter_allorder_storename, item.supplier)
                        val icon = helper.getView<ImageView>(R.id.adapter_allorder_icon)
                        Glide.with(this@OrderSearchActivity).load(item.proImg).into(icon)
                    }
                }

                recyelr.adapter = recycler_adapter
                recycler_adapter.setNewData(item!!.orderdetail)
                recycler_adapter.setOnItemClickListener { adapter, view, position ->
                    //跳转到订单详情
                    val intent = Intent(this@OrderSearchActivity, OrderInfoActivity::class.java)
                    intent.putExtra("orderid", item.innerOrderId)
                    startActivity(intent)
                }
                val button = helper.getView<Button>(R.id.adapter_allorder_orderpay)
                val button_layout = helper.getView<LinearLayout>(R.id.adapter_allorder_pay_layout);
                button.setOnClickListener {
                    when (button.text.toString()) {
                        "确认付款" -> {
                            var json = JSONObject()
                            var jsonarray = JSONArray()
                            jsonarray.put(item.innerOrderId)
                            json.put("json", jsonarray)
                            dismissloading()
                            var intent = Intent(this@OrderSearchActivity, OrderTureActivity::class.java)
                            intent.putExtra("orderbianhao", json.toString())
                            startActivity(intent)
                        }
                        "确认收货" -> {
                            AlertDialog.Builder(this@OrderSearchActivity).setTitle(R.string.app_name)
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
                        .setText(R.id.adapter_allorder_orderzeng, "(${item.zhongfl}蜂力)")

            }
        }
        searchorder_recycler.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //跳转到订单详情
            val intent = Intent(this@OrderSearchActivity, OrderInfoActivity::class.java)
            intent.putExtra("orderid", listdata_search[position].innerOrderId)
            startActivity(intent)
        }
        adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                page = page!! + 1
                mPersenter!!.searchorderlist(page.toString(), orderid!!, searchstr!!, false)
            }
        }, searchorder_recycler)
    }


    override fun onResume() {
        super.onResume()
        page = 1
        mPersenter!!.searchorderlist(page.toString(), orderid!!, searchstr!!, false)
    }

    override fun initPersenter() {
        mPersenter = OrderSearchPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }
}