package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.GoodsViewGroupItem
import com.hechuang.labeego.bean.ShoppingCarBean
import com.hechuang.labeego.bean.Shoppingcar_Bianji_bean
import com.hechuang.labeego.bean.Shoppingcar_Guige_bean
import com.hechuang.labeego.persenter.activity.ShoppingCarPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.BasePopupWindow
import com.hechuang.labeego.tools.ui.GoodsViewGroup
import com.hechuang.labeego.view.activity.IShoppingCarView
import kotlinx.android.synthetic.main.activity_shoppingcar.*
import org.json.JSONArray
import org.json.JSONObject

class ShoppingCarActivity : BaseActivity<ShoppingCarPersenter>(), IShoppingCarView, View.OnClickListener {
    override fun xiggai_error(yaunnumber: String, gaibiannumber: String, msg: Shoppingcar_Bianji_bean, position: Int) {
        if (!msg.data.message.equals("您还没有修改内容哦!"))
            MyToast.showMsg(msg.data.message)
    }

    override fun getcar_error(msg: String, shoppinglist: List<ShoppingCarBean.DataBean.ListBean.ProductBean>) {
        Log.d("shopping","getcar_error")
        this.shoppingcarlist = shoppinglist
        adapter_item!!.setNewData(shoppinglist)
        shoppingcar_zong_layout.visibility = View.GONE
        shoppingcar_bianji.visibility = View.GONE
        shoppingcar_bianji.isChecked = false
        shoppingcar_delete_layout.visibility = View.GONE
    }

    override fun delied_sessess(msg: String) {
        mPersenter!!.getshoppinglist()// 删除完成重置列表
    }

    override fun xiugai_sessess(yaunnumber: String, gaibiannumber: String, msg: Shoppingcar_Bianji_bean, position: Int) {//修改数量或者是修改规格， 不进行操作或者通知用户
        if (gaibiannumber != "") {
            shoppingcarlist!![position].proNum = gaibiannumber
        }
        val danjia = shoppingcarlist!![position].shopPrice.substring(1, shoppingcarlist!![position].shopPrice.length)
        val zongjia_dan = danjia.toFloat() * shoppingcarlist!![position].proNum.toInt()
        shoppingcarlist!![position].money = "¥" + zongjia_dan
        adapter_item!!.notifyDataSetChanged()
        shoppingcar_zong_text.text = "¥" + returnzong()
    }

    override fun getshoppingguigesessess(guige_bean: Shoppingcar_Guige_bean, position: Int) {
        Log.d("shpppingcar_dfafa", "$position")
        showpopup(guige_bean, position)
    }

    //---------------------------------------- 设置列表适配器----------------------------
    var isbianji: Boolean = false//是否是在编辑状态
    var check_all_boolean = false//是否全选
    var shoppingcarlist: List<ShoppingCarBean.DataBean.ListBean.ProductBean>? = null
    var adapter_item: BaseQuickAdapter<ShoppingCarBean.DataBean.ListBean.ProductBean, BaseViewHolder>? = null
    override fun getshoppingdatascesses(shoppinglist: List<ShoppingCarBean.DataBean.ListBean.ProductBean>) {
        shoppingcar_zong_layout.visibility = View.VISIBLE
        shoppingcar_bianji.visibility = View.VISIBLE
        this.shoppingcarlist = shoppinglist
        adapter_item!!.setOnItemClickListener { adapter, view, position ->
            //跳转到商品详情
        }
        adapter_item!!.setNewData(shoppingcarlist!!)
        adapter_item!!.setEnableLoadMore(false)
        shoppingcar_recycler.adapter = adapter_item
        shoppingcar_zong_text.text = "¥" + returnzong()
        dismissloading()
    }

