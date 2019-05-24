package com.hechuang.labeego.ui.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import butterknife.ButterKnife
import com.hechuang.labeego.R
import com.hechuang.labeego.adapter.SelectCityAdapter
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.SelectCityBean
import com.hechuang.labeego.persenter.activity.SelectVityPersenter
import com.hechuang.labeego.tools.ui.RecycleViewDivider
import com.hechuang.labeego.view.activity.ISelectCityView
import kotlinx.android.synthetic.main.activity_selectcity.*

/**
 * 切换城市
 */
class SelectCityActivity : BaseActivity<SelectVityPersenter>(), ISelectCityView, SelectCityAdapter.OnSelectCityItmeListener, View.OnClickListener {
    override fun dismissloading() {
        mLoading!!.dismiss()
    }

//    override fun getdataerror() {
//        MyToast.showMsg("查询出错，请稍后重试")
//        this@SelectCityActivity.finish()
//    }

    private var province = ""
    private var city = ""
    private var district = ""
    private var province_adapter: SelectCityAdapter? = null
    private var city_adapter: SelectCityAdapter? = null
    private var district_adapter: SelectCityAdapter? = null
    private var cishu = 1

    override fun initlayout(): Int {
        return R.layout.activity_selectcity
    }

    override fun initPersenter() {
        mPersenter = SelectVityPersenter(this, this)
    }

    override fun initView() {
        ButterKnife.bind(this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        userinfocity_Province.layoutManager = linearLayoutManager
        userinfocity_Province.addItemDecoration(RecycleViewDivider(this, 1))
        userinfocity_title.setOnClickListener(this)
        userinfocity_back.setOnClickListener(this)
        mPersenter!!.getProvince()
    }


    private fun settwocity(name: String) {
        province = name
        mPersenter!!.getCity(name)
    }

    private fun setthreecity(name: String) {
        city = name
        mPersenter!!.getCount(name)

    }

    override fun onClick(view: View) {
        cishu = cishu - 1
        when (cishu) {
            2 -> settwocity(province)
            1 -> mPersenter!!.getProvince()
            0 -> {
                val data = Intent()
                data.putExtra("city", "")
                data.putExtra("province", "")
                data.putExtra("district", "")
                setResult(Activity.RESULT_OK, data)
                this@SelectCityActivity.finish()
            }
        }
    }

    override fun OnSelectCityItmeListener(position: Int, name: String) {
        cishu = cishu + 1
        when (cishu) {
            2 -> settwocity(name)
            3 -> setthreecity(name)
            4 -> {
                district = name
                val data = Intent()
                data.putExtra("city", city)
                data.putExtra("province", province)
                data.putExtra("district", district)
                setResult(Activity.RESULT_OK, data)
                this@SelectCityActivity.finish()
            }
        }
        Log.d(TAG, "OnSelectCityItmeListener: $cishu")
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cishu = cishu - 1
            when (cishu) {
                2 -> mPersenter!!.getCity(province)
                1 -> mPersenter!!.getProvince()
                0 -> {
                    val data = Intent()
                    data.putExtra("city", "")
                    data.putExtra("province", "")
                    data.putExtra("district", "")
                    setResult(Activity.RESULT_OK, data)
                    this@SelectCityActivity.finish()
                }
            }
        }
        return false
    }

    override fun showloading() {
        mLoading!!.show()
    }


    override fun getprovinceok(selectCityBean: SelectCityBean.DataBean) {
        province_adapter = SelectCityAdapter(this, selectCityBean.list)
        province_adapter!!.setOnSelectCityItmeListener(this)
        userinfocity_Province!!.adapter = province_adapter
    }

    override fun getcityok(selectCityBean: SelectCityBean.DataBean) {
        city_adapter = SelectCityAdapter(this, selectCityBean.list)
        city_adapter!!.setOnSelectCityItmeListener(this)
        userinfocity_Province!!.adapter = city_adapter
    }

    override fun getcountok(selectCityBean: SelectCityBean.DataBean) {
        if (selectCityBean.list != null && selectCityBean.list.size > 0) {
            district_adapter = SelectCityAdapter(this, selectCityBean.list)
            district_adapter!!.setOnSelectCityItmeListener(this)
            userinfocity_Province!!.adapter = district_adapter
        } else {
            val data = Intent()
            data.putExtra("city", city)
            data.putExtra("province", province)
            data.putExtra("district", "")
            setResult(Activity.RESULT_OK, data)
            this@SelectCityActivity.finish()
        }
    }

    companion object {

        private val TAG = "UserinfoSelectCityActiv"
    }
}
