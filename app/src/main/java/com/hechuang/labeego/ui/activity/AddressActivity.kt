package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.Address_bean
import com.hechuang.labeego.bean.Address_morenBean
import com.hechuang.labeego.persenter.activity.AddressPersenter
import com.hechuang.labeego.tools.Util.AppManager
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IAddressView
import kotlinx.android.synthetic.main.acitvity_address.*

class AddressActivity : BaseActivity<AddressPersenter>(), IAddressView, View.OnClickListener {
    override fun deleteaddress_success(bean: Address_morenBean, position: Int) {
        adapter_addresslist!!.remove(position)
    }

    override fun deleteaddress_error(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun setaddressmoren_success(bean: Address_morenBean, position: Int) {
        for (i in 0 until addresslist.size) {
            if (i == position) {
                addresslist[i].isDefault = "1"
            } else {
                addresslist[i].isDefault = "0"
            }
        }
        adapter_addresslist!!.notifyDataSetChanged()
    }

    override fun setaddressmoren_error(msg: String) {
        MyToast.showMsg(msg)
    }

    var isbianji: Boolean? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.address_back -> finish()
            R.id.address_add -> {
                var intent = Intent(this@AddressActivity, NewAdderssActivity::class.java)
                intent.putExtra("addressid", "")
                startActivity(intent)
            }
        }
    }

    var adapter_addresslist: BaseQuickAdapter<Address_bean.DataBean.ListBean, BaseViewHolder>? = null
    var addresslist = arrayListOf<Address_bean.DataBean.ListBean>()
    var page = 1
    override fun getaddress_seccess(address_bean: Address_bean) {
//        Log.d("address", address_bean.data.toString())
        if (page == 1) {
            addresslist.clear()
            addresslist.addAll(address_bean.data.list)
            adapter_addresslist!!.setNewData(addresslist)
            address_recycler.adapter = adapter_addresslist
            adapter_addresslist!!.disableLoadMoreIfNotFullPage()
        } else {
            addresslist.addAll(address_bean.data.list)
            adapter_addresslist!!.notifyDataSetChanged()
        }
        adapter_addresslist!!.loadMoreComplete()


    }

    override fun initlayout(): Int {
        return R.layout.acitvity_address
    }

    override fun initView() {
        AppManager.getAppManager().addActivity(this)
        page = 1
        isbianji = false
        var linmanager = LinearLayoutManager(this)
        linmanager.orientation = OrientationHelper.VERTICAL
        address_recycler.layoutManager = linmanager
        mPersenter!!.getaddress(page.toString(), isbianji!!)
        address_add.setOnClickListener(this)
        address_back.setOnClickListener(this)

        adapter_addresslist = object : BaseQuickAdapter<Address_bean.DataBean.ListBean, BaseViewHolder>(R.layout.adapter_address_item) {
            override fun convert(helper: BaseViewHolder?, item: Address_bean.DataBean.ListBean?) {
//                helper!!.setGone(R.id.address_item_moren_layout, item!!.isbianji)
                val title_icon = helper!!.getView<ImageView>(R.id.address_item_icon)
                Glide.with(this@AddressActivity).load(R.drawable.avatar_default)
                        .apply(RequestOptions().centerCrop())
                        .into(title_icon)
                helper.setText(R.id.address_shouhuoren, item!!.receiveName)
                        .setText(R.id.address_phonenumber, "(${item.mobile})")
                        .setText(R.id.address_address, item.diqu)
                helper.addOnClickListener(R.id.address_item_moren_text)
                        .addOnClickListener(R.id.address_item_delete_text)
                        .addOnClickListener(R.id.address_item_bianji)
                        .addOnClickListener(R.id.address_item_moren_img)
                var img_moren = helper.getView<ImageView>(R.id.address_item_moren_img)
                if (item.isDefault == "1") {
                    Glide.with(this@AddressActivity).load(R.drawable.address_true).into(img_moren)
                    helper.setText(R.id.address_item_moren_text, "默认地址")
                } else {
                    Glide.with(this@AddressActivity).load(R.drawable.address_false).into(img_moren)
                    helper.setText(R.id.address_item_moren_text, "设为默认")
                }
            }
        }
        adapter_addresslist!!.setOnItemChildClickListener { adapter, view, position ->
            //            Log.d("address", addresslist[position].isDefault)
            when (view.id) {
                R.id.address_item_moren_text -> {
                    if (addresslist[position].isDefault == "1") {
                    } else {
                        AlertDialog.Builder(this@AddressActivity).setMessage("是否设为默认地址")
                                .setPositiveButton("确定") { dialog, which ->
                                    mPersenter!!.setmoren(addresslist[position].id, position)
                                }
                                .setNegativeButton("点错了") { dialog, which -> }
                                .show()
                    }
                }
                R.id.address_item_moren_img -> {
                    if (addresslist[position].isDefault == "1") {
                    } else {
                        AlertDialog.Builder(this@AddressActivity).setMessage("是否设为默认地址")
                                .setPositiveButton("确定") { dialog, which ->
                                    mPersenter!!.setmoren(addresslist[position].id, position)
                                }
                                .setNegativeButton("点错了") { dialog, which -> }
                                .show()
                    }
                }
                R.id.address_item_delete_text -> {
                    AlertDialog.Builder(this@AddressActivity).setMessage("是否删除地址")
                            .setPositiveButton("确定") { dialog, which ->
                                mPersenter!!.deleteaddress(addresslist[position].id, position)
                            }
                            .setNegativeButton("点错了") { dialog, which -> }
                            .show()
                }

                R.id.address_item_bianji -> {
                    var intent = Intent(this@AddressActivity, NewAdderssActivity::class.java)
                    intent.putExtra("addressid", addresslist[position].id)
                    startActivity(intent)
                }
            }
        }
//        adapter_addresslist!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
//            override fun onLoadMoreRequested() {
//                //加载更多
//                page++
//                mPersenter!!.getaddress(page.toString(), isbianji!!)
//                adapter_addresslist!!.loadMoreComplete()
//            }

//        }, address_recycler)
        adapter_addresslist!!.setOnItemClickListener { adapter, view, position ->
            if (addresslist[position].isbianji) {

            }

        }
    }


    override fun initPersenter() {
        mPersenter = AddressPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    override fun onResume() {
        super.onResume()
        page = 1
        mPersenter!!.getaddress(page.toString(), isbianji!!)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}