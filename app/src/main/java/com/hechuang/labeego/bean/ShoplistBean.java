package com.hechuang.labeego.bean;

import java.util.List;

public class ShoplistBean {

    /**
     * data : {"list":[{"ProId":"1","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-12/154461008085506.jpg","ProName":"测试商品1","SupplierId":"4","proNum":"2人付款","price":"￥5.00","isshipping":"","adbeefee":"可抵扣9990.02"}],"msg":"加载成功！","status":1}
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
         * list : [{"ProId":"1","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-12/154461008085506.jpg","ProName":"测试商品1","SupplierId":"4","proNum":"2人付款","price":"￥5.00","isshipping":"","adbeefee":"可抵扣9990.02"}]
         * msg : 加载成功！
         * status : 1
         */

        private String msg;
        private int status;
        private List<ListBean> list;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ProId : 1
             * ProImg : http://192.168.10.219:8066/Upload/product/2018-12-12/154461008085506.jpg
             * ProName : 测试商品1
             * SupplierId : 4
             * proNum : 2人付款
             * price : ￥5.00
             * isshipping :
             * adbeefee : 可抵扣9990.02
             */

            private String ProId;
            private String ProImg;
            private String ProName;
            private String SupplierId;
            private String proNum;
            private String price;
            private String isshipping;
            private String adbeefee;
            private String url;

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrl() {
                return url;
            }

            public String getProId() {
                return ProId;
            }

            public void setProId(String ProId) {
                this.ProId = ProId;
            }

            public String getProImg() {
                return ProImg;
            }

            public void setProImg(String ProImg) {
                this.ProImg = ProImg;
            }

            public String getProName() {
                return ProName;
            }

            public void setProName(String ProName) {
                this.ProName = ProName;
            }

            public String getSupplierId() {
                return SupplierId;
            }

            public void setSupplierId(String SupplierId) {
                this.SupplierId = SupplierId;
            }

            public String getProNum() {
                return proNum;
            }

            public void setProNum(String proNum) {
                this.proNum = proNum;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIsshipping() {
                return isshipping;
            }

            public void setIsshipping(String isshipping) {
                this.isshipping = isshipping;
            }

            public String getAdbeefee() {
                return adbeefee;
            }

            public void setAdbeefee(String adbeefee) {
                this.adbeefee = adbeefee;
            }
        }
    }
}
