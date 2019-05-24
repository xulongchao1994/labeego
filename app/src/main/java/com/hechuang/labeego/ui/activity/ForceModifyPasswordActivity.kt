package com.hechuang.labeego.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.persenter.activity.ForceModifyPasswordPersenter
import com.hechuang.labeego.tools.Util.MD5Builder
import com.hechuang.labeego.tools.Util.Utils
import com.hechuang.labeego.view.activity.IForceModifyPasswordView
import kotlinx.android.synthetic.main.activity_farcemodifypassword.*

/**
 * Created by Android_xu on 2017/12/4.
 * 强制修改密码
 */
class ForceModifyPasswordActivity : BaseActivity<ForceModifyPasswordPersenter>(), IForceModifyPasswordView, View.OnClickListener {
    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    private var mName_str: String? = null
    private var mPhone_str: String? = null
    private var mOnepwd_str: String? = null
    private var mTwopwd_str: String? = null
    private var sp: SharedPreferences? = null


    override fun onClick(v: View) {
        when (v.id) {
            R.id.twopassword_bt -> {
//                if (mName_str == null || mName_str == "") {
//                    MyToast.showMsg("请输入ID")
//                    return
//                }
                if (mPhone_str == null || mPhone_str == "") {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show()
                    return
                }
                if (mOnepwd_str == null || mOnepwd_str == "") {
                    Toast.makeText(this, "请输入原密码", Toast.LENGTH_SHORT).show()
                    return
                }
                if (mTwopwd_str == null || mTwopwd_str == "") {
                    Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show()
                    return
                }
                if (mTwopwd_str != mOnepwd_str) {
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show()
                    return
                }
                if (!Utils.Pwdisok(mTwopwd_str!!)) {
                    Toast.makeText(this, "新密码必须至少包含大小写字母和数字的组合，长度在8-30之间", Toast.LENGTH_SHORT).show()
                    return
                }
                val onepassword = MD5Builder.getMD5Str(mOnepwd_str!!)
                val twopassword = MD5Builder.getMD5Str(mTwopwd_str!!)
                mPersenter!!.settwopwd(mPhone_str!!, onepassword, twopassword)
            }
        }
    }


    override fun PwdSeccess(status: Int, msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        Log.d("forcelogind", status.toString() + "   " + msg
        )
        if (status == 1) {// 成功，退出登录
            startActivity(Intent(this@ForceModifyPasswordActivity, LoginActivity::class.java))
            finish()
        } else {//失败，提示信息
        }
    }


    override fun initlayout(): Int {
        return R.layout.activity_farcemodifypassword
    }

    override fun initPersenter() {
        mPersenter = ForceModifyPasswordPersenter(this, this)
    }

    override fun initView() {
        sp = getSharedPreferences("userInfo", 0)
        twopassword_bt.setOnClickListener(this)
        twopassword_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                mName_str = twopassword_name.text.toString()
            }
        })
        twopassword_phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                mPhone_str = twopassword_phone.text.toString()
            }
        })
        twopassword_onepwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                mOnepwd_str = twopassword_onepwd.text.toString()
            }
        })
        twopassword_twopwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                mTwopwd_str = twopassword_twopwd.text.toString()
            }
        })
    }

    override fun showloading() {

    }

}
