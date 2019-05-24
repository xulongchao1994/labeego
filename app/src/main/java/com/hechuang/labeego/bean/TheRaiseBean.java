package com.hechuang.labeego.bean;

import java.util.List;

public class TheRaiseBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":[{"status":"待付款","AddDate":"2018-08-21","InnerOrderId":"20180821172718289926","proid":"3","url":"http://192.168.1.170:8060/index.php/Home/Zcart/order_pay/id/20180821172718289926","orderdetail":[{"ProName":"引渤入疆001","ProImg":"http://192.168.1.170:8060/Upload/zProduct/2018-08-20/153477940586256.jpg","price":"20000.000","pv":"送20000.000","pronum":"1"}],"zhongjg":"共1种商品,合计:20000.000元","zhongfl":"送20000.000"}]}
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
         * list : [{"status":"待付款","AddDate":"2018-08-21","InnerOrderId":"20180821172718289926","proid":"3","url":"http://192.168.1.170:8060/index.php/Home/Zcart/order_pay/id/20180821172718289926","orderdetail":[{"ProName":"引渤入疆001","ProImg":"http://192.168.1.170:8060/Upload/zProduct/2018-08-20/153477940586256.jpg","price":"20000.000","pv":"送20000.000","pronum":"1"}],"zhongjg":"共1种商品,合计:20000.000元","zhongfl":"送20000.000"}]
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
             * status : 待付款
             * AddDate : 2018-08-21
             * InnerOrderId : 20180821172718289926
             * proid : 3
             * url : http://192.168.1.170:8060/index.php/Home/Zcart/order_pay/id/20180821172718289926
             * orderdetail : [{"ProName":"引渤入疆001","ProImg":"http://192.168.1.170:8060/Upload/zProduct/2018-08-20/153477940586256.jpg","price":"20000.000","pv":"送20000.000","pronum":"1"}]
             * zhongjg : 共1种商品,合计:20000.000元
             * zhongfl : 送20000.000
             */

            private String status;
            private String AddDate;
            private String InnerOrderId;
            private String proid;
            private String info_url;
            private String zhongjg;
            private String zhongfl;
            private String pay_url;
            private List<OrderdetailBean> orderdetail;

            public void setPay_url(String pay_url) {
                this.pay_url = pay_url;
            }

            public String getPay_url() {
                return pay_url;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getProid() {
                return proid;
            }

            public void setProid(String proid) {
                this.proid = proid;
            }

            public void setInfo_url(String info_url) {
                this.info_url = info_url;
            }

            public String getInfo_url() {
                return info_url;
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
                 * ProName : 引渤入疆001
                 * ProImg : http://192.168.1.170:8060/Upload/zProduct/2018-08-20/153477940586256.jpg
                 * price : 20000.000
                 * pv : 送20000.000
                 * pronum : 1
                 */

                private String ProName;
                private String ProImg;
                private String price;
                private String pv;
                private String pronum;

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

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getPv() {
                    return pv;
                }

                public void setPv(String pv) {
                    this.pv = pv;
                }

                public String getPronum() {
                    return pronum;
                }

                public void setPronum(String pronum) {
                    this.pronum = pronum;
                }
            }
        }
    }
}
