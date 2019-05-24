package com.hechuang.labeego.ui.fragment

import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseFragment
import com.hechuang.labeego.bean.Allianceshop_BannerBean
import com.hechuang.labeego.bean.Allianceshop_Classify_ShopBean
import com.hechuang.labeego.bean.Allianceshop_classify_bean
import com.hechuang.labeego.bean.Allianceshop_shop_bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.fragment.AllianceShopPetenter
import com.hechuang.labeego.tools.Util.GlideImageLoader
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RoundCorner
import com.hechuang.labeego.ui.activity.*
import com.hechuang.labeego.view.fragment.IAllianceShopFragmentView
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_allianceshop.*

/**
 *  商家首页，获取数据时一步一步来  规划7个接口
 * 1 获取轮播图数据
 * 2 获取分类数据
 * 3 获取广告图数据
 * 4 获取超值抢购数据（第一版先不做
 * 5 获取大牌甄选数据
 * 6 获取人气爆款数据
 * 7 获取猜你喜欢数据
 * 8 获取商家分类信息
 */

class AllianceShopFragment : BaseFragment<AllianceShopPetenter>(), IAllianceShopFragmentView {

    override fun getshopclassify(allianceshop_Classify_ShopBean: Allianceshop_Classify_ShopBean) {
        classify_shopadapter!!.setNewData(allianceshop_Classify_ShopBean.data.imgs)
        classify_shopadapter!!.setOnItemClickListener { adapter, view, position ->
            if (allianceshop_Classify_ShopBean.data.imgs[position].url == "") {
                MyToast.showMsg(allianceshop_Classify_ShopBean.data.imgs[position].msg)
            } else {
                if (allianceshop_Classify_ShopBean.data.imgs[position].url.contains("999000")) {
                    var intent = Intent(activity!!, JLWebActivity::class.java)
                    intent.putExtra("web", allianceshop_Classify_ShopBean.data.imgs[position].url)
                    startActivity(intent)
                } else {
                    var intent = Intent(activity!!, WebsActivity::class.java)
                    intent.putExtra("web", allianceshop_Classify_ShopBean.data.imgs[position].url)
                    startActivity(intent)
                }
            }
        }
    }

    /**
     * 获取超值抢购
     */
    override fun getscarebuying(allianceshop_shop_bean: Allianceshop_shop_bean) {
        allianceshop_scarebuying_layout.visibility = View.VISIBLE
        mPersenter!!.getsuper()
    }

    /**
     * 获取大牌甄选
     */
    override fun getsuper(allianceshop_shop_bean: Allianceshop_shop_bean) {
        allianceshop_super_layout.visibility = View.VISIBLE
        superadapter!!.setNewData(allianceshop_shop_bean.data.list)
        mPersenter!!.gethigh()
        superadapter!!.setOnItemClickListener { adapter, view, position ->
            if (allianceshop_shop_bean.data.list[position].url != "") {
//                if (allianceshop_shop_bean.data.list[position].proId != "") {
//                    startgoodinfo(allianceshop_shop_bean.data.list[position].proId, allianceshop_shop_bean.data.list[position].proImg)
//                }
                var intent = Intent(activity!!, WebsActivity::class.java)
                intent.putExtra("web", allianceshop_shop_bean.data.list[position].url)
                startActivity(intent)
            }
        }
    }

    /**
     * 获取人气爆款
     */
    override fun gethigh(allianceshop_shop_bean: Allianceshop_shop_bean) {
        allianceshop_high_layout.visibility = View.VISIBLE
        highadapter!!.setNewData(allianceshop_shop_bean.data.list)
        mPersenter!!.getliske()
        highadapter!!.setOnItemClickListener { adapter, view, position ->
            if (allianceshop_shop_bean.data.list[position].url != "") {
                var intent = Intent(activity, WebsActivity::class.java)
                intent.putExtra("web", allianceshop_shop_bean.data.list[position].url)
                startActivity(intent)
            }
//            if (allianceshop_shop_bean.data.list[position].proId != "") {
//                startgoodinfo(allianceshop_shop_bean.data.list[position].proId, allianceshop_shop_bean.data.list[position].proImg)
//            }
        }
    }

