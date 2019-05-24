package com.hechuang.labeego.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.CommentAddBean
import com.hechuang.labeego.bean.CommentinfoBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.activity.CommentInfoPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.ICommentInfoView
import kotlinx.android.synthetic.main.activity_commentinfo.*

@RequiresApi(Build.VERSION_CODES.M)
/**
 * 评论详情
 */
class CommentInfoActivity : BaseActivity<CommentInfoPersenter>(), ICommentInfoView, View.OnClickListener, View.OnScrollChangeListener {
    override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        commentinfo_message_layout.visibility = View.VISIBLE
        id = infodata!!.data.list.comment_header.id
    }

    override fun delete_success(msg: String) {

    }

    override fun delete_error(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun postzhuiping_success(databean: CommentAddBean) {
        commentinfo_comment.setText("")
        comment = ""
        commentcishu = true
        mPersenter!!.getinfodata(id!!)
    }

    override fun postzhuiping_error(msg: String) {
        MyToast.showMsg(msg)
        commentcishu = true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.commentinfo_zhuiping -> {
                postcomment()
            }
        }
    }

    fun postcomment() {
        if (commentcishu!!) {
            commentcishu = false
            if (comment.equals("")) {
                MyToast.showMsg("楼主什么都看不到哦")
                commentcishu = true
                return
            }
            mPersenter!!.post_zuuiping(infodata!!.data.list.good.proid, infodata!!.data.list.comment_header.id, infodata!!.data.list.comment_header.userid, comment!!)
            closeKeyboard()
//        commentinfo_comment.isFocusable = false
//        commentinfo_comment.isFocusableInTouchMode = false
            commentinfo_comment.requestFocus()
        } else {
            MyToast.showMsg("您的手速也忒快了")
        }
    }

    var infodata: CommentinfoBean? = null
    override fun getdatasuccess(datainfo: CommentinfoBean) {
        this.infodata = datainfo
        adapter = object : BaseQuickAdapter<CommentinfoBean.DataBean.ListBean.CommentListBean, BaseViewHolder>(R.layout.adapter_commentinfo_tiem) {
            override fun convert(helper: BaseViewHolder?, item: CommentinfoBean.DataBean.ListBean.CommentListBean?) {
                val usericon = helper!!.getView<ImageView>(R.id.commentinfo_item_usericon)
                Glide.with(this@CommentInfoActivity).load(item!!.avatarurl)
                        .apply(RequestOptions().centerCrop().error(R.mipmap.labeego_icon))
                       .into(usericon)
                var islouzhu = false
                var ishuifu = false
                when (item.stype) {
                    "1" -> {//管理员回复（暂无此功能
                    }
                    "2" -> {//别人回复楼主
                        islouzhu = false
                        ishuifu = false
                    }
                    "3" -> {//楼主
                        islouzhu = true
                        ishuifu = false
                    }
                    "4" -> {//别人回复别人 或者楼主回评论
                        islouzhu = false
                        ishuifu = true
                    }
                }
                if (item.truename == UserData.USERNAME) {
                    if (item.stype == "3") {
                        helper.setGone(R.id.commentinfo_item_delete, true)
                    } else {
                        helper.setGone(R.id.commentinfo_item_delete, false)
                    }
                } else {
                    helper.setGone(R.id.commentinfo_item_delete, true)
                }
                helper.addOnClickListener(R.id.commentinfo_item_delete)
                helper.setText(R.id.commentinfo_item_username, item.truename)
                        .setText(R.id.commentinfo_item_conetxt, item.comment)
                        .setText(R.id.commentinfo_item_time, item.time)
                        .setGone(R.id.commentinfo_item_louzhu, islouzhu)
                        .setText(R.id.commentinfo_item_huifu_who, item.p_name)
                        .setGone(R.id.commentinfo_item_huifu_layout, ishuifu)
            }
        }
        commentinfo_recyelr.adapter = adapter
        adapter!!.setEnableLoadMore(false)
        adapter!!.setNewData(infodata!!.data.list.comment_list)
        adapter!!.addHeaderView(addtopview(infodata!!))
        commentinfo_zhuiping.isEnabled = true
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //输入框获取焦点 记录id
            //可以回复
            //TODO
            commentinfo_message_layout.visibility = View.GONE
            id = infodata!!.data.list.comment_list[position].id

        }
        commentinfo_recyelr.setOnScrollChangeListener(this)
        adapter!!.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.commentinfo_item_delete -> {//删除评论
                    mPersenter!!.delete_pinjia(datainfo.data.list.comment_list[position].id)
                }
            }
        }
        if (datainfo.data.list.comment_list != null && datainfo.data.list.comment_list.size > 0) {
            pingnumber!!.text = "全部评论(${datainfo.data.list.comment_list.size})"
            kongbai!!.visibility = View.GONE
        } else {
            pingnumber!!.text = "全部评论(0)"
            kongbai!!.visibility = View.VISIBLE
        }
    }

    override fun getdataerror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun initlayout(): Int {
        return R.layout.activity_commentinfo
    }

    var commentcishu: Boolean? = null
    var adapter: BaseQuickAdapter<CommentinfoBean.DataBean.ListBean.CommentListBean, BaseViewHolder>? = null
    var id: String? = null
    var comment: String? = null
    override fun initView() {
        commentcishu = true
        id = intent.getStringExtra("id")

        val layoutmeaager = LinearLayoutManager(this)
        layoutmeaager.orientation = LinearLayoutManager.VERTICAL
        comment = ""
        commentinfo_recyelr.layoutManager = layoutmeaager
        commentinfo_comment.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                if (commentinfo_comment.text.toString() != "") {
                    comment = commentinfo_comment.text.toString()
                    postcomment()
                    return@OnEditorActionListener true
                }
            }
            false
        })
        commentinfo_comment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                comment = commentinfo_comment.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        commentinfo_zhuiping.setOnClickListener(this)
        commentinfo_zhuiping.isEnabled = false
        commentinfo_back.setOnClickListener { finish() }
        commentinfo_back_text.setOnClickListener { finish() }
        commentinfo_message_layout.visibility = View.VISIBLE
        commentinfo_message_layout.setOnClickListener {
            commentinfo_message_layout.visibility = View.GONE
        }
        commentinfo_message_img.setOnClickListener {
            commentinfo_message_layout.visibility = View.GONE
        }
        commentinfo_message_text.setOnClickListener {
            commentinfo_message_layout.visibility = View.GONE
        }
        mPersenter!!.getinfodata(id!!)
    }

    override fun initPersenter() {
        mPersenter = CommentInfoPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    var pingjialayout: LinearLayout? = null
    var kongbai: TextView? = null
    var pingnumber: TextView? = null
    fun addtopview(datainfo: CommentinfoBean): View {
        var view = LayoutInflater.from(this).inflate(R.layout.view_commnetinfo_title, null)
        //评论-----------------
        var usertime = view.findViewById<TextView>(R.id.commentinfo_title_time)
        var usericon = view.findViewById<ImageView>(R.id.commentinfo_title_usericon)
        var username = view.findViewById<TextView>(R.id.commentinfo_title_username)
        var context = view.findViewById<TextView>(R.id.commentinfo_title_context)
        var admin_comment = view.findViewById<TextView>(R.id.commentinfo_title_admin_context)
        //商品--------------------
        val goodsguige = view.findViewById<TextView>(R.id.commentinfo_title_guige)
        var goodslayout = view.findViewById<LinearLayout>(R.id.commentinfo_title_shopping_layout)
        var goodsicon = view.findViewById<ImageView>(R.id.commentinfo_title_goodsicon)
        var goodsname = view.findViewById<TextView>(R.id.commentinfo_title_goodsname)
        var goodsprice = view.findViewById<TextView>(R.id.commentinfo_title_price)
        var integral_layout = view.findViewById<LinearLayout>(R.id.commentinfo_title_integral_layout)
        var goodszeng = view.findViewById<TextView>(R.id.commentinfo_title_integral)
        pingnumber = view.findViewById<TextView>(R.id.commentinfo_title_pinglunnumber)
        kongbai = view.findViewById(R.id.commentinfo_title_kong)
        pingjialayout = view.findViewById<LinearLayout>(R.id.commentinfo_title_pingjia_layout)
        goodslayout.setOnClickListener {
            //跳转到商品详情
            var intent = Intent(this@CommentInfoActivity, GoodsInfoActivity::class.java)
            intent.putExtra("proimg", datainfo.data.list.good.proImg)
            intent.putExtra("pid", datainfo.data.list.good.proid)
            startActivity(intent)
        }
        Glide.with(this@CommentInfoActivity).load(datainfo.data.list.comment_header.avatarurl)
                .apply(RequestOptions().centerCrop().error(R.mipmap.labeego_icon))
                .into(usericon)
        username.text = datainfo.data.list.comment_header.truename
        usertime.text = datainfo.data.list.comment_header.time
        context.text = datainfo.data.list.comment_header.comment

        goodsguige.text = "套餐类型：${datainfo.data.list.good.stylename}"
        Glide.with(this@CommentInfoActivity).load(datainfo.data.list.good.proImg)
                .apply(RequestOptions().error(R.mipmap.ic_launcher))
       .into(goodsicon)
        goodsname.text = datainfo.data.list.good.proName
        goodsprice.text = datainfo.data.list.good.price
        if (datainfo.data.list.good.integral == null || datainfo.data.list.good.integral.equals("null") || datainfo.data.list.good.integral.equals("")) {
            integral_layout.visibility = View.GONE
        } else {
            integral_layout.visibility = View.VISIBLE
            goodszeng.text = datainfo.data.list.good.integral
        }
        return view
    }

    private fun closeKeyboard() {
        val view = getWindow().peekDecorView()
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }
}