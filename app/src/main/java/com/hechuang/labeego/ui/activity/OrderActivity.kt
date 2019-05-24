package com.hechuang.labeego.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.widget.TextView
import com.hechuang.labeego.R
import com.hechuang.labeego.R.id.*
import com.hechuang.labeego.adapter.Order_Viewpage_Adapter
import com.hechuang.labeego.base.BaseFragmentActivity
import com.hechuang.labeego.persenter.activity.OrderPersenter
import com.hechuang.labeego.ui.fragment.Order_Type_Fragment
import com.hechuang.labeego.ui.fragment.TheRaiseFragment
import com.hechuang.labeego.view.activity.IOrderView
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : BaseFragmentActivity<OrderPersenter>(), IOrderView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.order_back -> {
                finish()
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_order
    }

    var fragmentadapter: Order_Viewpage_Adapter? = null
    var titles = arrayListOf("全部", "待付款", "待发货", "待收货", "已完成", "众筹")
    var fragments = arrayListOf<Fragment>()
    override fun initView() {
        order_back.setOnClickListener(this)
        for (i in 0 until titles.size) {
            if (titles[i] == "众筹") {
                var zhongchouframgent = TheRaiseFragment()
                fragments.add(zhongchouframgent)
            } else {
                var fargment = Order_Type_Fragment()
                val bundle = Bundle()
                bundle.putString("ordertype", titles[i])
                fargment.arguments = bundle
                fragments.add(fargment)
            }
        }
        fragmentadapter = Order_Viewpage_Adapter(supportFragmentManager, fragments)
        order_viewpager.adapter = fragmentadapter
        order_tablayout.setupWithViewPager(order_viewpager)
        for (i in 0 until titles.size) {
            var tab = order_tablayout.getTabAt(i)
            tab!!.setCustomView(R.layout.order_tab_item)
            var item_text = tab.customView!!.findViewById<TextView>(R.id.order_teb_item_text)
            item_text.text = titles[i]
            if (i == 0) {
                item_text.setTextColor(resources.getColor(R.color.setnametextcolor))
            }

        }
        order_tablayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<TextView>(R.id.order_teb_item_text).setTextColor(resources.getColor(R.color.black))
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<TextView>(R.id.order_teb_item_text).setTextColor(resources.getColor(R.color.setnametextcolor))
                order_viewpager.currentItem = tab.position
            }
        })
        var intent_str = intent.getStringExtra("ordertype")
        when (intent_str) {
            "全部" -> {
                order_tablayout.getTabAt(0)!!.select()
            }
            "待付款" -> {
                order_tablayout.getTabAt(1)!!.select()
            }
            "待发货" -> {
                order_tablayout.getTabAt(2)!!.select()
            }
            "待收货" -> {
                order_tablayout.getTabAt(3)!!.select()
            }
            "已完成" -> {
                order_tablayout.getTabAt(4)!!.select()
            }
            "众筹" -> {
                order_tablayout.getTabAt(5)!!.select()
            }
        }
    }

    override fun initPersenter() {
        mPersenter = OrderPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

}