package com.hechuang.labeego.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.Order_true_bean
import com.hechuang.labeego.bean.Order_ture_list_bean
import com.hechuang.labeego.bean.Orderture_fuwuren
import com.hechuang.labeego.persenter.activity.OrderturePersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.Util.Utils
import com.hechuang.labeego.tools.ui.BasePopupWindow
import com.hechuang.labeego.view.activity.IOrder_tureView
import kotlinx.android.synthetic.main.activity_orderture.*
import java.text.DecimalFormat


class OrderTureActivity : BaseActivity<OrderturePersenter>(), IOrder_tureView, View.OnClickListener {
    override fun getdataerror(msg: String) {
//        AlertDialog.Builder(this)
//                .setTitle(R.string.app_name)
//                .setMessage("订单已过期或不是待付款状态")
//                .setCancelable(false)
//                .setPositiveButton("确定") { dialog, which ->
//                    finish()
//                }
//                .show()
    }

    override fun pay_error(paydata: Order_true_bean) {
        dismissloading()
        MyToast.showMsg(paydata.data.message)
    }

    var pay_ok = false
    var pay_sid = ""
    //    var pay_checktype = "2"
//    var pay_beemoneynumber = ""
//    var pay_beemoneynumber1 = ""
    var isxiaofeiquan = false

    @SuppressLint("SetTextI18n")
    override fun getfuwuren_seccess(fuwuren: Orderture_fuwuren) {
        pay_ok = fuwuren.data.status == "1"
        if (fuwuren.data.status == "1") {
            pay_sid = editText!!.text.toString()
            pay_ok = true
            fuwuren_text!!.text = fuwuren.data.list.trueName + "(" + fuwuren.data.list.mobile + ")"
        } else {
            pay_sid = ""
            pay_ok = false
            fuwuren_text!!.text = ""
        }
    }

