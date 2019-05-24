package com.hechuang.labeego.bean;

import java.util.List;

public class StoreConfirmanorder_Shoppingcar_Bean {

    /**
     * data : {"status":1,"message":"加载成功","order_token":"530c7d85ffc18518c7f82bb8e2dcf348","sprice":231.2,"address":{"UserId":"9611230","ReceiveName":"测试","Mobile":"18745788969","Address":"浙江省杭州市上城区闹闹测试不要发货订单"},"list":[{"goodsfreight":7,"sprice":231.2,"adbeefee":0.18,"supid":"4","supname":"茅台镇珍豪酒业有限公司","no":[{"proimg":"http://192.168.10.219:8066/Upload/product/2018-12-14/thumb_154475768247597.jpg","proid":"11","pronum":"2","stylename":"江小白","styleid":17,"shopprice":"99.00","templateid":"38","proname":"江小白"},{"proimg":"http://192.168.10.219:8066/Upload/product/2018-12-14/thumb_154475790276783.jpg","proid":"14","pronum":"2","stylename":"经典速食","styleid":20,"shopprice":"16.60","templateid":"39","proname":"经典速食"}]}],"adbeefee":{"kyfengbi":0.18,"kyaccountmoney":0.18,"kylafeng":"0.120","sjmoney":0.18}}
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
         * message : 加载成功
         * order_token : 530c7d85ffc18518c7f82bb8e2dcf348
         * sprice : 231.2
         * address : {"UserId":"9611230","ReceiveName":"测试","Mobile":"18745788969","Address":"浙江省杭州市上城区闹闹测试不要发货订单"}
         * list : [{"goodsfreight":7,"sprice":231.2,"adbeefee":0.18,"supid":"4","supname":"茅台镇珍豪酒业有限公司","no":[{"proimg":"http://192.168.10.219:8066/Upload/product/2018-12-14/thumb_154475768247597.jpg","proid":"11","pronum":"2","stylename":"江小白","styleid":17,"shopprice":"99.00","templateid":"38","proname":"江小白"},{"proimg":"http://192.168.10.219:8066/Upload/product/2018-12-14/thumb_154475790276783.jpg","proid":"14","pronum":"2","stylename":"经典速食","styleid":20,"shopprice":"16.60","templateid":"39","proname":"经典速食"}]}]
         * adbeefee : {"kyfengbi":0.18,"kyaccountmoney":0.18,"kylafeng":"0.120","sjmoney":0.18}
         */

        private int status;
        private String message;
        private String order_token;
        private double sprice;
        private AddressBean address;
        private List<AdbeefeeBean> adbeefee;
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

        public String getOrder_token() {
            return order_token;
        }

        public void setOrder_token(String order_token) {
            this.order_token = order_token;
        }

        public double getSprice() {
            return sprice;
        }

