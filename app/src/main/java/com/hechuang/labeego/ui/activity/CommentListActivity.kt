package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.CommentListBean
import com.hechuang.labeego.persenter.activity.CommentListPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.ICommentListView
import kotlinx.android.synthetic.main.activity_commentlist.*

/**
 * 评论列表
 */
class CommentListActivity : BaseActivity<CommentListPersenter>(), ICommentListView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    /**
     * 获取数据成功
     */
    override fun getdata_sccuss(lsitdata: List<CommentListBean.DataBean.ListBean>, isrefresh: Boolean) {
        if (page == 1) {
            datalist.clear()
            adapter!!.setNewData(lsitdata)
            if (isrefresh) {
                commentlist_swipe.isRefreshing = false
                if (lsitdata.size < 9) {
                    adapter!!.loadMoreEnd()
                }
            } else {
                if (lsitdata.size < 9) {
                    adapter!!.loadMoreEnd()
                } else {
                    adapter!!.loadMoreComplete()
                }
            }
        } else {
            adapter!!.addData(lsitdata)
            if (lsitdata.size < 9) {
                adapter!!.loadMoreEnd()
            } else {
                adapter!!.loadMoreComplete()
            }
        }
        datalist.addAll(lsitdata)

    }

    /**
     * 获取数据失败
     */
    override fun getdata_error(msg: String) {
        if (page == 1) {
            datalist.clear()
        } else {

        }
        MyToast.showMsg(msg)
    }

    override fun onRefresh() {
        page = 1
        mPersenter!!.getlistdata(proid!!, page.toString(), true)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.commentlist_back -> {
                finish()
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_commentlist
    }

    var adapter: BaseQuickAdapter<CommentListBean.DataBean.ListBean, BaseViewHolder>? = null
    var datalist = arrayListOf<CommentListBean.DataBean.ListBean>()
    var page: Int? = null
    var proid: String? = null
    var proimg: String? = null
    override fun initView() {
        commentlist_back.setOnClickListener(this)
        proid = intent.getStringExtra("proid")
        proimg = intent.getStringExtra("proimg")
//        Log.d("commentlist", proimg)
        page = 1
        val linearlayoutmanager = LinearLayoutManager(this)
        linearlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        commentlist_recyeler.layoutManager = linearlayoutmanager
        adapter = object : BaseQuickAdapter<CommentListBean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_commentlist_item) {
            override fun convert(helper: BaseViewHolder?, item: CommentListBean.DataBean.ListBean?) {
                var urer_icon = helper!!.getView<ImageView>(R.id.commentlist_item_usericon)
                Glide.with(this@CommentListActivity).load(item!!.avatarurl)
                        .apply(RequestOptions().error(R.mipmap.labeego_icon).centerCrop())
                        .into(urer_icon)
                helper.setText(R.id.commentlist_item_title, item.truename)
                        .setText(R.id.commentlist_item_context, item.comment)
                        .setText(R.id.commentlist_item_time, item.time)
                        .setText(R.id.commentlist_item_admin_context, item.admin_comment)
                        .setText(R.id.commentlist_item_stylename, "规格：${item.styleName}")

                if (item.admin_comment == "") helper.setGone(R.id.commentlist_item_admin_context, false)
            }
        }
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //跳转到评价详情
            var intent = Intent(this@CommentListActivity, CommentInfoActivity::class.java)
            intent.putExtra("id", datalist[position].id)
            startActivity(intent)
        }
        adapter!!.setOnLoadMoreListener({
            page = page!! + 1
            mPersenter!!.getlistdata(proid!!, page!!.toString(), false)
        }, commentlist_recyeler)
        commentlist_swipe.setOnRefreshListener(this)
        commentlist_recyeler.adapter = adapter
        Glide.with(this@CommentListActivity).load(proimg).into(commentlist_title_img)
        adapter!!.disableLoadMoreIfNotFullPage()
        mPersenter!!.getlistdata(proid!!, page!!.toString(), false)
    }

    override fun initPersenter() {
        mPersenter = CommentListPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }
}