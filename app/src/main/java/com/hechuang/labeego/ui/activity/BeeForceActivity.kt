package com.hechuang.labeego.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hechuang.labeego.R
import com.hechuang.labeego.adapter.Goodsinfo_Viewpage_Adapter
import com.hechuang.labeego.base.BaseFragmentActivity
import com.hechuang.labeego.persenter.activity.BeeForcePersenter
import com.hechuang.labeego.ui.fragment.BeeFragment
import com.hechuang.labeego.view.activity.IBeeForceView
import kotlinx.android.synthetic.main.activity_beeforce.*

class BeeForceActivity : BaseFragmentActivity<BeeForcePersenter>(), IBeeForceView {
    var title_strList = arrayListOf<String>("蜂王蜂力", "小蜜蜂蜂力", "众筹蜂力")
    var fragmentlaist = arrayListOf<Fragment>()
    override fun initlayout(): Int {
        return R.layout.activity_beeforce
    }

    override fun initView() {
        beeforce_back.setOnClickListener { finish() }
        for (i in 0 until title_strList.size) {
            var fdsfs = i + 1
            var queenbeeFragment = BeeFragment()
            val bundle = Bundle()
            bundle.putString("bee_type", fdsfs.toString())
            queenbeeFragment.arguments = bundle
            fragmentlaist.add(queenbeeFragment)
        }
        beeforce_viewpager.adapter = Goodsinfo_Viewpage_Adapter(supportFragmentManager, fragmentlaist, title_strList)
        beeforce_tab.setupWithViewPager(beeforce_viewpager)
    }

    override fun initPersenter() {
        mPersenter = BeeForcePersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }
}