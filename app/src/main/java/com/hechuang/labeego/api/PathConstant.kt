package com.hechuang.labeego.api

/**
 */
object PathConstant {
    val APP_NAME = "/labeego"
    @JvmField
    val APP_NAME_ZH = "labeego"

//    @JvmField
//    var app_host = "http://www.labeego.com"
//    var app_host = "http://lafeng.99xyg.com/"
    /**
     * apk下载路径
     * http://apk.hshc618.com/labeego.apk
     */
    val APP_DOWNLOAD_URL = "http://apk.hshc618.com/labeego.apk"
    /**
     * 图片目录
     */
    @JvmField
    val IMG_DIR = "$APP_NAME/img/"
    /**
     * 安装包目录
     */
    @JvmField
    val APK_DIR = "$APP_NAME/apk/"
    /**
     * 其他文件目录
     */
    @JvmField
    val TEMP_DIR = "$APP_NAME/temp/"
    /**
     * 用户需要加载的链接
     */
    var USER_WEB = ""
    //找回密码
    @JvmField
    var FAND_PSW = ApiFactify.WEB_HOST + "index.php/Home/FreeLogin/forgetPsw.html"
    //公共空间
    @JvmField
    var HC_REGISTER = ApiFactify.WEB_HOST + "index.php/Home/FreeLogin/HcUser_reg"
    //注册
    @JvmField
    var REGISTER = ApiFactify.WEB_HOST + "index.php/Home/FreeLogin/reg"


    /**
     * 获取城市列表（省
     */
    @JvmField
    val GET_PROVINCE = ApiFactify.WEB_HOST + "Api/Area/Area.php?act=province"

    /**
     * 获取城市列表(市
     */
    @JvmField
    val GET_CITY = ApiFactify.WEB_HOST + "Api/Area/Area.php?act=city"
    /**
     * 获取城市列表（区
     */
    @JvmField
    val GET_COUNT = ApiFactify.WEB_HOST + "Api/Area/Area.php?act=country"
    /**
     * 待付款订单
     */
    @JvmField
    var ORDER_DAIFUKUAN = ApiFactify.WEB_HOST + "index.php/Home/Order/index/Tradingclass/1"
    /**
     * 待收货订单
     */
    @JvmField
    var ORDER_DAISHOUHUO = ApiFactify.WEB_HOST + "index.php/Home/Order/index/Tradingclass/3"
    /**
     * 待发货订单
     */
    @JvmField
    var ORDER_DAIFAHUO = ApiFactify.WEB_HOST + "index.php/Home/Order/index/Tradingclass/2"
    /**
     * 已完成
     */
    @JvmField
    var ORDER_YIWANCHENG = ApiFactify.WEB_HOST + "index.php/Home/Order/index/Tradingclass/4"
    /**
     * 众筹订单
     */
    @JvmField
    var ORDER_ZHONGCHOU = ApiFactify.WEB_HOST + "index.php/Home/Order/index/Tradingclass/5"
    /**
     * 我的蜂力页面
     */
    @JvmField
    var MY_HOENY = ApiFactify.WEB_HOST + "index.php/Home/User/labeeamount"
    /**
     * 我的蜂蜜页面
     */
    @JvmField
    var MY_HOENY_2 = ApiFactify.WEB_HOST + "index.php/Home/User/apiserum"

    /**
     * 商家订单页面
     */
    @JvmField
    var STOREORDERLIST = ApiFactify.WEB_HOST + "index.php/Home/Unorder/index"
}
