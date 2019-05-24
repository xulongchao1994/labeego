package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
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
import com.hechuang.labeego.bean.*
import com.hechuang.labeego.persenter.activity.StoreShoppingCarPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IStoreShoppingCarView
import kotlinx.android.synthetic.main.activity_storeshoppingcar.*
import org.json.JSONArray
import org.json.JSONObject

class StoreShoppingCarActivity : BaseActivity<StoreShoppingCarPersenter>(), IStoreShoppingCarView, View.OnClickListener {
    override fun delete_eroor(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun goodsnumber(storeShoppingcar_shopingnumberBean: StoreShoppingcar_shopingnumberBean, position: Int) {
        shoppingcarlist!![position].proNum = storeShoppingcar_shopingnumberBean.data.pronum.toString()
        shoppingcarlist!![position].subprice = storeShoppingcar_shopingnumberBean.data.price.toString()
        adapter_item!!.notifyDataSetChanged()
        storeshoppingcar_zong_text.text = "¥" + returnzong()
    }

    override fun goodsnumbererror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun getcar_error(msg: String, shoppinglist: List<StoreShoppingCarBean.DataBean.ListBean.ProductBean>) {
        this.shoppingcarlist = shoppinglist
        adapter_item!!.setNewData(shoppinglist)
        storeshoppingcar_zong_layout.visibility = View.GONE
        storeshoppingcar_bianji.visibility = View.GONE
        storeshoppingcar_bianji.isChecked = false
        storeshoppingcar_delete_layout.visibility = View.GONE
    }

    override fun delied_sessess(msg: String) {
        mPersenter!!.getshoppinglist()// 删除完成重置列表
    }

    //---------------------------------------- 设置列表适配器----------------------------
    var isbianji: Boolean = false//是否是在编辑状态
    var check_all_boolean = false//是否全选
    var shoppingcarlist: List<StoreShoppingCarBean.DataBean.ListBean.ProductBean>? = null
    var adapter_item: BaseQuickAdapter<StoreShoppingCarBean.DataBean.ListBean.ProductBean, BaseViewHolder>? = null
    override fun getshoppingdatascesses(shoppinglist: List<StoreShoppingCarBean.DataBean.ListBean.ProductBean>) {
        storeshoppingcar_zong_layout.visibility = View.VISIBLE
        storeshoppingcar_bianji.visibility = View.VISIBLE
        this.shoppingcarlist = shoppinglist
        adapter_item!!.setOnItemClickListener { adapter, view, position ->
            //跳转到商品详情
        }
        adapter_item!!.setNewData(shoppingcarlist!!)
        adapter_item!!.setEnableLoadMore(false)
        storeshoppingcar_recycler.adapter = adapter_item
        storeshoppingcar_zong_text.text = "¥" + returnzong()
        dismissloading()
    }


    //----------------------------------列表商家选择功能-------------------------------------
    private fun chack_title_item(position: Int) {
        shoppingcarlist!![position].isstorecheck = !shoppingcarlist!![position].isstorecheck
        for (i in 0 until shoppingcarlist!!.size) {
            if (shoppingcarlist!![i].storename == shoppingcarlist!![position].storename) {
                shoppingcarlist!![i].ischeck = shoppingcarlist!![position].isstorecheck
            }
        }
        var title_all = 0
        for (i in 0 until shoppingcarlist!!.size) {
            if (shoppingcarlist!![i].ischeck) {
                title_all++
            }
        }
        if (title_all == shoppingcarlist!!.size) {
            Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_true).into(storeshoppingcar_checkall_img)
        } else {
            Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_false).into(storeshoppingcar_checkall_img)
        }
        adapter_item!!.notifyDataSetChanged()
        if (!isbianji) {
            storeshoppingcar_zong_text.text = "¥" + returnzong()
        }
    }

    //--------------错误页面
    fun erroreview(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.shoppingcar_error, null)
        val button = view.findViewById<Button>(R.id.shoppingcarerror_button)
        button.setOnClickListener {
            startActivity(Intent(this@StoreShoppingCarActivity, WebsActivity::class.java))
            finish()
        }
        return view
    }

    //-----------------------------------列表选择功能----------------------------------------
    private fun choice_item(position: Int) {
        shoppingcarlist!![position].ischeck = !shoppingcarlist!![position].ischeck
        if (shoppingcarlist!![position].ischeck == false) {
            for (i in 0 until shoppingcarlist!!.size) {
                if (shoppingcarlist!![i].storename == shoppingcarlist!![position].storename) {
                    shoppingcarlist!![i].isstorecheck = false
                }
            }
            Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_false).into(storeshoppingcar_checkall_img)
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
            if (fdsfa == shoppingcarlist!!.size) {//如果商品选中的数量和商品的数量相同，那就全选了，设置全选为选中
                Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_true).into(storeshoppingcar_checkall_img)
                for (i in 0 until shoppingcarlist!!.size) {
                    shoppingcarlist!![i].isstorecheck = true
                }
            } else {//这是没有全选，  没有全选判断一下这个商品所属的商家的商品是否全选  如果全选就把状态改成全选，只要有一个没有选中就把选中状态改成未选中
                Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_false).into(storeshoppingcar_checkall_img)
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
            storeshoppingcar_zong_text.text = "¥" + returnzong()
        }
    }

    //-----------------------------------------返回总价，选择时实时更新总价显示-----------------------------------
    fun returnzong(): String {
        var zongjia: Float = 0F
        for (i in 0 until shoppingcarlist!!.size) {
            if (shoppingcarlist!![i].ischeck) {
                val mo = shoppingcarlist!![i].subprice
                zongjia += mo.toFloat()
            }
        }
        return zongjia.toString()
    }

    //--------------------------------点击事件-----------------------------------
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.storeshoppingcar_bank -> finish()
            R.id.storeshoppingcar_delete -> {//删除
                var bianji: Int = 0
                var json: JSONObject? = null
                if (isbianji) {
                    json = JSONObject()
                    var jsonarr = JSONArray()
                    for (i in 0 until shoppingcarlist!!.size) {
                        if (shoppingcarlist!![i].ischeck) {
                            bianji++
                            jsonarr.put(shoppingcarlist!![i].styleId)
                        }
                    }
                    json.put("json", jsonarr)
                }
                if (bianji > 0) {
                    mPersenter!!.deletegoods(json.toString())
//                    mPersenter!!.bianji("", "", "", json.toString(), "", "", true, 0)
                }
            }
            R.id.storeshoppingcar_jiesuan -> {//结算
                var tijiao = 0
                var json: JSONObject? = null
                if (!isbianji) {
                    json = JSONObject()
                    var jsonarr = JSONArray()
                    for (i in 0 until shoppingcarlist!!.size) {
                        if (shoppingcarlist!![i].ischeck) {
                            tijiao++
                            jsonarr.put(shoppingcarlist!![i].styleId)
                        }
                    }
                    json.put("json", jsonarr)
                }
                if (tijiao > 0) {
                    var intent = Intent(this@StoreShoppingCarActivity, StoreConfirmAnOrderAcitivty::class.java)
                    intent.putExtra("shopcartid", json.toString())
                    intent.putExtra("type", "2")
                    startActivity(intent)
                    finish()
                } else {
                    MyToast.showMsg("请选择商品")
                }
            }
            R.id.storeshoppingcar_checkall_img -> {//全选——img
                check_all_boolean = !check_all_boolean
                if (check_all_boolean) {
                    Glide.with(this).load(R.drawable.store_shoppingcar_true).into(storeshoppingcar_checkall_img)
                } else {
                    Glide.with(this).load(R.drawable.store_shoppingcar_false).into(storeshoppingcar_checkall_img)
                }
                //----------------- 遍历数据设置item的选中状态
                var zongjia: Int
                for (i in 0 until shoppingcarlist!!.size) {
                    shoppingcarlist!![i].ischeck = check_all_boolean
                    shoppingcarlist!![i].isstorecheck = check_all_boolean
                }
                storeshoppingcar_zong_text.text = "¥" + returnzong()
                adapter_item!!.notifyDataSetChanged()
            }
            R.id.storeshoppingcar_checkall_text -> {//全选——text
                if (check_all_boolean) {
                    Glide.with(this).load(R.drawable.store_shoppingcar_true).into(storeshoppingcar_checkall_img)
                } else {
                    Glide.with(this).load(R.drawable.store_shoppingcar_false).into(storeshoppingcar_checkall_img)
                }
                for (i in 0 until shoppingcarlist!!.size) {
                    shoppingcarlist!![i].ischeck = check_all_boolean
                    shoppingcarlist!![i].isstorecheck = check_all_boolean
                }
                adapter_item!!.notifyDataSetChanged()
                storeshoppingcar_zong_text.text = "¥" + returnzong()
                //----------------- 遍历数据设置item的选中状态
            }
        }
    }

    //---------------------------------------------------------------------
    override fun initlayout(): Int {
        return R.layout.activity_storeshoppingcar
    }

    //----------------------------------------------------------------
    override fun initView() {
        storeshoppingcar_bank.setOnClickListener(this)
        storeshoppingcar_jiesuan.setOnClickListener(this)
        storeshoppingcar_checkall_img.setOnClickListener(this)
        storeshoppingcar_checkall_text.setOnClickListener(this)
        storeshoppingcar_delete.setOnClickListener(this)
        storeshoppingcar_bianji.setOnCheckedChangeListener { buttonView, isChecked ->
            isbianji = isChecked
            if (isChecked) {//完成
                storeshoppingcar_bianji.text = "完成"
                storeshoppingcar_delete_layout.visibility = View.VISIBLE
                storeshoppingcar_jiesuan.isClickable = false
                storeshoppingcar_jiesuan.background = (ContextCompat.getDrawable(this@StoreShoppingCarActivity, R.drawable.shopping_jiesuan_bg_r))
            } else {
                storeshoppingcar_bianji.text = "编辑"
                storeshoppingcar_delete_layout.visibility = View.GONE
                storeshoppingcar_jiesuan.isClickable = true
                storeshoppingcar_jiesuan.background = (ContextCompat.getDrawable(this@StoreShoppingCarActivity, R.drawable.shopping_jiesuan_bg))
            }
            for (i in 0 until shoppingcarlist!!.size) {
                shoppingcarlist!![i].isbianji = isbianji
            }
            adapter_item!!.notifyDataSetChanged()
        }
        var Linmanager = LinearLayoutManager(this)
        Linmanager.orientation = OrientationHelper.VERTICAL
        storeshoppingcar_recycler.layoutManager = Linmanager
        adapter_item = object : BaseQuickAdapter<StoreShoppingCarBean.DataBean.ListBean.ProductBean, BaseViewHolder>(R.layout.adapter_storeshoppingcar) {
            override fun convert(helper: BaseViewHolder?, item: StoreShoppingCarBean.DataBean.ListBean.ProductBean?) {
                val title_layout = helper!!.getView<LinearLayout>(R.id.storeshoppingcaritem_title)
                val title_check_img = helper.getView<ImageView>(R.id.storeshoppingcaritem_titleimg_choice)
                val icon = helper.getView<ImageView>(R.id.storeshoppingcaritem_icon)
                val check_img = helper.getView<ImageView>(R.id.storeshoppingcaritem_choice)
                val check_layout = helper.getView<LinearLayout>(R.id.storeshoppingcaritem_check_layot)
                val title_check_layout = helper.getView<LinearLayout>(R.id.storeshoppingcaritem_titletext_layout)
                helper.setGone(R.id.storeshoppingcaritem_guige_icon, false)
                if (item!!.isshowtitle) {
                    title_layout.visibility = View.VISIBLE
                    helper.setText(R.id.storeshoppingcaritem_titletext_name, item.storename)
                } else {
                    title_layout.visibility = View.GONE
                }
                if (item.ischeck) {
                    Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_true).into(check_img)
                } else {
                    Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_false).into(check_img)
                }
                if (item.isbianji) {
                    check_layout.visibility = View.VISIBLE
                    title_check_layout.visibility = View.VISIBLE
                } else {
                    check_layout.visibility = View.GONE
                    title_check_layout.visibility = View.GONE
                }
                if (item.isstorecheck) {
                    Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_true).into(title_check_img)
                } else {
                    Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_false).into(title_check_img)
                }
                Glide.with(this@StoreShoppingCarActivity).load(item.proImg).into(icon)
                helper.setText(R.id.storeshoppingcaritem_number, item.proNum)
                        .setText(R.id.storeshoppingcaritem_name, item.proName)
                        .setText(R.id.storeshoppingcaritem_guige, item.styleName)
                        .setText(R.id.storeshoppingcaritem_price, "¥" + item.subprice)
                helper.addOnClickListener(R.id.storeshoppingcaritem_guige_layout)
                        .addOnClickListener(R.id.storeshoppingcaritem_titleimg_choice)
                        .addOnClickListener(R.id.storeshoppingcaritem_choice)
                        .addOnClickListener(R.id.storeshoppingcaritem_check_layot)
                        .addOnClickListener(R.id.storeshoppingcaritem_titletext_layout)
                        .addOnClickListener(R.id.storeshoppingcaritem_number)
                var number_int = item.proNum.toInt()
                var min_int = item.minPurchase.toInt()
                var order_int = item.orderStep.toInt()
                var kucun_int = item.kucun.toInt()
                var numberreduce = helper.getView<TextView>(R.id.storeshoppingcaritem_numberreduce)
                var number = helper.getView<TextView>(R.id.storeshoppingcaritem_number)
                number.text = item.proNum
                var numberadd = helper.getView<TextView>(R.id.storeshoppingcaritem_numberadd)
                numberreduce.setOnClickListener {
                    if (number_int > min_int) {//如果数量大于起订量继续操作
                        number_int -= order_int
//                        item.proNum = number_int.toString()
//                        number.text = item.proNum
                        mPersenter!!.shopnumber(shoppingcarlist!![helper.layoutPosition].styleId, number_int.toString(), helper.layoutPosition)
//                        mPersenter!!.bianji(number_str, number_int.toString(), "",
//                                shoppingcarlist!![helper.layoutPosition].shopcartid, "", "", false, helper.layoutPosition)
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
                        mPersenter!!.shopnumber(shoppingcarlist!![helper.layoutPosition].styleId, number_int.toString(), helper.layoutPosition)
//                        mPersenter!!.bianji(number_str, number_int.toString(), "",
//                                shoppingcarlist!![helper.layoutPosition].shopcartid, "", "", false, helper.layoutPosition)
                    } else {//数量大于或者等于库存不进行操作
                        MyToast.showMsg("库存不足")
                        return@setOnClickListener
                    }
                }
            }
        }

        adapter_item!!.setOnItemChildClickListener { adapter, view, position ->
            when (view!!.id) {
//                R.id.shoppingcaritem_guige_layout -> {//规格选择
//                    mPersenter!!.getguige(shoppingcarlist!![position].proId, position)
//                }
                R.id.storeshoppingcaritem_titletext_layout -> {
                    chack_title_item(position)
                }
                R.id.storeshoppingcaritem_titleimg_choice -> {//标题头的全选
                    chack_title_item(position)
                }
                R.id.storeshoppingcaritem_check_layot -> {
                    choice_item(position)
                }
                R.id.storeshoppingcaritem_choice -> {//列表项的选择
                    choice_item(position)
                }
                R.id.storeshoppingcaritem_number -> {//购物车数量
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
//        var p_number_str = shoppingcarlist!![position].proNum
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
                mPersenter!!.shopnumber(shoppingcarlist!![position].shopcartid, p_number.toString(), position)
            } else {
                MyToast.showMsg("请设置正确的商品数量")
            }
        }
        bt_quxiao.setOnClickListener {
            if (number_dialog != null && number_dialog!!.isShowing) number_dialog!!.dismiss()
        }
    }

    override fun initPersenter() {
        mPersenter = StoreShoppingCarPersenter(this, this)
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
        Glide.with(this@StoreShoppingCarActivity).load(R.drawable.store_shoppingcar_false).into(storeshoppingcar_checkall_img)
    }
}