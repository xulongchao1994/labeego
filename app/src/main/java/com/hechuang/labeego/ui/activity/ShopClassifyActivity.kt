package com.hechuang.labeego.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.adapter.LeftRecyclerAdapter
import com.hechuang.labeego.adapter.RightRecyclerAdapter
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.Allianceshop_classify_bean
import com.hechuang.labeego.bean.ShopClassify_RightBean
import com.hechuang.labeego.bean.ShopClassify_liftBean
import com.hechuang.labeego.persenter.activity.ShopClassifyPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IShopClassifyView
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_shopclassify.*

@SuppressLint("StaticFieldLeak")
var liftadapter: LeftRecyclerAdapter? = null
var rightadatper: RightRecyclerAdapter? = null
var isScroll = false//用于判断是否是用户滑动

class ShopClassifyActivity : BaseActivity<ShopClassifyPersenter>(), IShopClassifyView {
    override fun getdata_error(string: String) {
    MyToast.showMsg(string)
    }

    override fun getdata_success(allianceshop_classify_bean: Allianceshop_classify_bean) {
        for (i in 0 until allianceshop_classify_bean.data.list.size) {
            liftbean.add(ShopClassify_liftBean(allianceshop_classify_bean.data.list[i].name, i == 0, allianceshop_classify_bean.data.list[i].id))
        }

        liftadapter = LeftRecyclerAdapter(this@ShopClassifyActivity, liftbean, shopclassify_lift)
        shopclassify_lift.adapter = liftadapter
        liftadapter!!.setItemClickListener {
            liftadapter!!.getSelectedPosition(it)
            rightadatper!!.getSelectedPosition(it)
        }
        rightadatper = RightRecyclerAdapter(this, allianceshop_classify_bean.data.list, shopclassify_right)
        rightadatper!!.setItemClickListener {

        }
        shopclassify_right.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var layoutmanager = recyclerView!!.layoutManager as LinearLayoutManager
//                    //获取最后一个可见view的位置    这两个随便选一个
                //获取第一个可见view的位置
                var firstItemPosition = layoutmanager.findFirstVisibleItemPosition()

                liftadapter!!.getSelectedPosition(firstItemPosition)

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                isScroll = newState == RecyclerView.SCROLL_STATE_DRAGGING

            }
        })
        shopclassify_right.adapter = rightadatper
    }

    var liftbean = arrayListOf<ShopClassify_liftBean>()
    override fun initlayout(): Int {
        return R.layout.activity_shopclassify
    }

    override fun initView() {
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        shopclassify_lift.layoutManager = layoutmanager
        var layoutmanager_right = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        shopclassify_right.layoutManager = layoutmanager_right
        mPersenter!!.getdata()

        shopclassify_back.setOnClickListener { finish() }
        shopclassify_edit.setOnClickListener {
            startActivity(Intent(this@ShopClassifyActivity, SearchShopActivity::class.java))
        }
        shopclassify_edit_img.setOnClickListener { startActivity(Intent(this@ShopClassifyActivity, SearchShopActivity::class.java)) }
    }

    override fun initPersenter() {
        mPersenter = ShopClassifyPersenter(this, this)
    }

    override fun showloading() {
    }

    override fun dismissloading() {
    }

}