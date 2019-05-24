package com.hechuang.labeego.bean;

import java.util.List;

public class Order_ture_list_bean {

    /**
     * data : {"status":1,"message":"数据获取成功","pronum":2,"heji":13000,"usermoney":"9999999.00元","list":[{"ProName":"茅台镇珍豪酒业\u2014【五星国宾酒10箱】42°500ml/瓶*6/箱","proNum":"1","Price":"3000.00元"},{"ProName":"茅台镇珍豪酒业\u2014\u2014【老酱酒27箱】酱香型53° 500ml/瓶*4/箱","proNum":"1","Price":"10000.00元"}]}
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
         * pronum : 2
         * heji : 13000
         * usermoney : 9999999.00元
         * list : [{"ProName":"茅台镇珍豪酒业\u2014【五星国宾酒10箱】
         * 42°500ml/瓶*6/箱","proNum":"1","Price":"3000.00元"},
         * {"ProName":"茅台镇珍豪酒业\u2014\u2014【老酱酒27箱】酱香型53° 500ml/瓶*4/箱","proNum":"1","Price":"10000.00元"}]
         */

        private String status;
        private String message;
        private String pronum;
        private String heji;
        private String usermoney;
        private String pay_token;
        private String type;
        private String serviceid;
        private String beemoneynum;
        private String servicename;
        private String labeemoney;
        private String servicemobile;
        private String beemoney;
        private String beemoneynummin;
        private String couponbalance;
        private String couponmin;
        private String couponmax;
        private String usermoneymax;

        @Override
        public String toString() {
            return "DataBean{" +
                    "status='" + status + '\'' +
                    ", message='" + message + '\'' +
                    ", pronum='" + pronum + '\'' +
                    ", heji='" + heji + '\'' +
                    ", usermoney='" + usermoney + '\'' +
                    ", pay_token='" + pay_token + '\'' +
                    ", type='" + type + '\'' +
                    ", serviceid='" + serviceid + '\'' +
                    ", beemoneynum='" + beemoneynum + '\'' +
                    ", servicename='" + servicename + '\'' +
                    ", labeemoney='" + labeemoney + '\'' +
                    ", servicemobile='" + servicemobile + '\'' +
                    ", beemoney='" + beemoney + '\'' +
                    ", beemoneynummin='" + beemoneynummin + '\'' +
                    ", couponbalance='" + couponbalance + '\'' +
                    ", couponmin='" + couponmin + '\'' +
                    ", couponmax='" + couponmax + '\'' +
                    ", list=" + list +
                    '}';
        }

        public void setUsermoneymax(String usermoneymax) {
            this.usermoneymax = usermoneymax;
        }

        public String getUsermoneymax() {
            return usermoneymax;
        }

        public void setCouponbalance(String couponbalance) {
            this.couponbalance = couponbalance;
        }

        public void setCouponmin(String couponmin) {
            this.couponmin = couponmin;
        }

        public void setCouponmax(String couponmax) {
            this.couponmax = couponmax;
        }

        public String getCouponbalance() {
            return couponbalance;
        }

        public String getCouponmin() {
            return couponmin;
        }

        public String getCouponmax() {
            return couponmax;
        }


        public void setLabeemoney(String labeemoney) {
            this.labeemoney = labeemoney;
        }

        public String getLabeemoney() {
            return labeemoney;
        }

        public void setBeemoneynummin(String beemoneynummin) {
            this.beemoneynummin = beemoneynummin;
        }

        public String getBeemoneynummin() {
            return beemoneynummin;
        }

        public void setServicename(String servicename) {
            this.servicename = servicename;
        }

        public void setServicemobile(String servicemobile) {
            this.servicemobile = servicemobile;
        }

        public String getServicename() {
            return servicename;
        }

        public String getServicemobile() {
            return servicemobile;
        }

        public void setBeemoneynum(String beemoneynum) {
            this.beemoneynum = beemoneynum;
        }

        public void setBeemoney(String beemoney) {
            this.beemoney = beemoney;
        }

        public String getBeemoneynum() {
            return beemoneynum;
        }

        public String getBeemoney() {
            return beemoney;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setServiceid(String serviceid) {
            this.serviceid = serviceid;
        }

        public String getType() {
            return type;
        }

        public String getServiceid() {
            return serviceid;
        }

        private List<ListBean> list;

        public void setPay_token(String pay_token) {
            this.pay_token = pay_token;
        }

        public String getPay_token() {
            return pay_token;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPronum() {
            return pronum;
        }

        public void setPronum(String pronum) {
            this.pronum = pronum;
        }

        public String getHeji() {
            return heji;
        }

        public void setHeji(String heji) {
            this.heji = heji;
        }

        public String getUsermoney() {
            return usermoney;
        }

        public void setUsermoney(String usermoney) {
            this.usermoney = usermoney;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ProName : 茅台镇珍豪酒业—【五星国宾酒10箱】42°500ml/瓶*6/箱
             * proNum : 1
             * Price : 3000.00元
             */

            private String ProName;
            private String proNum;
            private String Price;

            @Override
            public String toString() {
                return "ListBean{" +
                        "ProName='" + ProName + '\'' +
                        ", proNum='" + proNum + '\'' +
                        ", Price='" + Price + '\'' +
                        '}';
            }

            public String getProName() {
                return ProName;
            }

            public void setProName(String ProName) {
                this.ProName = ProName;
            }

            public String getProNum() {
                return proNum;
            }

            public void setProNum(String proNum) {
                this.proNum = proNum;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }
        }
    }
}
