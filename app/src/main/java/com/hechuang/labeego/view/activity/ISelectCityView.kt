package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.SelectCityBean

/**
 * 城市选择
 * Created by Android_xu on 2018/3/24.
 */

interface ISelectCityView : BaseView {
    fun getprovinceok(selectCityBean: SelectCityBean.DataBean)
    fun getcityok(selectCityBean: SelectCityBean.DataBean)
    fun getcountok(selectCityBean: SelectCityBean.DataBean)
}