    /**
     * 猜你喜欢
     */
    override fun getlike(allianceshop_shop_bean: Allianceshop_shop_bean) {
        allianceshop_like_layout.visibility = View.VISIBLE
        likeadapter!!.setNewData(allianceshop_shop_bean.data.list)
        likeadapter!!.setOnItemClickListener { adapter, view, position ->
            if (allianceshop_shop_bean.data.list[position].url != "") {
                var intent = Intent(activity, WebsActivity::class.java)
                intent.putExtra("web", allianceshop_shop_bean.data.list[position].url)
                startActivity(intent)
            }
//            if (allianceshop_shop_bean.data.list[position].proId != "") {
//                startgoodinfo(allianceshop_shop_bean.data.list[position].proId, allianceshop_shop_bean.data.list[position].proImg)
//            }
        }
        mPersenter!!.getshopclassify()
    }

    /**
     * 四个广告位
     */
    override fun getguanggao(alllianceshopbannerbean: Allianceshop_BannerBean) {
//        for (i in 0 until alllianceshopbannerbean.data.imgs.size) {
//            Log.d("imgs", alllianceshopbannerbean.data.imgs[i].img)
//        }
        Glide.with(activity!!).load(alllianceshopbannerbean.data.imgs[0].img)
                .apply(RequestOptions().override(1000, 400))
                .apply(RequestOptions.bitmapTransform(RoundCorner(activity!!, 10f, 10f, 10f, 10f)))
                .into(allianceshop_ad_top)
        Glide.with(activity!!).load(alllianceshopbannerbean.data.imgs[1].img)
                .apply(RequestOptions.bitmapTransform(RoundCorner(activity!!, 5f, 5f, 5f, 5f)))
                .apply(RequestOptions().override(200, 200)).into(allianceshop_ad__button_lift)
        Glide.with(activity!!).load(alllianceshopbannerbean.data.imgs[2].img)
                .apply(RequestOptions.bitmapTransform(RoundCorner(activity!!, 5f, 5f, 5f, 5f)))
                .apply(RequestOptions().override(200, 200)).into(allianceshop_ad__button_among)
        Glide.with(activity!!).load(alllianceshopbannerbean.data.imgs[3].img)
                .apply(RequestOptions.bitmapTransform(RoundCorner(activity!!, 5f, 5f, 5f, 5f)))
                .apply(RequestOptions().override(200, 200)).into(allianceshop_ad__button_right)
        allianceshop_ad_top.setOnClickListener {
            if (alllianceshopbannerbean.data.imgs[0].style == 5) {
                var intent = Intent(activity!!, JLWebActivity::class.java)
                intent.putExtra("web", alllianceshopbannerbean.data.imgs[0].url)
                startActivity(intent)
            } else {
                startweba(alllianceshopbannerbean.data.imgs[0].url)
            }
        }
        allianceshop_ad__button_lift.setOnClickListener {
//            if (alllianceshopbannerbean.data.imgs[1].proId != "") {
//                startgoodinfo(alllianceshopbannerbean.data.list[position].proId, allianceshop_shop_bean.data.list[position].proImg)
//            }
            startweba(alllianceshopbannerbean.data.imgs[1].url)
        }
        allianceshop_ad__button_among.setOnClickListener {
            startweba(alllianceshopbannerbean.data.imgs[2].url)
        }
        allianceshop_ad__button_right.setOnClickListener {
            startweba(alllianceshopbannerbean.data.imgs[3].url)
        }
        mPersenter!!.getscarebuying()
    }

//    fun startgoodinfo(proid: String, proimg: String) {
//        var intent = Intent(activity, ShopGoodsInfoActivity::class.java)
//        intent.putExtra("pid", proid)
//        intent.putExtra("proimg", proimg)
//        startActivity(intent)
//    }

    private fun startweba(url: String?) {
        if (url != "") {
            var intnet = Intent(activity, WebsActivity::class.java)
            intnet.putExtra("web", url)
            startActivity(intnet)
        }
    }