        public void setSprice(double sprice) {
            this.sprice = sprice;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public List<AdbeefeeBean> getAdbeefee() {
            return adbeefee;
        }

        public void setAdbeefee(List<AdbeefeeBean> adbeefee) {
            this.adbeefee = adbeefee;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AddressBean {
            /**
             * UserId : 9611230
             * ReceiveName : 测试
             * Mobile : 18745788969
             * Address : 浙江省杭州市上城区闹闹测试不要发货订单
             */

            private String UserId;
            private String ReceiveName;
            private String Mobile;
            private String Province;

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getReceiveName() {
                return ReceiveName;
            }

            public void setReceiveName(String ReceiveName) {
                this.ReceiveName = ReceiveName;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getAddress() {
                return Province;
            }

            public void setAddress(String Address) {
                this.Province = Address;
            }

            @Override
            public String toString() {
                return "AddressBean{" +
                        "UserId='" + UserId + '\'' +
                        ", ReceiveName='" + ReceiveName + '\'' +
                        ", Mobile='" + Mobile + '\'' +
                        ", Address='" + Province + '\'' +
                        '}';
            }
        }

        //        public static class AdbeefeeBean {
//            /**
//             * kyfengbi : 0.18
//             * kyaccountmoney : 0.18
//             * kylafeng : 0.120
//             * sjmoney : 0.18
//             */
//
//            private double kyfengbi;
//            private double kyaccountmoney;
//            private String kylafeng;
//            private double sjmoney;
//
//            public double getKyfengbi() {
//                return kyfengbi;
//            }
//
//            public void setKyfengbi(double kyfengbi) {
//                this.kyfengbi = kyfengbi;
//            }
//
//            public double getKyaccountmoney() {
//                return kyaccountmoney;
//            }
//
//            public void setKyaccountmoney(double kyaccountmoney) {
//                this.kyaccountmoney = kyaccountmoney;
//            }
//
//            public String getKylafeng() {
//                return kylafeng;
//            }
//
//            public void setKylafeng(String kylafeng) {
//                this.kylafeng = kylafeng;
//            }
//
//            public double getSjmoney() {
//                return sjmoney;
//            }
//
//            public void setSjmoney(double sjmoney) {
//                this.sjmoney = sjmoney;
//            }
//        }
        public static class AdbeefeeBean {
            private String show;
            private String money;
            private String judge;

            public void setShow(String show) {
                this.show = show;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public void setJudge(String judge) {
                this.judge = judge;
            }

            public String getShow() {
                return show;
            }

            public String getMoney() {
                return money;
            }

            public String getJudge() {
                return judge;
            }

            @Override
            public String toString() {
                return "AdbeefeeBean{" +
                        "show='" + show + '\'' +
                        ", money='" + money + '\'' +
                        ", judge='" + judge + '\'' +
                        '}';
            }
        }

        public static class ListBean {
            /**
             * goodsfreight : 7
             * sprice : 231.2
             * adbeefee : 0.18
             * supid : 4
             * supname : 茅台镇珍豪酒业有限公司
             * no : [{"proimg":"http://192.168.10.219:8066/Upload/product/2018-12-14/thumb_154475768247597.jpg","proid":"11","pronum":"2","stylename":"江小白","styleid":17,"shopprice":"99.00","templateid":"38","proname":"江小白"},{"proimg":"http://192.168.10.219:8066/Upload/product/2018-12-14/thumb_154475790276783.jpg","proid":"14","pronum":"2","stylename":"经典速食","styleid":20,"shopprice":"16.60","templateid":"39","proname":"经典速食"}]
             */

            private int goodsfreight;
            private double sprice;
            private String adbeefee;
            private String supid;
            private String supname;
            private String message;
            private String distribution;
            private List<NoBean> no;

            public void setDistribution(String distribution) {
                this.distribution = distribution;
            }

            public String getDistribution() {
                return distribution;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }

            public int getGoodsfreight() {
                return goodsfreight;
            }

            public void setGoodsfreight(int goodsfreight) {
                this.goodsfreight = goodsfreight;
            }

            public double getSprice() {
                return sprice;
            }

            public void setSprice(double sprice) {
                this.sprice = sprice;
            }

            public String getAdbeefee() {
                return adbeefee;
            }

            public void setAdbeefee(String adbeefee) {
                this.adbeefee = adbeefee;
            }

            public String getSupid() {
                return supid;
            }

            public void setSupid(String supid) {
                this.supid = supid;
            }

            public String getSupname() {
                return supname;
            }

            public void setSupname(String supname) {
                this.supname = supname;
            }

            public List<NoBean> getNo() {
                return no;
            }

            public void setNo(List<NoBean> no) {
                this.no = no;
            }


            public static class NoBean {
                /**
                 * proimg : http://192.168.10.219:8066/Upload/product/2018-12-14/thumb_154475768247597.jpg
                 * proid : 11
                 * pronum : 2
                 * stylename : 江小白
                 * styleid : 17
                 * shopprice : 99.00
                 * templateid : 38
                 * proname : 江小白
                 */

                private String proimg;
                private String proid;
                private String pronum;
                private String stylename;
                private int styleid;
                private String shopprice;
                private String templateid;
                private String proname;

                public String getProimg() {
                    return proimg;
                }

                public void setProimg(String proimg) {
                    this.proimg = proimg;
                }

                public String getProid() {
                    return proid;
                }

                public void setProid(String proid) {
                    this.proid = proid;
                }

                public String getPronum() {
                    return pronum;
                }

                public void setPronum(String pronum) {
                    this.pronum = pronum;
                }

                public String getStylename() {
                    return stylename;
                }

                public void setStylename(String stylename) {
                    this.stylename = stylename;
                }

                public int getStyleid() {
                    return styleid;
                }

                public void setStyleid(int styleid) {
                    this.styleid = styleid;
                }

                public String getShopprice() {
                    return shopprice;
                }

                public void setShopprice(String shopprice) {
                    this.shopprice = shopprice;
                }

                public String getTemplateid() {
                    return templateid;
                }

                public void setTemplateid(String templateid) {
                    this.templateid = templateid;
                }

                public String getProname() {
                    return proname;
                }

                public void setProname(String proname) {
                    this.proname = proname;
                }

                @Override
                public String toString() {
                    return "NoBean{" +
                            "proimg='" + proimg + '\'' +
                            ", proid='" + proid + '\'' +
                            ", pronum='" + pronum + '\'' +
                            ", stylename='" + stylename + '\'' +
                            ", styleid=" + styleid +
                            ", shopprice='" + shopprice + '\'' +
                            ", templateid='" + templateid + '\'' +
                            ", proname='" + proname + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "goodsfreight=" + goodsfreight +
                        ", sprice=" + sprice +
                        ", adbeefee='" + adbeefee + '\'' +
                        ", supid='" + supid + '\'' +
                        ", supname='" + supname + '\'' +
                        ", message='" + message + '\'' +
                        ", no=" + no +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", order_token='" + order_token + '\'' +
                    ", sprice=" + sprice +
                    ", address=" + address +
                    ", adbeefee=" + adbeefee +
                    ", list=" + list +
                    '}';
        }
    }
}
