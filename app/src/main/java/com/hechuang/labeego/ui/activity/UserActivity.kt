package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.UserInfoBean
import com.hechuang.labeego.bean.UserinfoListBean
import com.hechuang.labeego.persenter.activity.UserPeersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.RecycleViewDivider
import com.hechuang.labeego.view.activity.IUserView
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : BaseActivity<UserPeersenter>(), IUserView {
    override fun getuserinfodatasuccess(userinfoddata: UserInfoBean) {
        datalist.clear()
        datalist.add(UserinfoListBean(userinfoddata.data.list.truename.title, userinfoddata.data.list.truename.truename))
        datalist.add(UserinfoListBean(userinfoddata.data.list.mobile.title, userinfoddata.data.list.mobile.mobile))
        datalist.add(UserinfoListBean(userinfoddata.data.list.idcardno.title, userinfoddata.data.list.idcardno.idcardno))
        adapter!!.setNewData(datalist)
    }

    override fun getuserdatainfoerror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun initlayout(): Int {
        return R.layout.activity_user
    }

    var adapter: BaseQuickAdapter<UserinfoListBean, BaseViewHolder>? = null
    var datalist = ArrayList<UserinfoListBean>()
    override fun initView() {
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        user_recycler.layoutManager = layoutmanager
        user_back.setOnClickListener { finish() }
        user_recycler.addItemDecoration(RecycleViewDivider(this, LinearLayoutManager.VERTICAL))
        adapter = object : BaseQuickAdapter<UserinfoListBean, BaseViewHolder>(R.layout.adapter_userinfo) {
            override fun convert(helper: BaseViewHolder?, item: UserinfoListBean?) {
                helper!!.setText(R.id.adapter_userinfo_name, item!!.username)
                        .setText(R.id.adapter_userinfo_truename, item.turename)
                if (item.username != "姓名") {
                    helper.setGone(R.id.adapter_userinfo_right, false)
                } else {
                    helper.setGone(R.id.adapter_userinfo_right, true)
                }
            }
        }
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (datalist[position].username == "姓名") {
                var intent = Intent(this@UserActivity, SetNameActivity::class.java)
                intent.putExtra("name", datalist[position].turename)
                startActivity(intent)
            }
        }
        adapter!!.setEnableLoadMore(false)
        user_recycler.adapter = adapter
//        mPersenter!!.getuserinfodata()
    }

    override fun initPersenter() {
        mPersenter = UserPeersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    override fun onResume() {
        super.onResume()
        mPersenter!!.getuserinfodata()
    }
}