package com.hechuang.labeego.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import cn.sharesdk.onekeyshare.OnekeyShare
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.*
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.activity.ShopGoodsInfoPersenter
import com.hechuang.labeego.tools.Util.GlideImageLoader
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.BasePopupWindow
import com.hechuang.labeego.tools.ui.IdeaScrollView
import com.hechuang.labeego.view.activity.IShopGoodsInfoView
import com.youth.banner.BannerConfig
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_shopgoodsinfo.*
import org.json.JSONArray
import org.json.JSONObject

class ShopGoodsInfoActivity : BaseActivity<ShopGoodsInfoPersenter>(), IShopGoodsInfoView, View.OnClickListener, IdeaScrollView.OnScrollChangedColorListener, IdeaScrollView.OnSelectedIndicateChangedListener {
    var isNeedScrollTo: Boolean = true
    override fun onSelectedChanged(position: Int) {
        //此处控制滑动视图滑动位置
//        Log.d("goodsinfo", position.toString())
        isNeedScrollTo = false
        shopgoodsinfo_radio.check(shopgoodsinfo_radio.getChildAt(position).id)
        isNeedScrollTo = true
    }

    override fun onChanged(percentage: Float) {
        if (percentage > 0) {
            val color = getAlphaColor(if (percentage > 0.9f) 1.0f else percentage)
            shopgoodsinfo_title_layout.setBackgroundDrawable(ColorDrawable(color))
//            shopgoodsinfo_back.setBackgroundDrawable(ColorDrawable(color))
            shopgoodsinfo_title_layout.alpha = (if (percentage > 0.9f) 1.0f else percentage) * 255
            shopgoodsinfo_back.alpha = (if (percentage > 0.9f) 1.0f else percentage) * 255
            shopgoodsinfo_shoppingcar.alpha = (if (percentage > 0.9f) 1.0f else percentage) * 255
            shopgoodsinfo_fenxiang.alpha = (if (percentage > 0.9f) 1.0f else percentage) * 255

            shopgoodsinfo_title_layout_yin.alpha = (if (percentage > 0.9f) 0f else 1 - percentage) * 255
            shopgoodsinfo_back_yin.alpha = (if (percentage > 0.9f) 0f else 1 - percentage) * 255
            shopgoodsinfo_shoppingcar_yin.alpha = (if (percentage > 0.9f) 0f else 1 - percentage) * 255
            shopgoodsinfo_fenxiang_yin.alpha = (if (percentage > 0.9f) 0f else 1 - percentage) * 255
            if (percentage > 0.3) {
                shopgoodsinfo_title_layout_yin.visibility = View.GONE
                shopgoodsinfo_title_layout.visibility = View.VISIBLE
                shopgoodsinfo_radio.visibility = View.VISIBLE
            } else {
                shopgoodsinfo_radio.visibility = View.GONE
            }
        } else {
            val color = getAlphaColor(0f)
            shopgoodsinfo_title_layout.setBackgroundDrawable(ColorDrawable(color))
            shopgoodsinfo_title_layout.alpha = 0f
            shopgoodsinfo_title_layout.visibility = View.GONE
            shopgoodsinfo_title_layout_yin.visibility = View.VISIBLE
        }
        setRadioButtonTextColor(percentage)
    }

