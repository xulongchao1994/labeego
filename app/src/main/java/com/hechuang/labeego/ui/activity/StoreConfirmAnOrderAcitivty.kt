package com.hechuang.labeego.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Message
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import com.alipay.sdk.app.PayTask
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.alipayutils.PayResult
import com.hechuang.labeego.api.PathConstant
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.ConfirmanOrder_AlipayBean
import com.hechuang.labeego.bean.Order_tijiao
import com.hechuang.labeego.bean.StoreConfirmanorder_Shoppingcar_Bean
import com.hechuang.labeego.persenter.activity.StoreConfirmAnOrdrePersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.Util.Utils
import com.hechuang.labeego.view.activity.IStoreConfirmAnOrderView
import kotlinx.android.synthetic.main.activity_storeconfirmanroder.*
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text

class StoreConfirmAnOrderAcitivty : BaseActivity<StoreConfirmAnOrdrePersenter>(), IStoreConfirmAnOrderView, View.OnClickListener {
    override fun order_tijiao_error(msg: String) {
        istijiao = false
        MyToast.showMsg(msg)
    }

    private val SDK_PAY_FLAG = 1
    var istijiao = false
    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    val payResult = PayResult(msg.obj as Map<String, String>)
                    val resultInfo = payResult.getResult()// 同步返回需要验证的信息
                    val resultStatus = payResult.getResultStatus()
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        if (popupWindow != null && popupWindow!!.isShowing) {
                            popupWindow!!.dismiss()
                        }
                        Toast.makeText(this@StoreConfirmAnOrderAcitivty, "支付成功", Toast.LENGTH_SHORT).show()
                        startorderlist()
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(this@StoreConfirmAnOrderAcitivty, "支付失败", Toast.LENGTH_SHORT).show()
                        if (popupWindow != null && popupWindow!!.isShowing) {
                            popupWindow!!.dismiss()
                        }

                        startorderlist()
                    }
                }
                else -> {
                }
            }
        }
    }


    /**
     * 唤醒支付宝支付
     */
    override fun alipay_data(confirmanOrder_AlipayBean: ConfirmanOrder_AlipayBean) {
        val payRunnable = Runnable {
            val alipay = PayTask(this@StoreConfirmAnOrderAcitivty)
            val result = alipay.payV2(confirmanOrder_AlipayBean.data.list.response, true)
            Log.i("msp", result.toString())

            val msg = Message()
            msg.what = SDK_PAY_FLAG
            msg.obj = result
            mHandler.sendMessage(msg)
        }

        val payThread = Thread(payRunnable)
        payThread.start()
    }

    override fun getalipay_error(string: String) {
        MyToast.showMsg(string)
    }

    //    private var isfriend = false
    var pay_type = 1 //1是支付宝 2是微信
    var allprice = ""

    companion object {
        var STORECONFIRMANORDER: String = "activity.storeconfirmanorderacitivty"
    }

    var deduction = ""//用于判断是否使用 蜂币或广告费或啦蜂
    override fun order_tijiao_seccess(ordertijiao: Order_tijiao.DataBean) {
        istijiao = true
        //弹出支付的弹窗
        var json = JSONObject()
        var jsonarray = JSONArray()
        for (i in 0 until ordertijiao.list.size) {
            jsonarray.put(ordertijiao.list[i])
        }
        json.put("json", jsonarray)
        dismissloading()
        showpooup(json.toString())
    }

    /**
     * 提交订单完成弹出付款的弹出框
     */
    var popupWindow: PopupWindow? = null

    private fun showpooup(orderid: String) {
        popupWindow = PopupWindow(this)
        popupWindow!!.animationStyle = R.style.Animation_AppCompat_Dialog
        popupWindow!!.height = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.setBackgroundDrawable(ColorDrawable(0))
        var view = LayoutInflater.from(this).inflate(R.layout.popup_view, null)
        var conlayout = view.findViewById<LinearLayout>(R.id.popup_con_layout)
        var layout = view.findViewById<LinearLayout>(R.id.popup_layout)
        val recyc = view.findViewById<LinearLayout>(R.id.popup_relative)
        val paytypetext = view.findViewById<TextView>(R.id.popup_paytypetext)
        var zhifubao = view.findViewById<TextView>(R.id.popup_zhifubao)
        var weixin = view.findViewById<TextView>(R.id.popup_weixin)
        var relative = view.findViewById<LinearLayout>(R.id.popup_relative_2)
        var zhifuback = view.findViewById<ImageView>(R.id.popup_zhifu_back)
        var weixin_moren = view.findViewById<ImageView>(R.id.popup_zhifu_weixin_moren)
        var zhifuabo_moren = view.findViewById<ImageView>(R.id.popup_zhifu_zhifubao_moren)
        var allprice = view.findViewById<TextView>(R.id.popup_allprice)
        var pay_button = view.findViewById<Button>(R.id.popup_pay_button)
        val left_to_left = AnimationUtils.loadAnimation(
                this, R.anim
                .slide_left_to_left
        )
        val right_to_lift = AnimationUtils.loadAnimation(this, R.anim.slide_right_to_left)
        val left_to_left_in = AnimationUtils.loadAnimation(this, R.anim.slide_left_to_left_in)
        val lift_to_right = AnimationUtils.loadAnimation(this, R.anim.slide_left_to_right)
        allprice.text = this.allprice
        pay_button.setOnClickListener {
            when (pay_type) {
                1 -> {
                    mPersenter!!.getalipaydata(orderid)
                }
                2 -> {

                }
            }
        }
        zhifuback.setOnClickListener {
            recyc.startAnimation(left_to_left_in)
            recyc.visibility = View.VISIBLE
            relative.startAnimation(lift_to_right)
            relative.visibility = View.GONE
//            button.text = weixin.text
        }
        paytypetext.setOnClickListener {
            recyc.startAnimation(left_to_left)
            recyc.visibility = View.GONE
            relative.startAnimation(right_to_lift)
            relative.visibility = View.VISIBLE
        }
        zhifubao.setOnClickListener {
            recyc.startAnimation(left_to_left_in)
            recyc.visibility = View.VISIBLE
            relative.startAnimation(lift_to_right)
            relative.visibility = View.GONE
            paytypetext.text = "支付宝"
            zhifuabo_moren.visibility = View.VISIBLE
            weixin_moren.visibility = View.GONE
            pay_type = 1
        }
        weixin.setOnClickListener {
            recyc.startAnimation(left_to_left_in)
            recyc.visibility = View.VISIBLE
            relative.startAnimation(lift_to_right)
            relative.visibility = View.GONE
            paytypetext.text = "微信"
            zhifuabo_moren.visibility = View.GONE
            weixin_moren.visibility = View.VISIBLE
            pay_type = 2
        }
//        var window = window
//        var lp = window.attributes
//        lp.gravity = Gravity.BOTTOM
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT
//        window.attributes = lp
        var layoutparans =
                LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 2
                )