    //-------------------------------- 弹出框*********************************
    private var basePopupWindow: BasePopupWindow? = null
    private var yixuanguoge: TextView? = null
    private val guige_str = StringBuffer("请选择商品规格")
    private var guige_key: String? = null
    private var guige_value: String? = null
    private var guige_price: String? = null
    private var buy_layout: RelativeLayout? = null
    private var guige_kucun: String? = null
    private var dange_zong: String? = null
    var icon: ImageView? = null
    var goodsviewprice: TextView? = null
    var kucun: TextView? = null
    var goodsviewgroup: GoodsViewGroup? = null
    var buy: Button? = null
    private fun showpopup(guige_bean: Shoppingcar_Guige_bean, position: Int) {
        guige_price = ""
        guige_key = ""
        guige_kucun = ""
        guige_value = ""
        guige_str.setLength(0)
        val items = ArrayList<GoodsViewGroupItem>()
        for (i in 0 until guige_bean.data.list.styleName.size) {
            items.add(GoodsViewGroupItem(guige_bean.data.list.styleName[i].styleId, guige_bean.data.list.styleName[i].styleName))
        }
        if (basePopupWindow == null) {
            basePopupWindow = BasePopupWindow(this)
            val view = LayoutInflater.from(this@ShoppingCarActivity).inflate(R.layout.view_shoppingcar_guige, null)
            val popupoff = view.findViewById<View>(R.id.shoppingcar_view_buy_off) as ImageView
            popupoff.setOnClickListener {
                if (basePopupWindow != null && basePopupWindow!!.isShowing) basePopupWindow!!.dismiss()
            }
            icon = view.findViewById<View>(R.id.shoppingcar_view_buy_icon) as ImageView
            buy_layout = view.findViewById(R.id.shoppingcar_view_layout)
            buy_layout!!.setOnClickListener {
                if (basePopupWindow != null && basePopupWindow!!.isShowing) basePopupWindow!!.dismiss()
            }
            goodsviewprice = view.findViewById<TextView>(R.id.shoppingcar_view_buy_price)
            yixuanguoge = view.findViewById<View>(R.id.shoppingcar_view_buy_guige_true) as TextView
            kucun = view.findViewById<View>(R.id.shoppingcar_view_buy_stock) as TextView
            goodsviewgroup = view.findViewById<View>(R.id.shoppingcar_view_buy_guige) as GoodsViewGroup

            buy = view.findViewById<View>(R.id.shoppingcar_view_buy_buy) as Button
            basePopupWindow!!.contentView = view
            basePopupWindow!!.isOutsideTouchable = true

        }
        Glide.with(this)
                .load(shoppingcarlist!![position].proImg)
                .apply(RequestOptions().override(400,400))
                .into(icon!!)
        goodsviewprice!!.text = shoppingcarlist!![position].shopPrice
        yixuanguoge!!.text = guige_str
        kucun!!.text = "库存" + shoppingcarlist!![position].kucun + "件"
        yixuanguoge!!.text = "已选\"" + guige_value + "\""
        goodsviewgroup!!.addItemViews(items)
        goodsviewgroup!!.setGroupClickListener { isSelector, itemPos, key, value ->
            //回调到这
            if (guige_str.isNotEmpty()) {
                guige_str.setLength(0)
            }
            if (isSelector) {
                guige_key = key
                guige_value = value
                guige_str.append("已选\"")
                guige_str.append(value)
                guige_str.append("\"")
                guige_kucun = guige_bean.data.list.styleName[itemPos].kucun
                kucun!!.text = "库存" + guige_kucun + "件"
                guige_price = guige_bean.data.list.styleName[itemPos].price
                goodsviewprice!!.text = guige_price
                yixuanguoge!!.text = guige_str.toString()
            }
        }
        buy!!.setOnClickListener {
            Log.d("shoppingcar", "$guige_value $guige_key $guige_price $position")
            if (!guige_value.equals("") && !guige_key.equals("") && !guige_price.equals("")) {
                shoppingcarlist!![position].styleName = guige_value
                shoppingcarlist!![position].styleId = guige_key
                shoppingcarlist!![position].shopPrice = guige_price
                Log.d("shoppingcar", "$guige_value $guige_key $guige_price $position")
                val price = shoppingcarlist!![position].shopPrice.substring(1, shoppingcarlist!![position].shopPrice.length)
                mPersenter!!.bianji("", "", shoppingcarlist!![position].styleId, shoppingcarlist!![position].shopcartid, "", price, false, position)
                adapter_item!!.notifyDataSetChanged()
            }
            if (basePopupWindow != null && basePopupWindow!!.isShowing) basePopupWindow!!.dismiss()
        }
        basePopupWindow!!.showAtLocation(shoppingcar_recycler, Gravity.BOTTOM, 0, 0)
    }

