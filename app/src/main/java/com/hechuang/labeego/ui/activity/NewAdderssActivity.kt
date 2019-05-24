package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.AddressInfoBean
import com.hechuang.labeego.bean.Address_morenBean
import com.hechuang.labeego.bean.NewAddress_seccess_bean
import com.hechuang.labeego.persenter.activity.NewAddressPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.INewAddressView
import kotlinx.android.synthetic.main.activity_newaddress.*

class NewAdderssActivity : BaseActivity<NewAddressPersenter>(), INewAddressView, View.OnClickListener {
    override fun bianji_success(data: Address_morenBean) {
        dismissloading()
        MyToast.showMsg(data.data.msg)
        finish()
    }

    override fun bianji_error(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun getaddressinfo_success(data: AddressInfoBean) {
        newaddress_shouhuoren_et.setText(data.data.list.receiveName)
        shouhuoren_str = data.data.list.receiveName
        newaddress_phonenumber_et.setText(data.data.list.mobile)
        phonenumber_str = data.data.list.mobile
        province = data.data.list.province
        city = data.data.list.city
        district = data.data.list.county
        address_str = data.data.list.address
        newaddress_duqu.text = "$province $city $district"
        newaddress_address.setText(address_str)
        isdef = data.data.list.isDefault.toInt()
        when (isdef) {
            1 -> {//默认
                newaddress_ismoren.isChecked = true
            }
            0 -> {
                newaddress_ismoren.isChecked = false
            }
        }
    }

    override fun getaddressinfo_error(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun postaddress_seccess(postdata: NewAddress_seccess_bean) {
        if (postdata.data.status == 1) {
            dismissloading()
            MyToast.showMsg("添加地址成功")
            finish()
        } else {
            dismissloading()
            MyToast.showMsg(postdata.data.msg)
        }
    }

    private val UNION_SELECT_CITY = 1002
    private var province = ""
    private var city = ""
    private var district = ""
    private var shouhuoren_str = ""
    private var phonenumber_str = ""
    var address_str = ""
    var isdef = 0
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.newaddress_back -> finish()
            R.id.newaddress_tianjia -> {//提交
                if (shouhuoren_str == "") {
                    MyToast.showMsg("收货人不能为空")
                    return
                }
                if (phonenumber_str == "") {
                    MyToast.showMsg("手机号不能为空")
                    return
                }
                if (phonenumber_str.length != 11) {
                    MyToast.showMsg("手机号码必须是11位")
                    return
                }
                if (address_str == "") {
                    MyToast.showMsg("详细地址不能为空")
                    return
                }
                if (province == "" || city == "" || district == "") {
                    MyToast.showMsg("省市县选择错误，请重新选择")
                    return
                }
                if (addressid.equals("")) {
                    mPersenter!!.potaddress(shouhuoren_str, phonenumber_str, province, city, district, address_str, isdef.toString())
                } else {
                    mPersenter!!.postbianji(addressid!!, shouhuoren_str, phonenumber_str, province, city, district, address_str, isdef.toString())
                }
            }
            R.id.newaddress_duqu -> {//跳转到城市选择页面
                startActivityForResult(Intent(this@NewAdderssActivity, SelectCityActivity::class.java), UNION_SELECT_CITY)
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_newaddress
    }

    var addressid = ""
    override fun initView() {
        addressid = intent.getStringExtra("addressid")
        if (!addressid.equals("")) {
            mPersenter!!.getaddressinfo(addressid!!)
        }
        Log.d("newaddress", addressid)
        newaddress_back.setOnClickListener(this)
        newaddress_shouhuoren_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                shouhuoren_str = newaddress_shouhuoren_et!!.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        newaddress_phonenumber_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                phonenumber_str = newaddress_phonenumber_et.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        newaddress_duqu.setOnClickListener(this)
        newaddress_address.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                address_str = newaddress_address.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        newaddress_ismoren.setOnCheckedChangeListener { buttonView, isChecked ->
            isdef = if (isChecked) {
                1
            } else {
                0
            }
        }
        newaddress_tianjia.setOnClickListener(this)
    }

    override fun initPersenter() {
        mPersenter = NewAddressPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == UNION_SELECT_CITY) {
            province = intent.getStringExtra("province")
            city = intent.getStringExtra("city")
            district = intent.getStringExtra("district")
            if (!province.equals("")) {
                newaddress_duqu.text = province!! + "-" + city!! + "-" + district!!
            }
        }
    }
}