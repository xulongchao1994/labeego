package com.hechuang.labeego.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.PopupWindow
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hechuang.labeego.R
import com.hechuang.labeego.adapter.Login_popup_user_adapter
import com.hechuang.labeego.api.PathConstant
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.*
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.activity.LoginPersenter
import com.hechuang.labeego.tools.Util.KeyBoardUtils
import com.hechuang.labeego.tools.Util.MD5Builder
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.tools.ui.BasePopupWindow
import com.hechuang.labeego.view.activity.ILoginView
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPersenter>(), ILoginView, View.OnClickListener {
    override fun getauthcode(codeBean: AuthCodeBean) {
        starttiem()
        authcode = codeBean.data.vcode
    }

    override fun getautherror(msg: String) {
        MyToast.showMsg(msg)
        bt_login_yanzhenma!!.isClickable = true
        bt_login_yanzhenma!!.text = "获取验证码"
        if (time != null) {
            time!!.onFinish()
            time!!.cancel()
        }
    }

    internal var login_popupWindow: BasePopupWindow? = null
    override fun getphoneuserlist(phoneLoginBean: PhoneLoginBean) {
        bt_login_login!!.isClickable = true
        if (phoneLoginBean.data.list.size == 1) {
            mPersenter!!.getphonelogin(phoneLoginBean.data.token, phoneLoginBean.data.list[0].userId)
        } else {
            showpopup(phoneLoginBean)
        }
    }

    private fun showpopup(phoneLoginBean: PhoneLoginBean) {
        val view = LayoutInflater.from(this).inflate(R.layout.view_popup_user, null)
        val recyclerview = view.findViewById<RecyclerView>(R.id.login_popup_recycler)
        val linearlayoutmanager = LinearLayoutManager(this)
        linearlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = linearlayoutmanager
        login_popupWindow = BasePopupWindow(this)
        login_popupWindow!!.contentView = view
        login_popupWindow!!.showAtLocation(ll_login_qita, Gravity.BOTTOM, 0, 0)
        val adapter = Login_popup_user_adapter(R.layout.adapter_login_popup_item, phoneLoginBean.data.list)
        recyclerview.adapter = adapter
        adapter.setNewData(phoneLoginBean.data.list)
        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            if (login_popupWindow != null && login_popupWindow!!.isShowing) {
                login_popupWindow!!.dismiss()
            }
            mPersenter!!.getphonelogin(phoneLoginBean.data.token, phoneLoginBean.data.list[position].userId)
        }
    }

    override fun getphoneuserlisterror(msg: String) {
        bt_login_login!!.isClickable = true
        MyToast.showMsg(msg)
    }

    override fun getphoneloginsuccess(phoneSuccessBean: PhoneSuccessBean) {
        UserData.USERNAME = phoneSuccessBean.data.list[0].userId
        UserData.USERTYPE = phoneSuccessBean.data.list[0].userType
        UserData.UNIONID = phoneSuccessBean.data.list[0].trueName
        UserData.USERSESSIONID = phoneSuccessBean.data.list[0].sessionid
        UserData.USERTOKENID = phoneSuccessBean.data.list[0].token
        UserData.USERISLOGIN = true

        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        val editor = sp!!.edit()
        editor.putBoolean("islogin", true)
        editor.putString("username", UserData.USERNAME)
        editor.putString("token_id", UserData.USERTOKENID)
        editor.commit()
        if (login_popupWindow != null && login_popupWindow!!.isShowing) {
            login_popupWindow!!.dismiss()
        }
        startActivity(Intent(this@LoginActivity, WebsActivity::class.java))
    }

    override fun getphoneloginerror(msg: String) {
        MyToast.showMsg(msg)
        UserData.USERISLOGIN = false
        if (login_popupWindow != null && login_popupWindow!!.isShowing) {
            login_popupWindow!!.dismiss()
        }
    }

    @SuppressLint("HandlerLeak")
    private val hand = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.toString()) {
                "1" -> time!!.cancel()
            }
        }
    }
    private var time: CountDownTimer? = null
    private fun starttiem() {
        time = object : CountDownTimer((60 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                bt_login_yanzhenma!!.text = (millisUntilFinished / 1000).toString() + "s"
                if (millisUntilFinished <= 1000) {
                    bt_login_yanzhenma!!.isClickable = true
                    bt_login_yanzhenma!!.text = "获取验证码"
                    val msg = Message.obtain()
                    //                    msg.obj = data;
                    msg.what = 1   //标志消息的标志
                    hand.sendMessage(msg)
                }
            }

            override fun onFinish() {
                bt_login_yanzhenma!!.isClickable = true
                bt_login_yanzhenma!!.text = "获取验证码"
                val msg = Message.obtain()
                //                    msg.obj = data;
                msg.what = 1   //标志消息的标志
                hand.sendMessage(msg)
            }
        }
        time!!.start()
    }

    companion object {
        var LOGINACTION: String = "activity.loginactivity"
        var mLoginActivity: LoginActivity = LoginActivity()
    }

    private var logintype = 1 //0是短信验证登录，1是账号密码登录
    override fun get_view_success(dataBean: LoginviewBean.DataBean) {
        if (dataBean.status == 1) {
            tv_login_he_register.text = dataBean.list.hcuser
            tv_login_he_register.setOnClickListener {
                val intent_r = Intent(this@LoginActivity, ScanWebActivity::class.java)
                intent_r.putExtra("web", dataBean.list.hcurl)
                startActivity(intent_r)
            }
            tv_login_register.text = dataBean.list.reguser
            tv_login_register.setOnClickListener {
                val intent_r = Intent(this@LoginActivity, ScanWebActivity::class.java)
                intent_r.putExtra("web", dataBean.list.regurl)
                startActivity(intent_r)
            }
        }
        dismissloading()
    }

    override fun get_view_error(msg: String) {
        tv_login_he_register.text = ""
        tv_login_register.text = ""
        dismissloading()

    }

    override fun login_fmpw(mLogindata: Login_Bean) {
        dismissloading()
        Toast.makeText(this, mLogindata.data.msg, Toast.LENGTH_SHORT).show()
        UserData.USERNAME = mLogindata.data.userid
        UserData.USERTOKENID = mLogindata.data.token
        startActivity(Intent(this@LoginActivity, ForceModifyPasswordActivity::class.java))
    }

    override fun login_error(msg: String) {
        bt_login_yanzhenma!!.isClickable = true
        bt_login_login!!.isClickable = true
        dismissloading()
        Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_SHORT).show()
    }

    internal var xiangtong = false
    internal var fasdfasd = 0//记录namelist的位置
    override fun login_seccess(mLogindata: Login_Bean) { //登录成功
        bt_login_login!!.isClickable = true
        namebuffer = StringBuffer()
        pswbuffer = StringBuffer()
        for (i in mNameList.indices) {
            if (mNameList[i].name.equals(name_str)) {
                xiangtong = true
            }
        }

        if (mNameList.size > 9) {
            if (!xiangtong)
                mNameList.removeAt(mNameList.size - 1)
        }

        if (!xiangtong) {
            namebuffer!!.append("$name_str,")
            if (remeberpsw_b as Boolean) {
                pswbuffer!!.append("$psw_str,")
            } else {
                pswbuffer!!.append(" " + ",")
            }
        } else {
            if (!(remeberpsw_b as Boolean)) {
                for (i in mNameList.indices) {
                    if (mNameList[i].name.equals(name_str)) {
                        fasdfasd = i
                    }
                }
                mNameList.removeAt(fasdfasd)
                namebuffer!!.append("$name_str,")
                pswbuffer!!.append(" " + ",")
            } else {
                for (i in mNameList.indices) {
                    if (mNameList[i].name.equals(name_str)) {
                        fasdfasd = i
                    }
                }
                mNameList.removeAt(fasdfasd)
                namebuffer!!.append("$name_str,")
                pswbuffer!!.append("$psw_str,")
            }
        }
        for (i in mNameList.indices) {
            if (!mNameList[i].name.equals("")) {
                namebuffer!!.append(mNameList[i].name + ",")
                pswbuffer!!.append(mNameList[i].psw + ",")
            }
        }
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
        namelist_str = namebuffer.toString()
        pswlist_str = pswbuffer.toString()
        UserData.USERNAME = mLogindata.data.list.username
        UserData.USERSESSIONID = mLogindata.data.list.sessionid
        UserData.USERTOKENID = mLogindata.data.list.token
        UserData.USERTYPE = mLogindata.data.list.usertype
        UserData.USERISLOGIN = true
        val editor: SharedPreferences.Editor = sp!!.edit()
        editor.putString("username", name_str)
        editor.putString("password", psw_str)
        editor.putBoolean("isremeberpsw", remeberpsw_b!!)
        editor.putString("username_list", namelist_str)
        editor.putString("password_list", pswlist_str)
        editor.putString("token_id", mLogindata.data.list.token)
        editor.putString("urserid", mLogindata.data.list.username)
        editor.putString("usertype", mLogindata.data.list.usertype)
        editor.putString("usersessionid", mLogindata.data.list.sessionid)
        editor.putBoolean("islogin", true)
        editor.putBoolean("isoutlogin", false)
        var isisi = editor.commit()
        dismissloading()
        Log.d("login", "login_ok: " + mLogindata.data.list.status)
        when (mLogindata.data.list.status) {
            "1" -> {
                val intent1 = Intent(this@LoginActivity, WebsActivity::class.java)
                intent1.putExtra("web", mLogindata.data.list.url)
                startActivity(intent1)
                finish()
            }

            "2" -> {
                val intent = Intent(LOGINACTION)
                intent.putExtra("weizhi", 1)
                sendBroadcast(intent)
                val intent1 = Intent(this@LoginActivity, LabeegoActivity::class.java)
                intent1.putExtra("weizhi", 1)
                startActivity(intent1)
                finish()
            }

            "3" -> {
                startActivity(Intent(this, WebsActivity::class.java))
                finish()
            }
        }
    }


    var mNameList = ArrayList<Login_nameBean>()
    var psw_str: String? = null
    var name_str: String? = null
    var pswlist_str: String? = null
    var namelist_str: String? = null
    var namebuffer: StringBuffer? = null
    var pswbuffer: StringBuffer? = null
    var sp: SharedPreferences? = null
    var remeberpsw_b: Boolean? = null
    private var authcode_et_str = ""
    private var authcode = ""
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_login_back -> {
                startActivity(Intent(this, WebsActivity::class.java))
                finish()
            }
            R.id.bt_login_login -> {//登录按钮
                bt_login_login!!.isClickable = false
                name_str = et_login_name.text.toString().trim { it <= ' ' }
                psw_str = et_login_psw!!.text.toString().trim { it <= ' ' }
                authcode_et_str = et_login_yanzhengma!!.text.toString().trim { it <= ' ' }
                if (logintype == 0) {
                    if (popupWindow != null && popupWindow!!.isShowing) {
                        popupWindow!!.dismiss()
                    }
                    Log.d("login", "$authcode_et_str $authcode")
                    if (authcode_et_str != authcode) {
                        Toast.makeText(this@LoginActivity, "验证码输入错误", Toast.LENGTH_SHORT).show()
                        bt_login_login!!.isClickable = true
                        return
                    }
                    KeyBoardUtils.closeKeyBoard(et_login_name!!)
                    KeyBoardUtils.closeKeyBoard(et_login_psw!!)
                    KeyBoardUtils.closeKeyBoard(et_login_yanzhengma)
                    mPersenter!!.getuserlist(name_str!!, authcode_et_str)
                } else {
                    if (name_str == "") {
                        Toast.makeText(this@LoginActivity, "请输入用户ID", Toast.LENGTH_SHORT).show()
                        bt_login_login!!.isClickable = true
                        return
                    }
                    if (psw_str == "") {
                        Toast.makeText(this@LoginActivity, "请输入密码", Toast.LENGTH_SHORT).show()
                        bt_login_login!!.isClickable = true
                        return
                    }
//                    if (!isagreement_b!!) {
//                        Toast.makeText(this@LoginActivity, "请阅读并同意《用户服务协议》", Toast.LENGTH_SHORT).show()
//                        bt_login_login!!.isClickable = true
//                        return
//                    }
                    KeyBoardUtils.closeKeyBoard(et_login_name)
                    KeyBoardUtils.closeKeyBoard(et_login_psw)
                    KeyBoardUtils.closeKeyBoard(et_login_yanzhengma)
                    val password = MD5Builder.getMD5Str(psw_str!!)
                    mPersenter!!.login(name_str!!, password)
                }
//                KeyBoardUtils.closeKeyBoard(et_login_name)
//                KeyBoardUtils.closeKeyBoard(et_login_psw)
//                val password = MD5Builder.getMD5Str(psw_str!!)
//                Log.d("login", password)
//                mPersenter!!.login(name_str!!, password)
            }
            R.id.bt_login_name_list -> {//用户名列表
                if (popupWindow!!.isShowing()) {
                    popupWindow!!.dismiss()
                } else {
                    popupWindow!!.showAsDropDown(ll_login_name_layout)
                }
                bt_login_name_list.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.top_g))
                KeyBoardUtils.closeKeyBoard(et_login_name)
                KeyBoardUtils.closeKeyBoard(et_login_psw)
            }
            R.id.tv_login_findpsw -> {
                val intent_r = Intent(this@LoginActivity, ScanWebActivity::class.java)
                intent_r.putExtra("web", PathConstant.FAND_PSW)
                startActivity(intent_r)
//                val uri = Uri.parse(PathConstant.FAND_PSW)
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(intent)
            }
            R.id.tv_login_he_register -> {
                val intent_r = Intent(this@LoginActivity, ScanWebActivity::class.java)
                intent_r.putExtra("web", PathConstant.HC_REGISTER)
                startActivity(intent_r)
//                val uri = Uri.parse(PathConstant.HC_REGISTER)
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(intent)
            }
            R.id.tv_login_register -> {
                val intent_r = Intent(this@LoginActivity, ScanWebActivity::class.java)
                intent_r.putExtra("web", PathConstant.REGISTER)
                startActivity(intent_r)
//                val uri = Uri.parse(PathConstant.REGISTER)
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(intent)
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_login
    }

    private var wxAPI: IWXAPI? = null
    override fun initView() {
        wxAPI = WXAPIFactory.createWXAPI(this, UserData.wxappid, true)
        wxAPI!!.registerApp(UserData.wxappid)
        mPersenter!!.getviewdata()
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        et_login_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                name_str = et_login_name.text.toString()
            }
        })
        et_login_psw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                psw_str = et_login_psw.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        cb_login_rememberpsw.setOnCheckedChangeListener { _, isChecked -> remeberpsw_b = isChecked }
        et_login_psw.setOnFocusChangeListener { _, hasFocus ->
            run {
                if (hasFocus)
                    img_login_icon.visibility = View.GONE
                else
                    img_login_icon.visibility = View.VISIBLE
            }
        }
        cb_login_eye.setOnCheckedChangeListener { _, isChecked -> showOrHide(isChecked) }
        bt_login_name_list.setOnClickListener(this)//用户名列表那妞
        bt_login_login.setOnClickListener(this)//登录按钮
        tv_login_findpsw.setOnClickListener(this)