    //----------------------------------列表商家选择功能-------------------------------------
    private fun chack_title_item(position: Int) {
        shoppingcarlist!![position].isstorecheck = !shoppingcarlist!![position].isstorecheck
        for (i in 0 until shoppingcarlist!!.size) {
            if (shoppingcarlist!![i].storename == shoppingcarlist!![position].storename) {
                shoppingcarlist!![i].ischeck = shoppingcarlist!![position].isstorecheck
            }
        }
        var title_all: Int = 0
        for (i in 0 until shoppingcarlist!!.size) {
            if (shoppingcarlist!![i].ischeck) {
                title_all++
            }
        }
        if (title_all == shoppingcarlist!!.size) {
            Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_true).into(shoppingcar_checkall_img)
        } else {
            Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_false).into(shoppingcar_checkall_img)
        }
        adapter_item!!.notifyDataSetChanged()
        if (!isbianji) {
            shoppingcar_zong_text.text = "¥" + returnzong()
        }
    }


    fun erroreview(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.shoppingcar_error, null)
        val button = view.findViewById<Button>(R.id.shoppingcarerror_button)
        button.setOnClickListener {
            startActivity(Intent(this@ShoppingCarActivity, WebsActivity::class.java))
            finish()
        }
        return view
    }

    //-----------------------------------列表选择功能----------------------------------------
    private fun choice_item(position: Int) {
        Log.d("shopping", position.toString())
        shoppingcarlist!![position].ischeck = !shoppingcarlist!![position].ischeck
        if (shoppingcarlist!![position].ischeck == false) {
            for (i in 0 until shoppingcarlist!!.size) {
                if (shoppingcarlist!![i].storename == shoppingcarlist!![position].storename) {
                    shoppingcarlist!![i].isstorecheck = false
                }
            }
            Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_false).into(shoppingcar_checkall_img)
        } else {
            var fdsfa: Int = 0//用于判断这个商家的商品是否全选
            var storenumber: Int = 0//用于判断和这个商品同一个商家的商品个数
            var store_goodsnumber: Int = 0//用于判断和这个商品同一个商家的商品选中的个数
            for (i in 0 until shoppingcarlist!!.size) {
                if (shoppingcarlist!![i].ischeck) {
                    fdsfa++
                }
                if (shoppingcarlist!![i].storename == shoppingcarlist!![position].storename) {
                    storenumber++
                    if (shoppingcarlist!![i].ischeck) {
                        store_goodsnumber++
                    }
                }
            }
            Log.d("shoppingcar", "$fdsfa $storenumber $store_goodsnumber ${shoppingcarlist!!.size}")
            if (fdsfa == shoppingcarlist!!.size) {//如果商品选中的数量和商品的数量相同，那就全选了，设置全选为选中
                Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_true).into(shoppingcar_checkall_img)
                for (i in 0 until shoppingcarlist!!.size) {
                    shoppingcarlist!![i].isstorecheck = true
                }
            } else {//这是没有全选，  没有全选判断一下这个商品所属的商家的商品是否全选  如果全选就把状态改成全选，只要有一个没有选中就把选中状态改成未选中
                Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_false).into(shoppingcar_checkall_img)
                var titlecheck: Boolean = store_goodsnumber == storenumber
                for (i in 0 until shoppingcarlist!!.size) {
                    if (shoppingcarlist!![i].storename == shoppingcarlist!![position].storename) {
                        shoppingcarlist!![i].isstorecheck = titlecheck
                    }
                }
            }
        }
        adapter_item!!.notifyDataSetChanged()
        if (!isbianji) {
            shoppingcar_zong_text.text = "¥" + returnzong()
        }
    }

    //-----------------------------------------返回总价，选择时实时更新总价显示-----------------------------------
    fun returnzong(): String {
        var zongjia: Float = 0F
        for (i in 0 until shoppingcarlist!!.size) {
            if (shoppingcarlist!![i].ischeck) {
                val mo = shoppingcarlist!![i].money.substring(1, shoppingcarlist!![i].money.length)
                zongjia += mo.toFloat()
            }
        }
        return zongjia.toString()
    }

    //--------------------------------点击事件-----------------------------------
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.shoppingcar_bank -> finish()
            R.id.shoppingcar_delete -> {//删除
                var bianji: Int = 0
                var json: JSONObject? = null
                if (isbianji) {
                    json = JSONObject()
                    var jsonarr = JSONArray()
                    for (i in 0 until shoppingcarlist!!.size) {
                        if (shoppingcarlist!![i].ischeck) {
                            bianji++
                            jsonarr.put(shoppingcarlist!![i].shopcartid)
                        }
                    }
                    json.put("json", jsonarr)
                }
                if (bianji > 0) {
                    mPersenter!!.bianji("", "", "", json.toString(), "", "", true, 0)
                }
            }
            R.id.shoppingcar_jiesuan -> {//结算
                var tijiao: Int = 0
                var json: JSONObject? = null
                if (!isbianji) {
                    json = JSONObject()
                    var jsonarr = JSONArray()
                    for (i in 0 until shoppingcarlist!!.size) {
                        if (shoppingcarlist!![i].ischeck) {
                            tijiao++
                            jsonarr.put(shoppingcarlist!![i].shopcartid)
                        }
                    }
                    json.put("json", jsonarr)
                }
                Log.d("shoppingcar", tijiao.toString())
                if (tijiao > 0) {
                    var intent = Intent(this@ShoppingCarActivity, ConfirmAnOrderAcitivty::class.java)
                    intent.putExtra("shopcartid", json.toString())
                    intent.putExtra("type", "2")
                    startActivity(intent)
                    finish()
                } else {
                    MyToast.showMsg("请选择商品")
                }
            }
            R.id.shoppingcar_checkall_img -> {//全选——img
                check_all_boolean = !check_all_boolean
                if (check_all_boolean) {
                    Glide.with(this).load(R.drawable.shoppingcar_choice_true).into(shoppingcar_checkall_img)
                } else {
                    Glide.with(this).load(R.drawable.shoppingcar_choice_false).into(shoppingcar_checkall_img)
                }
                //----------------- 遍历数据设置item的选中状态
                var zongjia: Int
                for (i in 0 until shoppingcarlist!!.size) {
                    shoppingcarlist!![i].ischeck = check_all_boolean
                    shoppingcarlist!![i].isstorecheck = check_all_boolean
                }
                shoppingcar_zong_text.text = "¥" + returnzong()
                adapter_item!!.notifyDataSetChanged()
            }
            R.id.shoppingcar_checkall_text -> {//全选——text
                if (check_all_boolean) {
                    Glide.with(this).load(R.drawable.shoppingcar_choice_true).into(shoppingcar_checkall_img)
                } else {
                    Glide.with(this).load(R.drawable.shoppingcar_choice_false).into(shoppingcar_checkall_img)
                }
                for (i in 0 until shoppingcarlist!!.size) {
                    shoppingcarlist!![i].ischeck = check_all_boolean
                    shoppingcarlist!![i].isstorecheck = check_all_boolean
                }
                adapter_item!!.notifyDataSetChanged()
                shoppingcar_zong_text.text = "¥" + returnzong()
                //----------------- 遍历数据设置item的选中状态
            }
        }
    }

    //---------------------------------------------------------------------
    override fun initlayout(): Int {
        return R.layout.activity_shoppingcar
    }

    //----------------------------------------------------------------
    override fun initView() {
        shoppingcar_bank.setOnClickListener(this)
        shoppingcar_jiesuan.setOnClickListener(this)
        shoppingcar_checkall_img.setOnClickListener(this)
        shoppingcar_checkall_text.setOnClickListener(this)
        shoppingcar_delete.setOnClickListener(this)
        shoppingcar_bianji.setOnCheckedChangeListener { buttonView, isChecked ->
            isbianji = isChecked
            if (isChecked) {//完成
                shoppingcar_bianji.text = "完成"
                shoppingcar_delete_layout.visibility = View.VISIBLE
                shoppingcar_jiesuan.isClickable = false
                shoppingcar_jiesuan.background = (ContextCompat.getDrawable(this@ShoppingCarActivity, R.drawable.shopping_jiesuan_bg_r))
            } else {
                shoppingcar_bianji.text = "编辑"
                shoppingcar_delete_layout.visibility = View.GONE
                shoppingcar_jiesuan.isClickable = true
                shoppingcar_jiesuan.background = (ContextCompat.getDrawable(this@ShoppingCarActivity, R.drawable.shopping_jiesuan_bg))
            }
            for (i in 0 until shoppingcarlist!!.size) {
                shoppingcarlist!![i].isbianji = isbianji
            }
            adapter_item!!.notifyDataSetChanged()
        }
        var Linmanager = LinearLayoutManager(this)
        Linmanager.orientation = OrientationHelper.VERTICAL
        shoppingcar_recycler.layoutManager = Linmanager
        adapter_item = object : BaseQuickAdapter<ShoppingCarBean.DataBean.ListBean.ProductBean, BaseViewHolder>(R.layout.adapter_shoppingcar) {
            override fun convert(helper: BaseViewHolder?, item: ShoppingCarBean.DataBean.ListBean.ProductBean?) {
                Log.d("shoppingcar", item.toString())
                val title_layout = helper!!.getView<LinearLayout>(R.id.shoppingcaritem_title)
                val title_check_img = helper.getView<ImageView>(R.id.shoppingcaritem_titleimg_choice)
                val icon = helper.getView<ImageView>(R.id.shoppingcaritem_icon)
                val check_img = helper.getView<ImageView>(R.id.shoppingcaritem_choice)
                val check_layout = helper.getView<LinearLayout>(R.id.shoppingcaritem_check_layot)
                val title_check_layout = helper.getView<LinearLayout>(R.id.shoppingcaritem_titletext_layout)
                if (item!!.isshowtitle) {
                    title_layout.visibility = View.VISIBLE
                    helper.setText(R.id.shoppingcaritem_titletext_name, item.storename)
                } else {
                    title_layout.visibility = View.GONE
                }
                if (item.ischeck) {
                    Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_true).into(check_img)
                } else {
                    Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_false).into(check_img)
                }
                if (item.isbianji) {
                    check_layout.visibility = View.VISIBLE
                    title_check_layout.visibility = View.VISIBLE
                } else {
                    check_layout.visibility = View.GONE
                    title_check_layout.visibility = View.GONE
                }
                if (item.isstorecheck) {
                    Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_true).into(title_check_img)
                } else {
                    Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_false).into(title_check_img)
                }
                Glide.with(this@ShoppingCarActivity).load(item.proImg).into(icon)
                helper.setText(R.id.shoppingcaritem_number, item.proNum)
                        .setText(R.id.shoppingcaritem_name, item.proName)
                        .setText(R.id.shoppingcaritem_guige, item.styleName)
                        .setText(R.id.shoppingcaritem_price, item.shopPrice)
                helper.addOnClickListener(R.id.shoppingcaritem_guige_layout)
                        .addOnClickListener(R.id.shoppingcaritem_titleimg_choice)
                        .addOnClickListener(R.id.shoppingcaritem_choice)
                        .addOnClickListener(R.id.shoppingcaritem_check_layot)
                        .addOnClickListener(R.id.shoppingcaritem_titletext_layout)
                        .addOnClickListener(R.id.shoppingcaritem_number)
                var number_int = item.proNum.toInt()
                var number_str = item.proNum
                var min_int = item.minPurchase.toInt()
                var order_int = item.orderStep.toInt()
                var kucun_int = item.kucun.toInt()
                var numberreduce = helper.getView<TextView>(R.id.shoppingcaritem_numberreduce)
                var number = helper.getView<TextView>(R.id.shoppingcaritem_number)
                number.text = item.proNum
                var numberadd = helper.getView<TextView>(R.id.shoppingcaritem_numberadd)
                numberreduce.setOnClickListener {
                    if (number_int > min_int) {//如果数量大于起订量继续操作
                        number_int -= order_int
//                        item.proNum = number_int.toString()
//                        number.text = item.proNum
                        mPersenter!!.bianji(number_str, number_int.toString(), "",
                                shoppingcarlist!![helper.layoutPosition].shopcartid, "", "", false, helper.layoutPosition)
                    } else {//数量小于或者等于起订量不进行操作
                        MyToast.showMsg("数量不能小于库存")
                        return@setOnClickListener
                    }
                }
                numberadd.setOnClickListener {
                    if (number_int < kucun_int) {//如果数量小于库存继续操作
                        number_int += order_int
//                        item.proNum = number_int.toString()
//                        number.text = item.proNum
                        mPersenter!!.bianji(number_str, number_int.toString(), "",
                                shoppingcarlist!![helper.layoutPosition].shopcartid, "", "", false, helper.layoutPosition)
                    } else {//数量大于或者等于库存不进行操作
                        MyToast.showMsg("库存不足")
                        return@setOnClickListener
                    }
                }
            }
        }

        adapter_item!!.setOnItemChildClickListener { adapter, view, position ->
            when (view!!.id) {
                R.id.shoppingcaritem_guige_layout -> {//规格选择
                    mPersenter!!.getguige(shoppingcarlist!![position].proId, position)
                }
                R.id.shoppingcaritem_titletext_layout -> {
                    chack_title_item(position)
                }
                R.id.shoppingcaritem_titleimg_choice -> {//标题头的全选
                    chack_title_item(position)
                }
                R.id.shoppingcaritem_check_layot -> {
                    choice_item(position)
                }
                R.id.shoppingcaritem_choice -> {//列表项的选择
                    choice_item(position)
                }
                R.id.shoppingcaritem_number -> {//购物车数量
                    shoppingcarnumber(position)
                }
            }
        }
        adapter_item!!.emptyView = erroreview()
