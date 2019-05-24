package com.hechuang.labeego.view.activity

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.CardBagBean

interface ICardBagView : BaseView {
    /**
     * 获取我的卡包数据成功
     */
    fun getcarddatasuccess(beandata: CardBagBean)

    /**
     * 获取我的卡包数据失败
     */
    fun getcardtataerror(msg: String)
}