//        layoutparans.orientation =
//        layout.layoutParams = layoutparans
        conlayout.setOnClickListener { popupWindow!!.dismiss() }
        popupWindow!!.contentView = view
        popupWindow!!.setOnDismissListener {
            //已经生成订单，跳转到订单页面
            startorderlist()
        }
        popupWindow!!.showAtLocation(storeconfirmanorder_zong, Gravity.BOTTOM, 0, 0)
    }

    private fun wexinpay(orderid: String) {

    }

    fun startorderlist() {
        if (istijiao) {
            var intent = Intent(this@StoreConfirmAnOrderAcitivty, WebsActivity::class.java)
            intent.putExtra("webs", PathConstant.STOREORDERLIST)
            startActivity(intent)
        }
    }

    var adapter_shoppingcar: BaseQuickAdapter<StoreConfirmanorder_Shoppingcar_Bean.DataBean.ListBean, BaseViewHolder>? = null
    var orderdata: StoreConfirmanorder_Shoppingcar_Bean.DataBean? = null
    var allprice_y = ""
    override fun shoppingcar_success(shoppingcar_data: StoreConfirmanorder_Shoppingcar_Bean.DataBean) {
        this.orderdata = shoppingcar_data
        storeconfirmanorder_zong.text = "合计：${shoppingcar_data.sprice}"
        allprice_y = shoppingcar_data.sprice.toString()
        allprice = allprice_y
        adapter_shoppingcar = object : BaseQuickAdapter<StoreConfirmanorder_Shoppingcar_Bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_confirmanorder) {
            override fun convert(helper: BaseViewHolder?, item: StoreConfirmanorder_Shoppingcar_Bean.DataBean.ListBean?) {
                helper!!.setText(R.id.adapter_confirmanroder_store, item!!.supname)
                        .setText(R.id.adapter_confirmanroder_price, item.sprice.toString())
                        .setText(R.id.adapter_confirmanroder_peisong, item.distribution)
                helper!!.addOnClickListener(R.id.adapter_confirmanroder_peisong)
                var linmanager = LinearLayoutManager(this@StoreConfirmAnOrderAcitivty)
                linmanager.orientation = OrientationHelper.VERTICAL
                var recycler = helper.getView<RecyclerView>(R.id.adapter_confirmanroder_recycler)
                recycler.layoutManager = linmanager
                val item_recycler = object : BaseQuickAdapter<StoreConfirmanorder_Shoppingcar_Bean.DataBean.ListBean.NoBean, BaseViewHolder>(R.layout.adapter_confirmanorder_item) {
                    override fun convert(helper: BaseViewHolder?, item: StoreConfirmanorder_Shoppingcar_Bean.DataBean.ListBean.NoBean?) {
                        var icon = helper!!.getView<ImageView>(R.id.confirmanorder_item_icon)
                        Glide.with(this@StoreConfirmAnOrderAcitivty).load(item!!.proimg).into(icon)
                        helper.setText(R.id.confirmanorder_item_name, item.proname)
                                .setText(R.id.confirmanorder_item_price, item.shopprice)
//                                .setText(R.id.confirmanorder_item_integral, item.pv)
                                .setText(R.id.confirmanorder_item_prnum, "×${item.pronum}")
                                .setText(R.id.confirmanorder_item_guige, item.stylename)

                    }
                }
                item_recycler.setNewData(item.no)
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
        adapter_shoppingcar!!.setNewData(shoppingcar_data.list)
        adapter_shoppingcar!!.addHeaderView(get_shoppingcar_view(shoppingcar_data))
        adapter_shoppingcar!!.addFooterView(getbutotnview(shoppingcar_data.adbeefee))
        adapter_shoppingcar!!.setEnableLoadMore(false)
        adapter_shoppingcar!!.setOnItemChildClickListener { adapter, view, position ->
            showpeisongpopup(shoppingcar_data.list[position].distribution)
        }
        storeconfirmanorder_recycler.adapter = adapter_shoppingcar
        dismissloading()
    }

    private fun showpeisongpopup(distribution: String?) {
        var popupWindow = PopupWindow(this)
        popupWindow!!.animationStyle = R.style.Animation_AppCompat_Dialog
        popupWindow!!.height = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.setBackgroundDrawable(ColorDrawable(0))
        var view = LayoutInflater.from(this).inflate(R.layout.popup_storeconfirmanroder_peisong, null)
        var peisongtext = view.findViewById<TextView>(R.id.popup_peisong_text)
        peisongtext.text = distribution
        var peisongbutton = view.findViewById<Button>(R.id.popup_peisong_button)
        val peisonglayout = view.findViewById<LinearLayout>(R.id.popup_peisong_layout)
        peisonglayout.setOnClickListener {
            popupWindow.dismiss()
        }
        peisongbutton.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow!!.contentView = view
        popupWindow!!.showAtLocation(storeconfirmanorder_zong, Gravity.BOTTOM, 0, 0)
    }

    private fun get_shoppingcar_view(shoppingcar_data: StoreConfirmanorder_Shoppingcar_Bean.DataBean): View? {
        var view = LayoutInflater.from(this).inflate(R.layout.view_confirmanorder_recyclertitle, null)
        var shouhuoren = view.findViewById<TextView>(R.id.confirmanorder_view_weizhi_shouhuoren)
        var phone = view.findViewById<TextView>(R.id.confirmanorder_view_weizhi_phonenumber)
        var address = view.findViewById<TextView>(R.id.confirmanorder_view_weizhi_address)
        shouhuoren.text = shoppingcar_data.address.receiveName
        phone.text = shoppingcar_data.address.mobile
        address.text = shoppingcar_data.address.address
        shouhuoren.setOnClickListener(this)
        phone.setOnClickListener(this)
        address.setOnClickListener(this)
        return view
    }

    var fengbisw: Switch? = null
    var guanggaosw: Switch? = null
    var lafengsw: Switch? = null
    fun getbutotnview(beanlist: List<StoreConfirmanorder_Shoppingcar_Bean.DataBean.AdbeefeeBean>): View? {
        val buttonview = LayoutInflater.from(this).inflate(R.layout.adapter_button_store_confirmanorder, null)
        val fengbilayout = buttonview.findViewById<LinearLayout>(R.id.button_store_fengbi_layout)
        val fengbitext = buttonview.findViewById<TextView>(R.id.button_store_fengbi_text)
        fengbisw = buttonview.findViewById<Switch>(R.id.button_store_fengbi_sw)
        val guanggaolayout = buttonview.findViewById<LinearLayout>(R.id.button_store_guanggaofei_layout)
        val guanggaotext = buttonview.findViewById<TextView>(R.id.button_store_guanggaofei_text)
        guanggaosw = buttonview.findViewById<Switch>(R.id.button_store_guanggaofei_sw)
        val lafenglayout = buttonview.findViewById<LinearLayout>(R.id.button_store_lafeng_layout)
        val lafengtext = buttonview.findViewById<TextView>(R.id.button_store_lafeng_text)
        lafengsw = buttonview.findViewById<Switch>(R.id.button_store_lafeng_sw)
        if (beanlist.isNotEmpty()) {
            //------------------蜂币抵扣
            if (beanlist[0].judge == "0") {
                fengbilayout.visibility = View.GONE
            } else {
                fengbilayout.visibility = View.VISIBLE
                fengbitext.text = beanlist[0].show
                fengbisw!!.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        guanggaosw!!.isChecked = false
                        lafengsw!!.isChecked = false
                        deduction = "1"
                        allprice = beanlist[0].money.toString()
                    } else {
                        if (!guanggaosw!!.isChecked && !lafengsw!!.isChecked)
                            allprice = allprice_y
                    }
                    storeconfirmanorder_zong.text = "合计：${allprice}"
                }
            }

            if (beanlist[1].judge == "0") {
                guanggaolayout.visibility = View.GONE
            } else {
                guanggaolayout.visibility = View.VISIBLE
                guanggaotext.text = beanlist[1].show
                guanggaosw!!.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        fengbisw!!.isChecked = false
                        lafengsw!!.isChecked = false
                        deduction = "2"
                        allprice = beanlist[1].money.toString()
                    } else {
                        if (!fengbisw!!.isChecked && !lafengsw!!.isChecked)
                            allprice = allprice_y
                    }
                    storeconfirmanorder_zong.text = "合计：${allprice}"
                }
            }

            if (beanlist[2].judge == "0") {
                lafenglayout.visibility = View.GONE
            } else {
                lafenglayout.visibility = View.VISIBLE
                lafengtext.text = beanlist[2].show
                lafengsw!!.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        fengbisw!!.isChecked = false
                        guanggaosw!!.isChecked = false
                        deduction = "3"
                        allprice = beanlist[2].money.toString()
                    } else {
                        if (!fengbisw!!.isChecked && !guanggaosw!!.isChecked)
                            allprice = allprice_y
                    }
                    storeconfirmanorder_zong.text = "合计：${allprice}"
                }
            }
        }
        return buttonview
    }

    var dialog: AlertDialog? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.storeconfirmanorder_back -> finish()

            R.id.storeconfirmanorder_tijiao -> {//提交按钮
                var isfengbi = fengbisw!!.isChecked
                var isguangao = guanggaosw!!.isChecked
                var islafeng = lafengsw!!.isChecked
                if (!isfengbi || !isguangao || !islafeng) {
                    deduction = "4"
                }
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
                    var json = JSONObject()
                    var arary = JSONArray()
                    for (i in 0 until orderdata!!.list.size) {
                        if (orderdata!!.list[i].message != "") {
                            arary.put("${orderdata!!.list[i].supid}/${orderdata!!.list[i].message}")
                        }
                    }
                    json.put("json", arary)
                    mPersenter!!.ordr_tijiao((if (type == "2") "0" else type).toString()
                            , shocarid!!, orderdata!!.order_token, json.toString(), deduction)
                }
                dialog = AlertDialog.Builder(this)
                        .setView(view)
                        .show()
                Utils.setdialotwidth(this, dialog!!)
            }
            R.id.confirmanorder_view_weizhi_shouhuoren -> {
                startActivity(Intent(this@StoreConfirmAnOrderAcitivty, AddressActivity::class.java))
            }
            R.id.confirmanorder_view_weizhi_phonenumber -> {
                startActivity(Intent(this@StoreConfirmAnOrderAcitivty, AddressActivity::class.java))
            }
            R.id.confirmanorder_view_weizhi_address -> {
                startActivity(Intent(this@StoreConfirmAnOrderAcitivty, AddressActivity::class.java))
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_storeconfirmanroder
    }

    var shocarid: String? = null
    var type: String? = null // 1 立即购买 2 购物车
    override fun initView() {
        shocarid = intent.getStringExtra("shopcartid")
        type = intent.getStringExtra("type")

        storeconfirmanorder_back.setOnClickListener(this)
        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        storeconfirmanorder_recycler.layoutManager = layoutmanager
        storeconfirmanorder_tijiao.setOnClickListener(this)
        mPersenter!!.getdata_shoppingar(shocarid!!, type!!)
    }

    override fun initPersenter() {
        mPersenter = StoreConfirmAnOrdrePersenter(this, this)
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