//        Log.d("shopping","getshopping")
//        mPersenter!!.getshoppinglist()
    }

    /**
     * 购物车数量
     */
    var number_dialog: AlertDialog? = null

    private fun shoppingcarnumber(position: Int) {
        var p_number = shoppingcarlist!![position].proNum.toInt()
        var p_number_str = shoppingcarlist!![position].proNum
        var view = LayoutInflater.from(this).inflate(R.layout.view_shoppingnumber, null)
        number_dialog = AlertDialog.Builder(this).setView(view).show()
        val img_jian = view.findViewById<ImageView>(R.id.shoppingcaritem_view_jian)
        val et_number = view.findViewById<EditText>(R.id.shoppingcaritem_view_number)
        val img_jia = view.findViewById<ImageView>(R.id.shoppingcaritem_view_jia)
        val bt_quxiao = view.findViewById<Button>(R.id.shoppingcaritem_view_quxiao)
        val bt_queding = view.findViewById<Button>(R.id.shoppingcaritem_view_queding)
        et_number.setText(p_number.toString())
        img_jian.setOnClickListener {
            if (p_number > shoppingcarlist!![position].minPurchase.toInt()) {//如果数量大于起订量继续操作
                p_number -= shoppingcarlist!![position].minPurchase.toInt()
                et_number.setText(p_number.toString())
            } else {//数量小于或者等于起订量不进行操作
                return@setOnClickListener
                MyToast.showMsg("数量不能小于起订量")
            }
        }
        et_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_number.text.toString() != null && !et_number.text.toString().equals(""))
                    if (et_number.text.toString().length > 9) {
                        MyToast.showMsg("数量超过最大值")
                    } else {
                        p_number = et_number.text.toString().toInt()
                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        img_jia.setOnClickListener {
            if (p_number < shoppingcarlist!![position].kucun.toInt()) {//如果数量小于库存继续操作
                p_number += shoppingcarlist!![position].minPurchase.toInt()
                et_number.setText(p_number.toString())
            } else {//数量大于或者等于库存不进行操作
                MyToast.showMsg("库存不足")
                return@setOnClickListener
            }
        }
        bt_queding.setOnClickListener {
            if (number_dialog != null && number_dialog!!.isShowing) number_dialog!!.dismiss()
            if (p_number > shoppingcarlist!![position].kucun.toInt()) {
                p_number = shoppingcarlist!![position].kucun.toInt()
            }
            if (p_number % shoppingcarlist!![position].orderStep.toInt() == 0) {
                mPersenter!!.bianji("", p_number.toString(), "",
                        shoppingcarlist!![position].shopcartid, "", "", false, position)
            } else {
                MyToast.showMsg("请设置正确的商品数量")
            }
        }
        bt_quxiao.setOnClickListener {
            if (number_dialog != null && number_dialog!!.isShowing) number_dialog!!.dismiss()
        }
    }

    override fun initPersenter() {
        mPersenter = ShoppingCarPersenter(this, this)
    }

    override fun showloading() {
    }

    override fun dismissloading() {
    }


    override fun onResume() {
        super.onResume()
//        Log.d("shoppingcar", "onresume")
        mPersenter!!.getshoppinglist()
        check_all_boolean = false
        Glide.with(this@ShoppingCarActivity).load(R.drawable.shoppingcar_choice_false).into(shoppingcar_checkall_img)
    }
}