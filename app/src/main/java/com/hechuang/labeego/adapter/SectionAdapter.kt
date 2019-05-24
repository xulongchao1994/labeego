package com.hechuang.labeego.adapter

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.bean.HoneyListbean

class SectionAdapter(layoutResId: Int, sectionHeadResId: Int, data: List<HoneyListbean>) : BaseSectionQuickAdapter<HoneyListbean, BaseViewHolder>(layoutResId, sectionHeadResId, data) {

    override fun convertHead(helper: BaseViewHolder, item: HoneyListbean) {
        helper.setText(R.id.honey_title_text, item.header)
    }

    override fun convert(helper: BaseViewHolder, item: HoneyListbean) {
        helper.setText(R.id.honey_time, item.t.addTime)
                .setText(R.id.honey_moeny, item.t.amount)
                .setText(R.id.honey_memo, item.t.memo)
                .setText(R.id.honey_typename, item.t.typeName)

//        helper.setText(R.id.honey_time, "fdsaf")
//                .setText(R.id.honey_moeny, "fadsfa")
//                .setText(R.id.honey_memo, "fasdfa")
//                .setText(R.id.honey_typename, "WelcomePersenter")
    }
}