    /**
     * 分类图标
     */
    override fun getclassifysuccess(alllianceshopbannerbean: Allianceshop_BannerBean) {
        classifyadapter!!.setNewData(alllianceshopbannerbean.data.imgs)
        classifyadapter!!.setOnItemClickListener { adapter, view, position ->
            if (alllianceshopbannerbean.data.imgs[position].name != "全部分类") {
                var intent = Intent(activity, ShopListActivity::class.java)
                intent.putExtra("classify", alllianceshopbannerbean.data.imgs[position].id)
                startActivity(intent)
            } else {
                startActivity(Intent(activity, ShopClassifyActivity::class.java))
            }
        }

        mPersenter!!.getguanggao(UserData.USERNAME, UserData.USERTOKENID)
    }

    /**
     * 获取数据失败
     * int 用于判断走到了第几步
     * 1 获取轮播图数据
     * 2 获取分类数据
     * 3 获取广告图数据
     * 4 获取超值抢购数据（第一版先不做
     * 5 获取大牌甄选数据
     * 6 获取人气爆款数据
     * 7 获取猜你喜欢数据
     */
    //设置数据
    override fun getallianceshoperror(string: String, int: Int) {
        when (int) {
            1 -> {
                mPersenter!!.getclassify()
            }
            2 -> {
                mPersenter!!.getguanggao(UserData.USERNAME, UserData.USERTOKENID)
            }
            3 -> {
                mPersenter!!.getscarebuying()
            }
            4 -> {
                allianceshop_scarebuying_layout.visibility = View.GONE
                mPersenter!!.getsuper()
            }
            5 -> {
                allianceshop_super_layout.visibility = View.GONE
                mPersenter!!.gethigh()
            }
            6 -> {
                allianceshop_high_layout.visibility = View.GONE
                mPersenter!!.getliske()
            }
            7 -> {
                allianceshop_like_layout.visibility = View.GONE
                mPersenter!!.getshopclassify()
            }
        }
    }

    /**
     * 获取轮播图数据
     */
    var imagepath = arrayListOf<String>()

    override fun getbannersuccess(alllianceshopbannerbean: Allianceshop_BannerBean) {
        for (i in 0 until alllianceshopbannerbean.data.imgs.size) {
            imagepath.add(alllianceshopbannerbean.data.imgs[i].img)
        }
        allianceshop_banner.isAutoPlay(true)
        allianceshop_banner.setDelayTime(3000)
        allianceshop_banner.setIndicatorGravity(BannerConfig.CENTER)
        allianceshop_banner.setBannerStyle(BannerConfig.NUM_INDICATOR)
        allianceshop_banner.setIndicatorGravity(BannerConfig.RIGHT)
        allianceshop_banner.setImageLoader(GlideImageLoader())
        allianceshop_banner.setImages(imagepath)
        allianceshop_banner.start()
        allianceshop_banner.setOnBannerListener {
            startweba(alllianceshopbannerbean.data.imgs[it].url)
        }
        mPersenter!!.getclassify()
    }


    override fun initPersenter() {
        mPersenter = AllianceShopPetenter(this, activity!!)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_allianceshop
    }

    var scarebuyingadapter: BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>? = null
    var superadapter: BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>? = null
    var highadapter: BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>? = null
    var likeadapter: BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>? = null
    var classifyadapter: BaseQuickAdapter<Allianceshop_BannerBean.DataBean.ImgsBean, BaseViewHolder>? = null
    var classify_shopadapter: BaseQuickAdapter<Allianceshop_Classify_ShopBean.DataBean.ImgsBean, BaseViewHolder>? = null

