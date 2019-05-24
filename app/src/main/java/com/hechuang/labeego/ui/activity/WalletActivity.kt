package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.WalletBean
import com.hechuang.labeego.persenter.activity.WalletPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IWalletView
import kotlinx.android.synthetic.main.activity_wallet.*

class WalletActivity : BaseActivity<WalletPersenter>(), IWalletView {
    override fun getdatasuccess(walletbean: WalletBean) {
        Glide.with(this@WalletActivity).load(walletbean.data.list.money_img).into(wallet_icon)
        wallet_title_moeny.text = walletbean.data.list.money
        wallet_title.text = walletbean.data.list.title
        wallet_cankaomoeny.text = walletbean.data.list.price
        if (walletbean.data.list.bring_forward != null) {
            adapter!!.setNewData(walletbean.data.list.bring_forward)
        }
        if (walletbean.data.list.download != null) {
            wallet_recyler_button.visibility = View.VISIBLE
            if (walletbean.data.list.download.size < 2) {
                var layoutmanager = LinearLayoutManager(this)
                layoutmanager.orientation = LinearLayoutManager.VERTICAL
                wallet_recyler_button.layoutManager = layoutmanager
            } else {
                var gridlayout_button = GridLayoutManager(this, 2)
                wallet_recyler_button.layoutManager = gridlayout_button
            }

            button_adapter = object : BaseQuickAdapter<WalletBean.DataBean.ListBean.DownloadBean, BaseViewHolder>(R.layout.adapter_wallet_button) {
                override fun convert(helper: BaseViewHolder?, item: WalletBean.DataBean.ListBean.DownloadBean?) {
                    var icon = helper!!.getView<ImageView>(R.id.adapter_wallet_button_icon)
                    Glide.with(this@WalletActivity).load(item!!.img).into(icon)
                    helper.setText(R.id.adapter_wallet_button_text, item.title)
                }
            }
            button_adapter!!.setEnableLoadMore(false)
            wallet_recyler_button.adapter = button_adapter

            button_adapter!!.setNewData(walletbean.data.list.download)
        } else {
            wallet_recyler_button.visibility = View.GONE
        }
        wallet_notes_title.text = walletbean.data.list.note_header
        if (walletbean.data.list.note_list != null) {
            notes_adapter!!.setNewData(walletbean.data.list.note_list)
        }
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (walletbean.data.list.bring_forward[position].url == "") {
                MyToast.showMsg("此功能维护中")
            } else {
                var intent = Intent(this@WalletActivity, WebsActivity::class.java)
                intent.putExtra("web", walletbean.data.list.bring_forward[position].url)
                startActivity(intent)
            }
        }
        button_adapter!!.setOnItemClickListener { adapter, view, position ->
            if (walletbean.data.list.bring_forward[position].url == "") {
                MyToast.showMsg("此功能维护中")
            } else {
                var intent = Intent(this@WalletActivity, WebsActivity::class.java)
                intent.putExtra("web", walletbean.data.list.download[position].url)
                startActivity(intent)
            }
        }
    }

    override fun getdataerror(msg: String) {
    }

    override fun initlayout(): Int {
        return R.layout.activity_wallet
    }

    var adapter: BaseQuickAdapter<WalletBean.DataBean.ListBean.BringForwardBean, BaseViewHolder>? = null
    var button_adapter: BaseQuickAdapter<WalletBean.DataBean.ListBean.DownloadBean, BaseViewHolder>? = null
    var notes_adapter: BaseQuickAdapter<String, BaseViewHolder>? = null
    override fun initView() {
        wallet_back.setOnClickListener { finish() }
        var gridlayout = GridLayoutManager(this, 4)
        wallet_recycler.layoutManager = gridlayout
        adapter = object : BaseQuickAdapter<WalletBean.DataBean.ListBean.BringForwardBean, BaseViewHolder>(R.layout.adapter_wallet) {
            override fun convert(helper: BaseViewHolder?, item: WalletBean.DataBean.ListBean.BringForwardBean?) {
                var icon = helper!!.getView<ImageView>(R.id.adapter_wallet_icon)
                Glide.with(this@WalletActivity).load(item!!.img).into(icon)
                helper.setText(R.id.adapter_wallet_text, item.title)
            }
        }
        adapter!!.setEnableLoadMore(false)
        wallet_recycler.adapter = adapter


        var layoutlayout = LinearLayoutManager(this)
        layoutlayout.orientation = LinearLayoutManager.VERTICAL
        wallet_notes_recycler.layoutManager = layoutlayout
        notes_adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_wallet_notes) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.adapter_wallet_notes_notes, item)
            }
        }
        notes_adapter!!.setEnableLoadMore(false)
        wallet_notes_recycler.adapter = notes_adapter
        mPersenter!!.getwalletdata()
    }

    override fun initPersenter() {
        mPersenter = WalletPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    override fun onResume() {
        super.onResume()
        mPersenter!!.getwalletdata()
    }
}