    private val radioGroupListener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
        for (i in 0 until shopgoodsinfo_radio.getChildCount()) {
            val radioButton = shopgoodsinfo_radio.getChildAt(i) as RadioButton
            radioButton.setTextColor(if (radioButton.isChecked) getRadioCheckedAlphaColor(currentPercentage) else getRadioAlphaColor(currentPercentage))
            if (radioButton.isChecked && isNeedScrollTo) {
                shopgoodsinfo_ideascrollview.position = i
            }
        }
    }
    private var currentPercentage = 0f
    fun setRadioButtonTextColor(percentage: Float) {
        if (Math.abs(percentage - currentPercentage) >= 0.1f) {
            for (i in 0 until shopgoodsinfo_radio.childCount) {
                val radioButton = shopgoodsinfo_radio.getChildAt(i) as RadioButton
                radioButton.setTextColor(if (radioButton.isChecked) getRadioCheckedAlphaColor(percentage) else getRadioAlphaColor(percentage))
            }
            this.currentPercentage = percentage
        }
    }

    override fun onChangedFirstColor(percentage: Float) {
    }

    override fun onChangedSecondColor(percentage: Float) {
    }

    override fun getdoodsinfo_error(msg: String) {
        AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton("确定") { dialog, which ->
                    finish()
                }.setCancelable(false)
                .show()

    }

    //    var guige_framgent: String? = null
    private var sp: SharedPreferences? = null

    override fun buy_error(msg: String) {
        dismissloading()
        if (msg.equals("未登录")) {
            sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            val editor = sp!!.edit()
            editor.putString("token_id", "")
            editor.putString("urserid", "")
            editor.putString("usertype", "")
            editor.putBoolean("islogin", false)
            UserData.USERISLOGIN = false
            editor.commit()
            UserData.USERSESSIONID = ""
            UserData.USERTOKENID = ""
            UserData.USERTYPE = ""
            UserData.USER_ID = ""
            startActivity(Intent(this@ShopGoodsInfoActivity, LoginActivity::class.java))
            finish()
        }
        MyToast.showMsg(msg)
    }

    override fun getnumber(numberbena: ShoppingCarNumberBean) {
        if (numberbena.data.status == 1) {
            if (numberbena.data.num != "0") {
                shopgoodsinfo_shoppingcar_layout.visibility = View.VISIBLE
                shopgoodsinfo_shoppingcarnumber.text = numberbena.data.num
            } else {
                shopgoodsinfo_shoppingcar_layout.visibility = View.GONE
            }
        } else {
            shopgoodsinfo_shoppingcar_layout.visibility = View.GONE
        }
    }

    override fun buy_seccess(type: String, buy_Bean: StoreGoodsinfo_buy_bean) {
        if (buy_Bean.data.status == "1") {
            when (type) {
                "1" -> {//立即购买  跳转到提交订单页面
                    if (UserData.USERISLOGIN) {
                        var json = JSONObject()
                        var jsonarr = JSONArray()
                        jsonarr.put(buy_Bean.data.list.styleid)
                        json.put("json", jsonarr)
                        val intent = Intent(this, StoreConfirmAnOrderAcitivty::class.java)
                        intent.putExtra("shopcartid", json.toString())
                        intent.putExtra("type", "1")
                        startActivity(intent)
                    } else {
                        MyToast.showMsg("未登录")
                    }
                }
                "2" -> {//加入购物车
                    MyToast.showMsg("加入购物车成功，请到购物车查看")
//                    mPersenter!!.getshoppingcarnumber()
                }
            }
        } else {
            MyToast.showMsg(buy_Bean.data.msg)
        }
        dismissloading()
    }

    var buytype: String? = null
    var kucun_str: String? = null

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.shopgoodsinfo_back -> finish()
            R.id.shopgoodsinfo_back_yin -> finish()
            R.id.shopgoodsinfo_buynow -> {
                showpopup("1")
            }
            R.id.shopgoodsinfo_addshopping -> {
                showpopup("2")
            }
            R.id.goodsinfo_view_buy_addnumber -> {//数量加
                val minbie = Integer.valueOf(goodsInfoBean!!.data.list.orderStep)
                val kecun = Integer.valueOf(kucun_str)
                if (number_int < kecun) {
                    button_add!!.setBackgroundResource(R.drawable.goodsinfo_add_true)
                    number_int += minbie
                    if (number_int >= kecun) {
                        button_add!!.setBackgroundResource(R.drawable.goodsinfo_add_false)
                        number!!.setText(kucun_str)
//                        number!!.text = kucun_str
                        MyToast.showMsg("数量超过库存")
                        return
                    }
                }
                number!!.setText(number_int.toString())
            }
            R.id.goodsinfo_view_buy_reduce_number -> {//数量减
                val minbie = Integer.valueOf(goodsInfoBean!!.data.list.orderStep)
                if (number_int >= Integer.valueOf(goodsInfoBean!!.data.list.minPurchase)) {
                    button_reduce!!.setBackgroundResource(R.drawable.goodsinfo_minus_true)
                    number_int -= minbie
                    if (number_int <= Integer.valueOf(goodsInfoBean!!.data.list.minPurchase)) {
                        button_reduce!!.setBackgroundResource(R.drawable.goodsinfo_minus_flase)
                        number!!.setText(goodsInfoBean!!.data.list.minPurchase)
                        number_int = Integer.valueOf(goodsInfoBean!!.data.list.minPurchase)
                        MyToast.showMsg("商品数量不能为小于最小起订量")
                        return
                    }
                }
                number!!.setText(number_int.toString())
            }
            R.id.goodsinfo_view_buy_buy -> {//确定
                if (UserData.USERISLOGIN) {
//                    if (buytype.equals("0")) {
                    shopgoodsinfo_guige_text.text = guige_str.toString()
//                    } else {
                    if (guige_key == null || guige_key.equals("")) {
                        MyToast.showMsg("请选择规格")
                    } else {
                        if (number_int % goodsInfoBean!!.data.list.orderStep.toInt() == 0) {
                            showloading()
                            mPersenter!!.buy(buytype!!, goodsInfoBean!!.data.list.proId, guige_key!!, number_int.toString())
                        } else
                            MyToast.showMsg("请设置正确的商品数量")
                    }
//                    }
                } else {
                    MyToast.showMsg("未登录")
                }
                if (basePopupWindow != null && basePopupWindow!!.isShowing) {
                    basePopupWindow!!.dismiss()
                }
            }
            R.id.goodsinfo_view_buy_layout -> {
                if (basePopupWindow != null && basePopupWindow!!.isShowing) {
                    basePopupWindow!!.dismiss()
                }
            }
            R.id.goodsinfo_view_buy_off -> {
                if (basePopupWindow != null && basePopupWindow!!.isShowing) {
                    basePopupWindow!!.dismiss()
                }
            }
            R.id.shopgoodsinfo_shoppingcar -> {
                if (UserData.USERISLOGIN) {
                    var intent = Intent(this@ShopGoodsInfoActivity, StoreShoppingCarActivity::class.java)
                    startActivity(intent)
                } else {
                    MyToast.showMsg("未登录")
                }
            }
            R.id.shopgoodsinfo_shoppingcar_yin -> {
                if (UserData.USERISLOGIN) {
                    var intent = Intent(this@ShopGoodsInfoActivity, StoreShoppingCarActivity::class.java)
                    startActivity(intent)
                } else {
                    MyToast.showMsg("未登录")
                }
            }
            R.id.shopgoodsinfo_kefu -> {
                call("4009625123")
            }
            R.id.shopgoodsinfo_guige_layout -> {
                showpopup("2")
            }
        }
    }

    private fun call(phone: String) {
        if (Build.VERSION.SDK_INT >= 23) {
            val permmision = ContextCompat.checkSelfPermission(this@ShopGoodsInfoActivity, android.Manifest.permission.CALL_PHONE)
            if (permmision != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), 123)
                return
            } else {
                val intent2 = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent2)

            }
        } else {
            val intent2 = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent2)
        }
    }

    var url: String? = null
    var goodsInfoBean: ShopGoodInfoBean? = null
    var goodsid: String? = null
    var titles = arrayListOf("商品", "详情", "售后")
    var fragmentlist = arrayListOf<Fragment>()
    override fun getgooodsinfo_seccess(goodsInfoBean: ShopGoodInfoBean) {
        this.goodsInfoBean = goodsInfoBean
        var imglist = arrayListOf<String>()
        for (i in 0 until goodsInfoBean.data.list.imgs.size) {
            imglist.add(goodsInfoBean.data.list.imgs[i])
        }
        shopgoodsinfo_banner.setImageLoader(GlideImageLoader())
        shopgoodsinfo_banner.setBannerStyle(BannerConfig.NUM_INDICATOR)
        shopgoodsinfo_banner.setIndicatorGravity(BannerConfig.RIGHT)
        val display = getResources().displayMetrics
        val params = LinearLayout.LayoutParams(display.widthPixels, display.widthPixels)
        shopgoodsinfo_banner.layoutParams = params
        shopgoodsinfo_banner.setImages(imglist)
        shopgoodsinfo_banner.isAutoPlay(false)
        shopgoodsinfo_name.text = goodsInfoBean.data.list.proName
        shopgoodsinfo_price.text = goodsInfoBean.data.list.price
//        if (goodsInfoBean.data.list.integral == "") {
        shopgoodsinfo_integral_layout!!.visibility = View.GONE
//        } else {
//            shopgoodsinfo_integral_layout!!.visibility = View.VISIBLE
//        }
//        shopgoodsinfo_integral.text = goodsInfoBean.data.list.integral
        shopgoodsinfo_id.text = "ID:${goodsInfoBean.data.list.proId}"
        shopgoodsinfo_qidingliang.text = "起订量：${goodsInfoBean.data.list.minPurchase}"
        shopgoodsinfo_qidingbeishu.text = "起订倍数:${goodsInfoBean.data.list.orderStep}"
//        if (goodsInfoBean.data.list.avg.toFloat() > 0) {
//            shopgoodsinfo_pingfen.visibility = View.VISIBLE
//            shopgoodsinfo_pingfen_text.visibility = View.VISIBLE
//            shopgoodsinfo_pingfen.rating = goodsInfoBean.data.list.avg.toFloat()
//            shopgoodsinfo_pingfen_text.text = "${goodsInfoBean.data.list.avg}分"
//        } else {
        shopgoodsinfo_pingfen.visibility = View.GONE
        shopgoodsinfo_pingfen_text.visibility = View.GONE
//        }
        shopgoodsinfo_storename!!.text = goodsInfoBean.data.list.supplier
        url = " <style type=\"text/css\"> img{ max-width: 100%; height: auto; } </style> ${goodsInfoBean.data.list.proContent}"
        shopgoodsinfo_info.loadDataWithBaseURL(null, url, "text/html", "utf-8", null)
        val settings = shopgoodsinfo_info.getSettings()
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.javaScriptEnabled = true
        settings.displayZoomControls = true
//        shopgoodsinfo_pingjianumber.text = "商品评价(${goodsInfoBean.data.list.count})"
//        shopgoodsinfo_comment_more.setOnClickListener {
//
//            if (goodsInfoBean.data.list.userinfo != null && goodsInfoBean.data.list.userinfo.size > 0) {
//                var intent = Intent(this@ShopGoodsInfoActivity, CommentListActivity::class.java)
//                intent.putExtra("proimg", proimg)
//                intent.putExtra("proid", goodsInfoBean.data.list.proId)
//                startActivity(intent)
//            } else {
//                MyToast.showMsg("暂无更多评论")
//            }
//        }
//        if (goodsInfoBean.data.list.userinfo != null && goodsInfoBean.data.list.userinfo.size > 0) {
//            shopgoodsinfo_comment_user_layout.visibility = View.VISIBLE
//            Glide.with(this@ShopGoodsInfoActivity).load(goodsInfoBean.data.list.userinfo[0].avatarurl)
//                    .apply(RequestOptions().centerCrop().error(R.mipmap.labeego_icon))
//                    .into(shopgoodsinfo_comment_usericon)
//            shopgoodsinfo_comment_username.text = goodsInfoBean.data.list.userinfo[0].turename
//            shopgoodsinfo_comment.text = goodsInfoBean.data.list.userinfo[0].comment
//            shopgoodsinfo_comment.setTextColor(resources.getColor(R.color.black))
//            shopgoodsinfo_admin_comment.visibility = View.GONE
//        } else {
//            shopgoodsinfo_comment_user_layout.visibility = View.GONE
//            shopgoodsinfo_comment_username.text = ""
//            shopgoodsinfo_comment.text = "暂无评论"
//            shopgoodsinfo_comment.setTextColor(resources.getColor(R.color.userorder_text_color))
//            shopgoodsinfo_admin_comment.visibility = View.GONE
//        }
        shopgoodsinfo_info!!.webViewClient = MyWebViewClient()
        shopgoodsinfo_guige_layout!!.setOnClickListener(this)
        shopgoodsinfo_radio.setOnCheckedChangeListener(radioGroupListener)
        shopgoodsinfo_buynow.isEnabled = true
        shopgoodsinfo_addshopping.isEnabled = true
        shopgoodsinfo_shoppingcar.isEnabled = true
        shopgoodsinfo_shoppingcar_yin.isEnabled = true
        shopgoodsinfo_fenxiang_yin.isEnabled = true
        shopgoodsinfo_fenxiang.isEnabled = true
        shopgoodsinfo_yunfei.text = "运费政策：" + goodsInfoBean.data.list.shouhou
        val rectangle = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        shopgoodsinfo_ideascrollview.setbanner(shopgoodsinfo_banner, getMeasureHeight(shopgoodsinfo_title_layout) - rectangle.top)

        val araryDistance = java.util.ArrayList<Int>()
        araryDistance.add(0)
        araryDistance.add(getMeasureHeight(shopgoodsinfo_one) - getMeasureHeight(shopgoodsinfo_title_layout) + rectangle.top)
        araryDistance.add(getMeasureHeight(shopgoodsinfo_one) + getMeasureHeight(shopgoodsinfo_two) - getMeasureHeight(shopgoodsinfo_title_layout) + rectangle.top)
        shopgoodsinfo_ideascrollview.arrayDistance = araryDistance
        shopgoodsinfo_ideascrollview.onScrollChangedColorListener = this
        shopgoodsinfo_ideascrollview.onSelectedIndicateChangedListener = this
        shopgoodsinfo_title_layout.alpha = 0f
//        radioGroup.setAlpha(0f)
        shopgoodsinfo_radio.check(shopgoodsinfo_radio.getChildAt(0).getId())
        shopgoodsinfo_banner.start()
//        val one = findViewById<View>(R.id.one)
//        val two = findViewById<View>(R.id.two)
//        val four = findViewById<View>(R.id.four)
//        val three = findViewById<View>(R.id.three)

    }

    private fun showShare(picUrl: String) {
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.app_name))
        // titleUrl QQ和QQ空间跳转链接
        //        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.text = "我在啦蜂上发现了一个不错的商品，赶快来看看吧。"
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        oks.setImagePath(picUrl);//确保SDcard下面存在此张图片
        oks.setImageUrl(proimg)
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(picUrl)
        // comment是我对这条分享的评论，仅在人人网使用
        //        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this)
        UserData.isfenxiang = true
    }

    fun getMeasureHeight(view: View): Int {
        val width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED)
        val height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED)
        view.measure(width, height)
        return view.measuredHeight
    }

    internal inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view!!.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