    var orderdata: Order_ture_list_bean? = null
    var dialog: AlertDialog? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.orderture_back -> finish()
            R.id.orderture_pay -> {
//                showpopup()
                val view = LayoutInflater.from(this).inflate(R.layout.view_out, null)
                val text = view.findViewById<TextView>(R.id.out_title_text)
                text.text = "是否确认支付？"
                val sure = view.findViewById<Button>(R.id.out_title_queding)
                sure.text = "取消"
                sure.setOnClickListener { dialog!!.dismiss() }
                val quxiao = view.findViewById<Button>(R.id.out_title_quxiao)
                quxiao.text = "确定"
                quxiao.setOnClickListener {
                    dialog!!.dismiss()
                    payorder()

                }
                dialog = AlertDialog.Builder(this)
                        .setView(view)
                        .show()
                Utils.setdialotwidth(this, dialog!!)
            }
        }
    }

    private fun showpopup() {
        var popupwindow = BasePopupWindow(this)
        var view_s = LayoutInflater.from(this).inflate(R.layout.view_orderture_pay, null)
        popupwindow.contentView = view_s
        popupwindow.showAtLocation(orderture_pay, Gravity.BOTTOM, 0, 0)
    }

    private fun payorder() {
//
//        if (pay_checktype == "0") {
//            MyToast.showMsg("请选择一种支付方式支付")
//            return
//        } else if (pay_checktype == "2" && pay_beemoneynumber1 == "") {
//            MyToast.showMsg("请输入蜂币数量")
//            return
//        } else if (pay_checktype == "1" && pay_beemoneynumber == "") {
//            MyToast.showMsg("请输入消费券数量")
//            return
//        } else {
//            if (pay_checktype == "2") {
//                Log.d("orderture", pay_beemoneynumber + "  " + orderdata!!.data.beemoneynummin)
//                if (pay_beemoneynumber1.toFloat() < orderdata!!.data.beemoneynummin.toFloat()) {
//                    MyToast.showMsg("您输入的蜂币低于最低可使用值")
////                    MyToast.showMsg("输入啦蜂必须在${orderdata!!.data.beemoneynummin}至${orderdata!!.data.beemoneynum}之间")
//                    return
//                }
//                if (pay_beemoneynumber1.toFloat() > orderdata!!.data.beemoneynum.toFloat()) {
//                    MyToast.showMsg("您输入的蜂币高于最高可使用值")
//                    return
//                }
//            } else if (pay_checktype == "1") {
//                if (pay_beemoneynumber.toFloat() < orderdata!!.data.beemoneynummin.toFloat()) {
//                    MyToast.showMsg("您输入的消费券低于最低可使用值")
////                    MyToast.showMsg("输入啦蜂必须在${orderdata!!.data.beemoneynummin}至${orderdata!!.data.beemoneynum}之间")
//                    return
//                }
//                if (pay_beemoneynumber.toFloat() > orderdata!!.data.beemoneynum.toFloat()) {
//                    MyToast.showMsg("您输入的消费券高于最高可使用值")
//                    return
//                }
//            }
//        }
        var siquan = ""
        if (isxiaofeiquan) {
            siquan = "1"
        } else {
            siquan = "0"
        }
        when (orderdata!!.data.type) {
            "1" -> {//该会员不在体系内不需要填服务人
                mPersenter!!.postorderpay(orderbianhao!!, orderdata!!.data.pay_token, pay_sid, siquan)
            }
            "2" -> {//该会员在体系内但服务人没有值（需要填）
                if (pay_ok) {
                    mPersenter!!.postorderpay(orderbianhao!!, orderdata!!.data.pay_token, pay_sid, siquan)
                } else {
                    MyToast.showMsg("没有查找到服务人")
                }
            }
            "3" -> {//该会员在体系内且服务人有值
                if (pay_ok) {
                    mPersenter!!.postorderpay(orderbianhao!!, orderdata!!.data.pay_token, pay_sid, siquan)
                } else {
                    MyToast.showMsg("没有查找到服务人")
                }
            }
        }
    }

    var heji_str: Float? = null
    var userzong: Float? = null
    override fun getdataok(lisdata: Order_ture_list_bean) {
        if (lisdata.data.status.equals("1")) {
            this.orderdata = lisdata!!
//            Log.d("orderture_listdata", lisdata!!.data.toString())
            orderture_heji.text = "合计：${lisdata.data.heji}元"
            heji_str = lisdata.data.heji.toFloat()
            var z = lisdata.data.usermoney.substring(0, lisdata.data.usermoney.length - 1)
//            Log.d("orderture", lisdata.data.usermoney + "   " + z)
            userzong = z.toFloat()
            adapter!!.setNewData(lisdata.data.list)
            orderture_recycler.adapter = adapter
            adapter!!.addHeaderView(getheaderview(lisdata))
            adapter!!.addFooterView(getfooterview(lisdata))
//            Log.d("orderture", lisdata.data.type)
//            if (lisdata.data.type == "1") {
//            }
        } else {
            AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage(lisdata.data.message)
                    .setCancelable(false)
                    .setPositiveButton("确定") { dialog, which ->
                        finish()
                    }
                    .show()
        }
        dismissloading()
    }

    var fuwuren_text: TextView? = null
    var editText: EditText? = null
    private fun getfooterview(lisdata: Order_ture_list_bean): View? {
        val view = LayoutInflater.from(this)
                .inflate(R.layout.view_orderture_button, null)
        val text_price = view.findViewById<TextView>(R.id.orderture_moeny)
        text_price.text = "和币（${lisdata.data.usermoney})"
        val fengbi_price = view.findViewById<TextView>(R.id.orderture_beemoney)
        fengbi_price.text = "蜂币（${lisdata.data.beemoney}）"
        val hebi_pay = view.findViewById<TextView>(R.id.orderture_moeny_moeny)
        hebi_pay.text = "支付 ¥${lisdata.data.usermoneymax}"
        var beemoeny_pay = view.findViewById<TextView>(R.id.orderture_beemoney_moeny)
        beemoeny_pay.text = "支付 ¥${lisdata.data.beemoneynum}"
        val fuwurenlayout = view.findViewById<LinearLayout>(R.id.orderture_fuwuren_layout)
        val fuwurentext = view.findViewById<TextView>(R.id.orderture_sure_fuwuren)
        editText = view.findViewById(R.id.orderture_fuwuren_et)
        editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mPersenter!!.getfuwuren(editText!!.text.toString())
            }
        })
        fuwuren_text = view.findViewById(R.id.orderture_fuwuren_xianshi)
//        val isfengbi = view.findViewById<CheckBox>(R.id.orderture_fengbi_cb)
//        val fengbi_layout = view.findViewById<LinearLayout>(R.id.orderture_fengbi_layout)
//        isfengbi.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                fengbi_layout.visibility = View.VISIBLE
//                pay_checktype = "1"
//            } else {
//                fengbi_layout.visibility = View.GONE
//                pay_checktype = "0"
//            }
//        }
//        val pay_fengbi = view.findViewById<CheckBox>(R.id.orderture_paytype_fengbi_cb)
//        val pay_lanfeng = view.findViewById<CheckBox>(R.id.orderture_paytype_lafeng_cb)
//        val fengbiname = view.findViewById<TextView>(R.id.orderture_fengbi_name)
//        val fengbinumber = view.findViewById<TextView>(R.id.orderture_beemoneynum)
//        val fengbi_et = view.findViewById<EditText>(R.id.orderture_fengbi_et)
//        val fengbi_yue = view.findViewById<TextView>(R.id.orderture_fengbi_yue)
        val xiaofeiquan = view.findViewById<CheckBox>(R.id.orderture_paytype_xiaofeiquan_cb)
        var paytype_drawable = resources.getDrawable(R.drawable.ordertrue_paytype)
