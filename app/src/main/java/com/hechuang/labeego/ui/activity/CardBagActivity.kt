package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.CardBagBean
import com.hechuang.labeego.bean.CardBagListBean
import com.hechuang.labeego.persenter.activity.CardBagPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.ICardBagView
import jp.wasabeef.glide.transformations.CropTransformation
import kotlinx.android.synthetic.main.activity_cardbag.*

class CardBagActivity : BaseActivity<CardBagPersenter>(), ICardBagView {
    override fun getcarddatasuccess(beandata: CardBagBean) {
        for (i in 0 until beandata.data.list.size) {
            var carlistdata = CardBagListBean(true, "", beandata.data.list[i].img, beandata.data.list[i].title, beandata.data.list[i].num)
            cardlist.add(carlistdata)
            for (j in 0 until beandata.data.list[i].info.size) {
                var carlistdata_t = CardBagListBean(beandata.data.list[i].info[j])
                cardlist.add(carlistdata_t)
            }
        }
        adatper_card!!.setNewData(cardlist)
        adatper_card!!.setOnItemClickListener { adapter, view, position ->
            if (cardlist[position].t != null) {
                var intent = Intent(this@CardBagActivity, WebsActivity::class.java)
                intent.putExtra("web", cardlist[position].t.url)
                startActivity(intent)
            }

        }
    }

    override fun getcardtataerror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun initlayout(): Int {
        return R.layout.activity_cardbag
    }

    var cardlist = arrayListOf<CardBagListBean>()
    var adatper_card: BaseSectionQuickAdapter<CardBagListBean, BaseViewHolder>? = null
    override fun initView() {
        cardbag_back.setOnClickListener { finish() }
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        cardbag_recycler.layoutManager = layoutmanager
        adatper_card = getadapter()
        cardbag_recycler.adapter = adatper_card
        mPersenter!!.getdata()
    }

    override fun initPersenter() {
        mPersenter = CardBagPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    fun getadapter(): BaseSectionQuickAdapter<CardBagListBean, BaseViewHolder> {
        var adapter_m = object : BaseSectionQuickAdapter<CardBagListBean, BaseViewHolder>(R.layout.adapter__cardbag_item, R.layout.adapter_cardgag_re_title, cardlist) {
            override fun convertHead(helper: BaseViewHolder?, item: CardBagListBean?) {
                var icon_img = helper!!.getView<ImageView>(R.id.adapter_cardbag_title_icon)
                Glide.with(this@CardBagActivity).load(item!!.img).into(icon_img)
                helper.setText(R.id.adapter_cardbag_title_number, item.mun)
                        .setText(R.id.adapter_cardbag_title_type, item.title)
            }

            override fun convert(helper: BaseViewHolder?, item: CardBagListBean?) {
                var item_icon = helper!!.getView<ImageView>(R.id.adapter_cardbag_item_icon)
                Glide.with(this@CardBagActivity)
                        .load(item!!.t.img)
                        .apply(RequestOptions().override(500, 300).transforms(CropTransformation(this@CardBagActivity, 0, 170, CropTransformation.CropType.TOP)))
                        .into(item_icon)
                helper!!.setText(R.id.adapter_cardbag_item_price, item.t.money)
                        .setText(R.id.adapter_cardbag_item_type, item.t.title)
            }
        }
        adapter_m.setEnableLoadMore(false)

        return adapter_m
    }
}