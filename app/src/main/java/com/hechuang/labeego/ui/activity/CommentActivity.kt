package com.hechuang.labeego.ui.activity

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.CommentAddBean
import com.hechuang.labeego.bean.Commentbean
import com.hechuang.labeego.persenter.activity.CommentPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.ICommentView
import kotlinx.android.synthetic.main.activity_comment.*
import me.zhanghai.android.materialratingbar.MaterialRatingBar
import org.json.JSONArray
import org.json.JSONObject

/**
 * 评论页面
 */
class CommentActivity : BaseActivity<CommentPersenter>(), ICommentView, View.OnClickListener {
    //评价失败
    override fun postcomment_error(str: String) {
        MyToast.showMsg(str)
        commentcishu = true
    }

    //评价成功
    override fun postcomment_success(databean: CommentAddBean) {
        MyToast.showMsg(databean.data.message)
        commentcishu = false
        for (i in 0 until listdata.size) {
            listdata[i].isYincang = true
        }
        adapter!!.notifyDataSetChanged()
        finish()
    }

    var listdata = arrayListOf<Commentbean.DataBean.ListBean>()
    override fun getdatasuccess(databean: Commentbean) {
        if (listdata.size > 0) {
            listdata.clear()
        }
        listdata.addAll(databean.data.list)
        adapter!!.setNewData(listdata)
    }

    override fun getdataerror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.comment_back -> {
                finish()
            }
            R.id.comment_back_text -> {
                finish()
            }
            R.id.comment_fabu -> {
                if (commentcishu!!) {
                    commentcishu = false
                    var proidjson = JSONObject()
                    var proidjsonarray = JSONArray()
                    var gradejson = JSONObject()
                    var gradejsonarray = JSONArray()
                    var commentjson = JSONObject()
                    var commentjsonarray = JSONArray()
                    var anonymousjson = JSONObject()
                    var anonymousjsonarray = JSONArray()
                    for (i in 0 until listdata.size) {
                        proidjsonarray.put(listdata[i].proid)
                        gradejsonarray.put(listdata[i].comment)
                        commentjsonarray.put(listdata[i].comment_context)
                        if (listdata[i].isIsniming) {
                            anonymousjsonarray.put(1)
                        } else {
                            anonymousjsonarray.put(0)
                        }
                    }
                    proidjson.put("json", proidjsonarray)
                    gradejson.put("json", gradejsonarray)
                    commentjson.put("json", commentjsonarray)
                    anonymousjson.put("json", anonymousjsonarray)
                    mPersenter!!.postcomment(proidjson.toString(), gradejson.toString(), commentjson.toString(), anonymousjson.toString(), id!!)
                } else {
                    MyToast.showMsg("操作频繁，请稍后重试")
                }
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_comment
    }

    var commentcishu: Boolean? = null
    var adapter: BaseQuickAdapter<Commentbean.DataBean.ListBean, BaseViewHolder>? = null
    var id: String? = null
    override fun initView() {
        comment_back.setOnClickListener(this)
        comment_back_text.setOnClickListener(this)
        comment_fabu.setOnClickListener(this)
        commentcishu = true
        id = intent.getStringExtra("id")
        var lenenrt = LinearLayoutManager(this)
        lenenrt.orientation = LinearLayoutManager.VERTICAL
        comment_recycler.layoutManager = lenenrt
        adapter = object : BaseQuickAdapter<Commentbean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_comment) {
            override fun convert(helper: BaseViewHolder?, item: Commentbean.DataBean.ListBean?) {
                var image = helper!!.getView<ImageView>(R.id.adapter_comment_icon)
                Glide.with(this@CommentActivity).load(item!!.proimg).into(image)
                var comment = helper.getView<MaterialRatingBar>(R.id.adapter_comment_pingfen)
                comment.rating = item!!.comment.toFloat()
                comment.setOnRatingChangeListener { ratingBar, rating ->
                    listdata[helper.layoutPosition].comment = rating.toInt().toString()
                    var pingfentext = ""
                    when (rating.toInt()) {
                        1 -> {
                            pingfentext = "非常差"
                        }
                        2 -> {
                            pingfentext = "差"
                        }
                        3 -> {
                            pingfentext = "一般"
                        }
                        4 -> {
                            pingfentext = "好"
                        }
                        5 -> {
                            pingfentext = "非常好"
                        }
                    }

                    helper.setText(R.id.adapter_comment_pingfen_text, pingfentext)
                }
                helper.setText(R.id.adapter_comment_pingfen_text, item.comment_str)
                var connet = helper.getView<EditText>(R.id.adapter_comment_contenxt)
                connet.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        listdata[helper.layoutPosition].comment_context = connet.text.toString()
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
                if (item.isYincang) {
                    connet.requestFocus()
                    closeKeyboard()
                }
                val ismoren = helper!!.getView<ImageView>(R.id.adapter_comment_niming)
                if (item.isIsniming) {
                    Glide.with(this@CommentActivity).load(R.drawable.shoppingcar_choice_true).into(ismoren)
                    helper.setText(R.id.adapter_comment_niming_text, "你写的评论会以匿名的形式展现")
                } else {
                    Glide.with(this@CommentActivity).load(R.drawable.shoppingcar_choice_false).into(ismoren)
                    helper.setText(R.id.adapter_comment_niming_text, "你写的评论能帮助到其他小伙伴呦")
                }
                ismoren.setOnClickListener {
                }
                helper.addOnClickListener(R.id.adapter_comment_niming_layout)
            }
        }
        adapter!!.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when (view!!.id) {
                R.id.adapter_comment_niming_layout -> {
                    listdata[position].isIsniming = !listdata[position].isIsniming
                    adapter.notifyDataSetChanged()
                }
            }
        }
        comment_recycler.adapter = adapter
        adapter!!.setEnableLoadMore(false)
        mPersenter!!.getgoodsdata(id!!)
    }

    override fun initPersenter() {
        mPersenter = CommentPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    fun closeKeyboard() {
        val view = getWindow().peekDecorView()
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}