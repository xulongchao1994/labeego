package com.hechuang.labeego.api

import com.hechuang.labeego.bean.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface Api {
    //更新
    @POST("Public/Appload/version.php")
    fun getapp_v(): Observable<V_bean>

    //登录
    @FormUrlEncoded
    @POST("Api/Login/Login.php?act=login")
    fun UserLogin(@Field("username") username: String
                  , @Field("password") password: String): Observable<Login_Bean>

    /**
     * 强制修改密码
     * @param userid
     * @param token
     * @param mobile
     * @param name
     * @param pwd`
     * @param dbipwd
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Login/pap.php")
    fun Post_modifytowpwd(@Field("userid") userid: String,
                          @Field("token") token: String,
                          @Field("mobile") mobile: String,
//                          @Field("name") name: String,
                          @Field("pwd") pwd: String,
                          @Field("dbipwd") dbipwd: String): Observable<ForceModifyPwdBean>

    /**
     * 商品列表
     */
    @FormUrlEncoded
    @POST("Api/Product/goodslist.php")
    fun goodslist(@Field("sort") sort: String
                  , @Field("Page") Page: String
                  , @Field("keyword") keyword: String
                  , @Field("categoryid") categoryid: String
    ): Observable<GoodsListBean>

    /**
     * 商家商品列表
     * 参数：
     *supid：商家id
     *sort：当值为1时价格从高到低倒序，值为2时价格正序，默认按商品id倒序
     *Page:分页
     *keyword:商品搜索时关键字
     *sale：按销量排序  当值为1时销量从高到低倒序，值为2时价格正序
     */
    @FormUrlEncoded
    @POST("Api/Product/supgoodslist.php")
    fun storegoodslist(@Field("sort") sort: String
                       , @Field("Page") Page: String
                       , @Field("keyword") keyword: String
                       , @Field("supid") supid: String
                       , @Field("sale") sale: String
    ): Observable<GoodsListBean>

    /**
     * 商品详情
     */
    @FormUrlEncoded
//    @POST("Api/Product/prodetail.php?act=detail")
    @POST("Api/comment/gooddetails.php")
    fun getgoodsinfo(@Field("pid") pid: String): Observable<GoodsInfoBean>

    /**
     * 加入购物车和立即购买
     */
    @FormUrlEncoded
    @POST("Api/ShopCart/index.php?act=index")
    fun buy(@Field("num") num: String
            , @Field("proid") proid: String
            , @Field("username") username: String
            , @Field("token") token: String
            , @Field("styleid") styleid: String
            , @Field("pnum") pnum: String
    ): Observable<Goodsinfo_buy_bean>

    /**
     * 商品列表首页轮播图和分类
     */
    @POST("Api/Product/goodsindex.php")
    fun post_goodshome_banner(): Observable<GoodsHomeBannerBean>

    /***
     * 购物车列表
     */
    @FormUrlEncoded
    @POST("Api/ShopCart/shopcartlist.php?act=shopcartlist")
    fun shoppingcar(@Field("username") username: String,
                    @Field("token") token: String): Observable<ShoppingCarBean>

    /***
     * 购物车商品规格
     */
    @FormUrlEncoded
    @POST("Api/ShopCart/shopcartlist.php?act=style")
    fun shoppingcar_guige(@Field("username") username: String, @Field("token") token: String, @Field("ProId") ProId: String): Observable<Shoppingcar_Guige_bean>

    /**
     * 购物车编辑
     */
    @FormUrlEncoded
    @POST("Api/ShopCart/shopcartlist.php?act=edit")
    fun bianji(@Field("username") username: String, @Field("token") token: String, @Field("ProNum") ProNum: String, @Field("StyleId") StyleId: String
               , @Field("shopcartid") shopcartid: String, @Field("delid") delid: String, @Field("price") price: String): Observable<Shoppingcar_Bianji_bean>


    /**
     * 立即购买是的确认订单页面
     * num = "2"
     */
    @FormUrlEncoded
    @POST("Api/Order/orderindex.php?act=orderindex")
    fun confirmanroder_shoppingcar(@Field("num") num: String, @Field("username") username: String,
                                   @Field("token") token: String, @Field("shopcartid") shopcartid: String): Observable<Confirmanorder_Shoppingcar_Bean>

    /**
     * 提交订单
     */
    @FormUrlEncoded
    @POST("Api/Order/ordercommit.php?act=ordercommit")
    fun confirmanorder_tijia(@Field("addressid") addresid: String, @Field("num") num: String, @Field("username") username: String,
                             @Field("token") token: String, @Field("shopcartid") shopcartid: String, @Field("order_token") order_token: String
                             , @Field("message") message: String
    ): Observable<Order_tijiao>

    /**
     * 订单支付
     */
    @FormUrlEncoded
    @POST("Api/Order/orderpay.php?act=orderpay")
    fun order_turepay(@Field("username") username: String,
                      @Field("token") token: String,
                      @Field("orderbianhao") orderbianhao: String
    ): Observable<Order_ture_list_bean>

    /***
     * 收货地址列表
     */
    @FormUrlEncoded
    @POST("Api/Order/addresslist.php?act=addresslist")
    fun getaddress_user(@Field("username") username: String,
                        @Field("token") token: String,
                        @Field("Page") Page: String): Observable<Address_bean>

    /**
     * 添加收货地址
     */
    @FormUrlEncoded
    @POST("Api/Order/addresslist.php?act=addressadd")
    fun post_address(@Field("username") username: String,
                     @Field("token") token: String,
                     @Field("truename") truename: String,
                     @Field("mobile") mobile: String
                     , @Field("Province") Province: String
                     , @Field("City") City: String
                     , @Field("County") County: String
                     , @Field("addressdetail") addressdetail: String,
                     @Field("isdef") isdef: String): Observable<NewAddress_seccess_bean>

    /***
     * 确认支付
     */
    @FormUrlEncoded
    @POST("Api/Order/orderfukuan.php?act=orderfukuan")
    fun post_order(@Field("username") username: String,
                   @Field("token") token: String,
                   @Field("orderbianhao") orderbianhao: String,
                   @Field("pay_token") pay_token: String,
                   @Field("serviceid") serviceid: String,
                   @Field("vmoney") isxiaofeiquan: String,
                   @Field("ordertype") ordertype: String
    ): Observable<Order_true_bean>

    /**
     * 订单支付页面查询服务人接口
     */
    @FormUrlEncoded
    @POST("Api/Order/checkservice.php?act=checkservice")
    fun ordertrue_fuwuren(@Field("serviceid") serviceid: String): Observable<Orderture_fuwuren>

    /**
     * 购物车数量
     */
    @FormUrlEncoded
    @POST("Api/ShopCart/GetNum.php?act=num")
    fun getshoppingcarnumber(@Field("username") username: String,
                             @Field("token") token: String): Observable<ShoppingCarNumberBean>


    /**
     * 订单列表
     */
    @FormUrlEncoded
    @POST("Api/Order/orderlist.php")
    fun getallorderlist(
            @Field("num") num: String, @Field("username") username: String,
            @Field("token") token: String, @Field("Page") Page: String,
            @Field("orderstatus") orderstatus: String

    ): Observable<AllorderListBean>

    /**
     * 订单详情
     */
    @FormUrlEncoded
    @POST("Api/Order/orderdetail.php")
    fun getorderinfo(@Field("username") username: String,
                     @Field("token") token: String, @Field("orderid") orderid: String): Observable<OrderInfoBean>

    /**
     * 确认收货
     */
    @FormUrlEncoded
    @POST("Api/Order/orderreceipt.php")
    fun postshouhuo(@Field("username") username: String,
                    @Field("token") token: String, @Field("orderid") orderid: String): Observable<Order_shouhuo_Bean>


    /**
     * 订单搜索
     */
    @FormUrlEncoded
    @POST("Api/Order/ordercheck.php")
    fun searchorder(@Field("username") username: String,
                    @Field("token") token: String
                    , @Field("Page") Page: String
                    , @Field("orderid") orderid: String
                    , @Field("proname") proname: String
    ): Observable<SearchOrderBean>

    /**
     * 设置默认收货地址
     */
    @FormUrlEncoded
    @POST("Api/Order/addresslist.php?act=addressdef")
    fun setmorenaddress(@Field("username") username: String,
                        @Field("token") token: String
                        , @Field("addressid") addressid: String): Observable<Address_morenBean>

    /**
     * 获取收货地址详情
     */
    @FormUrlEncoded
    @POST("Api/Order/addressedit.php?act=addressedit")
    fun getaddressinfo(@Field("username") username: String,
                       @Field("token") token: String
                       , @Field("addressid") addressid: String): Observable<AddressInfoBean>


    /**
     * 删除地址功能
     */
    @FormUrlEncoded
    @POST("Api/Order/addressedit.php?act=addressdel")
    fun deleteaddress(@Field("username") username: String,
                      @Field("token") token: String
                      , @Field("addressid") addressid: String): Observable<Address_morenBean>

    /**
     * 编辑地址功能
     */
    @FormUrlEncoded
    @POST("Api/Order/addressedit.php?act=addressmodify")
    fun binajiaddress(
            @Field("addressid") addressid: String,
            @Field("username") username: String,
            @Field("token") token: String,
            @Field("truename") truename: String,
            @Field("mobile") mobile: String,
            @Field("Province") Province: String,
            @Field("City") City: String,
            @Field("County") County: String,
            @Field("addressdetail") addressdetail: String,
            @Field("isdef") isdef: String
    ): Observable<Address_morenBean>

    /**
     * 登录页面返回的数据，判断公共空间，进社区是否显示
     */
    @POST("Api/Index/index.php?act=index")
    fun loginview(): Observable<LoginviewBean>

    /**
     * 评论页面商品信息获取
     */
    @FormUrlEncoded
    @POST("Api/comment/commentgrade.php")
    fun comment_getgoodsdata(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("innerorderid") innerorderid: String): Observable<Commentbean>

    /**
     * 添加评论
     *
     * 接收参数	字段类型	备注
    userid	string	用户id
    token	string	用户token
    proid	Json --{"json":[ 1,2]}	商品id
    grade	Json	商品评分
    comment	Json	商品评论内容
    anonymous	Json	是否匿名参数
    innerorderid	string	订单编号
     */
    @FormUrlEncoded
    @POST("Api/comment/commentadd.php")
    fun post_comment(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("proid") proid: String,
            @Field("grade") grade: String,
            @Field("comment") comment: String,
            @Field("anonymous") anonymous: String,
            @Field("innerorderid") innerorderid: String): Observable<CommentAddBean>

    /**
     * 评价列表
     */
    @FormUrlEncoded
    @POST("Api/comment/commentlist.php")
    fun getcommentlistdata(@Field("proid") proid: String,
                           @Field("Page") Page: String
    ): Observable<CommentListBean>

    /**
     * 评论详情
     */
    @FormUrlEncoded
    @POST("Api/comment/commentdetails.php")
    fun getcommentinfodata(@Field("id") Id: String): Observable<CommentinfoBean>


    /**
     * 追加评论
     */
    @FormUrlEncoded
    @POST("Api/comment/commentreadd.php")
    fun commenginfo_zhuijia(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("proid") proid: String,
            @Field("pid") pid: String,
            @Field("p_userid") p_userid: String,
            @Field("comment") comment: String): Observable<CommentAddBean>

    /**
     * 删除评论
     */
    @FormUrlEncoded
    @POST("Api/comment/commentdel.php")
    fun delete_pingjia(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("id") id: String): Observable<CommentAddBean>

    /**
     * 个人中心
     */
    @FormUrlEncoded
    @POST("Api/index/personal.php")
    fun getminedata(
            @Field("userid") username: String,
            @Field("token") token: String): Observable<MineBean>

    /**
     * 我的蜂浆
     */
    @FormUrlEncoded
    @POST("Api/index/apiserum.php")
    fun getmyhoeny(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("page") page: String): Observable<MyHoneyBean>

    /**
     * 我的蜂蜜
     */
    @FormUrlEncoded
    @POST("Api/index/honey.php")
    fun gethoeny(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("page") page: String): Observable<HoenyBean>

    /**
     *  众筹订单
     */
    @FormUrlEncoded
    @POST("Api/Order/zmainlist.php")
    fun gettheraisedata(
            @Field("username") username: String,
            @Field("token") token: String,
            @Field("page") page: String,
            @Field("type") type: String): Observable<TheRaiseBean>

    /**
     * 获取蜂力数据（
     * page  页码
     * type 1表示蜂王2小蜜蜂3众筹
     */
    @FormUrlEncoded
    @POST("Api/index/integral.php")
    fun getbeedata(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("page") page: String,
            @Field("type") type: String): Observable<HoenyBean>

    /**
     * 获取个人信息接口
     */
    @FormUrlEncoded
    @POST("Api/index/personal_info.php")
    fun getuserinfo(
            @Field("userid") username: String,
            @Field("token") token: String): Observable<UserInfoBean>

    /**
     * 修改用户名接口
     */
    @FormUrlEncoded
    @POST("Api/index/change_truename.php")
    fun setnamepost(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("truename") truename: String): Observable<SetNameBean>

    /**
     * 设置用户密码
     *userid	  	用户名
     *token	       	用户token
     *pwd	       	用户密码
     *new_pwd	  	新密码
     *new_repwd	   	确认新密码
     */
    @FormUrlEncoded
    @POST("Api/index/change_pwd.php")
    fun setpsw(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("pwd") pwd: String,
            @Field("new_pwd") new_pwd: String,
            @Field("new_repwd") new_repwd: String): Observable<SetNameBean>

    /**
     * 获取蜂力详情
     */
    @FormUrlEncoded
    @POST("Api/index/record_show.php")
    fun getbeeinfodata(
            @Field("userid") username: String,
            @Field("token") token: String,
            @Field("type") type: String,
            @Field("id") id: String): Observable<BeeForceInfoBean>

    /**
     * 我的钱包
     */
    @FormUrlEncoded
    @POST("Api/index/labeeamount.php")
    fun getwalletdata(
            @Field("userid") username: String,
            @Field("token") token: String): Observable<WalletBean>

    /**
     * 我的卡包
     */
    @FormUrlEncoded
    @POST("Api/index/cardbag.php")
    fun getcardbagdata(
            @Field("userid") username: String,
            @Field("token") token: String): Observable<CardBagBean>


    /**
     * 获取验证码接口
     */
    @FormUrlEncoded
    @POST("Api/Home/vcode.php")
    fun post_authCode(@Field("mobile") mobile: String): Observable<AuthCodeBean>


    /**
     * 验证码登录成功
     */
    @FormUrlEncoded
    @POST("Api/Home/logon.php")
    fun post_phonelogin_success(@Field("token") token: String, @Field("userid") userid: String): Observable<PhoneSuccessBean>


    /**
     * 商家首页轮播图
     */
    @POST("Api/Shopping/slideshow.php")
    fun post_allianceshop_banner(): Observable<Allianceshop_BannerBean>

    /**
     * 商家首页分类信息
     */
    @POST("Api/Shopping/classify.php")
    fun post_allianceshop_classify(): Observable<Allianceshop_BannerBean>

    /**
     * 商家广告图
     */
    @FormUrlEncoded
    @POST("Api/Shopping/advertising.php")
    fun post_allianceshop_guanggao(@Field("userid") userid: String, @Field("token") token: String): Observable<Allianceshop_BannerBean>

    /**
     * 商家首页大牌甄选
     */
    @POST("Api/Shopping/big_brand.php")
    fun post_allianceshop_super(): Observable<Allianceshop_shop_bean>

    /**
     * 商家首页人气爆款
     */
    @POST("Api/Shopping/popularity.php")
    fun post_allianceshop_high(): Observable<Allianceshop_shop_bean>

    /**
     * 商家首页猜你喜欢
     */
    @POST("Api/Shopping/fond.php")
    fun post_allianceshop_like(): Observable<Allianceshop_shop_bean>

    /**
     * 商家分类页面
     */
    @POST("Api/Shopping/subordinate_classify.php")
    fun post_shopclassify(): Observable<Allianceshop_classify_bean>

    /**
     * 商家详情页面列表
     */
    @FormUrlEncoded
    @POST("Api/Shopping/shops_particulars.php")
    fun getshopinfolist(@Field("id") id: String, @Field("page") page: String): Observable<ShopInfoBean>


    /**
     * 商家商品列表
     */
    @FormUrlEncoded
    @POST("Api/Shopping/product_list.php")
    fun getshoplist(@Field("page") page: String,
                    @Field("classify") classify: String, @Field("seek") seek: String, @Field("prefecture") prefecture: String)
            : Observable<ShoplistBean>

    /**
     * 商家商品详情
     */
    @FormUrlEncoded
    @POST("Api/Shopping/product_details.php")
    fun getshopgoodinfo(@Field("proid") proid: String): Observable<ShopGoodInfoBean>

    /*
    商家分类页面
     */
    @FormUrlEncoded
    @POST("Api/Shopping/prefecture.php")
    fun getallianceshopclassify(@Field("userid") userid: String, @Field("token") token: String): Observable<Allianceshop_Classify_ShopBean>

    /**
     * 商家商品详情加入购物车和立即购买
     */
    @FormUrlEncoded
    @POST("Api/Purchase/index.php")
    fun shopgoodsinfo_addshopping(@Field("proid") proid: String
                                  , @Field("userid") username: String
                                  , @Field("token") token: String
                                  , @Field("styleid") styleid: String
                                  , @Field("num") num: String
                                  , @Field("pronum") pnum: String): Observable<StoreGoodsinfo_buy_bean>

    /**
     * 商家购物车列表
     */
    @FormUrlEncoded
    @POST("Api/Purchase/shopping_cart.php")
    fun GET_SHOPSHOPPING(@Field("userid") username: String
                         , @Field("token") token: String): Observable<StoreShoppingCarBean>

    /**
     * 提交订单页面信息
     */
    @FormUrlEncoded
    @POST("Api/Purchase/orderindex.php")
    fun GET_CONFIRMANORDERDATA(@Field("userid") username: String
                               , @Field("token") token: String,
                               @Field("styleid") styleid: String
                               , @Field("num") num: String): Observable<StoreConfirmanorder_Shoppingcar_Bean>

    /**
     * 提交订单
     */
    @FormUrlEncoded
    @POST("Api/Purchase/payment.php")
    fun POST_CONFIRMANORDER(@Field("userid") username: String
                            , @Field("token") token: String,
                            @Field("styleid") styleid: String
                            , @Field("buynow") buynow: String,
                            @Field("deduction") deduction: String,
                            @Field("message") message: String,
                            @Field("order_token") order_token: String

    ): Observable<Order_tijiao>

    @FormUrlEncoded
    @POST("Api/Purchase/pay.php")
    fun GET_ALIPAY(
            @Field("userid") username: String
            , @Field("token") token: String,
            @Field("orderid") orderid: String
    ): Observable<ConfirmanOrder_AlipayBean>

    /**
     * 商家购物车数量修改
     */
    @FormUrlEncoded
    @POST("Api/Purchase/modnum.php")
    fun STORESHOPPINGCAR_SHOPNUMBER(
            @Field("userid") username: String
            , @Field("token") token: String,
            @Field("styleid") styleid: String
            , @Field("pronum") pronum: String): Observable<StoreShoppingcar_shopingnumberBean>

    /**
     * 商家购物车商品删除
     */
    @FormUrlEncoded
    @POST("Api/Purchase/delpro.php")
    fun STORESHOPPINGCAR_DELETEGOODS(
            @Field("userid") username: String
            , @Field("token") token: String,
            @Field("styleid") styleid: String): Observable<StoreShoppingcar_shopingnumberBean>
}