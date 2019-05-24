package com.hechuang.labeego.bean

class GoodsHomeBannerBean(
        /**
         * data : {"status":1,"message":"加载成功！","list":{"banner":[{"style":0,"url":"","urlid":"","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"style":1,"url":"","urlid":"","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"style":2,"url":"","urlid":"","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"}],"icon":[{"categoryid":1,"name":"家居家纺","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":10,"name":"美食","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":19,"name":"保健","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":24,"name":"数码家电","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":29,"name":"服装","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":35,"name":"珠宝","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":62,"name":"美妆","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":71,"name":"家居日化","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":9998,"name":"店铺代金/优惠券","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":0,"name":"全部","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"}]}}
         */

        var data: DataBean?) {

    class DataBean(
            /**
             * status : 1
             * message : 加载成功！
             * list : {"banner":[{"style":0,"url":"","urlid":"","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"style":1,"url":"","urlid":"","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"style":2,"url":"","urlid":"","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"}],"icon":[{"categoryid":1,"name":"家居家纺","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":10,"name":"美食","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":19,"name":"保健","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":24,"name":"数码家电","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":29,"name":"服装","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":35,"name":"珠宝","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":62,"name":"美妆","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":71,"name":"家居日化","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":9998,"name":"店铺代金/优惠券","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"},{"categoryid":0,"name":"全部","imgs":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg"}]}
             */

            var status: String?, var message: String?, var list: ListBean?) {

        class ListBean(var banner: List<BannerBean>?, var icon: List<IconBean>?) {

            class BannerBean(
                    /**
                     * style : 0
                     * url :
                     * urlid :
                     * imgs : http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg
                     */

                    var style: String?, var url: String?, var id: String?, var imgs: String?)

            class IconBean(
                    /**
                     * categoryid : 1
                     * name : 家居家纺
                     * imgs : http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152775511051380.jpg
                     */

                    var categoryid: String?, var name: String?, var imgs: String?)
        }
    }
}