//        tv_login_he_register.setOnClickListener(this)
//        tv_login_register.setOnClickListener(this)
        img_login_back.setOnClickListener(this)
        ll_login_wexinlogin.setOnClickListener {
            if (!wxAPI!!.isWXAppInstalled) {//未安装微信
                //            Intent intent = new Intent(this, MyDialogActivity.class);
                //            startActivity(intent);
                MyToast.showMsg("未安装微信")
            } else {//已安装微信，唤醒微信登录
                val req = SendAuth.Req()
                req.scope = "snsapi_userinfo"
                req.state = System.currentTimeMillis().toString()
                wxAPI!!.sendReq(req)
            }
        }
        login_pswlogin!!.setOnClickListener {
            val str = login_pswlogin_text!!.text.toString()
            //                Log.d(TAG, "onClick: " + str);
            if (str == "账号密码登录") {
                logintype = 1
                login_pswlogin_text!!.text = "短信登录"
                cb_login_rememberpsw!!.visibility = View.VISIBLE
                ll_login_psw!!.visibility = View.VISIBLE
                ll_login_yanzhengma!!.visibility = View.GONE
                showOrHide(false)
                et_login_name!!.hint = "请输入用户ID"
                et_login_name!!.setText("")
                et_login_name!!.inputType = EditorInfo.TYPE_CLASS_TEXT
            } else if (str == "短信登录") {
                logintype = 0
                login_pswlogin_text!!.text = "账号密码登录"
                cb_login_rememberpsw!!.visibility = View.GONE
                ll_login_psw!!.visibility = View.GONE
                ll_login_yanzhengma!!.visibility = View.VISIBLE
                //                    login_psw.setHint("输入验证码");
                et_login_name!!.hint = "请输入手机号"
                et_login_name!!.setText("")
                et_login_name!!.inputType = EditorInfo.TYPE_CLASS_PHONE
                bt_login_name_list!!.visibility = View.GONE
                showOrHide(true)
            }
        }

        bt_login_yanzhenma!!.setOnClickListener(View.OnClickListener {
            Log.d("login", "login_yanzhengma")
            name_str = et_login_name!!.text.toString().trim { it <= ' ' }
            if (name_str == "" || name_str!!.length != 11) {
                Toast.makeText(this@LoginActivity, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            bt_login_yanzhenma!!.isClickable = false
            mPersenter!!.getauthcode(name_str!!)
        })
        shownamelist()
    }

    private fun showOrHide(isShow: Boolean) {
        //记住光标开始的位置
        val pos = et_login_psw.selectionStart
        if (isShow) {
            et_login_psw.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            et_login_psw.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        et_login_psw.setSelection(pos)
    }

    override fun initPersenter() {
        mPersenter = LoginPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    var popupWindow: PopupWindow? = null
    /**
     * 显示
     */
    private fun shownamelist() {
        val view = LayoutInflater.from(this).inflate(R.layout.popup_login_namelist, null)
        val nameList = view.findViewById<RecyclerView>(R.id.popup_name_list)
        popupWindow = PopupWindow(this)
        popupWindow!!.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.height = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow!!.contentView = view
        popupWindow!!.setBackgroundDrawable(ColorDrawable(0x00000000))
        //        popupWindow.setTouchable(false);
        popupWindow!!.isFocusable = true
        popupWindow!!.isOutsideTouchable = true
        popupWindow!!.setOnDismissListener({ bt_login_name_list.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.down_g)) }

        )
        val manager = LinearLayoutManager(this)
        manager.orientation = OrientationHelper.VERTICAL
        nameList.layoutManager = manager
        var baseadater = object : BaseQuickAdapter<Login_nameBean, BaseViewHolder>(R.layout.adapter_login_namelist) {
            override fun convert(helper: BaseViewHolder?, item: Login_nameBean?) {
                helper!!.setText(R.id.login_name_name, item!!.name)
                helper.addOnClickListener(R.id.login_name_delete)
            }
        }
        baseadater.setOnItemClickListener { adapter, view, position ->
            name_str = mNameList[position].name
            psw_str = mNameList[position].psw
            et_login_name.setText(name_str)
            if (!psw_str.equals(" ")) {
                et_login_psw.setText(psw_str)
            } else {
                et_login_psw.setText("")
            }
            if (popupWindow!!.isShowing) {
                popupWindow!!.dismiss()
            }
        }
        baseadater.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.login_name_delete -> {
                    namebuffer = StringBuffer("")
                    pswbuffer = StringBuffer("")
                    if (mNameList.size > 1) {
                        mNameList.removeAt(position)
//                        baseadater.remove(position)
//                        Log.d("logind", mNameList.size.toString())

                        for (i in 0 until mNameList.size) {
                            if (!mNameList[i].name.equals("")) {
                                namebuffer!!.append(mNameList[i].name + ",")
                                pswbuffer!!.append(mNameList[i].psw + ",")
                            }
                        }
                        namelist_str = namebuffer!!.toString()
                        pswlist_str = pswbuffer!!.toString()
                        Log.d("logind", "$namelist_str $pswlist_str")
                        baseadater.notifyDataSetChanged()
                    } else {
                        mNameList.clear()
                        namelist_str = ""
                        pswlist_str = ""
                        bt_login_name_list.visibility = View.GONE
                        if (popupWindow!!.isShowing) {
                            popupWindow!!.dismiss()
                        }
                    }
                    val editor: SharedPreferences.Editor = sp!!.edit()
                    editor.putString("username_list", namelist_str)
                    editor.putString("password_list", pswlist_str)
                    editor.commit()
                }
            }
        }
        baseadater.setNewData(mNameList)
        nameList.adapter = baseadater