//            //这个是一定要加上那个的,配合scrollView和WebView的height=wrap_content属性使用
            val w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED)
            val h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED)
            //重新测量
            shopgoodsinfo_info!!.measure(w, h)
            super.onPageFinished(view, url)
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_shopgoodsinfo
    }

    var proimg: String? = null
    override fun initView() {
        proimg = intent.getStringExtra("proimg")
        goodsid = intent.getStringExtra("pid")
//        goodsid = "93"

        Log.d("shopgoodsinfo", goodsid + "\n" + proimg)
        mPersenter!!.getgoodsinfodata(goodsid!!)
        shopgoodsinfo_back.setOnClickListener(this)
        shopgoodsinfo_back_yin.setOnClickListener(this)
        shopgoodsinfo_buynow.setOnClickListener(this)
        shopgoodsinfo_addshopping.setOnClickListener(this)
        shopgoodsinfo_shoppingcar.setOnClickListener(this)
        shopgoodsinfo_shoppingcar_yin.setOnClickListener(this)
        shopgoodsinfo_fenxiang.setOnClickListener { showShare("http://www.labeego.com/index.php/Home/Unproduct/product_details/proid/$goodsid") }
        shopgoodsinfo_fenxiang_yin.setOnClickListener { showShare("http://www.labeego.com/index.php/Home/Unproduct/product_details/proid/$goodsid") }
        shopgoodsinfo_fenxiang_yin.isEnabled = false
        shopgoodsinfo_fenxiang.isEnabled = false
        shopgoodsinfo_buynow.isEnabled = false
        shopgoodsinfo_addshopping.isEnabled = false
        shopgoodsinfo_shoppingcar.isEnabled = false
        shopgoodsinfo_shoppingcar_yin.isEnabled = false
        shopgoodsinfo_kefu.setOnClickListener(this)
        Glide.with(this@ShopGoodsInfoActivity).load(proimg).into(shopgoodsinfo_title_img)
    }

    override fun onResume() {
        super.onResume()
//        mPersenter!!.getshoppingcarnumber()
    }

    override fun initPersenter() {
        mPersenter = ShopGoodsInfoPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    fun getItems(): List<GoodsViewGroupItem> {
        val items = ArrayList<GoodsViewGroupItem>()
        for (i in 0 until goodsInfoBean!!.data.list.goodsstyle.size) {
            items.add(GoodsViewGroupItem(goodsInfoBean!!.data.list.goodsstyle[i].styleId, goodsInfoBean!!.data.list.goodsstyle[i].styleName))
        }
        return items
    }

    private var basePopupWindow: BasePopupWindow? = null
    private var yixuanguoge: TextView? = null
    private var noting: TextView? = null
    private val guige_str = StringBuffer("请选择商品规格")
    private var guige_key: String? = null
    private var button_add: Button? = null
    private var button_reduce: Button? = null
    private var number: EditText? = null
    private var number_int: Int = 0
    private var buy_layout: RelativeLayout? = null
    var popupoff: ImageView? = null
    var icon: ImageView? = null
    var kucun: TextView? = null
    var price: TextView? = null
    var goodsviewgroup: TagFlowLayout? = null
    var view: View? = null
    var icon_layout: LinearLayout? = null
    @SuppressLint("SetTextI18n")
    fun showpopup(buytype: String) {
        Log.d("goodsinfo", buytype)
        this.buytype = buytype
        if (basePopupWindow == null) {
            basePopupWindow = BasePopupWindow(this)
            view = LayoutInflater.from(this@ShopGoodsInfoActivity).inflate(R.layout.view_goodsinfo_buy, null)
            popupoff = view!!.findViewById<View>(R.id.goodsinfo_view_buy_off) as ImageView
            popupoff!!.setOnClickListener(this)
            icon_layout = view!!.findViewById(R.id.goodsinfo_view_icon_layout)
            icon = view!!.findViewById<View>(R.id.goodsinfo_view_buy_icon) as ImageView
            buy_layout = view!!.findViewById(R.id.goodsinfo_view_layout)
            buy_layout!!.setOnClickListener(this)
            yixuanguoge = view!!.findViewById<View>(R.id.goodsinfo_view_buy_guige_true) as TextView
            kucun = view!!.findViewById<View>(R.id.goodsinfo_view_buy_stock) as TextView
            noting = view!!.findViewById<View>(R.id.goodsinfo_view_buy_guige_noting) as TextView
            goodsviewgroup = view!!.findViewById<TagFlowLayout>(R.id.goodsinfo_view_buy_guige)
            price = view!!.findViewById<View>(R.id.goodsinfo_view_buy_price) as TextView
            button_reduce = view!!.findViewById<View>(R.id.goodsinfo_view_buy_reduce_number) as Button
            button_reduce!!.setOnClickListener(this)
            number = view!!.findViewById<View>(R.id.goodsinfo_view_buy_number) as EditText
            button_add = view!!.findViewById<View>(R.id.goodsinfo_view_buy_addnumber) as Button
            button_add!!.setOnClickListener(this)
            val buy = view!!.findViewById<View>(R.id.goodsinfo_view_buy_buy) as Button
            buy.setOnClickListener(this)
            basePopupWindow!!.contentView = view
            basePopupWindow!!.isOutsideTouchable = true
        }
        Glide.with(this)
                .load(goodsInfoBean!!.data.list.goodsstyle[0].proImg)
                .apply(RequestOptions().override(400, 400))
                .into(icon!!)
        yixuanguoge!!.text = guige_str
        kucun_str = goodsInfoBean!!.data.list.goodsstyle[0].kucun
        kucun!!.text = "库存" + kucun_str + "件"
        price!!.text = goodsInfoBean!!.data.list.price
        number_int = goodsInfoBean!!.data.list.minPurchase.toInt()
        number!!.setText(number_int.toString())
        number!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (number!!.text != null && !number!!.text.toString().equals("")) {
                    if (number!!.text.toString().length > 9) {
                        MyToast.showMsg("数量超过最大值")
                    } else {
//                        if (number!!.text.toString().toInt() % goodsInfoBean!!.data.list.orderStep.toInt() == 0)
                        number_int = number!!.text.toString().toInt()
//                        else
//                            MyToast.showMsg("请填写起订倍数的倍数")
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        goodsviewgroup!!.adapter = object : TagAdapter<ShopGoodInfoBean.DataBean.ListBean.GoodsstyleBean>(goodsInfoBean!!.data.list.goodsstyle) {
            override fun getView(parent: FlowLayout?, position: Int, t: ShopGoodInfoBean.DataBean.ListBean.GoodsstyleBean?): View {
                var textview = LayoutInflater.from(this@ShopGoodsInfoActivity).inflate(R.layout.goodsindo_guige_tv, parent, false) as TextView
                textview.text = t!!.styleName
                return textview
            }
        }
        Log.d("goodsinfo", guige_str.toString())
        if (guige_str.toString() != "") {
            for (i in 0 until goodsInfoBean!!.data.list.goodsstyle.size) {
                if (guige_str.toString() == "已选\"${goodsInfoBean!!.data.list.goodsstyle[i].styleName}\"") {
                    goodsviewgroup!!.adapter.setSelectedList(i)
                }
            }
        }
        goodsviewgroup!!.setOnSelectListener {
            if (it.size > 0) {
                var xuanzhong = 0
                for (s in it) {
                    xuanzhong = s
                }
                if (guige_str.isNotEmpty()) {
                    guige_str.setLength(0)
                }
                guige_key = goodsInfoBean!!.data.list.goodsstyle[xuanzhong].styleId
                guige_str.append("已选\"")
                guige_str.append(goodsInfoBean!!.data.list.goodsstyle[xuanzhong].styleName)
                guige_str.append("\"")
                kucun_str = goodsInfoBean!!.data.list.goodsstyle[xuanzhong].kucun
                kucun!!.text = "库存" + kucun_str + "件"
                yixuanguoge!!.text = guige_str
                price!!.text = goodsInfoBean!!.data.list.goodsstyle[xuanzhong].price
                noting!!.visibility = View.GONE
            } else {
                guige_key = ""
                kucun_str = ""
                guige_str.append("请选择商品规格")
                yixuanguoge!!.text = guige_str
                noting!!.visibility = View.GONE
            }
        }
        basePopupWindow!!.showAtLocation(shopgoodsinfo_store_bay_layout, Gravity.BOTTOM, 0, 0)
    }

    fun getAlphaColor(f: Float): Int {
        return Color.argb((f * 255).toInt(), 0xff, 0xff, 0xff)
    }

    fun getLayerAlphaColor(f: Float): Int {
        return Color.argb((f * 255).toInt(), 0x09, 0xc1, 0xf4)
    }

    fun getRadioCheckedAlphaColor(f: Float): Int {
        return Color.argb((f * 255).toInt(), 255, 79, 0)
    }

    fun getRadioAlphaColor(f: Float): Int {
        return Color.argb((f * 255).toInt(), 0, 0, 0)
    }
}