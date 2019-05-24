package com.hechuang.labeego.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.hechuang.labeego.tools.Util.AppManager
import com.hechuang.labeego.tools.ui.LoadingDialog
import com.hechuang.labeego.tools.ui.UiDensity

abstract class BaseActivity<P : BasePersenter<*>> : AppCompatActivity() {
    protected var mPersenter: P? = null
    @JvmField
    protected var mLoading: LoadingDialog? = null

    protected abstract fun initlayout(): Int
    protected abstract fun initView()
    protected abstract fun initPersenter()
    var mImmersionBar: ImmersionBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoading = LoadingDialog(this)
        mLoading!!.setCanceledOnTouchOutside(false)
        AppManager.getAppManager().addActivity(this)
        UiDensity.setDefault(this)
        setContentView(initlayout())
        initPersenter()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPersenter != null) {
            mPersenter!!.cannelRequest()
        }
        if (mImmersionBar != null)
            mImmersionBar!!.destroy()
        mLoading!!.dismiss()
        mLoading = null
        mPersenter = null
    }
}