    override fun initViews(view: View) {
        //----------设置轮播图
        var dispaywidht = resources.displayMetrics
        var layoutparams = LinearLayout.LayoutParams(dispaywidht.widthPixels, dispaywidht.widthPixels / 2)
        allianceshop_banner.layoutParams = layoutparams
        //----------------------------
        var gridlayoutmanager_grid = GridLayoutManager(activity, 5)
        allianceshop_classify_shop.layoutManager = gridlayoutmanager_grid
        classify_shopadapter = object : BaseQuickAdapter<Allianceshop_Classify_ShopBean.DataBean.ImgsBean, BaseViewHolder>(R.layout.adapter_allianceshop_classify_shop_item) {
            override fun convert(helper: BaseViewHolder?, item: Allianceshop_Classify_ShopBean.DataBean.ImgsBean?) {
                Glide.with(activity!!)
                        .load(item!!.img)
                        .apply(RequestOptions().error(R.mipmap.applogo))
                        .into(helper!!.getView(R.id.adapter_allianceshop_classify_shop_item_icon))
                helper.setText(R.id.adapter_allianceshop_classify_shop_item_text, item.name)
            }
        }
        allianceshop_classify_shop.adapter = classify_shopadapter
        //-----------设置分类
        var gridlayoutmanager = GridLayoutManager(activity, 5)
        allianceshop_classify.layoutManager = gridlayoutmanager
        classifyadapter = object : BaseQuickAdapter<Allianceshop_BannerBean.DataBean.ImgsBean, BaseViewHolder>(R.layout.adapter_allianceshop_classify_item) {
            override fun convert(helper: BaseViewHolder?, item: Allianceshop_BannerBean.DataBean.ImgsBean?) {
                Glide.with(activity!!)
                        .load(item!!.img)
                        .apply(RequestOptions().error(R.mipmap.applogo)).into(helper!!.getView(R.id.adapter_allianceshop_classify_item_icon))
                helper.setText(R.id.adapter_allianceshop_classify_item_text, item.name)
            }
        }
        allianceshop_classify.adapter = classifyadapter
        classifyadapter!!.setEnableLoadMore(false)
        allianceshop_classify.isNestedScrollingEnabled = false
        //---------设置广告图

        //------------设置超值抢购
        var layoutmanager = LinearLayoutManager(activity)
        layoutmanager.orientation = LinearLayoutManager.HORIZONTAL
        allianceshop_scarebuying_recycler.layoutManager = layoutmanager
        allianceshop_scarebuying_recycler.isNestedScrollingEnabled = false
        scarebuyingadapter = object : BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allianceshop_fengqiang_shop_item) {
            override fun convert(helper: BaseViewHolder?, item: Allianceshop_shop_bean.DataBean.ListBean?) {
                Glide.with(activity!!).load(item!!.proImg)
                        .apply(RequestOptions().error(R.mipmap.applogo)).into(helper!!.getView(R.id.adapter_allianceshop_fengqiang_shop_icon))
                helper.setText(R.id.adapter_allianceshop_fengqiang_shop_name, item.proName)
                        .setText(R.id.adapter_allianceshop_shop_fengqiang_price_price, item.price)
                        .setText(R.id.adapter_allianceshop_shop_fengqiang_price_shichangjia, item.marketprice)
                helper.getView<TextView>(R.id.adapter_allianceshop_shop_fengqiang_price_shichangjia).paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        allianceshop_scarebuying_recycler.adapter = scarebuyingadapter
        scarebuyingadapter!!.setEnableLoadMore(false)
//        allianceshop_scarebuying_recycler.addItemDecoration(GridDividerItemDecoration(activity, 2, 0, false))
        //-----------------设置大牌甄选
        var superlayoutmanager = GridLayoutManager(activity, 3)
        allianceshop_super_recycler.layoutManager = superlayoutmanager
        allianceshop_super_recycler.isNestedScrollingEnabled = false
        superadapter = object : BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allianceshop_shop_item) {
            override fun convert(helper: BaseViewHolder?, item: Allianceshop_shop_bean.DataBean.ListBean?) {
                Glide.with(activity!!).load(item!!.proImg)
                        .apply(RequestOptions().error(R.mipmap.applogo))
                        .apply(RequestOptions.bitmapTransform(RoundCorner(activity!!, 10f, 10f, 10f, 10f)))
                        .into(helper!!.getView(R.id.adapter_allianceshop_shop_icon)
                        )
                helper.setText(R.id.adapter_allianceshop_shop_name, item.proName)
                        .setText(R.id.adapter_allianceshop_shop_price_price, item.price)
                        .setText(R.id.adapter_allianceshop_shop_price_shichangjia, item.marketprice)
                        .setGone(R.id.adapter_allianceshop_shop_name, helper.layoutPosition != 0)
                        .setGone(R.id.adapter_allianceshop_shop_price_layout, helper.layoutPosition != 0)
                helper.getView<TextView>(R.id.adapter_allianceshop_shop_price_shichangjia).paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        allianceshop_super_recycler.adapter = superadapter
        superadapter!!.setEnableLoadMore(false)
        superlayoutmanager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }

//        allianceshop_super_recycler.addItemDecoration(GridDividerItemDecoration(activity, 2, 0, false))
        //-----------------设置人气爆款
        var hihglayoutmanager = GridLayoutManager(activity, 3)

        allianceshop_high_recycler.layoutManager = hihglayoutmanager
        allianceshop_high_recycler.isNestedScrollingEnabled = false

        highadapter = object : BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allianceshop_shop_item) {
            override fun convert(helper: BaseViewHolder?, item: Allianceshop_shop_bean.DataBean.ListBean?) {
                Glide.with(activity!!).load(item!!.proImg)
                        .apply(RequestOptions.bitmapTransform(RoundCorner(activity!!, 10f, 10f, 10f, 10f)))
                        .apply(RequestOptions().error(R.mipmap.applogo)).into(helper!!.getView(R.id.adapter_allianceshop_shop_icon))
                helper.setText(R.id.adapter_allianceshop_shop_name, item.proName)
                        .setText(R.id.adapter_allianceshop_shop_price_price, item.price)
                        .setText(R.id.adapter_allianceshop_shop_price_shichangjia, item.marketprice)
                        .setGone(R.id.adapter_allianceshop_shop_name, helper.layoutPosition != 0)
                        .setGone(R.id.adapter_allianceshop_shop_price_layout, helper.layoutPosition != 0)
                helper.getView<TextView>(R.id.adapter_allianceshop_shop_price_shichangjia).paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        allianceshop_high_recycler.adapter = highadapter
        highadapter!!.setEnableLoadMore(false)
        hihglayoutmanager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }

//        allianceshop_high_recycler.addItemDecoration(GridDividerItemDecoration(activity, 2, 0, false))
        //---------------设置猜你喜欢
        var likelayoutmanager = GridLayoutManager(activity, 2)
        allianceshop_like_recycler.layoutManager = likelayoutmanager
        allianceshop_like_recycler.isNestedScrollingEnabled = false
        likeadapter = object : BaseQuickAdapter<Allianceshop_shop_bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_allianceshop_shop_like_item) {
            override fun convert(helper: BaseViewHolder?, item: Allianceshop_shop_bean.DataBean.ListBean?) {
                Glide.with(activity!!)
                        .load(item!!.proImg)
                        .apply(RequestOptions().error(R.mipmap.applogo))
                        .apply(RequestOptions.bitmapTransform(RoundCorner(activity!!, 10f, 10f)))
                        .into(helper!!.getView(R.id.adapter_allianceshop_shop_like_icon))
                helper.setText(R.id.adapter_allianceshop_shop_like_name, item.proName)
                        .setText(R.id.adapter_allianceshop_shop_like_price_price, item.price)
                        .setText(R.id.adapter_allianceshop_shop_like_price_shichangjia, item.marketprice)
                helper.getView<TextView>(R.id.adapter_allianceshop_shop_like_price_shichangjia).paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
        allianceshop_like_recycler.adapter = likeadapter
        likeadapter!!.setEnableLoadMore(false)

//        allianceshop_like_recycler.addItemDecoration(GridDividerItemDecoration(activity, 2, 0, false))
        //----------------------------
        //大牌甄选更多
        allianceshop_super_move.setOnClickListener {
            var intent = Intent(activity, ShopListActivity::class.java)
            intent.putExtra("prefecture", "1")
            startActivity(intent)
        }
        //人气爆款更多
        allianceshop_high_move.setOnClickListener {
            var intent = Intent(activity, ShopListActivity::class.java)
            intent.putExtra("prefecture", "2")
            startActivity(intent)
        }
        //超值抢购更多
        allianceshop_scarebuying_move.setOnClickListener { }
        //------------------获取数据
        mPersenter!!.getbannerdata()
    }

    override fun showloading() {
    }

    override fun dismissloading() {
    }

    override fun onResume() {
        super.onResume()
    }
}