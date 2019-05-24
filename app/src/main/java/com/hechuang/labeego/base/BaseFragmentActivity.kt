package com.hechuang.labeego.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.gyf.barlibrary.ImmersionBar
import com.hechuang.labeego.tools.Util.AppManager
import com.hechuang.labeego.tools.ui.UiDensity

abstract class BaseFragmentActivity<P : BasePersenter<*>> : FragmentActivity() {
    protected var mPersenter: P? = null
    protected var mLoading: ProgressDialog? = null
    protected abstract fun initlayout(): Int
    protected abstract fun initView()
    protected abstract fun initPersenter()
    var mImmersionBar: ImmersionBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mImmersionBar = ImmersionBar.with(this)
//        mImmersionBar!!.statusBarColor(R.color.aloorodertitle)
//                .fitsSystemWindows(true).statusBarDarkFont(true).init()
        mLoading = ProgressDialog(this)
        mLoading!!.setMessage("正在加载...")
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
//            mPersenter!!.mContext = null
//            mPersenter!!.mView = null
            mPersenter!!.cannelRequest()
        }
        if (mImmersionBar != null)
            mImmersionBar!!.destroy()
        mLoading!!.dismiss()
        mLoading = null
        mPersenter = null
    }
}