package com.hechuang.labeego.ui.activity

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.activity.ChangePassWrodPersenter
import com.hechuang.labeego.tools.Util.KeyBoardUtils
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IChangePswView
import kotlinx.android.synthetic.main.activity_changepassword.*

class ChangePWAcitivity : BaseActivity<ChangePassWrodPersenter>(), IChangePswView, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    override fun setpswsuccess(msg: String) {
        dismissloading()
        MyToast.showMsg(msg)
        outlogin()
        startActivity(Intent(this@ChangePWAcitivity, LoginActivity::class.java))
        finish()
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
        UserData.USERNAME = ""
        UserData.USERTYPE = ""
        UserData.USER_ID = ""
    }

    override fun setpswerror(msg: String) {
        dismissloading()
        MyToast.showMsg(msg)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.changepw_back -> finish()
            R.id.changepw_confirmpw_bt -> {
                KeyBoardUtils.closeKeyBoard(changepw_oldpw_ed)
                KeyBoardUtils.closeKeyBoard(changepw_newpw_ed)
                KeyBoardUtils.closeKeyBoard(changepw_confirmpw_ed)
                confirmpw()
            }
        }
    }

    /**
     * 提价密码
     */
    private fun confirmpw() {
        Log.d("changepass", "$olderpsw_str  $newpsw_str  $confirmpsw_str")
        if (olderpsw_str == "") {
            MyToast.showMsg("请输入原密码")
            return
        }
        if (newpsw_str == "") {
            MyToast.showMsg("请输入新密码")
            return
        }
        if (confirmpsw_str == "") {
            MyToast.showMsg("请确认新密码")
            return
        }
        if (newpsw_str != confirmpsw_str) {
            MyToast.showMsg("两次密码不一致")
            return
        }
        mPersenter!!.setpsw(olderpsw_str, newpsw_str, confirmpsw_str)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        when (buttonView!!.id) {
            R.id.changepw_oldpw_cb -> {
                showOrHide(changepw_oldpw_ed, isChecked)
            }
            R.id.changepw_newpw_cb -> {
                showOrHide(changepw_newpw_ed, isChecked)
            }
            R.id.changepw_confirmpw_cb -> {
                showOrHide(changepw_confirmpw_ed, isChecked)
            }
        }
    }

    private fun showOrHide(view: EditText, isShow: Boolean) {
        //记住光标开始的位置
        val pos = view.selectionStart
        if (isShow) {
            view.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            view.transformationMethod = PasswordTransformationMethod.getInstance()

        }
        view.setSelection(pos)
    }

    override fun initlayout(): Int {
        return R.layout.activity_changepassword
    }

    var olderpsw_str = ""
    var newpsw_str = ""
    var confirmpsw_str = ""
    override fun initView() {
        changepw_back.setOnClickListener(this)
        changepw_oldpw_ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                olderpsw_str = changepw_oldpw_ed.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        changepw_newpw_ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                newpsw_str = changepw_newpw_ed.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        changepw_confirmpw_ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                confirmpsw_str = changepw_confirmpw_ed.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        changepw_oldpw_cb.setOnCheckedChangeListener(this)
        changepw_newpw_cb.setOnCheckedChangeListener(this)
        changepw_confirmpw_cb.setOnCheckedChangeListener(this)
        changepw_confirmpw_bt.setOnClickListener(this)
    }

    override fun initPersenter() {
        mPersenter = ChangePassWrodPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }
}