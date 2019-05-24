package com.hechuang.labeego.bean;

import java.util.List;

public class CardBagBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":[{"img":"http://lafeng.99xyg.com/Public/static/images/personal/25.png","title":"卡","num":"2张","info":[{"money":"4468889.70","img":"http://lafeng.99xyg.com/public/static/labeego/images/cardbag/hechuang.png","url":"http://lafeng.99xyg.com/index.php/Home/Cardbag/card_show/parmar/he/id/9611230","title":"和币"},{"money":"4466771.30","img":"http://lafeng.99xyg.com/public/static/labeego/images/cardbag/beemoney.png","url":"http://lafeng.99xyg.com/index.php/Home/Cardbag/card_show/parmar/bee/id/9611230","title":"蜂币"}]}]}]}
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
         * list : [{"img":"http://lafeng.99xyg.com/Public/static/images/personal/25.png","title":"卡","num":"2张","info":[{"money":"4468889.70","img":"http://lafeng.99xyg.com/public/static/labeego/images/cardbag/hechuang.png","url":"http://lafeng.99xyg.com/index.php/Home/Cardbag/card_show/parmar/he/id/9611230","title":"和币"},{"money":"4466771.30","img":"http://lafeng.99xyg.com/public/static/labeego/images/cardbag/beemoney.png","url":"http://lafeng.99xyg.com/index.php/Home/Cardbag/card_show/parmar/bee/id/9611230","title":"蜂币"}]},{"img":"http://lafeng.99xyg.com/Public/static/images/personal/26.png","title":"券","num":1,"info":[{"imgpath":"http://lafeng.99xyg.com/Upload/CardImg/5b10f1b01892f.png","money":"2145.00","title":"零购券","url":"http://lafeng.99xyg.com/index.php/Home/Cardbag/coupon_show/id/191/xid/2145.00"}]}]
         */
        private int status;
        private String message;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * img : http://lafeng.99xyg.com/Public/static/images/personal/25.png
             * title : 卡
             * num : 2张
             * info : [{"money":"4468889.70","img":"http://lafeng.99xyg.com/public/static/labeego/images/cardbag/hechuang.png","url":"http://lafeng.99xyg.com/index.php/Home/Cardbag/card_show/parmar/he/id/9611230","title":"和币"},{"money":"4466771.30","img":"http://lafeng.99xyg.com/public/static/labeego/images/cardbag/beemoney.png","url":"http://lafeng.99xyg.com/index.php/Home/Cardbag/card_show/parmar/bee/id/9611230","title":"蜂币"}]
             */

            private String img;
            private String title;
            private String num;
            private List<InfoBean> info;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public List<InfoBean> getInfo() {
                return info;
            }

            public void setInfo(List<InfoBean> info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * money : 4468889.70
                 * img : http://lafeng.99xyg.com/public/static/labeego/images/cardbag/hechuang.png
                 * url : http://lafeng.99xyg.com/index.php/Home/Cardbag/card_show/parmar/he/id/9611230
                 * title : 和币
                 */

                private String money;
                private String img;
                private String url;
                private String title;

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                @Override
                public String toString() {
                    return "InfoBean{" +
                            "money='" + money + '\'' +
                            ", img='" + img + '\'' +
                            ", url='" + url + '\'' +
                            ", title='" + title + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "img='" + img + '\'' +
                        ", title='" + title + '\'' +
                        ", num='" + num + '\'' +
                        ", info=" + info +
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
}
