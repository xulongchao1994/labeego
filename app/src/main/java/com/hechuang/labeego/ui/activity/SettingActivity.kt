package com.hechuang.labeego.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v4.content.ContextCompat
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.UserInfoBean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.activity.SettingPersenter
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.ISettingView
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity<SettingPersenter>(), ISettingView {

    override fun getuserinfodatasuccess(userinfoddata: UserInfoBean) {
        setting_username.text = userinfoddata.data.list.truename.truename
        settingo_mobile.text = userinfoddata.data.list.mobile.mobile
    }

    override fun getuserdatainfoerror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun initlayout(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        setting_userinfo.setOnClickListener { startActivity(Intent(this@SettingActivity, UserActivity::class.java)) }
        setting_vername.text = packageManager.getPackageInfo(packageName, 0).versionName
        setting_call.setOnClickListener { call() }
        setting_outlogin.setOnClickListener { outlogin() }
        setting_back.setOnClickListener { finish() }
        setting_safe.setOnClickListener {
            startActivity(Intent(this@SettingActivity, ChangePWAcitivity::class.java))
        }
        mPersenter!!.getuserinfodata()
    }

    private fun outlogin() {
        var sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("token_id", "")
        editor.putString("urserid", "")
        editor.putString("usertype", "")
        editor.putBoolean("islogin", false)
        UserData.USERISLOGIN = false
        editor.commit()
        UserData.USERSESSIONID = ""
        UserData.USERTOKENID = ""
        UserData.USERTYPE = ""
        UserData.USER_ID = ""
        startActivity(Intent(this@SettingActivity, LoginActivity::class.java))
        finish()
    }

    private fun call() {
        if (Build.VERSION.SDK_INT >= 23) {
            var perssino = ContextCompat.checkSelfPermission(this@SettingActivity, Manifest.permission.CALL_PHONE)
            if (perssino != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 123)
                return
            } else {
                var intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:4009625123"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        } else {
            var intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:4009625123"))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    override fun initPersenter() {
        mPersenter = SettingPersenter(this, this)
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