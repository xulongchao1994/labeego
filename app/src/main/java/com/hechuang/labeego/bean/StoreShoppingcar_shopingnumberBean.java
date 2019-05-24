package com.hechuang.labeego.bean;

public class StoreShoppingcar_shopingnumberBean {

    /**
     * data : {"status":1,"message":"修改成功!","price":891,"pronum":9}
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
         * message : 修改成功!
         * price : 891
         * pronum : 9
         */

        private int status;
        private String message;
        private int price;
        private int pronum;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPronum() {
            return pronum;
        }

        public void setPronum(int pronum) {
            this.pronum = pronum;
        }
    }
}
