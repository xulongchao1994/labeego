package com.hechuang.labeego.ui.activity

import android.R.attr.data
import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.OrderInfoBean
import com.hechuang.labeego.bean.Order_shouhuo_Bean
import com.hechuang.labeego.persenter.activity.OrderInfoPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IOrderInfoView
import kotlinx.android.synthetic.main.activity_orderinfo.*
import org.json.JSONArray
import org.json.JSONObject


class OrderInfoActivity : BaseActivity<OrderInfoPersenter>(), IOrderInfoView, View.OnClickListener {
    override fun shouhuo_seccess(data: Order_shouhuo_Bean) {
        MyToast.showMsg(data.data.message)
        mPersenter!!.getdata_srccess(orderid!!)
    }

    var hand = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg.toString()) {
                "1" -> {
                    tiem_c!!.cancel()
                }
            }
        }

    }

    override fun shouhuo_error(msg: String) {
        MyToast.showMsg(msg)
    }

    var ordertype_str: String? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.orderinfo_back -> {
                finish()
            }
            R.id.orderinfo_pay_button -> {
                when (ordertype_str) {
                    "付款" -> {
                        var json = JSONObject()
                        var jsonarray = JSONArray()
                        jsonarray.put(orderid)
                        json.put("json", jsonarray)
                        dismissloading()
                        var intent = Intent(this@OrderInfoActivity, OrderTureActivity::class.java)
                        intent.putExtra("orderbianhao", json.toString())
                        startActivity(intent)
                    }
                    "收货" -> {
                        mPersenter!!.postshouhuo(orderid!!)
                    }
                    "评价" -> {
                        var intent = Intent(this@OrderInfoActivity, CommentActivity::class.java)
                        intent.putExtra("id", orderid)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun getdataseccess(data: OrderInfoBean) {
        var infodata = data.data.list
        orderinfo_ordertype.text = infodata.status
        orderinfo_shouhuoren.text = infodata.receiveName
        orderinfo_phonenum.text = infodata.userTel
        orderinfo_address.text = infodata.address
        orderinfo_supplier.text = infodata.supplier
        if (infodata.message != "") {
            orderinfo_message_layout.visibility = View.VISIBLE
            orderinfo_message.text = "买家留言：${infodata.message}"
        } else {
            orderinfo_message_layout.visibility = View.GONE
        }
        if (infodata.adminMessage != "") {
            orderinfo_adminmessage_layout.visibility = View.VISIBLE
            orderinfo_adminmessage.text = "管理员家留言：${infodata.adminMessage}"
        } else {
            orderinfo_adminmessage_layout.visibility = View.GONE
        }
        if (infodata.serviceID != "" ) {
            orderinfo_fuwuren.visibility = View.VISIBLE
            orderinfo_fuwuren.text = "服务人：${infodata.serviceID}"
        } else {
            orderinfo_fuwuren.visibility = View.GONE
        }
        orderinfo_ordernumber.text = "订单号：${infodata.innerOrderId}"
        if (infodata.addDate != "") {
            orderinfo_addtime.text = "下单时间：${infodata.addDate}"
        } else {
            orderinfo_addtime.visibility = View.VISIBLE
            orderinfo_addtime.visibility = View.GONE
        }
        if (infodata.payDate != "") {
            orderinfo_paytime.visibility = View.VISIBLE
            orderinfo_paytime.text = "付款时间：${infodata.payDate}"
        } else {
            orderinfo_paytime.visibility = View.GONE
        }

        if (infodata.deliverDate != "") {
            orderinfo_fahuotime.visibility = View.VISIBLE
            orderinfo_fahuotime.text = "发货时间：${infodata.deliverDate}"
        } else {
            orderinfo_fahuotime.visibility = View.GONE
        }
        if (infodata.receiveDate != "") {
            orderinfo_shouhuotime.visibility = View.VISIBLE
            orderinfo_shouhuotime.text = "收货时间：${infodata.receiveDate}"
        } else {
            orderinfo_shouhuotime.visibility = View.GONE
        }
        orderinfo_zong.text = infodata.zhongjg
        orderinfo_song.text = "(${infodata.zhongfl}"
        when (infodata.status) {
            "待付款" -> {
                Glide.with(this).load(R.drawable.dfk).into(orderinfo_ordertype_img)
                ordertype_str = "付款"
                orderinfo_pay_button.text = "确认付款"
                orderinfo_pay_layout.visibility = View.VISIBLE
                orderinfo_order_endtime.visibility = View.VISIBLE
                orderinfo_pay_button.setBackgroundResource(R.drawable.goodsinfo_shoppcarnumber)
                tiem_cishu = tiem_cishu!! + 1
                if (tiem_cishu!! > 0) {
                    if (tiem_cishu!! > 1) {
                        tiem_c!!.cancel()
                    }
                    starttime(data.data.list.secondtime.toLong())
                }
            }
            "已付款" -> {//显示待发货
                Glide.with(this).load(R.drawable.dfh).into(orderinfo_ordertype_img)
                orderinfo_pay_layout.visibility = View.GONE
                orderinfo_order_endtime.visibility = View.GONE
            }

            "配货中" -> {//显示待发货
                Glide.with(this).load(R.drawable.dfh).into(orderinfo_ordertype_img)
                orderinfo_pay_layout.visibility = View.GONE
                orderinfo_order_endtime.visibility = View.GONE
            }
            "待发货" -> {
                Glide.with(this).load(R.drawable.dfh).into(orderinfo_ordertype_img)
                orderinfo_pay_button.visibility = View.GONE
                orderinfo_order_endtime.visibility = View.GONE
            }
            "已发货" -> {
                Glide.with(this).load(R.drawable.dsh).into(orderinfo_ordertype_img)
                ordertype_str = "收货"
                orderinfo_pay_button.text = "确认收货"
                orderinfo_pay_layout.visibility = View.VISIBLE
                orderinfo_order_endtime.visibility = View.GONE
                orderinfo_pay_button.setBackgroundResource(R.drawable.orderinfo_shouhuo)
            }
            "待收货" -> {
                Glide.with(this).load(R.drawable.dsh).into(orderinfo_ordertype_img)
                ordertype_str = "收货"
                orderinfo_pay_button.text = "确认收货"
                orderinfo_pay_layout.visibility = View.VISIBLE
                orderinfo_order_endtime.visibility = View.GONE
                orderinfo_pay_button.setBackgroundResource(R.drawable.orderinfo_shouhuo)
            }
            "已完成" -> {
                Glide.with(this).load(R.drawable.ywc).into(orderinfo_ordertype_img)
                if (infodata.comment_status == "0") {
                    ordertype_str = "评价"
                    orderinfo_pay_button.text = "去评价"
                    orderinfo_pay_layout.visibility = View.VISIBLE
                    orderinfo_order_endtime.visibility = View.GONE
                    orderinfo_pay_button.setBackgroundResource(R.drawable.orderinfo_pingjia)
                } else {
                    orderinfo_pay_layout.visibility = View.GONE
                    orderinfo_order_endtime.visibility = View.GONE
                }
            }
            "已收货" -> {
                Glide.with(this).load(R.drawable.ywc).into(orderinfo_ordertype_img)
                if (infodata.comment_status == "0") {
                    ordertype_str = "评价"
                    orderinfo_pay_button.text = "去评价"
                    orderinfo_pay_layout.visibility = View.VISIBLE
                    orderinfo_order_endtime.visibility = View.GONE
                    orderinfo_pay_button.setBackgroundResource(R.drawable.orderinfo_pingjia)
                } else {
                    orderinfo_pay_layout.visibility = View.GONE
                    orderinfo_order_endtime.visibility = View.GONE
                }
            }
            "过期订单" -> {
                Glide.with(this).load(R.drawable.yqx).into(orderinfo_ordertype_img)
                orderinfo_pay_layout.visibility = View.GONE
                orderinfo_order_endtime.visibility = View.GONE
            }
            "已取消" -> {
                Glide.with(this).load(R.drawable.yqx).into(orderinfo_ordertype_img)
                orderinfo_pay_layout.visibility = View.GONE
                orderinfo_order_endtime.visibility = View.GONE
            }
            "后台取消" -> {
                Glide.with(this).load(R.drawable.yqx).into(orderinfo_ordertype_img)
                orderinfo_pay_layout.visibility = View.GONE
                orderinfo_order_endtime.visibility = View.GONE
            }
            "取消待处理" -> {
                Glide.with(this).load(R.drawable.yqx).into(orderinfo_ordertype_img)
                orderinfo_pay_layout.visibility = View.GONE
                orderinfo_order_endtime.visibility = View.GONE
            }
        }
        orderinfo_ordernumber_copy.setOnClickListener {
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.text = infodata.innerOrderId
            MyToast.showMsg("已将订单号复制到粘贴板")
        }
        Log.d("ordeinfo", data.data.list.orderdetail.size.toString())
        recycler_adapter!!.setNewData(data.data.list.orderdetail)
        recycler_adapter!!.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(this@OrderInfoActivity, GoodsInfoActivity::class.java)
            intent.putExtra("pid", data.data.list.orderdetail[position].proId)
            intent.putExtra("proimg", data.data.list.orderdetail[position].proImg)
            startActivity(intent)
        }

    }

    override fun getdataerror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun initlayout(): Int {
        return R.layout.activity_orderinfo
    }

    var tiem_c: CountDownTimer? = null
    fun starttime(time: Long) {
        val xiaoshi = 1000 * 60 * 60
        val fenzhong = 1000 * 60
        tiem_c = object : CountDownTimer(time * 1000, 1000) {
            override fun onFinish() {
//                orderinfo_order_endtime.visibility = View.GONE
//                mPersenter!!.getdata_srccess(orderid!!)
                val msg = Message.obtain()
                msg.obj = data
                msg.what = 1   //标志消息的标志
                hand.sendMessage(msg)
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.d("orderinfo", millisUntilFinished.toString())
                if (millisUntilFinished > xiaoshi) { //小时
                    val jixiaoshi = millisUntilFinished / xiaoshi
                    val jifenzhong = (millisUntilFinished - (jixiaoshi * xiaoshi)) / fenzhong
                    orderinfo_order_endtime.text = "剩余${jixiaoshi}小时${jifenzhong}分钟后自动关闭"
                } else if (millisUntilFinished > fenzhong) {//分钟
                    val jifenzhong = millisUntilFinished / fenzhong
                    orderinfo_order_endtime.text = "剩余${jifenzhong}分钟后自动关闭"
                } else if (millisUntilFinished > 1000)//秒
                {
                    orderinfo_order_endtime.text = (millisUntilFinished / 1000).toString() + "秒后自动关闭"
                }
                if (millisUntilFinished <= 1000) {
                    orderinfo_order_endtime.visibility = View.GONE
                    mPersenter!!.getdata_srccess(orderid!!)
                    val msg = Message.obtain()
                    msg.obj = data
                    msg.what = 1   //标志消息的标志
                    hand.sendMessage(msg)
                }
            }
        }
        tiem_c!!.start()
    }

    var orderid: String? = null
    var recycler_adapter: BaseQuickAdapter<OrderInfoBean.DataBean.ListBean.OrderdetailBean, BaseViewHolder>? = null
    override fun initView() {
        orderid = intent.getStringExtra("orderid")
        if (orderid != null && !orderid.equals("")) {
            tiem_cishu = 0
            mPersenter!!.getdata_srccess(orderid!!)
        } else {//暂无订单
        }
        orderinfo_back.setOnClickListener(this)
        orderinfo_pay_button.setOnClickListener(this)
        var Linenermessage = LinearLayoutManager(this@OrderInfoActivity)
        Linenermessage.orientation = LinearLayoutManager.VERTICAL
        orderinfo_recycler.layoutManager = Linenermessage
        recycler_adapter = object : BaseQuickAdapter<OrderInfoBean.DataBean.ListBean.OrderdetailBean, BaseViewHolder>(R.layout.adapter_orderinfo_item) {
            override fun convert(helper: BaseViewHolder?, item: OrderInfoBean.DataBean.ListBean.OrderdetailBean?) {
                helper!!.setText(R.id.adapter_orderinfo_goodsname, item!!.proName)
                        .setText(R.id.adapter_orderinfo_goodstype, item.styleName)
                        .setText(R.id.adapter_orderinfo_goodsnumber, "×${item.proNum}")
                        .setText(R.id.adapter_orderinfo_goodsprice, item.price)
                        .setText(R.id.adapter_orderinfo_goodszeng, "(${item.pv}")
                val icon = helper.getView<ImageView>(R.id.adapter_orderinfo_icon)
                Glide.with(this@OrderInfoActivity).load(item.proImg).into(icon)
            }
        }
        recycler_adapter!!.setEnableLoadMore(false)
        orderinfo_recycler.adapter = recycler_adapter


    }

    override fun initPersenter() {
        mPersenter = OrderInfoPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    var tiem_cishu: Int? = null
    override fun onResume() {
//        Log.d("orderinfo", "onResume")
        super.onResume()
        if (orderid != null && !orderid.equals("")) {
            mPersenter!!.getdata_srccess(orderid!!)
        } else {//暂无订单
        }
    }

    override fun onStop() {
//        Log.d("orderinfo", "onstop")
        if (tiem_c != null) {
            tiem_c!!.cancel()
        }
        super.onStop()
    }

    override fun onDestroy() {
//        Log.d("orderinfo", "onDestroy")
        if (tiem_c != null) {
            tiem_c!!.cancel()
        }
        super.onDestroy()
    }

}