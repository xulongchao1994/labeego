package com.hechuang.labeego.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hechuang.labeego.tools.ui.LoadingDialog

abstract class BaseViewpageFragment<P : BasePersenter<*>> : Fragment() {

    protected var mPersenter: P? = null
    protected abstract fun initPersenter()
    protected abstract fun initLayout(): Int
    protected var mView: View? = null
    protected abstract fun initViews()
    protected var mLoading: LoadingDialog? = null
    protected var isViewInitiated: Boolean = false
    protected var isVisibleToUser: Boolean = false
    protected var isDataInitiated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(initLayout(), container, false)
        mLoading = LoadingDialog(activity)
        mLoading!!.setCanceledOnTouchOutside(false)
        initPersenter()
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareFetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareFetchData()
    }

    abstract fun fetchData()

    fun prepareFetchData(): Boolean {
        return prepareFetchData(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPersenter!!.cannelRequest()
    }

    fun prepareFetchData(forceUpdate: Boolean): Boolean {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData()
            isDataInitiated = true
            return true
        }
        return false
    }
}