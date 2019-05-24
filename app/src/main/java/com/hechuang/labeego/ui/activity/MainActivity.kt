package com.hechuang.labeego.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.hechuang.labeego.R
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.api.PathConstant
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.bean.LoginBean
import com.hechuang.labeego.bean.Login_Bean
import com.hechuang.labeego.bean.V_bean
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.activity.MainPersenter
import com.hechuang.labeego.tools.Util.MD5Builder
import com.hechuang.labeego.tools.Util.MyToast
import com.hechuang.labeego.view.activity.IMainView
import java.io.File


class MainActivity : BaseActivity<MainPersenter>(), IMainView {
    override fun login_success(loginBean: LoginBean) {
//        Log.d("mainactivity", "login_ok")
        if (loginBean.data.status == "1") {
            UserData.USERTOKENID = loginBean.data.list.token
            UserData.USER_ID = loginBean.data.list.userid
            UserData.USERTYPE = loginBean.data.list.usertype
            UserData.USERSESSIONID = loginBean.data.list.sessionid
            UserData.USERNAME = loginBean.data.list.username
            UserData.USERISLOGIN = true
            url_name = loginBean.data.list.url
            starus_name = loginBean.data.status
            editor!!.putString("token_id", loginBean.data.list.token)
            editor!!.putString("urserid", loginBean.data.list.username)
            editor!!.putString("usertype", loginBean.data.list.usertype)
//            editor!!.putString("name", loginBean.data.list.username)
            editor!!.putString("usersessionid", loginBean.data.list.sessionid)
            editor!!.putBoolean("islogin", true)
            editor!!.putBoolean("isforce", true)
            editor!!.putBoolean("isoutlogin", false)
            editor!!.commit()
            Log.d("main", "login_ok: " + UserData.USERTOKENID + UserData.USERNAME + UserData.USERSESSIONID + "   " + UserData.USERTOKENID)
        } else if (loginBean.data.status == "2") {
            UserData.USERTOKENID = loginBean.data.token
            UserData.USER_ID = loginBean.data.userid
            editor!!.putBoolean("islogin", true)
            editor!!.putBoolean("isforce", false)
            editor!!.commit()
        } else {
            editor!!.putBoolean("islogin", false)
            editor!!.putBoolean("isforce", false)
            UserData.USERISLOGIN = false
            editor!!.commit()
        }
        mPersenter!!.get_v()
    }

    var url_name = ""
    var starus_name = ""
    override fun getv_error(msg: String) {
        var intent = Intent(this@MainActivity, WebsActivity::class.java)
        startActivity(intent)
    }

    var editor: SharedPreferences.Editor? = null
    var sp: SharedPreferences? = null

    companion object {
        var MAINACTION: String = "activity.mainactivity"
    }

    override fun login_ok(loginBean: Login_Bean) {
//        Log.d("mainactivity", "login_ok")
        if (loginBean.data.status == 1) {
            UserData.USERTOKENID = loginBean.data.list.token
            UserData.USER_ID = loginBean.data.list.userid
            UserData.USERTYPE = loginBean.data.list.usertype
            UserData.USERSESSIONID = loginBean.data.list.sessionid
            UserData.USERNAME = loginBean.data.list.username
            UserData.USERISLOGIN = true
            url_name = loginBean.data.list.url
            starus_name = loginBean.data.list.status
            editor!!.putString("token_id", loginBean.data.list.token)
            editor!!.putString("urserid", loginBean.data.list.username)
            editor!!.putString("usertype", loginBean.data.list.usertype)
//            editor!!.putString("name", loginBean.data.list.username)
            editor!!.putString("usersessionid", loginBean.data.list.sessionid)
            editor!!.putBoolean("islogin", true)
            editor!!.putBoolean("isforce", true)
            editor!!.putBoolean("isoutlogin", false)
            editor!!.commit()
//            Log.d("main", "login_ok: " + UserData.USERTOKENID + UserData.USERNAME + UserData.USERSESSIONID + "   " + UserData.USERTOKENID)
        } else if (loginBean.data.status == 2) {
            UserData.USERTOKENID = loginBean.data.token
            UserData.USER_ID = loginBean.data.userid
            editor!!.putBoolean("islogin", true)
            editor!!.putBoolean("isforce", false)
            editor!!.commit()
        } else {
            editor!!.putBoolean("islogin", false)
            editor!!.putBoolean("isforce", false)
            UserData.USERISLOGIN = false
            editor!!.commit()
        }
        mPersenter!!.get_v()
    }

//    private fun s_web() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            val checkCallPhonePermission = ContextCompat.checkSelfPermission(
//                    this@MainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE), 111)
//            } else {
//                mPersenter!!.get_v()
//            }
//        } else {
//        }

//        var intent = Intent(this@MainActivity, WebsActivity::class.java)
//        intent.putExtra("browser", v_bean.browser.toString())
//        intent.putExtra("url", v_bean.where)
//        intent.putExtra("web", v_bean.url)
//        intent.putExtra("version", v_bean.versionName)
//        startActivity(intent)
//        finish()
//    }

