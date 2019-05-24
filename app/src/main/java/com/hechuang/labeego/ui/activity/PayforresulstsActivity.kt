package com.hechuang.labeego.ui.activity

import android.view.View
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.persenter.activity.PayforreculstsPersenter
import com.hechuang.labeego.view.activity.IPayforresultsView
import kotlinx.android.synthetic.main.activity_payforresults.*

class PayforresulstsActivity : BaseActivity<PayforreculstsPersenter>(), IPayforresultsView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.payforrecults_back -> finish()
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_payforresults
    }

    override fun initView() {
        payforrecults_back.setOnClickListener(this)
    }

    override fun initPersenter() {
        mPersenter = PayforreculstsPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

}