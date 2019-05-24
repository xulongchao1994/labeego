package com.hechuang.labeego.ui.fragment

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseFragment
import com.hechuang.labeego.bean.HoenyBean
import com.hechuang.labeego.bean.HoneyListbean
import com.hechuang.labeego.persenter.fragment.LittleBeePersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RecycleViewDivider
import com.hechuang.labeego.ui.activity.BeeForceInfoActivity
import com.hechuang.labeego.ui.activity.WebsActivity
import com.hechuang.labeego.view.fragment.ILittleBeeView
import kotlinx.android.synthetic.main.fragment_littlebee.*

/**
 * 小蜜蜂蜂力
 */
class BeeFragment : BaseFragment<LittleBeePersenter>(), ILittleBeeView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    override fun onLoadMoreRequested() {
        page += 1
        mPersenter!!.getbeedata(bee_type!!, page.toString(), false)
    }

    var yearlsit = arrayListOf<String>()
    var isHeaderViewAsFlow = false
    override fun gethoenydatasuccess(beandata: HoenyBean, isr: Boolean) {
        Log.d("beefragment", page.toString() + "  " + isr + "\n" + beandata.toString())
        if (beandata.data.list.record != null && beandata.data.list.record.size > 0) {
            adapter!!.setEnableLoadMore(true)
            //报存单页的列表数据，用于给adapter添加数据
            var listdata_one = arrayListOf<HoneyListbean>()
            for (i in 0 until beandata.data.list.record.size) {
                if (page == 1) {
                    yearlsit.clear()
                }
                yearlsit.add(beandata.data.list.record[i].y)
                if (yearlsit.size > 1) {
                    if (yearlsit[yearlsit.size - 1] ==
                            yearlsit[yearlsit.size - 2]) {
                    } else {
                        myhoneylistbean = HoneyListbean(true, beandata.data.list.record[i].y)
                        listdata_one.add(myhoneylistbean!!)
                    }
                } else {
                    myhoneylistbean = HoneyListbean(true, beandata.data.list.record[i].y)
                    listdata_one.add(myhoneylistbean!!)
                }
                for (j in 0 until beandata.data.list.record[i].list.size) {
                    listdata_one.add(HoneyListbean(beandata.data.list.record[i].list[j]))
                }
            }
            if (page == 1) {
                if (!isHeaderViewAsFlow) {
                    adapter!!.addHeaderView(topview(beandata, true))
                    isHeaderViewAsFlow = true
                } else {
                    honeyprice!!.text = beandata.data.list.header.count
                }
                listdata.clear()
                adapter!!.setNewData(listdata_one)
                listdata.addAll(listdata_one)
                if (isr) {
                    littlebee_refresh.isRefreshing = false
                } else {
                    if (listdata_one.size < 9) {
                        adapter!!.loadMoreEnd()
                    } else {
                        adapter!!.loadMoreComplete()
                    }
                }
            } else {
                if (isr) {
                    listdata.clear()
                    adapter!!.setNewData(listdata_one)
                    littlebee_refresh.isRefreshing = false
                } else {
                    adapter!!.addData(listdata_one)
                    listdata.addAll(listdata_one)
                    if (listdata_one.size < 10) {
                        adapter!!.loadMoreEnd()
                    } else {
                        adapter!!.loadMoreComplete()
                    }
                }
            }
        } else {
            if (beandata.data.list.header != null) {
                if (!isHeaderViewAsFlow) {
                    adapter!!.addHeaderView(topview(beandata, false))
                    isHeaderViewAsFlow = true
                    adapter!!.loadMoreEnd()
                } else {
                    honeyprice!!.text = beandata.data.list.header.count
                }
            }
            if (isr) {
                littlebee_refresh.isRefreshing = false
            } else {
                adapter!!.loadMoreEnd()
            }
        }
    }

    override fun gethoenydataerror(msg: String) {
        MyToast.showMsg(msg)
        littlebee_refresh.isRefreshing = false
        adapter!!.loadMoreEnd()
    }

    override fun onRefresh() {
        page = 1
        mPersenter!!.getbeedata(bee_type!!, page.toString(), true)
    }

    override fun initPersenter() {
        mPersenter = LittleBeePersenter(this, activity!!)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_littlebee
    }

    var myhoneylistbean: HoneyListbean? = null
    //页码
    var page = 1
    //列表适配器
    var adapter: BaseSectionQuickAdapter<HoneyListbean, BaseViewHolder>? = null
    //保存所有的列表数据，用于点击事件
    var listdata = arrayListOf<HoneyListbean>()
    //用于区分蜂力类型
    var bee_type: String? = null

    override fun initViews(view: View) {
        bee_type = arguments!!.getString("bee_type")
        littlebee_refresh.setOnRefreshListener(this)
        var layoutmanager = LinearLayoutManager(activity)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        littlebee_recycler.layoutManager = layoutmanager
        adapter = getadapter()
        littlebee_recycler.adapter = adapter
        littlebee_recycler.addItemDecoration(RecycleViewDivider(activity, LinearLayoutManager.VERTICAL))
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (listdata[position].t != null) {
                var intent = Intent(activity, BeeForceInfoActivity::class.java)
                intent.putExtra("beeid", listdata[position].t.id)
                intent.putExtra("beetype", "1")
                startActivity(intent)
            }
        }
        adapter!!.disableLoadMoreIfNotFullPage(littlebee_recycler)
        adapter!!.setOnLoadMoreListener(this, littlebee_recycler)
