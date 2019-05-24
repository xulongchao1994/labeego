package com.hechuang.labeego.bean;

import java.util.List;

public class MineBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":{"setting":{"avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/4.png","url":""},"name":"闹闹(9611230)","avatarurl":"http://lafeng.99xyg.com/Public/static/images/avatar_default.jpg","usertype":"http://lafeng.99xyg.com/Public/static/images/personal/3.png","column":[{"title":"我的蜂蜜","url":"http://lafeng.99xyg.com/index.php/Home/User/honey","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/11.png"},{"title":"我的蜂浆","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/12.png"},{"title":"我的蜂力","url":"http://lafeng.99xyg.com/index.php/Home/User/beepower","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/13.png"},{"title":"我的啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/labeeamount","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/14.png"}],"bag_list":[{"title":"我的卡包","url":"http://lafeng.99xyg.com/index.php/Home/User/cardbag"},{"title":"我的蜂巢","url":"http://lafeng.99xyg.com/index.php/Home/User/hive"}],"most_order":{"title":"查看更多订单","url":""},"order_type":[{"title":"待付款","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/1","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/15.png"},{"title":"待发货","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/2","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/16.png"},{"title":"待收货","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/3","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/17.png"},{"title":"已完成","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/4","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/18.png"}]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 1
         * message : 数据获取成功!
         * list : {"setting":{"avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/4.png","url":""},"name":"闹闹(9611230)","avatarurl":"http://lafeng.99xyg.com/Public/static/images/avatar_default.jpg","usertype":"http://lafeng.99xyg.com/Public/static/images/personal/3.png","column":[{"title":"我的蜂蜜","url":"http://lafeng.99xyg.com/index.php/Home/User/honey","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/11.png"},{"title":"我的蜂浆","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/12.png"},{"title":"我的蜂力","url":"http://lafeng.99xyg.com/index.php/Home/User/beepower","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/13.png"},{"title":"我的啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/labeeamount","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/14.png"}],"bag_list":[{"title":"我的卡包","url":"http://lafeng.99xyg.com/index.php/Home/User/cardbag"},{"title":"我的蜂巢","url":"http://lafeng.99xyg.com/index.php/Home/User/hive"}],"most_order":{"title":"查看更多订单","url":""},"order_type":[{"title":"待付款","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/1","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/15.png"},{"title":"待发货","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/2","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/16.png"},{"title":"待收货","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/3","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/17.png"},{"title":"已完成","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/4","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/18.png"}]}
         */

        private int status;
        private String message;
        private ListBean list;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * setting : {"avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/4.png","url":""}
             * name : 闹闹(9611230)
             * avatarurl : http://lafeng.99xyg.com/Public/static/images/avatar_default.jpg
             * usertype : http://lafeng.99xyg.com/Public/static/images/personal/3.png
             * column : [{"title":"我的蜂蜜","url":"http://lafeng.99xyg.com/index.php/Home/User/honey","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/11.png"},{"title":"我的蜂浆","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/12.png"},{"title":"我的蜂力","url":"http://lafeng.99xyg.com/index.php/Home/User/beepower","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/13.png"},{"title":"我的啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/labeeamount","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/14.png"}]
             * bag_list : [{"title":"我的卡包","url":"http://lafeng.99xyg.com/index.php/Home/User/cardbag"},{"title":"我的蜂巢","url":"http://lafeng.99xyg.com/index.php/Home/User/hive"}]
             * most_order : {"title":"查看更多订单","url":""}
             * order_type : [{"title":"待付款","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/1","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/15.png"},{"title":"待发货","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/2","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/16.png"},{"title":"待收货","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/3","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/17.png"},{"title":"已完成","url":"http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/4","avatarurl":"http://lafeng.99xyg.com/Public/static/images/personal/18.png"}]
             */

            private SettingBean setting;
            private String name;
            private String avatarurl;
            private String usertype;
            private MostOrderBean most_order;
            private List<ColumnBean> column;
            private List<BagListBean> bag_list;
            private List<OrderTypeBean> order_type;
            private List<Order_ListBean> order_list;

            public void setOrder_list(List<Order_ListBean> order_list) {
                this.order_list = order_list;
            }

            public List<Order_ListBean> getOrder_list() {
                return order_list;
            }

            public SettingBean getSetting() {
                return setting;
            }

            public void setSetting(SettingBean setting) {
                this.setting = setting;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAvatarurl() {
                return avatarurl;
            }

            public void setAvatarurl(String avatarurl) {
                this.avatarurl = avatarurl;
            }

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public MostOrderBean getMost_order() {
                return most_order;
            }

            public void setMost_order(MostOrderBean most_order) {
                this.most_order = most_order;
            }

            public List<ColumnBean> getColumn() {
                return column;
            }

            public void setColumn(List<ColumnBean> column) {
                this.column = column;
            }

            public List<BagListBean> getBag_list() {
                return bag_list;
            }

            public void setBag_list(List<BagListBean> bag_list) {
                this.bag_list = bag_list;
            }

            public List<OrderTypeBean> getOrder_type() {
                return order_type;
            }

            public void setOrder_type(List<OrderTypeBean> order_type) {
                this.order_type = order_type;
            }

            public static class SettingBean {
                /**
                 * avatarurl : http://lafeng.99xyg.com/Public/static/images/personal/4.png
                 * url :
                 */

                private String avatarurl;
                private String url;

                public String getAvatarurl() {
                    return avatarurl;
                }

                public void setAvatarurl(String avatarurl) {
                    this.avatarurl = avatarurl;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class Order_ListBean {
                private String url;
                private String title;

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public String getTitle() {
                    return title;
                }
            }

            public static class MostOrderBean {
                /**
                 * title : 查看更多订单
                 * url :
                 */

                private String title;
                private String url;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                @Override
                public String toString() {
                    return "MostOrderBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }
            }

            public static class ColumnBean {
                /**
                 * title : 我的蜂蜜
                 * url : http://lafeng.99xyg.com/index.php/Home/User/honey
                 * avatarurl : http://lafeng.99xyg.com/Public/static/images/personal/11.png
                 */

                private String title;
                private String url;
                private String avatarurl;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getAvatarurl() {
                    return avatarurl;
                }

                public void setAvatarurl(String avatarurl) {
                    this.avatarurl = avatarurl;
                }

                @Override
                public String toString() {
                    return "ColumnBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            ", avatarurl='" + avatarurl + '\'' +
                            '}';
                }
            }

            public static class BagListBean {
                /**
                 * title : 我的卡包
                 * url : http://lafeng.99xyg.com/index.php/Home/User/cardbag
                 */

                private String title;
                private String url;
                private String avatarurl;

                public void setAvatarurl(String avatarurl) {
                    this.avatarurl = avatarurl;
                }

                public String getAvatarurl() {
                    return avatarurl;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                @Override
                public String toString() {
                    return "BagListBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }
            }

            public static class OrderTypeBean {
                /**
                 * title : 待付款
                 * url : http://lafeng.99xyg.com/index.php/Home/Order/index/Tradingclass/1
                 * avatarurl : http://lafeng.99xyg.com/Public/static/images/personal/15.png
                 */

                private String title;
                private String url;
                private String avatarurl;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getAvatarurl() {
                    return avatarurl;
                }

                public void setAvatarurl(String avatarurl) {
                    this.avatarurl = avatarurl;
                }

                @Override
                public String toString() {
                    return "OrderTypeBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            ", avatarurl='" + avatarurl + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "setting=" + setting +
                        ", name='" + name + '\'' +
                        ", avatarurl='" + avatarurl + '\'' +
                        ", usertype='" + usertype + '\'' +
                        ", most_order=" + most_order +
                        ", column=" + column +
                        ", bag_list=" + bag_list +
                        ", order_type=" + order_type +
                        ", order_list=" + order_list +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MineBean{" +
                "data=" + data +
                '}';
    }
}