//        nameList.adapter = Login_nameAdapter(mNameList, this, RvListener { id, position ->
//            run {
//
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        name_str = null
        psw_str = null
    }

    override fun onResume() {
        super.onResume()
        if (mNameList.size > 0)
            mNameList.clear()
        remeberpsw_b = sp!!.getBoolean("isremeberpsw", false)
        cb_login_rememberpsw.isChecked = remeberpsw_b as Boolean
        name_str = sp!!.getString("username", "")
        namelist_str = sp!!.getString("username_list", "")
        pswlist_str = sp!!.getString("password_list", "")
        val nameshuzu = namelist_str!!.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val pswshuzu = pswlist_str!!.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val namel = nameshuzu.size
        for (i in 0 until namel) {
            val bean = Login_nameBean(nameshuzu[i], pswshuzu[i])
            mNameList.add(bean)
        }
        et_login_name.setText(name_str)

        if (remeberpsw_b as Boolean) {
            psw_str = sp!!.getString("password", "")
//            Log.d("login", "onResume: $psw_str")
            et_login_psw.setText(psw_str)
        } else {
            psw_str = ""
            et_login_psw.setText("")
        }
        if (namelist_str == null || namelist_str == "" || namelist_str!!.length < 0) {
            bt_login_name_list.setVisibility(View.GONE)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this, WebsActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}