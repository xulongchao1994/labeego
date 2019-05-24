package com.hechuang.labeego.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hechuang.labeego.tools.ui.LoadingDialog

abstract class BaseFragment<P : BasePersenter<*>> : Fragment() {
    protected var mPersenter: P? = null
    protected abstract fun initPersenter()
    protected abstract fun initLayout(): Int
    protected var mView: View? = null
    protected abstract fun initViews(view: View)
    protected var mLoading: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initPersenter()
        mView = inflater.inflate(initLayout(), container, false)
        mLoading = LoadingDialog(activity)
        mLoading!!.setCanceledOnTouchOutside(false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPersenter!!.cannelRequest()
    }
}