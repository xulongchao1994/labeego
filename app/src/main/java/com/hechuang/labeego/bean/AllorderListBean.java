package com.hechuang.labeego.bean;

import java.util.List;

public class AllorderListBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":[{"Status":"过期订单","AddDate":"2018-06-12","InnerOrderId":"20180612215924873822","orderdetail":[{"ProName":"四维润通-山药磷脂粉5盒","StyleName":"组合","proNum":"1","StyleId":"319","Pv":"送 3000.00","Price":"3000.00元","Supplier":"河南四通生物科技有限公司","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-06-12/152879449534222.jpg"}],"zhongjg":"共1种商品,合计:3000.00元","zhongfl":"送3000.00"}]}
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
         * list : [{"Status":"过期订单","AddDate":"2018-06-12","InnerOrderId":"20180612215924873822","orderdetail":[{"ProName":"四维润通-山药磷脂粉5盒","StyleName":"组合","proNum":"1","StyleId":"319","Pv":"送 3000.00","Price":"3000.00元","Supplier":"河南四通生物科技有限公司","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-06-12/152879449534222.jpg"}],"zhongjg":"共1种商品,合计:3000.00元","zhongfl":"送3000.00"}]
         */

        private int status;
        private String message;
        private List<ListBean> list;

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list=" + list +
                    '}';
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * Status : 过期订单
             * AddDate : 2018-06-12
             * InnerOrderId : 20180612215924873822
             * orderdetail : [{"ProName":"四维润通-山药磷脂粉5盒","StyleName":"组合","proNum":"1","StyleId":"319","Pv":"送 3000.00","Price":"3000.00元","Supplier":"河南四通生物科技有限公司","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-06-12/152879449534222.jpg"}]
             * zhongjg : 共1种商品,合计:3000.00元
             * zhongfl : 送3000.00
             */

            private String Status;
            private String AddDate;
            private String InnerOrderId;
            private String zhongjg;
            private String zhongfl;
            private String comment_status;
            private List<OrderdetailBean> orderdetail;

            @Override
            public String toString() {
                return "ListBean{" +
                        "Status='" + Status + '\'' +
                        ", AddDate='" + AddDate + '\'' +
                        ", InnerOrderId='" + InnerOrderId + '\'' +
                        ", zhongjg='" + zhongjg + '\'' +
                        ", zhongfl='" + zhongfl + '\'' +
                        ", comment_status='" + comment_status + '\'' +
                        ", orderdetail=" + orderdetail +
                        '}';
            }

            public void setComment_status(String comment_status) {
                this.comment_status = comment_status;
            }

            public String getComment_status() {
                return comment_status;
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

            public String getInnerOrderId() {
                return InnerOrderId;
            }

            public void setInnerOrderId(String InnerOrderId) {
                this.InnerOrderId = InnerOrderId;
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
                 * ProName : 四维润通-山药磷脂粉5盒
                 * StyleName : 组合
                 * proNum : 1
                 * StyleId : 319
                 * Pv : 送 3000.00
                 * Price : 3000.00元
                 * Supplier : 河南四通生物科技有限公司
                 * ProImg : http://lafeng.hetianpay.com/Upload/Product/2018-06-12/152879449534222.jpg
                 */

                private String ProName;
                private String StyleName;
                private String proNum;
                private String StyleId;
                private String Pv;
                private String Price;
                private String Supplier;
                private String ProImg;

                @Override
                public String toString() {
                    return "OrderdetailBean{" +
                            "ProName='" + ProName + '\'' +
                            ", StyleName='" + StyleName + '\'' +
                            ", proNum='" + proNum + '\'' +
                            ", StyleId='" + StyleId + '\'' +
                            ", Pv='" + Pv + '\'' +
                            ", Price='" + Price + '\'' +
                            ", Supplier='" + Supplier + '\'' +
                            ", ProImg='" + ProImg + '\'' +
                            '}';
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