//        var paytype_drawable2 = resources.getDrawable(R.drawable.ordertrue_paytype2)
        val scale = this.resources.displayMetrics.density.toInt()
        paytype_drawable.setBounds(0, 0, (15 * scale + 0.5f).toInt(), (15 * scale + 0.5f).toInt())
//        paytype_drawable2.setBounds(0, 0, (20 * scale + 0.5f).toInt(), (20 * scale + 0.5f).toInt())
        xiaofeiquan.setCompoundDrawables(paytype_drawable, null, null, null)
        xiaofeiquan.text = "   广告费（支付${lisdata.data.couponmax},余额${lisdata.data.couponbalance})"
        xiaofeiquan.setOnCheckedChangeListener { buttonView, isChecked ->
            isxiaofeiquan = isChecked
            val decimalFormat = DecimalFormat("###.00")
            var bee_f = lisdata.data.beemoneynum.toFloat()
            var cou_f = lisdata.data.couponmax.toFloat()
            Log.d("orderture", "$bee_f $cou_f")
            if (isChecked) {
                beemoeny_pay.text = "支付 ¥${String.format("%1.2f", bee_f - cou_f)}"
            } else {
                beemoeny_pay.text = "支付 ¥${lisdata.data.beemoneynum}"
            }
        }
//        pay_lanfeng.setCompoundDrawables(paytype_drawable2, null, null, null)
//        pay_checktype = "2"
//        pay_fengbi.isChecked = true
//        pay_lanfeng.isChecked = false
//        fengbiname.text = "蜂币数量"
//        if (!lisdata.data.beemoneynummin.equals("") && !lisdata.data.beemoneynum.equals("")) {
//            fengbinumber.text = "(最少使用${lisdata.data.beemoneynummin} - 最多使用${lisdata.data.beemoneynum}蜂币)"
//        } else if (lisdata.data.beemoneynummin.equals("") && !lisdata.data.beemoneynum.equals("")) {
//            fengbinumber.text = "(最多使用 ${lisdata.data.beemoneynum}蜂币)"
//        } else if (!lisdata.data.beemoneynummin.equals("") && lisdata.data.beemoneynum.equals("")) {
//            fengbinumber.text = "(最少使用 ${lisdata.data.beemoneynummin}蜂币)"
//        }
//        fengbi_yue.text = "蜂币余额：${lisdata.data.beemoney}"
//        fengbi_et.hint = "请输入使用的蜂币数量"
//        if (lisdata.data.beemoneynummin == null) {
//            lisdata.data.beemoneynummin = ""
//        }
//        if (lisdata.data.beemoneynum == null) {
//            lisdata.data.beemoneynum = ""
//        }
//
//        fengbi_et.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_CLASS_NUMBER
//        fengbi_et.filters = arrayOf(InputFilter { source, start, end, dest, _, dend ->
//            if (source == "." && dest.toString().isEmpty()) {
//                return@InputFilter "0."
//            }
//            if (dest.toString().contains(".")) {
//                val index = dest.toString().indexOf(".")
//                val mlength = dest.toString().substring(index).length
//                if (mlength == 3) {
//                    return@InputFilter ""
//                }
//            }
//            null
//        })
//
//        pay_fengbi.setOnCheckedChangeListener { buttonView, isChecked ->
//            //            Log.d("ordertrue_pay_fengbi", isChecked.toString())
//            if (isChecked) {
//                pay_checktype = "2"
//                pay_lanfeng.isChecked = false
//                fengbi_layout.visibility = View.VISIBLE
//                fengbiname.text = "蜂币数量"
//                if (!lisdata.data.beemoneynummin.equals("") && !lisdata.data.beemoneynum.equals("")) {
//                    fengbinumber.text = "(最少使用${lisdata.data.beemoneynummin} - 最多使用${lisdata.data.beemoneynum}蜂币)"
//                } else if (lisdata.data.beemoneynummin.equals("") && !lisdata.data.beemoneynum.equals("")) {
//                    fengbinumber.text = "(最多使用 ${lisdata.data.beemoneynum}蜂币)"
//                } else if (!lisdata.data.beemoneynummin.equals("") && lisdata.data.beemoneynum.equals("")) {
//                    fengbinumber.text = "(最少使用 ${lisdata.data.beemoneynummin}蜂币)"
//                }
//                fengbi_yue.text = "蜂币余额：${lisdata.data.beemoney}"
////                Log.d("ordertrue_pay_fengbi", pay_beemoneynumber1)
//                if (pay_beemoneynumber1 == "") {
//                    fengbi_et.hint = "请输入使用的蜂币数量"
//                } else {
//                    fengbi_et.setText(pay_beemoneynumber1)
//                    fengbi_et.setSelection(fengbi_et.text.toString().length)
//                }
//            } else {
//                if (!pay_lanfeng.isChecked) {
//                    pay_checktype = "0"
//                    fengbi_layout.visibility = View.GONE
//                }
//            }
//        }
//
//        pay_lanfeng.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                pay_checktype = "1"
//                pay_fengbi.isChecked = false
//                fengbi_layout.visibility = View.VISIBLE
//                fengbiname.text = "消费券数量"
//                if (!lisdata.data.couponmin.equals("") && !lisdata.data.couponmax.equals("")) {
//                    fengbinumber.text = "(最少使用${lisdata.data.couponmin} - 最多使用${lisdata.data.couponmax}消费券)"
//                } else if (lisdata.data.couponmin.equals("") && !lisdata.data.couponmax.equals("")) {
//                    fengbinumber.text = "(最多使用 ${lisdata.data.couponmax}消费券)"
//                } else if (!lisdata.data.couponmin.equals("") && lisdata.data.couponmax.equals("")) {
//                    fengbinumber.text = "(最少使用 ${lisdata.data.couponmin}消费券)"
//                }
//                fengbi_yue.text = "消费券余额：${lisdata.data.couponbalance}"
//                if (pay_beemoneynumber == "") {
//                    fengbi_et.hint = "请输入使用的消费券数量"
//                } else {
//                    fengbi_et.setText(pay_beemoneynumber)
//                    fengbi_et.setSelection(fengbi_et.text.toString().length)
//                }
//            } else {
//                if (!pay_fengbi.isChecked) {
//                    pay_checktype = "0"
//                    fengbi_layout.visibility = View.GONE
//                }
//            }
//        }
//        fengbi_et.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
////                if (pay_checktype == "2") {
//                pay_beemoneynumber1 = fengbi_et.text.toString().trim()
////                    pay_beemoneynumber = ""
////                } else {
//                pay_beemoneynumber = fengbi_et.text.toString().trim()
////                    pay_beemoneynumber1 = ""
////                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })
        when (lisdata.data.type) {
            "1" -> {//该会员不在体系内不需要填服务人
                fuwurenlayout.visibility = View.GONE
                fuwurentext.visibility = View.GONE
            }
            "2" -> {//该会员在体系内但服务人没有值（需要填）
                fuwurenlayout.visibility = View.VISIBLE
                fuwurentext.visibility = View.VISIBLE
            }
            "3" -> {//该会员在体系内且服务人有值
                pay_ok = true
                fuwurenlayout.visibility = View.VISIBLE
                fuwurentext.visibility = View.VISIBLE
                editText!!.setText(lisdata.data.serviceid)
                fuwuren_text!!.text = "${lisdata.data.servicename} ( ${lisdata.data.servicemobile} )"
                pay_sid = lisdata.data.serviceid
            }
        }
        return view
    }

    private fun getheaderview(lisdata: Order_ture_list_bean): View? {
        val view = LayoutInflater.from(this).inflate(R.layout.view_orderture_title, null)
        val text_title = view.findViewById<TextView>(R.id.orderture_yixuan_number)
        text_title.text = "已选商品( ${lisdata.data.pronum} )"
        return view
    }

    override fun pay_ok(paydata: Order_true_bean) {
        dismissloading()
        MyToast.showMsg(paydata.data.message)
        if (paydata.data.status == 1) {
            var intent = Intent(this@OrderTureActivity, OrderActivity::class.java)
            intent.putExtra("ordertype", "全部")
            startActivity(intent)
            finish()
        } else {
        }
    }

    var adapter: BaseQuickAdapter<Order_ture_list_bean.DataBean.ListBean, BaseViewHolder>? = null
    override fun initlayout(): Int {
        return R.layout.activity_orderture
    }

    var orderbianhao: String? = null
    override fun initView() {
//        pay_checktype = "1"
        orderbianhao = intent.getStringExtra("orderbianhao")
        var linmanager = LinearLayoutManager(this)
        linmanager.orientation = LinearLayoutManager.VERTICAL
        orderture_recycler.layoutManager = linmanager
        adapter = object : BaseQuickAdapter<Order_ture_list_bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_ordertureitem) {
            override fun convert(helper: BaseViewHolder?, item: Order_ture_list_bean.DataBean.ListBean?) {
                helper!!.setText(R.id.orderture_goods, item!!.proName)
                        .setText(R.id.orderture_goodsnum, "×${item.proNum}")
                        .setText(R.id.orderture_price, item.price)
            }
        }
        adapter!!.setEnableLoadMore(false)
        mPersenter!!.getorderpay(orderbianhao!!)
        orderture_back.setOnClickListener(this)
        orderture_pay.setOnClickListener(this)
    }

    override fun initPersenter() {
        mPersenter = OrderturePersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

}