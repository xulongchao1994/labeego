package com.hechuang.labeego.bean;

import java.util.List;

public class SearchOrderBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":[{"InnerOrderId":"20180626142137901260","Status":"已完成","AddDate":"2018-06-26","orderdetail":[{"ProName":"久零网精品馆代金券","StyleName":"1元/张","proNum":"1","StyleId":"296","Pv":"送 1.00","Price":"1.00元","Supplier":"久零网","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-07-13/153146656612952.jpg"},{"ProName":"久零网精品馆代金券","StyleName":"1元/张","proNum":"1","StyleId":"296","Pv":"送 1.00","Price":"1.00元","Supplier":"久零网","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-07-13/153146656612952.jpg"}],"zhongjg":"共2种商品,合计:2.00元","zhongfl":"送2.00"}]}
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
         * list : [{"InnerOrderId":"20180626142137901260","Status":"已完成","AddDate":"2018-06-26","orderdetail":[{"ProName":"久零网精品馆代金券","StyleName":"1元/张","proNum":"1","StyleId":"296","Pv":"送 1.00","Price":"1.00元","Supplier":"久零网","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-07-13/153146656612952.jpg"},{"ProName":"久零网精品馆代金券","StyleName":"1元/张","proNum":"1","StyleId":"296","Pv":"送 1.00","Price":"1.00元","Supplier":"久零网","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-07-13/153146656612952.jpg"}],"zhongjg":"共2种商品,合计:2.00元","zhongfl":"送2.00"}]
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
             * InnerOrderId : 20180626142137901260
             * Status : 已完成
             * AddDate : 2018-06-26
             * orderdetail : [{"ProName":"久零网精品馆代金券","StyleName":"1元/张","proNum":"1","StyleId":"296","Pv":"送 1.00","Price":"1.00元","Supplier":"久零网","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-07-13/153146656612952.jpg"},{"ProName":"久零网精品馆代金券","StyleName":"1元/张","proNum":"1","StyleId":"296","Pv":"送 1.00","Price":"1.00元","Supplier":"久零网","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-07-13/153146656612952.jpg"}]
             * zhongjg : 共2种商品,合计:2.00元
             * zhongfl : 送2.00
             */

            private String InnerOrderId;
            private String Status;
            private String AddDate;
            private String zhongjg;
            private String zhongfl;
            private List<OrderdetailBean> orderdetail;

            public String getInnerOrderId() {
                return InnerOrderId;
            }

            public void setInnerOrderId(String InnerOrderId) {
                this.InnerOrderId = InnerOrderId;
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
                 * ProName : 久零网精品馆代金券
                 * StyleName : 1元/张
                 * proNum : 1
                 * StyleId : 296
                 * Pv : 送 1.00
                 * Price : 1.00元
                 * Supplier : 久零网
                 * ProImg : http://lafeng.hetianpay.com/Upload/product/2018-07-13/153146656612952.jpg
                 */

                private String ProName;
                private String StyleName;
                private String proNum;
                private String StyleId;
                private String Pv;
                private String Price;
                private String Supplier;
                private String ProImg;

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

                public String getSupplier() {
                    return Supplier;
                }

                public void setSupplier(String Supplier) {
                    this.Supplier = Supplier;
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
