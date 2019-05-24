package com.hechuang.labeego.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.BeeForceInfoBean
import com.hechuang.labeego.persenter.activity.BeeForceInfoPersenter
import com.hechuang.labeego.view.activity.IBeeForceInfoView
import kotlinx.android.synthetic.main.activity_beeforceinfo.*

/**
 * 交易记录详情
 * 20180828
 */
class BeeForceInfoActivity : BaseActivity<BeeForceInfoPersenter>(), IBeeForceInfoView {
    override fun getinfodatasuccess(databean: BeeForceInfoBean) {
        beeforceinfo_price.text = databean.data.list.amount
        beeforceinfo_leixing.text = databean.data.list.title
        beeforceinfo_type.text = databean.data.list.typename
        info_adatper!!.setNewData(databean.data.list.infolist)
    }

    override fun getinfodataerror(msg: String) {
    }

    override fun initlayout(): Int {
        return R.layout.activity_beeforceinfo
    }

    var beeforceid = ""
    var beeforcetype = ""
    var info_adatper: BaseQuickAdapter<BeeForceInfoBean.DataBean.ListBean.InfolistBean, BaseViewHolder>? = null
    override fun initView() {
        beeforceinfo_back.setOnClickListener { finish() }
        beeforceid = intent.getStringExtra("beeid")
        beeforcetype = intent.getStringExtra("beetype")
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        beeforceinfo_recycler.layoutManager = layoutmanager
        info_adatper = object : BaseQuickAdapter<BeeForceInfoBean.DataBean.ListBean.InfolistBean, BaseViewHolder>(R.layout.adapter_beeforceinfo) {
            override fun convert(helper: BaseViewHolder?, item: BeeForceInfoBean.DataBean.ListBean.InfolistBean?) {
                helper!!.setText(R.id.adapter_beeforceinfo_text, item!!.infolist)
                        .setText(R.id.adapter_beeforceinfo_title, item.title)
            }
        }
        beeforceinfo_recycler.adapter = info_adatper
        info_adatper!!.setEnableLoadMore(false)
        mPersenter!!.getinfodata(beeforcetype, beeforceid)
    }

    override fun initPersenter() {
        mPersenter = BeeForceInfoPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }
}