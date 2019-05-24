package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.Allianceshop_classify_bean

interface IShopClassifyView :BaseView {

    fun  getdata_success(allianceshop_classify_bean: Allianceshop_classify_bean)

    fun getdata_error(string: String)
}