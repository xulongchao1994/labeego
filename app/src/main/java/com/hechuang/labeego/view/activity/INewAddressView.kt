package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.AddressInfoBean
import com.hechuang.labeego.bean.Address_morenBean
import com.hechuang.labeego.bean.NewAddress_seccess_bean

interface INewAddressView:BaseView {
    fun postaddress_seccess(postdata:NewAddress_seccess_bean)

    /**
     *获取地址信息成功
     */
    fun getaddressinfo_success(data:AddressInfoBean)
    /**
     * 获取地址失败
     */
    fun getaddressinfo_error(msg:String)

    /**
     * 修改地址成功
     */
    fun bianji_success(data:Address_morenBean)

    /**
     * 修改地址失败
     */
    fun bianji_error(msg:String)
}