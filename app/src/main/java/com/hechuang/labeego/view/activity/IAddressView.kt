package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.Address_bean
import com.hechuang.labeego.bean.Address_morenBean

interface IAddressView :BaseView {
    fun getaddress_seccess(address_bean: Address_bean)
    /***
     * 设置默认地址成功
     */
    fun setaddressmoren_success(bean:Address_morenBean,position:Int)

    /**
     * 设置默认失败
     */
    fun setaddressmoren_error(msg:String)
    /**
     * 删除地址成功
     */
    fun deleteaddress_success(bean: Address_morenBean,position: Int)

    /**
     * 删除失败
     */
    fun deleteaddress_error(msg:String)
}