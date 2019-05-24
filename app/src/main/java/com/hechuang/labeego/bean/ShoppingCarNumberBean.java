package com.hechuang.labeego.bean;

public class ShoppingCarNumberBean {

    /**
     * data : {"num":"0","status":1,"msg":"购物车中还没有购买商品"}
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
         * num : 0
         * status : 1
         * msg : 购物车中还没有购买商品
         */

        private String num;
        private int status;
        private String msg;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
