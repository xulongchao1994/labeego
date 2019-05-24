package com.hechuang.labeego.view.fragment

import com.hechuang.labeego.base.BaseView
import com.hechuang.labeego.bean.Allianceshop_BannerBean
import com.hechuang.labeego.bean.Allianceshop_Classify_ShopBean
import com.hechuang.labeego.bean.Allianceshop_shop_bean

interface IAllianceShopFragmentView : BaseView {
    fun getallianceshoperror(string: String, int: Int)
    fun getclassifysuccess(alllianceshopbannerbean: Allianceshop_BannerBean)
    fun getbannersuccess(alllianceshopbannerbean: Allianceshop_BannerBean)
    fun getguanggao(alllianceshopbannerbean: Allianceshop_BannerBean)
    fun getscarebuying(allianceshop_shop_bean: Allianceshop_shop_bean)
    fun getsuper(allianceshop_shop_bean: Allianceshop_shop_bean)
    fun gethigh(allianceshop_shop_bean: Allianceshop_shop_bean)
    fun getlike(allianceshop_shop_bean: Allianceshop_shop_bean)
    fun getshopclassify(allianceshop_Classify_ShopBean: Allianceshop_Classify_ShopBean)
}