//        page = 1
//        mPersenter!!.getbeedata(bee_type!!, page.toString(), false)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    var honeyprice: TextView? = null
    fun topview(beandata: HoenyBean, isshow: Boolean): View {
        var view = LayoutInflater.from(activity).inflate(R.layout.view_honeytop, null)
        var honeyprice_text = view.findViewById<TextView>(R.id.hoeny_top_price_text)
        honeyprice = view.findViewById<TextView>(R.id.hoeny_top_honeynumber)
        var hoenynoting = view.findViewById<TextView>(R.id.hoeny_top_noting)
        Log.d("beefragment", page.toString() + "  " + bee_type + "   " + isshow.toString())
        if (isshow) {
            hoenynoting.visibility = View.GONE
        } else {
            hoenynoting.visibility = View.VISIBLE
        }
        honeyprice!!.text = beandata.data.list.header.count
        honeyprice_text.text = beandata.data.list.header.title
        var honeyrecycler = view.findViewById<RecyclerView>(R.id.hoeny_top_recycler)
        var layoutmanager = LinearLayoutManager(activity)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        honeyrecycler.layoutManager = layoutmanager
        var adapter = object : BaseQuickAdapter<HoenyBean.DataBean.ListBeanX.CentreBean, BaseViewHolder>(R.layout.adapter_honeytop) {
            override fun convert(helper: BaseViewHolder?, item: HoenyBean.DataBean.ListBeanX.CentreBean?) {
                helper!!.setText(R.id.adapter_honey_recycler_item, item!!.title)
            }
        }
        honeyrecycler.adapter = adapter
        adapter.setEnableLoadMore(false)
        adapter.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(activity, WebsActivity::class.java)
            intent.putExtra("web", beandata.data.list.centre[position].url)
            startActivity(intent)
        }
        if (beandata.data.list.centre != null && beandata.data.list.centre.size > 0) {
            adapter.setNewData(beandata.data.list.centre)
        }
        return view
    }

    fun getadapter(): BaseSectionQuickAdapter<HoneyListbean, BaseViewHolder>? {
        var Myadapter = object : BaseSectionQuickAdapter<HoneyListbean, BaseViewHolder>(R.layout.adapter_honey, R.layout.adapter_honeytitle, listdata) {
            override fun convertHead(helper: BaseViewHolder?, item: HoneyListbean?) {
                helper!!.setText(R.id.honey_title_text, item!!.header)
            }

            override fun convert(helper: BaseViewHolder?, item: HoneyListbean?) {
                helper!!.setText(R.id.honey_time, item!!.t.addTime)
                        .setText(R.id.honey_moeny, item.t.amount)
                        .setText(R.id.honey_memo, item.t.memo)
                        .setText(R.id.honey_typename, item.t.typeName)
            }
        }
        return Myadapter
    }

    override fun onResume() {
        super.onResume()
//        adapter!!.disableLoadMoreIfNotFullPage(littlebee_recycler)
        Log.d("beefragment","onresume")
        isHeaderViewAsFlow = false
        page = 1
        mPersenter!!.getbeedata(bee_type!!, page.toString(), false)
    }
}