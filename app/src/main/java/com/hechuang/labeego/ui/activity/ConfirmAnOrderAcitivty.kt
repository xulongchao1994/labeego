package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.Confirmanorder_Shoppingcar_Bean
import com.hechuang.labeego.bean.Order_tijiao
import com.hechuang.labeego.persenter.activity.ConfirmAnOrdrePersenter
import com.hechuang.labeego.tools.Util.Utils
import com.hechuang.labeego.view.activity.IConfirmAnOrderView
import kotlinx.android.synthetic.main.activity_confirmanroder.*
import org.json.JSONArray
import org.json.JSONObject

class ConfirmAnOrderAcitivty : BaseActivity<ConfirmAnOrdrePersenter>(), IConfirmAnOrderView, View.OnClickListener {
    private var isfriend = false

    companion object {
        var CONFIRMANORDER: String = "activity.confirmanorderactivity"
    }

    override fun order_tijiao_seccess(ordertijiao: Order_tijiao.DataBean) {
        if (isfriend) {//跳转到个人中心
            val intent = Intent(LoginActivity.LOGINACTION)
            intent.putExtra("weizhi", 2)
            sendBroadcast(intent)
            val intent1 = Intent(this@ConfirmAnOrderAcitivty, LabeegoActivity::class.java)
            intent1.putExtra("weizhi", 2)
            startActivity(intent1)
            finish()
        } else {
            //跳转到确认订单页面
            var json = JSONObject()
            var jsonarray = JSONArray()
            for (i in 0 until ordertijiao.list.size) {
                jsonarray.put(ordertijiao.list[i])
            }
            json.put("json", jsonarray)
            dismissloading()
            var intent = Intent(this@ConfirmAnOrderAcitivty, OrderTureActivity::class.java)
            intent.putExtra("orderbianhao", json.toString())
            startActivity(intent)
            finish()
        }
    }


