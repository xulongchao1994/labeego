package com.hechuang.labeego.bean;

import java.util.List;

public class CommentinfoBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":{"good":{"stylename":"1坛封坛酒+3盒机油","Price":"10000.00","ProName":"啦蜂【1坛封坛酒+3盒机油】","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-18/152661512592045.jpg"},"comment_header":{"comment":"123","truename":"啦蜂","avatarurl":"http://lafeng.hetianpay.comhc2688_58b537157e4cd_716_big.jpg","id":"1","userid":"hc100001","time":"2018-07-20 16:24:04"},"comment_list":[{"comment":"111","truename":"","avatarurl":"","id":"2"},{"comment":"222","truename":"闹闹","avatarurl":"","id":"3"}]}}
     */

    private DataBean data;

    public CommentinfoBean(DataBean data) {
        this.data = data;
    }

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
         * list : {"good":{"stylename":"1坛封坛酒+3盒机油","Price":"10000.00","ProName":"啦蜂【1坛封坛酒+3盒机油】","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-18/152661512592045.jpg"},"comment_header":{"comment":"123","truename":"啦蜂","avatarurl":"http://lafeng.hetianpay.comhc2688_58b537157e4cd_716_big.jpg","id":"1","userid":"hc100001","time":"2018-07-20 16:24:04"},"comment_list":[{"comment":"111","truename":"","avatarurl":"","id":"2"},{"comment":"222","truename":"闹闹","avatarurl":"","id":"3"}]}
         */

        private int status;
        private String message;
        private ListBean list;

        public DataBean(int status, String message, ListBean list) {
            this.status = status;
            this.message = message;
            this.list = list;
        }

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
             * good : {"stylename":"1坛封坛酒+3盒机油","Price":"10000.00","ProName":"啦蜂【1坛封坛酒+3盒机油】","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-18/152661512592045.jpg"}
             * comment_header : {"comment":"123","truename":"啦蜂","avatarurl":"http://lafeng.hetianpay.comhc2688_58b537157e4cd_716_big.jpg","id":"1","userid":"hc100001","time":"2018-07-20 16:24:04"}
             * comment_list : [{"comment":"111","truename":"","avatarurl":"","id":"2"},{"comment":"222","truename":"闹闹","avatarurl":"","id":"3"}]
             */

            private GoodBean good;
            private CommentHeaderBean comment_header;
            private List<CommentListBean> comment_list;

            public ListBean(GoodBean good, CommentHeaderBean comment_header, List<CommentListBean> comment_list) {
                this.good = good;
                this.comment_header = comment_header;
                this.comment_list = comment_list;
            }

            public GoodBean getGood() {
                return good;
            }

            public void setGood(GoodBean good) {
                this.good = good;
            }

            public CommentHeaderBean getComment_header() {
                return comment_header;
            }

            public void setComment_header(CommentHeaderBean comment_header) {
                this.comment_header = comment_header;
            }

            public List<CommentListBean> getComment_list() {
                return comment_list;
            }

            public void setComment_list(List<CommentListBean> comment_list) {
                this.comment_list = comment_list;
            }

            public static class GoodBean {

                public GoodBean(String proid, String stylename, String price, String proName, String proImg, String integral) {
                    this.proid = proid;
                    this.stylename = stylename;
                    Price = price;
                    ProName = proName;
                    ProImg = proImg;
                    this.integral = integral;
                }

                /**
                 * stylename : 1坛封坛酒+3盒机油
                 * Price : 10000.00
                 * ProName : 啦蜂【1坛封坛酒+3盒机油】
                 * ProImg : http://lafeng.hetianpay.com/Upload/Product/2018-05-18/152661512592045.jpg
                 */
                private String proid;
                private String stylename;
                private String Price;
                private String ProName;
                private String ProImg;
                private String integral;

                public void setIntegral(String integral) {
                    this.integral = integral;
                }

                public String getIntegral() {
                    return integral;
                }

                public void setProid(String proid) {
                    this.proid = proid;
                }

                public String getProid() {
                    return proid;
                }

                public String getStylename() {
                    return stylename;
                }

                public void setStylename(String stylename) {
                    this.stylename = stylename;
                }

                public String getPrice() {
                    return Price;
                }

                public void setPrice(String Price) {
                    this.Price = Price;
                }

                public String getProName() {
                    return ProName;
                }

                public void setProName(String ProName) {
                    this.ProName = ProName;
                }

                public String getProImg() {
                    return ProImg;
                }

                public void setProImg(String ProImg) {
                    this.ProImg = ProImg;
                }
            }

            public static class CommentHeaderBean {
                public CommentHeaderBean(String comment, String truename, String avatarurl, String id, String userid, String time) {
                    this.comment = comment;
                    this.truename = truename;
                    this.avatarurl = avatarurl;
                    this.id = id;
                    this.userid = userid;
                    this.time = time;
                }

                /**
                 * comment : 123
                 * truename : 啦蜂
                 * avatarurl : http://lafeng.hetianpay.comhc2688_58b537157e4cd_716_big.jpg
                 * id : 1
                 * userid : hc100001
                 * time : 2018-07-20 16:24:04
                 */

                private String comment;
                private String truename;
                private String avatarurl;
                private String id;
                private String userid;
                private String time;

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getTruename() {
                    return truename;
                }

                public void setTruename(String truename) {
                    this.truename = truename;
                }

                public String getAvatarurl() {
                    return avatarurl;
                }

                public void setAvatarurl(String avatarurl) {
                    this.avatarurl = avatarurl;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }
            }

            public static class CommentListBean {
                /**
                 * comment : 111
                 * truename :
                 * avatarurl :
                 * id : 2
                 */
                private String stype;
                private String p_userid;
                private String comment;
                private String truename;
                private String avatarurl;
                private String id;
                private String userid;
                private String time;
                private String p_name;

                public CommentListBean(String stype, String p_userid, String comment, String truename, String avatarurl, String id, String userid, String time, String p_name) {
                    this.stype = stype;
                    this.p_userid = p_userid;
                    this.comment = comment;
                    this.truename = truename;
                    this.avatarurl = avatarurl;
                    this.id = id;
                    this.userid = userid;
                    this.time = time;
                    this.p_name = p_name;
                }

                public void setStype(String stype) {
                    this.stype = stype;
                }

                public void setP_userid(String p_userid) {
                    this.p_userid = p_userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public void setP_name(String p_name) {
                    this.p_name = p_name;
                }

                public String getStype() {
                    return stype;
                }

                public String getP_userid() {
                    return p_userid;
                }

                public String getUserid() {
                    return userid;
                }

                public String getP_name() {
                    return p_name;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTime() {
                    return time;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getTruename() {
                    return truename;
                }

                public void setTruename(String truename) {
                    this.truename = truename;
                }

                public String getAvatarurl() {
                    return avatarurl;
                }

                public void setAvatarurl(String avatarurl) {
                    this.avatarurl = avatarurl;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
