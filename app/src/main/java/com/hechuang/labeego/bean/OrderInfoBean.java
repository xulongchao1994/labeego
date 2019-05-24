package com.hechuang.labeego.bean;

import java.util.List;

public class OrderInfoBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":[{"Status":"过期订单","AddDate":"2018-06-15 10:13:55","PayDate":"","DeliverDate":"","ReceiveDate":"","Message":"","InnerOrderId":"20180615101355889916_40","UserTel":"15038853637","ReceiveName":"陈伟涛","Supplier":"凝紫堂养生科技有限公司","address":"河南省 郑州市 新郑市 中原电商大厦","downtime":"","orderdetail":[{"ProName":"汉方草本膏+复合益生菌+胶原蛋白酵素膏 各十盒","StyleName":"套","proNum":"2","StyleId":"342","Pv":"送 10000.00","Price":"10000.00元","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-06-14/152894888789021.jpg"}],"zhongjg":"共1种商品,合计:20000.00元","zhongfl":"送20000.00"}]}
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
         * message : 数据获取成功
         * list : [{"Status":"过期订单","AddDate":"2018-06-15 10:13:55","PayDate":"","DeliverDate":"","ReceiveDate":"","Message":"","InnerOrderId":"20180615101355889916_40","UserTel":"15038853637","ReceiveName":"陈伟涛","Supplier":"凝紫堂养生科技有限公司","address":"河南省 郑州市 新郑市 中原电商大厦","downtime":"","orderdetail":[{"ProName":"汉方草本膏+复合益生菌+胶原蛋白酵素膏 各十盒","StyleName":"套","proNum":"2","StyleId":"342","Pv":"送 10000.00","Price":"10000.00元","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-06-14/152894888789021.jpg"}],"zhongjg":"共1种商品,合计:20000.00元","zhongfl":"送20000.00"}]
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
             * Status : 过期订单
             * AddDate : 2018-06-15 10:13:55
             * PayDate :
             * DeliverDate :
             * ReceiveDate :
             * Message :
             * InnerOrderId : 20180615101355889916_40
             * UserTel : 15038853637
             * ReceiveName : 陈伟涛
             * Supplier : 凝紫堂养生科技有限公司
             * address : 河南省 郑州市 新郑市 中原电商大厦
             * downtime :
             * orderdetail : [{"ProName":"汉方草本膏+复合益生菌+胶原蛋白酵素膏 各十盒","StyleName":"套","proNum":"2","StyleId":"342","Pv":"送 10000.00","Price":"10000.00元","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-06-14/152894888789021.jpg"}]
             * zhongjg : 共1种商品,合计:20000.00元
             * zhongfl : 送20000.00
             */

            private String Status;
            private String AddDate;
            private String PayDate;
            private String DeliverDate;
            private String ReceiveDate;
            private String Message;
            private String InnerOrderId;
            private String UserTel;
            private String ReceiveName;
            private String Supplier;
            private String address;
            private String downtime;
            private String endtime;
            private String hour;
            private String minute;
            private String second;
            private String secondtime;
            private String zhongjg;
            private String zhongfl;
            private String comment_status;
            private String AdminMessage;
            private String Service;
            private List<OrderdetailBean> orderdetail;

            public void setAdminMessage(String adminMessage) {
                AdminMessage = adminMessage;
            }

            public String getAdminMessage() {
                return AdminMessage;
            }

            public void setServiceID(String serviceID) {
                Service = serviceID;
            }

            public String getServiceID() {
                return Service;
            }

            public void setComment_status(String comment_status) {
                comment_status = comment_status;
            }

            public String getComment_status() {
                return comment_status;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public void setMinute(String minute) {
                this.minute = minute;
            }

            public void setSecond(String second) {
                this.second = second;
            }

            public void setSecondtime(String secondtime) {
                this.secondtime = secondtime;
            }

            public String getEndtime() {
                return endtime;
            }

            public String getHour() {
                return hour;
            }

            public String getMinute() {
                return minute;
            }

            public String getSecond() {
                return second;
            }

            public String getSecondtime() {
                return secondtime;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }

            public String getAddDate() {
                return AddDate;
            }

            public void setAddDate(String AddDate) {
                this.AddDate = AddDate;
            }

            public String getPayDate() {
                return PayDate;
            }

            public void setPayDate(String PayDate) {
                this.PayDate = PayDate;
            }

            public String getDeliverDate() {
                return DeliverDate;
            }

            public void setDeliverDate(String DeliverDate) {
                this.DeliverDate = DeliverDate;
            }

            public String getReceiveDate() {
                return ReceiveDate;
            }

            public void setReceiveDate(String ReceiveDate) {
                this.ReceiveDate = ReceiveDate;
            }

            public String getMessage() {
                return Message;
            }

            public void setMessage(String Message) {
                this.Message = Message;
            }

            public String getInnerOrderId() {
                return InnerOrderId;
            }

            public void setInnerOrderId(String InnerOrderId) {
                this.InnerOrderId = InnerOrderId;
            }

            public String getUserTel() {
                return UserTel;
            }

            public void setUserTel(String UserTel) {
                this.UserTel = UserTel;
            }

            public String getReceiveName() {
                return ReceiveName;
            }

            public void setReceiveName(String ReceiveName) {
                this.ReceiveName = ReceiveName;
            }

            public String getSupplier() {
                return Supplier;
            }

            public void setSupplier(String Supplier) {
                this.Supplier = Supplier;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDowntime() {
                return downtime;
            }

            public void setDowntime(String downtime) {
                this.downtime = downtime;
            }

            public String getZhongjg() {
                return zhongjg;
            }

            public void setZhongjg(String zhongjg) {
                this.zhongjg = zhongjg;
            }

            public String getZhongfl() {
                return zhongfl;
            }

            public void setZhongfl(String zhongfl) {
                this.zhongfl = zhongfl;
            }

            public List<OrderdetailBean> getOrderdetail() {
                return orderdetail;
            }

            public void setOrderdetail(List<OrderdetailBean> orderdetail) {
                this.orderdetail = orderdetail;
            }

            public static class OrderdetailBean {
                /**
                 * ProName : 汉方草本膏+复合益生菌+胶原蛋白酵素膏 各十盒
                 * StyleName : 套
                 * proNum : 2
                 * StyleId : 342
                 * Pv : 送 10000.00
                 * Price : 10000.00元
                 * ProImg : http://lafeng.hetianpay.com/Upload/Product/2018-06-14/152894888789021.jpg
                 */

                private String ProName;
                private String StyleName;
                private String proNum;
                private String StyleId;
                private String Pv;
                private String ProId;
                private String Price;
                private String ProImg;

                public void setProId(String proId) {
                    ProId = proId;
                }

                public String getProId() {
                    return ProId;
                }

                public String getProName() {
                    return ProName;
                }

                public void setProName(String ProName) {
                    this.ProName = ProName;
                }

                public String getStyleName() {
                    return StyleName;
                }

                public void setStyleName(String StyleName) {
                    this.StyleName = StyleName;
                }

                public String getProNum() {
                    return proNum;
                }

                public void setProNum(String proNum) {
                    this.proNum = proNum;
                }

                public String getStyleId() {
                    return StyleId;
                }

                public void setStyleId(String StyleId) {
                    this.StyleId = StyleId;
                }

                public String getPv() {
                    return Pv;
                }

                public void setPv(String Pv) {
                    this.Pv = Pv;
                }

                public String getPrice() {
                    return Price;
                }

                public void setPrice(String Price) {
                    this.Price = Price;
                }

                public String getProImg() {
                    return ProImg;
                }

                public void setProImg(String ProImg) {
                    this.ProImg = ProImg;
                }
            }
        }
    }
}
