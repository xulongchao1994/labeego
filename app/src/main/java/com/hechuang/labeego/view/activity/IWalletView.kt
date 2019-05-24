package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.WalletBean

interface IWalletView: BaseView {
    /**
     * 获取数据成功
     */
    fun getdatasuccess(walletbean:WalletBean)
    /**
     * 获取数据失败
     */
    fun getdataerror(msg:String)
}