    override fun login_error(msg: String) {
//        Log.d("mainactivity", "login_error")
        editor!!.putBoolean("islogin", false)
        editor!!.putBoolean("isforce", false)
        UserData.USERISLOGIN = false
        editor!!.commit()
        mPersenter!!.get_v()
    }

    override fun initlayout(): Int {
        return R.layout.activity_main
    }

    var isfast: Int? = null
    override fun initView() {
        val username: String?
        val password: String?
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        val isoutlogin = sp!!.getBoolean("islogin", false)
        isfast = sp!!.getInt("isfast", 0)
//        Log.d("mainactivity", "onHandleIntent: $isoutlogin ${ApiFactify.HOST}")
        if (!isoutlogin) {
            username = ""
            password = ""
        } else {
            username = sp!!.getString("username", "")
            password = sp!!.getString("token_id", "")
        }
        editor = sp!!.edit()
//        val password2 = MD5Builder.getMD5Str(password!!)
        mPersenter!!.logind_main(username!!, password!!)
        if (Build.VERSION.SDK_INT >= 26) {
            val b = packageManager.canRequestPackageInstalls()
            if (!b) {
                requestPermissions(arrayOf(Manifest.permission.REQUEST_INSTALL_PACKAGES), 222)
            } else {
            }
        } else {
        }
        exit()
    }

    override fun initPersenter() {
        mPersenter = MainPersenter(this, this)
    }

    override fun getv_o(v_bean: V_bean) {
//        Log.d("mainactivity", "getv_o")
        PathConstant.USER_WEB = v_bean.url
        if (!url_name.equals("")) {
            when (starus_name) {
                "1" -> {
                    val intent = Intent(this@MainActivity, WebsActivity::class.java)
                    intent.putExtra("browser", v_bean.browser.toString())
                    intent.putExtra("url", v_bean.where)
//                    intent.putExtra("web", url_name)
                    intent.putExtra("version", v_bean.versionName)
                    intent.putExtra("auto", v_bean.auto)
                    startActivity(intent)
                    finish()
                }
                "2" -> {
                    val intent = Intent(MAINACTION)
                    intent.putExtra("weizhi", 1)
                    sendBroadcast(intent)
                    val intent1 = Intent(this@MainActivity, LabeegoActivity::class.java)
                    intent1.putExtra("weizhi", 1)
                    startActivity(intent1)
                    finish()
                }
                "3" -> {
                    val intent = Intent(this@MainActivity, WebsActivity::class.java)
                    intent.putExtra("browser", v_bean.browser.toString())
                    intent.putExtra("url", v_bean.where)
                    intent.putExtra("web", ApiFactify.WEB_HOST)
                    intent.putExtra("version", v_bean.versionName)
                    intent.putExtra("auto", v_bean.auto)
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            val intent = Intent(this@MainActivity, WebsActivity::class.java)
            intent.putExtra("browser", v_bean.browser.toString())
            intent.putExtra("url", v_bean.where)
            intent.putExtra("web", ApiFactify.WEB_HOST)
            intent.putExtra("version", v_bean.versionName)
            intent.putExtra("auto", v_bean.auto)
            startActivity(intent)
            finish()
        }
        exit()
//        intent.putExtra("web", v_bean.url)
//        intent.putExtra("version", v_bean.versionName)
//        intent.putExtra("auto", v_bean.auto)
//        startActivity(intent)
//        finish()
    }

    private fun exit() {
        try {
            deleteDatabase("webview.db")
            deleteDatabase("webviewCache.db")
        } catch (e: Exception) {
        }

        //WebView 缓存文件
        val appCacheDir = File(filesDir.absolutePath + "123")
        val webviewCacheDir = File(cacheDir.absolutePath + "/webviewCache")
        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir)
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir)
        }
    }

    fun deleteFile(file: File) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete()
            } else if (file.isDirectory()) {
                val files = file.listFiles()
                for (i in files!!.indices) {
                    deleteFile(files!![i])
                }
            }
            file.delete()
        } else {
//            Log.e("mainactivity", "delete file no exists " + file.getAbsolutePath())
        }
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            111 -> for (permission in permissions) {
                when (permission) {
                    "android.permission.READ_EXTERNAL_STORAGE" -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        mPersenter!!.get_v()
                    } else {
                        MyToast.showMsg("读写权限已禁止，部分功能暂时无法使用，建议在权限管理中打开")
                    }
                    "android.permission.WRITE_EXTERNAL_STORAGE" -> if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                        mPersenter!!.get_v()
                    } else {
                        MyToast.showMsg("读写权限已禁止，部分功能暂时无法使用，建议在权限管理中打开")
                    }
                }
            }
            222 ->
                for (permission in permissions) {
                    when (permission) {
                        "android.permission.REQUEST_INSTALL_PACKAGES" -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        } else {
                        }
                    }
                }
        }
    }
}
