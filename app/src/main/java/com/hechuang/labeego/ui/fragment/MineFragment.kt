package com.hechuang.labeego.ui.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseViewpageFragment
import com.hechuang.labeego.bean.MineBean
import com.hechuang.labeego.persenter.fragment.MinePersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RxTextViewVertical
import com.hechuang.labeego.ui.activity.*
import com.hechuang.labeego.view.fragment.IMineView
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * 个人中心
 */
class MineFragment : BaseViewpageFragment<MinePersenter>(), IMineView {
    override fun fetchData() {
//        adapterhoder = false
//        mPersenter!!.getdata()
    }

    var adapterhoder = false
    override fun getdatesuccess(bean: MineBean) {
        if (!adapterhoder) {
            adapter = object : BaseQuickAdapter<MineBean.DataBean.ListBean.BagListBean, BaseViewHolder>(R.layout.adapter_mine) {
                override fun convert(helper: BaseViewHolder?, item: MineBean.DataBean.ListBean.BagListBean?) {
                    Glide.with(activity!!).load(item!!.avatarurl).into(helper!!.getView(R.id.adapter_mine_img))
                    helper!!.setText(R.id.adapter_mine_text, item!!.title)
                }
            }
            mine_recycler.adapter = adapter
            adapter!!.addHeaderView(topview(bean))
            adapter!!.addFooterView(buttonview(bean))
            adapterhoder = true
        } else {
            usertext!!.text = bean.data.list.name
        }
        adapter!!.setNewData(bean.data.list.bag_list)
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (bean.data.list.bag_list[position].title == "我的卡包") {
                if (bean.data.list.bag_list[position].url == "") {
                    startActivity(Intent(activity, CardBagActivity::class.java))
                } else {
                    var intent = Intent(activity, WebsActivity::class.java)
                    intent.putExtra("web", bean.data.list.bag_list[position].url)
                    startActivity(intent)
                }
            } else {
                if (bean.data.list.bag_list[position].url != null && bean.data.list
                                .bag_list[position].url != "") {
                    var intent = Intent(activity, WebsActivity::class.java)
                    intent.putExtra("web", bean.data.list.bag_list[position].url)
                    startActivity(intent)
                } else {
                    MyToast.showMsg("此功能暂时未开通")
                }
            }
        }
    }

    override fun getminedataerror(msg: String) {
        if (msg.equals("未登录")) {
            activity!!.finish()
            startActivity(Intent(activity, LoginActivity::class.java))
        } else {
            MyToast.showMsg(msg)
        }
    }

    override fun initPersenter() {
        mPersenter = MinePersenter(this, activity!!)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_mine
    }

    var adapter: BaseQuickAdapter<MineBean.DataBean.ListBean.BagListBean, BaseViewHolder>? = null
    override fun initViews() {
        var linearlinenenr = GridLayoutManager(activity, 4)
        mine_recycler.layoutManager = linearlinenenr
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    var usertext: TextView? = null
    fun topview(bean: MineBean): View {
        var view = LayoutInflater.from(activity).inflate(R.layout.view_minetop, null)
        var usericon = view.findViewById<ImageView>(R.id.minetop_usericon)
        usertext = view.findViewById<TextView>(R.id.minetop_username)
        var usertype = view.findViewById<ImageView>(R.id.minetop_usertype)
        var titleimg = view.findViewById<ImageView>(R.id.minetop_title_img)
        Glide.with(activity!!).load(bean.data.list.setting.avatarurl).into(titleimg)
        Glide.with(activity!!).load(bean.data.list.avatarurl)
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions().error(R.mipmap.labeego_icon).centerCrop())
                .into(usericon)
        Glide.with(activity!!).load(bean.data.list.usertype).into(usertype)
        titleimg.setOnClickListener {
            //            if (bean.data.list.setting.url == "") {
            startActivity(Intent(activity, SettingActivity::class.java))
//            } else {
//                var weburl = bean.data.list.setting.url
//                var intent = Intent(activity, WebsActivity::class.java)
//                intent.putExtra("web", weburl)
//                startActivity(intent)
//            }
        }

        var rxtext = view.findViewById<RxTextViewVertical>(R.id.minetop_isfriend_rxtext)
        var rxtext_layout = view.findViewById<LinearLayout>(R.id.minetop_isfriend_layout);
        if (bean.data.list.order_list != null && bean.data.list.order_list.size > 0) {
            var textarray = arrayListOf<String>()
            for (i in 0 until bean.data.list.order_list.size) {
                textarray.add(bean.data.list.order_list[i].title)
            }
            rxtext_layout.visibility = View.VISIBLE
            rxtext.setTextList(textarray)
            rxtext.setText(15f, 5, -0xffffff)
            rxtext.setTextStillTime(3000)//设置停留时长间隔
            rxtext.setAnimTime(300)//设置进入和退出的时间间隔
            rxtext.setOnItemClickListener {
                if (bean.data.list.order_list[it].url != "") {
                    var intent = Intent(activity, WebsActivity::class.java)
                    intent.putExtra("web", bean.data.list.order_list[it].url)
                    startActivity(intent)
                }
            }
            rxtext.startAutoScroll()
        } else {
            rxtext_layout.visibility = View.GONE
        }
        usertext!!.text = bean.data.list.name
        var minetoprecycler = view.findViewById<RecyclerView>(R.id.minetop_recycler)
        var h_int: Int
        if (bean.data.list.column.size > 4) {
            h_int = 5
        } else {
            h_int = 4
        }
        var gridlayoutmanager = GridLayoutManager(activity, h_int)
        minetoprecycler.layoutManager = gridlayoutmanager
        var adapter = object : BaseQuickAdapter<MineBean.DataBean.ListBean.ColumnBean, BaseViewHolder>(R.layout.adapter_minetop_recycler) {
            override fun convert(helper: BaseViewHolder?, item: MineBean.DataBean.ListBean.ColumnBean?) {
                var icon = helper!!.getView<ImageView>(R.id.minetop_item_icon)
                Glide.with(activity!!).load(item!!.avatarurl).apply(RequestOptions().error(R.mipmap.labeego_icon)).into(icon)
                helper.setText(R.id.minetop_item_text, item.title)
            }
        }
        adapter.setOnItemClickListener { adapter, view, position ->
            if (bean.data.list.column[position].title == "") {
                var intent = Intent(activity, WebsActivity::class.java)
                intent.putExtra("web", bean.data.list.column[position].url)
                startActivity(intent)
            } else {
                when (bean.data.list.column[position].title) {
                    "我的蜂浆" -> startActivity(Intent(activity, MyHoenyAcativity::class.java))
                    "我的蜂蜜" -> startActivity(Intent(activity, HoenyActivity::class.java))
                    "我的蜂力" -> startActivity(Intent(activity, BeeForceActivity::class.java))
                    "我的钱包" -> startActivity(Intent(activity, WalletActivity::class.java))
                }
            }
        }
        adapter.setEnableLoadMore(false)
        minetoprecycler.adapter = adapter
        adapter.setNewData(bean.data.list.column)
        return view
    }

    fun buttonview(bean: MineBean): View {
        var view = LayoutInflater.from(activity).inflate(R.layout.view_minebutton, null)
        var mineordermoretext = view.findViewById<TextView>(R.id.minebutton_moreorder_text)
        var minebuttonmoreimg = view.findViewById<ImageView>(R.id.minebutton_moreorder_img)
        mineordermoretext.text = bean.data.list.most_order.title
        mineordermoretext.setOnClickListener {
            if (bean.data.list.most_order.url == "") {
                var intent = Intent(activity, OrderActivity::class.java)
                intent.putExtra("ordertype", "全部")
                startActivity(intent)
            } else {
                var intent = Intent(activity, WebsActivity::class.java)
                intent.putExtra("web", bean.data.list.most_order.url)
                startActivity(intent)
            }
        }
        minebuttonmoreimg.setOnClickListener {
            if (bean.data.list.most_order.url == "") {
                var intent = Intent(activity, OrderActivity::class.java)
                intent.putExtra("ordertype", "全部")
                startActivity(intent)
            } else {
                var intent = Intent(activity, WebsActivity::class.java)
                intent.putExtra("web", bean.data.list.most_order.url)
                startActivity(intent)
            }
        }
        var minebuttonrecycler = view.findViewById<RecyclerView>(R.id.minebutton_moreorder_recycler)
        var h_int = 4
        if (bean.data.list.column.size > 4) {
            h_int = 5
        } else {
            h_int = 4
        }
        var gridlayoutmanager = GridLayoutManager(activity, h_int)
        minebuttonrecycler.layoutManager = gridlayoutmanager
        var adapter = object : BaseQuickAdapter<MineBean.DataBean.ListBean.OrderTypeBean, BaseViewHolder>(R.layout.adapter_minetop_recycler) {
            override fun convert(helper: BaseViewHolder?, item: MineBean.DataBean.ListBean.OrderTypeBean?) {
                var icon = helper!!.getView<ImageView>(R.id.minetop_item_icon)
                Glide.with(activity!!).load(item!!.avatarurl).apply(RequestOptions().error(R.mipmap.labeego_icon)).into(icon)
                helper.setText(R.id.minetop_item_text, item.title)
            }
        }
        adapter.setOnItemClickListener { adapter, view, position ->
            if (bean.data.list.order_type[position].url == "") {
                if (bean.data.list.order_type[position].title == "啦蜂") {
                    var intent = Intent(activity, OrderActivity::class.java)
                    intent.putExtra("ordertype", "全部")
                    startActivity(intent)
                } else {
                    var intent = Intent(activity, WebsActivity::class.java)
                    intent.putExtra("web", bean.data.list.order_type[position].url)
                    startActivity(intent)
                }
            } else {
                var intent = Intent(activity, WebsActivity::class.java)
                intent.putExtra("web", bean.data.list.order_type[position].url)
                startActivity(intent)
            }
        }
        minebuttonrecycler.adapter = adapter
        adapter.setNewData(bean.data.list.order_type)
        return view
    }

    var resume = 0
    override fun onResume() {
        Log.d("minefragment", "onresume")
        super.onResume()
//        if (resume == 0) {
//            resume++
        adapterhoder = false
//        }
        mPersenter!!.getdata()
    }

//    override fun onPause() {
//        super.onPause()
//        resume = 0
//    }
}