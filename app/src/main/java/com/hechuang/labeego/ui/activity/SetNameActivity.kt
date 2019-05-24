package com.hechuang.labeego.ui.activity

import android.text.Editable
import android.text.TextWatcher
import com.hechuang.labeego.R
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.persenter.activity.SetNamePersenter
import com.hechuang.labeego.tools.Util.KeyBoardUtils
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.ISetNameView
import kotlinx.android.synthetic.main.activity_setname.*

class SetNameActivity : BaseActivity<SetNamePersenter>(), ISetNameView {
    override fun setnamesuccess(msg: String) {
        MyToast.showMsg(msg)
        finish()
    }

    override fun setnameerror(msg: String) {
        MyToast.showMsg(msg)
    }

    override fun initlayout(): Int {
        return R.layout.activity_setname
    }

    var name = ""
    var intentname = ""
    override fun initView() {
        intentname = intent.getStringExtra("name")
        setname_back.setOnClickListener { finish() }
        setname_title_text.setOnClickListener { finish() }
        setname_baocun.setOnClickListener {
            KeyBoardUtils.closeKeyBoard(setname_setname)
            if (name == "") {
                mPersenter!!.setname(intentname)
            } else {
                mPersenter!!.setname(name)
            }
        }
        setname_setname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                name = setname_setname.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        setname_setname.hint = intentname
    }

    override fun initPersenter() {
        mPersenter = SetNamePersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }
}