    var adapter_shoppingcar: BaseQuickAdapter<Confirmanorder_Shoppingcar_Bean.DataBean.ListBean, BaseViewHolder>? = null
    var orderdata: Confirmanorder_Shoppingcar_Bean.DataBean? = null
    override fun shoppingcar_success(shoppingcar_data: Confirmanorder_Shoppingcar_Bean.DataBean) {
//        Log.d("confirm", shoppingcar_data.order_token)
        this.orderdata = shoppingcar_data
        confirmanorder_zong.text = "合计：${shoppingcar_data.zongjia}"
        adapter_shoppingcar = object : BaseQuickAdapter<Confirmanorder_Shoppingcar_Bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_confirmanorder) {
            override fun convert(helper: BaseViewHolder?, item: Confirmanorder_Shoppingcar_Bean.DataBean.ListBean?) {
                helper!!.setText(R.id.adapter_confirmanroder_store, item!!.supname)
                        .setText(R.id.adapter_confirmanroder_price, item.zong)
                var linmanager = LinearLayoutManager(this@ConfirmAnOrderAcitivty)
                linmanager.orientation = OrientationHelper.VERTICAL
                var recycler = helper.getView<RecyclerView>(R.id.adapter_confirmanroder_recycler)
                recycler.layoutManager = linmanager
                val item_recycler = object : BaseQuickAdapter<Confirmanorder_Shoppingcar_Bean.DataBean.ListBean.ProductsBean, BaseViewHolder>(R.layout.adapter_confirmanorder_item) {
                    override fun convert(helper: BaseViewHolder?, item: Confirmanorder_Shoppingcar_Bean.DataBean.ListBean.ProductsBean?) {
                        var icon = helper!!.getView<ImageView>(R.id.confirmanorder_item_icon)
                        Glide.with(this@ConfirmAnOrderAcitivty).load(item!!.proImg).into(icon)
                        helper.setText(R.id.confirmanorder_item_name, item.supplier)
                                .setText(R.id.confirmanorder_item_price, item.money)
                                .setText(R.id.confirmanorder_item_integral, item.pv)
                                .setText(R.id.confirmanorder_item_prnum, "×${item.proNum}")
                                .setText(R.id.confirmanorder_item_guige, item.styleName)
                    }
                }
                item_recycler.setNewData(item.products)
                recycler.adapter = item_recycler
                var input = helper.getView<EditText>(R.id.adapter_confirmanroder_liuyan)
                input.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        item.message = input.text.toString()
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
            }
        }
//        Log.d("confirm", shoppingcar_data.data.list.size.toString())
        adapter_shoppingcar!!.setNewData(shoppingcar_data.list)
        adapter_shoppingcar!!.addHeaderView(get_shoppingcar_view(shoppingcar_data))
        adapter_shoppingcar!!.addFooterView(getbutotnview())
        adapter_shoppingcar!!.setEnableLoadMore(false)
        confirmanorder_recycler.adapter = adapter_shoppingcar
        dismissloading()
    }

    private fun get_shoppingcar_view(shoppingcar_data: Confirmanorder_Shoppingcar_Bean.DataBean): View? {
        var view = LayoutInflater.from(this).inflate(R.layout.view_confirmanorder_recyclertitle, null)
        var shouhuoren = view.findViewById<TextView>(R.id.confirmanorder_view_weizhi_shouhuoren)
        var phone = view.findViewById<TextView>(R.id.confirmanorder_view_weizhi_phonenumber)
        var address = view.findViewById<TextView>(R.id.confirmanorder_view_weizhi_address)
        shouhuoren.text = shoppingcar_data.receiveName
        phone.text = shoppingcar_data.mobile
        address.text = shoppingcar_data.diqu
        shouhuoren.setOnClickListener(this)
        phone.setOnClickListener(this)
        address.setOnClickListener(this)
        return view
    }

    fun getbutotnview(): View? {
        val buttonview = LayoutInflater.from(this).inflate(R.layout.adapter_button_confirmanorder, null)
        var isfriend_c = buttonview.findViewById<CheckBox>(R.id.confirmanorder_adapter_button_isfriend)
        isfriend_c.setOnCheckedChangeListener { buttonView, isChecked ->
            isfriend = isChecked
        }
        return buttonview
    }

    var dialog: AlertDialog? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.confirmanorder_back -> finish()

            R.id.confirmanorder_tijiao -> {//提交按钮
                val view = LayoutInflater.from(this).inflate(R.layout.view_out, null)
                val text = view.findViewById<TextView>(R.id.out_title_text)
                if (isfriend)
                    text.text = "是否确认请朋友支付？"
                else
                    text.text = "是否确认支付？"
                val sure = view.findViewById<Button>(R.id.out_title_queding)
                sure.text = "取消"
                sure.setOnClickListener { dialog!!.dismiss() }
                val quxiao = view.findViewById<Button>(R.id.out_title_quxiao)
                quxiao.text = "确定"
                quxiao.setOnClickListener {
                    dialog!!.dismiss()
                    var json = JSONObject()
                    var arary = JSONArray()
                    for (i in 0 until orderdata!!.list.size) {
                        if (orderdata!!.list[i].message != "") {
                            arary.put("${orderdata!!.list[i].supId}/${orderdata!!.list[i].message}")
                        }
                    }
                    json.put("json", arary)
                    if (isfriend) {
                        mPersenter!!.ordr_tijiao(orderdata!!.id, type!!, shocarid!!, orderdata!!.order_token, json.toString(), "2")
                    } else {
                        mPersenter!!.ordr_tijiao(orderdata!!.id, type!!, shocarid!!, orderdata!!.order_token, json.toString(), "")
                    }
                }
                dialog = AlertDialog.Builder(this)
                        .setView(view)
                        .show()
                Utils.setdialotwidth(this, dialog!!)
            }
            R.id.confirmanorder_view_weizhi_shouhuoren -> {
                startActivity(Intent(this@ConfirmAnOrderAcitivty, AddressActivity::class.java))
            }
            R.id.confirmanorder_view_weizhi_phonenumber -> {
                startActivity(Intent(this@ConfirmAnOrderAcitivty, AddressActivity::class.java))
            }
            R.id.confirmanorder_view_weizhi_address -> {
                startActivity(Intent(this@ConfirmAnOrderAcitivty, AddressActivity::class.java))
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_confirmanroder
    }

    var shocarid: String? = null
    var type: String? = null // 1 立即购买 2 购物车
    override fun initView() {
        shocarid = intent.getStringExtra("shopcartid")
        type = intent.getStringExtra("type")
        confirmanorder_back.setOnClickListener(this)
        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        confirmanorder_recycler.layoutManager = layoutmanager
        confirmanorder_tijiao.setOnClickListener(this)
//        Log.d("confirmanorder", "$shocarid $type")
//        mPersenter!!.getdata_shoppingar(shocarid!!, type!!)
    }

    override fun initPersenter() {
        mPersenter = ConfirmAnOrdrePersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    override fun onResume() {
        super.onResume()
        mPersenter!!.getdata_shoppingar(shocarid!!, type!!)
    }
}